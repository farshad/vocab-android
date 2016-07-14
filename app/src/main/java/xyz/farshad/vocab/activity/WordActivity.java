package xyz.farshad.vocab.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import xyz.farshad.vocab.R;
import xyz.farshad.vocab.component.WordListAdapter;
import xyz.farshad.vocab.model.Word;

public class WordActivity extends Activity {
/*
    WordRepository wordRepository;
    List<Word> words;
    private Long categoryId;

    //ViewPager viewPager;
    //WordSwipeAdapter wordSwipeAdapter;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        //viewPager = (ViewPager)findViewById(R.id.word_view_page);
       // wordSwipeAdapter = new WordSwipeAdapter(this);
       // viewPager.setAdapter(wordSwipeAdapter);
        //viewPager.setCurrentItem(5);

        Bundle b = getIntent().getExtras();
    /*    if (b != null && b.containsKey("action")) {
            categoryId = b.getLong("categoryId");
            String action = b.getString("action");

            //Toast.makeText(WordActivity.this, "id = "+ categoryId +"", Toast.LENGTH_LONG).show();

            wordRepository = new WordRepository(this);
            wordRepository.open();
            words = wordRepository.findByQuery("category_id = "+ categoryId +"");

            showWordList();
        }
*/
    }
/*
    private void showWordList(){
        showWordList(false);
    }

    private void showWordList(boolean reload){
        if (reload == true){
            words = wordRepository.findByQuery("id = " + categoryId);
        }
        ArrayAdapter<Word> adapter = new WordListAdapter(WordActivity.this, R.layout.word_list_view, words);
        ListView list = (ListView) findViewById(R.id.wordMainListView);
        list.setAdapter(adapter);
        list.setItemsCanFocus(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_word, menu);
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
    */
}
