package com.example.pc.day28;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class MyParser {
    private MainActivity activity;
    private MovieModel vo;
    private String myQuery; // 검색어

    // 생성자
    MyParser(MainActivity activity){
        this.activity = activity;
    }

    // 네이버에게 request 를 보내고,
    // 검색 결과를 파싱해서 이를 ArrayList로 return
    ArrayList<MovieModel> requestToNaver(int start, ArrayList<MovieModel> list){
        try {
            int count = 3; // 10건씩 꺼내기
            myQuery = activity.keyword; // 검색 키워드 받기
            if(myQuery != null && !myQuery.isEmpty()){ // myQuery에 키워드가 있을 경우
                myQuery = URLEncoder.encode(myQuery, "UTF-8");
                // myQuery (검색어)를 UTF-8로 인코딩
                // (네이버에서 UTF-8을 사용하기 때문에 그것에 맞춤)
            }

            // Naver에게 보낼 요청 URL 준비
            String urlStr = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=546e9a5c442932446806ae6eb1d7db23&targetDt="+myQuery;

            // string -> url 형식으로 변경
            URL url = new URL(urlStr);

            // 연결 준비
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET"); // 요청방식 get으로 설정

            // 받아올 응답 데이터의 형태 -> xml형태다!
            connection.setRequestProperty("Content-Type", "application/xml");

            // 위의 url의 실행 결과(응답메시지,xml)를 파싱할 우리의 도우미 객체
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();

            // Parser를 사용하여 결과를 파싱한다.
            parser.setInput(connection.getInputStream(), null);

            // Parser를 통해서 각 요소를 읽어들일 준비
            int parserEvent = parser.getEventType();
            while(parserEvent != XmlPullParser.END_DOCUMENT){ // 문서의 끝에 다다를까지 계속 반복
                if(parserEvent == XmlPullParser.START_TAG){
                    String tagName = parser.getName();
                    switch (tagName){
                        case "rnum":
                            vo = new MovieModel();
                            String rnum = parser.nextText();
                            vo.setRnum(rnum);
                            break;
                        case "movieNm":
                            String movieNm = parser.nextText();
                            vo.setMovieNm(movieNm);
                            break;
                        case "openDt":
                            String openDt = parser.nextText();
                            vo.setOpenDt(openDt);
                            break;
                        case "audiAcc":
                            String audiAcc = parser.nextText();
                            vo.setAudiAcc(audiAcc);
                            list.add(vo);
                    } // switch
                } // if
                parserEvent = parser.next(); // 다음 요소로 넘어가기
            } // while
            Log.d("MY", "파싱 완료");
        } catch(Exception e){
            Log.d("MY", "커넥션 중 예외 발생! " + e.getMessage());
        } // try-catch

        return list;
    } // requestToNaver()
}

