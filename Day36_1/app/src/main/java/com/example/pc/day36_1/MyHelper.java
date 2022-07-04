package com.example.pc.day36_1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;

public class MyHelper extends SQLiteOpenHelper {
    /*

        < DB에 접근할 때 사용할 도우미 >
        1. SQLiteOpenHelper를 상속받는다.
        필수오버라이드 :
        onCreate() -> DB초기화할 때 호출
        생성자
        onUpgrade() -> DB버전이 변경되었을 경우 자동 실행

        2. 쿼리문을 메서드로 만든다. (DAO -> Data Access Object)

        < DB의 핵심 쿼리(Query) >
        - CRUD
        C : create
        R : read
        U : update
        D : delete
     */
    // 생성자


    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB 생성 메서드

    // 생성자의 name에 해당하는 DB파일이 생성될 때 실행
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE userscore (num INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, score INTEGER, time TEXT) "); // 쿼리 실행
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // DB 버전이 변경되었을 때 호출
    }

    public void insert(String name, int score){
        // 이름, 점수는 인자로 받고
        // 날짜(등록시간)는 여기서 생성
        String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
                .format(System.currentTimeMillis());

        // INSERT INTO userscore VALUES( null, '홍길동', 88, '2019-03-06 11:33:23' );
        String sql = "INSERT INTO userscore VALUES( null, '" + name + "', " + score + ", '" + time + "');";

        // DB를 쓰기모드로 실행
        SQLiteDatabase db = getWritableDatabase();

        // DB 쿼리 실행
        db.execSQL(sql);

        // DB 종료
        db.close();
    }

    // 레코드 삭제
    public void delete(int num){
        // DELETE FROM userscore; ===> userscore의 모든 레코드 삭제
        // DELETE FROM userscore WHERE num = 10; ===> num이 10번인 레코드 삭제
        String sql = "DELETE FROM userscore WHERE num = " + num + "; ";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }
    // 레코드 수정
    public void delete(int num, String newName, int newScore){
        // UPDATE userscore SET num = 10; ===> 모든 레코드의 num을 10으로 변경
        // UPDATE userscore SET num = 10 WHERE name = 홍길동;
        //          ===> name이 홍길동인 레코드의 num을 10으로 변경

        // 번호가 num인 레코드의 name을 newName으로, score를 newScore로 변경
        // UPDATE userscore SET name=[newName], score=[newScore] WHERE num = [num];
        String sql = "UPDATE userscore SET name= '" + newName +"', score = " + newScore + " "
                + "WHERE num = " + num + ";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }
    // 레코드 조회
    public String readAll(){

        // SELECT name FROM userscore; ===> 모든 레코드들의 name 조회
        // SELECT name, num FROM userscore; ===> 모든 레코드들의 name, num 조회
        // SELECT * FROM userscore ===> 모든 레코드들의 모든 항목(*) 조회
        // SELECT * FROM userscore WHERE score >= 50; ===> score가 50이상인 레코드의 모든 항목(*) 조회
        String sql = "SELECT * FROM userscore ORDER BY score DESC;";
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery(sql, null);
        int rank = 1;
        while(cursor.moveToNext()){
            result += rank++ + "위. "
                    + cursor.getString(1) + " 님 "
                    + cursor.getInt(2) + " 점 "
                    + "[" + cursor.getString(3) + "] "
                    + "(" + cursor.getInt(0) + "번)\n";
        }
        cursor.close();

        return result;
    }

}