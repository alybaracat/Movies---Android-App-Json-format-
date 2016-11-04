package aucegypt.alybaracat.movies;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video_player extends AppCompatActivity {
String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        Button buttonPlayVideo2 = (Button)findViewById(R.id.button1);
        getWindow().setFormat(PixelFormat.UNKNOWN);

        URL = getIntent().getExtras().getString("URL");

        VideoView mVideoView2 = (VideoView)findViewById(R.id.videoView1);

        mVideoView2.setVideoPath(URL);
        mVideoView2.requestFocus();
        mVideoView2.start();


        MediaController mediaController = new
                MediaController(this);
        mediaController.setAnchorView(mVideoView2);
        mVideoView2.setMediaController(mediaController);

        buttonPlayVideo2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoView mVideoView2 = (VideoView) findViewById(R.id.videoView1);


                mVideoView2.setVideoPath(URL);
                mVideoView2.requestFocus();
                mVideoView2.start();
            }
        });
    }
}
