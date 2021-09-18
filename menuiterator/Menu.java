import java.util.LinkedList;
import java.util.Scanner;

public class Menu {
    public static final int APPETIZERS = 1;
    public static final int MAIN_DISH = 2;
    public static final int DESSERT = 3;
    public static final boolean HEART_HEALTHY = true;
    public static final boolean NOT_HEART_HEALTHY = false;
    LinkedList<MenuItem> root;
    MenuIterator myIter = null;

    public Menu() {
        root = new LinkedList<MenuItem>();
    }

    public void addItem(String itemName, int category, boolean heartHealthy, String price) {
        MenuItem menuItem = new MenuItem(itemName, category, heartHealthy, price);
        root.add(menuItem);
    }

    public void queryAddItem() {
        Scanner cin = new Scanner(System.in);
        String itemName;
        int category;
        boolean heartHealthy;
        String price;
        System.out.println("\nItem to be added to Menu");
        System.out.println("------------------------");
        System.out.print("Dish name: ");
        itemName = cin.nextLine();
        System.out.println("Course category: ");
        System.out.println("   1 Appetizer");
        System.out.println("   2 Main Course");
        System.out.println("   3 Dessert");
        System.out.print("Selection: ");
        category = cin.nextInt();
        cin.nextLine();
        System.out.print("Heart Healthy? (Y/N): ");
        heartHealthy = cin.nextLine().toUpperCase().substring(0, 1).equals("Y") ? true : false;
        System.out.print("Item Price: $");
        price = cin.next();
        cin.nextLine();
        this.addItem(itemName, category, heartHealthy, price);
    }

    public void print() {
        for (MenuItem m : root)
            System.out.println(m);
    }
///*
    public static void displayOptions() {
        System.out.println("(D)isplay Menu");
        System.out.println("(A)dd Item to Menu");
        System.out.println("(Q)uit");
        System.out.print("Selection: ");
    }
//*/
    public class MenuItem {
        String itemName;
        int category;
        boolean heartHealthy;
        public String price;

        public MenuItem(String itemName, int category, boolean heartHealthy, String price) {
            this.itemName = itemName;
            this.category = category;
            this.heartHealthy = heartHealthy;
            this.price = price;
        }

        public String toString() {
            String retVal = "" + itemName + ", ";
            switch (category) {
                case 1:
                    retVal = retVal + "Appetizer, ";
                    break;
                case 2:
                    retVal = retVal + "Main Course, ";
                    break;
                case 3:
                    retVal = retVal + "Dessert, ";
                    break;
            }
            retVal = retVal + (heartHealthy ? "Heart Healthy, " : "");
            retVal = retVal + "$" + price;
            return retVal;
        }
    }

    public void remove(TargetIterator iter) {
        iter.remove();
    }
// /*
    public static void displayFullMenu(Menu menu) {
        TargetIterator myIter = new CategoryIterator(menu, APPETIZERS);
        System.out.println("Appetizers");
        while ((boolean) myIter.hasNext()) {
            System.out.println("\t" + myIter.next());
        }
        myIter = new CategoryIterator(menu, MAIN_DISH);
        System.out.println("Main Course");
        while ((boolean) myIter.hasNext()) {
            System.out.println("\t" + myIter.next());
        }
        myIter = new CategoryIterator(menu, DESSERT);
        System.out.println("Desserts");
        while ((boolean) myIter.hasNext()) {
            System.out.println("\t" + myIter.next());
        }
        System.out.println();
    }

 //*/
/// *
    public void searchMenu() {
        TargetIterator myIter = null;
        String userResponse = "";
        String savedPrice = "";
        Double userResponseDouble;
        int category = 0;
        boolean setCat = false;
        boolean setHH = false;
        boolean setPrice = false;
        Scanner cin = new Scanner(System.in);

        System.out.print("Show all Items (Y): ");
        userResponse = cin.nextLine();
        userResponse = fixResponse(userResponse);
        if (userResponse.equals("N")) {
            System.out.print("\nSearch by Course (Y/N): ");
            userResponse = cin.nextLine();
            userResponse = fixResponse(userResponse);
            if (userResponse.equals("Y")) {
                setCat = true;
                System.out.println("Course category: ");
                System.out.println("   1 Appetizer");
                System.out.println("   2 Main Course");
                System.out.println("   3 Dessert");
                System.out.print("Selection: ");
                category = cin.nextInt();
                if (category < 1 || category > 3)
                    category = 2;
                cin.nextLine();
                myIter = new CategoryIterator(this, category);
            }
            System.out.print("       by Heart Healthy (Y/N): ");
            userResponse = cin.nextLine();
            userResponse = fixResponse3Choice(userResponse);
            if (userResponse.equals("Y") || userResponse.equals("N")) {
                setHH = true;
                if (myIter == null)
                    myIter = new HeartHealthyIterator(this, userResponse.equals("Y") ? HEART_HEALTHY : NOT_HEART_HEALTHY);
                else
                    myIter = new HeartHealthyIterator(myIter, userResponse.equals("Y") ?HEART_HEALTHY : NOT_HEART_HEALTHY);
            }
            System.out.print("       by Price: $");
            userResponse = cin.nextLine();
            try {
                userResponseDouble = Double.parseDouble(userResponse);
            } catch (NumberFormatException msg) {
                userResponseDouble = -1.0;
            }
            if (userResponseDouble >= 0.00) {
                userResponse = "" + ((int) (userResponseDouble * 100)) / 100.0;
                setPrice = true;
                savedPrice = userResponse;
                if (myIter == null)
                    myIter = new PriceIterator(this, savedPrice);
                else
                    myIter = new PriceIterator(myIter, savedPrice);
            }
            if (myIter != null)
                while ((boolean) myIter.hasNext()) {
                    System.out.print(myIter.next() + "   [D]elete?:");
                    userResponse = cin.nextLine();
                    userResponse = fixResponse(userResponse);
                    if (userResponse.equals("D")) {
                        remove(myIter);
                    }
                }
        } else {
            myIter = new AllItemsIterator(this);
            while ((boolean) myIter.hasNext()) {
                System.out.print(myIter.next() + "   [D]elete?: ");
                userResponse = cin.nextLine();
                userResponse = fixResponse(userResponse);
                if (userResponse.equals("D")) {
                    remove(myIter);
                }
            }
        }
    }
//*/
    public static String fixResponse(String response) {
        if (response.length() == 0)
            response = "N";
        else
            response = response.toUpperCase().substring(0, 1);
        return response;
    }

    public static String fixResponse3Choice(String response){
        if (response.length() == 0)
            response = "";
        else {
            response = response.toUpperCase().substring(0, 1);
            if (!(response.equals("Y") || response.equals("N")))
                response = "";
        }
        return response;
    }

    public static void main(String[] args) {

        Menu eatAtJoesMenu = new Menu();
        eatAtJoesMenu.addItem("Caesar Salad", Menu.APPETIZERS, Menu.HEART_HEALTHY, "14.99");
        eatAtJoesMenu.addItem("Lobster Dinner", Menu.MAIN_DISH, Menu.NOT_HEART_HEALTHY, "24.99");
        eatAtJoesMenu.addItem("Rice Pudding", Menu.DESSERT, Menu.NOT_HEART_HEALTHY, "3.50");
        eatAtJoesMenu.addItem("Shrimp Cocktail", Menu.APPETIZERS, Menu.NOT_HEART_HEALTHY, "6.99");
        eatAtJoesMenu.addItem("Prime Rib", Menu.MAIN_DISH, Menu.NOT_HEART_HEALTHY, "23.99");
        eatAtJoesMenu.addItem("Creme Brulee", Menu.DESSERT, Menu.NOT_HEART_HEALTHY, "3.50");
        eatAtJoesMenu.addItem("Charcuterie", Menu.APPETIZERS, Menu.NOT_HEART_HEALTHY, "16.99");
        eatAtJoesMenu.addItem("Spaghetti", Menu.MAIN_DISH, Menu.NOT_HEART_HEALTHY, "17.99");
        eatAtJoesMenu.addItem("Fresh Fruit", Menu.DESSERT, Menu.HEART_HEALTHY, "6.50");
        eatAtJoesMenu.addItem("Risotto", Menu.APPETIZERS, Menu.NOT_HEART_HEALTHY, "8.99");
        eatAtJoesMenu.addItem("Rockfish", Menu.MAIN_DISH, Menu.HEART_HEALTHY, "28.99");
        eatAtJoesMenu.addItem("Ice Cream", Menu.DESSERT, Menu.NOT_HEART_HEALTHY, "5.50");

        boolean done = false;
        Scanner cin = new Scanner(System.in);
        String userResponse;
        while (!done) {
            System.out.println();
            displayOptions();
            userResponse = cin.nextLine();
            userResponse = fixResponse(userResponse);
            switch (userResponse) {
                case "D":
                    displayFullMenu(eatAtJoesMenu);
                    System.out.println();
                    eatAtJoesMenu.searchMenu();
                    break;
                case "A":
                    eatAtJoesMenu.queryAddItem();
                    System.out.println("New Menu");
                    System.out.println("--------");
                    displayFullMenu(eatAtJoesMenu);
                    break;
                case "Q":
                    done = true;
                    break;
                default:
                    System.out.println(userResponse + " is not a valid option.\n");
            }
        }
    }
}