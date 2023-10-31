package xyz.farshad.vocab.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.neoattitude.defio.util.Resource
import kotlinx.coroutines.launch
import xyz.farshad.vocab.data.dto.LoginRequest
import xyz.farshad.vocab.data.dto.LoginResponse
import xyz.farshad.vocab.data.dto.RegisterRequest
import xyz.farshad.vocab.data.dto.RegisterResponse
import xyz.farshad.vocab.data.repository.AuthRepository

class AuthViewModel(
    private val repository: AuthRepository
) :
    BaseViewModel() {

    private val loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    private val registerResponse: MutableLiveData<Resource<RegisterResponse>> = MutableLiveData()

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val response = repository.login(loginRequest)
                loginResponse.postValue(handleResponse(response))
            } catch (t: Throwable) {
                loginResponse.postValue(Resource.Error(t.message!!))
            }
        }
    }

    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            try {
                val response = repository.register(registerRequest)
                registerResponse.postValue(handleResponse(response))
            } catch (t: Throwable) {
                loginResponse.postValue(Resource.Error(t.message!!))
            }
        }
    }

    fun watchLoginResponse(): MutableLiveData<Resource<LoginResponse>> {
        return loginResponse
    }

    fun watchRegisterResponse(): MutableLiveData<Resource<RegisterResponse>> {
        return registerResponse
    }
}