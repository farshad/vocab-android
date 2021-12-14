package xyz.farshad.vocab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.farshad.vocab.data.model.Course
import xyz.farshad.vocab.data.repository.CourseRepository

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class CourseViewModel(
    private val repository: CourseRepository
) : BaseViewModel() {

    private var courses: MutableLiveData<List<Course>>? = MutableLiveData()
    private var isAdded: MutableLiveData<Boolean> = MutableLiveData()

//    fun insert(goalPair: GoalPair) = viewModelScope.launch {
//        val id = repository.insert(goalPair.goal)
//
//        if (goalPair.tasks != null) {
//            goalPair.tasks.forEach { it.goalId = id }
//            taskRepository.insert(goalPair.tasks)
//        }
//        isAdded.postValue(true)
//    }

    fun fetchAll() {
        viewModelScope.launch {
            courses?.value = repository.fetchAll()
        }
    }

    fun watchCourse(): LiveData<List<Course>>? {
        return courses
    }

    fun watchIsAdded(): MutableLiveData<Boolean> {
        return isAdded
    }
}