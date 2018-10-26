package com.example.irina.art.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.irina.art.model.ArtistItem;

import java.util.List;

@Dao
public interface ArtistItemDao {
    @Query("SELECT * FROM artistitem")
    List<ArtistItem> getAll();

    @Query("SELECT * FROM artistitem WHERE id = :artistId")
    ArtistItem findById(Integer artistId);

    @Query("SELECT * FROM artistitem where artistName LIKE :artistName")
    ArtistItem findByName(String artistName);

    @Query("SELECT COUNT(*) from artistitem")
    int countArtists();

    @Insert
    void insert(ArtistItem artistitem);

    @Delete
    void delete(ArtistItem artistitem);

}
