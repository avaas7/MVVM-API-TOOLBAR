package com.ayata.ayataweatherapplication.room;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insert(Entity entity);

    @Update
    void update(Entity entity);

    @Delete
    void delete(Entity entity);

    @Query("DELETE FROM data_table")
    void deleteAllData();

    @Query("SELECT * FROM data_table")
    LiveData<List<Entity>> getAllData();

    @Query("SELECT * FROM data_table WHERE name = :name")
    LiveData<Entity> getData(String name);
}
