package com.test.playvideo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lmx.library.media.VideoPlayRecyclerView;

import java.util.ArrayList;

public class PlayerActivity extends Activity {
    private VideoPlayRecyclerView mRvVideo;
    private ScrollVideoAdapter adapter;
    private ArrayList<Message> videoList;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Intent intent = getIntent();

        if(intent != null){
            videoList = (ArrayList<Message>)intent.getSerializableExtra("videoList");
            position = intent.getIntExtra("position",1);
            if(videoList==null){
                finish();
            }
        }else {
            finish();
        }
        initView();
    }

    private void initView() {
        findViewById(R.id.ibBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRvVideo = findViewById(R.id.rvVideo);
        Message message = videoList.get(0);
        videoList.set(0,videoList.get(position));
        videoList.set(position,message);

        adapter = new ScrollVideoAdapter(this,videoList);
        mRvVideo.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.release();
    }
}