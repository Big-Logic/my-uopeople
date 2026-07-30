/**
 * Generic LibraryItem class representing items in a library.
 * @param <T> Type of item ID
 */
public class LibraryItem<T> {

    private String title;
    private String author;
    private T itemID;

    public LibraryItem(String title, String author, T itemID) {
        this.title = title;
        this.author = author;
        this.itemID = itemID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public T getItemID() {
        return itemID;
    }

    @Override
    public String toString() {
        return "ID: " + itemID +
                ", Title: " + title +
                ", Author: " + author;
    }
}