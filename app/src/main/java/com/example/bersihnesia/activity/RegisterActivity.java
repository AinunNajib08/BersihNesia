package com.example.bersihnesia.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import com.example.bersihnesia.R;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.RetrofitClient;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.PostPersonal;
import com.example.bersihnesia.model.UploadImage;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText nama, email, no_hp, password, address;
    ImageView photo;
    TextView name_photo;
    Spinner jk;
    String mediaPath;
    Bitmap bitmap;
    Button registrasi, pilih;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        no_hp = findViewById(R.id.no_hp);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        photo = findViewById(R.id.photo);
        name_photo = findViewById(R.id.name_photo);
        jk = findViewById(R.id.jk);
        mApiService = UtilsApi.getAPIService();

        /*--------------------String Array---------------*/
        String[] jkArray = {"--Pilih--", "Laki-laki", "Perempuan"};
        List<String> jkList = Arrays.asList(jkArray);
        ArrayAdapter<String> jkAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, jkList);
        jk.setAdapter(jkAdapter);

        pilih = findViewById(R.id.pilih);
        pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });

        registrasi = findViewById(R.id.registrasi);
        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
                retrofit2.Call<PostPersonal> postPersonalCall = mApiService.postPersonal(nama.getText().toString(), email.getText().toString(), address.getText().toString(), jk.getSelectedItem().toString(), no_hp.getText().toString(), password.getText().toString(), name_photo.getText().toString());
                postPersonalCall.enqueue(new Callback() {
                    @Override
                    public void onResponse(retrofit2.Call call, Response response) {
                        Toast.makeText(RegisterActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(retrofit2.Call call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Gagal Daftar", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);

                // str1.setText(mediaPath);
                String filename = mediaPath.substring(mediaPath.lastIndexOf("/") + 1);
                name_photo.setText(filename);


                // Set the Image in ImageView for Previewing the Media
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                //Bitmap bMapScaled = Bitmap.createScaledBitmap(bitmap, 150, 00, true);

                photo.setImageBitmap(bitmap);
                //imgView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();

            } else {
                Toast.makeText(this, "Pilih Foto Dulu", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
    private void uploadFile() {


        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(mediaPath);


        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());



        retrofit2.Call<UploadImage> call = mApiService.uploadFile(fileToUpload, filename);
        call.enqueue(new Callback<UploadImage>() {
            @Override
            public void onResponse(retrofit2.Call<UploadImage> call, Response<UploadImage> response) {
                UploadImage serverResponse = response.body();
                if (serverResponse != null) {
                    if (serverResponse.getSuccess()) {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<UploadImage> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
