package org.demo.schedule;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class KidSchedule extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rw_sched;
    ArrayList<Task> taskList;
    KidTaskAdapter adapter;
    Button btn_next;
    TextView txt_jour;
    Calendar calendar;
    Database bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_schedule);
        rw_sched = findViewById(R.id.rw_kidsched);
        btn_next = findViewById(R.id.btn_nxt);
        txt_jour = findViewById(R.id.txt_jour);
        calendar = Calendar.getInstance();

        txt_jour.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));

        bdd = new Database();
        taskList = new ArrayList<>();
        adapter = new KidTaskAdapter(taskList);

        rw_sched.setAdapter(adapter);
        rw_sched.setLayoutManager(new LinearLayoutManager(this));

        btn_next.setOnClickListener(this);

        bdd.readData(bdd.getUserRef().child("0659025246").child("Task"), new OnGetDataListener() {
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
                        //task.setHour(snapshot.child("hour").getValue().toString());
                        taskList.add(task);
                    }
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
    public void onClick(View view) {
        Task tache = new Task("mettre ses chaussures");
        taskList.add(0, tache);
        Toast.makeText(this, "ajout de ma tache", Toast.LENGTH_LONG).show();
        adapter.notifyDataSetChanged();
    }
}
