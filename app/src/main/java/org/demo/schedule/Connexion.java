package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;

public class Connexion extends AppCompatActivity implements View.OnClickListener {

    Button btn_connexion;
    Database bdd;
    EditText et_PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        btn_connexion = findViewById(R.id.btn_connexion);
        et_PhoneNumber = findViewById(R.id.editPhoneNumber);
        bdd = new Database();
        btn_connexion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btn_connexion) {
            final String phoneNumber = et_PhoneNumber.getText().toString();
            bdd.readData(bdd.getUserRef(), new OnGetDataListener() {
                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(phoneNumber).exists()){
                        SharedPreferences prefs = getSharedPreferences("login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("tel", phoneNumber);
                        editor.commit();

                        Log.d("Connexion", "Success");
                        finish();
                        // Connexion reussi

                    } else {

                        Log.d("Connexion", "Failed");
                        // Connexion echoué

                    }
                }

                @Override
                public void onStart() {Log.d("ONSTART", "Started");}

                @Override
                public void onFailure() {Log.d("onFailure", "Failed");}
            });

        }
    }
}
