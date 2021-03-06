package com.epiclancers.authorbookapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BookViewModel extends AndroidViewModel {

    private static BookDao bookDao;
    LiveData<List<Book>> arrayListLiveData;

    public BookViewModel(@NonNull Application application) {
        super(application);
        BookRoomDatabase database = BookRoomDatabase.getInstance(application);
        bookDao = database.bookDao();
        bookDao.getBooks();
        arrayListLiveData = bookDao.getBooks();
    }

    void insertBook(Book book){
        InsertBookAsyncTask task = new InsertBookAsyncTask();
        task.execute(book);
    }


    public void updateBook(Book updatedbook) {
        UpdateBookAsyncTask task = new UpdateBookAsyncTask();
        task.execute(updatedbook);
    }

    public void deleteBook(Book book) {
        DeleteBookAsyncTask task = new DeleteBookAsyncTask();
        task.execute(book);
    }

    public static class InsertBookAsyncTask extends AsyncTask<Book,Void,Void>{

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.insertBook(books[0]);
            return null;
        }
    }
    public static class DeleteBookAsyncTask extends AsyncTask<Book,Void,Void>{

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.delete(books[0]);
            return null;
        }
    }
    public static class UpdateBookAsyncTask extends AsyncTask<Book,Void,Void>{

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.update(books[0]);
            return null;
        }
    }

}
