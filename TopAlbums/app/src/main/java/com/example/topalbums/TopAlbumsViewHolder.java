package com.example.topalbums;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.topalbums.data.Album;

import androidx.recyclerview.widget.RecyclerView;

public class TopAlbumsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private ImageView albumImageView;
    private TextView albumTextView;
    private TextView artistTextView;
    private Album album;

    TopAlbumsViewHolder(LayoutInflater inflater, ViewGroup parent)
    {
        super(inflater.inflate(R.layout.album_item, parent, false));
        albumImageView = itemView.findViewById(R.id.album_image_view);
        albumTextView = itemView.findViewById(R.id.album_text_view);
        artistTextView = itemView.findViewById(R.id.artist_text_view);
        itemView.setOnClickListener(this);
    }

    void bind(Album album)
    {
        this.album = album;
        albumTextView.setText(album.getAlbum());
        artistTextView.setText(album.getArtist());
        Glide.with(itemView.getContext())
                .load(album.getArtworkUrl())
                .into(albumImageView);
    }

    // Handles the row being being clicked
    @Override
    public void onClick(View view)
    {
        Context context = view.getContext();
        Intent intent = new Intent(context, AlbumDetailActivity.class);
        intent.putExtra(AlbumDetailActivity.EXTRA_ALBUM_ART_URL, album.getArtworkUrl());
        intent.putExtra(AlbumDetailActivity.EXTRA_ALBUM_NAME, album.getAlbum());
        intent.putExtra(AlbumDetailActivity.EXTRA_ARTIST_NAME, album.getArtist());
        intent.putExtra(AlbumDetailActivity.EXTRA_GENRE, album.getGenre());
        intent.putExtra(AlbumDetailActivity.EXTRA_RELEASE, album.getReleaseDate());
        intent.putExtra(AlbumDetailActivity.EXTRA_COPYRIGHT, album.getCopyright());
        intent.putExtra(AlbumDetailActivity.EXTRA_LINK, album.getUrl());
        context.startActivity(intent);
    }
}
