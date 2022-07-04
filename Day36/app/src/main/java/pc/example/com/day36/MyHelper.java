package pc.example.com.day36;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;

public class MyHelper extends SQLiteOpenHelper {
    /*
        < DB에 접근할 때 사용할 도우미 >
        1. SQLiteOpenHelper를 상속받는다
            필수오버라이드 : onCreate() : DB를 초기화할 때 호출됨
                            생성자
                            onUpgrade -> DB 버전이 변경되었을 경우 자동 실행
        2. 쿼리문을 메소드로 만든다. (DAO -> Data Access Object)

        < DB의 핵심 쿼리(Query) >
        - CRUD
            c : create
            r : read
            u : update
            d : delete
     */

    // 생성자
    public MyHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB 생성 메서드
    @Override
    public void onCreate(SQLiteDatabase db) { // 생성자의 name에 해당하는 DB파일이 생성될 때 실행
        // < Table 생성 >
        // 이름 : userscore
        // 필드 : num(번호), name(사용자이름), score(점수)
        db.execSQL("CREATE TABLE userscore  (num INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, score INTEGER, time TEXT)"); // 쿼리 실행
        /*
        < 제약조건 >
        PRIMARY KEY : 레코드의 고유번호(식별번호)
                      -> 중복 안됨, 누락 안됨
        AUTO INCREMENT : 자동으로 인덱스 부여

         */
    }

    // UpGrade()

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // DB 버전이 변경되었을 때 호출됨
    }

    // CRUD
    // 레코드 추가
    public void insert(String name, int score) {
        // 이름, 점수는 인자로 받고
        // 날짜(등록시간)는 여기서 생성
        String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(System.currentTimeMillis());

        String sql = "INSERT INTO userscore VALUES(null, '" + name + "', " + score + ", '" + time + "');";

        // DB를 쓰기모드로 실행
        SQLiteDatabase db = getWritableDatabase();

        // DB 쿼리 실행
        db.execSQL(sql);

        // DB 종료
        db.close();
    }
    // 레코드 삭제
    public void delete(int num) {
        String sql = "DELETE FROM userscore WHERE num = " + num + ";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    // 레코드 수정
    public void update(int num, String newName, int newScore) {
        String sql = "UPDATE userscore SET name = '" + newName + "' , score = " + newScore + "WHERE num = " + num + ";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    // 레코드 조회
    public String readAll() {
        String sql = "SELECT * FROM userscore ORDER BY score desc;";
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery(sql , null);
        int rank = 1;
        while(cursor.moveToNext()) {
            result += rank++ + "위."
                    + cursor.getString(1) + "님 "
                    + cursor.getInt(2) + "점 "
                    + "[" + cursor.getString(3) +"] "
                    + "(" + cursor.getInt(0) + "번)\n";

        }
        cursor.close();

        return result;
    }
}













