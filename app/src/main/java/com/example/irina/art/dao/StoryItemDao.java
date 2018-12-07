package com.example.irina.art.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.irina.art.model.StoryItem;

import java.util.List;

@Dao
public interface StoryItemDao {
    @Query("SELECT * FROM storyitem")
    List<StoryItem> getAll();

    @Query("SELECT * FROM storyitem WHERE id = :storyItemId")
    StoryItem findById(Integer storyItemId);


    @Query("SELECT COUNT(*) from storyitem")
    int countStoryItems();

    @Insert
    void insert(StoryItem storyItem);

    @Delete
    void delete(StoryItem storyItem);

}
