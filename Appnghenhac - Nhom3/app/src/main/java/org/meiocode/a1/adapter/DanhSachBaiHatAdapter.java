package org.meiocode.a1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.meiocode.a1.R;
import org.meiocode.a1.activity.PlayNhacActivity;
import org.meiocode.a1.model.BaiHat;

import java.util.List;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder> {
    private Context context;
    private List<BaiHat> listBaiHat;

    public DanhSachBaiHatAdapter(Context context, List<BaiHat> listBaiHat) {
        this.context = context;
        this.listBaiHat = listBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = listBaiHat.get(position);
        holder.tvTenBaiHat.setText(baiHat.getTenBaiHat());
        holder.tvCaSi.setText(baiHat.getCaSi());
        Picasso.get().load(baiHat.getHinhBaiHat()).into(holder.imgHinhBaiHat);

    }

    @Override
    public int getItemCount() {
        return listBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenBaiHat;
        TextView tvCaSi;
        ImageView imgHinhBaiHat,imgaeviewluotthich;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhBaiHat = itemView.findViewById(R.id.imgHinhBaiHat);
            tvTenBaiHat = itemView.findViewById(R.id.textviewtenbaihathot);
            tvCaSi = itemView.findViewById(R.id.textviewcasibaihathot);
            imgaeviewluotthich= itemView.findViewById(R.id.imageviewluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", listBaiHat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

