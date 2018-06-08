package kr.or.dgit.it.review_application.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import kr.or.dgit.it.review_application.database.PhoneDbHelper;
import kr.or.dgit.it.review_application.dto.PhoneInfo;

public class PhoneDbDao {
    static final String TABLE_NAME = "tb_data";
    //컬럼 이름
    static final String COL_ID = "_id";
    static final String COL_NAME = "name";
    static final String COL_DATE = "date";
    //selection
    static final String[] COLUMNS =  new String[]{COL_ID, COL_NAME, COL_DATE};

    private PhoneDbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mCtx;

    public PhoneDbDao(Context ctx) {
        mCtx = ctx;
    }
    public void open() throws SQLException {
        mDbHelper = PhoneDbHelper.getInstance(mCtx);
        mDb = mDbHelper.getWritableDatabase();
    }

    public void close(){
        if (mDbHelper != null){
            mDbHelper.close();
        }
    }
    public void insertItem(PhoneInfo item){
        ContentValues row = new ContentValues();
        row.put(COL_NAME, item.getName());
        row.put(COL_DATE, item.getDate());
        mDb.insert(TABLE_NAME, null, row);
    }

    public void deleteItemById(int id){
        mDb.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)});
    }

    public void updateItem(PhoneInfo item){
        ContentValues row = new ContentValues();
        row.put(COL_DATE, item.getDate());
        row.put(COL_NAME, item.getName());
        mDb.update(TABLE_NAME, row, COL_ID +"=?", new String[]{String.valueOf(item.getId())});
    }

    public Cursor selectItemAll(String selection, String[] selectionArgs){

        Cursor mCursor = mDb.query(TABLE_NAME, COLUMNS, selection, selectionArgs,null,null,null);
        return mCursor;
    }
}
