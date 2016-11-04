package aucegypt.alybaracat.calculator;

import android.util.Log;

import java.util.Stack;

/**
 * Created by AlyBaracat on 6/11/16.
 */
public class Infix_to_Postfix {
    public static Stack<String> Postfix_evaluate = new Stack<String>();//Evaluating postfix expression
    private static Stack<String> postfix = new Stack<String>();//Creating postfix expression from infix expression
    private static String postfix_Ex = "";
    private static final String TAG = "MyActivity";

    public static void clear(){
        Postfix_evaluate.clear();
        postfix.clear();
        postfix_Ex="";

    }




    public static void stack_postfix(boolean flag, String o) {
        String temp;
        if (flag == true) {
            postfix_Ex = postfix_Ex + o; //It is an operand so we write it directly in the postfix notation
        } else {

            switch (o) {
                case "C":
                    clear();

                    break;

                case "*":
                case "/":
                    postfix_Ex = postfix_Ex + " ";
                    if (!postfix.empty()) {
                        temp = postfix.peek();
                    } else temp = "na";//As the + and - is higher precedence than * we only pop * and /
                    while (!postfix.empty() && temp != "na" && !temp.contains("+") && !temp.contains("-")) {
                        postfix_Ex = postfix_Ex + postfix.pop();
                    }
                    postfix.push(o);
                    break;

                case "+":
                case "-":
                    postfix_Ex = postfix_Ex + " ";

                    while (!postfix.empty()) {//We pop all Operators
                        postfix_Ex = postfix_Ex + postfix.pop();
                    }
                    postfix.push(o);
                    break;
                case "=":
                    while (!postfix.empty()) {
                        Log.d(TAG, "stack_postfix: Entered =");
                        postfix_Ex = postfix_Ex + " " + postfix.pop();
                    }                        break;

            }
        }
    }

    private static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double tryParse_D(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return -1.0;
        }
    }


    public static void Evaluate(){

        double operand1;
        double operand2;



        double result=0;
        int i=0;
        String temp;

        int temp_value;
        while(i<postfix_Ex.length()){
            Log.d(TAG, "Evaluate: " + postfix_Ex);
            temp=Character.toString(postfix_Ex.charAt(i));
            temp_value=tryParse(temp);
            if(temp.contains("C"))Postfix_evaluate.clear();
            if(temp.contains(" ")){
                i++;
                continue;}
            if(temp_value >= 0 && temp_value < 10 ||temp.contains(".")){

                while(!Character.toString(postfix_Ex.charAt(i+1)).contains(" ")){
                    temp= temp + Character.toString(postfix_Ex.charAt(i+1));
                    Log.d(TAG, "Evaluate: " + postfix_Ex);
                    i++;
                }
                // temp=postfix_Ex.substring(i,postfix_Ex.indexOf(" ",i));
                Postfix_evaluate.push(temp);
            }
            else {


               if(!Postfix_evaluate.empty()){

                   operand1=tryParse_D(Postfix_evaluate.pop());}
                else operand1=0;

                if(!Postfix_evaluate.empty()){operand2=tryParse_D(Postfix_evaluate.pop());}
                else operand2=0;


                if(temp.contains("+")) result = operand1 + operand2;
                if(temp.contains("-")) result = operand2 - operand1;
                if(temp.contains("*")) result = operand1 * operand2;
                if(temp.contains("/")) result = operand2 / operand1;

                Postfix_evaluate.push(Double.toString(result));


            }

            i++;

        }






    }
}


