package com.example.stormbaron.agank.ui.activity;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;
import com.example.stormbaron.agank.R;
import com.example.stormbaron.agank.app.PermissionUtil;
import com.example.stormbaron.agank.ui.fragments.BaseFragment;
import com.example.stormbaron.agank.ui.fragments.WelfareFragment;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements WelfareFragment.OnFragmentInteractionListener {
    private TabLayout mTabLayout;
    private TabLayout.Tab mDiscoveryTab, mFuliTab, mMyTab;
    private List<BaseFragment> fragments=new ArrayList<>();

    private FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtil.checkPermission(MainActivity.this);
        //PermissionUtil.requestPermission(this,1);
        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.id_main_tab_layout);
        mDiscoveryTab = mTabLayout.newTab().setText("福利");
        mFuliTab = mTabLayout.newTab().setText("干货");
        mMyTab = mTabLayout.newTab().setText("我的");
        mTabLayout.addTab(mDiscoveryTab);
        mTabLayout.addTab(mFuliTab);
        mTabLayout.addTab(mMyTab);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == mDiscoveryTab) {

                }
                if (tab == mFuliTab) {

                }
                if (tab == mMyTab) {
                    Matisse.from(MainActivity.this)
                            .choose(MimeType.allOf())
                            .countable(true)
                            .maxSelectable(9)
                            //.addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                            .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(new GlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mFragmentManager=getSupportFragmentManager();
        fragments.add(WelfareFragment.newInstance("1","2"));
        FragmentTransaction mFragmentTransaction= mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.id_main_content,fragments.get(0));
        mFragmentTransaction.commit();
    }


    List<Uri> mSelected;
    private int REQUEST_CODE_CHOOSE = 1100;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            Toast.makeText(this, "你点击了退回", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
