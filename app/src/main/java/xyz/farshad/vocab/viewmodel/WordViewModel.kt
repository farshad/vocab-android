package xyz.farshad.vocab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.farshad.vocab.data.model.Course
import xyz.farshad.vocab.data.model.Level
import xyz.farshad.vocab.data.model.Word
import xyz.farshad.vocab.data.repository.LevelRepository
import xyz.farshad.vocab.data.repository.WordRepository

/**
 * Created by Farshad Ahangari on 8/12/21.
 * farshad.ahg@gmail.com
 */
class WordViewModel(
    private val repository: WordRepository
) : BaseViewModel() {

    private var words: MutableLiveData<List<Word>>? = MutableLiveData()

    fun findByLevelId(levelId: Int) {
        viewModelScope.launch {
            words?.value = repository.findByLevelId(levelId)
        }
    }

    fun watchWord(): LiveData<List<Word>>? {
        return words
    }
}