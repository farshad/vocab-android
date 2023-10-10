package xyz.farshad.vocab.data.api

import retrofit2.Response
import retrofit2.http.GET
import xyz.farshad.vocab.data.dto.CourseResponse

interface CourseApi {
    @GET("v1/courses/")
    suspend fun getCourses(): Response<List<CourseResponse>>
}