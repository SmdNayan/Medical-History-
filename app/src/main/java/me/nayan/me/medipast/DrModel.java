package me.nayan.me.medipast;

/**
 * Created by User on 21-May-17.
 */

public class DrModel {
    private int drId;
    private String drName;
    private String drDetails;
    private String drAppointment;
    private String drPhone;
    private String drEmail;

    public DrModel() {

    }

    public DrModel(int drId, String drName, String drDetails, String drAppointment, String drPhone, String drEmail) {
        this.drId = drId;
        this.drName = drName;
        this.drDetails = drDetails;
        this.drAppointment = drAppointment;
        this.drPhone = drPhone;
        this.drEmail = drEmail;
    }

    public DrModel(String drName, String drDetails, String drAppointment, String drPhone, String drEmail) {
        this.drName = drName;
        this.drDetails = drDetails;
        this.drAppointment = drAppointment;
        this.drPhone = drPhone;
        this.drEmail = drEmail;
    }

    public int getDrId() {
        return drId;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public String getDrDetails() {
        return drDetails;
    }

    public void setDrDetails(String drDetails) {
        this.drDetails = drDetails;
    }

    public String getDrAppointment() {
        return drAppointment;
    }

    public void setDrAppointment(String drAppointment) {
        this.drAppointment = drAppointment;
    }

    public String getDrPhone() {
        return drPhone;
    }

    public void setDrPhone(String drPhone) {
        this.drPhone = drPhone;
    }

    public String getDrEmail() {
        return drEmail;
    }

    public void setDrEmail(String drEmail) {
        this.drEmail = drEmail;
    }
}

