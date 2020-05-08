package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class KidTaskActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt_titre;
    TextView txt_date;
    TextView txt_heure;
    ImageView img_act;
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
        img_act = findViewById(R.id.img_tache);

        //todo changer pour ne récupérer que le nom de la tache et récupérer ensuite dans la bdd depuis prf + extra ?
        task = (Task)getIntent().getSerializableExtra("task");

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
