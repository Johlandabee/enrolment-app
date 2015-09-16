package de.thenutheads.jlndbe.enrolmentapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DashboardActivity extends Activity {

    public static final int ADD_STUDENT_REQUEST = 0x539;

    private static ArrayList<Student> _students;

    private String _saveFile;
    private Context _context;

    private StudentAdapter _studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(de.thenutheads.jlndbe.schoolregistration.R.layout.activity_dashboard);

        _context = getApplicationContext();
        _saveFile = "schoolRegistrations.dat";

        loadStudentList();

        _studentAdapter = new StudentAdapter(this, new ArrayList<Student>());

    }

    @Override
    protected void onDestroy(){
        saveStudentList();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(de.thenutheads.jlndbe.schoolregistration.R.menu.menu_dashboard, menu);
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
            case de.thenutheads.jlndbe.schoolregistration.R.id.action_new_registration:
                startActivityForResult(new Intent(this, EditStudentActivity.class), ADD_STUDENT_REQUEST);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ADD_STUDENT_REQUEST:
                switch (requestCode){
                    case RESULT_OK:
                        if(data != null){

                        }
                        return;
                    case RESULT_CANCELED:
                        return;
                }
            default:
                return;
        }
    }

    private void loadStudentList(){
        FileInputStream fileIn;
        ObjectInputStream objectIn;

        try {

            fileIn = _context.openFileInput(_saveFile);
            objectIn = new ObjectInputStream(fileIn);

            _students = (ArrayList<Student>) objectIn.readObject();

        } catch (FileNotFoundException e){
            Toast.makeText(_context, de.thenutheads.jlndbe.schoolregistration.R.string.save_file_not_found_toast, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> getStudentArrayList(){
        return _students;
    }

    private void saveStudentList(){
        if(_students == null || _students.isEmpty()) return;

        FileOutputStream fileOut;
        ObjectOutputStream objectOut;

        try {

            fileOut = _context.openFileOutput(_saveFile, Context.MODE_PRIVATE);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(_students);
            objectOut.close();
            fileOut.close();

        } catch (Exception e){
            Toast.makeText(_context, de.thenutheads.jlndbe.schoolregistration.R.string.save_failed_toast, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
