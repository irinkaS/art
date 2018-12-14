package com.example.irina.art.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.irina.art.model.ArtistWithStoryItems;

@Dao
public interface ArtistWithStoryItemsDao {
    @Query("SELECT * FROM ArtistItem WHERE id = :artistItemId")
    ArtistWithStoryItems loadArtistWithStoryItems(long artistItemId);
}
