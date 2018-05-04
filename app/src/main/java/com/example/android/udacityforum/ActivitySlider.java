package com.example.android.udacityforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ActivitySlider extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;
    private TextView[] mDots;
    Button done;
    Button prev;

    private SliderAdapter sliderAdapter;


    private int nCurrentPage;
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            if (i == 3) {
                done.setVisibility(View.VISIBLE);
                prev.setVisibility(View.GONE);
            } else {
                done.setVisibility(View.GONE);
                prev.setVisibility(View.VISIBLE);
            }
            addDOtsIndicator(i);
            nCurrentPage = i;
        }


        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public void addDOtsIndicator(int position) {
        mDots = new TextView[4];
        mDotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.transparent_white));

            mDotsLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.viewpager_activity);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideviewpager);
        mDotsLayout = (LinearLayout) findViewById(R.id.dotslayout);
        done = findViewById(R.id.done);
        done.setVisibility(View.GONE);
        prev = findViewById(R.id.prev);

        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDOtsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);


    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}