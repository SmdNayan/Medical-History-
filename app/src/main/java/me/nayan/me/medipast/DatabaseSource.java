package me.nayan.me.medipast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by User on 21-May-17.
 */

public class DatabaseSource {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private DrModel drModel;
    private MedicalHistoryModel medicalHistoryModel;

    public DatabaseSource(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open(){
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }
    public void close(){
        sqLiteDatabase.close();
    }

    public boolean addDoctor(DrModel drs){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_NAME,drs.getDrName());
        values.put(DatabaseHelper.COL_DETAILS,drs.getDrDetails());
        values.put(DatabaseHelper.COL_APPOINTMENT,drs.getDrAppointment());
        values.put(DatabaseHelper.COL_PHONE,drs.getDrPhone());
        values.put(DatabaseHelper.COL_EMAIL,drs.getDrEmail());


        long id = sqLiteDatabase.insert(DatabaseHelper.TABLE_DOCTOR,null,values);
        this.close();
        if(id > 0){
            return true;
        }else{
            return false;
        }
    }



    public boolean addMedicalHistory(MedicalHistoryModel mhm){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_MEDICAL_HISTORY_DR_ID,mhm.getDr_id());
        values.put(DatabaseHelper.COL_MEDICAL_HISTORY_PRESCRIPTION_PICTURE,mhm.getPrescription());
        values.put(DatabaseHelper.COL_MEDICAL_HISTORY_DR_NAME,mhm.getDrName());
        values.put(DatabaseHelper.COL_MEDICAL_HISTORY_DETAILS,mhm.getDetails());
        values.put(DatabaseHelper.COL_MEDICAL_HISTORY_DATE,mhm.getDate());



        long id = sqLiteDatabase.insert(DatabaseHelper.TABLE_MEDICAL_HISTORY,null,values);
        this.close();
        if(id > 0){
            return true;
        }else{
            return false;
        }
    }


    public ArrayList<DrModel> getAllDrInfo(){
        ArrayList<DrModel>drArrayList = new ArrayList<>();
        this.open();
        /*Cursor cursor = sqLiteDatabase.rawQuery("select * from "+DatabaseHelper.TABLE_DOCTOR,null);*/

        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_DOCTOR,null,null,null,null,null,null);
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() > 0){
            for(int i = 0; i < cursor.getCount(); i++){
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_DR_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
                String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DETAILS));
                String appointment = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_APPOINTMENT));
                String phone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMAIL));

                drModel = new DrModel(id,name,details,appointment,phone,email);
                drArrayList.add(drModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return drArrayList;

    }


    public ArrayList<MedicalHistoryModel> getAllMedicalHistory(int drId){
        ArrayList<MedicalHistoryModel> medicalHistoryArrayList = new ArrayList<>();
        this.open();
        /*Cursor cursor = sqLiteDatabase.rawQuery("select * from "+DatabaseHelper.TABLE_DOCTOR,null);*/

        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_MEDICAL_HISTORY,null,DatabaseHelper.COL_MEDICAL_HISTORY_DR_ID+" =? ",new String[] {String.valueOf(drId)},null,null,null);
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() > 0){
            for(int i = 0; i < cursor.getCount(); i++){
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MEDICAL_HISTORY_ID));
                int dr_id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MEDICAL_HISTORY_DR_ID));
                String prescription = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICAL_HISTORY_PRESCRIPTION_PICTURE));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICAL_HISTORY_DR_NAME));
                String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICAL_HISTORY_DETAILS));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICAL_HISTORY_DATE));

//                medicalHistoryModel = new MedicalHistoryModel(id,dr_id,prescription,name,details,date);

                Log.d("date","-------"+date);

                medicalHistoryModel = new MedicalHistoryModel(id,date);
                medicalHistoryArrayList.add(medicalHistoryModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return medicalHistoryArrayList;

    }

    public ArrayList<MedicalHistoryModel> getSingleMedicalHistoryDetails(int history_id){
        ArrayList<MedicalHistoryModel>medicalHistoryArrayList = new ArrayList<>();
        this.open();
        /*Cursor cursor = sqLiteDatabase.rawQuery("select * from "+DatabaseHelper.TABLE_DOCTOR,null);*/

        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_MEDICAL_HISTORY,null,DatabaseHelper.COL_MEDICAL_HISTORY_ID+" =? ",new String[] {String.valueOf(history_id)},null,null,null);
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() > 0){

            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MEDICAL_HISTORY_ID));
            int dr_id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MEDICAL_HISTORY_DR_ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICAL_HISTORY_DR_NAME));
            String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICAL_HISTORY_DETAILS));
            String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICAL_HISTORY_DATE));
            String prescription = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICAL_HISTORY_PRESCRIPTION_PICTURE));

            medicalHistoryModel = new MedicalHistoryModel(dr_id,prescription,name,details,date);
            medicalHistoryArrayList.add(medicalHistoryModel);
            cursor.moveToNext();

        }
        cursor.close();
        this.close();
        return medicalHistoryArrayList;

    }



    public ArrayList<DrModel> getSingleDoctorDetails(long dr_id){
        ArrayList<DrModel> doctorModelArrayList= new ArrayList<>();

        this.open();
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_DOCTOR, null, DatabaseHelper.COL_DR_ID+" =? ",new String[] { String.valueOf(dr_id) },null,null,null);
        cursor.moveToFirst();

        int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_DR_ID));
        String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
        String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DETAILS));
        String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_APPOINTMENT));
        String phone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHONE));
        String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMAIL));

        drModel = new DrModel(name,details,date,phone,email);
        doctorModelArrayList.add(drModel);




        cursor.close();
        this.close();

        return doctorModelArrayList;
    }




    // -----------------DELETE--------------------//


    public boolean deleteDoctor(long dr_id){
        this.open();
        int deleteDrId = sqLiteDatabase.delete(DatabaseHelper.TABLE_DOCTOR,DatabaseHelper.COL_DR_ID+" = ?",new String[]{Long.toString(dr_id)});

        if (deleteDrId > 0){
            return true;
        }else {
            return false;
        }
    }



    // -----------------DELETE--------------------//


    public boolean updateDrDetailes(DrModel drs,long dr_id){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_NAME,drs.getDrName());
        values.put(DatabaseHelper.COL_DETAILS,drs.getDrDetails());
        values.put(DatabaseHelper.COL_APPOINTMENT,drs.getDrAppointment());
        values.put(DatabaseHelper.COL_PHONE,drs.getDrPhone());
        values.put(DatabaseHelper.COL_EMAIL,drs.getDrEmail());


        long id = sqLiteDatabase.update(DatabaseHelper.TABLE_DOCTOR,values,DatabaseHelper.COL_DR_ID+" = ?",new String[]{Long.toString(dr_id)});
        this.close();
        if(id > 0){
            return true;
        }else{
            return false;
        }
    }


}
