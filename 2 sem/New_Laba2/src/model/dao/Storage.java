package model.dao;

import model.Book;

import java.util.ArrayList;

public class Storage {

    static ArrayList<Book> books;

    public Storage() {
        if (books == null) {
            books = new ArrayList<>();
        }
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

/*    public void add(String[] book) {
        books.add(new Book(book[0], book[1], book[2], new Date(book[3])));
    }

    public void remove(int number) {
        books.remove(number);
    }

    public void edit(int number, String[] info) {
        books.get(number).setName(info[0]);
        books.get(number).setAuthor(info[1]);
        books.get(number).setEdition(info[2]);
        books.get(number).setDate(new Date(info[3]));
    }

    public Book searchBook(Book book) {
        for (int i = 0; i < books.size(); i++) {

        }
        return book;
    }*/
}
