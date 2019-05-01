package com.example.topalbums.data;

import java.util.List;

public class Album
{
    private String artistName;
    private String id;
    private String releaseDate;
    private String name;
    private String kind;
    private String copyright;
    private String artistId;
    private String contentAdvisoryRating;
    private String artistUrl;
    private String artworkUrl100;
    private List<Genre> genres;
    private String url;

    public String getAlbum() { return name; }
    public String getArtist() { return artistName; }
    public String getArtworkUrl() { return artworkUrl100; }
    public String getGenre()
    {
        if (genres != null && !genres.isEmpty())
            return genres.get(0).getGenre();
        return null;
    }
    public String getReleaseDate() { return releaseDate; }
    public String getCopyright() { return copyright; }
    public String getUrl() { return url; }
}
