package com.epiclancers.authorbookapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_NOTE = 1;
    public static final String KEY_BOOK_NAME = "book_name";
    public static final String KEY_AUTHOR_NAME = "author_name";
    FloatingActionButton newBook;
    BookViewModel bookViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
    }

    private void init() {
        newBook = findViewById(R.id.newBook);
    }

    public void newNote(View view) {
        Intent intent = new Intent( getApplicationContext() , NewBook.class );
        startActivityForResult(intent, NEW_NOTE );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_NOTE && resultCode == RESULT_OK && data!=null){
            String bookName = data.getStringExtra(KEY_BOOK_NAME);
            String authorName = data.getStringExtra(KEY_AUTHOR_NAME);
            Book book = new Book(bookName,authorName);
            bookViewModel.insertBook(book);
            Toast.makeText(this, "New Book Saved to Database", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No Data Added", Toast.LENGTH_SHORT).show();
        }
    }
}
