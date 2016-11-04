package aucegypt.alybaracat.calculator;

import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import java.util.Stack;

import static aucegypt.alybaracat.calculator.Infix_to_Postfix.*;


/**
 * Created by AlyBaracat on 6/9/16.
 */



public class Calculator {

    private static Calculator instance = null;
    private static final String TAG = "MyActivity";
    Infix_to_Postfix expression = new Infix_to_Postfix();
    private Boolean flag_concecutive_num = false;

    protected Calculator() {
        // Exists only to defeat instantiation.
    }

    public static Calculator getInstance() {
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }


    private static String return1;

    public String stackhandle(String o) {

        int value;

        value = tryParse(o);//Try Parse returns the value of the parseint of the string if possible , in case of exception it returns -1
        boolean flag = true;

        if (value >= 0 && value < 10 || o.contains(".")) {//The string we entered is a number
            flag = true;//Operand
            if (!flag_concecutive_num) {
                Infix_to_Postfix.stack_postfix(flag, o);
                return1 = o;
            } else {
                Log.d(TAG, "stackhandle: Entered in concesutive num");
                flag_concecutive_num = false;
                Infix_to_Postfix.clear();
                Infix_to_Postfix.stack_postfix(flag, o);
                return1 = o + "RST";
            }

            return return1;
        } else if (o.contains("C")) {
            flag_concecutive_num = false;
            flag = false;//Operator C
            Infix_to_Postfix.stack_postfix(flag, o);
            return "ERASE";
        } else if (!o.contains("=")) {
            flag_concecutive_num = false;

            flag = false;//Operators + - * /
            Infix_to_Postfix.stack_postfix(flag, o);
            return1 = " " + o + " ";
            return return1;

        } else {
            flag = false;//Operator =
            flag_concecutive_num = true;
            Infix_to_Postfix.stack_postfix(flag, o);
            String temp1;
            Infix_to_Postfix.Evaluate();
            if (Infix_to_Postfix.Postfix_evaluate.empty()) temp1 = "empty";
            else {
                temp1 = Infix_to_Postfix.Postfix_evaluate.pop();
                if (temp1.contains(".0")) {
                    temp1 = temp1.replace(".0", "");
                }

            }
            Infix_to_Postfix.clear();

            return " = " + temp1 + "NEW LINE";

        }


    }


    private static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }


}






