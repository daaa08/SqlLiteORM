package com.example.da08.sqlliteorm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Da08 on 2017. 6. 9..
 */

@DatabaseTable(tableName = "Bbs")
public class Bbs {

    @DatabaseField
    private int id;
}
