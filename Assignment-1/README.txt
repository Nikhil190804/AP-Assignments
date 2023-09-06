Name: Nikhil Kumar
Roll No.: 2022322
Section: A
Topic: AP-ASSIGNMENT-1


This whole Assignment-1 is a Project which has Build System : Maven
The file pom.xml has all the info regrading the build system and other details.
The main code files are in the folder : /src/main/java/AP_A1
The package of this is "AP_A1".


---:Steps to Run The Project:---
1. Compile the whole project using Maven tools  tab in the right corner of IntelliIj.
2. Double-Click on the compile tool it will compile all the project source files.
3. Navigate to the folder which contains all the source code: /src/main/java/AP_A1. It will have three files: "Main,java" , "Librarian.java" ,"Member.java".
4. Now select the file "Main.java".
5. Now run this selected file. This file uses the other two files for the librarian and member functions .
Note: I have added pom.xml with a build tag , if it may not work then please run the source code manually i.e - run "Main.java".


:---Libraries used in whole project:---
Note: These all libraries come preinstalled with JDK
java.util.Scanner;
java.util.HashMap;
java.util.Map;
java.time.Instant;
java.time.Duration;
java.util.Objects;
java.util.List;
java.util.ArrayList;
java.util.Iterator;


:---Data Structures used:---
The program uses data structures such as List, Map, and arrays to store information about members and books.
Member information is stored in a List<Member> Member_Record.
Book information is stored in a Map<Integer, String[]> Book_Catalogue, where each book has a unique identifier.
These are declared as static so that we don't create this every time as they act as a database for every record of member and book.


:---Description of the Logic used to develop the project:---
I have created three classes : Main , Librarian , Member . Now the main file for overall execution is: Main.java
Rest other files are helper files i.e - are desperate classes having some methods .
The loop begins with the initialization of a boolean variable, start_library, set to true. This variable controls the loop's execution.
Inside the loop, the program presents a user-friendly menu, displaying available options .
The program uses the Scanner class to read the user's choice as a string.
The user's choice is compared using Objects.equals(choice, ...) to determine the action to take based on the selected option.
Depending on the user's choice, the program takes different paths:
If the user selects "Enter as a Librarian" (option 1), it creates an instance of the Librarian class, allowing librarians to manage the library.
If the user selects "Enter as a Member" (option 2), it prompts the user to enter their name and phone number and checks if a member with those details exists. If found, the user can interact with their member account.
If the user selects "Exit" (option 3), the loop termination condition is met.
Now after choosing a proper branch the controlled is transfer to the appropriate class which have its methods and thus all functionalities are carried out.