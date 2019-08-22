package com.example.bersihnesia.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
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
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.IOException;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class ReportFragment extends Fragment {
    ImageView photo;
    TextView name_photo;
    ImageButton btn_camera, btn_galeri;
    CardView btn_lokasi;
    Bitmap bitmap;
    EditText txt_alamat;
    private int PLACE_PICKER_REQUEST = 2;
    private static final int CAMERA_REQUEST = 1888;

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
                startActivityForResult(intent, 1);
            }
        });
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
        txt_alamat=view.findViewById(R.id.txt_alamat);
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
                String mediaPath = cursor.getString(columnIndex);
                String filename = mediaPath.substring(mediaPath.lastIndexOf("/") + 1);
                name_photo.setText(filename);
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                photo.setImageBitmap(bitmap);
                cursor.close();

            } else if (requestCode == 1) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                photo.setImageBitmap(bitmap);
            } else if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {

                    Place place = PlacePicker.getPlace(data, getActivity());
                    String toastMsg = String.format(

                            "%s "
                            ,  place.getAddress());
                    String toastLatLong=String.format(
                            place.getLatLng().latitude+","+place.getLatLng().longitude);
                    txt_alamat.setText(toastMsg);
                }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }


    }
}
