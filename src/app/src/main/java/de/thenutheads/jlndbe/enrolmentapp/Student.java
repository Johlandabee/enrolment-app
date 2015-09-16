package de.thenutheads.jlndbe.enrolmentapp;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;

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
public class Student implements Parcelable {
    private Context _context;
    private String _name;
    private String _firstName;

    private int _dobDay, _dobMonth, _dobYear;

    private ArrayList<Subject> _subjects;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(_name);
        out.writeString(_firstName);
        out.writeInt(_dobYear);
        out.writeInt(_dobMonth);
        out.writeInt(_dobDay);
        out.writeList(_subjects);
    }

    public static final Parcelable.Creator<Student> CREATOR
            = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    private Student(Parcel in) {
        _name = in.readString();
        _firstName = in.readString();
        _dobYear = in.readInt();
        _dobMonth = in.readInt();
        _dobDay = in.readInt();
        _subjects = in.readArrayList(Subject.class.getClassLoader());
    }


    public Student(String name, String firstName, Calendar dob, ArrayList<Subject> subjects) {
        _context = App.getContext();

        _name = name;
        _firstName = firstName;
        _dobYear = dob.get(Calendar.YEAR);
        _dobMonth = dob.get(Calendar.MONTH);
        _dobDay = dob.get(Calendar.DAY_OF_MONTH);
        _subjects = subjects;
    }

    public void addSubject(Subject subject) {
        if (subject.equals(null))
            return;
        _subjects.add(subject);
    }

    public void removeSubject(String subjectName) {
        if (subjectName.equals(null))
            return;

        for (Subject subject : _subjects) {
            if (subject.getName().equals(subjectName))
                _subjects.remove(subject);
        }
    }

    public void setSubjectGrade(String subjectName, Subject.Grade grade) {
        for (Subject subject : _subjects) {
            if (subject.getName().equals(subjectName))
                subject.setGrade(grade);
        }
    }

    public int getAge() {
        Calendar cal_dob = Calendar.getInstance();
        Calendar cal_today = Calendar.getInstance();

        cal_dob.set(_dobYear, _dobMonth, _dobDay);

        int age = ((cal_today.get(Calendar.YEAR)) - (cal_dob.get(Calendar.YEAR)));

        if (((cal_today.get(Calendar.MONTH) < cal_dob.get(Calendar.MONTH)) ||
                ((cal_today.get(Calendar.MONTH) == cal_dob.get(Calendar.MONTH)) &&
                        (cal_today.get(Calendar.DAY_OF_MONTH) < cal_dob.get(Calendar.DAY_OF_MONTH))))) {
            age--;
        }
        return age;
    }

    public float getAverage() {
        if (_subjects == null || _subjects.isEmpty()) return 0.00f;

        int count = 0;
        float average = 0.00f;
        for (Subject subject : _subjects) {
            average += subject.getGrade().ordinal() + 1;
            count++;
        }
        average /= count;

        return average;
    }

    public String getName() {
        return _name;
    }

    public String getFirstName() {
        return _firstName;
    }

    public Calendar getDateOfBirth() {
        Calendar dob = Calendar.getInstance();
        dob.set(_dobYear, _dobMonth, _dobDay);
        return dob;
    }

    public String getIsMajorLocalizedString() {
        if (getAge() >= 18)
            return _context.getString(R.string.age_minor);
        return _context.getString(R.string.age_major);
    }
}
