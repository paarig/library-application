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
public class Dvd extends LibraryItem {

    public Dvd() {
        super();
    }

    public Dvd(String title, long isbn, int year, String publisher, String author, int count) throws Exception{
        super(title, isbn, year, publisher, count, author, LibraryItemType.DVD);
    }

    public Dvd saveDvd() {
        LibraryItem newDvd;
        try {
            newDvd = super.save();
        } catch (Exception ex) {
            Logger.getLogger(Ebook.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this;
    }
    
    public ArrayList<Dvd> searchDvdByTitle(String title) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByTitle(title, libraryItemType.DVD);
        ArrayList<Dvd> libraryDvdList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryDvdList.add(new Dvd(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryDvdList;
    }
    
    public ArrayList<Dvd> searchDvdByISBN(long ISBN) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByISBN(ISBN, libraryItemType.DVD);
        ArrayList<Dvd> libraryDvdList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryDvdList.add(new Dvd(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryDvdList;
    }

    public ArrayList<Dvd> searchDvdByPublisher(String publisher) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByPublisher(publisher, libraryItemType.DVD);
        ArrayList<Dvd> libraryDvdList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryDvdList.add(new Dvd(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryDvdList;
    }

    public ArrayList<Dvd> searchDvdByAuthor(String author) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByAuthor(author, libraryItemType.DVD);
        ArrayList<Dvd> libraryDvdList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryDvdList.add(new Dvd(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryDvdList;
    }    

}
