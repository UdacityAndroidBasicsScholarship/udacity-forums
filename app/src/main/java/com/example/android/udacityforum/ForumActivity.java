package com.example.android.udacityforum;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;

import java.util.ArrayList;

public class ForumActivity extends AppCompatActivity {

    RecyclerView rv;
    FloatingActionButton fab_button;
    NavigationView navigationView2;
    DrawerLayout drawerLayout2;
    Toolbar toolbar2;
    ActionBarDrawerToggle actionBarDrawerToggle2;
    View actionBarView2;
    TextView actionBarTitle2;
    private TextView UserEmail;
    private TextView UserName;
    private ImageView UserPic;
    private ActionBar actionBar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        //getting values from previous activities.
        final String name = getIntent().getExtras().getString("name");
        final String picURL = getIntent().getExtras().getString("picURL");
        final String Email = getIntent().getExtras().getString("Email");

        rv = findViewById(R.id.recycler_view);
        fab_button = findViewById(R.id.fab_button);
        ArrayList<QuestionFormat> quesList = new ArrayList<>();
        //sample data---------------------------------------
        for (int i = 0; i < 15; i++) {
            quesList.add(new QuestionFormat("heading " + i, "content " + i));
        }
        //-----------------------------------------------
        MyForumRecyclerViewAdapter adapter = new MyForumRecyclerViewAdapter(quesList, this);
        rv.setAdapter(adapter);
        fab_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewQuestionActivity.class));
            }
        });

        navigationView2 = findViewById(R.id.navigation_drawer);
        toolbar2 = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout2 = (DrawerLayout) findViewById(R.id.nav_drawer2);
        setSupportActionBar(toolbar2);

        actionBarDrawerToggle2 = new ActionBarDrawerToggle(this, drawerLayout2, toolbar2, R.string.open_drawer, R.string.close_drawer);
        drawerLayout2.addDrawerListener(actionBarDrawerToggle2);
        //setting the user data to fields.
        View header = navigationView2.getHeaderView(0);
        UserName = (TextView) header.findViewById(R.id.tv_name);
        UserName.setText(name);
        UserPic = (ImageView) header.findViewById(R.id.iv_profile);
        Glide.with(ForumActivity.this).load(picURL).into(UserPic);
        UserEmail = (TextView) header.findViewById(R.id.tv_email);
        UserEmail.setText(Email);


        navigationView2.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.menu_profile:
                        Intent n = new Intent(ForumActivity.this, activity_userpanel.class);
                        startActivity(n);
                        n.putExtra("name2", name);
                        n.putExtra("picURL2", picURL);
                        n.putExtra("Email2", Email);
                        startActivity(n);
                        break;

                    case R.id.menu_home:
                        startActivity(new Intent(ForumActivity.this, MainActivity.class));
                        break;

                    case R.id.menu_logout:
                        AuthUI.getInstance().signOut(ForumActivity.this);
                        AppExit();
                        break;
                }
                return false;
            }
        });

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams
                .MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        actionBarView2 = LayoutInflater.from(this).inflate(R.layout.actionbar_custom_view, null);

        actionBarTitle2 = (TextView) actionBarView2.findViewById(R.id.tv_heading);
        actionBarTitle2.setText(getString(R.string.discussion_form));

        actionBar2 = getSupportActionBar();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(actionBarView2, params);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ham_icon);


    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle2.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        MenuItem searchItem = menu.findItem(R.id.action_search);
        menuInflater.inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager) ForumActivity.this.getSystemService(getApplicationContext().SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(ForumActivity.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search2:
                Toast.makeText(getApplicationContext(), "Action Search Clicked", Toast.LENGTH_SHORT).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
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
