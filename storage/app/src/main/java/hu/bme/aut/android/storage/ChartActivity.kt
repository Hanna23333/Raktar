package hu.bme.aut.android.storage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import android.graphics.Color
import android.graphics.Typeface
import com.github.mikephil.charting.charts.PieChart
import hu.bme.aut.android.storage.data.StorageItem
import kotlin.concurrent.thread
import hu.bme.aut.android.storage.data.StorageItemDao


@Suppress("DEPRECATION")
class ChartActivity : AppCompatActivity(){

    lateinit var binding: PieChart
    private lateinit var dao: StorageItemDao
    lateinit var items: List<StorageItem>

    var food = 0f
    var drink = 0f
    var book = 0f
    var cleaning = 0f
    var material = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        dao = TodoApplication.todoDatabase.storageItemDao()
        //több szálon futtatja a kódot, hogy nem kell várni sokat.
        thread {
            items = dao.getAll()
            for (element in items) {
                if(element.category.name== "FOOD" )
                    food += element.piece
                if(element.category.name== "DRINK" )
                    drink += element.piece
                if(element.category.name== "BOOK" )
                    book  += element.piece
                if(element.category.name== "CLEANING" )
                    cleaning += element.piece
                if(element.category.name== "MATERIAL" )
                    material += element.piece
            }
            //
            //amikor a felhasználói felületet frissíti,
            // „vissza kell térnünk” a fő szálhoz, mivel csak ő érintheti meg és frissítheti az alkalmazás felhasználói felületét.
 //ez főszál,de csak akkor tud futni, ha az előző kód már sikerült futni.
            runOnUiThread {
                val entries = listOf(
                    PieEntry(food),
                    PieEntry(drink ),
                    PieEntry(book, ),
                    PieEntry(cleaning, ),
                    PieEntry(material, )

                )

                val dataSet = PieDataSet(entries, "storage")

                val colors: ArrayList<Int> = ArrayList()
                colors.add(resources.getColor(R.color.purple_200))
                colors.add(resources.getColor(R.color.yellow))
                colors.add(resources.getColor(R.color.red))
                colors.add(resources.getColor(R.color.teal_200))
                colors.add(resources.getColor(R.color.pink))

                dataSet.colors = colors
                val data = PieData(dataSet)

               data.setValueTextSize(15f)
                data.setValueTypeface(Typeface.DEFAULT_BOLD)
                data.setValueTextColor(Color.WHITE)
                binding.setData(data)
                //redraw
                binding.invalidate()
            }
        }

            binding = findViewById(R.id.pieChart)
            binding.setDrawHoleEnabled(true)
            binding.setHoleColor(Color.WHITE)
            binding.getDescription().setEnabled(false)


        }
    }
