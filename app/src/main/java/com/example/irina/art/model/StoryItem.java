package com.example.irina.art.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class StoryItem {
    @PrimaryKey
    private Integer id;
    @ColumnInfo
    private String pictureLink;
    @ColumnInfo
    private String text;
    @ColumnInfo
    private Integer artistItemId;

    public StoryItem(String pictureLink, String text) {
        this.pictureLink = pictureLink;
        this.text = text;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getArtistItemId() {
        return artistItemId;
    }

    public void setArtistItemId(Integer artistItemId) {
        this.artistItemId = artistItemId;
    }
}
