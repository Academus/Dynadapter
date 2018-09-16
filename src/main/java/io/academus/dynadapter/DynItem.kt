package io.academus.dynadapter

import android.content.Context
import android.view.View

abstract class DynItem {
    abstract fun getLayout(): Int
    abstract fun getId(): Long
    abstract fun onBindView(context: Context, position: Int, items: List<DynItem>, view: View)
}