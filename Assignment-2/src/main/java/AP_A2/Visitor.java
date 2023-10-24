package AP_A2;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Visitor {
    private String name;
    private int age;
    private String phone_number;
    private double balance;
    private String mail;
    private String password;
    private String feedback;
    private int membership;
    private HashMap<Attractions, Integer> attraction_map;

    public void setAttraction_map(HashMap<Attractions, Integer> attraction_map) {
        this.attraction_map = attraction_map;
    }

    public HashMap<Attractions, Integer> getAttraction_map() {
        return attraction_map;
    }

    public int getMembership() {
        return membership;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Visitor(String name, int age, String phone_number, int balance, String mail, String password,
            String feedback, int membership, HashMap<Attractions, Integer> map) {
        this.name = name;
        this.age = age;
        this.phone_number = phone_number;
        this.balance = balance;
        this.mail = mail;
        this.password = password;
        this.feedback = feedback;
        this.membership = membership;
        this.attraction_map = map;
    }

    public void VisitorLoop() {
        boolean start_visitor_loop = true;
        String choice;
        Scanner input_visitor = new Scanner(System.in);
        while (start_visitor_loop) {
            System.out.println("\nVisitor Menu");
            System.out.println("1. Explore the zoo");
            System.out.println("2. Buy Membership");
            System.out.println("3. Buy Tickets");
            System.out.println("4. View Discounts");
            System.out.println("5. View special Deals");
            System.out.println("6. Visit Animals");
            System.out.println("7. Visit Attractions");
            System.out.println("8. Leave Feedback");
            System.out.println("9. Log Out");
            System.out.print("Enter Your Choice: ");
            choice = input_visitor.nextLine();
            if (Objects.equals(choice, "1")) {
                exploreTheZoo();

            } else if (Objects.equals(choice, "2")) {
                buyMembership();

            } else if (Objects.equals(choice, "3")) {
                if (this.getMembership() == -1) {
                    System.out.println("Buy Membership First!!!!!");
                    continue;
                }
                if (this.getMembership() == 1) {
                    System.out.println("You have premium membership , You dont need tickets.....");
                    continue;
                } else {
                    buyTickets();
                }

            } else if (Objects.equals(choice, "4")) {
                System.out.println("View Discounts");
                if (Main.getMinor_discount() == 0 && Main.getSenior_discount() == 0 && Main.getStudent_discount() == 0) {
                    System.out.println("No Discounts Available!!!!!");
                    continue;
                } else {
                    if (Main.getMinor_discount() != 0) {
                        System.out
                                .println("Minor (" + Main.getMinor_discount() + "% ) discount: MINOR" + Main.getMinor_discount());
                    }
                    if (Main.getSenior_discount() != 0) {
                        System.out.println(
                                "Senior (" + Main.getSenior_discount() + "% ) discount: SENIOR" + Main.getSenior_discount());
                    }
                    if (Main.getStudent_discount() != 0) {
                        System.out.println(
                                "Student (" + Main.getStudent_discount() + "% ) discount: STUDENT" + Main.getStudent_discount());
                    }
                    for (Attractions attr : Main.Attractions_Record) {
                        if (attr.getDiscount() != 0) {
                            System.out.println(attr.getDiscount() + "%" + " Discount On Attraction " + attr.getName()
                                    + ": ATTRACTION" + attr.getDiscount());
                        }
                    }
                }

            } else if (Objects.equals(choice, "5")) {
                Main.viewDeals();
            } else if (Objects.equals(choice, "6")) {
                boolean res = printAnimals();
                if (res == false) {
                    continue;
                } else {
                    // animals are there
                    System.out.print("Enter Your Choice: ");
                    int option = input_visitor.nextInt();
                    input_visitor.nextLine();
                    visitAnimal(option);
                }

            } else if (Objects.equals(choice, "7")) {
                // check whether attraction is empty or not
                if (Main.Attractions_Record.isEmpty() == true) {
                    System.out.println("No Attraction to visit!!!!!");
                    continue;
                } else {
                    System.out.println("Visit Attractions");
                    int option = 1;
                    int counter = 1;
                    for (Attractions attr : Main.Attractions_Record) {
                        System.out.println(counter + ". " + attr.getName());
                        counter++;
                    }
                    System.out.println(counter + ". Back");
                    System.out.print("Enter Your Choice: ");
                    option = input_visitor.nextInt();
                    input_visitor.nextLine();
                    if (option == counter) {
                        continue;
                    } else {
                        handleVisitAttractions(option);
                    }
                }

            } else if (Objects.equals(choice, "8")) {
                System.out.println("Enter Your Feedback about the ZOO:-----");
                choice = input_visitor.nextLine();
                // this.feedback=choice;
                this.setFeedback(choice);

            } else if (Objects.equals(choice, "9")) {
                start_visitor_loop = false;
                //input_visitor.close();
                return;
            } else {
                System.out.println("Enter a Valid Option!!!!!");
                continue;
            }
        }
        //input_visitor.close();

    }

    public void exploreTheZoo() {
        Scanner input_exploreZoo = new Scanner(System.in);
        boolean start_exploreZooLoop = true;
        while (start_exploreZooLoop) {
            System.out.println("Explore The Zoo");
            System.out.println("1. View Attractions");
            System.out.println("2. View Animals");
            System.out.println("3. Back");
            System.out.print("Enter Your Choice: ");
            String choice = input_exploreZoo.nextLine();
            if (Objects.equals(choice, "1")) {
                // attraction here
                if (Main.Attractions_Record.isEmpty() == true) {
                    System.out.println("No Attraction to Visit......");
                    continue;
                } else {
                    for (Attractions attr : Main.Attractions_Record) {
                        System.out.println("-------------------");
                        System.out.println("Name of Attraction: " + attr.getName());
                        System.out.println("Description of Attraction: " + attr.getDescription());
                        if(attr.getSchedule()==0){
                            //closed
                            System.out.println("Attraction is Closed Now!!!!!");
                        }
                        else{
                            System.out.println("Attraction is Ope Now");
                            System.out.println("Buy Your Ticket Now!!!!!");
                        }
                        System.out.println("-------------------");
                    }
                }
            } else if (Objects.equals(choice, "2")) {
                // zoo animals here
                if (Main.Animals_Record.isEmpty() == true) {
                    System.out.println("No Animals to visit now!!!!!");
                    continue;
                } else {
                    for (Animal animal : Main.Animals_Record) {
                        System.out.println("-------------------");
                        System.out.println("Name of Animal: " + animal.getMy_name());
                        System.out.println("Info about Animal: " + animal.getDescription());
                        System.out.println("-------------------");
                    }
                }
            } else if (Objects.equals("3", choice)) {
                start_exploreZooLoop = false;
                //input_exploreZoo.close();
                break;
            } else {
                System.out.println("Not A Valid Option!!!!!");
                continue;
            }
        }
        //input_exploreZoo.close();

    }

    public void buyMembership() {
        System.out.println("Buy Membership:-");
        System.out.println("1. Basic Membership (₹20)");
        System.out.println("2. Preminum Membership (₹50)");
        String choice;
        Scanner input_buyMembership = new Scanner(System.in);
        choice = input_buyMembership.nextLine();
        if (Objects.equals(choice, "1")) {
            System.out.print("Apply Discount code (none/Code): ");
            choice = input_buyMembership.nextLine();
            if (Objects.equals(choice.toLowerCase(), "none")) {
                // proceed for balance check
                if (this.getBalance() >= 20) {
                    System.out.println("Basic Membership purchased successfully");
                    this.setBalance(this.getBalance() - 20);
                    this.setMembership(0);
                    System.out.println("Your balance is now :₹" + this.getBalance());
                    Main.setTotal_Revenue(Main.getTotal_Revenue()+20);
                } else {
                    System.out.println("Not enough Balanace!!!!!!");
                    //input_buyMembership.close();
                    return;
                }
            } else if (Objects.equals(choice.toLowerCase(), "senior" + Main.getSenior_discount())) {
                // proceed for balance check
                boolean res = validateDiscount(2);
                if (res == false) {
                    System.out.println("Can't Apply!!!!!");
                    //input_buyMembership.close();
                    return;
                }
                double new_price = 20 - (((Main.getSenior_discount()) / 100) * 20);
                if (this.getBalance() >= new_price) {
                    System.out.println("Basic Membership purchased successfully");
                    this.setBalance(this.getBalance() - new_price);
                    this.setMembership(0);
                    System.out.println("Your balance is now :₹" + this.getBalance());
                    Main.setTotal_Revenue(Main.getTotal_Revenue()+new_price);
                } else {
                    System.out.println("Not enough Balanace!!!!!!");
                    //input_buyMembership.close();
                    return;
                }
            } else if (Objects.equals(choice.toLowerCase(), "minor" + Main.getMinor_discount())) {
                // proceed for balance check
                boolean res = validateDiscount(0);
                if (res == false) {
                    System.out.println("Can't Apply!!!!!");
                    //input_buyMembership.close();
                    return;
                }
                double new_price = 20 - (((Main.getMinor_discount()) / 100) * 20);
                if (this.getBalance() >= new_price) {
                    System.out.println("Basic Membership purchased successfully");
                    this.setBalance(this.getBalance() - new_price);
                    this.setMembership(0);
                    System.out.println("Your balance is now :₹" + this.getBalance());
                    Main.setTotal_Revenue(Main.getTotal_Revenue()+new_price);
                } else {
                    System.out.println("Not enough Balanace!!!!!!");
                    //input_buyMembership.close();
                    return;
                }
            } else if (Objects.equals(choice.toLowerCase(), "student" + Main.getStudent_discount())) {
                // proceed for balance check
                boolean res = validateDiscount(1);
                if (res == false) {
                    System.out.println("Can't Apply!!!!!");
                    //input_buyMembership.close();
                    return;
                }
                double new_price = 20 - (((Main.getStudent_discount()) / 100) * 20);
                if (this.getBalance() >= new_price) {
                    System.out.println("Basic Membership purchased successfully");
                    this.setBalance(this.getBalance() - new_price);
                    this.setMembership(0);
                    System.out.println("Your balance is now :₹" + this.getBalance());
                    Main.setTotal_Revenue(Main.getTotal_Revenue()+new_price);
                } else {
                    System.out.println("Not enough Balanace!!!!!!");
                   // input_buyMembership.close();
                    return;
                }
            } else {
                System.out.println("Wrong info provided!!!!!");
                //input_buyMembership.close();
                return;
            }
        } else if (Objects.equals(choice, "2")) {
            System.out.print("Apply Discount code (none/Code): ");
            choice = input_buyMembership.nextLine();
            if (Objects.equals(choice.toLowerCase(), "none")) {
                // proceed for balance check
                if (this.getBalance() >= 50) {
                    System.out.println("Preminum Membership purchased successfully");
                    this.setBalance(this.getBalance() - 50);
                    this.setMembership(1);
                    System.out.println("Your balance is now :₹" + this.getBalance());
                    Main.setTotal_Revenue(Main.getTotal_Revenue()+50);
                } else {
                    System.out.println("Not enough Balanace!!!!!!");
                   // input_buyMembership.close();
                    return;
                }
            } else if (Objects.equals(choice.toLowerCase(), "senior" + Main.getSenior_discount())) {
                // proceed for balance check
                boolean res = validateDiscount(2);
                if (res == false) {
                   // input_buyMembership.close();
                    return;
                }
                double new_price = 50 - (((Main.getSenior_discount()) / 100) * 50);
                if (this.getBalance() >= new_price) {
                    System.out.println("Premium Membership purchased successfully");
                    this.setBalance(this.getBalance() - new_price);
                    this.setMembership(1);
                    System.out.println("Your balance is now :₹" + this.getBalance());
                    Main.setTotal_Revenue(Main.getTotal_Revenue()+new_price);
                } else {
                    System.out.println("Not enough Balanace!!!!!!");
                   // input_buyMembership.close();
                    return;
                }
            } else if (Objects.equals(choice.toLowerCase(), "minor" + Main.getMinor_discount())) {
                // proceed for balance check
                boolean res = validateDiscount(0);
                if (res == false) {
                   // input_buyMembership.close();
                    return;
                }
                double new_price = 50 - (((Main.getMinor_discount()) / 100) * 50);
                if (this.getBalance() >= new_price) {
                    System.out.println("Preminum Membership purchased successfully");
                    this.setBalance(this.getBalance() - new_price);
                    this.setMembership(1);
                    System.out.println("Your balance is now :₹" + this.getBalance());
                    Main.setTotal_Revenue(Main.getTotal_Revenue()+new_price);
                } else {
                    System.out.println("Not enough Balanace!!!!!!");
                   // input_buyMembership.close();
                    return;
                }
            } else if (Objects.equals(choice.toLowerCase(), "student" + Main.getStudent_discount())) {
                // proceed for balance check
                boolean res = validateDiscount(1);
                if (res == false) {
                  //  input_buyMembership.close();
                    return;
                }
                double new_price = 50 - (((Main.getStudent_discount()) / 100) * 50);
                if (this.getBalance() >= new_price) {
                    System.out.println("Premium Membership purchased successfully");
                    this.setBalance(this.getBalance() - new_price);
                    this.setMembership(1);
                    System.out.println("Your balance is now :₹" + this.getBalance());
                    Main.setTotal_Revenue(Main.getTotal_Revenue()+new_price);
                } else {
                    System.out.println("Not enough Balanace!!!!!!");
                    //input_buyMembership.close();
                    return;
                }
            } else {
                System.out.println("Wrong info provided!!!!!");
                //input_buyMembership.close();
                return;
            }
        } else {
            System.out.println("Not A valid Option!!!!!");
           // input_buyMembership.close();
            return;
        }
        //input_buyMembership.close();

    }

    public boolean validateDiscount(int discount) {
        if (discount == 0) {
            // minor
            if (this.getAge() < 18) {
                return true;
            } else {
                return false;
            }
        } else if (discount == 1) {
            // student
            if (this.getAge() < 18 && this.getAge() >= 6) {
                return true;
            } else {
                return false;
            }
        } else if (discount == 2) {
            // senior
            if (this.getAge() > 60) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void buyTickets() {
        Scanner input_buyTickets = new Scanner(System.in);
        int number_of_tickets = 0;
        System.out.println("Buy Tickets");
        System.out.print("Enter Number of Tickets: ");
        number_of_tickets = input_buyTickets.nextInt();
        input_buyTickets.nextLine();
        if(number_of_tickets<=0){
            System.out.println("Wrong Info for Tickets!!!!!");
            //input_buyTickets.close();
            return;
        }
        if (Main.Attractions_Record.isEmpty() == true) {
            System.out.println("No Attraction to Visit!!!!!");
            //input_buyTickets.close();
            return;
        }
        System.out.println("Select a Attraction to buy a Ticket");
        int counter = 1;
        for (Attractions attr : Main.Attractions_Record) {
            System.out.println(counter + ". " + attr.getName() + " (₹" + attr.getPrice() + ")");
            counter++;
        }
        System.out.println();
        System.out.print("Enter your choice: ");
        int ch = input_buyTickets.nextInt();
        boolean res = validateIndex(ch);
        input_buyTickets.nextLine();
        if(res==false){
            System.out.println("Wrong Info Provided!!!!!");
            //input_buyTickets.close();
            return;
        }
        if(Main.Attractions_Record.get(ch-1).getSchedule()==0){
            //closed now
            System.out.println(Main.Attractions_Record.get(ch-1).getName()+" is Closed Now !!!!!");
            return;
        }
        double Total_Price = (number_of_tickets) * (Main.Attractions_Record.get(ch - 1).getPrice());
        if(number_of_tickets==1){
            //only coupan codes are valid
            double discount_coupan = applyDiscount(number_of_tickets, ch-1);
            if(discount_coupan==-1){
                discount_coupan=0;
            }
            Total_Price=Total_Price-discount_coupan;
            boolean isValid=checkBalance(Total_Price);
            if(isValid==false){
                System.out.println("Not Enough Balance!!!!!!");
               // input_buyTickets.close();
                return;
            }
            Attractions attr = Main.Attractions_Record.get(ch-1);
            this.attraction_map.put(attr, number_of_tickets);
            Main.setTotal_Revenue(Main.getTotal_Revenue()+Total_Price);
            System.out.println("Ticket Purchased Successfully.....");
            this.setBalance(this.getBalance()-Total_Price);
            System.out.println("Your Balance is now: "+this.getBalance());
        }
        else{
            //coupan + deals
            double discount_coupan = applyDiscount(number_of_tickets, ch-1)*Total_Price;
            if(discount_coupan==-1){
                discount_coupan=0;
            }
            double after_discount_coupan = Total_Price-discount_coupan;
            //now deal 
            double net_price = applyDeals(after_discount_coupan, number_of_tickets);
            boolean isValid = checkBalance(net_price);
            if(isValid==false){
                System.out.println("Not Enough Balance!!!!!");
                //input_buyTickets.close();
                return;
            }
            Attractions attr = Main.Attractions_Record.get(ch-1);
            this.attraction_map.put(attr,number_of_tickets);
            Main.setTotal_Revenue(Main.getTotal_Revenue()+net_price);
            System.out.println("Ticket Purchased Successfully.....");
            this.setBalance(this.getBalance()-net_price);
            System.out.println("Your Balance is now: "+this.getBalance());
        }
        //input_buyTickets.close();
    }

    public boolean printAnimals() {
        if (Main.Animals_Record.isEmpty() == true) {
            System.out.println("No Animals to Visit!!!!!");
            return false;
        } else {
            int counter = 1;
            System.out.println("Visit Animals");
            for (Animal animal : Main.Animals_Record) {
                System.out.println(counter + ". " + animal.getMy_name());
                counter++;
            }
            return true;
        }
    }

    public void visitAnimal(int option) {
        Scanner input_visitAnimal = new Scanner(System.in);
        int ind = option - 1;
        Animal animal = Main.Animals_Record.get(ind);
        System.out.println("1. Feed " + animal.getMy_name());
        System.out.println("2. Read about " + animal.getMy_name());
        String chString = input_visitAnimal.nextLine();
        if (Objects.equals("1", chString)) {
            // feed here
            if (animal instanceof Mammals) {
                ((Mammals) animal).feed();
            } else if (animal instanceof Mammals) {
                ((Reptiles) animal).feed();
            } else if (animal instanceof Mammals) {
                ((Amphibians) animal).feed();
            } else {
                System.out.println("Invalid !!!!!");
            }
        } else if (Objects.equals("2", chString)) {
            // feed here
            if (animal instanceof Mammals) {
                ((Mammals) animal).read();
                ;
            } else if (animal instanceof Mammals) {
                ((Reptiles) animal).read();
                ;
            } else if (animal instanceof Mammals) {
                ((Amphibians) animal).read();
                ;
            } else {
                System.out.println("Invalid !!!!!");
            }
        } else {
            System.out.println("Invalid!!!!!");
           // input_visitAnimal.close();
            return;
        }
       // input_visitAnimal.close();

    }

    public void handleVisitAttractions(int option) {
        int ind = option - 1;
        Attractions attr = Main.Attractions_Record.get(ind);
        if(attr.getSchedule()==0){
            System.out.println("Attraction Not Open Yet!!!!!");
            return;
        }
        if (this.getMembership() == -1) {
            System.out.println("Buy Membership First!!!!!");
            return;
        }
        // check for basic and preminum
        if (this.getMembership() == 1) {
            // preminum here
            System.out.println("Welcome to " + attr.getName());
            System.out.println("Thanks For Visiting Us.....");
            Main.Attractions_Record.get(ind).setNumber_of_visitors(attr.getNumber_of_visitors() + 1);
        } else {
            // basic here
            // check for ticket
            HashMap<Attractions, Integer> attr_map = this.getAttraction_map();
            Attractions foundAttraction = null;
            boolean flag = false;
            Integer ticket = -1;
            for (Attractions attraction : attr_map.keySet()) {
                ticket = attr_map.get(attraction);
                if (attr.getID() == attraction.getID()) {
                    // match found
                    flag = true;
                    System.out.println("Welcome to " + attr.getName());
                    System.out.println("Thanks For Visiting Us.....");
                    System.out.println("1 Ticket Used");
                    Main.Attractions_Record.get(ind).setNumber_of_visitors(attr.getNumber_of_visitors() + 1);
                    foundAttraction = attr;
                    break;
                }
            }
            if (flag == true) {
                if (ticket - 1 == 0) {
                    this.getAttraction_map().remove(foundAttraction);
                } else {
                    this.attraction_map.replace(foundAttraction, ticket - 1);
                }
            } else {
                System.out.println("Purchase Ticket First!!!!!");
                return;
            }
        }

    }

    public double applyDeals(double price,int num_of_tickets){
        //return total costs after apling deal
        if(num_of_tickets==2){
            //apply 15 deal
            if(Main.getDeal_2_attractions_15()!=0){
                //apply it over price
                double discount=0.15*price;
                double new_price=price-discount;
                System.out.println("Special Deal Applied Successfully:  Buy 2 tickets and get 15% off");
                return new_price;
            }
            else{
                return price;
            }

        }
        else if(num_of_tickets>2){
            //apply 30 deal
            if(Main.getDeal_3_attractions_30()!=0){
                //apply it over price
                double discount = 0.3*price;
                double new_price=price-discount;
                System.out.println("Special Deal Applied Successfully: Buy 3 tickets and get 30% off");
                return new_price;
            }
            else{
                return price;
            }
        }
        else{
            //error
            return price;
        }
    }

    public boolean validateIndex(int choice){
        int ind = choice-1;
        if(ind<0){
            return false;
        }
        else{
            if(Main.Attractions_Record.size()>ind){
                return true;
            }
            else{
                return false;
            }
        }
    }
    
    public double applyDiscount(int num_of_tickets,int ind){
        String choice;
        Scanner input_applyDiscount = new Scanner(System.in);
        Attractions attr = Main.Attractions_Record.get(ind);
        System.out.print("Apply Discounts (none/code): ");
        choice = input_applyDiscount.nextLine();
        if(Objects.equals(choice.toLowerCase(),"minor"+Main.getMinor_discount())){
            //check for minor 
            boolean res = validateDiscount(0);
            if(res==false){
                System.out.println("Can't Apply Coupan Code!!!!!");
                //input_applyDiscount.close();
                return -1;
            }
            double discount = (Main.getMinor_discount()/100);
            //input_applyDiscount.close();
            return discount;
        }
        else if(Objects.equals(choice.toLowerCase(),"senior"+Main.getSenior_discount())){
            //check for senior
            boolean res = validateDiscount(2);
            if(res==false){
                System.out.println("Can't Apply Discount Code!!!!!");
                //input_applyDiscount.close();
                return -1;
            }
            double discount = (Main.getSenior_discount()/100);
           // input_applyDiscount.close();
            return discount;
        }
        else if(Objects.equals(choice.toLowerCase(),"student"+Main.getStudent_discount())){
            //check for student
            boolean res = validateDiscount(1);
            if(res==false){
                System.out.println("Can't Apply Discount Code!!!!!");
                //input_applyDiscount.close();
                return -1;
            }
            double discount = (Main.getStudent_discount()/100);
            //input_applyDiscount.close();
            return discount;
        }
        else if(Objects.equals(choice.toLowerCase(),"none")){
           // input_applyDiscount.close();
            return -1;
        }
        else if(Objects.equals(choice.toLowerCase(),attr.getName()+attr.getDiscount())){
            double discount = (attr.getDiscount());
            if(discount==0){
               // input_applyDiscount.close();
                return -1;
            }
            else{
               // input_applyDiscount.close();
                return discount;
            }
        }
        else{
            System.out.println("Incorrect Code , Please See View Discounts Section for Correct Code!!!!!");
          //  input_applyDiscount.close();
            return -1;
        }

    }

    public boolean checkBalance(double price){
        if(this.getBalance()>=price){
            return true;
        }
        else{
            return false;
        }
    }
}
