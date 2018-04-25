package com.example.android.udacityforum;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.AccountKit;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    View actionBarView;
    TextView actionBarTitle;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams
                .MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        actionBarView = LayoutInflater.from(this).inflate(R.layout.actionbar_custom_view, null);

        actionBarTitle = (TextView) actionBarView.findViewById(R.id.tv_heading);
        actionBarTitle.setText(getString(R.string.activity_name));

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(actionBarView, params);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //adding listener to navigation drawer items

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_logout) {
                    if (AccountKit.getCurrentAccessToken() != null)
                        AccountKit.logOut();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                return false;
            }
        });




    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.option_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        MenuItem home = menu.findItem(R.id.menu_home);
        MenuItem course = menu.findItem(R.id.menu_course);
        MenuItem profile = menu.findItem(R.id.menu_profile);
        MenuItem logout = menu.findItem(R.id.menu_logout);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(getApplicationContext().SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new:
                Toast.makeText(getApplicationContext(), "Action New Clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_top:
                Toast.makeText(getApplicationContext(), "Action Top Clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_badges:
                Toast.makeText(getApplicationContext(), "Action Badges Clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_search:
                Toast.makeText(getApplicationContext(), "Action Search Clicked", Toast.LENGTH_SHORT).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void launchForumActivity(View v) {
        Intent intent = new Intent(this, ForumActivity.class);
        startActivity(intent);
    }

}
