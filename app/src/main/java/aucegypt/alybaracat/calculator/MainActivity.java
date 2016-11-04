package aucegypt.alybaracat.calculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;




public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyActivity";
    Infix_Expression current=new Infix_Expression();
    Calculator calc = new Calculator();

    String display="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //*Wait lines;*
        final Button clickButton1 = (Button) findViewById(R.id.Button1);
        clickButton1.setOnClickListener(this);
      //  clickButton1.setBackgroundColor(Color.argb(255,52,152,219));
        final Button clickButton2 = (Button) findViewById(R.id.Button2);
        clickButton2.setOnClickListener(this);
        final Button clickButton3 = (Button) findViewById(R.id.Button3);
        clickButton3.setOnClickListener(this);
        final Button clickButton4 = (Button) findViewById(R.id.Button4);
        clickButton4.setOnClickListener(this);
        final Button clickButton5 = (Button) findViewById(R.id.Button5);
        clickButton5.setOnClickListener(this);
        final Button clickButton6 = (Button) findViewById(R.id.Button6);
        clickButton6.setOnClickListener(this);
        final Button clickButton7 = (Button) findViewById(R.id.Button7);
        clickButton7.setOnClickListener(this);
        final Button clickButton8 = (Button) findViewById(R.id.Button8);
        clickButton8.setOnClickListener(this);
        final Button clickButton9 = (Button) findViewById(R.id.Button9);
        clickButton9.setOnClickListener(this);
        final Button clickButton10 = (Button) findViewById(R.id.Button10);
        clickButton10.setOnClickListener(this);
        final Button clickButton11 = (Button) findViewById(R.id.Button11);
        clickButton11.setOnClickListener(this);
        final Button clickButton12 = (Button) findViewById(R.id.Button12);
        clickButton12.setOnClickListener(this);
        final Button clickButton13 = (Button) findViewById(R.id.Button13);
        clickButton13.setOnClickListener(this);
        final Button clickButton14 = (Button) findViewById(R.id.Button14);
        clickButton14.setOnClickListener(this);
        final Button clickButton15 = (Button) findViewById(R.id.Button15);
        clickButton15.setOnClickListener(this);
        final Button clickButton16 = (Button) findViewById(R.id.Button16);
        clickButton16.setOnClickListener(this);
        final Button clickButton17 = (Button) findViewById(R.id.Button17);
        clickButton17.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {

        TextView t1=(TextView)findViewById(R.id.textView2);


        String v=(String)view.getTag();
        current.add(v);
        display = current.getInfix_Ex();

        t1.setText(display);




    }
}

