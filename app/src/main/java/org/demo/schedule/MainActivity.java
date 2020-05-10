package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_la;
    Button btn_ac;
    String userId;
    Database bdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_la = findViewById(R.id.btn_la);
        btn_ac = findViewById(R.id.btn_ac);

        btn_la.setOnClickListener(this);
        btn_ac.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        if(prefs.contains("tel")) {
            userId = prefs.getString("tel", null);
            bdd = new Database();
            bdd.readData(bdd.getUserRef().child(userId), new OnGetDataListener() {
                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {
                    Context context = getApplicationContext();
                    String type = dataSnapshot.child("Type").getValue().toString();
                    if(type.equals("parent")) {
                        Intent intent = new Intent(context, KidChoosingActivity.class);
                        intent.putExtra("userID", userId);
                        startActivity(intent);
                    } else if (type.equals("enfant")) {
                        bdd.setAlarmManager(userId, context);
                        Intent intent = new Intent(context, KidSchedule.class);
                        intent.putExtra("userID", userId);
                        startActivity(intent);
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
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btn_la) {
            Intent intent = new Intent(this, AccountCreationActivity.class);
            startActivity(intent);
        }
        else if(view == btn_ac) {
            Intent intent = new Intent(this, Connexion.class);
            startActivity(intent);
        }
    }
}
