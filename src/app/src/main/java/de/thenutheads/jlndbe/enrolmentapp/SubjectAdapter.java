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
public class SubjectAdapter extends ArrayAdapter<Subject>{
    public SubjectAdapter(Context context, ArrayList<Subject> subjects){
        super(context, 0, subjects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Subject subject = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(de.thenutheads.jlndbe.schoolregistration.R.layout.item_subject, parent, false);
        }

        ((TextView) convertView.findViewById(de.thenutheads.jlndbe.schoolregistration.R.id.svName)).setText(subject.getName());
        ((TextView) convertView.findViewById(de.thenutheads.jlndbe.schoolregistration.R.id.svGrade)).setText(Subject.LocalizeGrade(subject.getGrade(), getContext()));

        return convertView;
    }

}
