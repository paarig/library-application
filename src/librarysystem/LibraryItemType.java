/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

/**
 *
 * @author paari
 */
public enum LibraryItemType {
    AUDIOBOOK, BOOK, DVD, EBOOK;
    
    public int getIndex() {
        return ordinal() + 1;
    }    
}
