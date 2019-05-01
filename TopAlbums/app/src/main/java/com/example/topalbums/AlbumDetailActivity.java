package com.example.topalbums;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class AlbumDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ALBUM_ART_URL = "album_art";
    public static final String EXTRA_ALBUM_NAME = "album_name";
    public static final String EXTRA_ARTIST_NAME = "album_artist_name";
    public static final String EXTRA_GENRE = "album_genre";
    public static final String EXTRA_RELEASE = "album_release";
    public static final String EXTRA_COPYRIGHT = "album_copyright";
    public static final String EXTRA_LINK = "album_view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        final Intent intent = getIntent();
        ImageView albumArtView = findViewById(R.id.album_image);
        Glide.with(this)
                .load(intent.getStringExtra(EXTRA_ALBUM_ART_URL))
                .into(albumArtView);

        TextView albumNameView = findViewById(R.id.album_name_text);
        albumNameView.setText(intent.getStringExtra(EXTRA_ALBUM_NAME));

        TextView artistNameView = findViewById(R.id.artist_name_text);
        artistNameView.setText(intent.getStringExtra(EXTRA_ARTIST_NAME));

        TextView genreView = findViewById(R.id.genre_text);
        genreView.setText(intent.getStringExtra(EXTRA_GENRE));

        TextView releaseDateView = findViewById(R.id.release_text);
        releaseDateView.setText(intent.getStringExtra(EXTRA_RELEASE));

        TextView copyrightView = findViewById(R.id.copyright_text);
        copyrightView.setText(intent.getStringExtra(EXTRA_COPYRIGHT));

        Button openLinkButton = findViewById((R.id.open_link_button));
        openLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse(intent.getStringExtra(AlbumDetailActivity.EXTRA_LINK));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });
    }
}
