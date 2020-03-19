package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class KidSchedule extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rw_sched;
    ArrayList<Tache> taskList;
    KidTaskAdapter adapter;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_schedule);
        rw_sched = findViewById(R.id.rw_kidsched);
        btn_next = findViewById(R.id.btn_nxt);

        taskList = new ArrayList<>();
        adapter = new KidTaskAdapter(taskList);

        rw_sched.setAdapter(adapter);
        rw_sched.setLayoutManager(new LinearLayoutManager(this));

        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Tache tache = new Tache("mettre ses chaussures");
        taskList.add(0, tache);
        Toast.makeText(this, "ajout de ma tache", Toast.LENGTH_LONG).show();
        adapter.notifyDataSetChanged();
    }
}
