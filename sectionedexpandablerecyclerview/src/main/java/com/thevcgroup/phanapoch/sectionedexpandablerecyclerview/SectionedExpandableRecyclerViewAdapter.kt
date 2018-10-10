package com.thevcgroup.phanapoch.sectionedexpandablerecyclerview

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter


/**
 *
 *
 * SectionedExpandableRecyclerViewAdapter:
 *
 */
open class SectionedExpandableRecyclerViewAdapter(val lm: LinearLayoutManager) : SectionedRecyclerViewAdapter() {
    private var currentPosition = -1
    private val tag: String = "com.hendraanggrian.widget.ExpandableItem"

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        try {
            setExpandableBehavior(holder, position)
        }catch (e: TypeCastException) {
            // FIX ME
            // exception occurred while accessing header or footer
        }
    }

    private fun setExpandableBehavior(holder: RecyclerView.ViewHolder, position: Int) {
        setExpandableClickListener(holder)

        val expandableItem = holder.itemView.findViewWithTag<View>(tag) as? SectionedExpandableItem
        expandableItem?.let {
            if (currentPosition != position && it.isOpened) {
                it.hideNow()
            } else if (currentPosition == position && !it.isOpened && !it.isClosedByUser) {
                it.showNow()
            }
        }

    }

    private fun setExpandableClickListener(holder: RecyclerView.ViewHolder) {
        val expandableItem = holder.itemView.findViewWithTag<View>(tag) as? SectionedExpandableItem

        expandableItem?.setOnClickListener {
            currentPosition = holder.adapterPosition

            hideOtherItems()
            try {
                val currExpandableItem = lm.getChildAt(currentPosition - lm.findFirstVisibleItemPosition())?.findViewWithTag<View>(tag) as? SectionedExpandableItem
                currExpandableItem?.let {
                    if (currExpandableItem.isOpened) {
                        currExpandableItem.hide()
                    } else {
                        currExpandableItem.show()
                    }
                }
            } catch (e: TypeCastException) {
                e.printStackTrace()
            }
        }
    }

    private fun hideOtherItems(){
        for (index in 0 until lm.childCount) {
            if (index != currentPosition - lm.findFirstVisibleItemPosition()) {
                try {
                    val currentExpandableItem = lm.getChildAt(index)?.findViewWithTag<View>(tag) as? SectionedExpandableItem
                    currentExpandableItem?.hide()
                }catch (e: TypeCastException) {
                    // FIX ME
                    // exception occurred while accessing header or footer
                }
            }
        }
    }
}