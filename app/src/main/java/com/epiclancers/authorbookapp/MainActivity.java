package com.epiclancers.authorbookapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BookListAdapter.onDeleteClickListener {

    public static final int NEW_NOTE = 1;
    public static final int UPDATE_NOTE = 2;
    public static final String KEY_BOOK_NAME = "book_name";
    public static final String KEY_AUTHOR_NAME = "author_name";
    public static final String KEY_BOOK_ID = "book_id";
    FloatingActionButton newBook;
    BookViewModel bookViewModel;
    private BookListAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        setUpRecyclerView();
        bookViewModel.arrayListLiveData.observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                adapter.addNewBooks(books);
            }
        });
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new BookListAdapter(this , this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        } else if (requestCode == UPDATE_NOTE && resultCode == RESULT_OK & data!= null){
            String updatedbookName = data.getStringExtra(KEY_BOOK_NAME);
            String updatedauthorName = data.getStringExtra(KEY_AUTHOR_NAME);
            int updateId = data.getIntExtra(KEY_BOOK_ID , -1);
            Book updatedbook = new Book(updateId,updatedbookName,updatedauthorName);
            bookViewModel.updateBook(updatedbook);
            Toast.makeText(this, "New Book Saved to Database " +updateId+ updatedauthorName + updatedbookName, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "No Data Added", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDelete(Book book) {
        String bookName = book.getBookName();
        bookViewModel.deleteBook(book);
        Toast.makeText(this, bookName + " deleted from Database", Toast.LENGTH_SHORT).show();
    }
}
