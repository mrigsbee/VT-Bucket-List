package mrigsbee.vtbucketlist;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> items;
    private final ArrayList<Boolean> checkboxes;

    public Adapter(Activity context, ArrayList<String> items, ArrayList<Boolean> checkboxes) {
        super(context, R.layout.row, items);

        this.context=context;
        this.items=items;
        this.checkboxes=checkboxes;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.row, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        txtTitle.setText(items.get(position));
        if(checkboxes.get(position)){
            imageView.setImageResource(R.drawable.ic_checkbox);
        } else {
            imageView.setImageResource(R.drawable.ic_checkbox_outline);
        }
        return rowView;
    }

    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }
}