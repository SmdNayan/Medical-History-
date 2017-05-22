package me.nayan.me.medipast;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 21-May-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Medi Past";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_DOCTOR = "tbl_dr";
    public static final String TABLE_MEDICAL_HISTORY = "tbl_medical_history";


    // column of TABLE_DOCTOR

    public static final String COL_DR_ID = "tbl_dr_col_id";
    public static final String COL_NAME = "tbl_dr_col_name";
    public static final String COL_DETAILS = "tbl_dr_col_details";
    public static final String COL_APPOINTMENT = "tbl_dr_col_appointment";
    public static final String COL_PHONE = "tbl_dr_col_phone";
    public static final String COL_EMAIL = "tbl_dr_col_email";


    // column of TABLE_MEDICAL_HISTORY

    public static final String COL_MEDICAL_HISTORY_ID = "tbl_medical_history_col_id";
    public static final String COL_MEDICAL_HISTORY_DR_ID = "tbl_medical_history_col_dr_id";
    public static final String COL_MEDICAL_HISTORY_PRESCRIPTION_PICTURE = "tbl_medical_history_col_prescription";
    public static final String COL_MEDICAL_HISTORY_DR_NAME = "tbl_medical_history_col_dr_name";
    public static final String COL_MEDICAL_HISTORY_DETAILS = "tbl_medical_history_col_details";
    public static final String COL_MEDICAL_HISTORY_DATE = "tbl_medical_history_col_date";


    /*public static final String CREATE_MOVIE_TABLE1 = "create table tbl_movie(tbl_id integer primary key, tbl_name text, tbl_year text);";*/

    public static final String CREATE_DOCTOR_TABLE = "create table "+ TABLE_DOCTOR +"("+
            COL_DR_ID +" integer primary key, "+
            COL_NAME+" text, "+
            COL_DETAILS+" text, "+
            COL_APPOINTMENT+" text, "+
            COL_PHONE+" text, "+
            COL_EMAIL+" text);";


    public static final String CREATE_MEDICAL_HISTORY_TABLE = "create table "+ TABLE_MEDICAL_HISTORY +"("+
            COL_MEDICAL_HISTORY_ID +" integer primary key, "+
            COL_MEDICAL_HISTORY_DR_ID +" integer, "+
            COL_MEDICAL_HISTORY_PRESCRIPTION_PICTURE +" text, "+
            COL_MEDICAL_HISTORY_DR_NAME+" text, "+
            COL_MEDICAL_HISTORY_DETAILS+" text, "+
            COL_MEDICAL_HISTORY_DATE+" text);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DOCTOR_TABLE);
        db.execSQL(CREATE_MEDICAL_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ TABLE_DOCTOR);
        db.execSQL("drop table if exists "+ TABLE_MEDICAL_HISTORY);
        onCreate(db);
    }
}
