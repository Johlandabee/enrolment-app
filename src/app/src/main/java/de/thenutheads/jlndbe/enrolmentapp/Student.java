package de.thenutheads.jlndbe.enrolmentapp;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;

/**
 * Created by Jlndbe on 08.09.2015.
 */
public class Student {
    private String _name;
    private String _firstName;
    private Date _dateOfBirth;
    private ArrayList<Subject> _subjects;

    public Student(String name, String firstName, Date dateOfBirth, ArrayList<Subject> subjects){
        _name = name;
        _firstName = firstName;
        _dateOfBirth = dateOfBirth;
        _subjects = subjects;
    }

    public void addSubject(Subject subject) {
        if(subject.equals(null))
            return;
        _subjects.add(subject);
    }

    public void removeSubject(String subjectName){
        if(subjectName.equals(null))
            return;

        for(Subject subject : _subjects){
            if(subject.getName().equals(subjectName))
                _subjects.remove(subject);
        }
    }

    public void setSubjectGrade (String subjectName, Subject.Grade grade){
        for(Subject subject : _subjects){
            if(subject.getName().equals(subjectName))
                subject.setGrade(grade);
        }
    }
    public int getAge() {
        Calendar cal_dob = Calendar.getInstance();
        Calendar cal_today = Calendar.getInstance();

        cal_dob.setTime(_dateOfBirth);

        int age = cal_today.get(Calendar.YEAR) - cal_dob.get(Calendar.YEAR);

        if(((cal_today.get(Calendar.MONTH) < cal_dob.get(Calendar.MONTH)) ||
                ((cal_today.get(Calendar.MONTH) == cal_dob.get(Calendar.MONTH)) &&
                        (cal_today.get(Calendar.DAY_OF_MONTH) < cal_dob.get(Calendar.DAY_OF_MONTH))))) {
            age--;
        }
        return age;
    }

    public double getAverage(){
        if(_subjects == null || _subjects.isEmpty()) return 0.0;

        int count = 0;
        double average = 0.0;
        for (Subject subject : _subjects) {
            average += subject.getGrade().toInteger();
            count++;
        }
        average /= count;

        return average;
    }

    public String getName(){
        return _name;
    }

    public String getFirstName(){
        return _firstName;
    }

    public Date getDateOfBirth(){
        return _dateOfBirth;
    }

    public boolean isMajor() {
        return getAge() >= 18;
    }
}
