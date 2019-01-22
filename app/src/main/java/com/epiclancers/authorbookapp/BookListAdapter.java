package com.epiclancers.authorbookapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    List<Book> bookArrayList = new ArrayList<>();
    Context context;
    public onDeleteClickListener onDeleteClickListener;

    public interface onDeleteClickListener{
        void onDelete(Book book);
    }

    public BookListAdapter(Context context , onDeleteClickListener deleteClickListener) {
        this.context = context;
        this.onDeleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_book , viewGroup,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i) {
        Book book = bookArrayList.get(i);
        bookViewHolder.setData(book.getAuthorName() , book.getBookName() , book.getId() , i );
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public void addNewBooks(List<Book> books) {
        bookArrayList = books;
        notifyDataSetChanged();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{

        TextView author_name,book_name;
        ImageView delete_button;
        CardView book_holder_card;
        static final String TAG= "BookListAdapter";
        int position;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);
            author_name = itemView.findViewById(R.id.author_name);
            book_name = itemView.findViewById(R.id.book_name);
            delete_button = itemView.findViewById(R.id.delete_book);
            book_holder_card = itemView.findViewById(R.id.card_view);
        }

        void setData(final String authorName, final String bookName, final int id, final int pos) {
            author_name.setText(authorName);
            book_name.setText(bookName);
            position = pos;
            book_holder_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent( context , EditTheBook.class );
                    intent.putExtra(MainActivity.KEY_AUTHOR_NAME , authorName);
                    intent.putExtra(MainActivity.KEY_BOOK_NAME , bookName);
                    intent.putExtra(MainActivity.KEY_BOOK_ID , id);
                    Activity activity = (Activity) context;
                    activity.startActivityForResult( intent , MainActivity.UPDATE_NOTE );
                }
            });
            delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteClickListener.onDelete(bookArrayList.get(pos));
                }
            });
        }
    }

}
