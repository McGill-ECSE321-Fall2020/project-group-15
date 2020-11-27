package com.artsee.artsee_android;

import android.content.Context;
import android.content.Intent;
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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.io.InputStream;
import java.util.List;

/**
 * An adapter class that should be used for the gallery. It is used with recycler view components.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Artwork> artworks; // data to populate

    public RecyclerViewAdapter(Context context, List<Artwork> list) {
        this.context = context;
        this.artworks = list;
    }

    /**
     * Creates an instance of MyViewHolder which is adapted to the generic viewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.card_view_item_artwork, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * Populates the data in MyViewHolder by using the bindings through view Ids
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_artwork_name.setText(artworks.get(position).getName());
        holder.tv_artist_name.setText("By " + artworks.get(position).getArtist().getName());
        holder.tv_artwork_price.setText("$" + Integer.toString(artworks.get(position).getPrice() / 100));
        // convert url to image
        new DownloadImageTask(holder.artwork_image)
                .execute(artworks.get(position).getUrl());

        //add an event listener when clicking on an artwork card
        holder.cardView.setOnClickListener(v -> {

            // create an intent for the next activity (page)
            Intent intent = new Intent(context, DetailedArtworkActivity.class);
            // passing data to the detailed artwork activity
            intent.putExtra("Name", artworks.get(position).getName());
            intent.putExtra("ArtistName", artworks.get(position).getArtist().getName());
            intent.putExtra("Price", artworks.get(position).getPrice());
            intent.putExtra("Url", artworks.get(position).getUrl());
            intent.putExtra("description", artworks.get(position).getDescription());
            intent.putExtra("date", artworks.get(position).getDate());
            intent.putExtra("numInStock", artworks.get(position).getNumInStock());
            intent.putExtra("artworkID",artworks.get(position).getID());
            intent.putExtra("artistDescription", artworks.get(position).getArtist().getArtistDescription());

            // starting the detailed artwork activity
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return artworks.size();
    }

    /**
     * A class to pass into the view adapter that uses the cardview.xml components
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_artwork_name;
        TextView tv_artist_name;
        TextView tv_artwork_price;
        ImageView artwork_image;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_artwork_name = (TextView) itemView.findViewById(R.id.artwork_name_id);
            tv_artist_name = (TextView) itemView.findViewById(R.id.by_artist_id);
            tv_artwork_price = (TextView) itemView.findViewById(R.id.price_id);
            artwork_image = (ImageView) itemView.findViewById(R.id.artwork_img_id);
            cardView = (CardView) itemView.findViewById(R.id.card_view_id);

        }
    }

    /**
     * A class to create an asyncTask for a separate thread to download the
     *  images because the main thread crashes on image rendering.
     */
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
