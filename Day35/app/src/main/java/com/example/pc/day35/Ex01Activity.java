package com.example.pc.day35;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


/*
    client_id : vieoKOQCjtwEsYNxbfpS
    client_secret : dWAW4Tpg6W
 */

public class Ex01Activity extends AppCompatActivity implements MyCallback {

    EditText editText;
    ImageView imageView;
    Button button;
    ProgressBar progressBar;

    public static String nKey; // 네이버에게 받은 캡차 key 값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        editText = findViewById(R.id.edit_text);
        imageView = findViewById(R.id.image);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progress_bar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // editText의 텍스트 얻기
                String userInput = editText.getText().toString().trim();
                if(userInput.isEmpty()) {
                    Toast.makeText(Ex01Activity.this, "문자를 입력하셔야 합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    new ResultAsyncTask(Ex01Activity.this).execute(userInput);
                }
            }
        });

        // 캡차 키 발급
        new NKeyAsyncTask(this).execute();
    }

    @Override
    public void setCaptchaImage(Bitmap bitmap) {
        // Bitmap 객체를 받아 이를 ImageView에 띄움
        progressBar.setVisibility(View.GONE);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void setCaptchaResult(boolean result) {
        // result 값을 받아 이를 Toast로 띄움
        String message = result ? "정답" : "땡";
        Log.d("MY", message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        // editText 공백으로
        editText.setText("");

        new NKeyAsyncTask(this).execute();
    }
}
