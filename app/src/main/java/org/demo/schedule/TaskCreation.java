package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TaskCreation extends AppCompatActivity implements View.OnClickListener {

    String user_id;
    Calendar calendar;
    EditText et_name_task;
    Spinner spinner_type;
    int current_year, current_month, current_day;
    int current_hour_heure, current_minute_heure;
    int current_hour_duree, current_minute_duree;
    String month, day, minutes, hours;
    Button choose_date_btn, choose_hr_btn, choose_duree_btn;
    Button choose_lun_btn, choose_mar_btn, choose_mer_btn, choose_jeu_btn, choose_ven_btn, choose_sam_btn, choose_dim_btn;
    Button choose_create_btn;
    DatePickerDialog date_picker;
    TimePickerDialog time_picker_dialog;
    Database bdd;
    boolean is_pushed_lun, is_pushed_mar, is_pushed_mer, is_pushed_jeu, is_pushed_ven, is_pushed_sam, is_pushed_dim = false;
    String type_task, date_task, hour_task, duration_task, interval_task;
    Integer number_rem_task;
    String[] day_week = new String[7];
    String day_week_task;
    String recurr_task = "ok";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);

        et_name_task = findViewById(R.id.task_name);
        spinner_type = findViewById(R.id.type);
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
        choose_create_btn = findViewById(R.id.btn_create_task);
        choose_create_btn.setOnClickListener(this);

        user_id = getIntent().getStringExtra("kidtel");

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
                        type_task = "quotidienne";
                        day_week[0] = "L";
                        day_week[1] = "M";
                        day_week[2] = "M";
                        day_week[3] = "J";
                        day_week[4] = "V";
                        day_week[5] = "S";
                        day_week[6] = "D";
                        break;
                    case 1:
                        findViewById(R.id.linear_dw).setVisibility(View.VISIBLE);
                        type_task = "scolaire";
                        break;
                    case 2:
                        findViewById(R.id.linear_dw).setVisibility(View.VISIBLE);
                        type_task = "extrascolaire";
                        break;
                    case 3:
                        findViewById(R.id.linear_dw).setVisibility(View.VISIBLE);
                        type_task = "ponctuelle";
                        break;
                    case 4:
                        findViewById(R.id.linear_dw).setVisibility(View.VISIBLE);
                        type_task = "reveil";
                        break;
                    case 5:
                        findViewById(R.id.linear_dw).setVisibility(View.VISIBLE);
                        type_task = "butoire";
                        break;
                    default:
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Add a spinner to choose the number of reminder for the task
        Spinner spinner_number = findViewById(R.id.num_reminder);
        ArrayAdapter<CharSequence> adapter_number = ArrayAdapter.createFromResource(this,
                R.array.number_reminder, android.R.layout.simple_spinner_item);
        adapter_number.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_number.setAdapter(adapter_number);
        spinner_number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        number_rem_task = 1;
                        break;
                    case 1:
                        number_rem_task = 2;
                        break;
                    case 2:
                        number_rem_task = 3;
                        break;
                    case 3:
                        number_rem_task = 4;
                        break;
                    case 4:
                        number_rem_task = 5;
                        break;
                    case 5:
                        number_rem_task = 6;
                        break;
                    default:
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Add a spinner to choose the number of reminder for the task
        Spinner spinner_interval = findViewById(R.id.reminder_interval);
        ArrayAdapter<CharSequence> adapter_interval = ArrayAdapter.createFromResource(this,
                R.array.interval_of_reminder, android.R.layout.simple_spinner_item);
        adapter_interval.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_interval.setAdapter(adapter_interval);
        spinner_interval.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        interval_task = "5";
                        break;
                    case 1:
                        interval_task = "10";
                        break;
                    case 2:
                        interval_task = "20";
                        break;
                    case 3:
                        interval_task = "30";
                        break;
                    default:
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_date) {

            //renvoie vers un calendrier afin de sélectionner la date du réveil
            calendar = Calendar.getInstance();
            current_year = calendar.get(Calendar.YEAR);
            current_month = calendar.get(Calendar.MONTH);
            current_day = calendar.get(Calendar.DAY_OF_MONTH);
            date_picker = new DatePickerDialog(TaskCreation.this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                    day = String.valueOf(selectedDay);
                    month = String.valueOf(selectedMonth + 1);

                    day = day.length() == 1 ? "0" + day : "" + selectedDay;
                    month = month.length() == 1 ? "0" + month : "" + selectedMonth;
                    choose_date_btn.setText(day + "/" + month + "/" + selectedYear);

                    date_task = selectedYear + "-" + month + "-" + day;
                }
            },current_year, current_month, current_day);
            date_picker.setTitle("Select date");
            date_picker.show();


        } else if (v.getId() == R.id.btn_dur) {

            //renseigne la durée de la tâche
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

                    duration_task = String.valueOf(choose_duree_btn.getText());
                }
            }, current_hour_duree, current_minute_duree, true);
            time_picker_dialog.setTitle("Select duration");
            time_picker_dialog.show();

        } else if (v.getId() == R.id.btn_hr) {

            //renseigne l'heure à laquelle la tâche devra commencer à être effectuée
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

                    hour_task = String.valueOf(choose_hr_btn.getText());
                }
            }, current_hour_heure, current_minute_heure, true);
            time_picker_dialog.setTitle("Select hour");
            time_picker_dialog.show();

        } else if (v.getId() == R.id.btn_lun) {
            if (!is_pushed_lun) {
                is_pushed_lun = true;
                day_week[0] = "L";
                //Log.d("Lundi c'est bon " + day_week[0], "success");
                choose_lun_btn.getBackground().setColorFilter(Color.parseColor("#ACED9A"), PorterDuff.Mode.SRC_ATOP);
            } else {
                is_pushed_lun = false;
                day_week[0] = "_";
                //Log.d("Lundi ça va plus " + day_week[0], "success");
                choose_lun_btn.getBackground().setColorFilter(Color.parseColor("#85A4AE"), PorterDuff.Mode.SRC_ATOP);
            }
        } else if (v.getId() == R.id.btn_mar) {
            if (!is_pushed_mar) {
                is_pushed_mar = true;
                day_week[1] = "M";
                //Log.d("Mardi c'est bon " + day_week[1], "success");
                choose_mar_btn.getBackground().setColorFilter(Color.parseColor("#ACED9A"), PorterDuff.Mode.SRC_ATOP);
            } else {
                is_pushed_mar = false;
                day_week[1] = "_";
                choose_mar_btn.getBackground().setColorFilter(Color.parseColor("#85A4AE"), PorterDuff.Mode.SRC_ATOP);
            }
        } else if (v.getId() == R.id.btn_mer) {
            if (!is_pushed_mer) {
                is_pushed_mer = true;
                day_week[2] = "M";
                //Log.d("Mercredi c'est bon " + day_week[0], "success");
                choose_mer_btn.getBackground().setColorFilter(Color.parseColor("#ACED9A"), PorterDuff.Mode.SRC_ATOP);
            } else {
                is_pushed_mer = false;
                day_week[2] = "_";
                choose_mer_btn.getBackground().setColorFilter(Color.parseColor("#85A4AE"), PorterDuff.Mode.SRC_ATOP);
            }
        } else if (v.getId() == R.id.btn_jeu) {
            if (!is_pushed_jeu) {
                is_pushed_jeu = true;
                day_week[3] = "J";
                //Log.d("Jeudi c'est bon " + day_week[0], "success");
                choose_jeu_btn.getBackground().setColorFilter(Color.parseColor("#ACED9A"), PorterDuff.Mode.SRC_ATOP);
            } else {
                is_pushed_jeu = false;
                day_week[3] = "_";
                choose_jeu_btn.getBackground().setColorFilter(Color.parseColor("#85A4AE"), PorterDuff.Mode.SRC_ATOP);
            }
        } else if (v.getId() == R.id.btn_ven) {
            if (!is_pushed_ven) {
                is_pushed_ven = true;
                day_week[4] = "V";
                //Log.d("Vendredi c'est bon " + day_week[0], "success");
                choose_ven_btn.getBackground().setColorFilter(Color.parseColor("#ACED9A"), PorterDuff.Mode.SRC_ATOP);
            } else {
                is_pushed_ven = false;
                day_week[4] = "_";
                choose_ven_btn.getBackground().setColorFilter(Color.parseColor("#85A4AE"), PorterDuff.Mode.SRC_ATOP);
            }
        } else if (v.getId() == R.id.btn_sam) {
            if (!is_pushed_sam) {
                is_pushed_sam = true;
                day_week[5] = "S";
                //Log.d("Samedi c'est bon " + day_week[0], "success");
                choose_sam_btn.getBackground().setColorFilter(Color.parseColor("#ACED9A"), PorterDuff.Mode.SRC_ATOP);
            } else {
                is_pushed_sam = false;
                day_week[5] = "_";
                choose_sam_btn.getBackground().setColorFilter(Color.parseColor("#85A4AE"), PorterDuff.Mode.SRC_ATOP);
            }
        } else if (v.getId() == R.id.btn_dim) {
            if (!is_pushed_dim) {
                is_pushed_dim = true;
                day_week[6] = "D";
                //Log.d("Dimanche c'est bon " + day_week[0], "success");
                choose_dim_btn.getBackground().setColorFilter(Color.parseColor("#ACED9A"), PorterDuff.Mode.SRC_ATOP);
            } else {
                is_pushed_dim = false;
                day_week[6] = "_";
                choose_dim_btn.getBackground().setColorFilter(Color.parseColor("#85A4AE"), PorterDuff.Mode.SRC_ATOP);
            }
        } else if (v.getId() == R.id.btn_create_task) {
            for(int i = 0; i < 7; i++) {
                if(TextUtils.isEmpty(day_week[i])) day_week[i] = "_";
            }
            day_week_task = TextUtils.join("", day_week);
            Log.d("" + day_week_task, "SUCCESS");

            if(duration_task == null || hour_task == null || date_task == null) {
                Toast.makeText(this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
            }
            else {
                if(number_rem_task == null) {
                    number_rem_task = 1;
                }
                if(interval_task == null) {
                    interval_task = "5";
                }

                Task task_child = new Task(String.valueOf(et_name_task.getText()), duration_task, recurr_task, hour_task, number_rem_task, interval_task, type_task, user_id, day_week_task, date_task);
                bdd.CreateTask(task_child);
                finish();
            }
        }
    }
}