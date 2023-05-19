package hu.bme.aut.android.storage.fragments


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog

import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.storage.R
import hu.bme.aut.android.storage.data.StorageItem
import hu.bme.aut.android.storage.databinding.DialogNewStorageItemBinding


class NewStorageItemDialogFragment : DialogFragment() {
    interface NewStorageItemDialogListener {
        fun onStorageItemCreated(newItem: StorageItem)
    }

    private lateinit var listener: NewStorageItemDialogListener
    private lateinit var binding: DialogNewStorageItemBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewStorageItemDialogListener
            ?: throw RuntimeException("Activity must implement the NewStorageItemDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewStorageItemBinding.inflate(LayoutInflater.from(context))
        binding.spCategory.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.category_items)
        )

        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_storage_item)
            .setView(binding.root)
            .setPositiveButton(R.string.button_ok) { dialogInterface, i ->
                if (isValid()) {
                    listener.onStorageItemCreated(getStorageItem())
                }
            }
            .setNegativeButton(R.string.button_cancel, null)
            .create()

    }



    private fun isValid() = binding.etName.text.isNotEmpty()

    private fun getStorageItem() = StorageItem(
        name = binding.etName.text.toString(),
        description = binding.etDescription.text.toString(),
        piece = binding.etPiece.text.toString().toIntOrNull() ?: 1,
        category = StorageItem.Category.getByOrdinal(binding.spCategory.selectedItemPosition)
            ?: StorageItem.Category.BOOK,

    )
    companion object {
        const val TAG = "NewStorageItemDialogFragment"
    }
}