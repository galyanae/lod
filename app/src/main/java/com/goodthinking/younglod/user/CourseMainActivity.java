package com.goodthinking.younglod.user;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.goodthinking.younglod.user.model.Course;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CourseMainActivity extends AppCompatActivity {

    private RecyclerView CourseRecyclerView;
    private CourseAdapter CourseAdapter;
    private FirebaseAuth auth;
    private DatabaseReference Coursedatabase, MyCoursetabase;
    private String flag_to_myCourses;

    private String role = "user";
    private boolean isManager = false;
    private Query queryRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        try {
            role = getIntent().getExtras().getString("Role");
        } catch (Exception e) {
            role = "user";
        }
        if (role.equals("manager")) isManager = true;
        System.out.println("UserActivity: Am I a manager? " + isManager);
        CourseRecyclerView = (RecyclerView) findViewById(R.id.CourseRecyclerView);

        try {
            role = getIntent().getExtras().getString("Role");
        } catch (Exception e) {
            role = "user";
        }
        if (role.equals("manager")) {
            isManager = true;
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setVisibility(View.VISIBLE);
        }

        System.out.println("Am I a manager? " + isManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        CourseRecyclerView.setLayoutManager(linearLayoutManager);
        CourseAdapter = new CourseAdapter(getApplicationContext());
        CourseRecyclerView.setAdapter(CourseAdapter);
        RefreshallCourses();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void RefreshallCourses() {

        Coursedatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference refCourses = Coursedatabase.child("Tables").child("Courses");
        queryRef = refCourses.orderByChild("statusIsValidDate").startAt("1");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //EventToArrayData(dataSnapshot);
                CourseArrayData.getInstance().getCourses().clear();
                System.out.println("number of course=" + dataSnapshot.getChildrenCount());
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Course course = data.getValue(Course.class);
                    course.setKey(data.getKey());
                    CourseArrayData.getInstance().getCourses().add(course);
                }
                CourseAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
