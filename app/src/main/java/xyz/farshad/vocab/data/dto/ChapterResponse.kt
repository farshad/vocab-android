package xyz.farshad.vocab.data.dto

import xyz.farshad.vocab.data.entity.Chapter
import java.io.Serializable

data class ChapterResponse(
    var id: String? = null,
    var version: Int? = null,
    var title: String? = null,
    var words: List<WordResponse>? = null,
) : Serializable {
    fun toEntity(dto: ChapterResponse, courseId: Long): Chapter {
        return Chapter(
            null,
            dto.id!!,
            dto.version,
            dto.title!!,
            courseId.toInt()
        )
    }
}
