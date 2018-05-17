package Networking;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.intern.myapplication.MainActivity;

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
import java.util.Calendar;
import java.util.Date;

import Commons.DestinatarMesaj;
import Commons.Mesaj;
import Commons.Utilizator;
import Utils.Constant;

import static Utils.Constant.SIMPLE_DATE_FORMAT;

/**
 * Created by intern on 9/22/2017.
 */

public class HttpConnectionDestinatarMesaj  extends AsyncTask<String, Void, DestinatarMesaj> implements Constant {

    URL url;
    HttpURLConnection connection;

    @Override
    protected DestinatarMesaj doInBackground(String... params) {
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

    private DestinatarMesaj parseHttpResponse(String JSONString) throws JSONException, ParseException {
        DestinatarMesaj destinatarMesaj = null;
        JSONArray jsonArray = new JSONArray(JSONString);
        for (int i = 0; i < jsonArray.length(); i++) {
            SharedPreferences preferenceSettings = MainActivity.getContext().getSharedPreferences(PREFERENCE_FILE, PREFERENCE_MODE_PRIVATE);
            Integer idAngajat = preferenceSettings.getInt(ANGAJAT_PREFERENCE_KEY, 0);
            JSONObject jsonDestinatar = jsonArray.getJSONObject(i);
            JSONObject jsonAngajat = jsonDestinatar.getJSONObject("destinatar");
            Integer idDestinatar = jsonAngajat.getInt("id");
            if(idDestinatar == idAngajat) {
                JSONObject jsonMesaj = jsonDestinatar.getJSONObject("mesaj");
                Integer idMesaj = jsonMesaj.getInt("id");
                destinatarMesaj = new DestinatarMesaj(idMesaj, idDestinatar);
            }
        }
        //Log.i("destinatar", String.valueOf(destinatarMesaj.getIdAngajat()));
        return destinatarMesaj;
    }
}
