package Networking;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Commons.Angajat;
import Commons.Factura;
import Commons.Utilizator;
import Utils.Constant;

/**
 * Created by idanciu on 9/18/2017.
 */

public class HttpConnectionAngajat extends AsyncTask<String, Void, ArrayList<Angajat>> implements Constant{
    URL url;
    HttpURLConnection connection;

    @Override
    protected ArrayList<Angajat> doInBackground(String... params) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            return parseHttpResponse(stringBuilder.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<Angajat> parseHttpResponse(String JSONString) throws JSONException, ParseException {
        ArrayList<Angajat> listaAngajati = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(JSONString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonAngajat = jsonArray.getJSONObject(i);
            Integer id = jsonAngajat.getInt("id");
            String nume = jsonAngajat.getString("nume");
            String cod = jsonAngajat.getString("cod");
            String email = jsonAngajat.getString("email");
            String telefon = jsonAngajat.getString("telefon");
            String memo = jsonAngajat.getString("memo");

            Angajat angajat = new Angajat(id, cod, nume, memo, email, telefon);
            listaAngajati.add(angajat);
        }

        return listaAngajati;
    }

}
