package me.nayan.me.medipast;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nayan.me.medipast.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by User on 21-May-17.
 */

public class ShowMedicalHistory extends AppCompatActivity {


    private ArrayList<MedicalHistoryModel> medicalHistoryModelArrayList;
    private DatabaseSource databaseSource;
    private UserAuthentication userAuthenticationSharedPreference;


    private ImageView prescriptionIV;
    private TextView drName;
    private TextView details;
    private TextView date;
    private int historyId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_medical_history);

        prescriptionIV = (ImageView) findViewById(R.id.showHistoryPrescriptionIV);
        drName = (TextView) findViewById(R.id.showHistoryDrNameTV);
        details = (TextView) findViewById(R.id.showHistoryDrDetailsTV);
        date = (TextView) findViewById(R.id.showHistoryDrDateTV);
        userAuthenticationSharedPreference = new UserAuthentication(this);
        int dr_id=userAuthenticationSharedPreference.getDrId();

        historyId=getIntent().getIntExtra("history_id",0);

        databaseSource = new DatabaseSource(this);
        medicalHistoryModelArrayList = new ArrayList<>();
        medicalHistoryModelArrayList = databaseSource.getSingleMedicalHistoryDetails(historyId);

        drName.setText(medicalHistoryModelArrayList.get(0).getDrName());
        details.setText(medicalHistoryModelArrayList.get(0).getDetails());
        date.setText(medicalHistoryModelArrayList.get(0).getDate());


        Context context= prescriptionIV.getContext();
        Uri fileUri = Uri.parse(medicalHistoryModelArrayList.get(0).getPrescription());

//        Picasso.with(context).load(fileUri);
        Picasso.with(context).load(fileUri)
                .resize(900, 500)
                .into(prescriptionIV);


        Log.d("uri: ",medicalHistoryModelArrayList.get(0).getPrescription());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.show_medical_history_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.historyActionHome:

                Intent intent = new Intent(ShowMedicalHistory.this,DrListActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.historyActionLogout:
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {

        boolean status = userAuthenticationSharedPreference.cleanUser();
        if (status){
            Intent intent = new Intent(ShowMedicalHistory.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }else {
            Toast.makeText(this, "Logout not successfully", Toast.LENGTH_SHORT).show();
        }

    }
}