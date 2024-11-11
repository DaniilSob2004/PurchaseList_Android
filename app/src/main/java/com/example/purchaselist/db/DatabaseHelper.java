package com.example.purchaselist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.purchaselist.utils.AppConstant;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, AppConstant.DATABASE_NAME, null, AppConstant.SCHEMA);
    }

    // вызывается один раз, когда БД создается впервые
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AppConstant.CREATE_TABLE_TYPE);
        db.execSQL(AppConstant.CREATE_TABLE_LIST);
        db.execSQL(AppConstant.CREATE_TABLE_PRODUCT);
        db.execSQL(AppConstant.INSERT_TYPE_TABLE);
        db.execSQL(AppConstant.INSERT_LIST_TABLE);
        db.execSQL(AppConstant.INSERT_PRODUCT_TABLE);
    }

    // вызывается при изменении версии БД, что обычно происходит при добавлении или удалении таблиц, изменении структуры полей и тд
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(AppConstant.DROP_PRODUCT);
        db.execSQL(AppConstant.DROP_LIST);
        db.execSQL(AppConstant.DROP_TYPE);
        onCreate(db);
    }
}
