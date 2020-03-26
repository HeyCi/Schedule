package org.demo.schedule;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {
    public void CreateUser(User user){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("User");
        userRef.setValue(user);
    }

    public void CreateTask(Task task){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("Task");
        userRef.setValue(task);
    }

    public Database() {
    }
}
