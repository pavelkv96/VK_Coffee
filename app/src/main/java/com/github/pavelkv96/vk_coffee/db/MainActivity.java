package com.github.pavelkv96.vk_coffee.db;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.github.pavelkv96.vk_coffee.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    MessagesFragment messagesFragment;
    FriendsFragment friendsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messagesFragment = new MessagesFragment();
        friendsFragment = new FriendsFragment();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment myFragment = null;
        Class fragmentClass;
        switch (item.getItemId()) {
            case R.id.nav_camera:
                fragmentClass = FriendsFragment.class;
                break;
            case R.id.nav_gallery:
                fragmentClass = MessagesFragment.class;
                break;
            default:
                fragmentClass = MessagesFragment.class;
        }
        try {
            myFragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.getMessage();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).commit();
        setTitle(item.getTitle());
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
