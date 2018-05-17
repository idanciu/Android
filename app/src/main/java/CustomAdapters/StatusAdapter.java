package CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intern.myapplication.R;

import java.util.List;

import Commons.StatusCount;

/**
 * Created by idanciu on 9/21/2017.
 */

public class StatusAdapter extends ArrayAdapter<StatusCount>{
    private final Context context;
    private List<StatusCount> list;
    private Integer imgid[];

    int[] listItemBackground = new int[] { R.layout.list_background1,
            R.layout.list_background2 };


    public StatusAdapter(Activity context, List<StatusCount> itemname, Integer imgid[]) {
        super(context, R.layout.status_list, itemname);
        this.context = context;
        this.list = itemname;
        this.imgid = imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.status_list, null,true);


        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.Itemname);


        imageView.setImageResource(imgid[position]);
        extratxt.setText((CharSequence) list.get(position).getStatus() + " (" + list.get(position).getNrFacturi() + " facturi)");
//        if(list.get(position).getStatus().equals("VALIDAT")) {
//            extratxt.setTextColor(context.getResources().getColor(R.color.colorValidat));
//        } else if(list.get(position).getStatus().equals("DRAFT")) {
//            extratxt.setTextColor(context.getResources().getColor(R.color.colorDraft));
//        } else  if(list.get(position).getStatus().equals("FINALIZAT")) {
//            extratxt.setTextColor(context.getResources().getColor(R.color.colorFinalizat));
//        } else  if(list.get(position).getStatus().equals("ACTIVAT")) {
//            extratxt.setTextColor(context.getResources().getColor(R.color.colorActiv));
//        } else  if(list.get(position).getStatus().equals("ARHIVAT")) {
//            extratxt.setTextColor(context.getResources().getColor(R.color.colorArhivat));
//        } else  if(list.get(position).getStatus().equals("EMIS")) {
//            extratxt.setTextColor(context.getResources().getColor(R.color.colorEmis));
//        }
//        int listItemBackgroundPosition = position % listItemBackground.length;
//        rowView.setBackgroundResource(listItemBackground[listItemBackgroundPosition]);
        return rowView;

    };
}
