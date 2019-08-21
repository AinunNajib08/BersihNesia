package com.example.bersihnesia.apihelper;

import android.text.Editable;

import com.example.bersihnesia.model.GetCommunity;
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
    @POST("api/login_personal")
    Call<PostPersonal> postLogin(@Field("email") String email,
                                 @Field("password") String password

    );

    @GET("api/list_community")
    Call<GetCommunity> getCommunity();

    @Multipart
    @POST("api/upload_image")
    Call<UploadImage> upload( @Header("Authorization") String authorization,
                              @PartMap Map<String, RequestBody> map
    );
}
