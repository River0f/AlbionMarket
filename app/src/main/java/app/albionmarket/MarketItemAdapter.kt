package app.albionmarket

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import app.albionmarket.data.MarketItem
import app.albionmarket.databinding.MarketItemBinding
import com.squareup.picasso.Picasso

class MarketItemAdapter: RecyclerView.Adapter<MarketItemAdapter.MarketItemHolder>() {
    private val marketItemList = ArrayList<MarketItem>()

    inner class MarketItemHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = MarketItemBinding.bind(item)
        fun bind(item: MarketItem) = with(binding){
            val imageUrl = "https://render.albiononline.com/v1/item/${item.itemId}"
            Picasso.get().load(imageUrl).into(ivItemImage)
            itemId.text = item.itemId

            val adapter = LocationAdapter(itemView.context, item.locations)
            lvLocations.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.market_item, parent, false)
        return MarketItemHolder(view)
    }

    override fun getItemCount(): Int {
        return marketItemList.size
    }

    override fun onBindViewHolder(holder: MarketItemHolder, position: Int) {
        holder.bind(marketItemList[position])
    }

    fun addMarketItem(items: List<MarketItem>) {
        marketItemList.clear()
        marketItemList.addAll(items)
        notifyDataSetChanged()
    }
}