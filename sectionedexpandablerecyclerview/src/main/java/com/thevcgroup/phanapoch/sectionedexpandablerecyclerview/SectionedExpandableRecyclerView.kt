package com.thevcgroup.phanapoch.sectionedexpandablerecyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hendraanggrian.widget.ExpandableRecyclerView

/**
 *
 *
 * SectionedExpandableRecyclerView:
 *
 */
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

}