package com.asiait.yygh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;
    List fragments = new ArrayList();
    String[] tab_titles;
    int[] tab_imgs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout=findViewById(R.id.tab_layout);
        mViewPager=findViewById(R.id.view_pager);
        fragments.add(Main_sy.newInstance("首页"));
        fragments.add(Main_rcap_hz.newInstance("首页"));
        fragments.add(Main_grzx.newInstance("个人中心"));
        tab_titles = new String[]{"首页","日程", "个人中心"};
        tab_imgs=new int[]
                {R.drawable.sy_selector,R.drawable.rc_selector,R.drawable.wd_selector}; setTabs(tab_titles,tab_imgs);
//设置viewpager的adapter
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
//TabLayout与ViewPager的绑定
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    /**
     * 设置添加Tab
     * @param tab_titles tab条目名字
     * @param tab_imgs tab上条目上的图片
     */
    private void setTabs(String[] tab_titles, int[] tab_imgs){
        for (int i = 0; i < tab_titles.length; i++) {
            //获取TabLayout的tab
            TabLayout.Tab tab = mTabLayout.newTab();
            //初始化条目布局view
            View view = getLayoutInflater().inflate(R.layout.main_tab_item,null);
            tab.setCustomView(view);
            //tab的文字
            TextView tvTitle = view.findViewById(R.id.tv_des);
            tvTitle.setText(tab_titles[i]);
            //tab的图片
            ImageView imgTab =  view.findViewById(R.id.iv_top);
            imgTab.setImageResource(tab_imgs[i]);
            if (i==0){
                //设置第一个默认选中
                mTabLayout.addTab(tab,true);
            }else {
                mTabLayout.addTab(tab,false);
            }
        }
    }

    /**
     * Created by ruancw on 2022/11/28.
     * FragmentAdapter
     */
    public class FragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments;
        public FragmentAdapter(FragmentManager fragmentManager, List<Fragment> mFragments) {
            super(fragmentManager);
            this.mFragments = mFragments;
        }
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}