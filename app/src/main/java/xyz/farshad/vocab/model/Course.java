package xyz.farshad.vocab.model;

import com.orm.SugarRecord;

/**
 * Created by farshad on 9/29/15.
 */
public class Course extends SugarRecord {

    String name;
    int wordCount;

    public Course(){

    }

    public Course(String name, int wordCount){
        this.name = name;
        this.wordCount= wordCount;
    }

    public String getName() {
        return name;
    }

    public int getWordCount() {
        return wordCount;
    }

}
