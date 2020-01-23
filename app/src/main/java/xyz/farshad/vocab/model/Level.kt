package xyz.farshad.vocab.model

import com.orm.SugarRecord

/**
 * Created by farshad on 7/14/16.
 */
class Level : SugarRecord {
    var name: String
        internal set
    var courseId: Int = 0
        internal set

    constructor() {}

    constructor(name: String, courseId: Int) {
        this.name = name
        this.courseId = courseId
    }
}
