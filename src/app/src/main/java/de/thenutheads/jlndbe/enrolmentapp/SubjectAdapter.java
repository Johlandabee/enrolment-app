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
public class SubjectAdapter extends ArrayAdapter<Subject> {
    public SubjectAdapter(Context context, ArrayList<Subject> subjects) {
        super(context, 0, subjects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Subject subject = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(de.thenutheads.jlndbe.enrolmentapp.R.layout.item_subject, parent, false);
        }

        ((TextView) convertView.findViewById(de.thenutheads.jlndbe.enrolmentapp.R.id.svName)).setText(subject.getName());
        ((TextView) convertView.findViewById(de.thenutheads.jlndbe.enrolmentapp.R.id.svGrade)).setText(Subject.LocalizeGrade(subject.getGrade(), getContext()));

        return convertView;
    }

}
