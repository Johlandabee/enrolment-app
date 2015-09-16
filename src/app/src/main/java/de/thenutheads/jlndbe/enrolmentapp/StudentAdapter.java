package de.thenutheads.jlndbe.enrolmentapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * Created by Steven Wobser on 15.09.2015
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
public class StudentAdapter extends ArrayAdapter<Student> {
    public StudentAdapter(Context context, ArrayList<Student> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(de.thenutheads.jlndbe.enrolmentapp.R.layout.item_student, parent, false);
        }

        ((TextView) convertView.findViewById(de.thenutheads.jlndbe.enrolmentapp.R.id.studentFullName)).setText(String.format("%d, %d", student.getName(), student.getFirstName()));
        ((TextView) convertView.findViewById(de.thenutheads.jlndbe.enrolmentapp.R.id.studentAge)).setText(String.format("%d: %d", de.thenutheads.jlndbe.enrolmentapp.R.string.label_age, student.getAge()));
        ((TextView) convertView.findViewById(de.thenutheads.jlndbe.enrolmentapp.R.id.studentAverage)).setText(String.format("%d: %d", de.thenutheads.jlndbe.enrolmentapp.R.string.label_average, student.getAverage()));

        return convertView;
    }

}