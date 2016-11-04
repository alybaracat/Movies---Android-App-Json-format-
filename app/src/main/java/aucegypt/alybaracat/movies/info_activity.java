package aucegypt.alybaracat.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class info_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Info");

        setSupportActionBar(myToolbar);
        CircleImageView image=(CircleImageView) findViewById(R.id.image);

    }
}
