package com.ayata.ayataweatherapplication.room;

import androidx.room.PrimaryKey;

@androidx.room.Entity(tableName = "data_table")
public class Entity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;

    public Entity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

