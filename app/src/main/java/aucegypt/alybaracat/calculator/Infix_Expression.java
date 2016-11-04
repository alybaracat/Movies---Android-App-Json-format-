package aucegypt.alybaracat.calculator;

import android.util.Log;

/**
 * Created by AlyBaracat on 6/12/16.
 */
public class Infix_Expression {
    private  String infix_Ex="";
    Calculator calc=new Calculator();
    String display="";
    private static final String TAG = "MyActivity";
    private Boolean flag_concecutive_num=false;



    public void add(String Op) {

        int value;

        value = tryParse(Op);//Try Parse returns the value of the parseint of the string if possible , in case of exception it returns -1


        if (value >= 0 && value < 10) {//Operand


            if(!flag_concecutive_num){//IF the user just pressed = and the pressed a number
                //We add Operands
                infix_Ex = infix_Ex + Op;//in case of pressing an operator after = we continue the expression
            }
            else {
                flag_concecutive_num=false;//in case of pressing a number we clear the string
                infix_Ex="";
                infix_Ex=Op;
            }

        }
        else if(Op.contains(".")){//. operator
            if(!infix_Ex.isEmpty()){
            if(Op.contains(".")) if (check_2_dots(Op) == true) {

            } else {
                infix_Ex = infix_Ex + Op;

            }
        }}
        else if(Op.contains("=")){//So we have to evaluate the expression
            if(!No_Operator()){
            if(!infix_Ex.isEmpty()) {
            flag_concecutive_num=true;
            infix_Ex = infix_Ex + Op;
            int index=0;
            int size=infix_Ex.length()-1;
            while(index<=size){//Char by Char
                String display_temp=calc.stackhandle(Character.toString(infix_Ex.charAt(index)));//We send the char to calculator class
                if(display_temp.contains("NEW LINE")){
                    display_temp=display_temp.replace("NEW LINE","");
                    display_temp=display_temp.replace(" = ","");
                    if(display_temp.contains("empty")) {
                        display_temp=display_temp.replace("empty","");
                    }
                    display=display_temp;
                }

                else switch (display_temp){
                        case "ERASE":display="";break;
                        default:display = display + display_temp;break;
                    }
                index++;
            }
            infix_Ex=display;
        }else infix_Ex="";}}
        else if(Op.contains("C")){//Clear Expression
            flag_concecutive_num=false;
            infix_Ex="";
        }
        else {
            if (!infix_Ex.isEmpty()) {//In case of Operator
                flag_concecutive_num = false;
                if (check_2_operators(Op) == true) {
                    Log.d(TAG, " 2 operators, the new Operator is" + Op);
                    int size_temp = infix_Ex.length() - 1;
                    int index_replace;
                    index_replace = size_temp;
                    char temp_char = infix_Ex.charAt(index_replace);
                   // infix_Ex = infix_Ex.(Character.toString(infix_Ex.charAt(index_replace)), Op);
                        infix_Ex=infix_Ex.substring(0,size_temp);
                    infix_Ex=infix_Ex+ Op;

                } else {
                    Log.d(TAG, "1 operators");

                    infix_Ex = infix_Ex + Op ;

                }
            }
        }

    }

    public String getInfix_Ex(){//Putting spaces for user interface
        String temp_infix;

        temp_infix=infix_Ex.replace("+"," + ");
        temp_infix=temp_infix.replace("-"," - ");
        temp_infix=temp_infix.replace("*"," * ");
        temp_infix=temp_infix.replace("/"," / ");

        return temp_infix;
    }

    private Boolean check_2_operators(String Op){

        int temp_i=infix_Ex.length()-1;
        String temp_str=Character.toString(infix_Ex.charAt(temp_i));

        if(temp_str.equals("+")||temp_str.equals("-")||temp_str.equals("*")||temp_str.equals("/")){
                Log.d(TAG, " 2 operators return true"+" Char= "+Op);

            return true;
        }

        else{
            Log.d(TAG, "1 operators return false");

            return false;
        }


    }

    private Boolean check_2_dots(String Op){

        int temp_i=infix_Ex.length()-1;
        String temp_str=Character.toString(infix_Ex.charAt(temp_i));
        Log.d(TAG, "temp string "+temp_str);

        if(temp_str.equals(".")){
            Log.d(TAG, " 2 operators return true"+" Char= "+Op);

            return true;
        }

        else{
            Log.d(TAG, "1 operators return false");

            return false;
        }


    }


        private static Integer tryParse(String text) {
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                return -1;
            }
        }



        private Boolean No_Operator(){

            if(infix_Ex.contains("+")||infix_Ex.contains("-")||infix_Ex.contains("/")||infix_Ex.contains("*")){
                return false;
            }
            else return true;
        }






    }



