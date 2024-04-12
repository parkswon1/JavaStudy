package thisisjava.src.innerClass.Book;

import thisisjava.src.innerClass.Book.Book;

public class BookMain {
    public static void main(String[] args){
        Book book = new Book("Java Programing");
        book.setAuthorName("Park");

        book.printBookDetails();
    }
}
