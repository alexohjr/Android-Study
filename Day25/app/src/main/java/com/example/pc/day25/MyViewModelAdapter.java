package com.example.pc.day25;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

public class MyViewModelAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<BookModel> list;
    private BookModel vo;
    private int resource;

    // 생성자
    public MyViewModelAdapter(Context context, int resource, ArrayList<BookModel> list){
        super(context, resource, list); // ArrayAdapter의 생성자 인자 : context, 아이템을 보여줄 레이아웃 id, 아이템 arraylist
        this.list = list;
        this.resource = resource;
        this.context = context;
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

        if(vo != null){
            TextView title = convertView.findViewById(R.id.book_title);
            TextView author = convertView.findViewById(R.id.book_author);
            TextView price = convertView.findViewById(R.id.book_price);
            ImageView img = convertView.findViewById(R.id.book_img);

            title.setText("Title : " + vo.getB_title());
            author.setText("Author : " + vo.getB_author());
            price.setText("Price : " + vo.getB_price() + "원");

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

        public ImageAsync(ImageView view, BookModel bm){
            this.imageView = view;
            this.model = bm;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL img_url = new URL(model.getB_img());

                // 이미지 경로에서 로드 작업 진행
                BufferedInputStream bis = new BufferedInputStream(img_url.openStream());

                // bis 에서 받은 데이터를 Bitmap으로 생성
                bitmap = BitmapFactory.decodeStream(bis);

                // 스트림 닫기
                bis.close();

            } catch(Exception e){
                e.printStackTrace();
            }

            if(bitmap != null){
                return bitmap;
            } else { // 책 표지 이미지를 못 받았을 경우
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
                return bitmap;
            }
        } // doInBackground

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
