package com.example.pc.day35;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class ImageAsyncTask extends AsyncTask<Void, Void, Bitmap> {

    private MyCallback caller;

    // 생성자
    public ImageAsyncTask(MyCallback callback) {
        this.caller = callback; // Ex01Activity
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        return getImage(); // 네이버에게 jpg이미지를 받아서 이를 bitmap으로 변환
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        caller.setCaptchaImage(bitmap);
    }

    private Bitmap getImage() {
        Bitmap bitmap = null;
        String clientId = MyCallback.CLIENT_ID;//애플리케이션 클라이언트 아이디값";
        String clientSecret = MyCallback.CLIENT_SECRET;//애플리케이션 클라이언트 시크릿값";
        try {
            String key = Ex01Activity.nKey; // https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
            String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // 랜덤한 이름으로  파일 생성
                String tempname = Long.valueOf(new Date().getTime()).toString();


                File f = new File(Environment.getExternalStorageDirectory() + "/" + tempname + ".jpg");
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }

                // 비트맵 생성
                bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/" + tempname + ".jpg");

                // 비트맵 생성 뒤, 임시 파일 삭제
                f.delete();

                is.close();
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                Log.d("MY", response.toString());
            }
        } catch (Exception e) {
            Log.d("MY", e.getMessage());
        }
        return bitmap;
    }
}
