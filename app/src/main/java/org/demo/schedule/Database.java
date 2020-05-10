package org.demo.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public void CreateUserChild(final User user, final String childNumber){
        CreateUserParent(user);
        userRef.child(user.getPhoneNumber()).child("Parent").setValue(user.getParent());
        userRef.child(user.getParent()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               if(dataSnapshot.child("Enfant").exists()){
                    String c = dataSnapshot.child("Enfant").getValue().toString();
                    userRef.child(user.getParent()).child("Enfant").setValue(c + "|" + childNumber);
                } else {
                    userRef.child(user.getParent()).child("Enfant").setValue(childNumber);
                }

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
        taskRef.child("reminderInterval").setValue(task.getReminderInterval());
        taskRef.child("type").setValue(task.getType());
        taskRef.child("childId").setValue(task.getChildId());
        taskRef.child("dayOfTheWeek").setValue(task.getDayOfTheWeek());
        taskRef.child("date").setValue(task.getDate());
    }

    public void CreateTaskSuccess(Task task){
        DatabaseReference taskRef = userRef.child(task.getChildId()).child("TaskSuccess").child(task.getName_task());
        taskRef.child("name_task").setValue(task.getName_task());
        taskRef.child("duration").setValue(task.getDuration());
        taskRef.child("recurrence").setValue(task.getRecurrence());
        taskRef.child("hour").setValue(task.getHour());
        taskRef.child("numberOfReminder").setValue(task.getNumberOfReminder());
        taskRef.child("reminderInterval").setValue(task.getReminderInterval());
        taskRef.child("type").setValue(task.getType());
        taskRef.child("childId").setValue(task.getChildId());
        taskRef.child("dayOfTheWeek").setValue(task.getDayOfTheWeek());
        taskRef.child("date").setValue(task.getDate());
        String dayOfTheWeek = task.getDayOfTheWeek();
        if(task.getDayOfTheWeek() == "_______"){
            userRef.child(task.getChildId()).child("Task").child(task.getName_task()).removeValue();
        }
        else{
            SimpleDateFormat sdf;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            if (day == 1) day = 7;
            else day --;
            int nextDay = 0;
            for (int i = day+1; i <= 7; i++) {
                nextDay++;
                if(dayOfTheWeek.charAt(i) != '_'){
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    task.setDate(sdf.format(new Date(System.currentTimeMillis()+(86400000 * nextDay))));
                    CreateTask(task);
                    return;

                }
            }
            for (int i = 0; i <= day; i++) {
                nextDay++;
                if(dayOfTheWeek.charAt(i) != '_'){
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    task.setDate(sdf.format(new Date(System.currentTimeMillis()+(86400000 * nextDay))));
                    CreateTask(task);
                    return;
                }
            }
        }
    }

    public void CreateTaskFailed(Task task){
        DatabaseReference taskRef = userRef.child(task.getChildId()).child("TaskFailed").child(task.getName_task());
        taskRef.child("name_task").setValue(task.getName_task());
        taskRef.child("duration").setValue(task.getDuration());
        taskRef.child("recurrence").setValue(task.getRecurrence());
        taskRef.child("hour").setValue(task.getHour());
        taskRef.child("numberOfReminder").setValue(task.getNumberOfReminder());
        taskRef.child("reminderInterval").setValue(task.getReminderInterval());
        taskRef.child("type").setValue(task.getType());
        taskRef.child("childId").setValue(task.getChildId());
        taskRef.child("dayOfTheWeek").setValue(task.getDayOfTheWeek());
        taskRef.child("date").setValue(task.getDate());
        String dayOfTheWeek = task.getDayOfTheWeek();
        if(task.getDayOfTheWeek() != "_______"){
            userRef.child(task.getChildId()).child("Task").child(task.getName_task()).removeValue();
        }
        else{
            SimpleDateFormat sdf;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            int nextDay = 0;
            for (int i = day+1; i <= 7; i++) {
                nextDay++;
                if(dayOfTheWeek.charAt(i) == '_'){
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    task.setDayOfTheWeek(sdf.format(new Date(System.currentTimeMillis()+(86400000 * nextDay))));
                    CreateTask(task);
                    return;

                }
            }
            for (int i = 0; i <= day; i++) {
                nextDay++;
                if(dayOfTheWeek.charAt(i) == '_'){
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    task.setDayOfTheWeek(sdf.format(new Date(System.currentTimeMillis()+(86400000 * nextDay))));
                    CreateTask(task);
                    return;
                }
            }
        }
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

    public void setAlarmManager(final String childNumber, final Context context){
        final List<Task> taskList = new ArrayList<Task>();
        readData(userRef, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                SimpleDateFormat sdf;
                String date, hour;
                String nextDate = null, nextHour = null;
                String name = null;
                Date nextTask = null;
                int dateI, hourI, dateNow, hourNow;
                int dateS = 99999999;
                int hourS = 9999;

                Date today = new Date();
                sdf = new SimpleDateFormat("yyyyMMdd");
                dateNow = Integer.parseInt(sdf.format(today));
                sdf = new SimpleDateFormat("HHmm");
                hourNow = Integer.parseInt(sdf.format(today));

                for(DataSnapshot data: dataSnapshot.child(childNumber).child("Task").getChildren()){
                    taskList.add(data.getValue(Task.class));
                    date = data.child("date").getValue().toString();
                    hour = data.child("hour").getValue().toString();
                    dateI = Integer.parseInt(date.replaceAll("-", ""));
                    hourI = Integer.parseInt(hour.replaceAll(":", ""));

                    if((dateS > dateI || (dateS == dateI && hourS > hourI)) && (dateI > dateNow || (dateI == dateNow && hourI > hourNow))){
                        dateS = dateI;
                        hourS = hourI;
                        nextDate = date;
                        nextHour = hour;
                        name = data.child("name_task").getValue().toString();
                    }
                }
                if(name != null) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        nextTask = sdf.parse(nextDate + " " + nextHour + ":00");
                    } catch (Exception e) {
                    }
                    long millis = nextTask.getTime();
                    SharedPreferences prefs = context.getSharedPreferences("task", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("name", name);
                    editor.commit();
                    Alarm alarm = new Alarm();
                    alarm.setAlarm(context, millis);
                }
            }

            @Override
            public void onStart() {Log.d("ONSTART", "Started");}

            @Override
            public void onFailure() {Log.d("onFailure", "Failed");}
        });
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