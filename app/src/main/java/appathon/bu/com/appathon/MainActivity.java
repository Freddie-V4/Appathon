package appathon.bu.com.appathon;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.nfc.Tag;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;


public class MainActivity extends Activity {

    private String[] navDrawerTitles;
    private DrawerLayout navDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;
    Fragment fragment = new Fragment();
    private Fragment blankFrag = new Fragment();
    private final int POSITION = 0;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkPlayServices();  //check for valid install of Google Play Services

        navDrawerTitles = getResources().getStringArray(R.array.nav_array);
        navDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, navDrawerTitles));

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        navDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
                navDrawerLayout, /* DrawerLayout object */
                //R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                // super.onDrawerClosed(view);
//                getActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                getActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu();
            }
        };

        // Set the drawer toggle as the DrawerListener
        navDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {


                finish();
            }
            return false;
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content
        // view
        boolean drawerOpen = navDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            selectItem(position);
        }
    }


    /** Swaps fragments in the main content view */
    /**
     * Starts an Activity when item is clicked
     */
    private void selectItem(int position) {

        switch (POSITION) {
            case 0:
                FragmentManager mFragManager0 = getFragmentManager();
                FragmentTransaction mTrans0 = mFragManager0.beginTransaction();
                Fragment mStartFrag = new StartingFragment();

                mTrans0.replace(R.id.fragment_replace, mStartFrag).commit();

                break;
            case 1:
                FragmentManager mFragManager3 = getFragmentManager();
                FragmentTransaction mTrans3 = mFragManager3.beginTransaction();

                Fragment mReasonsFrag = new ReasonsFragment();

                mTrans3.replace(R.id.fragment_replace, mReasonsFrag).commit();

                break;


        }


        Bundle args = new Bundle();
        args.putInt(ReasonsFragment.TEA_TYPE_POS, position);
        fragment.setArguments(args);

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
//      setTitle(navDrawerTitles[position]);
        navDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
//        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}