package xyz.farshad.vocab.data.dto

import xyz.farshad.vocab.data.entity.Course
import java.io.Serializable

data class CourseResponse(
    var id: String? = null,
    var version: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var courseType: CourseType? = null,
    var chapters: List<ChapterResponse>? = null,
) : Serializable {
    fun toEntity(dto: CourseResponse): Course {
        return Course(
           null,
            dto.id!!,
            dto.version!!,
            dto.title!!,
            dto.description!!
        )
    }
}
