package com.epiclancers.authorbookapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

public class BookViewModel extends AndroidViewModel {

    private static BookDao bookDao;

    public BookViewModel(@NonNull Application application) {
        super(application);
        BookRoomDatabase database = BookRoomDatabase.getInstance(application);
        bookDao = database.bookDao();
    }

    void insertBook(Book book){
        InsertBookAsyncTask task = new InsertBookAsyncTask();
        task.execute(book);
    }

    public static class InsertBookAsyncTask extends AsyncTask<Book,Void,Void>{

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.insertBook(books[0]);
            return null;
        }
    }

}
