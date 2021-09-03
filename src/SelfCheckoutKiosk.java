//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Self Checkout Kiosk
// Course: CS 300 Spring 2021
//
// Author: Zachary Collins
// Email: ztcollins@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: -
// Online Sources: -
//
///////////////////////////////////////////////////////////////////////////////
/**
 * Multiple operations used for the Self-Checkout Kiosk Driver to run properly. All methods run
 * properly on their own, but some may require the usage of the GROCERY_ITEMS array. One exception
 * to this case is the getSubTotal method which requires the getItemPrice method. The finished
 * product should display a menu of prices and allows the user to add and remove products from the
 * store and then display their final price. To find the flow of each method in the final product,
 * check the SelfCheckoutDriver.
 * 
 * @author Zach C
 *
 */
public class SelfCheckoutKiosk {

  public static final double TAX_RATE = 0.05; // sales tax
  // a perfect-size two-dimensional array that stores the available items in the grocery store
  // GROCERY_ITEMS[i][0] refers to a String that represents the name of the item
  // identified by index i
  // GROCERY_ITEMS[i][1] refers to a String that represents the unit price of the item
  // identified by index i in dollars.


  public static final String[][] GROCERY_ITEMS = new String[][] {{"Apple", "$1.59"},
      {"Avocado", "$0.59"}, {"Banana", "$0.49"}, {"Beef", "$3.79"}, {"Blueberry", "$6.89"},
      {"Broccoli", "$1.79"}, {"Butter", "$4.59"}, {"Carrot", "$1.19"}, {"Cereal", "$3.69"},
      {"Cheese", "$3.49"}, {"Chicken", "$5.09"}, {"Chocolate", "$3.19"}, {"Cookie", "$9.5"},
      {"Cucumber", "$0.79"}, {"Eggs", "$3.09"}, {"Grape", "$2.29"}, {"Ice Cream", "$5.39"},
      {"Milk", "$2.09"}, {"Mushroom", "$1.79"}, {"Onion", "$0.79"}, {"Pepper", "$1.99"},
      {"Pizza", "$11.5"}, {"Potato", "$0.69"}, {"Spinach", "$3.09"}, {"Tomato", "$1.79"}};



  /**
   * Returns the name of the item given its index
   * 
   * @param index - unique identifier of an item
   * @return the item name
   */
  public static String getItemName(int index) {
    return GROCERY_ITEMS[index][0];
  }


  /**
   * Returns the price of an item given its index (unique identifier)
   * 
   * @param index - unique identifier of an item
   * @return the item price
   */
  public static double getItemPrice(int index) {
    String removeDollar = GROCERY_ITEMS[index][1].substring(1);
    return Double.parseDouble(removeDollar);

  }


  /**
   * Prints the Catalog of the grocery store (item identifiers, names, and prices)
   * 
   */
  public static void printCatalog() {
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("Item id \tName \t\t Price");
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    for (int i = 0; i < GROCERY_ITEMS.length; i++) {
      System.out.println(i + "\t\t" + GROCERY_ITEMS[i][0] + "    \t " + GROCERY_ITEMS[i][1]);
    }
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
  }


  /**
   * Adds the name of a grocery item given its identifier at the end of (the bagging area) the
   * oversize array defined by the items array and its size If the items array reaches its capacity,
   * the following message: "Error! No additional item can be scanned. Please wait for
   * assistance."will be displayed and the method returns without making any change to the contents
   * of the items array.
   * 
   * @param id    - identifier of the item to be added to the bagging area (index of the item in the
   *              GROCERY_ITEMS array)
   * @param items - array storing the names of the items checked out and placed in the bagging area
   * @param size  - number of elements stored in items before trying to add a new item
   * @return Returns the number of elements stored in bagging area after the item with the provided
   *         identifier was added to the bagging area
   */
  public static int addItemToBaggingArea(int id, String[] items, int size) {
    if (items.length <= size) {
      System.out.println("Error! No additional item can be scanned. Please wait for assistance.");
      return size;
    } else {
      items[size] = getItemName(id);
      size++;
      return size;
    }
  }


  /**
   * Returns the number of occurrences of a given item in an oversize array of strings. The
   * comparison to find the occurrences of item is case insensitive.
   * 
   * @param item  - item to count its occurrences
   * @param items - a bag of string items
   * @param size  - number of items stored in items
   * @return number of occurrences of a given item
   */
  public static int count(String item, String[] items, int size) {
    int occurrences = 0;
    for (int i = 0; i < size; i++) {
      if (items[i].equals(item)) {
        occurrences++;
      }
    }
    return occurrences;
  }


  /**
   * Returns the index of the first occurrence of item in items if found, and -1 if the item not
   * found
   * 
   * @param item  - element to search for
   * @param items - an array of string elements
   * @param size  - number of elements stored in items
   * @return index of item or if it is not found
   */
  public static int indexOf(String item, String[] items, int size) {
    int index = -1;
    for (int i = 0; i < size; i++) {
      if (items[i].equals(item)) {
        index = i;
        break;
      }
    }
    return index;
  }


  /**
   * Removes the first occurrence of itemToRemove from the bagging area defined by the array items
   * and its size. If no match with itemToRemove is found, the method displays the following error
   * message "WARNING: item not found." without making any change to the items array. This method
   * compacts the contents of the items array after removing the itemToRemove so there are no empty
   * spaces in the middle of the array.
   * 
   * @param itemToRemove - item to remove from the bagging area
   * @param items        - a bag of items
   * @param size         - number of elements stored in the bag of items
   * @return returns the number of items present in the cart after the itemToRemove is removed from
   *         the cart
   */
  public static int remove(String itemToRemove, String[] items, int size) {
    if (size <= 0) {
      System.out.println("WARNING: attempting to remove item from empty" + " bagging area");
      return size;
    }
    String[] removedItems = new String[items.length];
    int found = 0;
    int subtracter = 0; // prevents null spaces from forming in between
    for (int i = 0; i < size; i++) {
      if (items[i].equals(itemToRemove) && found == 0) {
        found++;
        subtracter++;
      } else {
        removedItems[i - subtracter] = items[i];
      }
    }

    for (int i = 0; i < removedItems.length; i++) {
      items[i] = removedItems[i];
    }
    if (found == 0) {
      System.out.println("WARNING: item not found.");
      return size;
    }
    return size - 1;

  }


  /**
   * Gets a copy of the items array without duplicates. Adds every unique item stored within the
   * items array to the itemsSet array.The itemsSet array is initially empty. Recall that a set is a
   * collection which does not contain duplicate items). On the other hand, this method does not
   * make any change to the contents of the items array.
   * 
   * @param items    - list of items added to the bagging area
   * @param size     - number of elements stored in items
   * @param itemsSet - reference to an empty array which is going to contain the set of items
   *                 checked out (it does not contain duplicates)
   * @return returns the number of elements in items without accounting duplicates. In other words,
   *         this method returns the new size of the itemsSet array
   */
  public static int getUniqueCheckedOutItems(String[] items, int size, String[] itemsSet) {
    int newSize = 0;
    int subtracter = 0; // keeps itemsSet[] from having null spaces in between
    boolean found;
    for (int i = 0; i < size; i++) {
      found = false;
      for (int j = 0; j < itemsSet.length; j++) {
        if (items[i].equals(itemsSet[j])) {
          found = true;
          subtracter++;
          break;
        }
      }
      if (found == false) {
        itemsSet[i - subtracter] = items[i];
        newSize++;
      }
    }
    return newSize;
  }


  /**
   * Returns the total value (price) of the scanned items at checkout without tax in $ (double)
   * 
   * @param items - an array which stores the items checked out
   * @param size  - number of elements stored in the items array
   * @return Returns the total value (price) of the scanned items at checkout without tax in $
   *         (double)
   */
  public static double getSubTotalPrice(String[] items, int size) {
    double price = 0;

    for (int i = 0; i < size; i++) {
      int tempIndex = 0; // used to store the index of a grocery item
      for (int j = 0; j < GROCERY_ITEMS.length; j++) {
        if (items[i].equals(GROCERY_ITEMS[j][0])) {
          tempIndex = j;
        }
      }
      price = price + getItemPrice(tempIndex);
    }
    return price;
  }

}
