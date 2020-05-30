package com.test.playvideo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MainActivity extends Activity {


    ArrayList<Message> list = new ArrayList<>();
    private Button bn_play;

    private String[] conver_urls = {"https://jzvd.nathen.cn/snapshot/f402a0e012b14d41ad07939746844c5e00005.jpg",
            "https://jzvd.nathen.cn/snapshot/8bd6d06878fc4676a62290cbe8b5511f00005.jpg",
            "https://jzvd.nathen.cn/snapshot/371ddcdf7bbe46b682913f3d3353192000005.jpg",
            "https://jzvd.nathen.cn/snapshot/dabe6ca3c71942fd926a86c8996d750f00005.jpg",
            "https://jzvd.nathen.cn/snapshot/edac56544e2f43bb827bd0e819db381000005.jpg",
            "https://jzvd.nathen.cn/snapshot/531f1e488eb84b898ae9ca7f6ba758ed00005.jpg",
            "https://jzvd.nathen.cn/snapshot/ad0331e78393457d88ded2257d9e47c800005.jpg",
            "https://jzvd.nathen.cn/snapshot/6ae53110f7fd470683587746f027698400005.jpg",
            "https://jzvd.nathen.cn/snapshot/ef384b95897b470c80a4aca4dd1112a500005.jpg",
            "https://jzvd.nathen.cn/snapshot/86a055d08b514c9ca1e76e76862105ec00005.jpg"};

    private String[] urls  =   {"https://jzvd.nathen.cn/video/1137e480-170bac9c523-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/e0bd348-170bac9c3b8-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/2f03c005-170bac9abac-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/7bf938c-170bac9c18a-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/47788f38-170bac9ab8a-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/2d6ffe8f-170bac9ab87-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/633e0ce-170bac9ab65-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/2d6ffe8f-170bac9ab87-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/51f7552c-170bac98718-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/2a101070-170bad88892-0007-1823-c86-de200.mp4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<urls.length;i++){
            list.add(new Message(urls[i],conver_urls[i]));
        }
        bn_play = findViewById(R.id.bn_play);
        bn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PlayerActivity.class);
                intent.putExtra("videoList",list);
                intent.putExtra("position",3);
                startActivity(intent);
            }
        });
    }
}
