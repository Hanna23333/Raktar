package hu.bme.aut.android.storage.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter


@Entity(tableName = "storageItem")
    data class StorageItem(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "category") var category: Category,
    @ColumnInfo(name = "estimated_piece") var piece: Int


    ) {
        enum class Category {
            //osszesen 5 tipus van
            FOOD, DRINK, BOOK,CLEANING,MATERIAL;
            companion object {
                @JvmStatic
                @TypeConverter
                fun getByOrdinal(ordinal: Int): Category? {
                    var ret: Category? = null
                    for (cat in values()) {
                        if (cat.ordinal == ordinal) {
                            ret = cat
                            break
                        }
                    }
                    return ret
                }

                @JvmStatic
                @TypeConverter
                fun toInt(category: Category): Int {
                    return category.ordinal
                }
            }
        }
    }
