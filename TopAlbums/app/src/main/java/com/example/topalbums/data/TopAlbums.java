package com.example.topalbums.data;

import java.util.List;

public class TopAlbums
{
    private Feed feed;

    public List<Album> getAlbums() { return feed.getAlbums(); }
}
