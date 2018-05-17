package CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intern.myapplication.R;

import java.util.List;

import Commons.Utilizator;

/**
 * Created by idanciu on 9/21/2017.
 */

public class UserAdapter extends ArrayAdapter<Utilizator> {
    private final Context context;
    private List<Utilizator> list;
    private Integer imgid;
    int[] listItemBackground = new int[] { R.layout.list_background1,
            R.layout.list_background2 };

    public UserAdapter(Activity context, List<Utilizator> itemname, Integer imgid) {
        super(context, R.layout.user_list, itemname);
        this.context = context;
        this.list = itemname;
        this.imgid = imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.user_list, null,true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView numeLabel= (TextView) rowView.findViewById(R.id.tv_nume_label);
        TextView nume= (TextView) rowView.findViewById(R.id.tv_nume);
        TextView usernameLabel= (TextView) rowView.findViewById(R.id.tv_username_label);
        TextView username = (TextView) rowView.findViewById(R.id.tv_username);

        imageView.setImageResource(imgid);

        usernameLabel.setText("Username: ");
        username.setText(list.get(position).getUsername());
        numeLabel.setText("Nume: ");
        if(list.get(position).getAngajat() == null ) {
           nume.setText(list.get(position).getNume());
        } else {
           nume.setText(list.get(position).getAngajat().getNume());
        }
//        int listItemBackgroundPosition = position % listItemBackground.length;
//        rowView.setBackgroundResource(listItemBackground[listItemBackgroundPosition]);

        return rowView;

    };
}
