package hu.bme.aut.android.storage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.storage.adapter.StorageAdapter
import hu.bme.aut.android.storage.data.StorageItem
import hu.bme.aut.android.storage.databinding.ActivityMainBinding
import hu.bme.aut.android.storage.data.StorageItemDao
import hu.bme.aut.android.storage.fragments.NewStorageItemDialogFragment
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), StorageAdapter.StorageItemClickListener,
    NewStorageItemDialogFragment.NewStorageItemDialogListener
{
    private lateinit var binding: ActivityMainBinding
    private lateinit var dao: StorageItemDao
    private lateinit var adapter: StorageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        dao=TodoApplication.todoDatabase.storageItemDao()


        binding.fab.setOnClickListener {
            NewStorageItemDialogFragment().show(
                supportFragmentManager,
                NewStorageItemDialogFragment.TAG
            )
        }
        initRecyclerView()
     binding.chart.setOnClickListener{
          startActivity(Intent(this, ChartActivity::class.java))

      }

    }

    private fun initRecyclerView() {
        adapter = StorageAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = dao.getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onItemChanged(item: StorageItem) {
        thread {
            dao.update(item)
            Log.d("MainActivity", "StorageItem update was successful")
        }
    }

    override fun onItemDeleted(item: StorageItem) {
        thread {
            dao.deleteItem(item)
            Log.d("MainActivity", "StorageItem update was successful")
        }
    }

    override fun onStorageItemCreated(newItem: StorageItem) {
        thread {
            println("fun onStorageItemCreated:")

            val insertId = dao.insert(newItem)
            newItem.id = insertId
            runOnUiThread {
                adapter.addItem(newItem)

            }
        }

    }



}