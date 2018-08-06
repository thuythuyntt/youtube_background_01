package com.youtu.sleep.youtubebackground.screens.main.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.youtu.sleep.youtubebackground.R;
import com.youtu.sleep.youtubebackground.data.model.popularvideo.Item;

import java.util.List;

/**
 * Created by thuy on 01/08/2018.
 */
public class PopularVideosAdapter extends RecyclerView.Adapter<PopularVideosAdapter.MyViewHolder>{

    private Context mContext;
    private List<Item> mVideosList;

    public PopularVideosAdapter(Context context, List<Item> videosList) {
        this.mContext = context;
        this.mVideosList = videosList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(mVideosList.get(position));
    }

    @Override
    public int getItemCount() {
        return mVideosList == null ? 0 : mVideosList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImageVideo;
        private TextView mTextDuration;
        private TextView mTextVideoName;
        private TextView mTextChannel;
        private TextView mTextDescription;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageVideo = itemView.findViewById(R.id.image_video);
            mTextDuration = itemView.findViewById(R.id.text_duration);
            mTextVideoName = itemView.findViewById(R.id.text_name);
            mTextChannel = itemView.findViewById(R.id.text_channel);
            mTextDescription = itemView.findViewById(R.id.text_description);
        }

        void bindData(Item video){
            Glide.with(mContext).load(video.getSnippet().getThumbnails().getHigh().getUrl()).into(mImageVideo);
            mTextVideoName.setText(video.getSnippet().getTitle());
            mTextChannel.setText(video.getSnippet().getChannelTitle());
            mTextDescription.setText(video.getSnippet().getDescription());
        }
    }
}
