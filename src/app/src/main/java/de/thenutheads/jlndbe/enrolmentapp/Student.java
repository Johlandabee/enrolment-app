package de.thenutheads.jlndbe.enrolmentapp;

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
public class Student {
    private String _name;
    private String _firstName;
    private Date _dateOfBirth;
    private ArrayList<Subject> _subjects;

    public Student(String name, String firstName, Date dateOfBirth, ArrayList<Subject> subjects) {
        _name = name;
        _firstName = firstName;
        _dateOfBirth = dateOfBirth;
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

        cal_dob.setTime(_dateOfBirth);

        int age = cal_today.get(Calendar.YEAR) - cal_dob.get(Calendar.YEAR);

        if (((cal_today.get(Calendar.MONTH) < cal_dob.get(Calendar.MONTH)) ||
                ((cal_today.get(Calendar.MONTH) == cal_dob.get(Calendar.MONTH)) &&
                        (cal_today.get(Calendar.DAY_OF_MONTH) < cal_dob.get(Calendar.DAY_OF_MONTH))))) {
            age--;
        }
        return age;
    }

    public double getAverage() {
        if (_subjects == null || _subjects.isEmpty()) return 0.0;

        int count = 0;
        double average = 0.0;
        for (Subject subject : _subjects) {
            average += subject.getGrade().toInteger();
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

    public Date getDateOfBirth() {
        return _dateOfBirth;
    }

    public boolean isMajor() {
        return getAge() >= 18;
    }
}
