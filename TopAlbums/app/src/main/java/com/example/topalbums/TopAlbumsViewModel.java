package com.example.topalbums;

import android.os.AsyncTask;
import android.util.Log;

import com.example.topalbums.data.TopAlbums;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TopAlbumsViewModel extends ViewModel
{
    final MutableLiveData<TopAlbums> topAlbumsMutableLiveData = new MutableLiveData<>();

    MutableLiveData<TopAlbums> getTopAlbums() { return topAlbumsMutableLiveData; }

    void loadTopAlbums()
    {
        GetTopAlbumsTask topAlbumsTask = new GetTopAlbumsTask();
        topAlbumsTask.execute();
    }

    private class GetTopAlbumsTask extends AsyncTask<String, Void, TopAlbums>
    {
        private static final String URL_TOP_ALBUMS = "https://rss.itunes.apple.com/api/v1/us/apple-music/top-albums/all/25/explicit.json";

        public byte[] getUrlBytes() throws IOException
        {
            URL url = new URL(URL_TOP_ALBUMS);
            HttpURLConnection connection  = (HttpURLConnection)url.openConnection();

            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                InputStream in = connection.getInputStream();
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new IOException(connection.getResponseMessage());
                }

                int bytesRead = 0;
                byte[] buffer = new byte[1024];
                while ((bytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
                out.close();
                return out.toByteArray();
            } finally {
                connection.disconnect();
            }

        }

        @Override
        protected TopAlbums doInBackground(String... params)
        {
            TopAlbums topAlbums = null;

            try {
                topAlbums = new Gson().fromJson(new String(getUrlBytes()), TopAlbums.class);
            } catch (IOException ioe) {
                Log.e("GetTopAlbumsTask", "failed to get top albums");
            }

            return topAlbums;
        }

        @Override
        protected void onPostExecute(TopAlbums topAlbums)
        {
            topAlbumsMutableLiveData.setValue(topAlbums);
        }
    }
}
