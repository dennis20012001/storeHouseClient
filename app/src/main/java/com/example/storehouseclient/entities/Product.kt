import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Product(
    @SerializedName("product_id")
    val productId: Long?,

    @SerializedName("image")
    val image: ByteArray?,

    @SerializedName("name")
    val name: String,

    @SerializedName("amount")
    val amount: Int,

    @SerializedName("minimum_amount")
    val minimumAmount: Int?,

    @SerializedName("season")
    val season: Boolean,

    @SerializedName("enabled")
    val enabled: Boolean = true,

    @SerializedName("price")
    val price: BigDecimal
)
