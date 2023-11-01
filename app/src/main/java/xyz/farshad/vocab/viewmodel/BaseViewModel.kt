package xyz.farshad.vocab.viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import io.neoattitude.defio.util.Resource
import retrofit2.Response
import xyz.farshad.vocab.data.api.exception.ServerError

open class BaseViewModel : ViewModel() {
    inline fun <reified T> handleResponse(response: Response<T>): Resource<T>? {
        return when (response.isSuccessful) {
            true -> response.body()?.let { Resource.Success(it) }
            false -> {
                return if (response.message().isEmpty()) {
                    val serverError: ServerError =
                        Gson().fromJson(response.errorBody()?.string(), ServerError::class.java)
                    serverError.message?.let { Resource.Error(it) }
                } else {
                    Resource.Error(response.message())
                }
            }
        }
    }
}