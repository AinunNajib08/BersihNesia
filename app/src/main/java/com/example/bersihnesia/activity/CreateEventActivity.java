package com.example.bersihnesia.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.bersihnesia.R;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.PostPersonal;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEventActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private SimpleTimeZone simpleTimeZone;
    ImageButton datePick;
    private String pictureFilePath, pictureFile;
    String Id, mediaPath;
    TextView imgEvent, tvNameEvent, tvDesciption;
    ImageView imgFoto;
    Bitmap bitmap;
    EditText longlat;
    Activity activity;
    Intent intent;
    Button btnCreate;
    private int PLACE_PICKER_REQUEST = 2;
    private int REQUEST_PICTURE_CAPTURE = 1;
    EditText ettDate, etTime, tvLokasi;
    BaseApiService mApiService;
    ImageButton btnTime, btnCamera, btnGaleri, btnLocation;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        activity = this;
        imgFoto = findViewById(R.id.imgFoto);
        intent = getIntent();
        longlat = findViewById(R.id.longlat);
        datePick = findViewById(R.id.btnDatePick);
        tvNameEvent = findViewById(R.id.tvNameEvent);
        tvLokasi = findViewById(R.id.tvLokasi);
        tvDesciption = findViewById(R.id.deskripsi);
        btnLocation = findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(activity), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
        btnGaleri = findViewById(R.id.btnGaleri);
        imgEvent = findViewById(R.id.imgEvent);
        ettDate = findViewById(R.id.ettDate);
        btnTime = findViewById(R.id.btnTime);
        etTime = findViewById(R.id.etTime);
        btnGaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
        btnCamera = findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
                if (intent.resolveActivity(getApplicationContext().getPackageManager()) != null) {

                    File pictureFile = null;
                    try {
                        pictureFile = getPictureFile();
                    } catch (IOException ex) {
                        Toast.makeText(getApplicationContext(),
                                "Photo file can't be created, please try again",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (pictureFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                                "com.example.bersihnesia.fileprovider",
                                pictureFile);

                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(intent, 1);
                    }
                }
            }
        });
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });

        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApiService = UtilsApi.getAPIService();
                mApiService.postEventt(intent.getIntExtra("id_comm", 1) ,tvNameEvent.getText().toString(), imgEvent.getText().toString(), tvDesciption.getText().toString(), tvLokasi.getText().toString(), ettDate.getText().toString(), etTime.getText().toString(), longlat.getText().toString(), "Public" )
                        .enqueue(new Callback<PostPersonal>() {
                            @Override
                            public void onResponse(Call<PostPersonal> call, Response<PostPersonal> response) {
                                Toast.makeText(getApplicationContext(), "Terima Kasih Permintaan Sedang di Proses", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<PostPersonal> call, Throwable t) {

                            }
                        });
            }
        });
    }

    private File getPictureFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        pictureFile = "IMG_" + timeStamp;
        File storageDir = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(pictureFile, ".jpg", storageDir);
        pictureFilePath = image.getAbsolutePath();
        return image;
    }

    private void showTimeDialog() {
        Calendar calendar = Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                etTime.setText(hourOfDay+":"+minute);
            }
        },
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                ettDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                String filename = mediaPath.substring(mediaPath.lastIndexOf("/") + 1);
                imgEvent.setText(filename);
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), selectedImage);
                int nh = (int) (bitmap.getHeight() * (512.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                imgFoto.setImageBitmap(scaled);
                cursor.close();

            } else if (requestCode == REQUEST_PICTURE_CAPTURE && resultCode == RESULT_OK) {
                File imgFile = new File(pictureFilePath);
                if (imgFile.exists()) {
                    bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), Uri.fromFile(imgFile));
                    int nh = (int) (bitmap.getHeight() * (512.0 / bitmap.getWidth()));
                    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                    imgFoto.setImageBitmap(scaled);
                    imgEvent.setText(pictureFile + ".jpg");
                }
            } else if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {

                Place place = PlacePicker.getPlace(data, getApplicationContext());
                String toastMsg = String.format(

                        "%s "
                        , place.getAddress());
                String toastLatLong = String.format(
                        place.getLatLng().latitude + "," + place.getLatLng().longitude);
                tvLokasi.setText(toastMsg);
                longlat.setText(toastLatLong);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }


    }

}
