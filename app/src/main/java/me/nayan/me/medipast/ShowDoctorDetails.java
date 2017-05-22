package me.nayan.me.medipast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nayan.me.medipast.R;

import java.util.ArrayList;

/**
 * Created by User on 21-May-17.
 */

public class ShowDoctorDetails extends AppCompatActivity {

    private ArrayList<DrModel> drModelArrayList;
    private DrModel drModel;
    private DatabaseSource databaseSource;

    private TextView showDrName;
    private TextView showDetails;
    private TextView showDate;
    private TextView showPhone;
    private TextView showEmail;

    private String nameSt;
    private String detailsSt;
    private String dateSt;
    private String phoneSt;
    private String emailSt;


    private int dr_id;
    private String drNameString;

    private UserAuthentication userAuthenticationSharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_doctor_details);

        showDrName = (TextView) findViewById(R.id.showDrNameTV);
        showDetails = (TextView) findViewById(R.id.showDrDetails);
        showDate = (TextView) findViewById(R.id.showDate);
        showPhone = (TextView) findViewById(R.id.showPhoneTV);
        showEmail = (TextView) findViewById(R.id.showEmailTV);
        userAuthenticationSharedPreference = new UserAuthentication(this);

        drModelArrayList = new ArrayList<>();
//        dr_id = getIntent().getIntExtra("dr_id",0);
        dr_id = userAuthenticationSharedPreference.getDrId();

        databaseSource = new DatabaseSource(this);
        drModelArrayList = databaseSource.getSingleDoctorDetails(dr_id);

        nameSt = drModelArrayList.get(0).getDrName().toString();
        detailsSt = drModelArrayList.get(0).getDrDetails().toString();
        dateSt = drModelArrayList.get(0).getDrAppointment().toString();
        phoneSt = drModelArrayList.get(0).getDrPhone().toString();
        emailSt = drModelArrayList.get(0).getDrEmail().toString();

        showDrName.setText(nameSt);
        showDetails.setText(detailsSt);
        showDate.setText(dateSt);
        showPhone.setText(phoneSt);
        showEmail.setText(emailSt);


        drNameString = drModelArrayList.get(0).getDrName().toString();

    }

    public void addMedicalHistory(View view) {
        Intent intent = new Intent(ShowDoctorDetails.this,AddMedicalHistory.class);
        intent.putExtra("dr_name",drNameString);
        startActivity(intent);
    }

    public void showAllPrescription(View view) {
        Intent intent = new Intent(ShowDoctorDetails.this,HistoryList.class);
        intent.putExtra("dr_name",drNameString);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.doctor_action_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent;
        switch (item.getItemId()) {
            case R.id.actionUpdate:

                intent = new Intent(ShowDoctorDetails.this,AddDoctor.class);
                intent.putExtra("dr_id",dr_id);
                intent.putExtra("dr_name",nameSt);
                intent.putExtra("dr_details",detailsSt);
                intent.putExtra("dr_date",dateSt);
                intent.putExtra("dr_phone",phoneSt);
                intent.putExtra("dr_email",emailSt);
                startActivity(intent);
                finish();
                return true;
            case R.id.actionDelete:
                logout();
                deleteDr();
                return true;
            case R.id.actionHome:

                intent = new Intent(ShowDoctorDetails.this,DrListActivity.class);
                startActivity(intent);
                return true;
            case R.id.actionLogout:
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {

        boolean status = userAuthenticationSharedPreference.cleanUser();
        if (status){
            Intent intent = new Intent(ShowDoctorDetails.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }else {
            Toast.makeText(this, "Logout not successfully", Toast.LENGTH_SHORT).show();
        }

    }
    private void deleteDr(){
        boolean status = databaseSource.deleteDoctor(dr_id);
        if (status){
            Intent intent = new Intent(ShowDoctorDetails.this,DrListActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Doctor not delete", Toast.LENGTH_SHORT).show();
        }
    }

//    private void updateDrDetailes(){
//
//    }


}
