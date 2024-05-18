package com.example.sqliteapplication.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqliteapplication.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private final String DATABASE_NAME = "ChiTieu.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE items(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, category TEXT, price TEXT, date TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    //get all order by date desc
    public List<Item> getAll() {
        List<Item> result = new ArrayList<Item>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "date DESC";
        Cursor resultSet = st.query("items", null, null, null, null, null, order);
        while (resultSet != null && resultSet.moveToNext()) {
            int id = resultSet.getInt(0);
            String title = resultSet.getString(1);
            String category = resultSet.getString(2);
            String price = resultSet.getString(3);
            String date = resultSet.getString(4);
            result.add(new Item(id, title, category, price, date));
        }
        return result;
    }

    public long addItem(Item i) {
        ContentValues values = new ContentValues();
        values.put("title", i.getTitle());
        values.put("category", i.getCategory());
        values.put("price", i.getPrice());
        values.put("date", i.getDate());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("items", null, values);
    }

    public List<Item> getByDate(String date) {
        List<Item> list = new ArrayList<>();
        String whereClause = "date like ?";
        String[] whereArgs = {date};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null, whereClause, whereArgs, null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String category = rs.getString(2);
            String price = rs.getString(3);
            list.add(new Item(id, title, category, price, date));
        }
        return list;
    }

    public int update(Item i) {
        ContentValues values = new ContentValues();
        values.put("title", i.getTitle());
        values.put("category", i.getCategory());
        values.put("price", i.getPrice());
        values.put("date", i.getDate());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("items", values, whereClause, whereArgs);
    }

    public int delete(int id) {
        String whereClause = "id=?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("items", whereClause, whereArgs);
    }

    public List<Item> searchByTitle(String key) {
        List<Item> list = new ArrayList<>();
        String whereClause = "title like ?";
        String[] whereArgs = {"%" + key + "%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null, whereClause, whereArgs, null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String category = rs.getString(2);
            String price = rs.getString(3);
            String date = rs.getString(4);
            list.add(new Item(id, title, category, price, date));
        }
        return list;
    }

    public List<Item> searchByCategory(String cate) {
        List<Item> list = new ArrayList<>();
        String whereClause = "category like ?";
        String[] whereArgs = {cate};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null, whereClause, whereArgs, null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String category = rs.getString(2);
            String price = rs.getString(3);
            String date = rs.getString(4);
            list.add(new Item(id, title, category, price, date));
        }
        return list;
    }

    public List<Item> searchByDateFromTo(String from, String to) {
        List<Item> list = new ArrayList<>();
        String whereClause = "date BETWEEN ? AND ?";
        String[] whereArgs = {from.trim(), to.trim()};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null, whereClause, whereArgs, null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String category = rs.getString(2);
            String price = rs.getString(3);
            String date = rs.getString(4);
            list.add(new Item(id, title, category, price, date));
        }
        return list;
    }

}
