package de.thenutheads.jlndbe.enrolmentapp;

import android.content.Context;

/**
 * Created by Jlndbe on 08.09.2015.
 */
public class Subject {

    enum Grade{
        A(1),
        B(2),
        C(3),
        D(4),
        E(5),
        F(6);

        private int _numeric;
        Grade(int numeric){
            _numeric = numeric;
        }

        public int toInteger(){
            return _numeric;
        }

        @Override
        public String toString(){
            return LocalizeGrade(this ,App.getContext());
        }
    }

    private String _name;
    private Grade _grade;

    public Subject (String name, Grade grade){
        _name = name;
        _grade = grade;
    }

    public String getName(){ return _name; }
    public Grade getGrade(){ return _grade; }
    public void setGrade(Grade grade){ _grade = grade; }

    @Override
    public String toString(){
        return String.format("%d: %d", _name, _grade);
    }

    public static String LocalizeGrade(Grade grade, Context context){
        switch (grade){
            case A:
                return context.getString(de.thenutheads.jlndbe.schoolregistration.R.string.grade_a);
            case B:
                return context.getString(de.thenutheads.jlndbe.schoolregistration.R.string.grade_b);
            case C:
                return context.getString(de.thenutheads.jlndbe.schoolregistration.R.string.grade_c);
            case D:
                return context.getString(de.thenutheads.jlndbe.schoolregistration.R.string.grade_d);
            case E:
                return context.getString(de.thenutheads.jlndbe.schoolregistration.R.string.grade_e);
            case F:
                return context.getString(de.thenutheads.jlndbe.schoolregistration.R.string.grade_f);
            default:
                return null;
        }
    }
}


