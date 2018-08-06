package com.youtu.sleep.youtubebackground.screens.main.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.youtu.sleep.youtubebackground.R;
import com.youtu.sleep.youtubebackground.data.model.popularvideo.Item;
import com.youtu.sleep.youtubebackground.screens.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements PopularVideosContract.View {

    private static final String VALUE_PART = "snippet";
    private static final String VALUE_CHART = "mostPopular";

    private View mView;
    private List<Item> mList;
    private RecyclerView mRecyclerVideos;
    private PopularVideosAdapter mAdapter;
    private PopularVideosPresenter mPresenter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        initializationViews();
        return mView;
    }

    private void initializationViews() {
        mList = new ArrayList<>();
        mRecyclerVideos = (RecyclerView) mView.findViewById(R.id.recycler_most_popular_videos);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecyclerVideos.setLayoutManager(llm);
        mAdapter = new PopularVideosAdapter(getContext(), mList);
        mRecyclerVideos.setAdapter(mAdapter);

        mPresenter = new PopularVideosPresenter(this);
        mPresenter.getPopularVideos(VALUE_PART, VALUE_CHART);
    }

    @Override
    public void showPopularVideos(List<Item> videos) {
        mList.clear();
        mList.addAll(videos);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showGetPopularVideosErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
