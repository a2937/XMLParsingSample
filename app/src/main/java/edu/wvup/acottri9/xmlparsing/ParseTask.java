package edu.wvup.acottri9.xmlparsing;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ParseTask extends AsyncTask<String, Void, ArrayList<Item>>
{
    private MainActivity activity;


    public ParseTask(MainActivity activity)
    {
        this.activity = activity;
    }


    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param urls The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected ArrayList<Item> doInBackground(String... urls)
    {
        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SAXHandler handler = new SAXHandler();
            saxParser.parse(urls[0],handler);
            return handler.getItems();
        }
        catch (Exception e)
        {
            Log.w("MainActivity",e.getMessage());
           // Toast.makeText(activity,"Sorry - There was a problem parsing", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    protected void onPostExecute(ArrayList<Item> returnedItems)
    {
        activity.displayList(returnedItems);
    }


}

