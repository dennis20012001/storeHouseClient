import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.time.LocalDateTime

data class Invoice(
    @SerializedName("invoice_id")
    val invoiceId: Long?,

    @SerializedName("id_user")
    val userId: Long,

    @SerializedName("CIF")
    val cif: String,

    @SerializedName("date")
    val date: LocalDateTime,

    @SerializedName("total")
    val total: BigDecimal?,

    @SerializedName("paid")
    val paid: Boolean
)
