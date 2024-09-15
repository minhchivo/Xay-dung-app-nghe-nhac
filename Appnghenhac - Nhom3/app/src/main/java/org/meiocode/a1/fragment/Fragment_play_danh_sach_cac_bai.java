package org.meiocode.a1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.meiocode.a1.R;
import org.meiocode.a1.activity.PlayNhacActivity;
import org.meiocode.a1.adapter.PlayNhacAdapter;

public class Fragment_play_danh_sach_cac_bai extends Fragment {
    View view;
    RecyclerView recyclerView;
    PlayNhacAdapter playNhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_cac_bai,container,false);
        recyclerView = view.findViewById(R.id.recyclerviewplaynhac);
        if(PlayNhacActivity.mangbaihat.size()>0){
            playNhacAdapter = new PlayNhacAdapter(getActivity(), PlayNhacActivity.mangbaihat);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(playNhacAdapter);
        }
        return view;
    }
}
