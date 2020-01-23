package xyz.farshad.vocab.model

import com.orm.SugarRecord

/**
 * Created by farshad on 9/29/15.
 */
class Course : SugarRecord {

    var name: String
        internal set
    var wordCount: Int = 0
        internal set

    constructor() {

    }

    constructor(name: String, wordCount: Int) {
        this.name = name
        this.wordCount = wordCount
    }

}
