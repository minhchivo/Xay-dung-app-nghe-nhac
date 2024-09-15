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
import org.meiocode.a1.activity.DanhSachBaiHatActivity;
import org.meiocode.a1.model.Album;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> mangalbum;
    public AlbumAdapter(Context context, ArrayList<Album> mangalbum){
        this.context = context;
        this.mangalbum = mangalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false);

        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = mangalbum.get(position);
        holder.txttextviewalbum.setText(album.getTenAlbum());
        holder.txtviewcasi.setText(album.getTenCaSiAlbum());
        Picasso.get().load(album.getHinhAlbum()).into(holder.albumImageView);

    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView albumImageView;
        TextView txttextviewalbum,txtviewcasi;

        public ViewHolder(View itemView){
            super(itemView);
            albumImageView = itemView.findViewById(R.id.imageviewalbum);
            txttextviewalbum = itemView.findViewById(R.id.textviewalbum);
            txtviewcasi = itemView.findViewById(R.id.textviewcasi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("tenalbum",mangalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }
}
