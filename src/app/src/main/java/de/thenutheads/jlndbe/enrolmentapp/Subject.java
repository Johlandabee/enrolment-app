package de.thenutheads.jlndbe.enrolmentapp;

import android.content.Context;

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
public class Subject {

    enum Grade {
        A(1),
        B(2),
        C(3),
        D(4),
        E(5),
        F(6);

        private int _numeric;

        Grade(int numeric) {
            _numeric = numeric;
        }

        public int toInteger() {
            return _numeric;
        }

        @Override
        public String toString() {
            return LocalizeGrade(this, App.getContext());
        }
    }

    private String _name;
    private Grade _grade;

    public Subject(String name, Grade grade) {
        _name = name;
        _grade = grade;
    }

    public String getName() {
        return _name;
    }

    public Grade getGrade() {
        return _grade;
    }

    public void setGrade(Grade grade) {
        _grade = grade;
    }

    @Override
    public String toString() {
        return String.format("%d: %d", _name, _grade);
    }

    public static String LocalizeGrade(Grade grade, Context context) {
        switch (grade) {
            case A:
                return context.getString(de.thenutheads.jlndbe.enrolmentapp.R.string.grade_a);
            case B:
                return context.getString(de.thenutheads.jlndbe.enrolmentapp.R.string.grade_b);
            case C:
                return context.getString(de.thenutheads.jlndbe.enrolmentapp.R.string.grade_c);
            case D:
                return context.getString(de.thenutheads.jlndbe.enrolmentapp.R.string.grade_d);
            case E:
                return context.getString(de.thenutheads.jlndbe.enrolmentapp.R.string.grade_e);
            case F:
                return context.getString(de.thenutheads.jlndbe.enrolmentapp.R.string.grade_f);
            default:
                return null;
        }
    }
}


