package com.ok100.weather.gb.adepter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class BitmapFragmentpageAdepter extends FragmentStatePagerAdapter {

    private FragmentManager mfragmentManager;
    private List<Fragment> mlist;


    public BitmapFragmentpageAdepter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mlist = list;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Fragment getItem(int arg0) {
        return mlist.get(arg0);//显示第几个页面
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mlist.size();//有几个页面
    }

    public void addFragment(Fragment fm){
        mlist.set(0,fm);
        notifyDataSetChanged();
    }
}
