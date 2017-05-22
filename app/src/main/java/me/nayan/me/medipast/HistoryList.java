package me.nayan.me.medipast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.nayan.me.medipast.R;

import java.util.ArrayList;

/**
 * Created by User on 21-May-17.
 */

public class HistoryList extends AppCompatActivity {

    private GridView prescriptionList;
    private ArrayList<MedicalHistoryModel> medicalHistoryModelArrayList;
    private ArrayList<String> dateList=new ArrayList<>();
    private DatabaseSource databaseSource;
    private MedicalHistoryModel medicalHistoryModel;
    private UserAuthentication userAuthenticationSharedPreference;
    private int drId;
//    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        userAuthenticationSharedPreference = new UserAuthentication(this);

        prescriptionList = (GridView) findViewById(R.id.prescriptionListGV);
        drId = userAuthenticationSharedPreference.getDrId();

        databaseSource = new DatabaseSource(this);
        medicalHistoryModelArrayList = new ArrayList<>();

        medicalHistoryModelArrayList = databaseSource.getAllMedicalHistory(drId);

//        Log.d("date", String.valueOf(medicalHistoryModelArrayList.get()));
        for(MedicalHistoryModel medicalHistoryModel:medicalHistoryModelArrayList){
            dateList.add(medicalHistoryModel.getDate());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1,dateList);
        prescriptionList.setAdapter(arrayAdapter);

        prescriptionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HistoryList.this,ShowMedicalHistory.class);
                intent.putExtra("history_id",medicalHistoryModelArrayList.get(position).getId());

                startActivity(intent);
                finish();
            }
        });


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

                Intent intent = new Intent(HistoryList.this,DrListActivity.class);
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
            Intent intent = new Intent(HistoryList.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }else {
            Toast.makeText(this, "Logout not successfully", Toast.LENGTH_SHORT).show();
        }

    }
}