package com.youtu.sleep.youtubebackground.screens.main.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.youtu.sleep.youtubebackground.R;
import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuy on 01/08/2018.
 */
public class PopularVideosAdapter extends RecyclerView.Adapter<PopularVideosAdapter.MyViewHolder> {

    private List<Video> mVideos;

    PopularVideosAdapter() {
        this.mVideos = new ArrayList<>();
    }

    public void setData(List<Video> videos){
        this.mVideos.clear();
        this.mVideos.addAll(videos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(mVideos.get(position));
    }

    @Override
    public int getItemCount() {
        return mVideos == null ? 0 : mVideos.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageVideo;
        private TextView mTextDuration, mTextVideoName, mTextChannel, mTextDescription;

        public MyViewHolder(View itemView) {
            super(itemView);

            mImageVideo = itemView.findViewById(R.id.image_video);
            mTextDuration = itemView.findViewById(R.id.text_duration);
            mTextVideoName = itemView.findViewById(R.id.text_name);
            mTextChannel = itemView.findViewById(R.id.text_channel);
            mTextDescription = itemView.findViewById(R.id.text_description);
        }

        void bindData(Video video) {
            Glide.with(itemView.getContext()).load(video.getUrlThumbnail()).into(mImageVideo);
            mTextVideoName.setText(video.getTitle());
            mTextChannel.setText(video.getChannelTitle());
            mTextDescription.setText(video.getDescription());

        }
    }
}
