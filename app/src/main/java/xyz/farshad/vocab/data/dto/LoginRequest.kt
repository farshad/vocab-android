package xyz.farshad.vocab.data.dto

data class LoginRequest(
    private val username: String,
    private val password: String,
    private val mode: String = "ANDROID",
)
