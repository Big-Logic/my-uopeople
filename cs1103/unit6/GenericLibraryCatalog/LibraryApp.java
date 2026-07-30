import java.util.Scanner;


/**
 * Main class providing command-line interface.
 */
public class LibraryApp {


    public static void main(String[] args){


        Scanner scanner = new Scanner(System.in);


        Catalog<LibraryItem<?>> catalog =
                new Catalog<>();


        // Testing different item types

        Book book =
                new Book(
                        "Java Programming",
                        "James Gosling",
                        101
                );


        DVD dvd =
                new DVD(
                        "Introduction to Programming",
                        "Oracle",
                        "DVD001"
                );


        Magazine magazine =
                new Magazine(
                        "Technology Today",
                        "Tech Media",
                        202
                );


        catalog.addItem(book);
        catalog.addItem(dvd);
        catalog.addItem(magazine);



        int choice;


        do{

            System.out.println("\n===== Library Catalog Menu =====");
            System.out.println("1. View Catalog");
            System.out.println("2. Search Item");
            System.out.println("3. Remove Item");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");


            choice = scanner.nextInt();



            switch(choice){


                case 1:

                    catalog.displayCatalog();
                    break;



                case 2:

                    System.out.print("Enter Item ID: ");

                    Object searchID = scanner.next();

                    try{

                        System.out.println(
                            catalog.getItem(searchID)
                        );

                    }
                    catch(ItemNotFoundException e){

                        System.out.println(e.getMessage());
                    }

                    break;



                case 3:

                    System.out.print("Enter Item ID to remove: ");

                    Object removeID = scanner.next();


                    try{

                        catalog.removeItem(removeID);

                    }
                    catch(ItemNotFoundException e){

                        System.out.println(e.getMessage());
                    }

                    break;



                case 4:

                    System.out.println(
                        "Exiting Library System..."
                    );

                    break;



                default:

                    System.out.println(
                        "Invalid option."
                    );
            }


        }while(choice != 4);


        scanner.close();
    }
}