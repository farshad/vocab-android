package xyz.farshad.vocab.model;

/**
 * Created by farshad on 9/29/15.
 */
public class Category {
    private long id;
    private String name;
    private int wordCount;

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getWordCount() {
        return wordCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
