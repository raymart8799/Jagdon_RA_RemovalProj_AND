package com.jagdon.removal_jagdon.views.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.jagdon.removal_jagdon.R
import com.jagdon.removal_jagdon.models.MenuItem


class EditItemDialog(
    context: Context,
    private val item: MenuItem,
    private val onSave: (MenuItem) -> Unit
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_menu_item)

        val foodNameEditText: EditText = findViewById(R.id.foodNameEditText)
        val priceEditText: EditText = findViewById(R.id.priceEditText)
        val saveButton: Button = findViewById(R.id.addButton)

        foodNameEditText.setText(item.foodName)
        priceEditText.setText(item.price.toString())
        saveButton.setText(R.string.save_button_text)

        val dialogWidth = context.resources.displayMetrics?.widthPixels?.times(0.9) ?: 0f
        val dialogHeight = LinearLayout.LayoutParams.WRAP_CONTENT

        window?.setLayout(dialogWidth.toInt(), dialogHeight)

        saveButton.setOnClickListener {
            val foodName = foodNameEditText.text.toString()
            val priceText = priceEditText.text.toString()

            if (foodName.isNotBlank() && priceText.isNotBlank()) {
                val price = priceText.toDoubleOrNull()
                if (price != null) {
                    onSave(item.copy(foodName = foodName, price = price))
                    dismiss()
                } else {
                    priceEditText.error = "Enter a valid price"
                }
            } else {
                if (foodName.isBlank()) foodNameEditText.error = "Enter a food name"
                if (priceText.isBlank()) priceEditText.error = "Enter a price"
            }
        }
    }
}