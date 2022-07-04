package com.example.pc.day27;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MyParser {
    private MainActivity activity;
    private BookModel vo;
    private String myQuery; // 검색어

    // 생성자
    MyParser(MainActivity activity){
        this.activity = activity;
    }

    // 네이버에게 request 를 보내고,
    // 검색 결과를 파싱해서 이를 ArrayList로 return
    ArrayList<BookModel> requestToNaver(int start, ArrayList<BookModel> list){
        try {
            int count = 10; // 10건씩 꺼내기
            myQuery = activity.keyword; // 검색 키워드 받기
            if(myQuery != null && !myQuery.isEmpty()){ // myQuery에 키워드가 있을 경우
                myQuery = URLEncoder.encode(myQuery, "UTF-8");
                // myQuery (검색어)를 UTF-8로 인코딩
                // (네이버에서 UTF-8을 사용하기 때문에 그것에 맞춤)
            }

            // Naver에게 보낼 요청 URL 준비
            String urlStr = "https://openapi.naver.com/v1/search/book.xml?query="+myQuery + "&start="+start + "&display="+count+"&sort=count";
            // query : 검색어
            // start : 몇 번 째 부터
            // display : 몇 개
            // sort : 정렬 기준 ( count : 판매량 )

            // string -> url 형식으로 변경
            URL url = new URL(urlStr);

            // 연결 준비
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET"); // 요청방식 get으로 설정
            // GET, POST 방식 참고해보기

            /*
                Client ID : djoJH_LpsX0V6ROUXFM2
                Client Secret : J1G07lzyan
             */

            // 요청 메시지에 Property 추가 ( request 메시지의 header에 작성되는 전달 데이터 )
            connection.setRequestProperty("X-Naver-client-Id", "djoJH_LpsX0V6ROUXFM2");
            connection.setRequestProperty("X-Naver-client-Secret", "J1G07lzyan");

            // 받아올 응답 데이터의 형태 -> xml형태다!
            connection.setRequestProperty("Content-Type", "application/xml");

            // 위의 url의 실행 결과(응답메시지,xml)를 파싱할 우리의 도우미 객체
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();

            // Parser를 사용하여 결과를 파싱
            parser.setInput(connection.getInputStream(), null);

            // Parser를 통해서 각 요소를 읽어들이 준비
            int parserEvent = parser.getEventType();
            while(parserEvent != XmlPullParser.END_DOCUMENT){ // 문서의 끝에 다다를까지 계속 반복
                if(parserEvent == XmlPullParser.START_TAG){
                    String tagName = parser.getName();
                    switch (tagName){
                        case "title":
                            vo = new BookModel();
                            String title = parser.nextText();
                            // title 의 검색 키워드 부분에는 <b> 태그가 있음.
                            // 정규식을 사용하여 이를 지우기

                            /*Pattern pattern = Pattern.compile("</?[b]>");
                            // ? : 앞 글자는 없어도 된다.
                            // [] : 특정 글자, 숫자
                            // 예 : "[0-9]" : "12341234" (O)
                            //                "a12341234" (X)
                            // 예 : "[ㄱ-힣a-Z]" : 한글과 영어만
                            Matcher matcher = pattern.matcher(title);
                            if(matcher.find()){ // <b> 나 </b> 가 title에 있으면
                                title = matcher.replaceAll(""); // 그 부분을 "" 로 대체
                            }*/

                            title = title.replaceAll("</?[b]>", "");
                            vo.setB_title(title);
                            break;
                        case "image":
                            String img = parser.nextText();
                            vo.setB_img(img);
                            break;
                        case "author":
                            String author = parser.nextText();
                            vo.setB_author(author);
                            break;
                        case "price":
                            String price = parser.nextText();
                            vo.setB_price(price);
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

