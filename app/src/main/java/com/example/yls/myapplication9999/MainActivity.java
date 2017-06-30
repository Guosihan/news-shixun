package com.example.yls.myapplication9999;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.example.yls.myapplication9999.Fragment.MainFragment1;
import com.example.yls.myapplication9999.Fragment.MainFragment2;
import com.example.yls.myapplication9999.Fragment.MainFragment3;
import com.example.yls.myapplication9999.Fragment.MainFragment4;
import com.example.yls.myapplication9999.Fragment.MainFragment5;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity {

    private RadioGroup mRadioGroup;
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_01);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        initViewPager();
        initToolbar();
        initDrawerLayout();
    }
    private ActionBarDrawerToggle toggle;



    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("广交实训");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.item_01) {
            showToast("item 01");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initDrawerLayout() {
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                0,
                0);

        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();     // 同步drawerLayout和toolbar的状态
    }

    private void initViewPager() {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment1());
        fragments.add(new MainFragment2());
        fragments.add(new MainFragment3());
        fragments.add(new MainFragment4());
        fragments.add(new MainFragment5());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    @Override
    public void initListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_01:    // 点击单选时，切换ViewPager子界面
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_02:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_03:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_04:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.rb_05:
                        mViewPager.setCurrentItem(4);
                        break;
                }
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.rb_01);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.rb_02);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.rb_03);
                        break;
                    case 3:
                        mRadioGroup.check(R.id.rb_04);
                        break;
                    case 4:
                        mRadioGroup.check(R.id.rb_05);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initNavigationView();
    }

    private void initNavigationView() {

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.getItemId();
                return false;
            }
        });

    }





    @Override
    public void initData() {


    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
