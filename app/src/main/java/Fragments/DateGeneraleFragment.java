package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.intern.myapplication.FacturiActivity;
import com.example.intern.myapplication.MainActivity;
import com.example.intern.myapplication.OnSwipeTouchListener;
import com.example.intern.myapplication.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import Commons.Factura;
import CustomAdapters.DateGeneraleAdapter;
import Utils.Constant;


public class DateGeneraleFragment extends Fragment implements Constant {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date_generale, container, false);
    }

    Integer[] imgid = {
            R.drawable.data_estimata,
            R.drawable.data_emitere,
            R.drawable.serie,
            R.drawable.responsabil,
            R.drawable.moneda,
            R.drawable.tva,
            R.drawable.status_factura,
            R.drawable.data_scadenta,
            R.drawable.creat_de,
            R.drawable.validat_de,
            R.drawable.emis_de
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Date Generale");
        final Bundle bundle = this.getArguments();
        final Factura factura = bundle.getParcelable("object");

        Spanned dtest = Html.fromHtml("Dt. est. emit."+ "<br> <b>" + vDtEst(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned dtem = Html.fromHtml("Dt. emitere."+ "<br> <b>" + vDtEm(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned ser = Html.fromHtml("Serie factura"+ "<br> <b>" + vSerieCod(factura) + " " + vSerieSec(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned respon = Html.fromHtml("Responsabil"+ "<br> <b>" + vResponsabil(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned mon = Html.fromHtml("Moneda"+ "<br> <b>" + vMoneda(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned tv = Html.fromHtml("T.V.A."+ "<br> <b>" + vTVA(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned stat = Html.fromHtml("Status facura"+ "<br> <b>" + vStatus(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned dtscad = Html.fromHtml("Dt. scadenta"+ "<br> <b>" + vDtScad(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned creatde = Html.fromHtml("Creat de"+ "<br> <b>" + vCreatDe(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned valid = Html.fromHtml("Validat de"+ "<br> <b>" + vValidatDe(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);
        Spanned emis = Html.fromHtml("Emis de"+ "<br> <b>" + vEmisDe(factura) + "</b>", Html.FROM_HTML_MODE_LEGACY);


        ListView date_gen = (ListView) getActivity().findViewById(R.id.lista_date_genrale);
        List<Spanned> listDate = new ArrayList<Spanned>();
        listDate.add(dtest);
        listDate.add(dtem);
        listDate.add(ser);
        listDate.add(respon);
        listDate.add(mon);
        listDate.add(tv);
        listDate.add(stat);
        listDate.add(dtscad);
        listDate.add(creatde);
        listDate.add(valid);
        listDate.add(emis);
        //ArrayAdapter<Spanned> arrayAdapter = new ArrayAdapter<Spanned>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,listDate );
        final DateGeneraleAdapter arrayAdapter = new DateGeneraleAdapter(getActivity(), listDate, imgid);
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
                Fragment fragment = new ClientFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.activity_facturi_list, fragment);
                fragment.setArguments(bundle);
                ft.commit();
            }
        });

        }



    private String vDtEst(Factura factura){
        if (factura.getDtEstimata() != null)

            return SIMPLE_DATE_FORMAT.format(factura.getDtEstimata());
        else
            return "Nu exista inregistrare";
    }
    private String vDtEm(Factura factura){
        if (factura.getDtEmitere() != null)
            return SIMPLE_DATE_FORMAT.format(factura.getDtEmitere());
        else
            return "Nu exista inregistrare";
    }
    private String vSerieCod(Factura factura){
        if (factura.getSerieFactura() != null && factura.getSerieFactura().getCod() != null)
            return String.valueOf(factura.getSerieFactura().getCod());
        else
            return "Nu exista inregistrare";
    }
    private String vSerieSec(Factura factura){
        if (factura.getSerieFactura() != null && factura.getSerieFactura().getSecventa() != null)
            return String.valueOf(factura.getSerieFactura().getSecventa());
        else
            return "Nu exista inregistrare";
    }
    private String vResponsabil(Factura factura){
        if (factura.getAngajat() != null && factura.getAngajat().getNume() != null)
            return String.valueOf(factura.getAngajat().getNume());
        else
            return "Nu exista inregistrare";
    }
    private String vMoneda(Factura factura){
        if (factura.getMoneda() != null && factura.getMoneda().getNume() != null)
            return String.valueOf(factura.getMoneda().getNume());
        else
            return "Nu exista inregistrare";
    }
    private String vTVA(Factura factura){
        if (factura.getCotaTVA() != null && factura.getCotaTVA().getCod() != null)
            return String.valueOf(factura.getCotaTVA().getCod());
        else
            return "Nu exista inregistrare";
    }
    private String vStatus(Factura factura){
        if (factura.getStatusFactura() != null && factura.getStatusFactura().getStatus() != null)
            return String.valueOf(factura.getStatusFactura().getStatus());
        else
            return "Nu exista inregistrare";
    }
    private String vDtScad(Factura factura){
        if (factura.getDtScadenta() != null)
            return SIMPLE_DATE_FORMAT.format(factura.getDtScadenta());
        else
            return "Nu exista inregistrare";
    }
    private String vCreatDe(Factura factura){
        if (factura.getCreatDe() != null && factura.getCreatDe().getNume() != null)
            return String.valueOf(factura.getCreatDe().getNume());
        else
            return "Nu exista inregistrare";
    }
    private String vValidatDe(Factura factura){
        if (factura.getValidatDe() != null && factura.getValidatDe().getNume() != null)
            return String.valueOf(factura.getValidatDe().getNume());
        else
            return "Nu exista inregistrare";
    }
    private String vEmisDe(Factura factura){
        if (factura.getEmisDe() != null && factura.getEmisDe().getNume() != null)
            return String.valueOf(factura.getEmisDe().getNume());
        else
            return "Nu exista inregistrare";
    }
}


