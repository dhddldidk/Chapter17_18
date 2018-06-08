package kr.or.dgit.it.review_application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private int tabCount;//보여줄 fragment의 개수

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {//0,1,2,3,4
        switch (position){
            case 0: Tab1Fragment tab1 = new Tab1Fragment(); return tab1;
            case 1: return new Tab2Fragment();
            case 2: return new Tab3Fragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
