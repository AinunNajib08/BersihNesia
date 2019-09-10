package com.example.bersihnesia.apihelper;

import android.text.Editable;

import com.example.bersihnesia.model.Community;
import com.example.bersihnesia.model.GetCommunity;
import com.example.bersihnesia.model.GetItemReedem;
import com.example.bersihnesia.model.GetSampahOrganik;
import com.example.bersihnesia.model.GetSampahnonOrganik;
import com.example.bersihnesia.model.PostNotif;
import com.example.bersihnesia.model.PostPersonal;
import com.example.bersihnesia.model.UploadImage;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {

    @GET("api/list_event")
    Call<ResponseBody> getEvent();

    @GET("api/list_event/data/{id_event}")
    Call<ResponseBody> getEventDetail(@Path("id_event") Integer id_event);

    @GET("api/list_community/search/{cari}")
    Call<GetCommunity> getSearch(@Path("cari") Editable cari);

    @GET("api/list_event/search/{cari}")
    Call<ResponseBody> getEventSearch(@Path("cari") Editable cari);

    @GET("api/status_member/sum/{id_community}")
    Call<ResponseBody> getSum(@Path("id_community") int id_community);

    @GET("api/status_member/check/{id_community}/{id_personal}")
    Call<ResponseBody> getCheckMember(@Path("id_community") int id_community,
                                      @Path("id_personal") int id_personal);

    @GET("api/status_member/insert/{id_community}/{id_personal}")
    Call<ResponseBody> getInsertJoin(@Path("id_community") int id_community,
                                      @Path("id_personal") int id_personal);

    @GET("api/list_event/detail/{id_event}/{id_personal}")
    Call<ResponseBody> getCheck(@Path("id_event") int id_event,
                                @Path("id_personal") int id_personal);

    @GET("api/list_event/member/{id_event}")
    Call<ResponseBody> getMember(@Path("id_event") int id_event);

    @GET("api/list_event/insert/{id_event}/{id_personal}")
    Call<ResponseBody> getInsert(@Path("id_event") int id_event,
                                 @Path("id_personal") int id_personal);

    @Multipart
    @POST("api/upload_image")
    Call<UploadImage> uploadFile(@Part MultipartBody.Part file,
                                 @Part("file") RequestBody name);

    @FormUrlEncoded
    @POST("api/register_personal")
    Call<PostPersonal> postPersonal(@Field("name") String name,
                                    @Field("address") String address,
                                    @Field("contact_person") String contact_person,
                                    @Field("email") String email,
                                    @Field("password") String password,
                                    @Field("jk") String jk,
                                    @Field("photo") String photo
    );

    @FormUrlEncoded
    @POST("api/report_lokasi")
    Call<PostPersonal> postReport(@Field("id_personal") String id_personal,
                                  @Field("address") String address,
                                  @Field("longlat") String longlat,
                                  @Field("description") String description,
                                  @Field("photo") String photo
    );

    @FormUrlEncoded
    @POST("api/register_community")
    Call<PostPersonal> postCommunity(@Field("id_personal") String id_personal,
                                     @Field("name_community") String name_community,
                                     @Field("contact_person") String contact_person,
                                     @Field("address") String address,
                                     @Field("latlong") String latlong,
                                     @Field("description") String description,
                                     @Field("legality") String legality

    );

    @FormUrlEncoded
    @POST("api/item_reedem")
    Call<PostPersonal> postReedem(
                                    @Field("id_personal") String id_personal,
                                    @Field("id_item") int id_item,
                                     @Field("nama") String nama,
                                     @Field("no_hp") String no_hp,
                                     @Field("alamat_pengiriman") String alamat_pengiriman,
                                     @Field("reedem_point") String reedem_point

    );


    @FormUrlEncoded
    @POST("api/login_personal")
    Call<PostPersonal> postLogin(@Field("email") String email,
                                 @Field("password") String password

    );

    @FormUrlEncoded
    @POST("api/item_reedem/point")
    Call<PostPersonal> postPoint(@Field("id_personal") String id_personal

    );
    @FormUrlEncoded
    @POST("api/item_reedem/trans")
    Call<PostNotif> postTrans(@Field("id_personal") String id_personal

    );

    @GET("api/list_community")
    Call<GetCommunity> getCommunity();

    @GET("api/list_community/location")
    Call<Community> getLocCommunity();


    @GET("api/item_reedem")
    Call<GetItemReedem> getItem();

    @GET("api/list_community")
    Call<ResponseBody> getComm();

    @GET("api/Informasi_sampah")
    Call<GetSampahOrganik> getSampahOrganik();

    @GET("api/Informasi_sampahnon")
    Call<GetSampahnonOrganik> getSampahnonOrganik();

    @Multipart
    @POST("api/upload_image")
    Call<UploadImage> upload(@Header("Authorization") String authorization,
                             @PartMap Map<String, RequestBody> map
    );
}
