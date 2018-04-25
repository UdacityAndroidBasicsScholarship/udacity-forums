package com.example.android.udacityforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ActivitySlider extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;
    private TextView[] mDots;

    private SliderAdapter sliderAdapter;




    private  int nCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.viewpager_activity);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideviewpager);
        mDotsLayout = (LinearLayout) findViewById(R.id.dotslayout);



        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDOtsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);


    }

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
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDOtsIndicator(i);

            nCurrentPage=i;


        }



        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
    public void sendMessage(View view) {
        Intent intent = new Intent(this, login_screen.class);
        startActivity(intent);
    }
}