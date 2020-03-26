package org.demo.schedule;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {
    private FirebaseDatabase database;
    private DatabaseReference userRef;

    public void CreateUserParent(User user){
        userRef.child(user.getPhoneNumber()).child("FirstName").setValue(user.getFirstName());
        userRef.child(user.getPhoneNumber()).child("LastName").setValue(user.getLastName());
        userRef.child(user.getPhoneNumber()).child("PhoneNumber").setValue(user.getPhoneNumber());
        userRef.child(user.getPhoneNumber()).child("Type").setValue(user.getType());
    }

    public void CreateUserChild(User user){
        CreateUserParent(user);
        userRef.child(user.getPhoneNumber()).child("Parent").setValue(user.getParent());
    }

    public void CreateTask(Task task){
        DatabaseReference taskRef = userRef.child(task.getChildId()).child("Task").child(task.getName_task());
        taskRef.child("name_task").setValue(task.getName_task());
        taskRef.child("duration").setValue(task.getDuration());
        taskRef.child("recurrence").setValue(task.getRecurrence());
        taskRef.child("hour").setValue(task.getHour());
        taskRef.child("numberOfReminder").setValue(task.getNumberOfReminder());
        taskRef.child("type").setValue(task.getType());
        taskRef.child("childId").setValue(task.getChildId());
        taskRef.child("dayOfTheWeek").setValue(task.getDayOfTheWeek());
}

    public Database() {
        database =  FirebaseDatabase.getInstance();
        userRef = database.getReference("User");
    }
}
