package app.albionmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import app.albionmarket.data.Api
import app.albionmarket.data.ItemsList
import app.albionmarket.data.Location
import app.albionmarket.data.MarketItem
import app.albionmarket.data.MarketItemPure
import app.albionmarket.data.Navigation
import app.albionmarket.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://west.albion-online-data.com"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemsList: ItemsList
    private lateinit var groupedItemsList: List<MarketItem>
    private var marketItemAdapter = MarketItemAdapter()
    private var navigations = ArrayList<Navigation>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        itemsList = ItemsList()
        init()
        getMarketItems(itemsList.general)

        setContentView(binding.root)

    }

    fun getMarketItems(url: String) {
        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(Api::class.java)
        val retrofitData = retrofitBuilder.getItems( "api/v1/stats/prices/" + url)
        retrofitData.enqueue(object : Callback<List<MarketItemPure>?> {
            override fun onFailure(call: Call<List<MarketItemPure>?>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<MarketItemPure>?>,
                response: Response<List<MarketItemPure>?>
            ) {
                val responseBody = response.body()!!
                val toast = Toast.makeText(this@MainActivity, "Data loaded!", Toast.LENGTH_SHORT)
                toast.show()
                val groupedItems = responseBody.groupBy{it.item_id}.map{ (id, list) ->
                    val itr = list.listIterator()
                    val locations: ArrayList<Location> = ArrayList()
                    while (itr.hasNext()) {
                        if(binding.cT1.isChecked) {

                        }
                        val item = itr.next()

                            locations.add(Location(item.city,item.buy_price_min))


                    }
                    return@map MarketItem(id, locations)
                }
                groupedItemsList = groupedItems
                putElements()
            }
        })

    }

    fun putElements() {
        val filteredElementsByT = groupedItemsList.filter { marketItem ->
                    (marketItem.itemId.contains("T1") && binding.cT1.isChecked)  ||
                    (marketItem.itemId.contains("T2") && binding.cT2.isChecked) ||
                    (marketItem.itemId.contains("T3") && binding.cT3.isChecked) ||
                    (marketItem.itemId.contains("T4") && binding.cT4.isChecked) ||
                    (marketItem.itemId.contains("T5") && binding.cT5.isChecked) ||
                            (marketItem.itemId.contains("T6") && binding.cT6.isChecked)

        }
        val filteredElementsByQ = filteredElementsByT.filter {marketItem ->
            (!marketItem.itemId.contains("@") && binding.simple.isChecked) ||
                    (marketItem.itemId.contains("@1") && binding.good.isChecked) ||
                    (marketItem.itemId.contains("@3") && binding.perfect.isChecked) ||
                    (marketItem.itemId.contains("@5") && binding.gorgeous.isChecked)
        }
        marketItemAdapter.addMarketItem(filteredElementsByQ)
    }

    private fun init() {
        binding.apply {
            rvItems.layoutManager = LinearLayoutManager(this@MainActivity )
            rvItems.adapter = marketItemAdapter
            navigations.add(Navigation("Cloth armor", itemsList.cloth_armor ))
            navigations.add(Navigation("Axe", itemsList.axe))
            navigations.add(Navigation("Magic", itemsList.magic ))
            navigations.add(Navigation("Bow",  itemsList.bow))
            navigations.add(Navigation("Dagger",  itemsList.dagger ))
            navigations.add(Navigation("Book",  itemsList.book))
            lvLocations.adapter = NavigationItemAdapter(this@MainActivity, navigations)
            lvLocations.adapter = NavigationItemAdapter(this@MainActivity, navigations)
        }

    }

    fun applyFilter(view: View) {
        putElements()
    }
}