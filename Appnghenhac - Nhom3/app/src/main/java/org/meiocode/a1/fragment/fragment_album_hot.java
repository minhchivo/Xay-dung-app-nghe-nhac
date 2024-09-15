package org.meiocode.a1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.meiocode.a1.R;
import org.meiocode.a1.activity.DanhSachBaiHatActivity;
import org.meiocode.a1.adapter.AlbumAdapter;
import org.meiocode.a1.model.Album;
import org.meiocode.a1.service.APIService;
import org.meiocode.a1.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_album_hot extends Fragment {
    View view;
    RecyclerView recyclerViewalbum;
    TextView txtalbum,txtxemthemalbum;
    AlbumAdapter albumAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_hot, container, false);
        recyclerViewalbum = view.findViewById(R.id.recycleviewalbum);
        txtalbum = view.findViewById(R.id.textviewalbum);
        txtxemthemalbum = view.findViewById(R.id.textviewxemthemalbum);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getServices();
        Call<List<Album>> callback =dataservice.GetAlbumHot();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList= (ArrayList<Album>) response.body();
                albumAdapter = new AlbumAdapter(getActivity(),albumArrayList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerViewalbum.setLayoutManager(layoutManager);
                recyclerViewalbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                
            }
        });
    }
}
