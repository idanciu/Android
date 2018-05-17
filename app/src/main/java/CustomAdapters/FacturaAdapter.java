package CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intern.myapplication.R;

import java.util.List;

import Commons.Factura;
import Commons.SerieFactura;
import Commons.StatusCount;
import Utils.Constant;

/**
 * Created by idanciu on 9/21/2017.
 */

public class FacturaAdapter extends ArrayAdapter<Factura> implements Constant{
    private final Context context;
    private List<Factura> list;
    private Integer imgid;

    int[] listItemBackground = new int[] { R.layout.list_background1,
            R.layout.list_background2 };


    public FacturaAdapter(Activity context, List<Factura> itemname, Integer imgid) {
        super(context, R.layout.factura_list, itemname);
        this.context = context;
        this.list = itemname;
        this.imgid = imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.factura_list, null,true);


        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView facturaLabel = (TextView) rowView.findViewById(R.id.tv_factura_label);
        TextView serie = (TextView) rowView.findViewById(R.id.tv_serie);
        TextView numar = (TextView) rowView.findViewById(R.id.tv_nr);
        TextView data = (TextView) rowView.findViewById(R.id.tv_data);
        TextView status = (TextView) rowView.findViewById(R.id.tv_status);
        TextView suma = (TextView) rowView.findViewById(R.id.tv_suma);
        TextView client = (TextView) rowView.findViewById(R.id.tv_client);


        imageView.setImageResource(imgid);
        facturaLabel.setText("Fact. nr. ");
        if(list.get(position).getSerieFactura() != null) {
            serie.setText(list.get(position).getSerieFactura().getCod());
            if(list.get(position).getSerieFactura().getSecventa() != null) {
                numar.setText(" " + String.valueOf(list.get(position).getSerieFactura().getSecventa()));
            } else {
                numar.setText("");
            }
        } else {
            serie.setText("");
        }

        if(list.get(position).getDtEmitere() != null) {
            data.setText(SIMPLE_DATE_FORMAT.format(list.get(position).getDtEmitere()));
        } else {
            data.setText("");
        }

        if(list.get(position).getStatusFactura() != null) {
            status.setText((CharSequence) list.get(position).getStatusFactura().getStatus());
            if(list.get(position).getStatusFactura().getStatus().equals("VALIDAT")) {
                status.setTextColor(context.getResources().getColor(R.color.colorValidat));
            } else if(list.get(position).getStatusFactura().getStatus().equals("DRAFT")) {
                status.setTextColor(context.getResources().getColor(R.color.colorDraft));
            } else  if(list.get(position).getStatusFactura().getStatus().equals("FINALIZAT")) {
                status.setTextColor(context.getResources().getColor(R.color.colorFinalizat));
            } else  if(list.get(position).getStatusFactura().getStatus().equals("ACTIVAT")) {
                status.setTextColor(context.getResources().getColor(R.color.colorActiv));
            } else  if(list.get(position).getStatusFactura().getStatus().equals("ARHIVAT")) {
                status.setTextColor(context.getResources().getColor(R.color.colorArhivat));
            } else  if(list.get(position).getStatusFactura().getStatus().equals("EMIS")) {
                status.setTextColor(context.getResources().getColor(R.color.colorEmis));
            }
        } else {
            status.setText("");
        }
        if(Double.valueOf(list.get(position).getSuma()) != null && list.get(position).getMoneda() != null) {
            suma.setText((CharSequence) String.valueOf(list.get(position).getSuma() + " " + list.get(position).getMoneda().getCod()));
        }

        if(list.get(position).getClient() != null) {
            client.setText((CharSequence) list.get(position).getClient().getNume());
        }
//        int listItemBackgroundPosition = position % listItemBackground.length;
//        rowView.setBackgroundResource(listItemBackground[listItemBackgroundPosition]);
        return rowView;

    };
}
