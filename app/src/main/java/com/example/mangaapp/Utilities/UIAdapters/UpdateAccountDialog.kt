package com.example.mangaapp.Utilities.UIAdapters

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mangaapp.R

class UpdateAccountDialog(context: Context,
                          private val fieldType: FieldType,
                          private val onUpdateListener: (String) -> Unit
) : Dialog(context) {

    enum class FieldType {
        EMAIL,
        PASSWORD
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = LayoutInflater.from(context).inflate(
            R.layout.update_account,
            null
        )
        setContentView(view)

        window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        val backButton = view.findViewById<ImageView>(R.id.backButton)
        val title = view.findViewById<TextView>(R.id.title)
        val inputField = view.findViewById<EditText>(R.id.inputField)
        val applyButton = view.findViewById<Button>(R.id.applyButton)

        when (fieldType) {
            FieldType.EMAIL -> {
                title.text = "Update Email"
                inputField.hint = "Enter new email"
                inputField.inputType = android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }
            FieldType.PASSWORD -> {
                title.text = "Update Password"
                inputField.hint = "Enter new password"
                inputField.inputType = android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }

        backButton.setOnClickListener {
            dismiss()
        }

        applyButton.setOnClickListener {
            val newValue = inputField.text.toString().trim()
            if (newValue.isNotEmpty()) {
                onUpdateListener(newValue)
                dismiss()
            } else {
                inputField.error = "Field cannot be empty"
            }
        }
    }
}
