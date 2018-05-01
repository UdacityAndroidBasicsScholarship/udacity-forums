package com.example.android.udacityforum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.udacityforum.Fragments.userPanelFragment1;
import com.example.android.udacityforum.Fragments.userPanelFragment2;
import com.example.android.udacityforum.Fragments.userPanelFragment3;
import com.example.android.udacityforum.Fragments.userPanelFragment4;
import com.firebase.ui.auth.AuthUI;

import java.io.IOException;

public class activity_userpanel extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;
    private ImageButton fab11;
    private ImageView pic;
    private TextView userNameLayout;
    private TextView emailLayout;
    private TextView UserEmail;
    private TextView UserName;
    private ImageView UserPic;
    NavigationView navigationView3;
    DrawerLayout drawerLayout3;
    Toolbar toolbar3;
    ActionBarDrawerToggle actionBarDrawerToggle3;
    View actionBarView3;
    TextView actionBarTitle3;
    private ActionBar actionBar3;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpanel);

        //For selecting picture from device when clicked on the imageButton.
        fab11 = (ImageButton) findViewById(R.id.fab11);
        fab11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, PICK_IMAGE_REQUEST);
            }
        });


        pic = (ImageView) findViewById(R.id.imageButton);
        userNameLayout = (TextView) findViewById(R.id.textView);
        emailLayout = (TextView) findViewById(R.id.textView2);

        final String name2 = getIntent().getExtras().getString("name2");
        final String picURL2 = getIntent().getExtras().getString("picURL2");
        final String Email2 = getIntent().getExtras().getString("Email2");

        //setting the imported data to the views.
        userNameLayout.setText(name2);
        emailLayout.setText(Email2);
        Glide.with(activity_userpanel.this).load(picURL2).into(pic);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        toolbar3 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar3);
        navigationView3 = findViewById(R.id.navigation_drawer);
        drawerLayout3 = (DrawerLayout) findViewById(R.id.nav_drawer3);
        actionBarDrawerToggle3 = new ActionBarDrawerToggle(this, drawerLayout3, toolbar3, R.string.open_drawer, R.string.close_drawer);
        drawerLayout3.addDrawerListener(actionBarDrawerToggle3);
        //setting the user data to fields.
        View header = navigationView3.getHeaderView(0);
        UserName = (TextView) header.findViewById(R.id.tv_name);
        UserName.setText(name2);
        UserPic = (ImageView) header.findViewById(R.id.iv_profile);
        Glide.with(activity_userpanel.this).load(picURL2).into(UserPic);
        UserEmail = (TextView) header.findViewById(R.id.tv_email);
        UserEmail.setText(Email2);

        navigationView3.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.menu_profile:
                        Intent o = new Intent(activity_userpanel.this, activity_userpanel.class);
                        startActivity(o);
                        o.putExtra("name2", name2);
                        o.putExtra("picURL2", picURL2);
                        o.putExtra("Email2", Email2);
                        startActivity(o);
                        break;

                    case R.id.menu_home:
                        startActivity(new Intent(activity_userpanel.this, MainActivity.class));
                        break;

                    case R.id.menu_logout:
                        AuthUI.getInstance().signOut(activity_userpanel.this);
                        AppExit();
                        break;
                }
                return false;
            }
        });

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams
                .MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        actionBarView3 = LayoutInflater.from(this).inflate(R.layout.actionbar_custom_view, null);

        actionBarTitle3 = (TextView) actionBarView3.findViewById(R.id.tv_heading);
        actionBarTitle3.setText(getString(R.string.user_panel));

        actionBar3 = getSupportActionBar();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(actionBarView3, params);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ham_icon);


    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle3.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_userpanel, menu);
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


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    userPanelFragment1 tab1 = new userPanelFragment1();
                    return tab1;
                case 1:
                    userPanelFragment2 tab2 = new userPanelFragment2();
                    return tab2;
                case 2:
                    userPanelFragment3 tab3 = new userPanelFragment3();
                    return tab3;
                case 3:
                    userPanelFragment4 tab4 = new userPanelFragment4();
                    return tab4;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }

    //getting the image.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.imageButton);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void AppExit() {

        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    /*int pid = android.os.Process.myPid();=====> use this if you want to kill your activity. But its not a good one to do.
    android.os.Process.killProcess(pid);*/

    }


}
