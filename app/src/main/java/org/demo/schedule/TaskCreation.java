package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class TaskCreation extends AppCompatActivity {

    Calendar calendar;
    int currentHour_heure, currentHour_duree;
    int currentMinute_heure, currentMinute_duree;
    Button choose_hr_btn;
    Button choose_duree_btn;
    TimePickerDialog timePickerDialog;
    Database bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);

        bdd = new Database();

        // Add a spinner in order to choose the type of the task
        Spinner spinner_type = findViewById(R.id.type);
        ArrayAdapter<CharSequence> adapter_type = ArrayAdapter.createFromResource(this,
                R.array.task_type, android.R.layout.simple_spinner_item);
        adapter_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type.setAdapter(adapter_type);

        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        findViewById(R.id.linear_dw).setVisibility(View.GONE);
                        break;
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                        findViewById(R.id.linear_dw).setVisibility(View.VISIBLE);
                        break;
                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Add a timePicker to choose the hour of the task
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

        // Add a second timePicker to choose the duration of the task
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

        // Add a spinner to choose the number of reminder for the task
        Spinner spinner_number = findViewById(R.id.num_reminder);
        ArrayAdapter<CharSequence> adapter_number = ArrayAdapter.createFromResource(this,
                R.array.number_reminder, android.R.layout.simple_spinner_item);
        adapter_number.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_number.setAdapter(adapter_number);

        // Add a spinner to choose the number of reminder for the task
        Spinner spinner_interval = findViewById(R.id.reminder_interval);
        ArrayAdapter<CharSequence> adapter_interval = ArrayAdapter.createFromResource(this,
                R.array.interval_of_reminder, android.R.layout.simple_spinner_item);
        adapter_interval.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_interval.setAdapter(adapter_interval);

    }

}
