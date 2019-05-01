package com.example.topalbums;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.topalbums.data.TopAlbums;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TopAlbumsFragment extends Fragment
{
    private RecyclerView albumsRecyclerView;
    private TopAlbumsViewModel topAlbumsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.albums_list, container, false);

        albumsRecyclerView =  view.findViewById(R.id.albums_recycler_view);
        albumsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        topAlbumsViewModel = ViewModelProviders.of(this).get(TopAlbumsViewModel.class);

        // Create the observer which updates the recycler view.
        final Observer<TopAlbums> topAlbumsObserver = new Observer<TopAlbums>()
        {
            @Override
            public void onChanged(@Nullable final TopAlbums topAlbums)
            {
                // Update the UI
                if (topAlbums != null)
                    albumsRecyclerView.setAdapter(new TopAlbumsAdapter(topAlbums.getAlbums()));
            }
        };

        // Observe definitions data changes
        topAlbumsViewModel.getTopAlbums().observe(getViewLifecycleOwner(), topAlbumsObserver);
        topAlbumsViewModel.loadTopAlbums();

        return view;
    }

}


