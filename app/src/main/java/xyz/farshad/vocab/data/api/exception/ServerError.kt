package xyz.farshad.vocab.data.api.exception

data class ServerError(
    val type: String?,
    val message: String?,
    private var errors: MutableList<ServerFieldError>? = null
)