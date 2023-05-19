package hu.bme.aut.android.storage.data



import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [StorageItem::class], version = 1)
@TypeConverters(value = [StorageItem.Category::class])
abstract class StorageListDatabase : RoomDatabase() {
    abstract fun storageItemDao(): StorageItemDao

}