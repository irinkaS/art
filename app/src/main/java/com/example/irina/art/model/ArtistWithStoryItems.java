package com.example.irina.art.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class ArtistWithStoryItems {
    @Embedded
    public ArtistItem artistItem;

    @Relation(parentColumn = "id", entityColumn = "artistItemId", entity = StoryItem.class)
    public List<StoryItem> storyItems;
}
