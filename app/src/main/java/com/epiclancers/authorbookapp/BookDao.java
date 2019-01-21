package com.epiclancers.authorbookapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BookDao {

    @Insert
    public void insertBook(Book book);

    @Query("SELECT * from books_table")
    public LiveData<List<Book>> getBooks();

}
