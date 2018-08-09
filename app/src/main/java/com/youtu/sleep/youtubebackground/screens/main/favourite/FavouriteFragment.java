package com.youtu.sleep.youtubebackground.screens.main.favourite;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.youtu.sleep.youtubebackground.R;
import com.youtu.sleep.youtubebackground.data.model.popularvideo.Video;
import com.youtu.sleep.youtubebackground.data.repository.YoutubeVideoRepository;
import com.youtu.sleep.youtubebackground.screens.main.VideoAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment implements FavouriteVideosContract.View {

    private VideoAdapter mAdapter;
    private FavouriteVideosPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializationViews(view);
        getData();
    }

    /**
     * call get favourite videos data method
     */

    private void getData() {
        YoutubeVideoRepository repository = YoutubeVideoRepository.getInstance(getContext());
        mPresenter = new FavouriteVideosPresenter(this, repository);
        mPresenter.getFavouriteVideos();
    }

    /**
     * setup view
     */

    private void initializationViews(View view) {
        RecyclerView mRecyclerVideos = view.findViewById(R.id.recycler_favourite_videos);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecyclerVideos.setLayoutManager(llm);
        mAdapter = new VideoAdapter();
        mRecyclerVideos.setAdapter(mAdapter);
    }

    @Override
    public void showFavouriteVideos(List<Video> videos) {
        mAdapter.setData(videos);
    }

    @Override
    public void showFavouriteVideosErrorMessage() {
        Toast.makeText(getContext(), R.string.fail_message, Toast.LENGTH_SHORT).show();
    }
}
