package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class KidTaskActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt_titre;
    TextView txt_date;
    TextView txt_heure;
    Button btn_afinir;
    Button btn_fini;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_task);
        txt_titre = findViewById(R.id.txt_titre);
        txt_date = findViewById(R.id.txt_date);
        txt_heure = findViewById(R.id.txt_heure);
        btn_afinir = findViewById(R.id.btn_afaire);
        btn_fini = findViewById(R.id.btn_fini);

        task = (Task)getIntent().getSerializableExtra("task");

        txt_titre.setText(task.getName_task());
        txt_date.setText(task.getDate());
        txt_heure.setText(task.getHour());

        btn_fini.setOnClickListener(this);
        btn_afinir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btn_afinir) {
            Toast.makeText(this, "tache pas finie", Toast.LENGTH_LONG).show();
        } else if(view == btn_fini) {
            Toast.makeText(this, "tache finie, bravo !", Toast.LENGTH_LONG).show();
        }
    }
}
