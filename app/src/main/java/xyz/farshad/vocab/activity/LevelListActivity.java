package xyz.farshad.vocab.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.orm.SugarContext;

import java.util.List;

import xyz.farshad.vocab.R;
import xyz.farshad.vocab.component.DataAdapter.LevelListAdapter;
import xyz.farshad.vocab.model.Level;

public class LevelListActivity extends Activity {

    List<Level> levels;
    private Long courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_list);
        setTitle("Levels");
        SugarContext.init(LevelListActivity.this);

        Bundle b = getIntent().getExtras();
        if(b != null && b.containsKey("courseId")){
            courseId = b.getLong("courseId");
            levels = Level.find(Level.class, "course_id = ?", courseId.toString());

            showLevelList(false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_list, menu);
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

    private void showLevelList(boolean reload){
        if (reload == true){
            levels = Level.listAll(Level.class);
        }
        ArrayAdapter<Level> adapter = new LevelListAdapter(LevelListActivity.this, R.layout.level_list_view, levels);
        ListView list = (ListView) findViewById(R.id.levelListView);
        list.setAdapter(adapter);
        list.setItemsCanFocus(true);
    }
}

