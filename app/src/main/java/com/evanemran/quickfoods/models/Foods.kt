package com.evanemran.quickfoods.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

val menuItems = listOf(
    Foods(1, "Coffee", "Freshly brewed coffee, perfect for a morning pick-me-up.", 250, "https://t4.ftcdn.net/jpg/01/16/61/93/360_F_116619399_YA611bKNOW35ffK0OiyuaOcjAgXgKBui.jpg",  ),
    Foods(2, "Tea", "Selection of herbal and black teas to soothe your soul.", 180, "https://t4.ftcdn.net/jpg/01/16/61/93/360_F_116619399_YA611bKNOW35ffK0OiyuaOcjAgXgKBui.jpg",  ),
    Foods(3, "Croissant", "Buttery and flaky pastry, ideal for a light breakfast.", 300, "https://t4.ftcdn.net/jpg/01/16/61/93/360_F_116619399_YA611bKNOW35ffK0OiyuaOcjAgXgKBui.jpg",  ),
    Foods(4, "Sandwich (Ham & Cheese)", "Classic ham and cheese on toasted bread, a satisfying lunch option.", 450, "https://t4.ftcdn.net/jpg/01/16/61/93/360_F_116619399_YA611bKNOW35ffK0OiyuaOcjAgXgKBui.jpg",  ),
    Foods(5, "Salad (Greek)", "Refreshing salad with feta cheese, olives, and tomatoes.", 600, "https://t4.ftcdn.net/jpg/01/16/61/93/360_F_116619399_YA611bKNOW35ffK0OiyuaOcjAgXgKBui.jpg",  ),
    Foods(6, "Juice (Orange)", "Freshly squeezed orange juice, packed with Vitamin C.", 200, "https://t4.ftcdn.net/jpg/01/16/61/93/360_F_116619399_YA611bKNOW35ffK0OiyuaOcjAgXgKBui.jpg",  ),
    Foods(7, "Muffin (Blueberry)", "Moist and delicious blueberry muffin, a sweet treat anytime.", 220, "https://t4.ftcdn.net/jpg/01/16/61/93/360_F_116619399_YA611bKNOW35ffK0OiyuaOcjAgXgKBui.jpg",  ),
    Foods(8, "Yogurt Parfait", "Layered yogurt with granola and fresh fruit, a healthy and delicious breakfast.", 350, "https://t4.ftcdn.net/jpg/01/16/61/93/360_F_116619399_YA611bKNOW35ffK0OiyuaOcjAgXgKBui.jpg",  ),
    Foods(9, "Cookie (Chocolate Chip)", "Warm and gooey chocolate chip cookie, a classic indulgence.", 150, "https://t4.ftcdn.net/jpg/01/16/61/93/360_F_116619399_YA611bKNOW35ffK0OiyuaOcjAgXgKBui.jpg",  ),
    Foods(10, "Water (Bottled)", "Purified bottled water, a refreshing choice for any occasion.", 100, "https://t4.ftcdn.net/jpg/01/16/61/93/360_F_116619399_YA611bKNOW35ffK0OiyuaOcjAgXgKBui.jpg",  ),
)
data class Foods(
    val id: Int,
    @SerializedName("name")
    var foodName: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("price")
    var price: Int,
    @SerializedName("image_url")
    var image_url: String,
    var isFavorite: Boolean = false,
    var isAvailable: Boolean = true
)