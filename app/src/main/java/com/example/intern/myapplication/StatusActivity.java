package com.example.intern.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Commons.StatusCount;
import CustomAdapters.StatusAdapter;
import Networking.HttpConnectionStatus;

public class StatusActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public static String status = "";
    ListView lvStatus;
    ArrayList<StatusCount> listaStatus = new ArrayList<>();

    Integer[] imgid = {
            R.drawable.validated,
            R.drawable.archived,
            R.drawable.finished,
            R.drawable.emitted,
            R.drawable.draft,
            R.drawable.activated
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        consumeHttpConnection();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    private void init() {
        lvStatus = (ListView) findViewById(R.id.lista_lv_status);
        //final ArrayAdapter<StatusCount> adapter = new ArrayAdapter<StatusCount>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, listaStatus);

        final StatusAdapter statusAdapter = new StatusAdapter(this, listaStatus, imgid);
        lvStatus.setAdapter(statusAdapter);
        lvStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StatusCount statusObj = statusAdapter.getItem(position);
                status = statusObj.getStatus();
                Intent intent = new Intent(getApplicationContext(), FacturiActivity.class);
                startActivity(intent);
            }
        });
    }

    public void consumeHttpConnection() {
        HttpConnectionStatus connection = new HttpConnectionStatus() {
            @Override
            protected void onPostExecute(ArrayList<StatusCount> status) {
                init();
                super.onPostExecute(status);
                if (status != null) {
                    listaStatus.addAll(status);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_lvStatus), Toast.LENGTH_SHORT).show();
                }
            }
        };
        //connection.execute("http://" + PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("ip", "192.168.8.98/kepres205") + "/api/rs/statusCount/list");
        connection.execute("https://api.myjson.com/bins/9bg7d");
        for(StatusCount s : listaStatus) {
            switch(s.getStatus()) {
                case "DRAFT":
                    s.setSorter(0);
                    break;
                case "VALIDAT":
                    s.setSorter(1);
                    break;
                case "ARHIVAT":
                    s.setSorter(2);
                    break;
                case "FINALIZAT":
                    s.setSorter(3);
                    break;
                case "ACTIVAT":
                    s.setSorter(4);
                    break;
                case "EMIS":
                    s.setSorter(5);
                    break;
            }
            Log.i("status", s.getSorter().toString());
        }
        Collections.sort(listaStatus, StatusCount.DESCENDING_COMPARATOR);
        //connection.execute("http://192.168.8.98/kepres205/api/rs/statusCount/list");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void displaySelectedScreen(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_facturi:
                Intent statusIntent = new Intent(getApplicationContext(), StatusActivity.class);
                startActivity(statusIntent);
                break;
            case R.id.nav_settings:
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settingsIntent);
                break;
            case R.id.nav_utilizatori:
                Intent userIntent = new Intent(getApplicationContext(), UtilizatoriActivity.class);
                startActivity(userIntent);
                break;
            case R.id.nav_login:
                Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.nav_mesaj:
                Intent mesajIntent = new Intent(getApplicationContext(), MesajeActivity.class);
                startActivity(mesajIntent);
                break;
        }
        if (fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }
}
