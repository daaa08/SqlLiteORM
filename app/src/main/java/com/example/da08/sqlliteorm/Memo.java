package com.example.da08.sqlliteorm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Da08 on 2017. 6. 9..
 */
@DatabaseTable(tableName = "Memo")  // database의 Memo라는 이름으로 table사용 (이름은 꼭 설정 안해줘도 됨)
public class Memo {

    // table 속성 정의
    @DatabaseField (generatedId = true)  // database의 column 으로 쓰는거는 @DatabaseField 를 붙여줘야 함 , (generatedId = true)는 자동id 생성
    private int id;
    @DatabaseField
    private String title;
    @DatabaseField
    private String content;
    @DatabaseField
    private Date date;

    public Memo(){
        // ormlite는 기본 생성자가 없으면 동작하지 않음
        setDate();
    }

    public Memo(String title, String content){ // 오버라이드
        this.title = title;
        this.content = content;
        setDate();
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    private void setDate() {
        Date date = new Date(System.currentTimeMillis());
                this.date = date;
    }


}
