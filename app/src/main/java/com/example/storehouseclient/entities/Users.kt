import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("idUser")
    val idUser: Long?,

    @SerializedName("name")
    val name: String,

    @SerializedName("mail")
    val mail: String?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("pass")
    val pass: String?,

    @SerializedName("rol")
    val rol: Role,

    @SerializedName("enabled")
    val enabled: Boolean
) {
    enum class Role {
        USUARIO, ADMINISTRADOR
    }
}
