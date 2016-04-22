package com.com.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yandongzhang.week6internetexercise.R;
import com.model.Newsfeed;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by yandongzhang on 16/4/16.
 */
public class NewsfeedAdapter extends BaseAdapter {

private Context context;
private ArrayList<Newsfeed> newsfeeds;


    public NewsfeedAdapter(Context context, ArrayList<Newsfeed> newsfeeds) {
        this.context = context;
        this.newsfeeds = newsfeeds;
    }

    @Override
    public int getCount() {
        return newsfeeds.size();
    }

    @Override
    public Object getItem(int position) {
        return newsfeeds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_article_item, null); // List layout here
        }

        ImageView thumbnailsImageView = (ImageView)view.findViewById(R.id.thumbImageView);
        TextView titleView = (TextView)view.findViewById(R.id.titleTextView);

        titleView.setText(newsfeeds.get(i).getTitle());

        String imgUrl = newsfeeds.get(i).getImgUrl();

        if(imgUrl.length() > 3 ){

            new DownloadImageTask(thumbnailsImageView).execute(imgUrl);
            System.out.println("Image Url :" + imgUrl);


        }


        return view;
    }



    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String url_display = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(url_display).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
                System.out.print("We have not network");
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
