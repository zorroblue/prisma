package com.summer.ram.deepfaceapp.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.Buffer;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by rameshwar on 17/12/17.
 */

public class ImageUploadService {

    public static Bitmap bitmap = null;

    public static void send(String filePath, final ImageHandler imageHandler) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

// Change base URL to your upload server URL.
        Service service = new Retrofit.Builder().baseUrl("http://10.0.2.2:8090").client(client).build().create(Service.class);

        File file = new File(filePath);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), reqFile);

        retrofit2.Call<okhttp3.ResponseBody> req = service.postImage(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Do Something
                if(response.isSuccessful()) {
                    try {
                        Log.d("DEBUG123", "Success");
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(response.body().byteStream());
                        //handleResponse(bufferedInputStream);
                        imageHandler.bitmapHandler(bufferedInputStream);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        Log.d("DEBUG123", "Failed");
                    }
                }
                else {
                    Log.d("DEBUG123", "Image upload not successful");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static void handleResponse(BufferedInputStream bufferedInputStream) {
        bitmap = BitmapFactory.decodeStream(bufferedInputStream);
        Log.d("DEBUG123", "Bitmap Done");
    }

    public interface ImageHandler {
        public void bitmapHandler(BufferedInputStream bufferedInputStream);
    }
}
