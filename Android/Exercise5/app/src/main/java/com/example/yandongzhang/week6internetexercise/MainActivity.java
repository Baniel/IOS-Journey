package com.example.yandongzhang.week6internetexercise;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.com.adapter.NewsfeedAdapter;
import com.model.Newsfeed;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends Activity {



    public static final String JSON_DOWNLOAD_LOCATION = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20feed%20where%20url=%27www.abc.net.au%2Fnews%2Ffeed%2F51120%2Frss.xml%27&format=json";

    private ListView newsfeedListView;
    private ArrayList<Newsfeed> newsfeedsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("test news feed Internet?");

        newsfeedListView = (ListView)findViewById(R.id.newsListView);
        newsfeedsList = new ArrayList<Newsfeed>();




        new SetupNewsTask().execute(JSON_DOWNLOAD_LOCATION);



        newsfeedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                pushToNewsDetail(position);

            }
        });


        ConnectivityManager con=(ConnectivityManager)getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi=con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean internet=con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        if(wifi|internet){
        }else{
            Toast.makeText(getApplicationContext(),
                    "Network connection is not available", Toast.LENGTH_LONG)
                    .show();
        }






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private class SetupNewsTask extends AsyncTask<String, Void, String> {
        // Download JSON resource and return String representation
        protected String doInBackground(String... urls) {

// Setup HTTP client and request

            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(urls[0]);

            try {
// Execute our HTTP request and obtain response
                HttpResponse response = client.execute(request);
                InputStream input = response.getEntity().getContent();

// Process each line of response data

                String result = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder sb = new StringBuilder();
                while ((result = reader.readLine()) != null) {

                    sb.append(result);

                }

// Return response data as String

                return sb.toString();
            } catch (Exception e) {

                e.printStackTrace();


            }

            return null;
        }

// Add news to database after processing resource

        protected void onPostExecute(String result) {
            if (result != null) {

            System.out.println("start to onPostExecut!!!!!!!!!");

            try {
                JSONObject responseJSON = new JSONObject(result);

                JSONObject query = responseJSON.getJSONObject("query");

                JSONObject results = query.getJSONObject("results");

                JSONArray item = results.getJSONArray("item");

                int length = item.length();

                System.out.println("find news :" + length);

                for(int i = 1; i < length ; i ++){

                    JSONObject newsJson = item.getJSONObject(i);

                    String title = newsJson.getString("title");

                    String link = newsJson.getString("link");

                    String imgUrl = "";


                    if(newsJson.has("group")) {


                        JSONObject group = newsJson.getJSONObject("group");

                        JSONObject thumbnail = group.getJSONObject("thumbnail");

                        imgUrl = thumbnail.getString("url");


                    }

                    Newsfeed news = new Newsfeed(title,imgUrl,link);

                    newsfeedsList.add(news);

                }

                refreshTableView();

            } catch (Exception e) {
                e.printStackTrace();

            } }


            if (result == null)
            {
                String title = "The network is disconnect!!!";
                String link = "www.google.com";
                String imgUrl = "res/drawable.off.jpeg";
                Newsfeed news = new Newsfeed(title,imgUrl,link);
                newsfeedsList.add(news);
                refreshTableView();
            }



        }
    }
//df

    private void refreshTableView(){

        NewsfeedAdapter adapter = new NewsfeedAdapter(this,newsfeedsList);
        newsfeedListView.setAdapter(adapter);


    }

    private void pushToNewsDetail(int position){

        Newsfeed news = (Newsfeed)newsfeedListView.getAdapter().getItem(position);

        Intent i = new Intent(this,newsDetailActivity.class);

        i.putExtra("link",news.getLink());


        startActivity(i);


    }


}
