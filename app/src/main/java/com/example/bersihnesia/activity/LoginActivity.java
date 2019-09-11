package com.example.bersihnesia.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bersihnesia.MainActivity;
import com.example.bersihnesia.R;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.PostPersonal;

import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
EditText email,password;
Button login;
TextView regist;
ProgressDialog progressDialog;
BaseApiService mApiService;
SharedPreferences  sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        regist=findViewById(R.id.daftar);
        mApiService = UtilsApi.getAPIService();
        sharedPreferences = LoginActivity.this.getSharedPreferences("remember",Context.MODE_PRIVATE);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog=new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                retrofit2.Call<PostPersonal> postLogin=mApiService.postLogin(email.getText().toString(),password.getText().toString());
                postLogin.enqueue(new Callback<PostPersonal>() {
                    @Override
                    public void onResponse(retrofit2.Call<PostPersonal> call, Response<PostPersonal> response) {
                        progressDialog.dismiss();
                        String id_personal = response.body().getId_personal();
                        String email=response.body().getEmail();
                        String name=response.body().getName();
                        String address=response.body().getAddress();
                        String jk=response.body().getJk();
                        String no=response.body().getContact_person();
                        String photo=response.body().getPhoto_personal();
                        String point=response.body().getPoint();

                        Log.e("Berhasil Login", "Berhasil " +point);
                        if (TextUtils.isEmpty(id_personal)){
                            Toast.makeText(LoginActivity.this,"Email atau Password Salah",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"Berhasil Login",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("id_personal", id_personal);
                            editor.putString("name", name);
                            editor.putString("no", no);
                            editor.putString("address", address);
                            editor.putString("jk", jk);
                            editor.putString("photo", photo);
                            editor.putString("email", email);
                            editor.apply();
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<PostPersonal> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);


                    }
                });
            }
        });
    }
}
