package com.summer.ram.deepfaceapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.widget.Toast;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.summer.ram.deepfaceapp.components.ButtonContainerSpec;
import com.summer.ram.deepfaceapp.components.HomeComponent;
import com.summer.ram.deepfaceapp.network.ImageUploadService;


public class MainActivity extends Activity {

    public int PICK_IMAGE_REQUEST = 1;
    public String filePath = null;
    public int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE=12;
    public LithoView lithoView;
    public ComponentContext c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        c = new ComponentContext(this);

        final Component component = createHomeComponent(c);

         lithoView = LithoView.create(
                this,
                component
                );

        setContentView(lithoView);
    }

    Component createHomeComponent(ComponentContext c) {
        return HomeComponent.create(c)
                .context(MainActivity.this)
                .filePath(filePath)
                .buttonListener(new ButtonContainerSpec.OnButtonClickListener() {
                    @Override
                    public void onButtonClick() {
                        imageBrowse();
                        Log.d("Browsed filepath = ", filePath!=null ? filePath : "NULL");

                    }
                })
                .build();
    }

    @TargetApi(23)
    private void checkPermissionForReading()
    {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
                Toast.makeText(MainActivity.this, "Please allow reading your pictures", Toast.LENGTH_SHORT).show();
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }
    }

    private void imageBrowse() {
        checkPermissionForReading();
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if(requestCode == PICK_IMAGE_REQUEST){
                Uri picUri = data.getData();

                filePath = getPath(picUri);
                Log.d("DEBUG123", filePath);

                // recreate the entire component as the data flow is from root to child and not intra-component
                lithoView.setComponent(createHomeComponent(c));

                // upload the picture to the server
                if(filePath != null) { // a picture has been selected
                    ImageUploadService.send(filePath);
                }
            }

        }

    }

    // Get Path of selected image
    private String getPath(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(),    contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
}