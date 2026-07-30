import java.util.ArrayList;

/**
 * Generic Catalog class that stores different types of library items.
 *
 * @param <T> Type of LibraryItem stored
 */
public class Catalog<T extends LibraryItem<?>> {

    private ArrayList<T> items;


    public Catalog() {
        items = new ArrayList<>();
    }


    // Add item to catalog
    public void addItem(T item) {
        items.add(item);
        System.out.println("Item added successfully.");
    }


    // Remove item by ID
    public void removeItem(Object id) throws ItemNotFoundException {

        for (T item : items) {

            if(item.getItemID().equals(id)) {
                items.remove(item);
                System.out.println("Item removed successfully.");
                return;
            }
        }

        throw new ItemNotFoundException(
                "Error: Item with ID " + id + " does not exist."
        );
    }


    // Retrieve item details
    public T getItem(Object id) throws ItemNotFoundException {

        for(T item : items){

            if(item.getItemID().equals(id)){
                return item;
            }
        }

        throw new ItemNotFoundException(
                "Error: Item not found."
        );
    }


    // Display all items
    public void displayCatalog(){

        if(items.isEmpty()){
            System.out.println("Catalog is empty.");
            return;
        }

        System.out.println("\nCurrent Library Catalog:");

        for(T item : items){
            System.out.println(item);
        }
    }
}