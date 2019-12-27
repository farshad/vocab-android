package xyz.farshad.vocab.activity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;
import xyz.farshad.vocab.R;
import xyz.farshad.vocab.component.DataAdapter.WordSwipeAdapter;
import xyz.farshad.vocab.databinding.ActivityWordPagerBinding;
import xyz.farshad.vocab.model.Word;

public class WordPagerActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {

    private ActivityWordPagerBinding binding;
    private TextToSpeech textToSpeech;
    private List<Word> words;
    private Integer currentItem;
    private boolean sound = true;
    private int speed = 3;
    Menu optionsMenu;

    Timer timer;
    ViewPager viewPager;
    WordSwipeAdapter wordSwipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_word_pager);
        setTitle("Words");
        textToSpeech = new TextToSpeech(this, this);
        textToSpeech.setLanguage(Locale.US);

        Bundle b = getIntent().getExtras();
        if (b != null && b.containsKey("wordId") && b.containsKey("levelId")) {
            int wordId = b.getInt("wordId");
            int levelId = b.getInt("levelId");
            words = Word.find(Word.class, "level_id = ?", Integer.toString(levelId));

            setPageAdopter(wordId);
        }
        pageSwitcher(speed);
        setViewPagerChangeListener();
        binding.hideTranslateButton.setOnClickListener(this);
        binding.showTranslateButton.setOnClickListener(this);
        binding.volumeUp.setOnClickListener(this);
        binding.pause.setOnClickListener(this);
        binding.play.setOnClickListener(this);

    }

    private void setPageAdopter(int wordId) {
        viewPager = findViewById(R.id.word_view_page);
        wordSwipeAdapter = new WordSwipeAdapter(this, words);
        viewPager.setAdapter(wordSwipeAdapter);
        currentItem = wordId;
        textToSpeech.speak(words.get(currentItem).getName(), TextToSpeech.QUEUE_FLUSH, null);
        viewPager.setCurrentItem(currentItem);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_word_pager, menu);
        optionsMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MenuItem soundOff = optionsMenu.getItem(0);
        MenuItem soundOn = optionsMenu.getItem(1);
        switch (id) {
            case R.id.sound_off:
                soundOn.setVisible(true);
                soundOff.setVisible(false);
                sound = false;
                break;
            case R.id.sound_on:
                soundOn.setVisible(false);
                soundOff.setVisible(true);
                sound = true;
                break;
            case R.id.increase_speed:
                if (speed != 0) {
                    speed--;
                    timer.cancel();
                    pageSwitcher(speed);
                }
                break;
            case R.id.decrease_speed:
                speed++;
                timer.cancel();
                pageSwitcher(speed);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInit(int i) {

    }

    public void pageSwitcher(int seconds) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000);
    }

    @Override
    public void onClick(View view) {
        View currentView = viewPager.findViewWithTag("word_pager" + viewPager.getCurrentItem());
        switch (view.getId()) {
            case R.id.showTranslateButton:
                currentView.findViewById(R.id.wordPagerTranslate).setVisibility(View.VISIBLE);
                binding.showTranslateButton.setVisibility(View.GONE);
                binding.hideTranslateButton.setVisibility(View.VISIBLE);
                break;
            case R.id.hideTranslateButton:
                currentView.findViewById(R.id.wordPagerTranslate).setVisibility(View.INVISIBLE);
                binding.showTranslateButton.setVisibility(View.VISIBLE);
                binding.hideTranslateButton.setVisibility(View.GONE);
                break;
            case R.id.volumeUp:
                textToSpeech.speak(words.get(viewPager.getCurrentItem()).getName(), TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.pause:
                binding.play.setVisibility(View.VISIBLE);
                binding.pause.setVisibility(View.GONE);
                timer.cancel();
                break;
            case R.id.play:
                binding.play.setVisibility(View.GONE);
                binding.pause.setVisibility(View.VISIBLE);
                pageSwitcher(speed);
                break;
        }
    }

    private void setViewPagerChangeListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                if (sound) {
                    textToSpeech.speak(words.get(position).getName(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(() -> {
                if (currentItem == words.size()) {
                    timer.cancel();
                } else {
                    viewPager.setCurrentItem(currentItem++);
                }
            });

        }
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        timer.purge();
        super.onDestroy();
    }
}
