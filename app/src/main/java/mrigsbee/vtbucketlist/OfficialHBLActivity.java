package mrigsbee.vtbucketlist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.enabled;
import static android.R.attr.entries;
import static mrigsbee.vtbucketlist.R.id.newItemText;


public class OfficialHBLActivity  extends AppCompatActivity {

    ListView list;
    ArrayList<String> items;
    Toolbar toolbar;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officialhbl);

        db = DatabaseHandler.getInstance(this);

        initToolBar();
        initList();

        list=(ListView)findViewById(R.id.hblist);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                items );

        list.setAdapter(arrayAdapter);

        /*
            On click: item is saved to user's bucket list
         */
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long arg3) {
                String select = (list.getItemAtPosition(position).toString());
                db.add( new TableEntry (select));
                Toast.makeText(OfficialHBLActivity.this, "\"" + select.trim() + "\" was added to your list", Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }

    public void initList(){
        items = new ArrayList<>();

        items.add("Jump to Enter Sandman at a home football game");
        items.add("Run through the tunnel in Lane Stadium and touch the Hokie Stone for good luck");
        items.add("Participate in Virginia Tech’s Relay for Life, the largest collegiate Relay for Life in the world! ");
        items.add("Visit the April 16th Memorial and/or attend an April 16th Remembrance event.");
        items.add("Eat at the Homeplace in Catawba");
        items.add("Watch a sunset from the War Memorial Pylons");
        items.add("Learn the words to the Old Hokie cheer");
        items.add("Practice our school motto, Ut Prosim (That I May Serve), and participate in The Big Event");
        items.add("Learn the words to the VT Alma Mater");
        items.add("Hike the Cascades – even better when the Cascades are frozen!");
        items.add("Experience all four seasons of Blacksburg on the Drillfield – sometimes in the same week! ");
        items.add("Get a picture with the HokieBird");
        items.add("Wear the Tradition – order your class ring!");
        items.add("Learn the words to Tech Triumph, the Virginia Tech fight song");
        items.add("Memorize the names of the 8 Pylons at the War Memorial – Brotherhood, Honor, Leadership, " +
                "Sacrifice, Service, Loyalty, Duty, and Ut Prosim");
        items.add("Visit the Virginia Tech museum at the Holtzman Alumni Center");
        items.add("Enjoy a picnic at the Duckpond or Gazebo, and see VT’s oldest building, Solitude");
        items.add("Celebrate a big win in Lane Stadium with 66,233 of your closest friends! ");
        items.add("High-five the HokieBird");
        items.add("Go to at least one away football game");
        items.add("Cheer with the Cassell Guard at a home basketball game");
        items.add("Attend an SAA-sponsored Spirit Rally");
        items.add("Welcome new Hokies by participating in Hokie Helpers, Hokie Hi Picnic, Orientation, Gobblerfest, or Hokie " +
                "Camp");
        items.add("Learn how to gobble like a true Hokie");
        items.add("Take a picture of the place that means the most to you on campus");
        items.add("Attend a Hokie sporting event, other than football or basketball");
        items.add("Attend the annual Midwinters Dance, a traditional event for the entire university community held in the " +
                "Commonwealth Ballroom of Squires Student Center");
        items.add("Hike to McAfee’s Knob and get your picture taken on the edge. Avoid the crowds - go on a weekday!");
        items.add("Hike Dragon’s Tooth. Bring enough water and practice Leave No Trace on the trail");
        items.add("Help someone in need and participate in Actively Caring for People");
        items.add("Sing karaoke at TOTS Tuesday");
        items.add("Meet the University President. (Get a selfie with Dr. Sands!)");
        items.add("Attend a concert in Burruss Hall");
        items.add("Play an intramural sport");
        items.add("Eat a turkey leg at a home football game");
        items.add("Attend your class’s Ring Premiere");
        items.add("Experience the first snow of the year on the Drillfield");
        items.add("Attend a Virginia Tech Homecoming week event");
        items.add("Go tubing or paddling down the New River");
        items.add("Attend Ring Dance");
        items.add("Eat lobster at West End or hibachi at Turner Place");
        items.add("Go to a holiday dinner at D2");
        items.add("Eat Sunday Brunch at West End or D2");
        items.add("Support Downtown Blacksburg and eat at PK’s, Mike’s Grill, and The Cellar");
        items.add("Eat a gyro at Souvlaki");
        items.add("Eat brunch at Gillie’s");
        items.add("Visit the Farmer’s Market in Downtown Blacksburg on Wednesdays or Saturdays");
        items.add("Get a parking ticket from Virginia Tech Parking Services (Let’s face it: it’s inevitable)");
        items.add("Find all the gargoyles on campus. Hint: there are 14!");
        items.add("Study in Torgersen Bridge or at the Math Empo");
        items.add("Act out the Principles of Community, embrace Aspirations of Student Learning, and practice Hokies Respect");
        items.add("Get a picture with a painted HokieBird around Blacksburg. Hint: there are 69 Gobble de Art statues!");
        items.add("Wear the Orange and Maroon Hokie Effect shirts to the corresponding football games");
        items.add("Wear the HokieZone t-shirt with Hokie Pride.");
        items.add("Walk, run, or bike down the Huckleberry Trail");
        items.add("Take a class that seems interesting, even if it has nothing to do with your major! (Highly suggest John Boyer’s " +
                "World Regions class)");
        items.add("Ride on the Blacksburg Transit’s Ring Bus");
        items.add("Go to the Merryman Athletic Facility and visit the football museum");
        items.add("Play a game of pool or go bowling in the BreakZONE");
        items.add(" Rent a canoe or other equipment from Venture Out for an outdoor adventure!");
        items.add("Take an exercise class at War Memorial or McComas Hall. (Hint: they have free group exercise classes during " +
                "the first week of each semester)");
        items.add("Watch a movie at The Lyric Theatre. Don’t forget, free popcorn on Mondays!");
        items.add("Visit Perspectives Gallery in Squires to view the newest art exhibit");
        items.add("Visit Mountain Lake to hike the trails and do the self-guided tour of the Dirty Dancing movie locations at the " +
                "hotel and grounds");
        items.add("Watch a movie at the Starlite Drive-In Theater in Christiansburg");
        items.add("Experience a cultural event on campus, such as the International Street Fair, a Martin Luther King Jr. event, " +
                "Dwali, etc.");
        items.add("Enjoy a Virginia Slice from Benny’s!");
        items.add("Get a picture with the biggest VT on campus. Hint: it’s on Upper Quad!");
        items.add("Enjoy donuts from Carol Lee Donut Shop");
        items.add("Participate in a fraternity or sorority philanthropy event");
        items.add("Watch a play put on by the School of Performing Arts and Cinema or attend a performance at the Moss Arts " +
                "Center");
        items.add("GRADUATE!");
    }

    public void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.app_name);
            toolbar.setTitle("Official Hokie Bucket List");
            toolbar.setNavigationIcon(R.drawable.ic_menu);
            toolbar.setNavigationOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(OfficialHBLActivity.this, MainActivity.class);
                            OfficialHBLActivity.this.startActivity(myIntent);
                        }
                    }
            );
        }
    }
}
