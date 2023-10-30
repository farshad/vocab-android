package xyz.farshad.vocab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import io.neoattitude.defio.util.SingleLiveEvent
import kotlinx.coroutines.launch
import xyz.farshad.vocab.data.entity.Cache
import xyz.farshad.vocab.data.repository.CacheRepository

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class CacheViewModel(
    private val repository: CacheRepository,
) : BaseViewModel() {

    private var cacheValue: SingleLiveEvent<Cache>? = SingleLiveEvent()

    fun insert(cache: Cache) = viewModelScope.launch {
        repository.insert(cache)
    }

    fun delete(key: String) = viewModelScope.launch {
        repository.delete(key)
    }

    fun findByKey(key: String) {
        viewModelScope.launch {
            cacheValue?.value = repository.findByKey(key)
        }
    }

    fun getCacheValue(): LiveData<Cache>? {
        return cacheValue
    }
}