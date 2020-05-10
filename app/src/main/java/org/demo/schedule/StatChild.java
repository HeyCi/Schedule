package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class StatChild extends AppCompatActivity implements View.OnClickListener {

    Calendar calendar;
    Calendar calendar_fd;
    Calendar calendar_ld;
    SimpleDateFormat monformat;
    TextView txt_sem;
    TextView txt_int;
    TextView et_nb_task;
    TextView et_done_task;
    TextView et_late_task;
    TextView et_percent_task;
    TextView et_congrat_condition;
    Button btn_congrat, btn_precedent, btn_suivant;
    float percent = 0;
    String childId;
    String message;
    Database bdd;
    ArrayList<Task> successed_task, failed_task;
    Integer success_count = 0, failed_count = 0, total_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_child);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SEND_SMS}, 0);

        bdd = new Database();

        calendar = Calendar.getInstance();
        calendar_fd = Calendar.getInstance();
        calendar_ld = Calendar.getInstance();
        txt_sem = findViewById(R.id.txt_semaine);
        txt_int = findViewById(R.id.txt_interval);
        et_nb_task = findViewById(R.id.nb_task);
        et_done_task = findViewById(R.id.done_task);
        et_late_task = findViewById(R.id.late_task);
        et_percent_task = findViewById(R.id.percent_task);
        et_congrat_condition = findViewById(R.id.task_condition);
        btn_precedent = findViewById(R.id.btn_prec);
        btn_precedent.setOnClickListener(this);
        btn_suivant = findViewById(R.id.btn_suiv);
        btn_suivant.setOnClickListener(this);
        btn_congrat = findViewById(R.id.btn_congrat);
        btn_congrat.setOnClickListener(this);
        String valid_task = "Les conditions sont remplies pour être fier de votre enfant. Félicitez-le !";
        String invalid_task = "Les conditions requises ne sont pas remplies. Votre enfant doit bénéficier de plus de 80% de tâches complétées.";
        childId = getIntent().getStringExtra("kidtel");
        message = "Félicitations mon enfant, je suis fier de toi.";

        total_count = success_count + failed_count;
        monformat = new SimpleDateFormat("dd/MM/yyyy");

        calendar_fd.add(Calendar.DAY_OF_MONTH, Calendar.MONDAY-calendar_fd.get(Calendar.DAY_OF_WEEK));
        calendar_ld.add(Calendar.DAY_OF_MONTH, Calendar.SATURDAY-calendar_ld.get(Calendar.DAY_OF_WEEK) + 1);
        txt_sem.setText("Semaine " + calendar.get(Calendar.WEEK_OF_YEAR));
        txt_int.setText("Du " + monformat.format(calendar_fd.getTime()) + " au " + monformat.format(calendar_ld.getTime()));

        et_nb_task.setText(String.valueOf(total_count));
        et_done_task.setText(String.valueOf(success_count));
        et_late_task.setText(String.valueOf(failed_count));
        if(total_count != 0) {
            percent = ((float)success_count / total_count) * 100;
        }
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
    protected void onStart() {
        super.onStart();
        getBddData();
    }

    @Override
    public void onClick(View v) {
        if(v == btn_congrat) {
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(childId, null, message, null, null);
            Toast.makeText(this, "Notification envoyée !", Toast.LENGTH_SHORT).show();
        }
        else if(v == btn_precedent) {
            calendar.add(Calendar.WEEK_OF_YEAR, -1);
            txt_sem.setText("Semaine " + calendar.get(Calendar.WEEK_OF_YEAR));
            calendar_fd.add(Calendar.WEEK_OF_YEAR, -1);
            calendar_ld.add(Calendar.WEEK_OF_YEAR, -1);

            txt_int.setText("Du " + monformat.format(calendar_fd.getTime()) + " au " + monformat.format(calendar_ld.getTime()));

            getBddData();
            et_nb_task.setText(String.valueOf(total_count));
            et_done_task.setText(String.valueOf(success_count));
            et_late_task.setText(String.valueOf(failed_count));
            if(total_count != 0) {
                percent = ((float)success_count / total_count) * 100;
            }
            et_percent_task.setText(percent + " %");
        }
        else if(v == btn_suivant) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            txt_sem.setText("Semaine " + calendar.get(Calendar.WEEK_OF_YEAR));
            calendar_fd.add(Calendar.WEEK_OF_YEAR, 1);
            calendar_ld.add(Calendar.WEEK_OF_YEAR, 1);

            txt_int.setText("Du " + monformat.format(calendar_fd.getTime()) + " au " + monformat.format(calendar_ld.getTime()));

            getBddData();
            et_nb_task.setText(String.valueOf(total_count));
            et_done_task.setText(String.valueOf(success_count));
            et_late_task.setText(String.valueOf(failed_count));
            if(total_count != 0) {
                percent = ((float)success_count / total_count) * 100;
            }
            et_percent_task.setText(percent + " %");
        }

    }

    public void getBddData() {
            bdd.readData(bdd.getUserRef().child(childId), new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("TaskSuccess").exists()) {
                    for (DataSnapshot data : dataSnapshot.child("TaskSuccess").getChildren()) {
                        successed_task.add(data.getValue(Task.class));
                    }
                    success_count = successed_task.size();
                }
                else {
                    success_count = 0;
                }
                if(dataSnapshot.child("TaskFailed").exists()) {
                    for (DataSnapshot data : dataSnapshot.child("TaskFailed").getChildren()) {
                        failed_task.add(data.getValue(Task.class));
                    }
                    failed_count = failed_task.size();
                }
                else {
                    failed_count = 0;
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
    }
}
