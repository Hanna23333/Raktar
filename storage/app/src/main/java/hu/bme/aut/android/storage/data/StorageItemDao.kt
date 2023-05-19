package hu.bme.aut.android.storage.data

import androidx.room.*
// Data Access Object
@Dao
interface StorageItemDao {
    @Query("SELECT * FROM storageItem")
    fun getAll(): List<StorageItem>

    @Insert
    fun insert(storageItems: StorageItem): Long

    @Update
    fun update(storageItems: StorageItem)

    @Delete
    fun deleteItem(storageItems: StorageItem)


}