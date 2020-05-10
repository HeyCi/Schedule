package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

public class KidTaskActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt_titre;
    TextView txt_date;
    TextView txt_heure;
    ImageView img_act;
    Button btn_afinir;
    Button btn_fini;
    String task_name;
    String userId;
    Task task;
    Database bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_task);
        txt_titre = findViewById(R.id.txt_titre);
        txt_date = findViewById(R.id.txt_date);
        txt_heure = findViewById(R.id.txt_heure);
        btn_afinir = findViewById(R.id.btn_afaire);
        btn_fini = findViewById(R.id.btn_fini);
        img_act = findViewById(R.id.img_tache);

        SharedPreferences prefs = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = prefs.getString("tel", null);
        SharedPreferences sharedprefs = this.getSharedPreferences("task", Context.MODE_PRIVATE);
        task_name = sharedprefs.getString("name", null);
        bdd = new Database();
        bdd.readData(bdd.getUserRef().child(userId).child("Task"), new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.child("name_task").getValue().toString().equals(task_name)) {
                        task = snapshot.getValue(Task.class);

                        txt_titre.setText(task.getName_task());
                        txt_date.setText(task.getDate());
                        txt_heure.setText(task.getHour());
                        switch (task.getType()) {
                            case "quotidienne":
                                img_act.setImageResource(R.mipmap.img_viequot);
                                break;
                            case "ponctuelle":
                                img_act.setImageResource(R.mipmap.img_ponct);
                                break;
                            case "extrascolaire":
                                img_act.setImageResource(R.mipmap.img_extra);
                                break;
                            case "scolaire":
                                img_act.setImageResource(R.mipmap.img_scol);
                                break;
                            case "reveil":
                                img_act.setImageResource(R.mipmap.img_reveil);
                                break;
                            case "butoire":
                                img_act.setImageResource(R.mipmap.img_butoire);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onStart() {
                Log.d("ONSTART", "Started");
            }

            @Override
            public void onFailure() {
                Log.d("onFailure", "Failed");
            }
        });



        btn_fini.setOnClickListener(this);
        btn_afinir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btn_afinir) {
            bdd.CreateTaskFailed(task);
        } else if(view == btn_fini) {
            bdd.CreateTaskSuccess(task);
        }
    }
}
