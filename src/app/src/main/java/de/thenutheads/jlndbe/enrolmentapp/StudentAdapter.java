package de.thenutheads.jlndbe.enrolmentapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * Created by Jlndbe on 15.09.2015.
 */
public class StudentAdapter extends ArrayAdapter<Student>{
    public StudentAdapter(Context context, ArrayList<Student> students){
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Student student = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(de.thenutheads.jlndbe.schoolregistration.R.layout.item_student, parent, false);
        }

        ((TextView) convertView.findViewById(de.thenutheads.jlndbe.schoolregistration.R.id.studentFullName)).setText(String.format("%d, %d", student.getName(), student.getFirstName()));
        ((TextView) convertView.findViewById(de.thenutheads.jlndbe.schoolregistration.R.id.studentAge)).setText(String.format("%d: %d", de.thenutheads.jlndbe.schoolregistration.R.string.label_age, student.getAge()));
        ((TextView) convertView.findViewById(de.thenutheads.jlndbe.schoolregistration.R.id.studentAverage)).setText(String.format("%d: %d", de.thenutheads.jlndbe.schoolregistration.R.string.label_average, student.getAverage()));

        return convertView;
    }

}