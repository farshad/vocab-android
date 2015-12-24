package xyz.farshad.vocab.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import xyz.farshad.vocab.R;
import xyz.farshad.vocab.component.CategoryListAdapter;
import xyz.farshad.vocab.dao.CategoryRepo;
import xyz.farshad.vocab.dao.WordRepo;
import xyz.farshad.vocab.model.Category;
import xyz.farshad.vocab.model.Word;


public class MainActivity extends Activity {

    CategoryRepo categoryRepo;
    List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryRepo = new CategoryRepo(this);
        categoryRepo.open();
        categories = categoryRepo.findAll();

        // create default categories if not exist
        if(categories.size() == 0){
            createDefaultCategories();
            categories = categoryRepo.findAll();
        }
        showCategoryList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void createDefaultCategories(){
        Category category = new Category();
        category.setName("504");
        category.setWordCount(504);
        categoryRepo.insert(category);

        category = new Category();
        category.setName("Test");
        category.setWordCount(2);
        categoryRepo.insert(category);

        category = new Category();
        category.setName("public");
        category.setWordCount(5);
        categoryRepo.insert(category);

        WordRepo wordRepo = new WordRepo(this);
        wordRepo.open();

        Word word = new Word();
        word.setName("Abandon");
        word.setMeaning("desert");
        word.setExample("abandon her idea");
        word.setTranslate("ترک کردن");
        word.setCategory_id(1);
        word.setView_count(0);
        word.setFavorite(false);
        wordRepo.insert(word);

        word = new Word();
        word.setName("keen");
        word.setMeaning("sharp");
        word.setExample("keen sense of smell");
        word.setTranslate("مشتاق");
        word.setCategory_id(1);
        word.setView_count(0);
        word.setFavorite(false);
        wordRepo.insert(word);

        word = new Word();
        word.setName("Tact");
        word.setMeaning("ability");
        word.setExample("she always uses tact");
        word.setTranslate("تدبیر");
        word.setCategory_id(1);
        word.setView_count(0);
        word.setFavorite(false);
        wordRepo.insert(word);

    }

    private void showCategoryList(){
        showCategoryList(false);
    }

    private void showCategoryList(boolean reload){
        if (reload == true){
            categories = categoryRepo.findAll();
        }
        ArrayAdapter<Category> adapter = new CategoryListAdapter(MainActivity.this, R.layout.category_list_view, categories);
        ListView list = (ListView) findViewById(R.id.catMainListView);
        list.setAdapter(adapter);
        list.setItemsCanFocus(true);
    }
}
