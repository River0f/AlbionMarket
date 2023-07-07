package app.albionmarket
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import app.albionmarket.data.Navigation


class NavigationItemAdapter(context: Context, private val locations: List<Navigation>)
    : ArrayAdapter<Navigation>(context, R.layout.navigation_item, locations) {

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.navigation_item, parent, false)

        val titleText = rowView.findViewById(R.id.tvTitle) as TextView
        titleText.text = locations[position].title
        titleText.setOnClickListener(View.OnClickListener {
            (context as MainActivity).getMarketItems(locations[position].url)
        })
        return rowView
    }
}