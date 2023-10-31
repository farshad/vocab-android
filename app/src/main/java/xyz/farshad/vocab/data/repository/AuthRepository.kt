package xyz.farshad.vocab.data.repository

import xyz.farshad.vocab.data.api.AuthApi
import xyz.farshad.vocab.data.dto.LoginRequest
import xyz.farshad.vocab.data.dto.RegisterRequest

class AuthRepository(private val authApi: AuthApi) {
    suspend fun login(loginRequest: LoginRequest) = authApi.login(loginRequest)
    suspend fun register(registerRequest: RegisterRequest) = authApi.register(registerRequest)
}