package com.goodthinking.younglod.user;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class EventAddNew_Firebase extends AppCompatActivity {

    private EditText AddEventHeadline, AddEventSynopsys, AddEventInfo, AddEventParticipatorsno, AddEventHostName;
    private TextView AddEventdate, AddEventtime;
    private Button AddEventbtn,EditEventbtn;
    private DatabaseReference Eventdatabase;
    private ProgressDialog progressDialog;
    private int position;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private String key;

    private String timeEvent;
    private String dateEvent;
    private CheckBox eventIsNotValid;
    private CheckBox eventIsClosed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add_new__firebase);

        progressDialog = new ProgressDialog(this);

        AddEventHeadline = (EditText) findViewById(R.id.fbAddEventHeadlineview);
        AddEventdate = (TextView) findViewById(R.id.fbAddEventdateview);
        AddEventtime = (TextView) findViewById(R.id.fbAddEventtimeview);
        AddEventSynopsys = (EditText) findViewById(R.id.fbAddEventSynopsysview);
        AddEventInfo = (EditText) findViewById(R.id.fbAddEventInfoview);
        AddEventParticipatorsno = (EditText) findViewById(R.id.fbAddEventParticipatorsno);
        AddEventHostName = (EditText) findViewById(R.id.fbEventhostname);
        AddEventbtn = (Button) findViewById(R.id.fbSaveNewEventbtn);
        EditEventbtn= (Button) findViewById(R.id.fbEditEventbtn);
        eventIsNotValid = (CheckBox) findViewById(R.id.editEventIsValidCheckBox);
        eventIsClosed = (CheckBox) findViewById(R.id.editEventIsClosedCheckBox);


        if (getIntent().getExtras() != null) {
            Intent intent = getIntent();
            key = intent.getStringExtra("Eventkey");
            position = intent.getIntExtra("position", 0);

            // Bundle extras = getIntent().getExtras();
            // Event event=(Event)extras.get(

            // Event event = (Event) intent.getSerializableExtra("Event");
         //   if (key != " ") {
            if (!key.equals("")) {
                AddEventHeadline.setText(EventArraydata.getInstance().getEvents().get(position).getEventName());
                AddEventdate.setText(EventArraydata.getInstance().getEvents().get(position).getEventDate());
                AddEventtime.setText(EventArraydata.getInstance().getEvents().get(position).getEventTime());
                AddEventSynopsys.setText(EventArraydata.getInstance().getEvents().get(position).getEventSynopsys());
                AddEventInfo.setText(EventArraydata.getInstance().getEvents().get(position).getEventInformation());
                AddEventParticipatorsno.setText(String.valueOf(EventArraydata.getInstance().getEvents().get(position).getEventParticipatorsno()));
                AddEventHostName.setText(EventArraydata.getInstance().getEvents().get(position).getEventHost());
                AddEventbtn.setEnabled(false);
            }
            else{
                EditEventbtn.setEnabled(false);
            }

        }

        Eventdatabase = FirebaseDatabase.getInstance().getReference();
        setDateField();
        setTimeField();

    }

    private void setDateField() {
        AddEventdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentdate = Calendar.getInstance();
                mYear = mCurrentdate.get(Calendar.YEAR);
                mMonth = mCurrentdate.get(Calendar.MONTH);
                mDay = mCurrentdate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(EventAddNew_Firebase.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                monthOfYear++;
                               // AddEventdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                AddEventdate.setText(String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear, year));
                                dateEvent = String.format("%04d-%02d-%02d", year, monthOfYear, dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
            }


        });
    }

    private void setTimeField() {
        AddEventtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                mHour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                mMinute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EventAddNew_Firebase.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                      //  AddEventtime.setText(selectedHour + ":" + selectedMinute);
                        timeEvent = String.format("%02d:%02d", selectedHour, selectedMinute);
                        AddEventtime.setText(timeEvent);
                    }
                }, mHour, mMinute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

    }


    public void SaveNewEventbtn(View view) {
        DatabaseReference newEvent = Eventdatabase.child("Tables").child("Events");
        key = newEvent.push().getKey();

        String EventHeadLine = AddEventHeadline.getText().toString().trim();
        String Eventdate = AddEventdate.getText().toString().trim();
        String Eventtime = AddEventtime.getText().toString().trim();
        String EventSynopsys = AddEventSynopsys.getText().toString().trim();
        String EventInfo = AddEventInfo.getText().toString().trim();
        String EventHost = AddEventHostName.getText().toString().trim();
        int MaxNoOfParticipetors = AddEventParticipatorsno.getText().toString().equals("") ? 0 : Integer.parseInt(AddEventParticipatorsno.getText().toString());

        Boolean EventIsNotValid = eventIsNotValid.isChecked();
        Boolean EventIsClosed = eventIsClosed.isChecked();

        String StatusIsValidDate;

        if (eventIsNotValid.isChecked() == true) {
            StatusIsValidDate = "0-"+dateEvent.trim();

        }
        else {
            StatusIsValidDate = "1-"+dateEvent.trim();
        }

        Event event = new Event(EventHeadLine, Eventdate, Eventtime,
                EventSynopsys, EventInfo, EventHost, EventIsNotValid, MaxNoOfParticipetors, EventIsClosed, StatusIsValidDate);
        event.setKey(key);
        //Map<String, Object> eventnew = new HashMap<>();
        //eventnew.put(key, event.Objecttofirebase());

        progressDialog.setMessage("Adding new Event...");
        progressDialog.show();

        newEvent.child(key).setValue(event, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Toast.makeText(getApplicationContext(), "Creation failed" + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "successfully created", Toast.LENGTH_LONG).show();
                    finish();
                }
                progressDialog.dismiss();
            }
        });

    }

    public void CancelAddEventbtn(View view) {
        Intent intent=new Intent(getApplicationContext(),EventRecyclerview_Firebase.class);
        startActivity(intent);
        finish();
    }



}
