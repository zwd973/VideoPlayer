package com.test.playvideo;

import android.content.Context;
import android.os.Handler;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lmx.library.media.VideoPlayAdapter;
import com.test.playvideo.view.VideoLoadingProgressbar;

import java.util.ArrayList;


public class ScrollVideoAdapter extends VideoPlayAdapter<ScrollVideoAdapter.ViewHolder> {
    private Context mContext;

    private int mCurrentPosition;
    private ViewHolder mCurrentHolder;

    private VideoPlayer videoPlayer;
    private TextureView textureView;

    private ArrayList<Message> videoList;

    private Handler handler;
    public ScrollVideoAdapter(Context mContext, final ArrayList<Message> videoList) {
        this.mContext = mContext;
        videoPlayer = new VideoPlayer();
        textureView = new TextureView(mContext);
        textureView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(videoPlayer.getState()== VideoPlayer.State.PLAYING)
                    videoPlayer.pause();
                else
                    videoPlayer.start();
                return false;
            }
        });
        videoPlayer.setTextureView(textureView);
        this.videoList = videoList;
        handler = new Handler();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(videoList.get(position).getAvactar()).apply(options).into(holder.ivCover);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    @Override
    public void onPageSelected(int itemPosition, View itemView) {
        mCurrentPosition = itemPosition;
        mCurrentHolder = new ViewHolder(itemView);
        playVideo(videoList.get(itemPosition).getFeedurl());
    }

    private void playVideo(String url) {
        videoPlayer.reset();
        mCurrentHolder.pbLoading.setVisibility(View.VISIBLE);
        videoPlayer.setOnStateChangeListener(new VideoPlayer.OnStateChangeListener() {
            @Override
            public void onReset() {
                mCurrentHolder.ivCover.setVisibility(View.VISIBLE);
                mCurrentHolder.pbLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onRenderingStart() {
                mCurrentHolder.ivCover.setVisibility(View.GONE);
                mCurrentHolder.pbLoading.setVisibility(View.INVISIBLE);
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mCurrentHolder.pg_bar.setVisibility(View.INVISIBLE);
//                    }
//                }, 1000);
            }

            @Override
            public void onProgressUpdate(float per) {
                mCurrentHolder.pg_bar.setProgress((int)(per * 100));
            }

            @Override
            public void onPause() {
                mCurrentHolder.pbLoading.setVisibility(View.INVISIBLE);
                mCurrentHolder.pg_bar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStop() {
                mCurrentHolder.ivCover.setVisibility(View.VISIBLE);
                mCurrentHolder.pbLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onComplete() {
                videoPlayer.start();
            }
        });
        if (textureView.getParent() != mCurrentHolder.flVideo) {
            if (textureView.getParent() != null) {
                ((FrameLayout) textureView.getParent()).removeView(textureView);
            }
            mCurrentHolder.flVideo.addView(textureView);
        }
        videoPlayer.setDataSource(url);
        videoPlayer.prepare();
    }

    public void release() {
        videoPlayer.release();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private FrameLayout flVideo;
        private ImageView ivCover;
        private VideoLoadingProgressbar pbLoading;
        private ImageView im_like;
        private ImageView im_favor;
        private ImageView im_star;
        private TextView tv_like;
        private TextView tv_favor;
        private TextView tv_star;

        private SeekBar pg_bar;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            flVideo = itemView.findViewById(R.id.flVideo);
            ivCover = itemView.findViewById(R.id.ivCover);
            pbLoading = itemView.findViewById(R.id.pbLoading);

            im_like = itemView.findViewById(R.id.im_like);
            im_like.setImageResource(R.drawable.heart_off);
            im_like.setOnClickListener(this);

            im_favor = itemView.findViewById(R.id.im_favor);
            im_favor.setImageResource(R.drawable.thumb_off);
            im_favor.setOnClickListener(this);

            im_star = itemView.findViewById(R.id.im_star);
            im_star.setImageResource(R.drawable.star_off);
            im_star.setOnClickListener(this);

            pg_bar = itemView.findViewById(R.id.seekBar);
            pg_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(fromUser)
                        videoPlayer.seekTo(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.im_like:
                    ((ImageView)v).setImageResource(R.drawable.heart_on);
                    break;
                case R.id.im_favor:
                    ((ImageView)v).setImageResource(R.drawable.thumb_on);
                    break;
                case  R.id.im_star:
                    ((ImageView)v).setImageResource(R.drawable.star_on);
                    break;
            }

        }
    }
}
