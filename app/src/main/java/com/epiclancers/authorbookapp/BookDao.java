package com.epiclancers.authorbookapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

@Dao
public interface BookDao {

    @Insert
    public void insertBook(Book book);

}
