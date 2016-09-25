package mrigsbee.vtbucketlist;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.id.input;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> items;
    Adapter adapter;

    ArrayList<Boolean> checkboxes;
    Toolbar toolbar;

    /*
        Used to "disable" onclicklistener for the list when the user is viewing
        the 'delete' dialog. Otherwise, the checkboxes can be checked/unchecked even
        when the dialog is open. This prevents accidental checking/un-checking of list
        items.
    */
    boolean enabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    enabled = true;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.app_name);
//            getSupportActionBar().setHomeButtonEnabled(true);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //set up lists
        items = new ArrayList<>();
        checkboxes = new ArrayList<>();

        //set up adapter
        adapter = new Adapter(this, items, checkboxes);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        /*
            On click: toggles check/un-checked box
         */
        list.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id){

                if(enabled) {
                    checkboxes.set(position, !checkboxes.get(position));

                    ImageView imageView = (ImageView) itemView.findViewById(R.id.icon);
                    if (checkboxes.get(position)) {
                        imageView.setImageResource(R.drawable.ic_checkbox);
                    } else {
                        imageView.setImageResource(R.drawable.ic_checkbox_outline);
                    }
                }
            }
        });
        /*
            On LONG click: Delete item (via alert dialog)
         */
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long arg3) {

                enabled = false;
                final int location = position;
                final String text = items.get(location);

                //ALERT: Ask the user if they really want to delete the list item. Must select
                //yes or no.
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle("Delete Item");
                dialogBuilder.setMessage("Are you sure you want to delete \"" + text.trim() + "\"?");
                dialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.remove(text);
                                checkboxes.remove(location);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "\"" + text.trim() + "\" was deleted", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                                enabled = true;
                            }
                        });
                dialogBuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                enabled = true;
                            }
                        });
                AlertDialog dialog = dialogBuilder.create();
                dialog.show();
                return false;
            }

        });
    }

    public void onAddItem(View v) {
        EditText newItemText = (EditText) findViewById(R.id.newItemText);
        String itemText = newItemText.getText().toString();

        items.add(itemText);
        checkboxes.add(false);

        adapter.notifyDataSetChanged(); //Tell UI to display newly added item
    }
}
