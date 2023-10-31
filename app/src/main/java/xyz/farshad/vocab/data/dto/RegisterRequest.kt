package xyz.farshad.vocab.data.dto

data class RegisterRequest(
    private val name: String,
    private val email: String,
    private val password: String,
    private val mode: String = "ANDROID",
)
