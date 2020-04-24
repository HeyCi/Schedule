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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdultSchedule extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rw_sched;
    ArrayList<Task> taskList;
    KidTaskAdapter adapter;
    Button btn_add;
    Button btn_nxt;
    Button btn_prec;
    TextView txt_jour;
    Calendar calendar;
    Database bdd;
    String userId;
    List<String> childList;
    String childId;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult_schedule);
        rw_sched = findViewById(R.id.rw_kidsched);
        btn_add = findViewById(R.id.btn_add_task);
        btn_nxt = findViewById(R.id.btn_nxt);
        btn_prec = findViewById(R.id.btn_prec);
        txt_jour = findViewById(R.id.txt_jour);
        calendar = Calendar.getInstance();

        txt_jour.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));

        bdd = new Database();
        taskList = new ArrayList<>();
        adapter = new KidTaskAdapter(taskList);
        childList = new ArrayList<>();

        prefs = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = prefs.getString("tel", null);
        bdd.readData(bdd.getUserRef().child(userId), new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                //childList = Arrays.asList(user.getEnfants());
                Log.d("montest", "liste " + user.getPhoneNumber());
                Log.d("montest", "liste " + Arrays.toString(user.getEnfants()));
                /*childList = (List<String>) dataSnapshot.child("Enfants").getValue();
                Log.d("montest", "liste " + childList);
                childId = childList.get(0);*/
                childId = "0698765432";
                getBddData();
            }

            @Override
            public void onStart() {
                Log.d("ONSTART", "Started");
            }

            @Override
            public void onFailure() {
                Log.d("ONFAIL", "Failed");
            }
        });

        rw_sched.setAdapter(adapter);
        rw_sched.setLayoutManager(new LinearLayoutManager(this));

        btn_add.setOnClickListener(this);
        btn_nxt.setOnClickListener(this);
        btn_prec.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btn_add){
            Intent intent = new Intent(this, TaskCreation.class);
            startActivity(intent);
        } else if(view == btn_nxt) {
            calendar.add(Calendar.DATE, 1);
            txt_jour.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
            getBddData();
        } else if (view == btn_prec) {
            calendar.add(Calendar.DATE, -1);
            txt_jour.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
            getBddData();
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
}
