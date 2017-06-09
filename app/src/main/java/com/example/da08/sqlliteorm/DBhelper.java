package com.example.da08.sqlliteorm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Da08 on 2017. 6. 9..
 */

// database 에 접근할 수 있는 환경을 만들어 준 것
public class DBhelper extends OrmLiteSqliteOpenHelper{

    public static final String DATABASE_NAME = "database.db";
    public static final int DATABASE_VERSION = 2;  // 앱 업그레이드 시 버전 바꿔줘야 함

    private static DBhelper instance = null;

    // DBhelper 를 메모리에 하나만 있게해서 효율을 높여보자
    public static DBhelper getInstance(Context context){
        if(instance == null) {
            instance = new DBhelper(context);
        }
        return instance;
    }


    // 최초 호출될때 database.db 파일을 /data/data/패키지명/database/database.db/ 디렉토리 아래에 생성해줌
    private DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
    최초에 생성되면 버전체크를해서 onCreate를 호출 (처음 설치하는 사람은 업그레이드와 상관없이 onCreate가 호출됨
    떄문에 함수를 추가하여 onUpgrade 를 불러오거나 테이블 이력을 남겨줌
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        // 여기서 테이블 생성해야 한다  - 쿼리를 날리지 않아도 생성이 됨
        try {
            TableUtils.createTable(connectionSource, Memo.class);
            TableUtils.createTable(connectionSource, Bbs.class);  // 이력 추가
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 앱 출시 후 업그레이드가 되었을때 버전을 바꿔야하는데 바꿔주면 onUpgrade가 호출 됨 (이미 설치했던 사람이라는 과정하에)
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            /*
                Memo 테이블의 특정 Column 만 변경되었을 경우 (ex 이름 필드가 추가된다던지..)

                1 기존 테이블을 백업하기 위한 임시테이블을 생성하고 데이터를 담아둔다
                    ex> create table temp_name; -> 기존 데이터
                2 Memo 테이블을 삭제하고 다시 생성한다
                3 백업된 데이터를 다시 입력한다
                4 임시 데이터 삭제
             */
            TableUtils.createTable(connectionSource, Bbs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create - 데이터를 입력해주는 역할
    public void create(Memo memo){

        try {
            // 1 테이블에 연결
            Dao<Memo, Integer> dao = getDao(Memo.class);  // data access object = dao
            // 2 데이터를 입력
            dao.create(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    // Read - 전체 데이터를 읽음
    public List<Memo> readAll(){

        List<Memo> datas = null;
        try {
            // 1 테이블에 연결
            Dao<Memo, Integer> dao = getDao(Memo.class);  // data access object = dao
            // 2.1 데이터를 입력
            datas = dao.queryForAll(); // 전체 데이터 받음
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    // 하나만 읽어야할 경우
    public Memo read(int memoid){
        Memo memo = null;

        try {
            // 1 테이블에 연결
            Dao<Memo, Integer> dao = getDao(Memo.class);  // data access object = dao
            // 2.1 데이터를 입력
            memo = dao.queryForId(memoid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memo;

    }

    // Search - 데이터 검색하기
    public List<Memo> search(String word){
        List<Memo> datas = null;
        try {
            // 1 테이블에 연결
            Dao<Memo, Integer> dao = getDao(Memo.class);  // data access object = dao
            // 2.1 데이터를 검색하기
            String query = "select * from memo where content like '%"+word+"%'";
            GenericRawResults<Memo> temps = dao.queryRaw(query, dao.getRawRowMapper());
            datas = temps.getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;

    }

    // Update
    public void Update(Memo memo){

        try {
            // 1 테이블에 연결
            Dao<Memo, Integer> dao = getDao(Memo.class);
            // 2 데이터를 수정
            dao.update(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Delete Object
    public void delete(Memo memo){

        try {
            // 1 테이블에 연결
            Dao<Memo, Integer> dao = getDao(Memo.class);
            // 2 데이터를 삭제
            dao.delete(memo);
            // *참고 : 아이디 삭제
//            dao.deleteById(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Delete By Id
    public void delete(int id){

        try {
            // 1 테이블에 연결
            Dao<Memo, Integer> dao = getDao(Memo.class);
            // 2 데이터를 삭제
            dao.deleteById(id);
            // *참고 : 아이디 삭제
//            dao.deleteById(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
