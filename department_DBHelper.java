package com.example.assignment3;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class department_DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "DepartmentData.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "department_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "applicant_name";
    private static final String COLUMN_CNIC = "applicant_cnic";
    private static final String COLUMN_MATRIC = "applicant_matric";
    private static final String COLUMN_INTER = "applicant_inter";
    private static final String COLUMN_DEGREE = "applicant_degree";
    private static final String COLUMN_DEPARTMENT = "applicant_department";


    department_DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_CNIC + " TEXT, " +
                COLUMN_MATRIC + " INTEGER, " +
                COLUMN_INTER + " INTEGER, " +
                COLUMN_DEGREE + " TEXT, " +
                COLUMN_DEPARTMENT + " TEXT) ; ";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addDepartment(String name, String cnic, String matric, String inter, String degree, String department){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_CNIC,cnic);
        cv.put(COLUMN_MATRIC,matric);
        cv.put(COLUMN_INTER,inter);
        cv.put(COLUMN_DEGREE,degree);
        cv.put(COLUMN_DEPARTMENT,department);
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

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    void updateData(String row_id, String name, String cnic, String matric, String inter, String degree, String department){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_CNIC,cnic);
        cv.put(COLUMN_MATRIC,matric);
        cv.put(COLUMN_INTER,inter);
        cv.put(COLUMN_DEGREE,degree);
        cv.put(COLUMN_DEPARTMENT,department);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

}



