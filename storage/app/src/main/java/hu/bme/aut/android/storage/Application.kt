package hu.bme.aut.android.storage

import android.app.Application
import androidx.room.Room

import hu.bme.aut.android.storage.data.StorageListDatabase

class TodoApplication : Application() {


    companion object {
        lateinit var todoDatabase:  StorageListDatabase
            private set
    }
    override fun onCreate() {
        super.onCreate()

        todoDatabase = Room.databaseBuilder(
            applicationContext,
            StorageListDatabase::class.java,
            "todo_database"
        ).fallbackToDestructiveMigration().build()
    }

}

