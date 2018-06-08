package kr.or.dgit.it.review_application.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PhoneDbHelper extends SQLiteOpenHelper {

    private static final String TAG = PhoneDbHelper.class.getSimpleName();
    private static final int VERSION = 1;
    private static final String DB_NAME = "phonebook.db";
    private final Context context;
    private static PhoneDbHelper instance;

    public synchronized static PhoneDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new PhoneDbHelper(context);
        }
        return instance;
    }

    private PhoneDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        applySqlFile(db, VERSION, "phonebook.sql");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == VERSION){
            db.execSQL("drop table tb_data");
            onCreate(db);
        }
    }

    private void applySqlFile(SQLiteDatabase db, int version, String fileName) {
        String filename = fileName;

        try (final InputStream inputStream = context.getAssets().open(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));) {
            final StringBuilder statement = new StringBuilder();

            for (String line; (line = reader.readLine()) != null; ) {
                if (!TextUtils.isEmpty(line) && !line.startsWith("--")) {
                    statement.append(line.trim());
                }
                if (line.endsWith(";")) {
                    db.execSQL(statement.toString());
                    statement.setLength(0);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Could not apply SQL file -> " + e);
        }
    }
}
