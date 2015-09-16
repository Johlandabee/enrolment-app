package de.thenutheads.jlndbe.enrolmentapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class EditStudentActivity extends Activity {

    private EditText _dobEdit, _nameEdit, _firstNameEdit;
    private DatePickerDialog _dobDialog;
    private SimpleDateFormat _dobFormat;

    private SubjectAdapter _subjectAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(de.thenutheads.jlndbe.schoolregistration.R.layout.activity_add_registration);

        setDobDialog();

        _nameEdit = (EditText) findViewById(de.thenutheads.jlndbe.schoolregistration.R.id.editName);
        _firstNameEdit = (EditText) findViewById(de.thenutheads.jlndbe.schoolregistration.R.id.editFirstName);

        _subjectAdapter = new SubjectAdapter(this, new ArrayList<Subject>());
        ((ListView) findViewById(de.thenutheads.jlndbe.schoolregistration.R.id.sListView)).setAdapter(_subjectAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(de.thenutheads.jlndbe.schoolregistration.R.menu.menu_add_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case de.thenutheads.jlndbe.schoolregistration.R.id.action_settings:
                return true;
            case de.thenutheads.jlndbe.schoolregistration.R.id.action_save_registration:
                validateForm();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setDobDialog(){
        Calendar dobCalendar = Calendar.getInstance();
        _dobFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        _dobEdit = (EditText)findViewById(de.thenutheads.jlndbe.schoolregistration.R.id.editDateOfBirth);

        _dobDialog = new DatePickerDialog(EditStudentActivity.this ,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar date = Calendar.getInstance();
                date.set(year, monthOfYear, dayOfMonth);
                _dobEdit.setText(_dobFormat.format(date.getTime()));
            }
        }, dobCalendar.get(Calendar.YEAR), dobCalendar.get(Calendar.MONTH), dobCalendar.get(Calendar.DAY_OF_MONTH));

        _dobEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _dobDialog.show();
            }
        });
    }

    public void onClickEditSubject(View v){

    }

    public void validateForm(){
        if(_dobEdit.getText().toString().matches("") || _nameEdit.getText().toString().matches("") ||
                _firstNameEdit.getText().toString().matches("") || _subjectAdapter.getCount() == 0){

            Toast.makeText(EditStudentActivity.this, de.thenutheads.jlndbe.schoolregistration.R.string.add_student_not_valid_toast, Toast.LENGTH_LONG).show();
            return;
        }

        finish();
    }

    public void onClickAddSubject(View v){
        _subjectAdapter.add(new Subject(getString(de.thenutheads.jlndbe.schoolregistration.R.string.default_subject_name), Subject.Grade.A));
    }

    public void onClickRemoveSubject(View v){
        View parent = (View) v.getParent();
        _subjectAdapter.remove(_subjectAdapter.getItem(((ListView) parent.getParent())
                .getPositionForView(parent)));
    }
}
