package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StatChild extends AppCompatActivity implements View.OnClickListener {

    TextView et_nb_task;
    TextView et_done_task;
    TextView et_late_task;
    TextView et_percent_task;
    TextView et_congrat_condition;
    Button btn_congrat;
    float percent;
    int task_nb = 20;
    int task_done = 16;
    int task_late = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_child);

        et_nb_task = findViewById(R.id.nb_task);
        et_done_task = findViewById(R.id.done_task);
        et_late_task = findViewById(R.id.late_task);
        et_percent_task = findViewById(R.id.percent_task);
        et_congrat_condition = findViewById(R.id.task_condition);
        btn_congrat = findViewById(R.id.btn_congrat);
        btn_congrat.setOnClickListener(this);
        String valid_task = "Les conditions sont remplies pour être fier de votre enfant. Félicitez-le !";
        String invalid_task = "Les conditions requises ne sont pas remplies. Votre enfant doit bénéficier de plus de 80% de tâches complétées.";

        et_nb_task.setText(String.valueOf(task_nb));
        et_done_task.setText(String.valueOf(task_done));
        et_late_task.setText(String.valueOf(task_late));
        percent = ((float)task_done / task_nb) * 100;
        Log.d("" + percent, "SECfb");
        et_percent_task.setText(percent + " %");

        if(percent < 80) {
            findViewById(R.id.btn_congrat).setVisibility(View.GONE);
            et_congrat_condition.setTextColor(0xFF731818);
            et_congrat_condition.setText(invalid_task);
        }
        else {
            findViewById(R.id.btn_congrat).setVisibility(View.VISIBLE);
            et_congrat_condition.setTextColor(0xFF226816);
            et_congrat_condition.setText(valid_task);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == btn_congrat) {
            //envoi SMS à l'enfant
        }
    }
}
