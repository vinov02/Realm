package com.example.realm;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;

public class CourseUpdate extends AppCompatActivity {


    private EditText courseNameEdt, courseDurationEdt, courseDescriptionEdt, courseTracksEdt;


    private String courseName, courseDuration, courseDescription, courseTracks;
    private long id;
    private Button updateCourseBtn ,delete;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_update);


        realm = Realm.getDefaultInstance();
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);
        delete = findViewById(R.id.idBtnDeleteCourse);


        courseName = getIntent().getStringExtra("courseName");
        courseDuration = getIntent().getStringExtra("courseDuration");
        courseDescription = getIntent().getStringExtra("courseDescription");
        courseTracks = getIntent().getStringExtra("courseTracks");
        id = getIntent().getLongExtra("id", 0);

        courseNameEdt.setText(courseName);
        courseDurationEdt.setText(courseDuration);
        courseDescriptionEdt.setText(courseDescription);
        courseTracksEdt.setText(courseTracks);


        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseName = courseNameEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String courseTracks = courseTracksEdt.getText().toString();


                if (TextUtils.isEmpty(courseName)) {
                    courseNameEdt.setError("Please enter Course Name");
                } else if (TextUtils.isEmpty(courseDescription)) {
                    courseDescriptionEdt.setError("Please enter Course Description");
                } else if (TextUtils.isEmpty(courseDuration)) {
                    courseDurationEdt.setError("Please enter Course Duration");
                } else if (TextUtils.isEmpty(courseTracks)) {
                    courseTracksEdt.setError("Please enter Course Tracks");
                } else {

                    final DataModel model = realm.where(DataModel.class).equalTo("id", id).findFirst();
                    updateCourse(model, courseName, courseDescription, courseDuration, courseTracks);
                }


                Toast.makeText(CourseUpdate.this, "Course Updated.", Toast.LENGTH_SHORT).show();


                Intent i = new Intent(CourseUpdate.this, Display.class);
                startActivity(i);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteCourse(id);
                Toast.makeText(CourseUpdate.this, "Course Deleted.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CourseUpdate.this, Display.class);
                startActivity(i);
                finish();
            }
        });
    }


    private void deleteCourse(long id) {

        DataModel model = realm.where(DataModel.class).equalTo("id", id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm();
            }
        });
    }


    private void updateCourse(DataModel model, String courseName, String courseDescription, String courseDuration, String courseTracks) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.setCourseDescription(courseDescription);
                model.setCourseName(courseName);
                model.setCourseDuration(courseDuration);
                model.setCourseTracks(courseTracks);
                realm.copyToRealmOrUpdate(model);
            }
        });
    }
}
