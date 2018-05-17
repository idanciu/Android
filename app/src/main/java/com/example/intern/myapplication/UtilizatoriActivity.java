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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Commons.Utilizator;
import CustomAdapters.UserAdapter;
import Networking.HttpConnectionUtilizatori;

public class UtilizatoriActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Integer imgid = R.drawable.usercard;
    ListView lvUtilizatori;
    ArrayList<Utilizator> listaUtilizatori = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilizatori);
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
        lvUtilizatori = (ListView) findViewById(R.id.lista_lv_utilizatori);
        //ArrayAdapter<Utilizator> adapter = new ArrayAdapter<Utilizator>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, listaUtilizatori);
        final UserAdapter userAdapter = new UserAdapter(this, listaUtilizatori, imgid);

        lvUtilizatori.setAdapter(userAdapter);
    }

    public void consumeHttpConnection() {
        HttpConnectionUtilizatori connection = new HttpConnectionUtilizatori() {
            @Override
            protected void onPostExecute(ArrayList<Utilizator> utilizators) {
                init();
                super.onPostExecute(utilizators);
                if (utilizators != null) {
                    listaUtilizatori.addAll(utilizators);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_lvUtilizatori), Toast.LENGTH_SHORT).show();
                }
            }
        };

        //connection.execute("http://" + PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("ip", "192.168.8.98/kepres205") + "/api/rs/utilizator/list");
        connection.execute("https://api.myjson.com/bins/8q0ll");
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
