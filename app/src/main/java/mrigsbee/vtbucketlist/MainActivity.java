package mrigsbee.vtbucketlist;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> items;

    ArrayList<Boolean> checkboxes;
    Toolbar toolbar;

    DatabaseHandler db;

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

        db = new DatabaseHandler(this);

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

        list=(ListView)findViewById(R.id.list);
        refreshUI();

        final EditText newItemText = (EditText) findViewById(R.id.newItemText);
        newItemText.setSingleLine(true); // Allows "enter" to close keyboard

        newItemText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //Close keyboard, add item to list, and clear the editor
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    addItem();
                    newItemText.setText("");
                    return true;
                }
                return false;
            }
        });

        /*
            On click: toggles check/un-checked box
         */
        list.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id){

                if(enabled) {
                    List<TableEntry> entries = db.getAll();
                    TableEntry entry = entries.get(position);
                    if(entry.getCheckbox().equals(TableEntry.CHECKBOX_OUTLINE)) entry.setCheckbox(TableEntry.CHECKBOX);
                    else entry.setCheckbox(TableEntry.CHECKBOX_OUTLINE);
                    db.update(entry);
                    refreshUI();
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
                List<TableEntry> entries = db.getAll();
                final TableEntry entry = entries.get(location);
                final String text = entry.getEntry();

                //ALERT: Ask the user if they really want to delete the list item. Must select
                //yes or no.
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle("Delete Item");
                dialogBuilder.setMessage("Are you sure you want to delete \"" + text.trim() + "\"?");
                dialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                db.delete(entry);
                                refreshUI();

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

    public void addItem(){
        EditText newItemText = (EditText) findViewById(R.id.newItemText);
        String itemText = newItemText.getText().toString();

        db.add( new TableEntry (itemText));
        refreshUI();

    }

    /*
        Updates the UI. Call this when changes are made to the database.
     */
    public void refreshUI(){
        Cursor cursor = db.getAllRows();
        String[] fromFieldNames = new String[] {
                DatabaseHandler.KEY_CHECKBOX,
                DatabaseHandler.KEY_ENTRY
        };

        int[] toViewId = new int[] {
                R.id.icon,
                R.id.item
        };

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
                this, R.layout.row, cursor, fromFieldNames, toViewId, 0);

        list.setAdapter(cursorAdapter);

        cursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue (View view, Cursor cursor, int columnIndex){
                if (view.getId() == R.id.icon) {
                    ImageView imageView = (ImageView) view.findViewById(R.id.icon);

                    String image_name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.KEY_CHECKBOX));
                    int id = getResources().getIdentifier(image_name, "drawable", getPackageName());
                    imageView.setImageResource(id);

                    return true;
                }
                return false;
            }
        });


    }
}