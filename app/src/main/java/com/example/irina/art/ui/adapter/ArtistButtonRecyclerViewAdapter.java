package com.example.irina.art.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.irina.art.R;
import com.example.irina.art.model.ArtistItem;

import java.util.List;

public class ArtistButtonRecyclerViewAdapter extends RecyclerView.Adapter<ArtistButtonRecyclerViewAdapter.ViewHolder> {

	private List<ArtistItem> artistItems;

	public ArtistButtonRecyclerViewAdapter(List<ArtistItem> artistItems) {
		super();
		this.artistItems = artistItems;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View artist = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist_button, parent, false);

		return new ViewHolder(artist);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		if (artistItems == null || artistItems.get(position) == null) {
			return;
		}

		final ArtistItem artistItem = artistItems.get(position);
        final Context context = holder.itemView.getContext();
		holder.artistButton.setText(artistItem.getArtistName());
		holder.artistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, artistItem.getArtistName(), Toast.LENGTH_LONG).show();
            }
        });

	}

	public ArtistItem getItem(int position) {
		return artistItems.get(position);
	}

	@Override
	public int getItemCount() {
		if (artistItems == null) {
			return 0;
		}

		return artistItems.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		Button artistButton;

		//Initializing Views
		ViewHolder(View view) {
			super(view);
			artistButton = (Button) view.findViewById(R.id.artistButton);
		}
	}

}