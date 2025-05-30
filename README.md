# Advanced Programming in Java - IIITD Coursework

This repository contains the coursework for the Advanced Programming in Java course offered at the Indraprastha Institute of Information Technology, Delhi (IIITD).

## Assignment 1: Library Management System

This assignment implements a command-line based library management system. Users can interact as either a Librarian or a Member.

**Core Classes & Functionalities:**

*   **`Main.java`**:
    *   **Entry Point**: Initializes the library portal and presents the main user interface (Librarian, Member, or Exit).
    *   **Data Storage**: Manages global static collections for books (`Book_Catalogue`) and member records (`Member_Record`).
    *   **Member Validation**: `check_for_valid_member(String name, String phone)`: Verifies member credentials before granting access to member functionalities.

*   **`Librarian.java`**: (Accessed via the 'Enter as a Librarian' option in `Main`)
    *   Provides a menu for library administration tasks:
    *   **`register_a_member()`**: Adds new members to the system.
    *   **`remove_a_member()`**: Deletes members (if they have no pending dues/issued books).
    *   **`add_book()`**: Adds new books and their copies to the library catalogue.
    *   **`remove_book()`**: Removes books from the catalogue (if not currently issued).
    *   **`view_books(int flag)`**: Lists all books; `flag` determines if all books or only available books are shown.
    *   **`member_record()`**: Displays a list of all members, their issued books, and any applicable fines.
    *   **`fine_to_lib(Map<Integer, String[]> books)`**: Calculates fines for overdue books (a 10-second grace period is provided, after which a fine of 3 units per second of delay is applied).

*   **`Member.java`**: (Accessed via the 'Enter as a Member' option in `Main` after successful validation)
    *   Represents a library member and provides a menu for member actions:
    *   **List Available Books**: Shows books currently available for borrowing (uses `Librarian.view_books(0)`).
    *   **Issue Book**:
        *   Allows members to borrow an available book.
        *   A member cannot issue more than 2 books.
        *   Checks for outstanding fines before allowing a new book to be issued.
        *   Records the issue time for fine calculation.
    *   **List My Books**: Displays all books currently borrowed by the member.
    *   **Pay Fine**: Allows the member to clear any accumulated fines.
    *   **Return Book**:
        *   Allows members to return a borrowed book.
        *   Calculates and applies a fine if the book is returned after the 10-second grace period.
    *   **`fine_to_library(Map<Integer, String[]> my_books)`**: Internal check to determine if a member is eligible to issue new books based on current fines or the number of books already borrowed.

This project is built using Maven. For more detailed information on running the assignment and specific interactions, please refer to the `README.txt` file located in the `Assignment-1` directory.

## Assignment 2: Zoo Management System

This assignment implements a command-line based zoo management system called "ZOOtopia!". It features separate interfaces for Administrators and Visitors, each with a range of functionalities.

**Core Classes & Functionalities:**

*   **`Main.java`**:
    *   **Entry Point**: Initializes the ZOOtopia! portal and presents the main menu (Admin, Visitor, View Special Deals, Exit).
    *   **Global Data Management**: Manages static `ArrayLists` for storing records of attractions (`Attractions_Record`), animals (`Animals_Record`), and visitors (`Visitor_Record`).
    *   **System Settings**: Holds static variables for various discount percentages (student, minor, senior), flags for activating special deals (e.g., `deal_2_attractions_15` for 15% off on >2 tickets), total zoo revenue, and counters for different animal types (Mammals, Reptiles, Amphibians).
    *   **`checkValidCredentials(String uname, String pass)`**: Validates administrator login details.
    *   **`handleVisitors()`**: Provides options for visitors to either register a new account or log in to an existing one.
    *   **`viewDeals()`**: Displays any special deals that are currently active, as set by the administrator.

*   **`Admin.java`** (implements `Discountable` interface):
    *   Accessed via the 'Enter as a Admin' option in `Main` after successful credential validation.
    *   Provides a comprehensive menu for zoo administration:
    *   **`manageAttractions()`**: Allows administrators to Add, View, Modify (name, description, price), and Remove zoo attractions. Each attraction has a unique ID, name, description, price, and an open/closed status.
    *   **`manageAnimals()`**:
        *   Administrators can Add, Update (description), and Remove animals from the zoo.
        *   The system enforces a minimum of two animals per category (Mammals, Reptiles, Amphibians).
        *   **`addAnimal(int flag)`**: A helper method to add animals to the correct category (Mammal, Reptile, or Amphibian).
    *   **`scheduleEvents()`**: Allows administrators to change the open/closed status of attractions and update their ticket prices.
    *   **`applyDiscount()`**: (Method from `Discountable` interface) Enables administrators to set, modify, or remove percentage-based discounts for:
        *   Visitor categories: Minors (e.g., age < 18), Seniors (e.g., age > 60), Students (e.g., age 6-17).
        *   Specific zoo attractions.
    *   **`setSpecialDeals()`**: Activate or deactivate special promotional deals, such as "buy more than 2 tickets, get 15% off" or "buy more than 3 tickets, get 30% off."
    *   **`viewVisitorStats()`**: Displays key statistics: total number of visitors, total revenue generated by the zoo, and the name of the most popular attraction (based on visitor count).
    *   **`viewFeedback()`**: Allows administrators to read feedback submitted by visitors.

*   **`Visitor.java`** (implements `Discountable` interface):
    *   Represents a zoo visitor. Each visitor has a name, age, phone number, account balance, email, password, feedback string, membership status (No Membership, Basic, or Premium), and a map (`attraction_map`) to keep track of purchased tickets.
    *   Accessed via the 'Enter as a Member' (Visitor) option in `Main`, followed by registration or login.
    *   Provides a menu (`VisitorLoop()`) for visitor actions:
    *   **`exploreTheZoo()`**: Allows visitors to view lists of attractions (name, description, open/closed status) and animals (name, description). This action is free and does not require membership or tickets.
    *   **`buyMembership()`**:
        *   Visitors can purchase 'Basic Membership' (Rs. 20) or 'Premium Membership' (Rs. 50).
        *   Eligible visitors (based on age) can apply discount codes (e.g., "MINOR10" for 10% off if minor discount is active) during purchase.
        *   The cost is deducted from the visitor's balance and added to the zoo's total revenue.
    *   **`buyTickets()`**:
        *   Requires at least 'Basic Membership'. 'Premium Membership' holders do not need to buy individual tickets.
        *   Visitors select an attraction and the number of tickets they wish to purchase.
        *   The system automatically applies eligible age-based discounts and any active special deals (e.g., bulk ticket purchase discounts) to the total price.
        *   The final cost is deducted from the visitor's balance, and tickets are added to their `attraction_map`.
    *   **`viewDiscounts()`**: Displays available discount categories and their corresponding codes (e.g., "SENIOR20").
    *   **`viewSpecialDeals()`**: Shows currently active special deals (uses `Main.viewDeals()`).
    *   **`visitAnimals()`**:
        *   Requires membership.
        *   Lists all animals in the zoo. The visitor can choose an animal to either "Feed" (which prints the animal's sound) or "Read about" (which prints the animal's description).
    *   **`visitAttractions()`**:
        *   Requires membership.
        *   'Premium Members' can visit any open attraction without a ticket.
        *   'Basic Members' must have a purchased ticket for the specific attraction they wish to visit. One ticket for that attraction is consumed from their `attraction_map`.
        *   Visiting an attraction increments its visitor counter.
    *   **`leaveFeedback()`**: Allows visitors to type and submit their feedback about the zoo.
    *   **`validateDiscount(int discountType)`**: Internal helper to check if the visitor's age makes them eligible for a specific discount category (minor, student, senior).
    *   **`applyDeals(double price, int num_of_tickets)`**: Calculates the final price after applying any active quantity-based special deals.

*   **`Attractions.java`** (implements `Comparable<Attractions>`):
    *   Defines a zoo attraction with properties: unique ID, name, description, ticket price, schedule (1 for open, 0 for closed), any specific discount percentage applied to it, and a counter for the number of visitors.
    *   The `compareTo()` method allows sorting attractions based on the number of visitors, used to determine the most popular attraction.

*   **`Animal.java`** (abstract class):
    *   The base abstract class for all animals in the zoo. Defines common properties: `my_name`, `sound`, and `description`.
    *   Includes abstract methods: `feed()` and `read()`.
    *   **Subclasses**: `Mammals`, `Reptiles`, `Amphibians`. These concrete classes extend `Animal` and provide implementations for `feed()` (typically prints the animal's sound) and `read()` (prints the animal's description).

*   **`Discountable`** (interface):
    *   An interface with a single method `applyDiscount()`. It is implemented by both `Admin` (to set system-wide discounts) and `Visitor` (though its use in `Visitor` seems more for discount validation/application during purchases rather than defining new discounts).

This project is built using Maven. The `Assignment-2/README.txt` file contains specific instructions for building and running this assignment (e.g., `mvn clean`, `mvn compile`, `mvn package`, and `java -jar target/Assignment-2-1.0-SNAPSHOT.jar`).

## Building and Running the Assignments

Both assignments in this repository are Maven projects. To build and run them, you will typically use the following Maven commands in the respective assignment's root directory (i.e., `Assignment-1/` or `Assignment-2/`):

1.  **Compile:** `mvn compile`
2.  **Package:** `mvn package` (This usually creates a JAR file in the `target` directory)
3.  **Run:** The exact command to run the packaged application might vary.
    *   For Assignment 2, it is `java -jar target/Assignment-2-1.0-SNAPSHOT.jar`.
    *   For Assignment 1, the `README.txt` suggests running the `Main.java` file directly from an IDE after compilation, or potentially via a JAR if the `pom.xml` is configured for it.

Please refer to the `README.txt` file within each assignment's directory for specific and detailed instructions on building, running, and any required setup.
