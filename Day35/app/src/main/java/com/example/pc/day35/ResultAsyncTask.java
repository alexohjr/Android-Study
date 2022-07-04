package com.example.pc.day35;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResultAsyncTask extends AsyncTask<String, Void, Boolean> {

    private MyCallback caller;

    ResultAsyncTask(MyCallback caller) {
        this.caller = caller;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        return getResult(strings[0]);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        caller.setCaptchaResult(aBoolean);
    }

    private boolean getResult(String input) {
        String clientId = MyCallback.CLIENT_ID;//애플리케이션 클라이언트 아이디값";
        String clientSecret = MyCallback.CLIENT_SECRET;//애플리케이션 클라이언트 시크릿값";
        try {
            String code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
            String key = Ex01Activity.nKey; // 캡차 키 발급시 받은 키값
            String value = input; // 사용자가 입력한 캡차 이미지 글자값
            String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code +"&key="+ key + "&value="+ value;

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
            JSONObject jsonObject = new JSONObject(response.toString());
            return jsonObject.getBoolean("result");
        } catch (Exception e) {
            Log.d("MY", e.getMessage());
        }
        return false;
    }
}
