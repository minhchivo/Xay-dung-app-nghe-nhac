package org.meiocode.a1.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import org.meiocode.a1.R;
import org.meiocode.a1.adapter.BannerAdapter;
import org.meiocode.a1.model.QuangCao;
import org.meiocode.a1.service.APIService;
import org.meiocode.a1.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_banner extends Fragment {
    View view;
    ViewPager viewPager;
    BannerAdapter bannerAdapter;
    Handler handler;
    int CurrentItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        GetData();
        anhxa();
        return view;
    }

    private void anhxa() {
        viewPager = view.findViewById(R.id.viewPager);

    }

    private void GetData() {
        Dataservice dataservice = APIService.getServices();
        Call<List<QuangCao>> callback = dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                ArrayList<QuangCao> banners = (ArrayList<QuangCao>) response.body();
                bannerAdapter = new BannerAdapter(getActivity() ,banners);
                viewPager.setAdapter(bannerAdapter);
                handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        CurrentItem = viewPager.getCurrentItem();
                        CurrentItem++;
                        if(CurrentItem >= viewPager.getAdapter().getCount()){
                            CurrentItem = 0;
                        }
                        viewPager.setCurrentItem(CurrentItem, true);
                        handler.postDelayed(this, 3000);
                    }
                };
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {

            }
        });

    }
}