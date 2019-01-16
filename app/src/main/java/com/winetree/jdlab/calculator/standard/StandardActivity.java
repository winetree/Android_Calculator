package com.winetree.jdlab.calculator.standard;

import android.os.Bundle;
import com.winetree.jdlab.calculator.R;
import com.winetree.jdlab.calculator.common.CommonActivity;

public class StandardActivity extends CommonActivity {//implements NavigationView.OnNavigationItemSelectedListener
	
    private StandardContract.Presenter standardPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getNavigationBar();

        /**
         * Fragment Initialize
         */
        StandardFragment standardFragment = (StandardFragment)getSupportFragmentManager().findFragmentById(R.id.standard);

        if(standardFragment == null) {
            standardFragment = standardFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.content_main, standardFragment).commit();
        }

    }

//    /**
//     * Action Bar
//     */
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        FragmentManager manager = getSupportFragmentManager();
//
//        if (id == R.id.standard) {
//            //nothing
//        } else if (id == R.id.nav_gallery) {
//            Intent intent = new Intent(StandardActivity.this, DataActivity.class);
//            startActivity(intent);
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

}
