package de.thenutheads.jlndbe.enrolmentapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
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

/**
 * Created by Steven Wobser on 08.09.2015
 * <p/>
 * <p/>
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015 Steven Wobser
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class EditStudentActivity extends Activity {

    private EditText _dobEdit, _nameEdit, _firstNameEdit;

    private Calendar _dob;
    private DatePickerDialog _dobDialog;
    private SimpleDateFormat _dobFormat;

    private ArrayList<Subject> _subjects;
    private SubjectAdapter _subjectAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(de.thenutheads.jlndbe.enrolmentapp.R.layout.activity_add_registration);

        setDobDialog();

        _nameEdit = (EditText) findViewById(de.thenutheads.jlndbe.enrolmentapp.R.id.editName);
        _firstNameEdit = (EditText) findViewById(de.thenutheads.jlndbe.enrolmentapp.R.id.editFirstName);

        _subjects = new ArrayList<>();

        _subjectAdapter = new SubjectAdapter(this, _subjects);
        ((ListView) findViewById(de.thenutheads.jlndbe.enrolmentapp.R.id.sListView)).setAdapter(_subjectAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(de.thenutheads.jlndbe.enrolmentapp.R.menu.menu_add_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case de.thenutheads.jlndbe.enrolmentapp.R.id.action_settings:
                return true;
            case de.thenutheads.jlndbe.enrolmentapp.R.id.action_save_registration:
                validateForm();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setDobDialog() {
        Calendar dobCalendar = Calendar.getInstance();
        _dobFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        _dobEdit = (EditText) findViewById(de.thenutheads.jlndbe.enrolmentapp.R.id.editDateOfBirth);

        _dobDialog = new DatePickerDialog(EditStudentActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                _dob = Calendar.getInstance();
                _dob.set(year, monthOfYear, dayOfMonth);
                _dobEdit.setText(_dobFormat.format(_dob.getTime()));
            }
        }, dobCalendar.get(Calendar.YEAR), dobCalendar.get(Calendar.MONTH), dobCalendar.get(Calendar.DAY_OF_MONTH));

        _dobEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _dobDialog.show();
            }
        });
    }

    public void onClickEditSubject(View v) {
        // TODO: Alert dialog...
    }

    public void validateForm() {
        if (_dobEdit.getText().toString().matches("") || _nameEdit.getText().toString().matches("") ||
                _firstNameEdit.getText().toString().matches("") || _subjectAdapter.getCount() == 0 || _dob == null) {

            Toast.makeText(EditStudentActivity.this, de.thenutheads.jlndbe.enrolmentapp.R.string.toast_add_student_not_valid, Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(DashboardActivity.PARCELABLE_STUDENT_OBJECT, new Student(_nameEdit.getText().toString(),
                _firstNameEdit.getText().toString(), _dob, _subjects));

        setResult(RESULT_OK, intent);

        finish();
    }

    public void onClickAddSubject(View v) {
        _subjectAdapter.add(new Subject(getString(de.thenutheads.jlndbe.enrolmentapp.R.string.default_subject_name), Subject.Grade.A));
    }

    public void onClickRemoveSubject(View v) {
        View parent = (View) v.getParent();
        _subjectAdapter.remove(_subjectAdapter.getItem(((ListView) parent.getParent())
                .getPositionForView(parent)));
    }
}
