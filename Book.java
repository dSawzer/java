import java.util.*;

abstract class Item { //// Abstract class representing an item in the library
    private final String title;
    private final String uniqueID;
    private boolean isBorrowed;

    public Item(String title, String uniqueID) {
        this.title = title;
        this.uniqueID = uniqueID;
        this.isBorrowed = false;
    }

    public String getTitle() { //getters
        return title;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowItem() {
        isBorrowed = true;
    }

    public void returnItem() {
        isBorrowed = false;
    }

    abstract void displayDetails();
}

class Book extends Item { //// Class representing a book, extending the Item class
    private final String author;

    public Book(String title, String uniqueID, String author) {
        super(title, uniqueID);
        this.author = author;
    }


    public void borrowItem() {
        super.borrowItem();
        System.out.println("Book '" + getTitle() + "' by " + author + " has been borrowed.");
    }


    public void returnItem() {
        super.returnItem();
        System.out.println("Book '" + getTitle() + "' by " + author + " has been returned.");
    }


    void displayDetails() {
        System.out.println("Book Title: " + getTitle());
        System.out.println("Author: " + author);
    }
}

class DVD extends Item { // Class representing a DVD, extending the Item class
    private final int duration;

    public DVD(String title, String uniqueID, int duration) {
        super(title, uniqueID);
        this.duration = duration;
    }


    public void borrowItem() {
        super.borrowItem();
        System.out.println("DVD '" + getTitle() + "' (Duration: " + duration + " minutes) has been borrowed.");
    }


    public void returnItem() {
        super.returnItem();
        System.out.println("DVD '" + getTitle() + "' (Duration: " + duration + " minutes) has been returned.");
    }


    void displayDetails() {
        System.out.println("DVD Title: " + getTitle());
        System.out.println("Duration: " + duration + " minutes");
    }
}

class Patron { // Class representing a patron of the library
    private final String name;
    private final String ID;
    private final List<Item> borrowedItems;

    public Patron(String name, String ID) {
        this.name = name;
        this.ID = ID;
        this.borrowedItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public List<Item> getBorrowedItems() {
        return borrowedItems;
    }

    public void borrow(Item item) {
        borrowedItems.add(item);
    }

    public void returnItem(Item item) {
        borrowedItems.remove(item);
    }
}

interface IManageable { // Interface for managing library items
    void add(Item item);

    void remove(Item item);

    void listAvailable();

    void listBorrowed();
}

class Library implements IManageable { //list available items in the library
    public List<Item> items;
    public List<Patron> patrons;

    public Library() {
        this.items = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }


    public void add(Item item) {
        items.add(item);
    }


    public void remove(Item item) {
        items.remove(item);
    }


    public void listAvailable() {
        System.out.println("Available Items:");
        for (Item item : items) {
            if (!item.isBorrowed()) {
                item.displayDetails();
            }
        }
    }


    public void listBorrowed() {
        System.out.println("Borrowed Items:");
        for (Item item : items) {
            if (item.isBorrowed()) {
                item.displayDetails();
            }
        }
    }

    public void registerPatron(Patron patron) {
        patrons.add(patron);
    }

    public void lendItem(Patron patron, Item item) {
        if (!item.isBorrowed()) {
            patron.borrow(item);
            item.borrowItem();
        } else {
            System.out.println("Item is already borrowed.");
        }
    }

    public void returnItem(Patron patron, Item item) {
        if (patron.getBorrowedItems().contains(item)) {
            patron.returnItem(item);
            item.returnItem();
        } else {
            System.out.println("Patron did not borrow this item.");
        }
    }
}

 class Main { //// Main class to run the program
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add Book");
            System.out.println("2. Add DVD");
            System.out.println("3. Register Patron");
            System.out.println("4. Lend Item");
            System.out.println("5. Return Item");
            System.out.println("6. List Available Items");
            System.out.println("7. List Borrowed Items");
            System.out.println("8. Exit");

            int option = scanner.nextInt(); // Read user input for menu option
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    addBook(library, scanner);
                    break;
                case 2:
                    addDVD(library, scanner);
                    break;
                case 3:
                    registerPatron(library, scanner);
                    break;
                case 4:
                    lendItem(library, scanner);
                    break;
                case 5:
                    returnItem(library, scanner);
                    break;
                case 6:
                    library.listAvailable();
                    break;
                case 7:
                    library.listBorrowed();
                    break;
                case 8:
                    scanner.close(); // Close the scanner
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addBook(Library library, Scanner scanner) { //// Method to add a book to the library
        System.out.println("Enter book title:"); // Read book title from user input
        String title = scanner.nextLine();

        System.out.println("Enter book unique ID:");//ID
        String uniqueID = scanner.nextLine();

        System.out.println("Enter book author:");//author
        String author = scanner.nextLine();

        library.add(new Book(title, uniqueID, author));
        System.out.println("Book added to the library.");
    }

    private static void addDVD(Library library, Scanner scanner) {
        System.out.println("Enter DVD title:");
        String title = scanner.nextLine();

        System.out.println("Enter DVD unique ID:");
        String uniqueID = scanner.nextLine();

        System.out.println("Enter DVD duration (in minutes):");
        int duration = scanner.nextInt();
        scanner.nextLine(); // consume newline

        library.add(new DVD(title, uniqueID, duration));
        System.out.println("DVD added to the library.");
    }

    private static void registerPatron(Library library, Scanner scanner) {
        System.out.println("Enter patron name:");
        String name = scanner.nextLine();

        System.out.println("Enter patron ID:");
        String ID = scanner.nextLine();

        library.registerPatron(new Patron(name, ID));
        System.out.println("Patron registered.");
    }

    private static void lendItem(Library library, Scanner scanner) {
        System.out.println("Enter patron ID:");
        String patronID = scanner.nextLine();

        System.out.println("Enter item unique ID:");
        String itemID = scanner.nextLine();

        Patron patron = findPatronByID(library, patronID);
        Item item = findItemByID(library, itemID);

        if (patron != null && item != null) {
            library.lendItem(patron, item);
            System.out.println("Item lent to patron.");
        } else {
            System.out.println("Patron or item not found.");
        }
    }

    private static void returnItem(Library library, Scanner scanner) {
        System.out.println("Enter patron ID:");
        String patronID = scanner.nextLine();

        System.out.println("Enter item unique ID:");
        String itemID = scanner.nextLine();

        Patron patron = findPatronByID(library, patronID);
        Item item = findItemByID(library, itemID);

        if (patron != null && item != null) {
            library.returnItem(patron, item);
            System.out.println("Item returned by patron.");
        } else {
            System.out.println("Patron or item not found.");
        }
    }

     static Patron findPatronByID(Library library, String patronID) {
        for (Patron patron : library.patrons) {
            if (patron.getID().equals(patronID)) {
                return patron;
            }
        }
        return null;
    }

    private static Item findItemByID(Library library, String itemID) {
        for (Item item : library.items) {
            if (item.getUniqueID().equals(itemID)) {
                return item;
            }
        }
        return null; // Return null if item is not found
    }
}