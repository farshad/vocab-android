package xyz.farshad.vocab.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.orm.SugarContext;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import xyz.farshad.vocab.R;
import xyz.farshad.vocab.component.DataAdapter.LevelListAdapter;
import xyz.farshad.vocab.model.Level;

public class LevelListActivity extends AppCompatActivity {

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

    private void showLevelList(boolean reload){
        if (reload){
            levels = Level.listAll(Level.class);
        }
        ArrayAdapter<Level> adapter = new LevelListAdapter(this, R.layout.level_list_view, levels);
        ListView list = findViewById(R.id.levelListView);
        list.setAdapter(adapter);
        list.setItemsCanFocus(true);
    }
}

