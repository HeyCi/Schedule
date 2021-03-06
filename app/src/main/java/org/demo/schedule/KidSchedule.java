package org.demo.schedule;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

public class KidSchedule extends AppCompatActivity implements View.OnClickListener, OnTaskClickListener {

    RecyclerView rw_sched;
    ArrayList<Task> taskList;
    KidTaskAdapter adapter;
    Button btn_next;
    Button btn_prec;
    TextView txt_jour;
    TextView txt_date;
    Calendar calendar;
    Database bdd;
    String userId;
    SimpleDateFormat monformat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_schedule);
        rw_sched = findViewById(R.id.rw_kidsched);
        btn_next = findViewById(R.id.btn_nxt);
        btn_prec = findViewById(R.id.btn_prec);
        txt_jour = findViewById(R.id.txt_jour);
        txt_date = findViewById(R.id.txt_date);
        calendar = Calendar.getInstance();

        userId = getIntent().getStringExtra("userID");

        monformat = new SimpleDateFormat("dd/MM/yyyy");
        txt_jour.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
        txt_date.setText(monformat.format(calendar.getTime()));

        bdd = new Database();
        taskList = new ArrayList<>();
        adapter = new KidTaskAdapter(taskList);
        adapter.setOnTaskClickListener(this);

        rw_sched.setAdapter(adapter);
        rw_sched.setLayoutManager(new LinearLayoutManager(this));

        btn_next.setOnClickListener(this);
        btn_prec.setOnClickListener(this);

        getBddData();
    }


    @Override
    public void onClick(View view) {
        if(view == btn_next) {
            calendar.add(Calendar.DATE, 1);
            txt_jour.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
            txt_date.setText(monformat.format(calendar.getTime()));
            getBddData();
        } else if (view == btn_prec) {
            calendar.add(Calendar.DATE, -1);
            txt_jour.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
            txt_date.setText(monformat.format(calendar.getTime()));
            getBddData();
        }
    }

    public void getBddData() {
        taskList.clear();
        bdd.readData(bdd.getUserRef().child(userId).child("Task"), new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()
                ) {
                    String dateString = snapshot.child("date").getValue().toString();
                    String[] dateSplit = dateString.split("-");
                    Calendar taskCalendar = Calendar.getInstance();
                    taskCalendar.set(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1])-1, Integer.parseInt(dateSplit[2]));
                    if(taskCalendar.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                        Task task = new Task(snapshot.child("name_task").getValue().toString());
                        task.setHour(snapshot.child("hour").getValue().toString());
                        task.setType(snapshot.child("type").getValue().toString());
                        taskList.add(task);
                    }
                    Collections.sort(taskList);
                    adapter.notifyDataSetChanged();
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

    @Override
    public void onTaskClick(Task task, int position) {
        Intent intent = new Intent(this, KidTaskActivity.class);
        SharedPreferences prefs = this.getSharedPreferences("task", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", task.getName_task());
        editor.commit();
        startActivity(intent);
    }
}
