package aucegypt.alybaracat.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class Episode extends AppCompatActivity {

    private int position1;
    private int position2;

    Video[] videos;
    Episodes[] episodes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);
        position2 = getIntent().getExtras().getInt("Position1");
        position1 = getIntent().getExtras().getInt("Position2");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);


        //     TextView text = (TextView)findViewById(R.id.text);
        videos = Model.getInstance().Get_Array();

        Log.d("TAG", "onCreate: pos1=" + Integer.toString(position1) + " Position 2= " +Integer.toString(position2));
        CircleImageView image=(CircleImageView) findViewById(R.id.image);
        Picasso.with(this).load(videos[position1].shows[position2].image).into(image);

        TextView text=(TextView)findViewById(R.id.text);
        text.setText(videos[position1].shows[position2].show);

        setSupportActionBar(myToolbar);

        episodes= videos[position1].shows[position2].episodes;

        ArrayList<Episodes> arrayofUsers=new ArrayList<Episodes>(Arrays.asList(episodes));

        EpisodeAdapter adapter=new EpisodeAdapter(this,arrayofUsers);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent= new Intent(Episode.this, Video_player.class);
                intent.putExtra("URL",videos[position1].shows[position2].episodes[i].url);
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
                        .show();
                Intent intent = new Intent(Episode.this, info_activity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
}
