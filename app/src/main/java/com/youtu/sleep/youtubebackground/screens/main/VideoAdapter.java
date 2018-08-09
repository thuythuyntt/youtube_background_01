package com.youtu.sleep.youtubebackground.screens.main;

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

import static com.youtu.sleep.youtubebackground.utils.Contants.FALSE;
import static com.youtu.sleep.youtubebackground.utils.Contants.TRUE;

/**
 * Created by thuy on 01/08/2018.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private List<Video> mVideos;
    private OnItemClick mListener;

    public VideoAdapter(OnItemClick listener) {
        this.mListener = listener;
        this.mVideos = new ArrayList<>();
    }

    public VideoAdapter() {
        this.mVideos = new ArrayList<>();
    }

    public void setData(List<Video> videos) {
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

    class MyViewHolder extends RecyclerView.ViewHolder {

        private boolean isFavourite = false;

        private ImageView mImageVideo, mImageFavourite;
        private TextView mTextDuration, mTextVideoName, mTextChannel, mTextDescription;

        public MyViewHolder(final View itemView) {
            super(itemView);

            mImageVideo = itemView.findViewById(R.id.image_video);
            mImageFavourite = itemView.findViewById(R.id.image_favourite);
            mTextDuration = itemView.findViewById(R.id.text_duration);
            mTextVideoName = itemView.findViewById(R.id.text_name);
            mTextChannel = itemView.findViewById(R.id.text_channel);
            mTextDescription = itemView.findViewById(R.id.text_description);

            mImageFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isFavourite) {
                        checkFavouriteVideo();
                    } else {
                        unCheckFavouriteVideo();
                    }
                }
            });
        }

        void checkFavouriteVideo() {
            mImageFavourite.setBackgroundResource(R.drawable.ic_favourite_default);
            Video v = mVideos.get(getAdapterPosition());
            v.setIsFavourite(TRUE);
            mListener.onFavouriteVideoClick(v);
            isFavourite = true;
        }

        void unCheckFavouriteVideo() {
            mImageFavourite.setBackgroundResource(R.drawable.ic_favourite_unable);
            Video v = mVideos.get(getAdapterPosition());
            v.setIsFavourite(FALSE);
            mListener.onRemoveFavouriteVideoClick(v);
            isFavourite = false;
        }

        void bindData(Video video) {
            Glide.with(itemView.getContext()).load(video.getUrlThumbnail()).into(mImageVideo);
            mTextVideoName.setText(video.getTitle());
            mTextChannel.setText(video.getChannelTitle());
            mTextDescription.setText(video.getDescription());
            if(video.getIsFavourite()==TRUE){
                mImageFavourite.setBackgroundResource(R.drawable.ic_favourite_default);
            } else {
                mImageFavourite.setBackgroundResource(R.drawable.ic_favourite_unable);
            }
        }
    }

    public interface OnItemClick {
        void onFavouriteVideoClick(Video video);

        void onRemoveFavouriteVideoClick(Video video);

    }
}
