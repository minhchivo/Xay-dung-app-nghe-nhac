package org.meiocode.a1.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class mainviewpager extends FragmentStatePagerAdapter{
    private ArrayList<Fragment> arrayfragments=new ArrayList<>();
    private ArrayList<String> arraytitle=new ArrayList<>();

    public mainviewpager(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrayfragments.get(position);
    }

    @Override
    public int getCount() {
        return arrayfragments.size();
    }
    public void addfragment(Fragment fragment, String title){
        arrayfragments.add(fragment);
        arraytitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arraytitle.get(position);
    }
}