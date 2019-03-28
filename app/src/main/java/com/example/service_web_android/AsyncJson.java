package com.example.service_web_android;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AsyncJson extends AsyncTask<String, Integer, JSONArray> {

    private AppCompatActivity myActivity;

    public AsyncJson(AppCompatActivity mainActivity) {
        myActivity = mainActivity;
    }

    @Override
    protected JSONArray doInBackground(String... strings) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress(10);

        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        Log.i("Service Web", "Initialisation");
        publishProgress(25);
        try {
            url = new URL(strings[0]);
            Log.i("Service Web", "Connexion");
            publishProgress(40);
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            Log.i("Service Web", "Lecture");
            publishProgress(55);
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream
            result = readStream(in); // Read stream
            Log.i("Service Web", "Fin lecture");
            publishProgress(70);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        Log.i("Service Web", result);
        publishProgress(85);
        JSONArray json = null;
        try {
            json = new JSONArray(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        publishProgress(100);
        return json;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        ProgressBar pb = (ProgressBar) myActivity.findViewById(R.id.progressBar);
        pb.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(JSONArray s) {
        ArrayList<Medecin> lesMedecins = new ArrayList<Medecin>();
        ProgressBar pb = (ProgressBar) myActivity.findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        try {
            for(int i = 0;i < s.length(); i++) {
                int num = Integer.parseInt(s.getJSONObject(i).getString("PRA_NUM"));
                String nom = s.getJSONObject(i).getString("PRA_NOM");
                String prenom = s.getJSONObject(i).getString("PRA_PRENOM");
                String adresse = s.getJSONObject(i).getString("PRA_ADRESSE");
                String codePostal = s.getJSONObject(i).getString("PRA_CP");
                String ville = s.getJSONObject(i).getString("PRA_VILLE");
                String libelle = s.getJSONObject(i).getString("TYP_LIBELLE");
                double coefNotoriete = Double.parseDouble(s.getJSONObject(i).getString("PRA_COEFNOTORIETE"));
                lesMedecins.add(new Medecin(num, nom, prenom, adresse, codePostal, ville, libelle, coefNotoriete));
            }
        }
        catch (JSONException e) { e.printStackTrace(); }
        TextView textResult = (TextView) myActivity.findViewById(R.id.txtResult);
        textResult.setText("");
        for (Medecin unMedecin : lesMedecins) {
            textResult.append("\n" + unMedecin.getNom() + " " + unMedecin.getPrenom() + ", " + unMedecin.getAdresse()
                        + " " + unMedecin.getCodePostal() + " " + unMedecin.getVille() + "\n");
        }
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
