package org.meiocode.a1.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.meiocode.a1.R;
import org.meiocode.a1.adapter.mainviewpager;
import org.meiocode.a1.fragment.fragment_account;
import org.meiocode.a1.fragment.fragment_thu_vien;
import org.meiocode.a1.fragment.fragment_tim_kiem;
import org.meiocode.a1.fragment.fragment_trang_chu;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        init();
    }
    private void init(){
        mainviewpager mainviewpager = new mainviewpager(getSupportFragmentManager());
        mainviewpager.addfragment(new fragment_trang_chu(), "Trang Chủ");
        mainviewpager.addfragment(new fragment_tim_kiem(), "Tìm Kiếm");
        mainviewpager.addfragment(new fragment_thu_vien(), "Thư Viện");
        mainviewpager.addfragment(new fragment_account(),"Account");
        viewPager.setAdapter(mainviewpager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.search_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.account_circle_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.account_circle_24dp);
    }

}