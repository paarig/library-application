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
public class AudioBook extends LibraryItem {

    public AudioBook() {
        super();
    }

    public AudioBook(String title, long isbn, int year, String publisher, String author, int count) throws Exception{
        super(title, isbn, year, publisher, count, author, LibraryItemType.AUDIOBOOK);
    }

    public AudioBook saveAudioBook() {
        LibraryItem newAudioBook;
        try {
            newAudioBook = super.save();
        } catch (Exception ex) {
            Logger.getLogger(AudioBook.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this;
    }
    
    public ArrayList<AudioBook> searchAudiobookByTitle(String title) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByTitle(title, libraryItemType.AUDIOBOOK);
        ArrayList<AudioBook> libraryAudiobookList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryAudiobookList.add(new AudioBook(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryAudiobookList;
    }
 
    public ArrayList<AudioBook> searchAudiobookByISBN(long ISBN) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByISBN(ISBN, libraryItemType.AUDIOBOOK);
        ArrayList<AudioBook> libraryAudiobookList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryAudiobookList.add(new AudioBook(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryAudiobookList;
    }

    public ArrayList<AudioBook> searchAudiobookByPublisher(String publisher) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByPublisher(publisher, libraryItemType.AUDIOBOOK);
        ArrayList<AudioBook> libraryAudiobookList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryAudiobookList.add(new AudioBook(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryAudiobookList;
    }    
    
    public ArrayList<AudioBook> searchAudiobookByAuthor(String author) throws Exception{
        ArrayList<LibraryItem> libraryItemList = super.searchByAuthor(author, libraryItemType.AUDIOBOOK);
        ArrayList<AudioBook> libraryAudiobookList = new ArrayList<>();
        for (int i = 0; i<libraryItemList.size(); i++){
            libraryAudiobookList.add(new AudioBook(libraryItemList.get(i).gettitle(),libraryItemList.get(i).getisbn(),libraryItemList.get(i).getyear(),libraryItemList.get(i).getpublisher(), libraryItemList.get(i).getauthor(), libraryItemList.get(i).getcount()));
        }
        return libraryAudiobookList;
    }        
}
