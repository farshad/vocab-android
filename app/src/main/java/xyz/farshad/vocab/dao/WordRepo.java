package xyz.farshad.vocab.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import xyz.farshad.vocab.model.Word;

/**
 * Created by farshad on 9/29/15.
 */
public class WordRepo {

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    private static final String[] columns = {
            VocabDBOpenHelper.WORDS_COL_ID,
            VocabDBOpenHelper.WORDS_COL_NAME,
            VocabDBOpenHelper.WORDS_COL_MEANING,
            VocabDBOpenHelper.WORDS_COL_EXAMPLE,
            VocabDBOpenHelper.WORDS_COL_TRANSLATE,
            VocabDBOpenHelper.WORDS_COL_CATEGORY_ID,
            VocabDBOpenHelper.WORDS_COL_VIEW_COUNT,
            VocabDBOpenHelper.WORDS_COL_FAVORITE};

    public WordRepo(Context context){
        dbHelper = new VocabDBOpenHelper(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Word insert(Word word){
        ContentValues values = new ContentValues();
        values.put(VocabDBOpenHelper.WORDS_COL_NAME, word.getName());
        values.put(VocabDBOpenHelper.WORDS_COL_MEANING, word.getMeaning());
        values.put(VocabDBOpenHelper.WORDS_COL_EXAMPLE, word.getExample());
        values.put(VocabDBOpenHelper.WORDS_COL_TRANSLATE, word.getTranslate());
        values.put(VocabDBOpenHelper.WORDS_COL_CATEGORY_ID, word.getCategory_id());
        values.put(VocabDBOpenHelper.WORDS_COL_VIEW_COUNT, word.getView_count());
        values.put(VocabDBOpenHelper.WORDS_COL_FAVORITE, word.getFavorite());

        long insert = database.insert(VocabDBOpenHelper.TABLE_WORDS, null, values);
        word.setId(insert);
        return word;
    }

    public List<Word> findByQuery(String q){
        List<Word> words = new ArrayList<Word>();
        Cursor cursor = database.query(VocabDBOpenHelper.TABLE_WORDS, columns, q, null, null, null, null);

        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Word word = new Word();
                word.setId(cursor.getLong(cursor.getColumnIndex(VocabDBOpenHelper.WORDS_COL_ID)));
                word.setName(cursor.getString(cursor.getColumnIndex(VocabDBOpenHelper.WORDS_COL_NAME)));
                word.setMeaning(cursor.getString(cursor.getColumnIndex(VocabDBOpenHelper.WORDS_COL_MEANING)));
                word.setExample(cursor.getString(cursor.getColumnIndex(VocabDBOpenHelper.WORDS_COL_EXAMPLE)));
                word.setTranslate(cursor.getString(cursor.getColumnIndex(VocabDBOpenHelper.WORDS_COL_TRANSLATE)));
                word.setCategory_id(cursor.getInt(cursor.getColumnIndex(VocabDBOpenHelper.WORDS_COL_CATEGORY_ID)));
                word.setView_count(cursor.getInt(cursor.getColumnIndex(VocabDBOpenHelper.WORDS_COL_VIEW_COUNT)));
                boolean fav = cursor.getInt(cursor.getColumnIndex(VocabDBOpenHelper.WORDS_COL_FAVORITE)) > 0;
                word.setFavorite(fav);
                words.add(word);
            }
        }
        return words;
    }
}
