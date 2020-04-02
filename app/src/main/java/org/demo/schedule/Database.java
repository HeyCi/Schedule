package org.demo.schedule;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

interface OnGetDataListener {
    void onSuccess(DataSnapshot dataSnapshot);
    void onStart();
    void onFailure();
}

public class Database {
    private final FirebaseDatabase database;
    private final DatabaseReference userRef;

    public DatabaseReference getUserRef() {
        return userRef;
    }

    public void readData(DatabaseReference ref, final OnGetDataListener listener) {
        listener.onStart();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                listener.onFailure();
            }
        });
    }

    public void CreateUserParent(User user){
        userRef.child(user.getPhoneNumber()).child("FirstName").setValue(user.getFirstName());
        userRef.child(user.getPhoneNumber()).child("LastName").setValue(user.getLastName());
        userRef.child(user.getPhoneNumber()).child("PhoneNumber").setValue(user.getPhoneNumber());
        userRef.child(user.getPhoneNumber()).child("Type").setValue(user.getType());
    }

    public void CreateUserChild(final User user){
        CreateUserParent(user);
        userRef.child(user.getPhoneNumber()).child("Parent").setValue(user.getParent());
        userRef.child(user.getParent()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String child = dataSnapshot.child("Enfant").getValue().toString();
                if(child == null) userRef.child(user.getParent()).child("Enfant").setValue(user.getPhoneNumber());
                else userRef.child(user.getParent()).child("Enfant").setValue(child + "|" + user.getPhoneNumber());
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("APPX", "Failed to read value.", error.toException());
            }
        });
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

    public User GetUser(String id){
        DatabaseReference ref = userRef.child(id);
        final User userToSend = new User();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                userToSend.setFirstName(dataSnapshot.child("FirstName").toString());
                userToSend.setLastName(dataSnapshot.child("LastName").toString());
                userToSend.setType(dataSnapshot.child("Type").toString());
                userToSend.setPhoneNumber(dataSnapshot.child("PhoneNumber").toString());
                if(userToSend.getType() == "Enfant") userToSend.setParent(dataSnapshot.child("Parent").toString());
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("APPX", "Failed to read value.", error.toException());
            }
        });
        return userToSend;
    }
    public Database() {
        database =  FirebaseDatabase.getInstance();
        userRef = database.getReference("User");
    }
}


/*db.readData(db.getUserRef.child("userid"), new OnGetDataListener() {
                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {

               //got data from database....now you can use the retrieved data


                }
                @Override
                public void onStart() {
                    //when starting
                    Log.d("ONSTART", "Started");
                }

                @Override
                public void onFailure() {
                    Log.d("onFailure", "Failed");
                }
            });*/