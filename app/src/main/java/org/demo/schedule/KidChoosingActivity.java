package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KidChoosingActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner kidspinner;
    Button btn_selectkid;
    String kidselected;
    String userId;
    List<String> prenomsEnfants;
    Database bdd;
    List<String> kidList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_choosing);

        btn_selectkid = findViewById(R.id.btn_selectkid);
        kidspinner = findViewById(R.id.kidspinner);

        prenomsEnfants = new ArrayList<>();
        prenomsEnfants.add("Sélectionner...");

        bdd = new Database();
        userId = getIntent().getStringExtra("userID");
        bdd.readData(bdd.getUserRef(), new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                String enfants = dataSnapshot.child(userId).child("Enfant").getValue().toString();
                kidList = Arrays.asList(enfants.split("\\|"));
                for(String kidtel : kidList) {
                    String gamin = dataSnapshot.child(kidtel).child("FirstName").getValue().toString();
                    prenomsEnfants.add(gamin);
                }
            }

            @Override
            public void onStart() {
                Log.d("ONSTART", "Started");
            }

            @Override
            public void onFailure() {
                Log.d("ONFAIL", "Failed");
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, prenomsEnfants);

        kidspinner.setAdapter(adapter);

        kidspinner.setOnItemSelectedListener(this);
        btn_selectkid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(!kidselected.equals("Sélectionner...")) {
            Intent intent = new Intent(this, AdultSchedule.class);
            intent.putExtra("kidtel", kidselected);
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i != 0) {
            kidselected = kidList.get(i - 1);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d("SELECT", "nothing");
    }
}
