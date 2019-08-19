package com.example.bersihnesia.apihelper;

import com.example.bersihnesia.BuildConfig;

public class UtilsApi {
    public static final String BASE_URL_API = BuildConfig.URL;

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }

}
