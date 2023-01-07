package com.ayata.ayataweatherapplication.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {Entity.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract Dao dao();

}
