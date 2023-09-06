package AP_A1;

// import all necessary library
import java.util.Objects;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


public class Main {
    // define some data structures - for member record and books record
    public static int Member_ID=1;
    public static int Book_ID=1;
    // structure of Book_Catalogue is {Book_ID:[Name, Author , Status]}
    public static Map<Integer, String[]> Book_Catalogue = new HashMap<>();
    public static List<Member> Member_Record = new ArrayList<>();
    public static void main(String[] args) {
        boolean start_library=true;
        // start the portal
        System.out.println("Library Portal Initialized");
        Scanner input = new Scanner(System.in);
        while(start_library){
            System.out.println("---------------------------------");
            System.out.println("1.      Enter as a Librarian");
            System.out.println("2.      Enter as a Member");
            System.out.println("3.      Exit");
            System.out.println("---------------------------------");
            String choice=input.nextLine();
            // now resolve the choice of options here
            if(Objects.equals(choice, "1")){
                Librarian main_librarian=new Librarian();
                // call the librarian class here
                main_librarian.do_nothing();
            } else if (Objects.equals(choice, "2")) {
                String mem_name;
                String phone;
                System.out.print("Enter The Name: ");
                mem_name=input.nextLine();
                System.out.print("Enter Phone Number: ");
                phone=input.nextLine();
                Map<Integer, Map<Integer,String[]> > result=check_for_valid_member(mem_name,phone);
                if(result.containsKey(-1)){
                    //List<String> book_of_mem = new ArrayList<>();
                    System.out.println("Member with Name: "+mem_name+" and Phone No: "+phone+" doesn't exist.");
                    continue;
                    // enter now
                }
                else{
                    // bhag yha se
                    Map.Entry<Integer, Map<Integer, String[]>> entry = result.entrySet().iterator().next();
                    Integer outerKey = entry.getKey();
                    Map<Integer, String[]> books_of_mem = new HashMap<>();
                    books_of_mem=result.get(outerKey);
                    System.out.println("Welcome "+mem_name+" Member ID: "+outerKey);
                    Member member_object = new Member(mem_name, phone, 0,outerKey,books_of_mem);
                    member_object.main();
                }
                //call the member class here after some checks
                
            }
            else if (Objects.equals(choice, "3")){
                // break the loop and terminate
                start_library=false;
                Terminate();
            }
            else{
                System.out.println("Please Enter a Valid Option!!!");
                continue;
            }
        }

    }


    public static void Terminate(){
        System.out.println("");
        System.out.println("Thanks for visiting!\n");
        System.exit(0);
    }


    static Map<Integer, Map<Integer,String[]> > check_for_valid_member(String a,String b){
        Map<Integer, Map<Integer,String[]> > return_me = new HashMap<>();
        for(Member mem : Member_Record){
            // member found 
            if(mem.name.equals(a) && mem.phone_number.equals(b) ){
                return_me.put(mem.member_id, mem.my_books);
                return return_me;
            }
        }
        // member not found
        return_me.put(-1, null);
        return return_me;
    }
}