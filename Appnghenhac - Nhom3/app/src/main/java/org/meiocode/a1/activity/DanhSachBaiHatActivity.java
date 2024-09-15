package org.meiocode.a1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.meiocode.a1.R;
import org.meiocode.a1.adapter.DanhSachBaiHatAdapter;
import org.meiocode.a1.model.Album;
import org.meiocode.a1.model.BaiHat;
import org.meiocode.a1.model.PlayList;
import org.meiocode.a1.model.QuangCao;
import org.meiocode.a1.service.APIService;
import org.meiocode.a1.service.Dataservice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    QuangCao quangcao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    ImageView imageViewdanhsachcakhuc;
    ArrayList<BaiHat> mangbaihat;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    PlayList playList;
    Album album;
    BaiHat baihat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        anhxa();
        Init();
        if (quangcao != null && !quangcao.getTenBaiHat().equals("")) {
            setValueInView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataQuangCao(quangcao.getId());
        }
        if (playList != null && !playList.getTen().equals("")) {
            setValueInView(playList.getTen(), playList.getHinhNen());
            GetDataPlayList(playList.getIdPlayList());
        }
        if(album != null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(), album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }

    }

    private void GetDataBaiHat(){

    }
    private void GetDataAlbum(String idAlbum) {
        Dataservice dataservice = APIService.getServices();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhSachBaiHatAdapter);
                eventclick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlayList(String IdPlayList) {
        Dataservice dataservice = APIService.getServices();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoPlayList(IdPlayList);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhSachBaiHatAdapter);
                eventclick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataQuangCao(String Id) {
        Dataservice dataservice = APIService.getServices();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoQuangCao(Id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                if (mangbaihat != null) {
                    DanhSachBaiHatAdapter adapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, mangbaihat);
                    recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                    recyclerViewdanhsachbaihat.setAdapter(adapter);
                    eventclick();
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {
                Toast.makeText(DanhSachBaiHatActivity.this, "Lỗi khi tải dữ liệu bài hát", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setValueInView(final String ten, final String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        new Thread(() -> {
            try {
                final URL url = new URL(hinh);
                final Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                final BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);

                runOnUiThread(() -> {
                    collapsingToolbarLayout.setBackground(bitmapDrawable);
                    Picasso.get().load(hinh).into(imageViewdanhsachcakhuc);
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    throw new RuntimeException(e);
                });
            } catch (IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    throw new RuntimeException(e);
                });
            }
        }).start();
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerViewdanhsachbaihat = findViewById(R.id.recyclerviewdanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        imageViewdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("banner")) {
            quangcao = (QuangCao) intent.getSerializableExtra("banner");
        }
        if (intent != null && intent.hasExtra("itemplaylist")) {
            playList = (PlayList) intent.getSerializableExtra("itemplaylist");
        }
        if(intent != null && intent.hasExtra("tenalbum")){
            album = (Album) intent.getSerializableExtra("tenalbum");
        }
        if (intent != null && intent.hasExtra("baihathot")) {
            baihat = (BaiHat) intent.getSerializableExtra("baihathot");
        }
    }

    private void eventclick() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(DanhSachBaiHatActivity.this, PlayNhacActivity.class);
            intent.putExtra("cacbaihat", mangbaihat);
            startActivity(intent);
        });
    }
}
