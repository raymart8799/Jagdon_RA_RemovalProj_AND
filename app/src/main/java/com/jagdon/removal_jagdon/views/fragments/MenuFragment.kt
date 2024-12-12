package com.jagdon.removal_jagdon.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import android.util.Log
import com.jagdon.removal_jagdon.R
import com.jagdon.removal_jagdon.models.MenuItem
import com.jagdon.removal_jagdon.viewmodels.MenuViewModel
import com.jagdon.removal_jagdon.views.adapters.MenuAdapter
import com.jagdon.removal_jagdon.views.dialogs.AddItemDialog
import com.jagdon.removal_jagdon.views.dialogs.EditItemDialog

class MenuFragment : Fragment() {
    private lateinit var viewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val addButton: ImageButton = view.findViewById(R.id.addItemButton)
        val listView: ListView = view.findViewById(R.id.menuListView)

        viewModel = ViewModelProvider(this)[MenuViewModel::class.java]

        val adapter = MenuAdapter(requireContext(), mutableListOf()) { item, action ->
            when (action) {
                "edit" -> showEditDialog(item)
                "delete" -> viewModel.deleteItem(item.id)
            }
        }
        listView.adapter = adapter

        viewModel.menuItems.observe(viewLifecycleOwner) { items ->
            Log.d("MenuFragment", "Updated items: $items")
            adapter.updateData(items)
        }

        viewModel.fetchMenuItems()

        addButton.setOnClickListener {
            val dialog = AddItemDialog(requireContext()) { newItem ->
                Log.d("MenuFragment", "New item: ${newItem.foodName}, ${newItem.price}")
                viewModel.addItem(newItem)
            }
            dialog.show()
        }

        return view
    }

    private fun showEditDialog(item: MenuItem) {
        val dialog = EditItemDialog(requireContext(), item) { updatedItem ->
            viewModel.editItem(updatedItem.id, updatedItem)
        }
        dialog.show()
    }
}
