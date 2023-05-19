package hu.bme.aut.android.storage.adapter


import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.android.storage.R
import hu.bme.aut.android.storage.data.StorageItem
import hu.bme.aut.android.storage.databinding.ItemStorageListBinding

class StorageAdapter(private val listener: StorageItemClickListener) :
    RecyclerView.Adapter<StorageAdapter.StorageViewHolder>() {

    private val items = mutableListOf<StorageItem>()

//hozzuk létre az adott lista elemet megjelenítő View-t és a hozzá tartozó ViewHolder-t
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StorageViewHolder(
        ItemStorageListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

//kötjük hozzá a modell elemhez a nézetet
    override fun onBindViewHolder(holder: StorageViewHolder, position: Int) {
        val storageItem = items[position]

        holder.binding.ivIcon.setImageResource(getImageResource(storageItem.category))
        holder.binding.tvName.text = storageItem.name
        holder.binding.tvDescription.text = storageItem.description
        holder.binding.tvCategory.text = storageItem.category.name
        holder.binding.tvPiece.text = "${storageItem.piece} Db"

        holder.binding.ibRemove.setOnClickListener {

            val removed = items.removeAt(position)
            items.remove(removed)
            notifyItemRemoved(position)
            listener.onItemDeleted(storageItem)
        }
        holder.binding.ibAdd.setOnClickListener {
            storageItem.piece++
            holder.binding.tvPiece.text = "${storageItem.piece} Db"
            listener.onItemChanged(storageItem)
        }
        holder.binding.ibReduce.setOnClickListener {
            //Ha torolni szeretnenk akkor mellette van egy torles gomb
            if(storageItem.piece>1){
            storageItem.piece--
            holder.binding.tvPiece.text = "${storageItem.piece} Db"
            listener.onItemChanged(storageItem)}
            else Snackbar.make(holder.binding.root,R.string.warn_message, Snackbar.LENGTH_SHORT).show()
        }

    }

    @DrawableRes()
    private fun getImageResource(category: StorageItem.Category): Int {
        return when (category) {
            StorageItem.Category.FOOD -> R.drawable.ic_food
            StorageItem.Category.DRINK -> R.drawable.ic_drink
            StorageItem.Category.BOOK -> R.drawable.ic_book
            StorageItem.Category.CLEANING -> R.drawable.ic_cleaning_agent
            StorageItem.Category.MATERIAL -> R.drawable.ic_material
        }
    }

    fun addItem(item: StorageItem) {
        println("items.add")
        items.add(item)
        println("item.size = " + items.size)
        notifyItemInserted(items.size - 1)
    }


    fun update(storageItems: List<StorageItem>) {
        items.clear()
        items.addAll(storageItems)
        notifyDataSetChanged()
    }

    //a listában található ( megjelenítendő) elemek számát kell, hogy visszaadja.
    override fun getItemCount(): Int = items.size


    interface StorageItemClickListener {
        fun onItemChanged(item: StorageItem)
        abstract fun onItemDeleted(StorageItem: StorageItem)
    }

    inner class StorageViewHolder(val binding: ItemStorageListBinding) : RecyclerView.ViewHolder(binding.root)
}