package xyz.farshad.vocab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.neoattitude.defio.util.Resource
import kotlinx.coroutines.launch
import xyz.farshad.vocab.data.dto.CourseResponse
import xyz.farshad.vocab.data.entity.Course
import xyz.farshad.vocab.data.repository.CourseRepository

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class CourseViewModel(
    private val repository: CourseRepository
) : BaseViewModel() {

    private var courses: MutableLiveData<List<Course>>? = MutableLiveData()
    private val courseResponses: MutableLiveData<Resource<List<CourseResponse>>> = MutableLiveData()
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

    fun getCourses() {
        viewModelScope.launch {
            viewModelScope.launch {
                courseResponses.postValue(Resource.Loading())
                try {
                    val response = repository.getCourses()
                    courseResponses.postValue(handleResponse(response))
                } catch (t: Throwable) {
                    courseResponses.postValue(Resource.Error(t.message!!))
                }
            }
        }
    }

    fun fetchAll() {
        viewModelScope.launch {
            courses?.value = repository.fetchAll()
        }
    }

    fun watchCourse(): LiveData<List<Course>>? {
        return courses
    }

    fun watchCourseResponses(): MutableLiveData<Resource<List<CourseResponse>>> {
        return courseResponses
    }

    fun watchIsAdded(): MutableLiveData<Boolean> {
        return isAdded
    }
}