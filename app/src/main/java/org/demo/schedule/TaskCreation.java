package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.TimePicker;

import java.util.Calendar;

public class TaskCreation extends AppCompatActivity {

    Calendar calendar;
    int currentHour_heure, currentHour_duree;
    int currentMinute_heure, currentMinute_duree;
    Button choose_hr_btn;
    Button choose_duree_btn;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);

        choose_hr_btn = findViewById(R.id.btn_hr);
        choose_hr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                currentHour_heure = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute_heure = calendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(TaskCreation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        choose_hr_btn.setText(hourOfDay + ":" + minute);
                    }
                }, currentHour_heure, currentMinute_heure, true);

                timePickerDialog.show();
            }
        });

        choose_duree_btn = findViewById(R.id.btn_dur);
        choose_duree_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                currentHour_duree = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute_duree = calendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(TaskCreation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        choose_duree_btn.setText(hourOfDay + ":" + minute);
                    }
                }, currentHour_duree, currentMinute_duree, true);

                timePickerDialog.show();
            }
        });

    }

}
