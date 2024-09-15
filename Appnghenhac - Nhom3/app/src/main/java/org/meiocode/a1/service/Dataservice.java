package org.meiocode.a1.service;

import org.meiocode.a1.model.Album;
import org.meiocode.a1.model.BaiHat;
import org.meiocode.a1.model.PlayList;
import org.meiocode.a1.model.QuangCao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET ("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();


    @GET ("playlistforcurrentday.php")
    Call<List<PlayList>> GetPlayListForCurrentDay();

    @GET("albumhot.php")
    Call<List<Album>>GetAlbumHot();

    @GET("baihatduocthich.php")
    Call<List<BaiHat>>GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoQuangCao(@Field("Id") String Id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoPlayList(@Field("IdPlayList") String IdPlayList);
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoAlbum(@Field("IdAlbum") String IdAlbum);
    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> GetSearchBaiHatTheoBaiHat(@Field("tukhoa") String tukhoa);

}
