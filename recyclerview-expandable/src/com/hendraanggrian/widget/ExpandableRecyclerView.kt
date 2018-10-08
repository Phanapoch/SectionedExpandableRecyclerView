package com.hendraanggrian.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 * @see ExpandableItem
 */
open class ExpandableRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(context, attrs, defStyle) {

    override fun setLayoutManager(layout: RecyclerView.LayoutManager?) {
        if (layout !is LinearLayoutManager) {
            throw IllegalArgumentException("lm manager must be an instance of LinearLayoutManager!")
        }
        super.setLayoutManager(layout)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        if (adapter !is Adapter<*>) {
            throw IllegalArgumentException("adapter must be an instance of ExpandableRecyclerView.Adapter!")
        }
        super.setAdapter(adapter)
    }

    abstract class Adapter<VH : RecyclerView.ViewHolder>(private val lm: LinearLayoutManager) : RecyclerView.Adapter<VH>() {
        private var currentPosition = -1

        override fun onBindViewHolder(holder: VH, position: Int) {
            val expandableItem = holder.itemView.findViewWithTag<View>(ExpandableItem.TAG) as ExpandableItem
            expandableItem.setOnClickListener {
                currentPosition = holder.layoutPosition
                for (index in 0 until lm.childCount) {
                    if (index != currentPosition - lm.findFirstVisibleItemPosition()) {
                        val currentExpandableItem = lm.getChildAt(index)!!.findViewWithTag<View>(ExpandableItem.TAG) as ExpandableItem
                        currentExpandableItem.hide()
                    }
                }
                val expandableItem = lm.getChildAt(currentPosition - lm.findFirstVisibleItemPosition())!!.findViewWithTag<View>(ExpandableItem.TAG) as ExpandableItem
                if (expandableItem.isOpened) {
                    expandableItem.hide()
                } else {
                    expandableItem.show()
                }
            }
            if (currentPosition != position && expandableItem.isOpened) {
                expandableItem.hideNow()
            } else if (currentPosition == position && !expandableItem.isOpened && !expandableItem.isClosedByUser) {
                expandableItem.showNow()
            }
        }
    }
}