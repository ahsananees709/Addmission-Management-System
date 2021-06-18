package com.example.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class applicant_DBHelper extends SQLiteOpenHelper {
//These are Strings table name column names that are used in table
    private Context context;
    private static final String DATABASE_NAME = "ApplicantsData.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "applicants_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "applicant_name";
    private static final String COLUMN_CNIC = "applicant_cnic";
    private static final String COLUMN_EMAIL = "applicant_email";
    private static final String COLUMN_PHONE = "applicant_phone";


    applicant_DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Query for creating Schema
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_CNIC + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PHONE + " TEXT) ; ";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addApplicant(String name, String cnic, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
//Putting data in Content Values object for inserting in data base
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_CNIC,cnic);
        cv.put(COLUMN_EMAIL,email);
        cv.put(COLUMN_PHONE,phone);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1)
        {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context,"Added Successfully!",Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
// Curse can hold our whole table data
        Cursor cursor = null;
        if(db != null){
           cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    void updateData(String row_id, String name, String cnic, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_CNIC,cnic);
        cv.put(COLUMN_EMAIL,email);
        cv.put(COLUMN_PHONE,phone);
//Update the data whose id comes fron Details Activity
        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        //Delete the data whose id comes fron Details Activity
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

}
