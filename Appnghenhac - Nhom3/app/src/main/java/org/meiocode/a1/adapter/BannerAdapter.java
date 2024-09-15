package org.meiocode.a1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import org.meiocode.a1.R;
import org.meiocode.a1.activity.DanhSachBaiHatActivity;
import org.meiocode.a1.model.QuangCao;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayListbanner;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    @Override
    public int getCount() {
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner,null);
        ImageView imagebackgroundbanner = view.findViewById(R.id.imageview);
        ImageView imgsongbanner = view.findViewById(R.id.imageviewbanner);
        TextView txttenbanner = view.findViewById(R.id.textviewtitle);
        TextView txtnoidungbanner = view.findViewById(R.id.textviewtitledetail);
        Picasso.get().load(arrayListbanner.get(position).getHinhAnh()).into(imagebackgroundbanner);
        Picasso.get().load(arrayListbanner.get(position).getHinhAnh()).into(imgsongbanner);
        txttenbanner.setText(arrayListbanner.get(position).getTenBaiHat());
        txtnoidungbanner.setText(arrayListbanner.get(position).getNoiDung());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("banner",arrayListbanner.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}

