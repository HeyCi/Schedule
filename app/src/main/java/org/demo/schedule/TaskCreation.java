package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class TaskCreation extends AppCompatActivity implements View.OnClickListener {

    Calendar calendar;
    int currentHour_heure, currentHour_duree;
    int currentMinute_heure, currentMinute_duree;
    Button choose_hr_btn;
    Button choose_duree_btn;
    Button choose_lun_btn;
    Button choose_mar_btn;
    Button choose_mer_btn;
    Button choose_jeu_btn;
    Button choose_ven_btn;
    Button choose_sam_btn;
    Button choose_dim_btn;
    Drawable button_background;
    TimePickerDialog timePickerDialog;
    Database bdd;
    String[] dayWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);

        choose_hr_btn = findViewById(R.id.btn_hr);
        choose_duree_btn = findViewById(R.id.btn_dur);
        choose_lun_btn = (Button) findViewById(R.id.btn_lun);
        choose_mar_btn = findViewById(R.id.btn_mar);
        choose_mer_btn = findViewById(R.id.btn_mer);
        choose_jeu_btn = findViewById(R.id.btn_jeu);
        choose_ven_btn = findViewById(R.id.btn_ven);
        choose_sam_btn = findViewById(R.id.btn_sam);
        choose_dim_btn = findViewById(R.id.btn_dim);
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_dur) {
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
        else if(v.getId() == R.id.btn_hr) {
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
        // JE N'ARRIVE PAS A GET LA COLOR LA!
        else if(v.getId() == R.id.btn_lun) {
            button_background = choose_lun_btn.getBackground();
        }
    }
}