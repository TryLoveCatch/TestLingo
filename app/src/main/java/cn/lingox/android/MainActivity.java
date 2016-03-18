package cn.lingox.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import cn.lingox.android.bin.home.HomeFragment;
import cn.lingox.android.framework.BaseActivity;
import cn.lingox.android.framework.IToolbarAndFab;
import cn.lingox.android.test.R;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    //===============界面变量==============
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.fab)
    FloatingActionButton mFab;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    //===============逻辑变量==============
    private int mIndex;
    //===============生命周期==============

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_main);
    }



    //===============事件接口==============
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
                    mDrawerLayout.closeDrawers();
                }else{
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawers();
        }else {
            showExitDialog();
        }
    }

    @Override
    public void initViewProperty() {
        setForbidFinishActivityGesture(true);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment tFrg = getSupportFragmentManager().findFragmentById(R.id.content_frame);
                if (tFrg instanceof IToolbarAndFab) {
                    ((IToolbarAndFab) tFrg).onToolbarClicked(mToolbar);
                }
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Fragment tFrg = getSupportFragmentManager().findFragmentById(R.id.content_frame);
                if (tFrg instanceof IToolbarAndFab) {
                    ((IToolbarAndFab) tFrg).onFabClicked(mFab);
                }
            }
        });


        ActionBarDrawerToggle tDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        tDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(tDrawerToggle);

        mNavigationView.setNavigationItemSelectedListener(this);

        fillView();
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_favourites:
                break;
            case R.id.item_follow:
                break;
            case R.id.item_feedback:
                break;
            case R.id.item_setting:
                break;
        }
        mDrawerLayout.closeDrawers();
        return true;
    }

    //===============对外方法==============
    //===============私有方法==============
    private void loadData(){

    }

    private void fillView(){
        setContentFragment(HomeFragment.class);
    }


    private void showExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_exit);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                getApplication().onTerminate();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setMessage(R.string.msg_exit);
        AlertDialog tDialog = builder.create();
        tDialog.show();
    }

}
