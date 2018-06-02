package edu.wvup.acottri9.xmlparsing;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private ListView listView;
    private ArrayList<Item> listItems;

    private final String URL = "http://www.cbn.com/cbnnews/world/feed/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        ParseTask task = new ParseTask(this);
        task.execute(URL);

    }

    public void displayList(ArrayList<Item> items)
    {
        listItems = items;
        if(items != null)
        {

           ArrayList<String> titles = new ArrayList<String>();
           for(Item item : items)
           {
               titles.add(item.getTitle());
           }
               ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,titles);
               listView.setAdapter(adapter);
               ListItemHandler listItemHandler = new ListItemHandler();
               listView.setOnItemClickListener(listItemHandler);
        }
        else
        {
            Toast.makeText(this,"Sorry - No data found", Toast.LENGTH_LONG).show();
        }

    }

    private class ListItemHandler implements AdapterView.OnItemClickListener
    {

        /**
         * Callback method to be invoked when an item in this AdapterView has
         * been clicked.
         * <p>
         * Implementers can call getItemAtPosition(position) if they need
         * to access the data associated with the selected item.
         *
         * @param parent   The AdapterView where the click happened.
         * @param view     The view within the AdapterView that was clicked (this
         *                 will be a view provided by the adapter)
         * @param position The position of the view in the adapter.
         * @param id       The row id of the item that was clicked.
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Item selectedItem = listItems.get(position);
            Uri uri = Uri.parse(selectedItem.getLink());
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(browserIntent);
        }
    }
}
