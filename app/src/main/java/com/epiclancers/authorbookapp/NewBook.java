package com.epiclancers.authorbookapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewBook extends AppCompatActivity {

    EditText bookName,authorName;
    Button addToCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);
        init();
    }

    private void init() {
        bookName = findViewById(R.id.book_name);
        authorName = findViewById(R.id.author_name);
        addToCollection = findViewById(R.id.button);
    }

    public void addToCollection(View view){
        String book_name = bookName.getText().toString();
        String author_name = authorName.getText().toString();
        if (!book_name.isEmpty() && !author_name.isEmpty()){
            Intent intent = new Intent();
            intent.putExtra(MainActivity.KEY_AUTHOR_NAME , author_name);
            intent.putExtra(MainActivity.KEY_BOOK_NAME , book_name);
            setResult( MainActivity.RESULT_OK , intent);
            finish();
        }else{
            Toast.makeText(this, "Please fill both the Fields", Toast.LENGTH_SHORT).show();
        }
    }

}
