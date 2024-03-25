package Lib;

import java.util.ArrayList;
import java.util.Scanner;

class Book { //Creating variables for books
    String name;
    String author;
    String isbn;
    String dateOfWriting;

    public Book(String name, String author, String isbn, String dateOfWriting) { //Assignment to string values
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.dateOfWriting = dateOfWriting;
    }
}

public class Library {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>(); //Creating an array for books
        Scanner scanner = new Scanner(System.in); //Creating a scanner object to read keyboard input

        while (true) { //Menu to select options
            System.out.println("1. Add a new book");
            System.out.println("2. View all books");
            System.out.println("3. Search for a book by title");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt(); // Receive input from the user and store it in the choice variable

            switch (choice) { //Case option1 add a book
                case 1:
                    System.out.println("Enter book details:");
                    System.out.print("Name: ");
                    String name = scanner.next();
                    System.out.print("Author: ");
                    String author = scanner.next();
                    System.out.print("ISBN: ");
                    String isbn = scanner.next();
                    System.out.print("Date of Writing: ");
                    String dateOfWriting = scanner.next();
                    books.add(new Book(name, author, isbn, dateOfWriting)); //Adding a new book
                    break;
                case 2: //case option2 printing all exist books and info.
                    System.out.println("All Books:");
                    for (Book book : books) {
                        System.out.println("Name: " + book.name);
                        System.out.println("Author: " + book.author);
                        System.out.println("ISBN: " + book.isbn);
                        System.out.println("Date of Writing: " + book.dateOfWriting);
                        System.out.println();
                    }
                    break;
                case 3: //case option3 for searching exist book
                    System.out.print("Enter the title to search: ");
                    String searchTitle = scanner.next(); //Input to search for a saved book
                    for (Book book : books) { //Cycle to find a book
                        if (book.name.equalsIgnoreCase(searchTitle)) {
                            System.out.println("Book found:");
                            System.out.println("Name: " + book.name);
                            System.out.println("Author: " + book.author);
                            System.out.println("ISBN: " + book.isbn);
                            System.out.println("Date of Writing: " + book.dateOfWriting);
                            break;
                        }
                        else{
                            System.out.println("This book isn't in our library");
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again."); //Error if the user did not enter 1,2 or 3
            }
        }
    }
}
