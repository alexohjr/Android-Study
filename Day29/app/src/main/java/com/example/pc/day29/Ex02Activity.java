package com.example.pc.day29;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

public class Ex02Activity extends AppCompatActivity {

    private ImageView img;
    private Button button;
    private Bitmap bitmap;

    private final int PICK_FROM_CAMERA = 111;

    private String nowTime; // 이미지 저장할 때 파일 이름으로 사용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        button = findViewById(R.id.btn_gallery);
        img = findViewById(R.id.img);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowTime = String.valueOf(System.currentTimeMillis());

                // ACTION_GET_CONTENT or ACTION_PICK ==> 갤러리로 이동
                // MediaStore.Images.Media.EXTERNAL_CONTENT_URI :: 외부 이미지 갤러리의 uri
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 1); // crop 될 사각형의 x 비율
                intent.putExtra("aspectY", 1); // crop 될 사각형의 y 비율
                intent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());

                intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                startActivityForResult(intent, PICK_FROM_CAMERA);
            }
        });
    }

    private Uri getTempUri() {
        return Uri.fromFile(getTempFile());
    }

    private File getTempFile() {
        // 사진을 저장할 폴더 생성
        File f = new File(Environment.getExternalStorageDirectory(), "myTemp");
        if(!f.exists()) {
            f.mkdir();
        }
        File f2 = new File(f, nowTime + "_tmp.jpg");
        try {
            f2.createNewFile();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return f2;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_FROM_CAMERA && resultCode == RESULT_OK && data != null) {
            String filePath = Environment.getExternalStorageDirectory() + "/myTemp/" + nowTime + "_tmp.jpg";

            bitmap = BitmapFactory.decodeFile(filePath);
            File file = new File(filePath);
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
            } catch(Exception e) {
                e.printStackTrace();
            }
            bitmap = Bitmap.createScaledBitmap(bitmap, 480, 480, true);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            img.setImageBitmap(bitmap);
            return;
        }
    }
}
