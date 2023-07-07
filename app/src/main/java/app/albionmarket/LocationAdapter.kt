package app.albionmarket

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import app.albionmarket.data.Location

class LocationAdapter(context: Context, private val locations: ArrayList<Location>)
    : ArrayAdapter<Location>(context, R.layout.location_item, locations) {

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)

        val titleText = rowView.findViewById(R.id.tvCity) as TextView
        val imageView = rowView.findViewById(R.id.tvPrice) as TextView
        Log.d("log", position.toString())
        titleText.text = locations[position].city + ':'
        imageView.text = locations[position].by_price_min.toString()

        return rowView
    }
}