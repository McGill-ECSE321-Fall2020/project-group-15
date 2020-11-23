package com.artsee.artsee_android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.InputStream;
import java.util.List;

/**
 * An adapter class that should be used for the gallery. It is used with recycler view componenets.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Artwork> data;

    public RecyclerViewAdapter(Context context, List<Artwork> list) {
        this.context = context;
        this.data = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.card_view_item_artwork, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_artwork_name.setText(data.get(position).getName());
        holder.tv_artist_name.setText(data.get(position).getArtist().getName());
        holder.tv_artwork_price.setText(Integer.toString(data.get(position).getPrice()));
        // convert url to image
        new DownloadImageTask(holder.artwork_image)
                .execute(data.get(position).getUrl());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // A class to pass into the view adapter that uses the cardview.xml ids
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_artwork_name;
        TextView tv_artist_name;
        TextView tv_artwork_price;
        ImageView artwork_image;
        public MyViewHolder(View itemView) {
            super(itemView);

            tv_artwork_name = (TextView) itemView.findViewById(R.id.artwork_name_id);
            tv_artist_name = (TextView) itemView.findViewById(R.id.by_artist_id);
            tv_artwork_price = (TextView) itemView.findViewById(R.id.price_id);
            artwork_image = (ImageView) itemView.findViewById(R.id.artwork_img_id);

        }
    }

    // A class to create an asyncTask for a separate thread to download the
    // images because the main thread was crashing on image rendering
    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bitImage;

        public DownloadImageTask(ImageView bitImage) {
            this.bitImage = bitImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mBmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mBmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mBmp;
        }

        protected void onPostExecute(Bitmap result) {
            bitImage.setImageBitmap(result);
        }
    }


}
