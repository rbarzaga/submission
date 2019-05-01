package com.example.topalbums;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.topalbums.data.Album;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TopAlbumsAdapter extends RecyclerView.Adapter<TopAlbumsViewHolder>
{
    private final List<Album> topAlbums;

    TopAlbumsAdapter(List<Album> topAlbums)
    {
        this.topAlbums = topAlbums;
    }

    @Override
    public TopAlbumsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        return new TopAlbumsViewHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(TopAlbumsViewHolder holder, int position)
    {
        holder.bind(topAlbums.get(position));
    }

    @Override
    public int getItemCount()
    {
        return topAlbums != null  ? topAlbums.size() : 0;
    }

}
