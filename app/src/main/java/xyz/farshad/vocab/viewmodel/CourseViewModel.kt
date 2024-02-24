package xyz.farshad.vocab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.neoattitude.defio.util.Resource
import kotlinx.coroutines.launch
import xyz.farshad.vocab.data.dto.CourseResponse
import xyz.farshad.vocab.data.entity.Course
import xyz.farshad.vocab.data.repository.ChapterRepository
import xyz.farshad.vocab.data.repository.CourseRepository
import xyz.farshad.vocab.data.repository.SelectionRepository
import xyz.farshad.vocab.data.repository.WordRepository

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
@Suppress("NAME_SHADOWING")
class CourseViewModel(
    private val repository: CourseRepository,
    private val chapterRepository: ChapterRepository,
    private val wordRepository: WordRepository,
    private val selectionRepository: SelectionRepository,
) : BaseViewModel() {

    private var courses: MutableLiveData<List<Course>>? = MutableLiveData()
    private val courseResponses: MutableLiveData<Resource<List<CourseResponse>>> = MutableLiveData()
    private var isAdded: MutableLiveData<Resource<Boolean>> = MutableLiveData()

    fun getAll() {
        viewModelScope.launch {
            viewModelScope.launch {
                courseResponses.postValue(Resource.Loading())
                try {
                    val response = repository.getAll()
                    courseResponses.postValue(handleResponse(response))
                } catch (t: Throwable) {
                    courseResponses.postValue(Resource.Error(t.message!!))
                }
            }
        }
    }

    fun addToSelection(courseId: String) {
        viewModelScope.launch {
            isAdded.postValue(Resource.Loading())

            try {
                val response = selectionRepository.add(courseId)
                if (response.isSuccessful) {
                    val courseResponse: CourseResponse = response.body()!!
                    val course = courseResponse.toEntity(courseResponse)
                    val courseId = repository.insert(course)
                    courseResponse.chapters?.forEach {
                        val chapter = it.toEntity(it, courseId)
                        val chapterId = chapterRepository.insert(chapter)
                        it.words?.forEach { w ->
                            val word = w.toEntity(w, chapterId)
                            wordRepository.insert(word)
                        }
                    }

                    isAdded.postValue(Resource.Success(true))
                } else {
                    isAdded.postValue(Resource.Error("Failed to get data: ${response.message()}"))
                }
            } catch (t: Throwable) {
                isAdded.postValue(Resource.Error(t.message ?: "Unknown error"))
            }
        }
    }

    fun searchByTitle(query: String) {
        viewModelScope.launch {
            courses?.value = repository.searchByTitle(query)
        }
    }

    fun fetchAll() {
        viewModelScope.launch {
            courses?.value = repository.fetchAll()
        }
    }

    fun watchCourses(): LiveData<List<Course>>? {
        return courses
    }

    fun watchCourseResponses(): MutableLiveData<Resource<List<CourseResponse>>> {
        return courseResponses
    }

    fun watchIsAdded(): MutableLiveData<Resource<Boolean>> {
        return isAdded
    }
}