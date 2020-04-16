package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class TaskCreation extends AppCompatActivity implements View.OnClickListener {

    Calendar calendar;
    int current_year, current_month, current_day;
    int current_hour_heure, current_minute_heure;
    int current_hour_duree, current_minute_duree;
    String month, day, minutes, hours;
    Button choose_date_btn, choose_hr_btn, choose_duree_btn;
    Button choose_lun_btn, choose_mar_btn, choose_mer_btn, choose_jeu_btn, choose_ven_btn, choose_sam_btn, choose_dim_btn;
    DatePickerDialog date_picker;
    TimePickerDialog time_picker_dialog;
    Database bdd;
    boolean is_pushed_lun, is_pushed_mar, is_pushed_mer, is_pushed_jeu, is_pushed_ven, is_pushed_sam, is_pushed_dim = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);

        choose_date_btn = findViewById(R.id.btn_date);
        choose_date_btn.setOnClickListener(this);
        choose_hr_btn = findViewById(R.id.btn_hr);
        choose_hr_btn.setOnClickListener(this);
        choose_duree_btn = findViewById(R.id.btn_dur);
        choose_duree_btn.setOnClickListener(this);
        choose_lun_btn = findViewById(R.id.btn_lun);
        choose_lun_btn.setOnClickListener(this);
        choose_mar_btn = findViewById(R.id.btn_mar);
        choose_mar_btn.setOnClickListener(this);
        choose_mer_btn = findViewById(R.id.btn_mer);
        choose_mer_btn.setOnClickListener(this);
        choose_jeu_btn = findViewById(R.id.btn_jeu);
        choose_jeu_btn.setOnClickListener(this);
        choose_ven_btn = findViewById(R.id.btn_ven);
        choose_ven_btn.setOnClickListener(this);
        choose_sam_btn = findViewById(R.id.btn_sam);
        choose_sam_btn.setOnClickListener(this);
        choose_dim_btn = findViewById(R.id.btn_dim);
        choose_dim_btn.setOnClickListener(this);
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
        if (v.getId() == R.id.btn_date) {

            calendar = Calendar.getInstance();
            current_year = calendar.get(Calendar.YEAR);
            current_month = calendar.get(Calendar.MONTH);
            current_day = calendar.get(Calendar.DAY_OF_MONTH);
            date_picker = new DatePickerDialog(TaskCreation.this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                    day = String.valueOf(selectedDay);
                    month = String.valueOf(selectedMonth);

                    day = day.length() == 1 ? "0" + selectedDay : "" + selectedDay;
                    month = month.length() == 1 ? "0" + selectedMonth : "" + selectedMonth;

                    choose_date_btn.setText(day + "/" + month + "/" + selectedYear);
                }
            },current_year, current_month, current_day);
            date_picker.setTitle("Select date");
            date_picker.show();


        } else if (v.getId() == R.id.btn_dur) {
            calendar = Calendar.getInstance();
            current_hour_duree = calendar.get(Calendar.HOUR_OF_DAY);
            current_minute_duree = calendar.get(Calendar.MINUTE);
            time_picker_dialog = new TimePickerDialog(TaskCreation.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hours = String.valueOf(hourOfDay);
                    minutes = String.valueOf(minute);

                    minutes = minutes.length() == 1 ? "0" + minute : "" + minute;
                    hours = hours.length() == 1 ? "0" + hourOfDay : "" + hourOfDay;
                    choose_duree_btn.setText(hours + ":" + minutes);
                }
            }, current_hour_duree, current_minute_duree, true);

            time_picker_dialog.show();
        } else if (v.getId() == R.id.btn_hr) {
            calendar = Calendar.getInstance();
            current_hour_heure = calendar.get(Calendar.HOUR_OF_DAY);
            current_minute_heure = calendar.get(Calendar.MINUTE);
            time_picker_dialog = new TimePickerDialog(TaskCreation.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hours = String.valueOf(hourOfDay);
                    minutes = String.valueOf(minute);

                    minutes = minutes.length() == 1 ? "0" + minute : "" + minute;
                    hours = hours.length() == 1 ? "0" + hourOfDay : "" + hourOfDay;
                    choose_hr_btn.setText(hours + ":" + minutes);
                }
            }, current_hour_heure, current_minute_heure, true);

            time_picker_dialog.show();
        } else if (v.getId() == R.id.btn_lun) {
            //int basic_color_btn = getResources().getColor(R.color.colorButton);
            //Log.d("state : OUI", "OKOUIOUI");
            //Log.d("couleur : " + color_btn, "SUCCESS");
            if (!is_pushed_lun) {
                is_pushed_lun = true;
            } else {
                is_pushed_lun = false;
            }
        } else if (v.getId() == R.id.btn_mar) {
            //int basic_color_btn = getResources().getColor(R.color.colorButton);
            //Log.d("state : OUI", "OKOUIOUI");
            //Log.d("couleur : " + color_btn, "SUCCESS");
            if (!is_pushed_mar) {
                is_pushed_mar = true;
            } else {
                is_pushed_mar = false;
            }
        } else if (v.getId() == R.id.btn_mer) {
            //int basic_color_btn = getResources().getColor(R.color.colorButton);
            //Log.d("state : OUI", "OKOUIOUI");
            //Log.d("couleur : " + color_btn, "SUCCESS");
            if (!is_pushed_mer) {
                is_pushed_mer = true;
            } else {
                is_pushed_mer = false;
            }
        } else if (v.getId() == R.id.btn_jeu) {
            //int basic_color_btn = getResources().getColor(R.color.colorButton);
            //Log.d("state : OUI", "OKOUIOUI");
            //Log.d("couleur : " + color_btn, "SUCCESS");
            if (!is_pushed_jeu) {
                is_pushed_jeu = true;
            } else {
                is_pushed_jeu = false;
            }
        } else if (v.getId() == R.id.btn_ven) {
            //int basic_color_btn = getResources().getColor(R.color.colorButton);
            //Log.d("state : OUI", "OKOUIOUI");
            //Log.d("couleur : " + color_btn, "SUCCESS");
            if (!is_pushed_ven) {
                is_pushed_ven = true;
            } else {
                is_pushed_ven = false;
            }
        } else if (v.getId() == R.id.btn_sam) {
            //int basic_color_btn = getResources().getColor(R.color.colorButton);
            //Log.d("state : OUI", "OKOUIOUI");
            //Log.d("couleur : " + color_btn, "SUCCESS");
            if (!is_pushed_sam) {
                is_pushed_sam = true;
            } else {
                is_pushed_sam = false;
            }
        } else if (v.getId() == R.id.btn_dim) {
            //int basic_color_btn = getResources().getColor(R.color.colorButton);
            //Log.d("state : OUI", "OKOUIOUI");
            //Log.d("couleur : " + color_btn, "SUCCESS");
            if (!is_pushed_dim) {
                is_pushed_dim = true;
            } else {
                is_pushed_dim = false;
            }
        } else if (v.getId() == R.id.btn_create_task) {

        }
    }
}