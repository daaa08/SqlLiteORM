package com.example.da08.sqlliteorm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBhelper dBhelper = DBhelper.getInstance(this);

//        // 1 데이터 생성
//        for(int i = 0; i <10; i++) {
//            Memo memo = new Memo();
//            memo.setTitle("Title");
//            memo.setContent("Content");
//            dBhelper.create(memo);
//        }
//
//        // 2 데이터 한개 읽어오기
//        Memo one = dBhelper.read(3);
//        Log.i("memo",one.getId()+"title"+one.getTitle()+",content"+one.getContent());

//        // 3 데이터 전체 읽어오기
//        List<Memo> datas = dBhelper.readAll();
//        for(Memo one : datas){
//            Log.i("memo",one.getId()+"title"+one.getTitle()+",content"+one.getContent());
//        }

//        // 4 데이터 검색하기
//        // 기존 데이터 넣기
//        dBhelper.create(new Memo("titile1", "content1"));
//        dBhelper.create(new Memo("titile2", "content2"));
//        dBhelper.create(new Memo("titile3", "content3"));
//        dBhelper.create(new Memo("titile4", "content4"));
//
//        // 검색하기
//        List<Memo> datas = dBhelper.search("content3");
//        for(Memo one : datas) {
//            Log.i("memo", one.getId() + "title" + one.getTitle() + ",content" + one.getContent());
//        }

        // 5 수정하기
//        Memo memo = dBhelper.read(3);
//        memo.setContent("title");
//        dBhelper.Update(memo);

        // 6 삭제하기
        dBhelper.delete(3);

        //
        BbsDao bbsDao = BbsDao.getInstance(getBaseContext());
        bbsDao.create(new Bbs());
    }
}
