package AP_A1;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.time.Instant;
import java.time.Duration;

class Member {
    // declare the properties of a member
    String name;
    String phone_number;
    int fine;
    int age;
    int member_id;
    Map<Integer, String[]> my_books = new HashMap<>();
    // List<String> my_books = new ArrayList<>();

    public Member(String name, String phone_number, int fine,int member_id, Map<Integer, String[]> my_books) {
        this.name = name;
        this.phone_number = phone_number;
        this.fine = fine;
        this.member_id=member_id;
        this.my_books = my_books;
    }

    void main() {
        boolean start_member = true;
        Scanner input = new Scanner(System.in);
        while (start_member) {
            System.out.println("---------------------------------");
            System.out.println("1.      List Available Books");
            System.out.println("2.      Issue Book");
            System.out.println("3.      List My Books");
            System.out.println("4.      Pay Fine");
            System.out.println("5.      Return Book");
            System.out.println("6.      Back");
            System.out.println("---------------------------------");
            String choice = input.nextLine();
            if (choice.equals("1")) {
                Librarian.view_books(0);
                // call for availble books
            } else if (choice.equals("2")) {
                long result = fine_to_library(my_books);
                if (result == -1 || result == 0) {
                    System.out.println("Following Books Can be issued.....");
                    Librarian.view_books(0);
                    System.out.println("");
                    int book_id;
                    String book_name;
                    System.out.print("Enter the Book ID: ");
                    book_id = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter the Book Name: ");
                    book_name = input.nextLine();
                    boolean flag = false;
                    for (Map.Entry<Integer, String[]> entry : Main.Book_Catalogue.entrySet()) {
                        int key = entry.getKey();
                        String[] values = entry.getValue();
                        String auth = values[1];
                        if (key == book_id) {
                            // valid
                            flag = true;
                            values[2] = "Issued";
                            Instant start = Instant.now();
                            String startTime = start.toString();
                            Main.Book_Catalogue.put(key, values);
                            String issued_list[] = { book_name, auth, startTime };
                            my_books.put(book_id, issued_list);
                            System.out.println("Issued Successfully.....");
                            break;
                        }
                    }
                    if (flag == false) {
                        System.out.println("Incorrect Details Provided !!!");
                        continue;
                    }

                    // empty h
                } else if (result == -2) {
                    System.out.println("Not allowed to Issue More Than 2 Books!!! ");
                    continue;
                } else {
                    System.out.println("You Have Some Dues to the library . Please Pay The Dues First !!!");
                    System.out.println("Dues pending at this Moment: "+(int)result);
                }
                // call for issue books
            } else if (choice.equals("3") || choice.equals("5")) {
                // call for my books
                int flag = 0;
                if (choice.equals("3")) {
                    flag = 0;
                } else {
                    flag = 1;
                }
                Boolean check = my_books.isEmpty();
                if (check == true) {
                    System.out.println("No Issued Books.....");
                } else {
                    System.out.println("Your Books are :-----");
                    for (Map.Entry<Integer, String[]> entry : my_books.entrySet()) {
                        int key = entry.getKey();
                        String[] values = entry.getValue();
                        String name = values[0];
                        String author = values[1];
                        System.out.println("--------------------------");
                        System.out.println("Book ID: " + key);
                        System.out.println("Book Name: " + name);
                        System.out.println("Author Name: " + author);
                        System.out.println("--------------------------");
                    }

                }
                if (flag == 1) {
                    System.out.println("Enter the details for Returning Book..........");
                    int id;
                    System.out.print("Enter the Book ID: ");
                    id = input.nextInt();
                    input.nextLine();
                    // call for return book
                    int var = 0;
                    for (Map.Entry<Integer, String[]> entry : Main.Book_Catalogue.entrySet()) {
                        int key = entry.getKey();
                        String[] values = entry.getValue();
                        if (key == id && values[2].equals("Issued")) {
                            // valid
                            //flag = true;
                            values[2] = "Available";
                            Main.Book_Catalogue.put(key, values);
                            for (Map.Entry<Integer, String[]> my_book_entry : my_books.entrySet()) {
                                int my_book_key = my_book_entry.getKey();
                                if (my_book_key == id) {
                                    // return after fine adder
                                    String[] my_book_value = my_book_entry.getValue();
                                    Instant parsedStartTime = Instant.parse(my_book_value[2]);
                                    Instant endTime = Instant.now();
                                    Duration duration = Duration.between(parsedStartTime, endTime);
                                    long seconds = duration.getSeconds();
                                    if (seconds <= 10) {
                                        System.out.println("Book ID: " + id +" successfully returned. ");
                                        my_books.remove(my_book_key);
                                        var=1;
                                    } else {
                                        seconds = seconds - 10;
                                        long money = seconds * 3;
                                        fine = (int)money;
                                        System.out.println("Book ID: " + id + " successfully returned. " + money
                                        + " Rupees has been charged for a delay of " + seconds + " days.");
                                        my_books.remove(my_book_key);
                                        var=1;
                                    }
                                }
                            }

                        }
                    }
                    if(var==0){
                        System.out.println("Wrong Details provided!!!");
                    }
                }
            } else if (choice.equals("4")) {
                // call for fine
                if (fine == 0) {
                    System.out.println("No Dues to the Library :-) ");
                } else {
                    System.out.println("Your Dues to The Library is: " + fine);
                    System.out.println("Dues Successfully Paid.....");
                    fine=0;
                }
            } else if (choice.equals("6")) {
                return;
            } else {
                System.out.println("Please Enter a valid option!!!");
                continue;
            }
        }
    }

    long fine_to_library(Map<Integer, String[]> my_books) {
        long result;
        boolean isempty = my_books.isEmpty();
        if (isempty == true) {
            return -1;
        } else {
            if (my_books.size() == 2) {
                return -2;
            }
            // check now
            Instant end = Instant.now();
            for (Map.Entry<Integer, String[]> entry : my_books.entrySet()) {
                //int key = entry.getKey();
                String[] values = entry.getValue();
                Instant start = Instant.parse(values[2]);
                Duration duration = Duration.between(start, end);
                long seconds = duration.getSeconds();
                seconds = seconds - 10;
                if (seconds <= 10) {
                    // no due
                    return 0;
                } else {
                    // dues
                    result = seconds * 3;
                    return result;
                }

            }

        }
        return 0;

    }
}

