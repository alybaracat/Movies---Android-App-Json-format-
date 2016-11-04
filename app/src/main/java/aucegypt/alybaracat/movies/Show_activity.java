package aucegypt.alybaracat.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Show_activity extends AppCompatActivity {
    public int position;

    public Video[]videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);
        String name="";


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Shows");

        setSupportActionBar(myToolbar);

        position = getIntent().getExtras().getInt("Position");

   //     TextView text = (TextView)findViewById(R.id.text);



       videos = Model.getInstance().Get_Array();


        ArrayList<Element> arrayofUsers=new ArrayList<Element>();

        int index=0;

        while(index<videos[position].shows.length){
            Element temp=new Element(videos[position].shows[index].show,videos[position].shows[index].description,videos[position].shows[index].image,videos[index].no_of_shows);
            arrayofUsers.add(temp);
            index++;

        }




        ElementAdapter adapter=new ElementAdapter(this,arrayofUsers);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent= new Intent(Show_activity.this, Episode.class);
                intent.putExtra("Position1",i);
                intent.putExtra("Position2",position);
                startActivity(intent);
            }
        });
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

                break;
            // action with ID action_settings was selected
            case R.id.action_info:
                Toast.makeText(this, "Info selected", Toast.LENGTH_SHORT)
                        .show(); Intent intent = new Intent(Show_activity.this, info_activity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
    }

