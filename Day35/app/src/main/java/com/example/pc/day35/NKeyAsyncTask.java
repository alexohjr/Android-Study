package com.example.pc.day35;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NKeyAsyncTask extends AsyncTask<Void, Void, String> {

    private Context context;

    // 생성자
    public NKeyAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        // 캡차 키 요청
        return getKey();
    }

    @Override
    protected void onPostExecute(String s) { // s : 발급 받은 키
        Ex01Activity.nKey = s;
        new ImageAsyncTask((MyCallback) context).execute();
    }

    private String getKey() {

        String clientId = MyCallback.CLIENT_ID;//애플리케이션 클라이언트 아이디값";
        String clientSecret = MyCallback.CLIENT_SECRET;//애플리케이션 클라이언트 시크릿값";
        try {
            String code = "0"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
            String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            Log.d("MY", response.toString());

            // response : { "key" : 키값 }
            JSONObject jsonObject = new JSONObject(response.toString());
            return jsonObject.getString("key");

        } catch (Exception e) {
            Log.d("MY", e.getMessage());
        }
        return null;
    }
}

