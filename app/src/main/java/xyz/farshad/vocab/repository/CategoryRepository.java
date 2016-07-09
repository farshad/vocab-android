package xyz.farshad.vocab.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import xyz.farshad.vocab.model.Category;

/**
 * Created by farshad on 9/29/15.
 */
public class CategoryRepository {

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    private static final String[] columns = {
            VocabDBOpenHelper.CATEGORIES_COL_ID,
            VocabDBOpenHelper.CATEGORIES_COL_NAME,
            VocabDBOpenHelper.CATEGORIES_COL_WORD_COUNT
    };
private final Context ctx;
    public CategoryRepository(Context context){
        dbHelper = new VocabDBOpenHelper(context);
        ctx = context;
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Category insert(Category category){
        ContentValues values = new ContentValues();
        values.put(VocabDBOpenHelper.CATEGORIES_COL_NAME, category.getName());
        values.put(VocabDBOpenHelper.CATEGORIES_COL_WORD_COUNT, category.getWordCount());
        long insert = database.insert(VocabDBOpenHelper.TABLE_CATEGORIES, null, values);

        category.setId(insert);
        return category;
    }

    public List<Category> findAll(){
        List<Category> categories = new ArrayList<Category>();
        Cursor entity = database.query(VocabDBOpenHelper.TABLE_CATEGORIES, columns, null, null, null, null, null);

        if (entity.getCount() > 0){
            while (entity.moveToNext()){
                Category category = new Category();
                category.setId(entity.getLong(entity.getColumnIndex(VocabDBOpenHelper.CATEGORIES_COL_ID)));
                category.setName(entity.getString(entity.getColumnIndex(VocabDBOpenHelper.CATEGORIES_COL_NAME)));
                category.setWordCount(entity.getInt(entity.getColumnIndex(VocabDBOpenHelper.CATEGORIES_COL_WORD_COUNT)));
                categories.add(category);
            }
        }
        return categories;
    }

    public void update(Category category){
        String strFilter = "id=" + category.getId();
        ContentValues values = new ContentValues();
        values.put(VocabDBOpenHelper.CATEGORIES_COL_NAME, category.getName());
        values.put(VocabDBOpenHelper.CATEGORIES_COL_WORD_COUNT, category.getWordCount());

        database.update(VocabDBOpenHelper.TABLE_CATEGORIES, values, strFilter, null);
    }
}
