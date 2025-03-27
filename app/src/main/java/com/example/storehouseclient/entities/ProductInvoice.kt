import com.google.gson.annotations.SerializedName

data class ProductInvoice(
    @SerializedName("detail_id")
    val detailId: Long?,

    @SerializedName("invoice_id")
    val invoiceId: Long,

    @SerializedName("product_id")
    val productId: Long,

    @SerializedName("quantity")
    val quantity: Int
)
