package aucegypt.alybaracat.movies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;





public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener  {

    TextView mTextView;
    Video [] videos ;
    OutputStreamWriter outputStreamWriter;
    Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("TV Series");

        setSupportActionBar(myToolbar);//Toolbar


        SendRequest2();//Checking For the dates ti check if there is an update



        if(file_is_empty()==true){//IF the file is empty load the file
            Log.d("empty", "onCreate: the file is empty ");
            SendRequest();}
        else {
            Log.d("not empty", "onCreate: file is not empty");


            Gson gson = new Gson();
            Type type = new TypeToken<Video[]>() {
            }.getType();
            //videos = gson.fromJson(String.valueOf(response),type);
            String response_temp = readFromFile();
            videos = gson.fromJson(response_temp, type);
            Model.getInstance().Set_Array(videos);


            ArrayList<Element> arrayofUsers = new ArrayList<Element>();

            int index = 0;

            while (index < videos.length) {
                Element temp = new Element(videos[index].list, "TV shows", videos[index].image, videos[index].no_of_shows);
                arrayofUsers.add(temp);
                index++;

            }

            ElementAdapter adapter = new ElementAdapter(this, arrayofUsers);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent intent = new Intent(MainActivity.this, Show_activity.class);
                    intent.putExtra("Position", position);
                    startActivity(intent);
                    //    overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave)
                }
            });


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuinflater = getMenuInflater();
        menuinflater.inflate(R.menu.activity_main_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_update:
                Toast.makeText(this, "Update selected", Toast.LENGTH_SHORT)
                        .show();
                SendRequest();
                break;
            // action with ID action_settings was selected
            case R.id.action_info:
                Toast.makeText(this, "Info selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(MainActivity.this, info_activity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }



    public void SendRequest(){

        mTextView  = (TextView) findViewById(R.id.text);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://188.166.163.170/videos.json";
        //mTextView.setText("That didn't work!");

        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,this,this);
        queue.add(stringRequest);


    }

     public void SendRequest2(){



        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://188.166.163.170/date.txt";
        //mTextView.setText("That didn't work!");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                Type type = new TypeToken<Date>() {
                }.getType();
                date = gson.fromJson(String.valueOf(response), type);
                Log.d("HI", "onResponse: " + response);
                if(date.day%5==0){
                    SendRequest();
                    Toast toast = Toast.makeText(getApplicationContext(), "Update Content", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

         queue.add(stringRequest);



     }


    @Override
    public void onErrorResponse(VolleyError error) {
 //       mTextView.setText("That didn't work!");

    }

    @Override
    public void onResponse(Object response) {
        // Display the first 500 characters of the response string.

//        Gson gson=new Gson();
       // Type type = new TypeToken<Video[]>(){}.getType();
       // videos = gson.fromJson(String.valueOf(response),type);

        writeToFile(String.valueOf(response));
        //String response_temp= readFromFile();
       // videos = gson.fromJson(response_temp,type);



       /* Model.getInstance().Set_Array(videos);


        ArrayList<Element> arrayofUsers=new ArrayList<Element>();

        int index=0;

        while(index<videos.length){
                Element temp=new Element(videos[index].list,"TV shows",videos[index].image,videos[index].no_of_shows);
                arrayofUsers.add(temp);
            index++;

        }




        ElementAdapter adapter=new ElementAdapter(this,arrayofUsers);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                Intent intent= new Intent(MainActivity.this, Show_activity.class);
                intent.putExtra("Position",position);
                startActivity(intent);
            //    overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);


            }
        });*/


    }




    private void writeToFile(String data) {
        try {
            outputStreamWriter = new OutputStreamWriter(openFileOutput("new.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput("new.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    private Boolean file_is_empty() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput("new.txt");

            if ( inputStream != null ) {

                return false;
            }
        }
        catch (FileNotFoundException e) {
            return true;
        } catch (IOException e) {
            return true;
        }

        return true;
    }
}
