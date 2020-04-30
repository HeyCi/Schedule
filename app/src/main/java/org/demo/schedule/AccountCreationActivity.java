package org.demo.schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AccountCreationActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rw_kidaccount;
    Button btn_add_kid;
    Button btn_create;
    KidAccountAdapter kidAccountAdapter;
    ArrayList<User> kidList;
    EditText et_firstname_parent;
    EditText et_lastname_parent;
    EditText et_phone_parent;
    Database bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        bdd = new Database();

        rw_kidaccount = findViewById(R.id.rw_kidaccount);
        btn_add_kid = findViewById(R.id.btn_add_kid);
        btn_create = findViewById(R.id.btn_create_accounts);
        et_firstname_parent = findViewById(R.id.et_firstname_parent);
        et_lastname_parent = findViewById(R.id.et_lastname_parent);
        et_phone_parent = findViewById(R.id.et_phone_parent);

        kidList = new ArrayList<>();
        kidAccountAdapter = new KidAccountAdapter(kidList);

        rw_kidaccount.setAdapter(kidAccountAdapter);
        rw_kidaccount.setLayoutManager(new LinearLayoutManager(this));

        btn_create.setOnClickListener(this);
        btn_add_kid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btn_add_kid) {
            User kid = new User("null", "null", "null", "enfant", "null");
            kidList.add(kid);
            kidAccountAdapter.notifyDataSetChanged();
        }
        else if(view == btn_create) {
            User parent = new User(String.valueOf(et_firstname_parent.getText()), String.valueOf(et_lastname_parent.getText()), String.valueOf(et_phone_parent.getText()), "parent");
            bdd.CreateUserParent(parent);
            kidList = kidAccountAdapter.getKids();
            for(User kid : kidList) {
                kid.setParent(parent.getPhoneNumber());
                bdd.CreateUserChild(kid);
            }
        }
    }
}
