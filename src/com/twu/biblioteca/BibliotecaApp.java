package com.twu.biblioteca;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class BibliotecaApp {

    public static final String WELCOME_MSG = "Welcome to Biblioteca!";
    public static final String MAINMENU_MSG = "Type option command";
    public static final String INVALIDOPT_MSG = "Select a valid option!";


    private Library library;
    private List<MenuOption> menuOptions;

    public static void main(String[] args) {
        BibliotecaApp biblioteca = new BibliotecaApp();
        print(biblioteca.welcome());
        biblioteca.initLibrary();
        biblioteca.initMenuOptions();
        biblioteca.mainMenu();
    }

    public String welcome() {
        return WELCOME_MSG;
    }

    public void mainMenu() {
        print("\n" + MAINMENU_MSG);
        menuOptions.forEach(m -> print(m.toString()));
        String userinput = readUserInput();
        selectMenuOption(userinput);
        mainMenu();
    }

    private void selectMenuOption(String userinput) {
        Optional<MenuOption> menuOption = menuOptions.stream().filter(m -> m.getCommand().equals(userinput)).findAny();
        if (menuOption.isPresent()) {
            menuOption.get().execute(this);
        } else {
            print(INVALIDOPT_MSG);
        }
    }

    public static String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printLibrary() {
        List<Book> listOfBooks = library.listAvailableBooks();
        print("List of books:");
        int longestTitle = getLongestString(listOfBooks.stream().map(book -> book.getTitle()));
        int longestAuthor = getLongestString(listOfBooks.stream().map(book -> book.getAuthor()));
        printHeader(longestTitle, longestAuthor);
        printBooks(longestTitle, longestAuthor, listOfBooks);
    }

    private void printBooks(int longestTitle, int longestAuthor, List<Book> listOfBooks) {
        for (Book book : listOfBooks) {
            printBookListLine(longestTitle, longestAuthor, book.getTitle(), book.getAuthor(), book.getYear() + "");
        }
    }

    private void printHeader(int longestTitle, int longestAuthor) {
        printBookListLine(longestTitle, longestAuthor, "Title", "Author", "Year");
    }

    private void printBookListLine(int longestTitle, int longestAuthor, String title, String author, String year) {
        StringBuilder builder = new StringBuilder();
        builder.append(title);
        appendSpaces(builder, longestTitle - title.length() + 1);
        builder.append(author);
        appendSpaces(builder, longestAuthor - author.length() + 1);
        builder.append(year);
        print(builder.toString());
    }

    private static void appendSpaces(StringBuilder builder, int numSpaces) {
        for (int i = 0; i < numSpaces; i++)
            builder.append(" ");
    }

    public static int getLongestString(Stream<String> streamOfStrings) {
        return streamOfStrings.max(Comparator.comparing(String::length)).orElse("").length();
    }

    public static void print(String stringToPrint) {
        System.out.println(stringToPrint);
    }

    private void initLibrary() {
        library = new Library();
        library.addBook("Harry Potter", "JK Rowling", 1997);
        library.addBook("Lord of the Rings", "JRR Tolkien", 1954);
        library.addBook("Book 3", "Jack Doe", 2001);
    }

    public Library getLibrary() {
        return library;
    }

    private void initMenuOptions() {
        menuOptions = new ArrayList<>();
        menuOptions.add(new MenuOption("List Books", "L",
                (Object bib)-> {((BibliotecaApp) bib).printLibrary(); return true;}));
        menuOptions.add(new MenuOption("Checkout Book", "C", "Thank you enjoy the book!",
                "That book is not available.", "Which book do you want to checkout?",
                (Object bookName)-> library.checkoutBook((String) bookName)));
        menuOptions.add(new MenuOption("Return Book", "R", "Thank you for returning the book!",
                "That is not a valid book to return.", "Which book do you want to return?",
                (Object bookName)-> library.returnBook((String) bookName)));
        menuOptions.add(new MenuOption("Quit", "Q",
                (Object bib)-> {System.exit(0); return true;}));
    }
}
