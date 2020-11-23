package com.artsee.artsee_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewGalleryActivity extends AppCompatActivity {

    // for testing purposes
    List<Artwork> artworks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artworks = new ArrayList<>();
        artworks.add(new Artwork("Mona Lisa", 14,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FLisa_del_Giocondo&psig=AOvVaw3NfDVKauPiNBAoYYzedj9X&ust=1606245413430000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCIiD8Mywme0CFQAAAAAdAAAAABAI",
                new Artist("Adrien")));
        artworks.add(new Artwork("white", 12,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.dreamstime.com%2Fabstract-art-painting-white-gray-color-texture-background-ideas-image148073133&psig=AOvVaw3-zQErIS_8oX2ANbxCYSZC&ust=1606245722516000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNi5nuCxme0CFQAAAAAdAAAAABAN",
                new Artist("Bob")));
        artworks.add(new Artwork("new", 10,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FLisa_del_Giocondo&psig=AOvVaw3NfDVKauPiNBAoYYzedj9X&ust=1606245413430000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCIiD8Mywme0CFQAAAAAdAAAAABAI",
                new Artist("John")));
        artworks.add(new Artwork("Simple", 20,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FLisa_del_Giocondo&psig=AOvVaw3NfDVKauPiNBAoYYzedj9X&ust=1606245413430000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCIiD8Mywme0CFQAAAAAdAAAAABAI",
                new Artist("Jack")));
        artworks.add(new Artwork("", 24,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FLisa_del_Giocondo&psig=AOvVaw3NfDVKauPiNBAoYYzedj9X&ust=1606245413430000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCIiD8Mywme0CFQAAAAAdAAAAABAI",
                new Artist("Gareth")));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_id);
        RecyclerViewAdapter rvAdapter = new RecyclerViewAdapter(this, artworks);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(rvAdapter);

    }
}
