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
 * Tests all methods within SelfCheckoutKiosk class
 * 
 * @author Zach C
 *
 */
public class SelfCheckoutKioskTester {

  /**
   * Checks whether SelfCheckoutKisok.getItemName() and SelfCheckoutKisok.getItemPrice() method
   * works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testItemNameAndPriceGetterMethods() {
    // consider all identifiers values as input arguments
    // GROCERY_ITEMS array is a perfect size array. So, its elements are stored
    // in the range of indezes from 0 .. GROCERY_ITEMS.length -1
    for (int i = 0; i < SelfCheckoutKiosk.GROCERY_ITEMS.length; i++) {
      // check first for the correctness of the getItemName(i) method
      if (!SelfCheckoutKiosk.getItemName(i).equals(SelfCheckoutKiosk.GROCERY_ITEMS[i][0])) {
        System.out.println("Problem detected: Called your getItemName() method with "
            + "input value " + i + ". But it did not return the expected output.");
        return false;
      }

      // Now, let's check for the correctness of the getItemPrice(i) method
      double expectedPriceOutput =
          Double.valueOf(SelfCheckoutKiosk.GROCERY_ITEMS[i][1].substring(1).trim());
      // We do not use == to compare floating-point numbers (double and float)
      // in java. Two variables a and b of type double are equal if the absolute
      // value of their difference is less or equal to a small threshold epsilon.
      // For instance, if Math.abs(a - b) <= 0.001, then a equals b
      if (Math.abs((SelfCheckoutKiosk.getItemPrice(i) - expectedPriceOutput)) > 0.001) {
        // you can print a descriptive error message before returning false
        return false;
      }
    }
    return true; // No defect detected -> The implementation passes this test
  }


  /**
   * Checks the correctness of SelfCheckoutKiosk.addItemToBaggingArea() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddItemToBaggingArea() {
    // Create an empty bagging area
    String[] items = new String[10];
    int size = 0;

    // Define the test scenarios:

    // (1) Add one item to an empty bagging area
    // try to add an apple (id: 0) to the bagging area
    size = SelfCheckoutKiosk.addItemToBaggingArea(0, items, size);
    if (size != 1) {
      System.out.println("Problem detected: Tried to add one item to an empty, "
          + "bagging area. The returned size must be 1. But your addItemToBaggingArea "
          + "method returned a different output.");
      return false;
    }
    if (!items[0].equals(SelfCheckoutKiosk.getItemName(0))) {
      // notice here the importance of checking for the correctness of your getItemName()
      // method before calling it above
      System.out.println("Problem detected: Tried to add only one item to an empty, "
          + "bagging area. But that item was not appropriately added to the contents "
          + "of the items array.");
    }

    // (2) Consider a non-empty bagging area
    items = new String[] {"Milk", "Chocolate", "Onion", null, null, null, null};
    size = 3;
    size = SelfCheckoutKiosk.addItemToBaggingArea(10, items, size);
    if (size != 4) {
      System.out.println("Problem detected: Tried to add only one item to an non-empty, "
          + "bagging area. The size must be incremented after the method returns. But "
          + "it was not the case");
      return false;
    }
    if (!items[3].equals(SelfCheckoutKiosk.getItemName(10))) {
      System.out.println("Problem detected: Tried to add one item to an non-empty, "
          + "bagging area. But that item was not appropriately added to the contents "
          + "of the items array.");
    }

    // (3) Consider adding an item to a full bagging are
    items = new String[] {"Pizza", "Eggs", "Apples"};
    String[] itemsTester = new String[] {"Pizza", "Eggs", "Apples"};
    size = 3;
    size = SelfCheckoutKiosk.addItemToBaggingArea(2, items, size);
    if (size != 3) {
      System.out.println("Problem detected: size should not be larger than array");
      return false;
    }
    if (items[0] != "Pizza" && items[1] != "Eggs" && items[2] != "Apples") {
      System.out
          .println("Problem detected: items should not be changed when there" + " is no space");
      System.out.println(items[0] + items[1] + items[2]);
      return false;
    }



    return true; // No defects detected by this unit test
  }


  /**
   * Checks the correctness of SelfCheckoutKiosk.testCount() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCount() {
    // (1) a bagging area (defined by the items array and its size)
    // which contains 0 occurrences of the item
    String[] items1 = {"Pizza", "Eggs"};
    int size1 = 2;
    String item1 = "Apples";
    int occurrences1 = 0;
    if (SelfCheckoutKiosk.count(item1, items1, size1) != occurrences1) {
      System.out.println("ERROR: (1) occurences should equal 0. Actual: "
          + SelfCheckoutKiosk.count(item1, items1, size1));
      return false;
    }
    // (2) a bagging area which contains at least 4 items and only one occurrence
    // of the item to count
    String[] items2 = {"Pizza", "Eggs", "Pizza", "Apples"};
    int size2 = 4;
    String item2 = "Apples";
    int occurrences2 = 1;
    if (SelfCheckoutKiosk.count(item2, items2, size2) != occurrences2) {
      System.out.println("ERROR: (2) occurences should equal 1. Actual: "
          + SelfCheckoutKiosk.count(item2, items2, size2));
      return false;
    }
    // (3) a bagging area which contains at least 5 items
    // and 2 occurrences of the item to count.
    String[] items3 = {"Pizza", "Eggs", "Pizza", "Apples", "Eggs"};
    int size3 = 5;
    String item3 = "Eggs";
    int occurrences3 = 2;
    if (SelfCheckoutKiosk.count(item3, items3, size3) != occurrences3) {
      System.out.println("ERROR: (3) occurences should equal 2. Actual: "
          + SelfCheckoutKiosk.count(item3, items3, size3));
      return false;
    }

    return true;
  }


  /**
   * Checks the correctness of SelfCheckoutKiosk.indexOf() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testIndexOf() {
    // (1) Consider the cases where the items array contains at least one match
    // with the item to find
    String[] items1 = {"Pizza", "Eggs"};
    int size1 = 2;
    String item1 = "Eggs";
    int index1 = 1;
    if (SelfCheckoutKiosk.indexOf(item1, items1, size1) != index1) {
      System.out.println("ERROR: (1) index should equal 1. Actual: "
          + SelfCheckoutKiosk.indexOf(item1, items1, size1));
      return false;
    }

    // the case when the item was not stored in
    // the array and the expected output is -1
    String[] items2 = {"Pizza", "Eggs"};
    int size2 = 2;
    String item2 = "Apples";
    int index2 = -1;
    if (SelfCheckoutKiosk.indexOf(item2, items2, size2) != index2) {
      System.out.println("ERROR: (2) index should equal -1. Actual: "
          + SelfCheckoutKiosk.indexOf(item2, items2, size2));
      return false;
    }

    return true;
  }


  /**
   * Checks the correctness of SelfCheckoutKiosk.remove() method Checks that when only one attempt
   * to remove an item stored in the bagging area is made, only one occurrence of that item is
   * removed from the array of items, that the returned size is correct, and that the items array
   * contains all the other items.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemove() {
    String[] items = {"Pizza", "Eggs", "Eggs", "Apples", "Pizza"};
    int size = 5;
    String itemToRemove = "Eggs";
    int newSize = 4;
    if (SelfCheckoutKiosk.remove(itemToRemove, items, size) != newSize) {
      System.out.println("ERROR: size should equal 4. Actual: "
          + SelfCheckoutKiosk.remove(itemToRemove, items, size));
      return false;
    }
    if (items[0].equals("Pizza") && items[1].equals("Eggs") && items[2].equals("Apples")
        && items[3].equals("Pizza") && items[4] == "null") {
      System.out.println("ERROR: incorrect item placement");
      for (int i = 0; i < items.length; i++) {
        System.out.println(items[i]);
      }
      return false;
    }

    String[] items2 = {};
    int size2 = 0;
    String itemToRemove2 = "Eggs";
    int newSize2 = 0;
    if (SelfCheckoutKiosk.remove(itemToRemove2, items2, size2) != newSize2) {
      System.out.println("ERROR: size should equal 0. Actual: "
          + SelfCheckoutKiosk.remove(itemToRemove, items, size));
      return false;
    }


    return true;
  }



  /**
   * Checks the correctness of SelfCheckoutKiosk.testGetSubTotalPrice() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetSubTotalPrice() {
    String[] items = {"Pizza", "Eggs", "Eggs", "Apples", "Pizza"};
    int size = 5;
    double price = 30.77;
    if (price != SelfCheckoutKiosk.getSubTotalPrice(items, size)) {
      System.out.println("ERROR: Price mismatch. Should be: 30.77. Actual:"
          + SelfCheckoutKiosk.getSubTotalPrice(items, size));
      return false;
    }

    String[] items2 = {};
    int size2 = 0;
    double price2 = 0;
    if (price2 != SelfCheckoutKiosk.getSubTotalPrice(items2, size2)) {
      System.out.println("ERROR: Price mismatch. Should be: " + price2 + ". Actual:"
          + SelfCheckoutKiosk.getSubTotalPrice(items2, size2));
      return false;
    }
    return true;
  }

  /**
   * Checks the correctness of SelfCheckoutKiosk.testGetUniqueCheckedOutItems() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetUniqueCheckedOutItems() {
    String[] items = {"Pizza", "Eggs", "Eggs", "Apples", "Pizza"};
    String[] itemsSet = new String[7];
    int size = 5;
    int newSize = 3;
    if (newSize != SelfCheckoutKiosk.getUniqueCheckedOutItems(items, size, itemsSet)) {
      System.out.println("ERROR: size mismatch. Should be: 3. Actual:"
          + SelfCheckoutKiosk.getUniqueCheckedOutItems(items, size, itemsSet));
      return false;
    }

    String[] items2 = {};
    String[] itemsSet2 = new String[0];
    int size2 = 0;
    int newSize2 = 0;
    if (newSize2 != SelfCheckoutKiosk.getUniqueCheckedOutItems(items2, size2, itemsSet2)) {
      System.out.println("ERROR: size mismatch. Should be: 0. Actual:"
          + SelfCheckoutKiosk.getUniqueCheckedOutItems(items2, size2, itemsSet2));
      return false;
    }

    return true;
  }

  /**
   * main method used to call the unit tests
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out
        .println("tesItemNameAndPriceGetterMethods(): " + testItemNameAndPriceGetterMethods());
    // SelfCheckoutKiosk.printCatalog();
    System.out.println("testAddItemToBaggingArea():" + testAddItemToBaggingArea());
    System.out.println("testCount():" + testCount());
    System.out.println("testIndexOf():" + testIndexOf());
    System.out.println("testRemove():" + testRemove());
    System.out.println("testGetSubTotalPrice():" + testGetSubTotalPrice());
    System.out.println("testGetUniqueCheckedOutItems():" + testGetUniqueCheckedOutItems());
  }

}
