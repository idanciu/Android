package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.intern.myapplication.FacturiActivity;
import com.example.intern.myapplication.OnSwipeTouchListener;
import com.example.intern.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import Commons.Factura;
import CustomAdapters.DateGeneraleAdapter;

public class ClientFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_client, container, false);
    }
    Integer[] imgid = {
            R.drawable.nume_client,
            R.drawable.cod_inregistrare,
            R.drawable.cod_fiscal,
            R.drawable.banca,
            R.drawable.cod_iban,
            R.drawable.adresa
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Client");
        final Bundle bundle = this.getArguments();
        Factura factura = bundle.getParcelable("object");

        Spanned numc = Html.fromHtml("Nume companie"+ "<br> <b>" + vNumComp(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned codi = Html.fromHtml("Cod inregistrare"+ "<br> <b>" + vCodIn(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned codf = Html.fromHtml("Cod fiscal"+ "<br> <b>" + vCodFis(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned banca = Html.fromHtml("Banca"+ "<br> <b>" + vBanca(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned iban = Html.fromHtml("Cod IBAN"+ "<br> <b>" + vIBAN(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned adr = Html.fromHtml("Adresa"+ "<br> <b>" + vAdresa(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);

        ListView date_gen = (ListView) getActivity().findViewById(R.id.lista_client);
        List<Spanned> listClient = new ArrayList<Spanned>();
        listClient.add(numc);
        listClient.add(codi);
        listClient.add(codf);
        listClient.add(banca);
        listClient.add(iban);
        listClient.add(adr);
        //ArrayAdapter<Spanned> arrayAdapter = new ArrayAdapter<Spanned>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,listClient );
        final DateGeneraleAdapter arrayAdapter = new DateGeneraleAdapter(getActivity(), listClient, imgid);
        date_gen.setAdapter(arrayAdapter);


        Button butonDateGen = (Button) getActivity().findViewById(R.id.flow_date_gen);

        butonDateGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DateGeneraleFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.activity_facturi_list, fragment);
                fragment.setArguments(bundle);
                ft.commit();
            }
        });

        Button butonFurizor = (Button) getActivity().findViewById(R.id.flow_furnizor);

        butonFurizor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FurnizorFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.activity_facturi_list, fragment);
                fragment.setArguments(bundle);
                ft.commit();
            }
        });

        Button butonClienti = (Button) getActivity().findViewById(R.id.flow_clienti);

        butonClienti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ClientFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.activity_facturi_list, fragment);
                fragment.setArguments(bundle);
                ft.commit();
            }
        });

        date_gen.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeLeft() {
                Fragment fragment = new FurnizorFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.activity_facturi_list, fragment);
                fragment.setArguments(bundle);
                ft.commit();
            }

            @Override
            public void onSwipeRight() {
                Fragment fragment = new DateGeneraleFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.activity_facturi_list, fragment);
                fragment.setArguments(bundle);
                ft.commit();
            }
        });
    }
    private String vNumComp(Factura factura){
        if (factura.getClient() != null && factura.getClient().getNume() != null)
            return String.valueOf(factura.getClient().getNume());
        else
            return "Nu exista inregistrare";
    }
    private String vCodIn(Factura factura){
        if (factura.getClient() != null && factura.getClient().getCodInregistrare() != null)
            return String.valueOf(factura.getClient().getCodInregistrare());
        else
            return "Nu exista inregistrare";
    }
    private String vCodFis(Factura factura){
        if (factura.getClient() != null && factura.getClient().getCodFiscal() != null)
            return String.valueOf(factura.getClient().getCodFiscal());
        else
            return "Nu exista inregistrare";
    }
    private String vBanca(Factura factura){
        if (factura.getClient() != null && factura.getClient().getBanca() != null)
            return String.valueOf(factura.getClient().getBanca());
        else
            return "Nu exista inregistrare";
    }
    private String vIBAN(Factura factura){
        if (factura.getClient() != null && factura.getClient().getIban() != null)
            return String.valueOf(factura.getClient().getIban());
        else
            return "Nu exista inregistrare";
    }
    private String vAdresa(Factura factura){
        if (factura.getClient() != null && factura.getClient().getAdresa() != null)
            return String.valueOf(factura.getClient().getAdresa());
        else
            return "Nu exista inregistrare";
    }
}
