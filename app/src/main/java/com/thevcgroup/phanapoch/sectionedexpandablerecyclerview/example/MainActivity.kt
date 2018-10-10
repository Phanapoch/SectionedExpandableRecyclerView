package com.thevcgroup.phanapoch.sectionedexpandablerecyclerview.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.thevcgroup.phanapoch.sectionedexpandablerecyclerview.SectionedExpandableRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 *
 * MainActivity: Usage example for SectionedExpandableRecyclerView
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = LinearLayoutManager(this)
        val adapter = SectionedExpandableRecyclerViewAdapter(layout)

        adapter.addSection(MySection("A", this))
        adapter.addSection(MySection("B", this))

        recyclerView.setLayoutManager(layout)
        recyclerView.setAdapter(adapter)
    }
}