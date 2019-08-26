package com.example.bersihnesia.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bersihnesia.R;
import com.example.bersihnesia.activity.HomeActivity;
import com.example.bersihnesia.activity.RegisterActivity;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.PostPersonal;
import com.example.bersihnesia.model.UploadImage;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class ReportFragment extends Fragment {
    RequestBody requestBody;
    ImageView photo;
    TextView name_photo, longlat;
    ImageButton btn_camera, btn_galeri;
    ImageButton btn_lokasi;
    Bitmap bitmap;
    EditText txt_alamat, description;
    Button btn_report;
    BaseApiService mApiService;
    String Id, mediaPath;
    private String pictureFilePath, pictureFile;
    private int PLACE_PICKER_REQUEST = 2;
    private int REQUEST_PICTURE_CAPTURE = 1;
    private static final int CAMERA_REQUEST = 1888;
    private File file;
    SharedPreferences sharedPreferences;

    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        photo = view.findViewById(R.id.photo);
        btn_camera = view.findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {

                    File pictureFile = null;
                    try {
                        pictureFile = getPictureFile();
                    } catch (IOException ex) {
                        Toast.makeText(getContext(),
                                "Photo file can't be created, please try again",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (pictureFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(getContext(),
                                "com.example.bersihnesia.fileprovider",
                                pictureFile);

                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(intent, 1);
                    }
                }
            }
        });
        mApiService = UtilsApi.getAPIService();
        btn_galeri = view.findViewById(R.id.btn_galeri);
        btn_galeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
        name_photo = view.findViewById(R.id.name_photo);
        btn_lokasi = view.findViewById(R.id.btn_lokasi);
        longlat = view.findViewById(R.id.longlat);
        longlat.setVisibility(View.GONE);
        btn_lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
        description = view.findViewById(R.id.description);
        txt_alamat = view.findViewById(R.id.txt_alamat);
        name_photo = view.findViewById(R.id.name_photo);
        sharedPreferences = view.getContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        Id = sharedPreferences.getString("id_personal", "0");
        btn_report = view.findViewById(R.id.btn_report);
        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
                Call<PostPersonal> postReport = mApiService.postReport(Id, txt_alamat.getText().toString(), longlat.getText().toString(), description.getText().toString(), name_photo.getText().toString());
                postReport.enqueue(new Callback<PostPersonal>() {
                    @Override
                    public void onResponse(Call<PostPersonal> call, Response<PostPersonal> response) {
                        Toast.makeText(getContext(), "Terimakasih untuk laporan anda!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getContext(),HomeActivity.class);
                        startActivity(intent);
                        getActivity().finish();

                    }

                    @Override
                    public void onFailure(Call<PostPersonal> call, Throwable t) {
                        Toast.makeText(getContext(), "Gagal", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                String filename = mediaPath.substring(mediaPath.lastIndexOf("/") + 1);
                name_photo.setText(filename);
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                int nh = (int) (bitmap.getHeight() * (512.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                photo.setImageBitmap(scaled);
                cursor.close();

            } else if (requestCode == REQUEST_PICTURE_CAPTURE && resultCode == RESULT_OK) {
                File imgFile = new File(pictureFilePath);
                if (imgFile.exists()) {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.fromFile(imgFile));
                    int nh = (int) (bitmap.getHeight() * (512.0 / bitmap.getWidth()));
                    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                    photo.setImageBitmap(scaled);
                    name_photo.setText(pictureFile + ".jpg");
                }
            } else if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {

                Place place = PlacePicker.getPlace(data, getActivity());
                String toastMsg = String.format(

                        "%s "
                        , place.getAddress());
                String toastLatLong = String.format(
                        place.getLatLng().latitude + "," + place.getLatLng().longitude);
                txt_alamat.setText(toastMsg);
                longlat.setText(toastLatLong);
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }


    }

    private File getPictureFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        pictureFile = "IMG_" + timeStamp;
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(pictureFile, ".jpg", storageDir);
        pictureFilePath = image.getAbsolutePath();
        return image;
    }

    private void uploadFile() {

    if (mediaPath!=null){
         file = new File(mediaPath);
    } else if (pictureFilePath!=null) {
        file = new File(pictureFilePath);
    }

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
                            Toast.makeText(getContext().getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext().getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<UploadImage> call, Throwable t) {
                    Toast.makeText(getContext().getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                }
            });
        }



}
