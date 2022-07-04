package com.example.pc.day28;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

public class MyViewModelAdapter extends ArrayAdapter implements AdapterView.OnItemClickListener {
    private Context context;
    private ArrayList<MovieModel> list;
    private MovieModel vo;
    private int resource;

    // 생성자
    public MyViewModelAdapter(Context context, int resource, ArrayList<MovieModel> list, ListView listView){
        super(context, resource, list); // ArrayAdapter의 생성자 인자 : context, 아이템을 보여줄 레이아웃 id, 아이템 arraylist
        this.list = list;
        this.resource = resource;
        this.context = context;
        listView.setOnItemClickListener(this);
    }

    // getView : List의 원소들을 View로 생성
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView != null){
            return convertView;
        }

        // Inflater 객체 얻어옴
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(resource, null); // book_item.xml을 가지고 화면을 추가함
        vo = list.get(position); // position 번째 원소 받기
        if(position < 5) {
            if(vo != null){
                TextView rnum = convertView.findViewById(R.id.rnum);
                TextView movieNm = convertView.findViewById(R.id.movieNm);
                TextView openDt = convertView.findViewById(R.id.openDt);
                TextView audiAcc = convertView.findViewById(R.id.audiAcc);

                rnum.setText(vo.getRnum());
                movieNm.setText("제목 : " + vo.getMovieNm());
                openDt.setText("개봉일 : " + vo.getOpenDt());
                audiAcc.setText("누적관객 수 : " + vo.getAudiAcc());

            } // if
        }


        return convertView;
    } // getView()

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 아이템이 클릭되었을 때 상세보기 페이지 열기

        // 책의 고유 id 값은 표지 이미지의 jpg 파일명과 같음
        String movieNm = list.get(position).getMovieNm();
        Log.d("MY", "책 표지 이미지 url : " + movieNm);

        // 추출한 책 id + 상세보기 페이지 url
        String url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query="+movieNm;

        // intent를 사용하여 웹 페이지 열기
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }
}
