package com.thevcgroup.phanapoch.sectionedexpandablerecyclerview

import android.content.Context
import android.util.AttributeSet
import com.hendraanggrian.widget.ExpandableItem

/**
 *
 *
 * This class is no modify logic. It's just wrapper of ExpandableItem
 *
 */
class SectionedExpandableItem: ExpandableItem {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)


}