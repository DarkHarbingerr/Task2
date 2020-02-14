package com.komurdzhiev.task2;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest extends AsyncTask<String, Void, Films> {
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 5000;
    public static final int CONNECTION_TIMEOUT = 5000;

    @Override
    protected Films doInBackground(String... params) {
        String stringURL = params[0];
        String inputLine;
        String result = "";

        try {
            URL sourceURL = new URL(stringURL);
            HttpURLConnection connection = (HttpURLConnection) sourceURL.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.connect();

            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());

            BufferedReader bufferedReader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            bufferedReader.close();

            streamReader.close();

            result = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        return gson.fromJson(result, Films.class);
    }
}

/*
class DownloadImage extends AsyncTask<String, Integer, Bitmap> {

    ImageView imageView;
    String imageURL;

    public DownloadImage(ImageView imageView) {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String... urls) {

        imageURL = urls[0];
        Bitmap bitmap = null;

        URL url = null;
        try {
            url = new URL(imageURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(true);
            bitmap = BitmapFactory.decodeStream((InputStream)connection.getContent());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        //if(imageURL.equals(imageView.getTag())) {
            imageView.setImageBitmap(result);
        //}
    }
}*/
