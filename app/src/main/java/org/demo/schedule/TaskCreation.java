package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.TimePicker;

public class TaskCreation extends AppCompatActivity {

    Button choose_btn;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);

        choose_btn = findViewById(R.id.btn_hr);
        choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(TaskCreation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        choose_btn.setText(hourOfDay + ":" + minute);
                    }
                }, 0, 0, false);

                timePickerDialog.show();
            }
        });

    }

}
