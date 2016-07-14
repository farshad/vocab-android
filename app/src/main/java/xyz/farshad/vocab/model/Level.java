package xyz.farshad.vocab.model;

import com.orm.SugarRecord;

/**
 * Created by farshad on 7/14/16.
 */
public class Level extends SugarRecord {
    String name;
    int courseId;

    public Level() {
    }

    public Level(String name, int courseId) {
        this.name = name;
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public int getCourseId() {
        return courseId;
    }
}
