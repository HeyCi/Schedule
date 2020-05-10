package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

public class AdultSchedule extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rw_sched;
    ArrayList<Task> taskList;
    KidTaskAdapter adapter;
    Button btn_add;
    Button btn_nxt;
    Button btn_prec;
    Button btn_stat;
    TextView txt_jour;
    TextView txt_date;
    Calendar calendar;
    Database bdd;
    String childId;
    SimpleDateFormat monformat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult_schedule);
        rw_sched = findViewById(R.id.rw_kidsched);
        btn_add = findViewById(R.id.btn_add_task);
        btn_nxt = findViewById(R.id.btn_nxt);
        btn_prec = findViewById(R.id.btn_prec);
        btn_stat = findViewById(R.id.btn_stat);
        txt_jour = findViewById(R.id.txt_jour);
        txt_date = findViewById(R.id.txt_date);
        calendar = Calendar.getInstance();

        monformat = new SimpleDateFormat("dd/MM/yyyy");
        txt_jour.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
        txt_date.setText(monformat.format(calendar.getTime()));

        bdd = new Database();
        taskList = new ArrayList<>();
        adapter = new KidTaskAdapter(taskList);

        childId = getIntent().getStringExtra("kidtel");

        rw_sched.setAdapter(adapter);
        rw_sched.setLayoutManager(new LinearLayoutManager(this));

        btn_add.setOnClickListener(this);
        btn_nxt.setOnClickListener(this);
        btn_prec.setOnClickListener(this);
        btn_stat.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getBddData();
    }

    @Override
    public void onClick(View view) {
        if(view == btn_add){
            Intent intent = new Intent(this, TaskCreation.class);
            intent.putExtra("kidtel", childId);
            startActivity(intent);
        } else if(view == btn_nxt) {
            calendar.add(Calendar.DATE, 1);
            txt_jour.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
            txt_date.setText(monformat.format(calendar.getTime()));
            getBddData();
        } else if (view == btn_prec) {
            calendar.add(Calendar.DATE, -1);
            txt_jour.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
            txt_date.setText(monformat.format(calendar.getTime()));
            getBddData();
        } else if (view == btn_stat) {
            Intent intent = new Intent(this, StatChild.class);
            intent.putExtra("kidtel", childId);
            startActivity(intent);
        }
    }

    public void getBddData() {
        taskList.clear();
        bdd.readData(bdd.getUserRef().child(childId).child("Task"), new OnGetDataListener() {
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
                        /*taskList.add(dataSnapshot.getValue(Task.class));
                        for (Task matask : taskList) {
                            Log.d("tache", matask.getName_task());
                        }*/
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
}
