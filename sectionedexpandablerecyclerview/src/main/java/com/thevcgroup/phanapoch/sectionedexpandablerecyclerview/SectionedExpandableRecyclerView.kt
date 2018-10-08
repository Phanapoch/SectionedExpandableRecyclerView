package com.thevcgroup.phanapoch.sectionedexpandablerecyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hendraanggrian.widget.ExpandableRecyclerView

//class SectionedExpandableRecyclerView: ExpandableRecyclerView {
//
//    constructor(context: Context?) : super(context!!)
//    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
//    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle)
//
//    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
//        super.setAdapter(adapter as ExpandableRecyclerView.Adapter)
//    }
//}

open class SectionedExpandableRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(context, attrs, defStyle) {

    override fun setLayoutManager(layout: RecyclerView.LayoutManager?) {
        if (layout !is LinearLayoutManager) {
            throw IllegalArgumentException("lm manager must be an instance of LinearLayoutManager!")
        }
        super.setLayoutManager(layout)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
//        if (adapter !is Adapter<*>) {
//            throw IllegalArgumentException("adapter must be an instance of ExpandableRecyclerView.Adapter!")
//        }
        super.setAdapter(adapter)
    }

    abstract class Adapter<VH : RecyclerView.ViewHolder>(private val lm: LinearLayoutManager) : RecyclerView.Adapter<VH>() {
        private var currentPosition = -1

        private val tag: String = "com.hendraanggrian.widget.ExpandableItem"

        override fun onBindViewHolder(holder: VH, position: Int) {
            val expandableItem = holder.itemView.findViewWithTag<View>(tag) as SectionedExpandableItem
            expandableItem.setOnClickListener {
                currentPosition = holder.layoutPosition
                for (index in 0 until lm.childCount) {
                    if (index != currentPosition - lm.findFirstVisibleItemPosition()) {
                        val currentExpandableItem = lm.getChildAt(index)!!.findViewWithTag<View>(tag) as SectionedExpandableItem
                        currentExpandableItem.hide()
                    }
                }
                val expandableItem = lm.getChildAt(currentPosition - lm.findFirstVisibleItemPosition())!!.findViewWithTag<View>(tag) as SectionedExpandableItem
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