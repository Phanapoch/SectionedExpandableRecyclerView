package com.thevcgroup.phanapoch.sectionedexpandablerecyclerview.example

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.thevcgroup.phanapoch.sectionedexpandablerecyclerview.SectionedExpandableItem
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection

/**
 *
 *
 * MySection: Custom your own view in section instead adapter
 *
 */
class MySection(val section: String, val context: Context) : StatelessSection(SectionParameters.builder()
        .itemResourceId(R.layout.item_row)
        .headerResourceId(R.layout.section_row)
        .build()) {

    private val items = arrayOf(
            Item(R.drawable.ic_test1, "144 Easy Weekend Getaways"),
            Item(R.drawable.ic_test3, "AB Paris Farewell")
    )

    override fun getContentItemsTotal(): Int {
        return items.size // number of items of this section
    }

    override fun getItemViewHolder(view: View): ItemViewHolder {
        return ItemViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as? ItemViewHolder

        val (drawable, title) = items[position]

        itemHolder?.imageView?.setImageDrawable(context?.let { ContextCompat.getDrawable(it, drawable) })
        itemHolder?.textView?.text = title
        itemHolder?.button?.setOnClickListener { Toast.makeText(context, String.format("Clicked! pos: %s section: %s",position,section), Toast.LENGTH_SHORT).show() }
    }

    override fun getHeaderViewHolder(view: View): HeaderViewHolder {
        return HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        val headerHolder = holder as? HeaderViewHolder
        headerHolder?.sectionTitle?.text = section
    }
}

/**
 *
 *
 * Define your content view holder
 *
 */
class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val item: SectionedExpandableItem? = itemView.findViewById(R.id.item_row) as? SectionedExpandableItem
    val imageView: ImageView? = item?.headerLayout?.findViewById(R.id.imageView) as? ImageView
    val textView: TextView? = item?.headerLayout?.findViewById(R.id.textView) as? TextView
    val button: Button? = item?.contentLayout?.findViewById(R.id.button) as? Button
}

class HeaderViewHolder(headerView: View) : RecyclerView.ViewHolder(headerView) {
    val sectionTitle: TextView = headerView.findViewById(R.id.sectionTitle)
}