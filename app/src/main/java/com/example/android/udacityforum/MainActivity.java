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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 1;
    private TextView UserEmail;
    private ImageView UserPic;
    private TextView UserName;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseUser userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //navigation view.
        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //setting values to navigation drawer header.
        View header = navigationView.getHeaderView(0);
        UserName = (TextView) header.findViewById(R.id.tv_name);
        UserPic = (ImageView) header.findViewById(R.id.iv_profile);
        UserEmail = (TextView) header.findViewById(R.id.tv_email);

        //Firebase authentication.
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                userDetails = firebaseAuth.getCurrentUser();
                if (userDetails != null) {
                    //user is signed in.
                    UserName.setText(userDetails.getDisplayName());
                    Glide.with(MainActivity.this).load(userDetails.getPhotoUrl()).into(UserPic);
                    UserEmail.setText(userDetails.getEmail());
                } else {
                    //user is signed out.
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.EmailBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.FacebookBuilder().build()))
                                    .setLogo(R.drawable.udacity_logo)
                                    .setTheme(R.style.FullscreenTheme)
                                    .build(),
                            RC_SIGN_IN);

                }
            }
        };

        //on item click in navigation side bar.
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.menu_profile:
                        Intent k = new Intent(MainActivity.this, activity_userpanel.class);
                        k.putExtra("name2", userDetails.getDisplayName());
                        k.putExtra("picURL2", userDetails.getPhotoUrl().toString());
                        k.putExtra("Email2", userDetails.getEmail());
                        startActivity(k);
                        break;

                    case R.id.menu_home:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
                    case R.id.menu_logout:
                        AuthUI.getInstance().signOut(MainActivity.this);
                        finish();
                }
                return false;
            }
        });

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams
                .MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        View actionBarView = LayoutInflater.from(this).inflate(R.layout.actionbar_custom_view, null);

        TextView actionBarTitle = (TextView) actionBarView.findViewById(R.id.tv_heading);
        actionBarTitle.setText(getString(R.string.activity_name));

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(actionBarView, params);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ham_icon);

        Button btn_forum = (Button) findViewById(R.id.btn_forum);
        btn_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(MainActivity.this, ForumActivity.class);
                j.putExtra("name", userDetails.getDisplayName());
                j.putExtra("picURL", userDetails.getPhotoUrl().toString());
                j.putExtra("Email", userDetails.getEmail());
                startActivity(j);
            }
        });
        Button btn_user = (Button) findViewById(R.id.user_panel);
        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(MainActivity.this, activity_userpanel.class);
                k.putExtra("name2", userDetails.getDisplayName());
                k.putExtra("picURL2", userDetails.getPhotoUrl().toString());
                k.putExtra("Email2", userDetails.getEmail());
                startActivity(k);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Signed In", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                finish();
            }
        }
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

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    public void export() {

    }

}
