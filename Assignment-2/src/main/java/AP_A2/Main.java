package AP_A2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

//list all the interfaces used
interface Discountable {
    void applyDiscount();
}

public class Main {
    // list out the data structures and variables
    public static List<Visitor> Visitor_Record = new ArrayList<>();
    public static List<Attractions> Attractions_Record = new ArrayList<>();
    public static List<Animal> Animals_Record = new ArrayList<>();
    private static int student_discount = 0;
    private static int minor_discount = 10;
    private static int senior_discount = 20;
    private static int deal_2_attractions_15=0;
    private static int deal_3_attractions_30=0;
    private static double Total_Revenue = 0;
    private static int Mammals_Counter=0;
    private static int Reptiles_Counter=0;
    private static int Amphibians_Counter=0;
    
    public static int getDeal_2_attractions_15() {
        return deal_2_attractions_15;
    }

    public static void setDeal_2_attractions_15(int deal_2_attractions_15) {
        Main.deal_2_attractions_15 = deal_2_attractions_15;
    }

    public static int getDeal_3_attractions_30() {
        return deal_3_attractions_30;
    }

    public static void setDeal_3_attractions_30(int deal_3_attractions_30) {
        Main.deal_3_attractions_30 = deal_3_attractions_30;
    }

    public static int getMinor_discount() {
        return minor_discount;
    }

    public static void setMinor_discount(int minor_discount) {
        Main.minor_discount = minor_discount;
    }

    public static int getSenior_discount() {
        return senior_discount;
    }

    public static void setSenior_discount(int senior_discount) {
        Main.senior_discount = senior_discount;
    }

    public static int getStudent_discount() {
        return student_discount;
    }

    public static void setStudent_discount(int student_discount) {
        Main.student_discount = student_discount;
    }

    
    public static double getTotal_Revenue() {
        return Total_Revenue;
    }

    public static void setTotal_Revenue(double total_Revenue) {
        Total_Revenue = total_Revenue;
    }
    
    public static int getMammals_Counter() {
        return Mammals_Counter;
    }

    public static void setMammals_Counter(int mammals_Counter) {
        Mammals_Counter = mammals_Counter;
    }

    public static int getReptiles_Counter() {
        return Reptiles_Counter;
    }

    public static void setReptiles_Counter(int reptiles_Counter) {
        Reptiles_Counter = reptiles_Counter;
    }


    public static int getAmphibians_Counter() {
        return Amphibians_Counter;
    }

    public static void setAmphibians_Counter(int amphibians_Counter) {
        Amphibians_Counter = amphibians_Counter;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to ZOOtopia!!!!!");
        boolean start_loop = true;
        Scanner input_main = new Scanner(System.in);
        String choice;
        while (start_loop) {
            System.out.println("\n1. Enter as a Admin");
            System.out.println("2. Enter as a Member");
            System.out.println("3. View special deals");
            System.out.println("4. Exit");
            System.out.print("Enter Your Choice: ");
            choice = input_main.nextLine();
            if (Objects.equals("1", choice)) {
                // call admin class here
                String u_name;
                String pwd;
                System.out.print("Enter Admin Username: ");
                u_name = input_main.nextLine();
                System.out.print("Enter Admin Password: ");
                pwd = input_main.nextLine();
                boolean result = checkValidCredentials(u_name, pwd);
                if (result == true) {
                    // valid as a admin pass the control
                    System.out.println("Logged in as a Admin");
                    Admin admin = new Admin(result);
                    admin.startAdminLoop();
                } else {
                    System.out.println("Wrong Credentials!!!!!");
                    continue;
                }

            } else if (Objects.equals("2", choice)) {
                // call visitor here
                handleVisitors();
            } else if (Objects.equals("3", choice)) {
                // call special here
                viewDeals();

            } else if (Objects.equals("4", choice)) {
                // exit the main loop here
                start_loop = false;
                break;
            } else {
                System.out.println("Please Enter a valid option!!!!!");
                continue;
            }
        }
        //input_main.close();
    }

    public static boolean checkValidCredentials(String uname, String pass) {
        if ((Objects.equals(uname, Admin.getUsername())) && Objects.equals(pass, Admin.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public static void handleVisitors() {
        boolean start_handleVisiors_loop = true;
        Scanner input_handleVisitors = new Scanner(System.in);
        String choice;
        while (start_handleVisiors_loop) {
            System.out.println("1. Register ");
            System.out.println("2. LogIn ");
            System.out.println("3. Back");
            choice = input_handleVisitors.nextLine();
            if (Objects.equals("1", choice)) {
                // register a member and add it to arraylist
                String name;
                int age;
                String phone_number;
                int balance;
                String mail;
                String password;
                String feedback;
                System.out.print("Enter Visitor Name: ");
                name = input_handleVisitors.nextLine();
                System.out.print("Enter Visitor age: ");
                age= input_handleVisitors.nextInt();
                input_handleVisitors.nextLine();
                System.out.print("Enter Visitor Phone Number: ");
                phone_number = input_handleVisitors.nextLine();
                System.out.print("Enter Visitor Balance: ");
                balance = input_handleVisitors.nextInt();
                input_handleVisitors.nextLine();
                System.out.print("Enter Visitor Email: ");
                mail = input_handleVisitors.nextLine();
                System.out.print("Enter Visitor Password: ");
                password = input_handleVisitors.nextLine();
                feedback="";
                HashMap<Attractions,Integer> map = new HashMap<>();
                Visitor newVisitor= new Visitor(name, age, phone_number, balance, mail, password, feedback,-1,map);
                Visitor_Record.add(newVisitor);
                System.out.println("Registered Successfully.....");

            } else if (Objects.equals("2", choice)) {
                // search in arrylist here
                String mail;
                String password;
                System.out.print("Enter Visitor Mail :");
                mail=input_handleVisitors.nextLine();
                System.out.print("Enter Visitor Password :");
                password=input_handleVisitors.nextLine();
                //now search this in arrraylist
                boolean isFound = false;
                for (Visitor visitor : Visitor_Record) {
                    if(Objects.equals(visitor.getMail(), mail) && Objects.equals(visitor.getPassword(), password)){
                        isFound=true;
                        //now pass the control to visitor
                        visitor.VisitorLoop();
                        start_handleVisiors_loop=false;
                        //input_handleVisitors.close();
                        return;
                    } 
                }
                if(isFound==false){
                    System.out.println("Invalid User!!!!!");
                    continue;
                }
            } else if (Objects.equals("3", choice)) {
                start_handleVisiors_loop = false;
               // input_handleVisitors.close();
                return;
            } else {
                System.out.println("Enter a Valid Option!!!!!");
            }
            //input_handleVisitors.close();
        }
    }

    public static void viewDeals(){
        if(Main.deal_2_attractions_15==0 && Main.deal_3_attractions_30==0){
            System.out.println("\nNo Deals Set, Admin must set deals to view and apply..... ");
            return;
        }
        else{
            System.out.println("View Deals");
            if(Main.deal_2_attractions_15!=0){
                System.out.println("-------------------");
                System.out.println("DEAL: If a person buys more than 2 tickets, they get a special discount of 15% on the total amount");
                System.out.println("-------------------");
            }
            if(Main.deal_3_attractions_30!=0){
                System.out.println("-------------------");
                System.out.println("DEAL: If a person buys more than 3 tickets, they get a special discount of 30% on the total amount");
                System.out.println("-------------------");
            }
            return;
        }
    }
}
