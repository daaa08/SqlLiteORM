package com.example.da08.sqlliteorm;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Da08 on 2017. 6. 9..
 */

public class BbsDao {

    DBhelper dBhelper;
    Dao<Bbs, Integer> dao;

    public BbsDao(Context context){
        // 1 database 연결
        dBhelper = DBhelper.getInstance(context);
        try {
            dao = dBhelper.getDao(Bbs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void create(Bbs bbs){
        try {
            dao.create(bbs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  List<Bbs> read(){
        List<Bbs> datas = null;

        try {
            datas = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public static void update(Bbs bbs){

    }

    public static void delete(Bbs bbs){

    }
}
