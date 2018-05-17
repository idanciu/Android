package CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intern.myapplication.R;

import java.util.List;

import Commons.Mesaj;
import Commons.StatusCount;
import Utils.Constant;

/**
 * Created by idanciu on 9/25/2017.
 */

public class MesajAdapter extends ArrayAdapter<Mesaj> {
    private final Context context;
    private List<Mesaj> list;
    private Integer imgid;

    int[] listItemBackground = new int[] { R.layout.list_background1,
            R.layout.list_background2 };


    public MesajAdapter(Activity context, List<Mesaj> itemname, Integer imgid) {
        super(context, R.layout.mesaj_list, itemname);
        this.context = context;
        this.list = itemname;
        this.imgid = imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.mesaj_list, null,true);


        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView titluLabel = (TextView) rowView.findViewById(R.id.tv_mesaj_titlu_label);
        TextView titlu = (TextView) rowView.findViewById(R.id.tv_mesaj_titlu);
        TextView dataLabel = (TextView) rowView.findViewById(R.id.tv_data_label);
        TextView data = (TextView) rowView.findViewById(R.id.tv_data);
        TextView expeditorLabel = (TextView) rowView.findViewById(R.id.tv_expeditor_label);
        TextView expeditor = (TextView) rowView.findViewById(R.id.tv_expeditor);


        imageView.setImageResource(imgid);
        titluLabel.setText("Titlu: ");
        titlu.setText((CharSequence) list.get(position).getTitlu());
        if(data != null) {
            dataLabel.setText("Data: ");
            data.setText((CharSequence) Constant.SIMPLE_DATE_FORMAT.format(list.get(position).getData()));
        } else {
            data.setText("");
        }
        expeditorLabel.setText("Expeditor: ");
        expeditor.setText((CharSequence) list.get(position).getExpeditor().getNume());
        return rowView;

    };
}