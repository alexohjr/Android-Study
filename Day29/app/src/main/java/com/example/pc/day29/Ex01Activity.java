package com.example.pc.day29;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Ex01Activity extends AppCompatActivity {

    private final int TAKE_CAMERA = 1; // 카메라 촬영임을 표시할 상수
    private final int TAKE_GALLERY = 2; // 앨범 선택임을 표시할 상수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        Button cameraBtn = findViewById(R.id.camera_btn);
        Button galleryBtn = findViewById(R.id.gallery_btn);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                // 다음 실행할 액티비티를 카메라 촬영으로
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, TAKE_CAMERA);
                /*
                    startActivityForResult(실행할 액티비티, 전달할 결과값(int형))
                    : intent를 실행한 후, 다시 이 액티비티로 돌아오도록 하고
                      결과값을 넣으면서 intent를 실행하면
                      intent 액티비티가 끝나고 다시 Ex01Activity로 돌아올 때
                      onActivityResult() 메서드가 실행됨, 이 때 결과값이 자동으로 들어감
                 */
            }
        });

        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                // 갤러리로 이동
                intent.setAction(intent.ACTION_GET_CONTENT);

                // 모든 이미지 타입을 포함(jpg, jpeg, png 등)
                intent.setType("image/*");

                startActivityForResult(intent, TAKE_GALLERY);
            }
        });

    } // onCreate()

    // startActivityForResult()를 통해 타 액티비티가 실행되면
    // 이후 다시 Ex01Activity로 돌아옴. 이 때 onActivityResult()가 자동 실행됨

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // resultCode : 타 액티비티의 실행 결과값
        //              문제 없이 액티비티가 종료되었을 경우 -1(RESULT_OK)이 들어옴
        if(resultCode != RESULT_OK) {
            return; // 타 액티비티가 정상 종료되지 않았을 경우 이 메서드도 종료함
        }

        // 카메라 촬영이었을 경우
        if(requestCode == TAKE_CAMERA) {
            if(data != null) {
                // 촬영된 이미지는 인자값으로 받은 intent 객체에
                // 'data'라는 이름으로 저장되어 있음
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");

                if(bitmap != null) {
                    ImageView imageView = findViewById(R.id.img_view);
                    imageView.setImageBitmap(bitmap);
                }
            }
            return;
        }

        // 갤러리 선택이었을 경우
        if(requestCode == TAKE_GALLERY) {
            if(data != null) {
                Uri uri = data.getData();
                if(uri != null) {
                    Log.d("MY", uri.toString());
                    ImageView imageView = findViewById(R.id.img_view);
                    imageView.setImageURI(uri);
                }
            }
        }
    }
}
