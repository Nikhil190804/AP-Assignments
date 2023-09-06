package AP_A1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Iterator;
import java.time.Duration;
import java.time.Instant;


class Librarian {
    public Librarian(){
        boolean start_lib = true;
        Scanner input = new Scanner(System.in);
        while(start_lib){
        System.out.println("---------------------------------");
        System.out.println("1.      Register a Member");
        System.out.println("2.      Remove a Member");
        System.out.println("3.      Add a Book");
        System.out.println("4.      Remove a Book");
        System.out.println("5.      View all members along with their books and fines to be paid");
        System.out.println("6.      View all Books");
        System.out.println("7.      Back");
        System.out.println("---------------------------------");
        int choice = input.nextInt();
        if(choice==1){
        register_a_member();
        } else if (choice==2) {
            remove_a_member();
        }
        else if(choice==3){
        add_book();
        }
        else if(choice==4){
            remove_book();
        }
        else if(choice==5){
            member_record();
        }
        else if(choice==6){
            view_books(1);
        }
        else if(choice==7){
            return;
        }
        else{
            System.out.println("Please Enter a valid option!!!");
            continue;
        }
        }
    }


    void register_a_member(){
         String name;
         int age;
         String  phone_no;
         int mem_id;
         Scanner input = new Scanner(System.in);
         System.out.print("Enter The Name: ");
         name=input.nextLine();
         System.out.print("Enter The Age: ");
         age=input.nextInt();
         age=age+0;
         System.out.print("");
         System.out.print("Enter The Phone Number: ");
         input.nextLine();
         phone_no=input.nextLine();
         mem_id=Main.Member_ID;
        System.out.println("Member Successfully Registered with Member ID:  "+mem_id);
        Main.Member_ID++;
        // now add the member into central database
        Map<Integer, String[]> my_books = new HashMap<>();
        Member newMember = new Member(name,phone_no,0,mem_id,my_books);
        Main.Member_Record.add(newMember);
     }


     void remove_a_member(){
        int mem_id;
         System.out.print("Enter the Member ID to Delete: ");
        Scanner input = new Scanner(System.in);
        mem_id=input.nextInt();
        input.nextLine();
        //  perfrom the deletion here
        Iterator<Member> iterator = Main.Member_Record.iterator();
        while (iterator.hasNext()) {
            Member member = iterator.next();
            if (member.member_id == mem_id) {
                //check for the fine
                int[] array = fine_to_lib(member.my_books);
                if(array[0]==-1){
                    //empty
                    System.out.println("Member with ID " + mem_id + " deleted.");
                    iterator.remove(); // Remove the member from the list
                }
                else{
                    System.out.println("The Given Member Has Some Issued Books Hence Can't Remove!!!");
                }
                return; // Exit the loop after deletion
            }
        }
        System.out.println("Incorrect Details Provided!!!");
     }

     
    void do_nothing(){
        return;
    }
     void add_book(){
         Scanner input = new Scanner(System.in);
         String book_title;
         String author;
         int copies;
         String status="Available";
         System.out.print("Enter the Book Name: ");
         book_title=input.nextLine();
         System.out.print("Enter the Author's Name: ");
         author=input.nextLine();
         System.out.print("Enter the Number of copies: ");
         copies=input.nextInt();
         input.nextLine();
         //  now begin adding these book to the central database of books
        for(int i=0;i<copies;i++){
            String[] array = new String[3];
            array[0]=book_title;
            array[1]=author;
            array[2]=status;
            int id=Main.Book_ID;
            Main.Book_Catalogue.put(id,array);
            Main.Book_ID++;
        }
        System.out.println("Book Added Successfully!");
     }


    void remove_book(){
        // taking book id as a input to delete book from database
        Scanner input = new Scanner(System.in);
        int removal_id;
        System.out.print("Enter The Book ID: ");
        removal_id=input.nextInt();
        input.nextLine();
        // now loop across the hashmap and find the book
        for (Integer key : Main.Book_Catalogue.keySet()) {
            if (key == removal_id) {
                String[] bookDetails = Main.Book_Catalogue.get(key);
                if(bookDetails[2]=="Available"){
                    //nnow a valid removal
                    Main.Book_Catalogue.remove(key);
                    System.out.println("Book with ID " + key + " has been deleted.");
                }
                else{
                    System.out.println("The Given Book has been Issued to a Member Hence Can't Remove!!!");
                }
                break; // Exit the loop
            }
        }
    }

    static void view_books(int flag){
        for (Integer key : Main.Book_Catalogue.keySet()) {
            String[] array = Main.Book_Catalogue.get(key);
            String bookName = array[0];
            String author = array[1];
            String stat = array[2];
            if(flag==1){
                System.out.println("-----------------------");
                System.out.println("Book ID: " + key);
                System.out.println("Book Name: " + bookName);
                System.out.println("Author: " + author);
                System.out.println("Book Status: " + stat);
                System.out.println("-----------------------");
            }
            else{
                if(stat.equals("Issued")){
                    continue;
                }
                else{
                    System.out.println("-----------------------");
                    System.out.println("Book ID: " + key);
                    System.out.println("Book Name: " + bookName);
                    System.out.println("Author: " + author);
                    System.out.println("-----------------------");
                }  
            }
             // Add a blank line for separation
        }
    }


    void member_record(){
        System.out.println("The Registered Members with their record ......................");
        for(Member member : Main.Member_Record){
            System.out.println("--------------------------------");
            System.out.println("Member ID: " + member.member_id);
            System.out.println("Name: " + member.name);
            System.out.println("Phone Number: " + member.phone_number);
            // now call for fine 
            int[] array = fine_to_lib(member.my_books);
            if(array[0]==-1){
                // no book issued yet
                System.out.println("Member has no issued books");
                continue;
            }
            else{
                System.out.println("Books are as follows.....");
                int i=0;
                for (Map.Entry<Integer, String[]> bookEntry : member.my_books.entrySet()) {
                int bookId = bookEntry.getKey();
                String[] bookDetails = bookEntry.getValue();
                String bookName = bookDetails[0];
                String author = bookDetails[1];
                System.out.println("   --------");
                System.out.println("  Book ID: " + bookId);
                System.out.println("  Book Name: " + bookName);
                System.out.println("  Author: " + author);
                System.out.println("  Fine on this Book: "+array[i]);
                System.out.println("   --------");
                i++;
                }
            }
            //System.out.println("Fine: " + member.fine);
            System.out.println("--------------------------------");
        }

    }


    int[]  fine_to_lib(Map<Integer, String[]> books){
        int[] return_value=new int[2];
        boolean isEmpty=books.isEmpty();
        Instant end=Instant.now();
        if(isEmpty==true){
            return_value[0]=-1;
            return_value[1]=-1;
            return return_value;
        }
        else{
            // now processs
            int i=0;
            for(Map.Entry<Integer,String[]> entry: books.entrySet()){
                //int key=entry.getKey();
                String[] values = entry.getValue();
                Instant start = Instant.parse(values[2]);
                Duration duration = Duration.between(start, end);
                long seconds = duration.getSeconds();
                seconds=seconds-10;
                if(seconds<=10){
                    return_value[i]=0;
                }
                else{
                    return_value[i]=(int)seconds*3;
                }
                i++;
            }
            return return_value;
        }
    }

}

