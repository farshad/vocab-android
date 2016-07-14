package xyz.farshad.vocab.model;

import com.orm.SugarRecord;

/**
 * Created by farshad on 9/29/15.
 */
public class Word extends SugarRecord{
    private String name;
    private String meaning;
    private String example;
    private String translate;
    private int levelId;
    private int viewCount;
    private boolean favorite;

    public Word() {
    }

    public Word(String name, String meaning, String example, String translate, int levelId, int viewCount, boolean favorite) {
        this.name = name;
        this.meaning = meaning;
        this.example = example;
        this.translate = translate;
        this.levelId = levelId;
        this.viewCount = viewCount;
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getExample() {
        return example;
    }

    public String getTranslate() {
        return translate;
    }

    public int getLevelId() {
        return levelId;
    }

    public int getviewCount() {
        return viewCount;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
