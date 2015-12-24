package xyz.farshad.vocab.component;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import xyz.farshad.vocab.R;

/**
 * Created by farshad on 10/1/15.
 */
public class WordSwipeAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    public WordSwipeAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return (view==(LinearLayout)o) ;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.word_swipe_layer, container, false);
        TextView textView = (TextView) item_view.findViewById(R.id.textView);
        textView.setText("page " + position);

        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
