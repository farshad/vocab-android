package xyz.farshad.vocab.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by farshad on 9/29/15.
 */
public class VocabDBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "vocab.db";
    private static final int DATABASE_VERSION = 8;

    // categories table
    public static final String TABLE_CATEGORIES = "categories";
    public static final String CATEGORIES_COL_ID = "id";
    public static final String CATEGORIES_COL_NAME = "name";
    public static final String CATEGORIES_COL_WORD_COUNT = "word_count";
    private static final String CREATE_CATEGORIES = "CREATE TABLE "
            + TABLE_CATEGORIES + "(" + CATEGORIES_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CATEGORIES_COL_NAME + " TEXT, " + CATEGORIES_COL_WORD_COUNT + " INTEGER)";

    // words table
    public static final String TABLE_WORDS = "words";
    public static final String WORDS_COL_ID = "id";
    public static final String WORDS_COL_NAME = "name";
    public static final String WORDS_COL_MEANING = "meaning";
    public static final String WORDS_COL_EXAMPLE = "example";
    public static final String WORDS_COL_TRANSLATE = "translate";
    public static final String WORDS_COL_CATEGORY_ID = "category_id";
    public static final String WORDS_COL_VIEW_COUNT = "view_count";
    public static final String WORDS_COL_FAVORITE = "favorite";
    private static final String CREATE_WORDS = "CREATE TABLE "
            + TABLE_WORDS + "(" + WORDS_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WORDS_COL_NAME + " TEXT, "  + WORDS_COL_MEANING + " TEXT, "
            + WORDS_COL_TRANSLATE + " TEXT, " + WORDS_COL_CATEGORY_ID + " INTEGER, "
            + WORDS_COL_EXAMPLE + " TEXT, " + WORDS_COL_FAVORITE + " boolean, "
            + WORDS_COL_VIEW_COUNT + " INTEGER)";

    public VocabDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CATEGORIES);
        sqLiteDatabase.execSQL(CREATE_WORDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(sqLiteDatabase);
    }
}
