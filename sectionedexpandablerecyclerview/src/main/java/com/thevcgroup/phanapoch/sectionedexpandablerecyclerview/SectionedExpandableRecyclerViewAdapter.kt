package com.thevcgroup.phanapoch.sectionedexpandablerecyclerview

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter

open class SectionedExpandableRecyclerViewAdapter(val lm: LinearLayoutManager) : SectionedRecyclerViewAdapter() {
    private var currentPosition = -1

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
        val expandableItem = holder.itemView.findViewWithTag<View>("com.hendraanggrian.widget.ExpandableItem") as SectionedExpandableItem
        expandableItem.setOnClickListener {
//            currentPosition = holder.layoutPosition
            currentPosition = holder.adapterPosition
            Log.d("chen", "currentPosition = "+currentPosition)
            Log.d("chen", " lm.childCount = "+ lm.childCount)
            for (index in 0 until lm.childCount) {
                if (index != currentPosition - lm.findFirstVisibleItemPosition()) {
                    try {
                        val currentExpandableItem = lm.getChildAt(index)!!.findViewWithTag<View>("com.hendraanggrian.widget.ExpandableItem") as SectionedExpandableItem
                        currentExpandableItem.hide()
                        Log.d("chen", ""+index)
                    }catch (e: TypeCastException) {
                        // FIX ME
                        // exception occurred while accessing header or footer
                    }
                }
            }
            try {
                val expandableItem = lm.getChildAt(currentPosition - lm.findFirstVisibleItemPosition())!!.findViewWithTag<View>("com.hendraanggrian.widget.ExpandableItem") as SectionedExpandableItem
                if (expandableItem.isOpened) {
                    expandableItem.hide()
                } else {
                    expandableItem.show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        if (currentPosition != position && expandableItem.isOpened) {
            expandableItem.hideNow()
        } else if (currentPosition == position && !expandableItem.isOpened && !expandableItem.isClosedByUser) {
            expandableItem.showNow()
        }
    }
}