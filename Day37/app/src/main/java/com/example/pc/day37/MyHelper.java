package com.example.pc.day37;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE money_table   " +
                        "(num INTEGER PRIMARY KEY AUTOINCREMENT," + // 고유번호
                        "type INTEGER," + // 수입(0) OR 지출(1)
                        "category TEXT," + // 월급/이자/식료품/교육비
                        "name TEXT," + // 아이템이름(9월월급/점심값) 사용자가 직접 입력
                        "cost INTEGER," + // 비용
                        "year INTEGER," +
                        "month INTEGER," +
                        "date INTEGER," + // 일
                        "time TEXT)" // 작성시간(현재시간)
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // DB 버전이 변경되었을 때 호출됨
    }

    public void insert(int type, String category, String name, int cost, int year, int month, int date) {
        // 이름, 점수는 인자로 받고
        // 날짜(등록시간)는 여기서 생성
        String time = new SimpleDateFormat("YYYY-MM-dd").format(System.currentTimeMillis());

        //String sql = "INSERT INTO money_table VALUES(null, '" + type + "', " + score + ", '" + time + "');";

        String sql = "INSERT INTO money_table VALUES(null, "+type+", '"+category+"', '"+name+"', "+cost+", "+year+", "+month+", "+date+", '"+time+"');";

        // DB를 쓰기모드로 실행
        SQLiteDatabase db = getWritableDatabase();

        // DB 쿼리 실행
        db.execSQL(sql);

        // DB 종료
        db.close();
    }

    public ArrayList<DTO> readAll() {
        String sql = "SELECT * FROM money_table;";

        SQLiteDatabase db = getReadableDatabase();

        ArrayList<DTO> arrayList = new ArrayList<>();


        Cursor cursor = db.rawQuery(sql , null);

        if(cursor.moveToFirst()) {
            do {
                DTO dto = new DTO();
                dto.setNum(cursor.getInt(0));
                dto.setType(cursor.getInt(1));
                dto.setCategory(cursor.getString(2));
                dto.setName(cursor.getString(3));
                dto.setCost(cursor.getInt(4));
                dto.setYear(cursor.getInt(5));
                dto.setMonth(cursor.getInt(6));
                dto.setDate(cursor.getInt(7));
                dto.setTime(cursor.getString(8));
                arrayList.add(dto);
            } while(cursor.moveToNext());
        }

        cursor.close();
        return arrayList;
    }

    public void deleteAll() {
        String sql = "delete from money_table";

        // DB를 쓰기모드로 실행
        SQLiteDatabase db = getWritableDatabase();

        // DB 쿼리 실행
        db.execSQL(sql);

        // DB 종료
        db.close();
    }
}
