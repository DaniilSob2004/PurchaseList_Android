package com.example.purchaselist.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.purchaselist.models.MyList;
import com.example.purchaselist.models.Product;
import com.example.purchaselist.models.Type;
import com.example.purchaselist.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;  // БД, предоставляет методы для выполнения SQL-запросов

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }


    public DatabaseManager open() {
        database = dbHelper.getWritableDatabase();  // открывает соединение с БД для записи
        return this;  // для вызова по цепочке
    }

    public void close() {
        dbHelper.close();
    }


    public List<Type> getTypes() {
        List<Type> types = new ArrayList<>();
        Cursor cursor = getAllEntries(AppConstant.TYPE_TABLE, AppConstant.TYPE_COLUMNS);

        while (cursor.moveToNext()) {
            @SuppressLint("Range")  // указывает компилятору игнорировать предупреждение о доступе к индексу столбца через строковое значение
            int id = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_TYPE_ID));

            @SuppressLint("Range")
            String label = cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_TYPE_LABEL));

            @SuppressLint("Range")
            String rule = cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_TYPE_RULE));

            types.add(new Type(id, label, rule));
        }

        return types;
    }

    public List<MyList> getLists() {
        List<MyList> lists = new ArrayList<>();
        Cursor cursor = getAllEntries(AppConstant.LIST_TABLE, AppConstant.LIST_COLUMNS);

        while (cursor.moveToNext()) {
            @SuppressLint("Range")  // указывает компилятору игнорировать предупреждение о доступе к индексу столбца через строковое значение
            int id = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_LIST_ID));

            @SuppressLint("Range")
            String name = cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_LIST_NAME));

            @SuppressLint("Range")
            int date = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_LIST_DATA));

            @SuppressLint("Range")
            String description = cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_LIST_DESCRIPTION));

            lists.add(new MyList(id, name, date, description));
        }

        return lists;
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Cursor cursor = getAllEntries(AppConstant.PRODUCT_TABLE, AppConstant.PRODUCT_COLUMNS);

        while (cursor.moveToNext()) {
            @SuppressLint("Range")  // указывает компилятору игнорировать предупреждение о доступе к индексу столбца через строковое значение
            int id = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_PRODUCT_ID));

            @SuppressLint("Range")
            String name = cursor.getString(cursor.getColumnIndex(AppConstant.COLUMN_PRODUCT_NAME));

            @SuppressLint("Range")
            float count = cursor.getFloat(cursor.getColumnIndex(AppConstant.COLUMN_PRODUCT_COUNT));

            @SuppressLint("Range")
            int idList = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_PRODUCT_ID_LIST));

            @SuppressLint("Range")
            int checked = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_PRODUCT_CHECKED));

            @SuppressLint("Range")
            int idType = cursor.getInt(cursor.getColumnIndex(AppConstant.COLUMN_PRODUCT_ID_TYPE));

            products.add(new Product(id, name, count, idList, checked, idType));
        }

        return products;
    }

    public List<Product> getProductsByListId(int id) {
        List<Product> products = getProducts();
        return products
                .stream()
                .filter(p -> p.getIdList() == id)
                .collect(Collectors.toList());
    }

    public List<Product> getProductsCheckedByListId(int id) {
        List<Product> products = getProductsByListId(id);
        return products
                .stream()
                .filter(p -> p.getChecked() == 1)
                .collect(Collectors.toList());
    }


    public long addList(MyList myList) {
        ContentValues cv = new ContentValues();
        cv.put(AppConstant.COLUMN_LIST_NAME, myList.getName());
        cv.put(AppConstant.COLUMN_LIST_DATA, myList.getDate());
        cv.put(AppConstant.COLUMN_LIST_DESCRIPTION, myList.getDescription());

        return database.insert(AppConstant.LIST_TABLE, null, cv);
    }

    public long deleteList(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = new String[]{ String.valueOf(id) };
        return database.delete(AppConstant.LIST_TABLE, whereClause, whereArgs);
    }


    private Cursor getAllEntries(String tableName, String[] columns) {
        return database.query(
                tableName,
                columns,
                null,
                null,
                null,
                null,
                null);
    }
}

/*
public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        Cursor cursor = getAllEntries();

        while (cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(MyConstants.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(MyConstants.COLUMN_NAME));
            int year = cursor.getInt(cursor.getColumnIndex(MyConstants.COLUMN_YEAR));
            users.add(new User(id, name, year));
        }
        cursor.close();
        return  users;
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {
                MyConstants.COLUMN_ID,
                MyConstants.COLUMN_NAME,
                MyConstants.COLUMN_YEAR};
        return  database.query(
                MyConstants.TABLE,
                columns,
                null,
                null,
                null,
                null,
                null);
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, MyConstants.TABLE);
    }

    public User getUser(long id){
        User user = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",MyConstants.TABLE, MyConstants.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            String name = cursor.getString(1);
            int year = cursor.getInt(cursor.getColumnIndex(MyConstants.COLUMN_YEAR));
            user = new User(id, name, year);
        }
        cursor.close();
        return  user;
    }

    public long insert(User user){
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.COLUMN_NAME, user.getName());
        cv.put(MyConstants.COLUMN_YEAR, user.getYear());

        return  database.insert(MyConstants.TABLE, null, cv);
    }

    public long delete(long userId){
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return database.delete(MyConstants.TABLE, whereClause, whereArgs);
    }

    public long update(User user){
        String whereClause = MyConstants.COLUMN_ID + "=" + user.getId();
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.COLUMN_NAME, user.getName());
        cv.put(MyConstants.COLUMN_YEAR, user.getYear());
        return database.update(MyConstants.TABLE, cv, whereClause, null);
    }
* */
