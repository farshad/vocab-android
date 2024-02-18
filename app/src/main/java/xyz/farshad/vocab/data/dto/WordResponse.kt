package xyz.farshad.vocab.data.dto

import xyz.farshad.vocab.data.entity.Word
import java.io.Serializable

data class WordResponse(
    var id: String? = null,
    var version: Int? = null,
    var title: String? = null,
    var translate: String? = null,
    var example: String? = null,
    var viewCount: Int? = null,
) : Serializable {
    fun toEntity(dto: WordResponse, chapterId: Long): Word {
        return Word(
            null,
            dto.id!!,
            dto.version,
            dto.title!!,
            dto.translate!!,
            dto.example,
            false,
            chapterId.toInt(),
            dto.viewCount
        )
    }
}
