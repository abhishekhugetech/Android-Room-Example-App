package com.epiclancers.authorbookapp;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = Book.class , version = 1)
public abstract class BookRoomDatabase extends RoomDatabase {

    private static BookRoomDatabase INSTANCE;

    public abstract BookDao bookDao();

    public static BookRoomDatabase getInstance(Application context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder( context.getApplicationContext() ,
                    BookRoomDatabase.class , "book_database")
                    .build();
        }
        return INSTANCE;
    }



}
