package me.nayan.me.medipast;

/**
 * Created by User on 21-May-17.
 */

public class MedicalHistoryModel  {
    private int id;
    private int dr_id;
    private String prescription;
    private String drName;
    private String details;
    private String date;

    public MedicalHistoryModel(int id, String date) {
        this.id=id;
        this.date = date;

    }

    public MedicalHistoryModel(int id, int dr_id, String prescription, String drName, String details, String date) {

        this.id = id;
        this.dr_id = dr_id;
        this.prescription = prescription;
        this.drName = drName;
        this.details = details;
        this.date = date;
    }

    public MedicalHistoryModel(int dr_id, String prescription, String drName, String details, String date) {

        this.dr_id=dr_id;
        this.prescription = prescription;
        this.drName = drName;
        this.details = details;
        this.date = date;
    }

    public int getDr_id() {
        return dr_id;
    }

    public void setDr_id(int dr_id) {
        this.dr_id = dr_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

