package com.set.view.surface.video;

import android.app.Activity;
import android.os.Bundle;
import com.set.view.R;


/**
 * TextureView+Mediaplayer 播放
 */

public class TextureVideoActivity extends Activity {
    private VideoPlayer mVideoPlayer;
    private VideoController controllerUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textureview_video);
        mVideoPlayer =  findViewById(R.id.video_player);
        controllerUI =  findViewById(R.id.video_controller);
//        String videoUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        String videoUrl = "http://vjs.zencdn.net/v/oceans.mp4";
//        String videoUrl = "https://media.w3.org/2010/05/sintel/trailer.mp4";
//        String videoUrl = "http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4";
        controllerUI.setUrl(videoUrl);
        mVideoPlayer.setController(controllerUI);
    }
}