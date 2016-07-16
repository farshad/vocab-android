package xyz.farshad.vocab.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import xyz.farshad.vocab.R;
import xyz.farshad.vocab.component.DataAdapter.WordListAdapter;
import xyz.farshad.vocab.model.Word;

public class WordListActivity extends Activity {

    List<Word> words;
    private Long levelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        Bundle b = getIntent().getExtras();
        if (b != null && b.containsKey("levelId")) {
            levelId = b.getLong("levelId");
            words = Word.find(Word.class, "level_id = ?", levelId.toString());

            showWordList(false);
        }
        //Toast.makeText(WordActivity.this, "id = "+ levelId +"", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_word_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showWordList(boolean reload){
        if (reload == true){
            words = Word.find(Word.class, "level_id = ?", levelId.toString());
        }
        ArrayAdapter<Word> adapter = new WordListAdapter(WordListActivity.this, R.layout.word_list_view, words);
        ListView list = (ListView) findViewById(R.id.wordMainListView);
        list.setAdapter(adapter);
        list.setItemsCanFocus(true);
    }
}
