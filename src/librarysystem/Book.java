/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Book extends LibraryItem {


    public Book() {
        super();
    }

    public Book(String title, long isbn, int year, String publisher, String author, int count) throws Exception{
        super(title, isbn, year, publisher, count, author, LibraryItemType.BOOK);
    }

    public Book saveBook() {
        LibraryItem newBook;
        try {
            newBook = super.save();
        } catch (Exception ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this;
    }

    public Book readBook(long isbn) {
        Book newBook;
        try {
            LibraryItem book = read(isbn);
            newBook = new Book(book.title, book.isbn, book.year, book.publisher, book.author, book.count);
            return newBook;
        } catch (Exception ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Book> searchBookByTitle(String title) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByTitle(title, libraryItemType.BOOK);
        ArrayList<Book> libraryBookList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryBookList.add(new Book(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryBookList;
    }
    
    public ArrayList<Book> searchBookByISBN(long ISBN) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByISBN(ISBN, libraryItemType.BOOK);
        ArrayList<Book> libraryBookList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryBookList.add(new Book(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryBookList;
    }

    public ArrayList<Book> searchBookByPublisher(String publisher) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByPublisher(publisher, libraryItemType.BOOK);
        ArrayList<Book> libraryBookList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryBookList.add(new Book(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryBookList;
    }    

    public ArrayList<Book> searchBookByAuthor(String author) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByAuthor(author, libraryItemType.BOOK);
        ArrayList<Book> libraryBookList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryBookList.add(new Book(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryBookList;
    }    
}
