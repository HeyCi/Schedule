package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_la;
    Button btn_ac;
    Button btn_sg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_la = findViewById(R.id.btn_la);
        btn_ac = findViewById(R.id.btn_ac);
        btn_sg = findViewById(R.id.btn_sg);

        btn_la.setOnClickListener(this);
        btn_ac.setOnClickListener(this);
        btn_sg.setOnClickListener(this);

        User userTest = new User("nametest", "lastNametest", "0659025247","Parent");
        User userChildTest = new User("child", "lastChild", "0659025246","Enfant", "0659025247");
        Database db = new Database();
        db.GetUser(userTest.getPhoneNumber());
        /*db.CreateUserParent(userTest);
        db.CreateUserChild(userChildTest);
        Task taskTest = new Task("taskTest", null, null, null, 5, "Quotidienne","0659025246","LMMJVSD");
        db.CreateTask(taskTest);*/
    }

    @Override
    public void onClick(View view) {
        if(view == btn_la) {
            Intent intent = new Intent(this, Connexion.class);
            startActivity(intent);
            finish();
        }
        else if(view == btn_ac) {
            Intent intent = new Intent(this, KidSchedule.class);
            startActivity(intent);
            finish();
        }
        else if(view == btn_sg) {
            Intent intent = new Intent(this, TaskCreation.class);
            startActivity(intent);
            finish();
        }
    }
}
