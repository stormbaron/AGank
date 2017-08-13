package com.example.stormbaron.agank.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;
import com.example.stormbaron.agank.R;
import com.example.stormbaron.agank.app.PermissionUtil;
import com.example.stormbaron.agank.model.entity.MessageEvent;
import com.example.stormbaron.agank.ui.fragments.BaseFragment;
import com.example.stormbaron.agank.ui.fragments.GankFragment;
import com.example.stormbaron.agank.ui.fragments.OnFragmentInteractionListener;
import com.example.stormbaron.agank.ui.fragments.UserFragment;
import com.example.stormbaron.agank.ui.fragments.WelfareFragment;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements OnFragmentInteractionListener {
    private Context mContext = this;
    private TabLayout mTabLayout;
    private TabLayout.Tab mDiscoveryTab, mFuliTab, mMyTab;
    private List<BaseFragment> fragments = new ArrayList<>();
    private List<TabLayout.Tab> tabs = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private int currentPosition = 0;
    private String[] title = {"轻松一刻", "干货", "我的"};
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtil.checkPermission(MainActivity.this);
        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.id_main_tab_layout);

        mDiscoveryTab = mTabLayout.newTab().setText(getResources().getStringArray(R.array.tab_array)[0]);
        mFuliTab = mTabLayout.newTab().setText(getResources().getStringArray(R.array.tab_array)[1]);
        mMyTab = mTabLayout.newTab().setText(getResources().getStringArray(R.array.tab_array)[2]);

        mTabLayout.addTab(mDiscoveryTab);
        mTabLayout.addTab(mFuliTab);
        mTabLayout.addTab(mMyTab);
        tabs.add(mDiscoveryTab);
        tabs.add(mFuliTab);
        tabs.add(mMyTab);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeFagment(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mFragmentManager = getSupportFragmentManager();
        fragments.add(WelfareFragment.newInstance("1", "2"));
        fragments.add(new GankFragment());
        fragments.add(UserFragment.newInstance("1", "2"));

        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.id_main_content, fragments.get(currentPosition));

        mFragmentTransaction.commit();
        configToolBar();
    }

    private void configToolBar() {
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle(title[0]);
        toolbar.setTitleTextColor(getResources().getColor(R.color.icons, null));
        setSupportActionBar(toolbar);
    }


    FragmentTransaction mFragmentTransaction;

    public void changeFagment(TabLayout.Tab tab) {
        int position = tabs.indexOf(tab);
        if (currentPosition == position) {
            return;
        }
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.id_main_content, fragments.get(position));
        currentPosition = position;
        mFragmentTransaction.commit();
        toolbar.setTitle(title[position]);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    //如果有Menu,创建完后,系统会自动添加到ToolBar上
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    private long oldTimeMillis = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if (System.currentTimeMillis() - oldTimeMillis < 1000) {
                finish();
            } else {
                Toast.makeText(this, "连续点击将退出", Toast.LENGTH_SHORT).show();
                oldTimeMillis = System.currentTimeMillis();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

    }
}
