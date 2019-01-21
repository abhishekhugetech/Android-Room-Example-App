package com.epiclancers.authorbookapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "books_table")
public class Book {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "col_book_name")
    public String bookName;
    @ColumnInfo(name = "col_author_name")
    public String authorName;

    public Book(String bookName, String authorName) {
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public Book(int id, String bookName, String authorName) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public Book() {
    }
}
