package de.thenutheads.jlndbe.enrolmentapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
public class DashboardActivity extends Activity {

    public static final int ADD_STUDENT_REQUEST = 0x2A,
            EDIT_STUDENT_REQUEST = 0x2B;

    public static final String SAVE_FILE = "enrolments.json",
            PARCELABLE_STUDENT_OBJECT = "obj_parcelable_student";

    private Context _context;
    private ArrayList<Student> _students;
    private StudentAdapter _studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(de.thenutheads.jlndbe.enrolmentapp.R.layout.activity_dashboard);

        _context = DashboardActivity.this;

        loadStudentList();

        _studentAdapter = new StudentAdapter(this, new ArrayList<Student>());
        ((ListView) findViewById(R.id.studentListView)).setAdapter(_studentAdapter);

    }

    @Override
    protected void onDestroy() {
        saveStudentList();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(de.thenutheads.jlndbe.enrolmentapp.R.menu.menu_dashboard, menu);
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
            case de.thenutheads.jlndbe.enrolmentapp.R.id.action_new_registration:
                startActivityForResult(new Intent(this, EditStudentActivity.class), ADD_STUDENT_REQUEST);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case ADD_STUDENT_REQUEST:
                if (resultCode == RESULT_OK && data != null)
                    addStudentToList((Student) data.getParcelableExtra(PARCELABLE_STUDENT_OBJECT));
                break;
            default:
                break;
        }
    }

    public void onClickRemoveStudent(View v) {
        View parent = (View) v.getParent();
        _studentAdapter.remove(_studentAdapter.getItem(((ListView) parent.getParent())
                .getPositionForView(parent)));
    }

    public void onClickEditStudent(View v) {
        // TODO:
    }

    private void addStudentToList(Student student) {
        _studentAdapter.add(student);
    }

    private void loadStudentList() {
        // TODO: JSON-Serializer
        /**
         FileInputStream fileIn;
         ObjectInputStream objectIn;

         try {

         fileIn = _context.openFileInput(SAVE_FILE);
         objectIn = new ObjectInputStream(fileIn);

         _students = (ArrayList<Student>) objectIn.readObject();

         } catch (FileNotFoundException e) {
         Toast.makeText(_context, de.thenutheads.jlndbe.enrolmentapp.R.string.toast_save_file_not_found, Toast.LENGTH_SHORT).show();
         e.printStackTrace();
         } catch (Exception e) {
         e.printStackTrace();
         }
         **/
    }

    private void saveStudentList() {
        /** TODO: JSON-Serializer **/
        /**
         if (_students == null || _students.isEmpty()) return;

         FileOutputStream fileOut;
         ObjectOutputStream objectOut;

         try {

         fileOut = _context.openFileOutput(SAVE_FILE, Context.MODE_PRIVATE);
         objectOut = new ObjectOutputStream(fileOut);
         objectOut.writeObject(_students);
         objectOut.close();
         fileOut.close();

         } catch (Exception e) {
         Toast.makeText(_context, de.thenutheads.jlndbe.enrolmentapp.R.string.toast_save_failed, Toast.LENGTH_SHORT).show();
         e.printStackTrace();
         }
         */
    }
}
