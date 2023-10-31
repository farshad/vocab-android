package xyz.farshad.vocab.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import xyz.farshad.vocab.data.dto.LoginRequest
import xyz.farshad.vocab.data.dto.LoginResponse
import xyz.farshad.vocab.data.dto.RegisterRequest
import xyz.farshad.vocab.data.dto.RegisterResponse

interface AuthApi {

    @Headers("remove-token: true")
    @POST("v1/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @Headers("remove-token: true")
    @POST("v1/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

}