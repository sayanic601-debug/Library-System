
import java.util.*;
import java.io.*;

// Book Class
class Book {
    String title;
    String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String toString() {
        return "Title: " + title + " | Author: " + author;
    }
}

// Library Class
class Library {
    ArrayList<Book> books = new ArrayList<>();
    String fileName = "books.txt";

    Library() {
        loadBooks();
    }

    // Add Book
    void addBook(String title, String author) {
        books.add(new Book(title, author));
        saveBooks();
        System.out.println("Book added successfully!");
    }

    // Search Book
    void searchBook(String title) {
        boolean found = false;

        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found!");
        }
    }

    // List All Books
    void listBooks() {
        if (books.isEmpty()) {
            System.out.println("Library is empty.");
            return;
        }

        System.out.println("\n--- All Books ---");

        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Save Books to File
    void saveBooks() {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Book book : books) {
                writer.println(book.title + "|" + book.author);
            }
        } catch (IOException e) {
            System.out.println("Error saving books.");
        }
    }

    // Load Books from File
    void loadBooks() {
        File file = new File(fileName);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|", 2);

                if (data.length == 2) {
                    books.add(new Book(data[0], data[1]));
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading books.");
        }
    }
}

// Main Class
public class LibrarySystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n===== LIBRARY SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. List All Books");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();

                    library.addBook(title, author);
                    break;

                case 2:
                    System.out.print("Enter title to search: ");
                    String search = sc.nextLine();

                    library.searchBook(search);
                    break;

                case 3:
                    library.listBooks();
                    break;

                case 4:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
