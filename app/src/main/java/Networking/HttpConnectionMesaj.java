package Networking;

import android.os.AsyncTask;
import android.preference.PreferenceManager;
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

import Commons.Angajat;
import Commons.DestinatarMesaj;
import Commons.Mesaj;
import Commons.SerieFactura;
import Commons.Utilizator;
import Utils.Constant;

/**
 * Created by intern on 9/22/2017.
 */

public class HttpConnectionMesaj extends AsyncTask<String, Void, ArrayList<Mesaj>> implements Constant{

    URL url;
    HttpURLConnection connection;
    ArrayList<DestinatarMesaj> listaDestinatari = new ArrayList<>();


    public void consumeHttpConnectionDestinatari() {
        HttpConnectionDestinatarMesaj connection = new HttpConnectionDestinatarMesaj() {
            @Override
            protected void onPostExecute(DestinatarMesaj dest) {
                super.onPostExecute(dest);
                if(dest != null) {
                    listaDestinatari.add(dest);
                }
            }
        };
        //connection.execute("http://" + PreferenceManager.getDefaultSharedPreferences(MainActivity.getContext()).getString("ip", "192.168.8.98/kepres20") + "/api/rs/destinatarmesaj/list");
        connection.execute("https://api.myjson.com/bins/1f0w36");
    }


    @Override
    protected ArrayList<Mesaj> doInBackground(String... params) {
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

    private ArrayList<Mesaj> parseHttpResponse(String JSONString) throws JSONException, ParseException {
        consumeHttpConnectionDestinatari();
        ArrayList<Mesaj> listaMesaje = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(JSONString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonMesj = jsonArray.getJSONObject(i);
            Integer id = jsonMesj.getInt("id");
           // for(DestinatarMesaj d : listaDestinatari) {
             //   if(d.getIdMesaj() == id) {
                    Date data = null;
                    if(!jsonMesj.isNull("data")) {
                        Long dataLong = jsonMesj.getLong("data");
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(dataLong);
                        String dataString = Constant.SIMPLE_DATE_FORMAT.format(calendar.getTime());
                        data = Constant.SIMPLE_DATE_FORMAT.parse(dataString);
                    }
                    Utilizator expeditor = null;
                    if(!jsonMesj.isNull("expeditor")) {
                        JSONObject jsonUtilizator = jsonMesj.getJSONObject("expeditor");
                        expeditor = parseUtilizator(jsonUtilizator);
                    }

                    String titlu = jsonMesj.getString("titlu");
                    String continut = jsonMesj.getString("continut");
                    String trimis = jsonMesj.getString("trimis");
                    String citit = jsonMesj.getString("citit");

                    Mesaj mesaj = new Mesaj(id, data, titlu, continut, trimis, citit, expeditor);
                    listaMesaje.add(mesaj);
                }
           // }

       // }
        return listaMesaje;
    }

    private Utilizator parseUtilizator(JSONObject object) throws JSONException {
        String nume = object.getString("nume");
        return new Utilizator(nume, null, null, null);
    }


}
