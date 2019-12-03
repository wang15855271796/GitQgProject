package com.puyue.www.qiaoge.view.scrollview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.puyue.www.qiaoge.R;



/**
 * @author cginechen
 * @date 2016-12-28
 */

public class NestingScrollActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    private ViewPager mViewPager;
    private SparseArray<RecyclerView> mPageMap = new SparseArray<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_nesting_scroll);
      //  mToolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mPagerAdapter);

    }



    private RecyclerView getPageView(int pos) {
        RecyclerView view = mPageMap.get(pos);
        if (view == null) {
            RecyclerView recyclerView = new RecyclerView(this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
          //  recyclerView.setAdapter(new MyRecyclerAdapter());
            mPageMap.put(pos, recyclerView);
            return recyclerView;
        }
        return view;
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            View view = getPageView(position);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(view, params);
            return view;
        }


    };

}
