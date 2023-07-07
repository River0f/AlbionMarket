package app.albionmarket.data

data class MarketItemPure(
    val buy_price_max: Int,
    val buy_price_max_date: String,
    val buy_price_min: Int,
    val buy_price_min_date: String,
    val city: String,
    val item_id: String,
    val quality: Int,
    val sell_price_max: Int,
    val sell_price_max_date: String,
    val sell_price_min: Int,
    val sell_price_min_date: String
)