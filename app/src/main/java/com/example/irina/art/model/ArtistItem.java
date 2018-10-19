package com.example.irina.art.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ArtistItem {
    @PrimaryKey
    private Integer id;
    @ColumnInfo
    private String artistName;

    public ArtistItem(Integer id, String artistName) {
        this.artistName = artistName;
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
