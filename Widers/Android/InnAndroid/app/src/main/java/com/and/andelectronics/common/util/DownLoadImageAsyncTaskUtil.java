package com.and.andelectronics.common.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by 원성민 on 2017-06-13.
 */

public class DownLoadImageAsyncTaskUtil extends AsyncTask<String, Void, Bitmap>{
    private ImageView imageView;

    public DownLoadImageAsyncTaskUtil(ImageView imageView)
    {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params)
    {
        String urlDisplay = params[0];
        Bitmap bitmap = null;
        try
        {
            InputStream inputStream = new java.net.URL(urlDisplay).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

        }
        catch (Exception ex)
        {
            Log.e("Error", ex.getMessage());
            ex.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        //super.onPostExecute(bitmap);
        this.imageView.setImageBitmap(bitmap);
    }
}
