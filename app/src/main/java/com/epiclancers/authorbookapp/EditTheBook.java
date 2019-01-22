package com.epiclancers.authorbookapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTheBook extends AppCompatActivity {

    private EditText book_name;
    private EditText author_name;
    private Button updateButton;
    private Button button_cancel;
    private String bookName;
    private String authorName;
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);
        init();
    }

    private void init() {
        Bundle intent = getIntent().getExtras();
        bookName = intent.getString(MainActivity.KEY_BOOK_NAME);
        authorName = intent.getString(MainActivity.KEY_AUTHOR_NAME);
        bookId = intent.getInt(MainActivity.KEY_BOOK_ID);

        book_name = findViewById(R.id.book_name);
        author_name = findViewById(R.id.author_name);

        book_name.setText(bookName);
        author_name.setText(authorName);

        updateButton = findViewById(R.id.button);
        updateButton.setText("Update this Book");
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookName = book_name.getText().toString();
                authorName = author_name.getText().toString();
                if (!bookName.isEmpty() && !authorName.isEmpty()){
                Intent intent = new Intent();
                intent.putExtra(MainActivity.KEY_BOOK_NAME , bookName);
                intent.putExtra(MainActivity.KEY_AUTHOR_NAME , authorName);
                intent.putExtra(MainActivity.KEY_BOOK_ID , bookId);
                setResult(MainActivity.RESULT_OK , intent);
                finish();
                }else{
                    Toast.makeText(EditTheBook.this, "Please Fill Both the Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_cancel = findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
