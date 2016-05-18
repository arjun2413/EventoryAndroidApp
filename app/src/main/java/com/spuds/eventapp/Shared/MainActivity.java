package com.spuds.eventapp.Shared;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
import com.spuds.eventapp.About.AboutFragment;
import com.spuds.eventapp.CategoriesList.CategoriesListFragment;
import com.spuds.eventapp.CreateEvent.CreateEventFragment;
import com.spuds.eventapp.FindPeople.FindPeopleFragment;
import com.spuds.eventapp.HomeFeed.HomeFeedTabsFragment;
import com.spuds.eventapp.Login.LoginActivity;
import com.spuds.eventapp.MyEvents.MyEventsTabsFragment;
import com.spuds.eventapp.Notifications.NotificationsFragment;
import com.spuds.eventapp.Profile.ProfileFragment;
import com.spuds.eventapp.R;
import com.spuds.eventapp.Settings.SettingsFragment;
import com.spuds.eventapp.SubscriptionFeed.SubscriptionFeedTabsFragment;
import com.spuds.eventapp.SubscriptionsList.SubscriptionsListFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment currentFragment;
    HomeFeedTabsFragment homeFeedTabsFragment;
    NotificationsFragment notificationsFragment;
    CategoriesListFragment categoriesListFragment;
    MyEventsTabsFragment myEventsTabsFragment;
    SubscriptionsListFragment subscriptionsListFragment;
    FindPeopleFragment findPeopleFragment;
    SubscriptionFeedTabsFragment subscriptionFeedTabsFragment;
    AboutFragment aboutFragment;
    SettingsFragment settingsFragment;

    SearchBox search;
    public String searchType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFragments();
        setupMainToolbar();
        setupSearchToolbar();
        setupDrawer();

        searchType = getString(R.string.fragment_home_feed);
    }

    void setupMainToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    void setupSearchToolbar() {
        search = (SearchBox) findViewById(R.id.searchbox);

        search.setLogoText("Search for Events");
        search.setLogoTextColor(Color.parseColor("#bfbfbf"));
        search.setDrawerLogo(R.drawable.campus);

        final ArrayList<SearchResult> list = new ArrayList<SearchResult>();

        search.setSearchables(list);

        search.setSearchListener(new SearchBox.SearchListener(){

            @Override
            public void onSearchTermChanged(String s) {
                //React to the search term changing
                //Called after it has updated results
                Log.v("onSearchTermChanged", s);

                SearchResult option = new SearchResult(s, getResources().getDrawable(R.drawable.email));
                list.add(option);
                option = new SearchResult(s, getResources().getDrawable(R.drawable.email));

                list.add(option);
                option = new SearchResult(s, getResources().getDrawable(R.drawable.email));

                list.add(option);


                // TODO (M): Search

                if (searchType.equals(R.string.fragment_home_feed)) {

                } else if (searchType.equals(R.string.my_events)) {

                } else if (searchType.equals(R.string.fragment_my_sub)) {

                } else if (searchType.equals(R.string.fragment_my_sub_feed)) {

                } else if (searchType.equals(R.string.fragment_find_people)) {

                } else if (searchType.equals(R.string.cat_academic)) {

                } else if (searchType.equals(R.string.cat_campus)) {

                } else if (searchType.equals(R.string.cat_concerts)) {

                } else if (searchType.equals(R.string.cat_food)) {

                } else if (searchType.equals(R.string.cat_free)) {

                } else if (searchType.equals(R.string.cat_social)) {

                } else if (searchType.equals(R.string.cat_sports)) {

                } else {
                    Log.v("search: ", "something went wrong");
                }

                search.setSearchables(list);
                search.updateResults();

            }

            @Override
            public void onSearch(String searchTerm) {
                Toast.makeText(MainActivity.this, searchTerm +" Searched", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResultClick(SearchResult result) {
                //React to a result being clicked
            }


            @Override
            public void onSearchOpened() {

            }

            @Override
            public void onSearchCleared() {

            }

            @Override
            public void onSearchClosed() {

            }

        });
        params = (RelativeLayout.LayoutParams) search.getLayoutParams();

    }

    RelativeLayout.LayoutParams params;

    public void removeSearchToolbar() {


        if (findViewById(R.id.search) != null)
            ((ViewManager) search.getParent()).removeView(search);

    }

    public void addSearchToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (findViewById(R.id.search) == null)
            ((ViewManager) toolbar.getParent()).addView(search, params);

        search.setLogoText("Search for Events");

    }

    void setupDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);
    }

    void setupFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Checks if home fragment has been committed already
        currentFragment = fragmentManager.findFragmentByTag(getString(R.string.home));

        if (currentFragment == null) {

            // TODO (M): POST request to get account information
            // ex: who subscribing to, first 10 for each feed, profile information
            // sending information to respective fragments
            // talk about what to load first, what to load later

            homeFeedTabsFragment = new HomeFeedTabsFragment();
            notificationsFragment = new NotificationsFragment();
            categoriesListFragment = new CategoriesListFragment();
            myEventsTabsFragment = new MyEventsTabsFragment();
            subscriptionsListFragment = new SubscriptionsListFragment();
            findPeopleFragment = new FindPeopleFragment();
            subscriptionFeedTabsFragment = new SubscriptionFeedTabsFragment();
            aboutFragment = new AboutFragment();
            settingsFragment = new SettingsFragment();

            fragmentTransaction
                    .add(R.id.fragment_frame_layout, settingsFragment, getString(R.string.settings))
                    .hide(settingsFragment)
                    .add(R.id.fragment_frame_layout, aboutFragment, getString(R.string.about))
                    .hide(aboutFragment)
                    .add(R.id.fragment_frame_layout, subscriptionFeedTabsFragment, getString(R.string.subscriptionFeed))
                    .hide(subscriptionFeedTabsFragment)
                    .add(R.id.fragment_frame_layout, findPeopleFragment, getString(R.string.findPeople))
                    .hide(findPeopleFragment)
                    .add(R.id.fragment_frame_layout, subscriptionsListFragment, getString(R.string.subscriptions))
                    .hide(subscriptionsListFragment)
                    .add(R.id.fragment_frame_layout, myEventsTabsFragment, getString(R.string.my_events))
                    .hide(myEventsTabsFragment)
                    .add(R.id.fragment_frame_layout, categoriesListFragment, getString(R.string.category))
                    .hide(categoriesListFragment)
                    .add(R.id.fragment_frame_layout, notificationsFragment, getString(R.string.notifications))
                    .hide(notificationsFragment)
                    .add(R.id.fragment_frame_layout, homeFeedTabsFragment, getString(R.string.home))
                    .commit();

            currentFragment = homeFeedTabsFragment;
        } else {
            homeFeedTabsFragment = (HomeFeedTabsFragment) currentFragment;
            notificationsFragment = (NotificationsFragment) getSupportFragmentManager().
                    findFragmentByTag(getString(R.string.notifications));
            categoriesListFragment = (CategoriesListFragment) getSupportFragmentManager().
                    findFragmentByTag(getString(R.string.category));
            myEventsTabsFragment = (MyEventsTabsFragment) getSupportFragmentManager().
                    findFragmentByTag(getString(R.string.my_events));
            subscriptionsListFragment = (SubscriptionsListFragment) getSupportFragmentManager().
                    findFragmentByTag(getString(R.string.subscriptions));
            findPeopleFragment = (FindPeopleFragment) getSupportFragmentManager().
                    findFragmentByTag(getString(R.string.findPeople));
            subscriptionFeedTabsFragment = (SubscriptionFeedTabsFragment) getSupportFragmentManager().
                    findFragmentByTag(getString(R.string.subscriptionFeed));
            aboutFragment = (AboutFragment) getSupportFragmentManager().
                    findFragmentByTag(getString(R.string.about));
            settingsFragment = (SettingsFragment) getSupportFragmentManager().
                    findFragmentByTag(getString(R.string.settings));


            fragmentTransaction
                    .hide(notificationsFragment)
                    .hide(categoriesListFragment)
                    .hide(myEventsTabsFragment)
                    .hide(subscriptionsListFragment)
                    .hide(findPeopleFragment)
                    .hide(subscriptionFeedTabsFragment)
                    .hide(aboutFragment)
                    .hide(settingsFragment)
                    .commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String title = item.getTitle().toString();

        Fragment newFragment = null;
        if (id == R.id.home) {

            addSearchToolbar();
            searchType = getString(R.string.fragment_home_feed);
            newFragment = homeFeedTabsFragment;

        } else if (id == R.id.notifications) {

            removeSearchToolbar();
            newFragment = notificationsFragment;

        } else if (id == R.id.category) {

            removeSearchToolbar();
            newFragment = categoriesListFragment;

        } else if (id == R.id.myEvents) {

            addSearchToolbar();
            searchType = getString(R.string.fragment_my_events);
            newFragment = myEventsTabsFragment;

        } else if (id == R.id.subscriptions) {

            addSearchToolbar();
            searchType = getString(R.string.fragment_my_sub);
            search.setLogoText("Search for Subscriptions");
            newFragment = subscriptionsListFragment;

        } else if (id == R.id.find_people) {

            addSearchToolbar();
            searchType = getString(R.string.fragment_find_people);
            search.setLogoText("Search for People");
            newFragment = findPeopleFragment;

        } else if (id == R.id.subscriptionFeed) {

            addSearchToolbar();
            searchType = getString(R.string.fragment_my_sub_feed);
            newFragment = subscriptionFeedTabsFragment;

        } else if (id == R.id.about) {

            removeSearchToolbar();
            newFragment = aboutFragment;

        } else if (id == R.id.accMang) {

            removeSearchToolbar();
            newFragment = settingsFragment;

        } else if (id == R.id.logOut) {
            new AlertDialog.Builder(this)
                    .setTitle("Log Out")
                    .setMessage("Are you sure you want to log out?")
                    .setPositiveButton("Log Out", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    //.setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        if (newFragment != null) {
            Log.v("MainActivity", "newFragment != null switching to " + title);
            getSupportFragmentManager().beginTransaction()
                    .show(newFragment)
                    .replace(R.id.fragment_frame_layout, newFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(title)
                    .commit();
        }

        // Close drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void goToProfile(View view) {
        Fragment profileFragment = new ProfileFragment();

        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.profile_type), getString(R.string.profile_type_owner));
        // TODO (M): app owner's id
        bundle.putString(getString(R.string.user_id), "1adsf");

        profileFragment.setArguments(bundle);

        removeSearchToolbar();
        // Add Event Details Fragment to fragment manager
        getSupportFragmentManager().beginTransaction()
                .show(profileFragment)
                .replace(R.id.fragment_frame_layout, profileFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(getString(R.string.fragment_profile))
                .commit();

        // Close drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

                super.onBackPressed();

        }
        removeSearchToolbar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_create_event) {
            CreateEventFragment createEventFragment = new CreateEventFragment();

            removeSearchToolbar();
            // Add Event Details Fragment to fragment manager
            this.getSupportFragmentManager().beginTransaction()
                    .show(createEventFragment)
                    .replace(R.id.fragment_frame_layout, createEventFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(getString(R.string.fragment_create_event))
                    .commit();
        }
        // Must be left empty so onOptionsItemSelected will be called in subsequent fragments
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SearchBox.VOICE_RECOGNITION_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            search.populateEditText(matches.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
