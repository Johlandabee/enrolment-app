package de.thenutheads.jlndbe.enrolmentapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EditSubjectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(de.thenutheads.jlndbe.schoolregistration.R.layout.activity_edit_subject);

        Spinner gradeSpinner = (Spinner) findViewById(de.thenutheads.jlndbe.schoolregistration.R.id.sGradeSpinner);

        gradeSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Subject.Grade.values()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(de.thenutheads.jlndbe.schoolregistration.R.menu.menu_edit_subject, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == de.thenutheads.jlndbe.schoolregistration.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
