package xyz.farshad.vocab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.farshad.vocab.data.entity.Chapter
import xyz.farshad.vocab.data.repository.ChapterRepository

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class ChapterViewModel(
    private val repository: ChapterRepository
) : BaseViewModel() {

    private var levels: MutableLiveData<List<Chapter>>? = MutableLiveData()

    fun findByCourseId(courseId: Int) {
        viewModelScope.launch {
            levels?.value = repository.findByCourseId(courseId)
        }
    }

    fun watchLevel(): LiveData<List<Chapter>>? {
        return levels
    }
}