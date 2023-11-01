package xyz.farshad.vocab.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.neoattitude.defio.util.Resource
import kotlinx.coroutines.launch
import xyz.farshad.vocab.data.entity.Sync
import xyz.farshad.vocab.data.repository.ChapterRepository
import xyz.farshad.vocab.data.repository.CourseRepository
import xyz.farshad.vocab.data.repository.SyncRepository
import xyz.farshad.vocab.data.repository.WordRepository

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class SyncViewModel(
    private val repository: SyncRepository,
    private val courseRepository: CourseRepository,
    private val chapterRepository: ChapterRepository,
    private val wordRepository: WordRepository
) : BaseViewModel() {

    private val sync: MutableLiveData<Resource<Sync>> = MutableLiveData()

    fun get() {
        viewModelScope.launch {
            //sync.postValue(Resource.Loading())
            try {
                val response = repository.get()
                if (response.courses != null){
                    courseRepository.deleteAll()
                    courseRepository.insertAll(response.courses!!)
                }
                if (response.chapters != null){
                    chapterRepository.deleteAll()
                    chapterRepository.insertAll(response.chapters!!)
                }
                if (response.words != null){
                    wordRepository.deleteAll()
                    wordRepository.insertAll(response.words!!)
                }
                sync.postValue(Resource.Success(response))
            } catch (t: Throwable) {
                sync.postValue(Resource.Error(t.message!!))
            }
        }
    }

    fun watchSync(): MutableLiveData<Resource<Sync>> {
        return sync
    }
}