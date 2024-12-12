package com.jagdon.removal_jagdon.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.jagdon.removal_jagdon.R
import com.jagdon.removal_jagdon.models.MenuItem
import java.util.Locale

class MenuAdapter(
    private val context: Context,
    private var items: MutableList<MenuItem>,
    private val onItemAction: (MenuItem, String) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.menu_list_item, parent, false)

        val titleTextView: TextView = view.findViewById(R.id.menuItemTitle)
        val priceTextView: TextView = view.findViewById(R.id.menuItemPrice)
        val deleteButton: Button = view.findViewById(R.id.menuItemDeleteButton)

        val menuItem = items[position]
        titleTextView.text = menuItem.foodName
        priceTextView.text = String.format(Locale.getDefault(), "%.2f", menuItem.price)

        deleteButton.setOnClickListener {
            onItemAction(menuItem, "delete")
        }

        view.setOnClickListener {
            onItemAction(menuItem, "edit")
        }

        return view
    }

    fun updateData(newItems: List<MenuItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
