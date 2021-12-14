package xyz.farshad.vocab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.farshad.vocab.data.model.Course
import xyz.farshad.vocab.data.model.Level
import xyz.farshad.vocab.data.repository.LevelRepository

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class LevelViewModel(
    private val repository: LevelRepository
) : BaseViewModel() {

    private var levels: MutableLiveData<List<Level>>? = MutableLiveData()

    fun findByCourseId(courseId: Int) {
        viewModelScope.launch {
            levels?.value = repository.findByCourseId(courseId)
        }
    }

    fun watchLevel(): LiveData<List<Level>>? {
        return levels
    }
}