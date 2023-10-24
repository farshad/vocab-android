package xyz.farshad.vocab.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import xyz.farshad.vocab.data.dto.CourseResponse

interface SelectionApi {
    @GET("v1/selection/add/{courseId}")
    suspend fun add(@Path("courseId") courseId: String): Response<CourseResponse>
}