package com.example.pc.day27;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    private ArrayList<BookModel> list;
    private BookModel vo;
    private int resource;

    // 생성자
    public MyViewModelAdapter(Context context, int resource, ArrayList<BookModel> list, ListView listView) {
        // ArrayAdapter의 생성자 인자 : context, 아이템을 보여줄 레이아웃 id, 아이템리스트
        super(context, resource, list);
        this.list = list;
        this.resource = resource;
        this.context = context;
        listView.setOnItemClickListener(this);
    }

    // getView : List의 원소들을 View로 생성


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView != null) {
            return convertView;
        }

        // Inflater 객체 얻어옴
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // book_item.xml을 가지고 화면을 추가함
        convertView = inflater.inflate(resource, null);
        vo = list.get(position); // position 번째 원소 받기

        if(vo != null) {
            TextView title = convertView.findViewById(R.id.book_title);
            TextView author = convertView.findViewById(R.id.book_author);
            TextView price = convertView.findViewById(R.id.book_price);
            ImageView img = convertView.findViewById(R.id.book_img);

            title.setText("Title : " + vo.getB_title());
            author.setText("Author : " + vo.getB_author());
            price.setText("Price : " + vo.getB_price());

            // 이미지는 현재 이미지 경로만 가지고 있기 때문에
            // 해당 경로를 찾아가는 네트워크를 또 해야 함
            new ImageAsync(img, vo).execute();
        } // if
        return convertView;
    } // getView()

    class ImageAsync extends AsyncTask<String, String, Bitmap> {
        Bitmap bitmap;
        ImageView imageView;
        BookModel model;

        public ImageAsync(ImageView view, BookModel bm) {
            this.imageView = view;
            this.model = bm;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL img_url = new URL(model.getB_img());

                // 이미지 경로에서 로드 작업 진행
                BufferedInputStream bis = new BufferedInputStream(img_url.openStream());

                // bis에서 받은 데이터를 Bitmap으로 생성
                bitmap = BitmapFactory.decodeStream(bis);

                // 스트림 닫기
                bis.close();
            } catch(Exception e) {
                e.printStackTrace();
            }

            if(bitmap != null) {
                return bitmap;
            } else { // 책표지 없을 경우
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
                return bitmap;
            }
        } // doInBackground

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 아이템이 클릭되었을 때 상세보기 페이지 열기

        // 책의 고유 id값은 표지 이미지의 jpg 파일명과 같음
        String bookImg = list.get(position).getB_img();
        Log.d("MY", "책 표지 이미지 url : " + bookImg);

        // jpg 파일에서 책 id값을 추출
        String bookId = bookImg.substring(bookImg.lastIndexOf("/")+1, bookImg.lastIndexOf(".jpg"));
        Log.d("MY", " 추출한 책 id값 : " + bookId);

        // 추출한 책 id + 상세보기 페이지 url
        String url = "https://book.naver.com/bookdb/book_detail.nhn?bid="+bookId;

        // intent를 사용하여 웹 페이지 열기
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }
}
