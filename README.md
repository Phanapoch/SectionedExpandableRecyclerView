[![Release](https://jitpack.io/v/Phanapoch/SectionedExpandableRecyclerView.svg)](https://jitpack.io/#Phanapoch/SectionedExpandableRecyclerView)
[![Download](https://jitpack.io/v/Phanapoch/SectionedExpandableRecyclerView/month.svg)](https://jitpack.io/#Phanapoch/SectionedExpandableRecyclerView)

# SectionedExpandableRecyclerView
An improvement recyclerview-expandable integrated with SectionedRecyclerViewAdapter

Demo: [Nexus 5](https://appetize.io/app/3g4fpbhvqnpxgbwprxgrp65hmr?device=nexus5&language=en)

![SectionedExpandableRecyclerView GIF](https://github.com/Phanapoch/SectionedExpandableRecyclerView/blob/master/demo/demo1.gif)

## Download
```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
  
dependencies {
	implementation 'com.github.Phanapoch:SectionedExpandableRecyclerView:0.1.2'
}
```

## Usage
1.  create `SectionedExpandableRecyclerView` somewhere in your app
```xml
 <com.thevcgroup.phanapoch.sectionedexpandablerecyclerview.SectionedExpandableRecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:clipToPadding="false"
        android:paddingBottom="8dp"
        android:paddingTop="8dp" />
```
2.  define your item_row and section_row
```xml
<!-- item_row -->
    <com.thevcgroup.phanapoch.sectionedexpandablerecyclerview.SectionedExpandableItem
        android:id="@+id/item_row"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layoutContent="@layout/view_content"
        app:layoutHeader="@layout/view_header"/>

<!-- section_row -->
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        tools:text="Title test text"
        android:id="@+id/sectionTitle"/>

```
3.  custom a section class by extend `StatelessSection`
```kotlin
class MySection(val section: String, val context: Context) : StatelessSection(SectionParameters.builder()
        .itemResourceId(R.layout.item_row)
        .headerResourceId(R.layout.section_row)
        .build()) {

    private val items = arrayOf(
            Item(R.drawable.ic_test1, "14 Easy Weekend Getaways"),
            Item(R.drawable.ic_test3, "A Paris Farewell")
    )

    override fun getContentItemsTotal(): Int {
        return items.size
    }

    override fun getItemViewHolder(view: View?): ItemViewHolder {
        return ItemViewHolder(view!!)
    }
	// Expandable Item
    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as ItemViewHolder

        val (drawable, title) = items[position]

        itemHolder.imageView.setImageDrawable(context?.let { ContextCompat.getDrawable(it, drawable) })
        itemHolder.textView.text = title
    }
	// Section Header
    override fun getHeaderViewHolder(view: View?): HeaderViewHolder {
        return HeaderViewHolder(view!!)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        val headerHolder = holder as HeaderViewHolder
        headerHolder.sectionTitle.text = section
    }
}
```
4.  ViewHolder part
```kotlin
class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val item: SectionedExpandableItem = itemView.findViewById(R.id.item_row) as SectionedExpandableItem
    val imageView: ImageView = item.headerLayout.findViewById(R.id.imageView) as ImageView
    val textView: TextView = item.headerLayout.findViewById(R.id.textView) as TextView
}

class HeaderViewHolder(headerView: View) : RecyclerView.ViewHolder(headerView) {
    val sectionTitle: TextView = headerView.findViewById(R.id.sectionTitle)
}
```

5.  final setup your recyclerview
```kotlin
val layout = LinearLayoutManager(this)
val adapter = SectionedExpandableRecyclerViewAdapter(layout)

adapter.addSection(MySection("A", this))
adapter.addSection(MySection("B", this))

recyclerView.setLayoutManager(layout)
recyclerView.setAdapter(adapter)
```

## Credits project
-  [recyclerview-expandable](https://github.com/hendraanggrian/recyclerview-expandable)
-  [SectionedRecyclerViewAdapter](https://github.com/luizgrp/SectionedRecyclerViewAdapter)

## License
SectionedExpandableRecyclerView is available under the MIT license
