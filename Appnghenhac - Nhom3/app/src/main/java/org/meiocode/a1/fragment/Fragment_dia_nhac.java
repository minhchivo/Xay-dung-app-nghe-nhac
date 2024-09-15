package org.meiocode.a1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.meiocode.a1.R;

public class Fragment_dia_nhac extends Fragment {
    View view;
    ImageView imageviewdianhac;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac, container, false);
        imageviewdianhac = view.findViewById(R.id.imagedianhac);
        return view;
    }
    public void Playnhac(String hinhanh) {
        Picasso.get().load(hinhanh).into(imageviewdianhac);
    }
}
