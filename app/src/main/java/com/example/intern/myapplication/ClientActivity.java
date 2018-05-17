package com.example.intern.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Commons.ClientCount;
import Commons.StatusCount;
import Networking.HttpConnectionClient;
import Networking.HttpConnectionStatus;
import Utils.Constant;

public class ClientActivity extends Fragment implements Constant {
    public static String client = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        consumeHttpConnection();
        return inflater.inflate(R.layout.activity_client, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Client factura");
    }

    ListView lvClient;
    ArrayList<ClientCount> listaClienti = new ArrayList<>();

    public void initComponents() {
        lvClient = (ListView) getActivity().findViewById(R.id.lista_lv_clienti);
        final ArrayAdapter<ClientCount> adapter = new ArrayAdapter<ClientCount>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, listaClienti);
        lvClient.setAdapter(adapter);
        lvClient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClientCount clientObj = adapter.getItem(position);
                client = clientObj.getNume();

            }
        });

    }

    public void consumeHttpConnection() {
        HttpConnectionClient connection = new HttpConnectionClient() {
            @Override
            protected void onPostExecute(ArrayList<ClientCount> clients) {
                initComponents();
                super.onPostExecute(clients);
                if (clients != null) {
                    listaClienti.addAll(clients);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.toast_lvClient), Toast.LENGTH_SHORT).show();
                }
            }
        };
        //connection.execute("http://" + PreferenceManager.getDefaultSharedPreferences(getContext()).getString("ip", "192.168.8.98/kepres205") + "/api/rs/client/list");
    }
}
