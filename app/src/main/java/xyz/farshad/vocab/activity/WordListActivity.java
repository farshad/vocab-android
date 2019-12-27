package xyz.farshad.vocab.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import xyz.farshad.vocab.R;
import xyz.farshad.vocab.component.DataAdapter.WordListAdapter;
import xyz.farshad.vocab.model.Word;

public class WordListActivity extends AppCompatActivity {

    List<Word> words;
    private Long levelId;
    private String levelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        setTitle("Words");

        Bundle b = getIntent().getExtras();
        if (b != null && b.containsKey("levelId")) {
            levelName = b.getString("levelName");
            levelId = b.getLong("levelId");
            words = Word.find(Word.class, "level_id = ?", levelId.toString());

            showWordList(false);
        }
        setToolBar();
    }

    private void setToolBar(){
        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle("Words of " + levelName);
    }

    private void showWordList(boolean reload){
        if (reload){
            words = Word.find(Word.class, "level_id = ?", levelId.toString());
        }
        ArrayAdapter<Word> adapter = new WordListAdapter(WordListActivity.this, R.layout.word_list_view, words);
        ListView list = findViewById(R.id.wordMainListView);
        list.setAdapter(adapter);
        list.setItemsCanFocus(true);
    }
}
