package xyz.farshad.vocab.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import xyz.farshad.vocab.data.dto.CourseResponse

interface CourseApi {
    @GET("v1/courses/public")
    suspend fun getAll(): Response<List<CourseResponse>>

    @GET("v1/courses/public/{id}")
    suspend fun getById(@Path("id") id: String): Response<CourseResponse>
}