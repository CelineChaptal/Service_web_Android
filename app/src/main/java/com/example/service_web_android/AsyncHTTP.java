package com.example.service_web_android;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncHTTP extends AsyncTask<String, Integer, String> {

    private AppCompatActivity myActivity;

    public AsyncHTTP(AppCompatActivity mainActivity) {
        myActivity = mainActivity;
    }

    @Override
    protected String doInBackground(String... strings) {
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        Log.i("Service Web", "Initialisation");
        try {
            url = new URL(strings[0]);
            Log.i("Service Web", "Connexion");
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            Log.i("Service Web", "Lecture");
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream
            result = readStream(in); // Read stream
            Log.i("Service Web", "Fin lecture");
        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        finally { if (urlConnection != null)
            urlConnection.disconnect();  }
        Log.i("Service Web", result);
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        TextView text = (TextView) myActivity.findViewById(R.id.textView3);
        text.setText(s); // Updates the textview
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}
