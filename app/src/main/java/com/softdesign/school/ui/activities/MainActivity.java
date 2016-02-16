package com.softdesign.school.ui.activities;

import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import com.softdesign.school.R;
import com.softdesign.school.ui.fragments.ContactsFragment;
import com.softdesign.school.ui.fragments.ProfileFragment;
import com.softdesign.school.ui.fragments.SettingFragment;
import com.softdesign.school.ui.fragments.TasksFragment;
import com.softdesign.school.ui.fragments.TeamFragment;
import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity {


    public static final String TOOL_BAR_COLOR = "Tool Bar Color";
    public static final String STATUS_BAR_COLOR = "Status Bar Color";

    private int mStatusBarColor;
    private int mToolBarColor;

    private NavigationView mNavigationView;
    private DrawerLayout mNavigationDrawer;

    private Fragment mFragment;
    private Toolbar mToolbar;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("School custom bars");
        Lg.e(this.getLocalClassName(), "========================\non Create");

        mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        //для обращения к элементам NavigationView
        View mHeaderLayout = mNavigationView.getHeaderView(0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        getNewToolBar();
        setupToolBar();
        setupDrawer();

        //задаем отступ в NavigationDrawer для того, чтобы элементы не уходили под StatuBar
        //и не делаем отступ в версии андроида < 5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mHeaderLayout.setPadding(0, getStatusBarHeight(), 0, 0);
        }


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame_container, new ProfileFragment())
                    .commit();
        }
    }


    /**
     * Метод для инициализации своего кастомного тулбара
     */
    private void getNewToolBar() {
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
    }

    /**
     * Метод определяющий высоту статусбара
     *
     * @return возвращает значние int
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * Метод настраивающий кастомный тулбар
     * добавляет кнопку меню, и задает картинку
     */
    private void setupToolBar() {
        if (mActionBar != null) {
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);  // задает иконку
            mActionBar.setDisplayHomeAsUpEnabled(true); //картинка с меню (обычно 3 полоски)
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           /* Toast.makeText(this, "Menu click", Toast.LENGTH_SHORT).show();*/
            mNavigationDrawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Lg.e(this.getLocalClassName(), "on start");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Lg.e(this.getLocalClassName(), "on resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.e(this.getLocalClassName(), "on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lg.e(this.getLocalClassName(), "on stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.e(this.getLocalClassName(), "on restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.e(this.getLocalClassName(), "on destroy");
    }

    private void setupDrawer() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_profile:
                        mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
                        mFragment = new ProfileFragment();
                        Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_contacts:
                        mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(true);
                        mFragment = new ContactsFragment();
                        Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_team:
                        mNavigationView.getMenu().findItem(R.id.drawer_team).setChecked(true);
                        mFragment = new TeamFragment();
                        Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_tasks:
                        mNavigationView.getMenu().findItem(R.id.drawer_tasks).setChecked(true);
                        mFragment = new TasksFragment();
                        Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_setting:
                        mNavigationView.getMenu().findItem(R.id.drawer_setting).setChecked(true);
                        mFragment = new SettingFragment();
                        Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                        break;
                }
                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, mFragment).addToBackStack(mFragment.getClass().getName()).commit();
                }
                mNavigationDrawer.closeDrawers();
                return false;
            }
        });

    }

    public void checkMenu(int id) {
        mNavigationView.getMenu().findItem(id).setChecked(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.e(this.getLocalClassName(), "on save instance state");
        outState.putInt(STATUS_BAR_COLOR, mStatusBarColor);
        outState.putInt(TOOL_BAR_COLOR, mToolBarColor);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.e(this.getLocalClassName(), "on restore instance state");


        if (savedInstanceState != null) {
            mStatusBarColor = savedInstanceState.getInt(STATUS_BAR_COLOR);
            mToolBarColor = savedInstanceState.getInt(TOOL_BAR_COLOR);
            setThemeColor(mStatusBarColor, mToolBarColor);


        }
    }


    /**
     * Метод меняющий цвета Statusbar и Toolbar, на входе поулчает цвета:
     *
     * @param colorStatusBar цвет статусбара
     * @param colorToolBar   цвет тулбара
     */
    private void setThemeColor(int colorStatusBar, int colorToolBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(colorStatusBar);
        }
        mActionBar.setBackgroundDrawable(new ColorDrawable(colorToolBar));
    }


}






