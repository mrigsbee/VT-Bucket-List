package mrigsbee.vtbucketlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity{

    ListView list;
    ArrayList<String> items;
    Adapter adapter;

    ArrayList<Boolean> checkboxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up lists
        items = new ArrayList<>();
        checkboxes = new ArrayList<>();

        //set up adapter
        adapter = new Adapter(this, items, checkboxes);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        // WHEN CLICKING AN ITEM, A TOAST APPEARS WITH THE TITLE OF THE ITEM
//        list.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // TODO Auto-generated method stub
//                String Slecteditem= items[+position];
//                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }

    public void onAddItem(View v) {
        EditText newItemText = (EditText) findViewById(R.id.newItemText);
        String itemText = newItemText.getText().toString();

        items.add(itemText);
        checkboxes.add(false);

        adapter.notifyDataSetChanged(); //Tell UI to display newly added item
    }
}
