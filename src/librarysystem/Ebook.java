/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paari
 */
public class Ebook extends LibraryItem{
    
    public Ebook() {
        super();
    }

    public Ebook(String title, long isbn, int year, String publisher, String author, int count) throws Exception{
        super(title, isbn, year, publisher, count, author, LibraryItemType.EBOOK);
        this.author=author;
    }

    public Ebook saveEbook() {
        LibraryItem newEbook;
        try {
            newEbook = super.save();
        } catch (Exception ex) {
            Logger.getLogger(Ebook.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this;
    }
    
    public ArrayList<Ebook> searchEbookByTitle(String title) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByTitle(title, libraryItemType.EBOOK);
        ArrayList<Ebook> libraryEbookList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryEbookList.add(new Ebook(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryEbookList;
    }

    public ArrayList<Ebook> searchEbookByISBN(long ISBN) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByISBN(ISBN, libraryItemType.EBOOK);
        ArrayList<Ebook> libraryEbookList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryEbookList.add(new Ebook(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryEbookList;
    }  

    public ArrayList<Ebook> searchEbookByPublisher(String publisher) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByPublisher(publisher, libraryItemType.EBOOK);
        ArrayList<Ebook> libraryEbookList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryEbookList.add(new Ebook(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryEbookList;
    } 
    
    public ArrayList<Ebook> searchEbookByAuthor(String author) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByAuthor(author, libraryItemType.EBOOK);
        ArrayList<Ebook> libraryEbookList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryEbookList.add(new Ebook(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryEbookList;
    }     

}
