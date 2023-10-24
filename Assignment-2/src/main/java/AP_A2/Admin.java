package AP_A2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Collections;

public class Admin implements Discountable {
    // final so to provide protection
    private static final String username = "admin";
    private static final String password = "admin123";
    private boolean isValid;
    private static int Attraction_ID = 1; // this is just a counter to give unique ID's

    public static int getAttraction_ID() {
        return Attraction_ID;
    }

    public static void setAttraction_ID(int attraction_ID) {
        Attraction_ID = attraction_ID + 1;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public Admin(boolean isValid) {
        setValid(isValid);
    }

    public void startAdminLoop() {
        System.out.println("\nAdmin Menu");
        boolean start_admin_loop = true;
        Scanner input_admin = new Scanner(System.in);
        String choice;
        while (start_admin_loop) {
            System.out.println("\n1. Manage Attractions");
            System.out.println("2. Manage Animals");
            System.out.println("3. Schedule Events");
            System.out.println("4. Set Discounts");
            System.out.println("5. Set Special Deal");
            System.out.println("6. View Visitor Stats");
            System.out.println("7. View Feedback");
            System.out.println("8. Exit");
            System.out.print("Enter Your Choice: ");
            choice = input_admin.nextLine();
            if (Objects.equals("1", choice)) {
                manageAttractions();
            } else if (Objects.equals("2", choice)) {
                manageAnimals();

            } else if (Objects.equals("3", choice)) {
                System.out.print("Enter the ID of Attraction :");
                int id = input_admin.nextInt();
                input_admin.nextLine();
                if (Main.Attractions_Record.isEmpty() == true) {
                    System.out.println("Attractions are currently empty!!!!!");
                } else {
                    boolean flag = false;
                    for (Attractions attr : Main.Attractions_Record) {
                        if (attr.getID() == id) {
                            flag = true;
                            System.out.print("Change opening and closing (Opening:1) (Closing:0): ");
                            choice = input_admin.nextLine();
                            if (Objects.equals(choice, "1")) {
                                attr.setSchedule(1);
                                System.out.println("Successfully set.....");
                            } else {
                                attr.setSchedule(0);
                                System.out.println("Successfully set.....");
                            }
                            System.out.print("New Price: ");
                            int p = input_admin.nextInt();
                            input_admin.nextLine();
                            attr.setPrice(p);
                            System.out.println("Successfully set.....");
                            break;
                        }
                    }
                    if (flag == false) {
                        System.out.println("Not Found!!!!!");
                    }

                }

            } else if (Objects.equals("4", choice)) {
                applyDiscount();

            } else if (Objects.equals("5", choice)) {
                setSpecialDeals();

            } else if (Objects.equals("6", choice)) {
                showStats();

            } else if (Objects.equals("7", choice)) {
                if (Main.Visitor_Record.isEmpty() == true) {
                    System.out.println("No Visitor Yet!!!!!");
                } else {
                    for (Visitor visitor : Main.Visitor_Record) {
                        System.out.println("Visitor Name: " + visitor.getName());
                        if (visitor.getFeedback().isEmpty() == true) {
                            System.out.println("No feedback given");
                        } else {
                            System.out.println("Feedback :" + visitor.getFeedback());
                        }
                    }
                }

            } else if (Objects.equals("8", choice)) {
                start_admin_loop = false;
                break;
            } else {
                System.out.println("Please Enter a valid option!!!!!");
                continue;
            }
        }

        //input_admin.close();
    }

    public void manageAttractions() {
        Scanner input_attractions = new Scanner(System.in);
        String choice;
        System.out.println("Manage Attractions");
        System.out.println("\n1. Add Attraction");
        System.out.println("2. View Attraction");
        System.out.println("3. Modify Attraction");
        System.out.println("4. Remove Attraction");
        System.out.println("5. Back");
        System.out.print("Enter Your Choice: ");
        choice = input_attractions.nextLine();
        if (Objects.equals("1", choice)) {
            String name;
            String des;
            int price;
            System.out.print("Enter the Name: ");
            name = input_attractions.nextLine();
            System.out.print("Enter the Description: ");
            des = input_attractions.nextLine();
            System.out.print("Enter the Price: ");
            price = input_attractions.nextInt();
            input_attractions.nextLine();
            // add check for duplicate
            Attractions attr = new Attractions(name, des, price);
            attr.setSchedule(1);
            attr.setDiscount(0);
            attr.setNumber_of_visitors(0);
            Main.Attractions_Record.add(attr);
            System.out.println("Added successfully.....");
        } else if (Objects.equals("2", choice)) {
            if (Main.Attractions_Record.isEmpty() == true) {
                System.out.println("Attractions are currently empty!!!!!");
            } else {
                for (Attractions attr : Main.Attractions_Record) {
                    System.out.println("--------------------");
                    System.out.println("Unique ID: " + attr.getID());
                    System.out.println("Name: " + attr.getName());
                    System.out.println("Description: " + attr.getDescription());
                    System.out.println("Price :" + attr.getPrice());
                    System.out.println("Number of Visitors: " + attr.getNumber_of_visitors());
                    if (attr.getSchedule() == 0) {
                        System.out.println("Status: Closed");
                    } else {
                        System.out.println("Status: Open");
                    }
                    System.out.println("--------------------");
                }
            }

        } else if (Objects.equals("3", choice)) {
            System.out.print("Enter the ID for modification: ");
            int ch = input_attractions.nextInt();
            input_attractions.nextLine();
            if (Main.Attractions_Record.isEmpty() == true) {
                System.out.println("Attractions are currently empty!!!!!");
            } else {
                boolean flag = false;
                for (Attractions attr : Main.Attractions_Record) {
                    if (attr.getID() == ch) {
                        flag = true;
                        System.out.print("Which field you want to change: (name/description/price) :");
                        choice = input_attractions.nextLine();
                        if (Objects.equals(choice, "name")) {
                            // name
                            System.out.print("Enter new Name: ");
                            choice = input_attractions.nextLine();
                            attr.setName(choice);
                        } else if (Objects.equals(choice, "description")) {
                            // description
                            System.out.print("Enter new Description: ");
                            choice = input_attractions.nextLine();
                            attr.setDescription(choice);
                        } else if (Objects.equals(choice, "price")) {
                            // price
                            System.out.print("Enter new price: ");
                            int p = input_attractions.nextInt();
                            input_attractions.nextLine();
                            attr.setID(p);
                        } else {
                            System.out.println("Wrong Format!!!!!");
                        }
                    }
                }
                if (flag == false) {
                    System.out.println("Not Found!!!!!");
                }

            }
        } else if (Objects.equals("4", choice)) {
            System.out.print("Enter the ID for deletion: ");
            int id = input_attractions.nextInt();
            input_attractions.nextLine();
            Main.Attractions_Record.removeIf(obj -> obj.getID() == id);
            System.out.println("Deletion Success.....");

        } else if (Objects.equals("5", choice)) {
            //input_attractions.close();
            return;
        } else {
            System.out.println("Please Enter a valid option!!!!!");
            //input_attractions.close();
            return;
        }
        //input_attractions.close();
    }

    public void applyDiscount() {
        Scanner input_applyDiscount = new Scanner(System.in);
        String choice;
        System.out.println("1. Add Discount ");
        System.out.println("2. Modify Discount ");
        System.out.println("3. Remove Discount ");
        System.out.println("4. Back");
        System.out.print("Enter Your Choice: ");
        choice = input_applyDiscount.nextLine();
        if (Objects.equals("1", choice)) {
            System.out.print("Enter Discount category (minors,seniors,students,attractions) :");
            choice = input_applyDiscount.nextLine();
            if (Objects.equals("minors", choice.toLowerCase())) {
                System.out.print("Enter the Discount as a number: ");
                int dis = input_applyDiscount.nextInt();
                input_applyDiscount.nextLine();
                Main.setMinor_discount(dis);
                System.out.println("Success.....");
            } else if (Objects.equals("seniors", choice.toLowerCase())) {
                System.out.print("Enter the Discount as a number: ");
                int dis = input_applyDiscount.nextInt();
                input_applyDiscount.nextLine();
                Main.setSenior_discount(dis);
                System.out.println("Success.....");
            } else if (Objects.equals("students", choice.toLowerCase())) {
                System.out.print("Enter the Discount as a number: ");
                int dis = input_applyDiscount.nextInt();
                input_applyDiscount.nextLine();
                Main.setStudent_discount(dis);
                System.out.println("Success.....");
            } else if (Objects.equals("attractions", choice.toLowerCase())) {
                int id;
                System.out.print("Enter the ID of Attraction: ");
                id = input_applyDiscount.nextInt();
                input_applyDiscount.nextLine();
                for (Attractions attr : Main.Attractions_Record) {
                    if (attr.getID() == id) {
                        System.out.print("Enter the Discount :");
                        id = input_applyDiscount.nextInt();
                        input_applyDiscount.nextLine();
                        attr.setDiscount(id);
                        System.out.println("Success.....");
                        //input_applyDiscount.close();
                        return;
                    }
                }
                System.out.println("Not Found!!!!!");
            } else {
                System.out.println("Invalid!!!!!");
            }
        } else if (Objects.equals("2", choice)) {
            System.out.print("Enter Discount category (minors,seniors,students,attractions) :");
            choice = input_applyDiscount.nextLine();
            if (Objects.equals("minors", choice.toLowerCase())) {
                System.out.print("Enter the Discount as a number: ");
                int dis = input_applyDiscount.nextInt();
                input_applyDiscount.nextLine();
                Main.setMinor_discount(dis);
                System.out.println("Success.....");
            } else if (Objects.equals("seniors", choice.toLowerCase())) {
                System.out.print("Enter the Discount as a number: ");
                int dis = input_applyDiscount.nextInt();
                input_applyDiscount.nextLine();
                Main.setSenior_discount(dis);
                System.out.println("Success.....");
            } else if (Objects.equals("students", choice.toLowerCase())) {
                System.out.print("Enter the Discount as a number: ");
                int dis = input_applyDiscount.nextInt();
                input_applyDiscount.nextLine();
                Main.setStudent_discount(dis);
                System.out.println("Success.....");

            } else if (Objects.equals("attractions", choice.toLowerCase())) {
                int id;
                System.out.print("Enter the ID of Attraction: ");
                id = input_applyDiscount.nextInt();
                input_applyDiscount.nextLine();
                for (Attractions attr : Main.Attractions_Record) {
                    if (attr.getID() == id) {
                        System.out.print("Enter the Discount :");
                        id = input_applyDiscount.nextInt();
                        input_applyDiscount.nextLine();
                        attr.setDiscount(id);
                        System.out.println("Success.....");
                        //input_applyDiscount.close();
                        return;
                    }
                }
                System.out.println("Not Found!!!!!");
            } else {
                System.out.println("Invalid!!!!!");
            }
        } else if (Objects.equals("3", choice)) {
            System.out.print("Enter Discount category (minors,seniors,students,attractions) :");
            choice = input_applyDiscount.nextLine();
            if (Objects.equals("minors", choice.toLowerCase())) {
                Main.setMinor_discount(0);
                System.out.println("Success.....");
            } else if (Objects.equals("seniors", choice.toLowerCase())) {
                Main.setSenior_discount(0);
                System.out.println("Success.....");
            } else if (Objects.equals("students", choice.toLowerCase())) {
                Main.setStudent_discount(0);
                System.out.println("Success.....");

            } else if (Objects.equals("attractions", choice.toLowerCase())) {
                int id;
                System.out.print("Enter the ID of Attraction: ");
                id = input_applyDiscount.nextInt();
                input_applyDiscount.nextLine();
                for (Attractions attr : Main.Attractions_Record) {
                    if (attr.getID() == id) {
                        attr.setDiscount(0);
                        System.out.println("Success.....");
                        //input_applyDiscount.close();
                        return;
                    }
                }
                System.out.println("Not Found!!!!!");
            } else {
                System.out.println("Invalid!!!!!");
            }
            // add overloaded method here

        } else if (Objects.equals("4", choice)) {
            //input_applyDiscount.close();
            return;
        } else {
            System.out.println("Not a Valid option!!!!!");
        }
        //input_applyDiscount.close();
    }

    public void showStats() {
        System.out.println("\n-----Some Stats related to ZOO-----");
        System.out.println("Number of visitors :" + Main.Visitor_Record.size());
        System.out.println("Revenue Generated: " + Main.getTotal_Revenue());
        List<Attractions> copy_of_attractions = new ArrayList<>(Main.Attractions_Record);
        Collections.sort(Main.Attractions_Record);
        Main.Attractions_Record = copy_of_attractions;
        Attractions most = copy_of_attractions.get(0);
        System.out.println("Popular Attraction: " + most.getName());
        System.out.println("Number of vistors in this: " + most.getNumber_of_visitors());
    }

    public void manageAnimals() {
        Scanner input_manageAnimals = new Scanner(System.in);
        System.out.println("\nManage Animals");
        System.out.println("1. Add Animal");
        System.out.println("2. Update Animal details");
        System.out.println("3. Remove Animal");
        System.out.println("4. Back");
        System.out.print("Enter Your Choice: ");
        String choice = input_manageAnimals.nextLine();
        if (Objects.equals("1", choice)) {
            String cat;
            System.out.print("Enter the category: (mammals,reptiles,amphibians):");
            cat = input_manageAnimals.nextLine();
            if (Objects.equals("mammals", cat.toLowerCase())) {
                addAnimal(1);
                if(Main.getMammals_Counter()<2){
                    System.out.println("Mammal Need to be Atleat 2!!!!");
                    System.out.println("Add More Mammal");
                    addAnimal(1);
                }
            } else if (Objects.equals("reptiles", cat.toLowerCase())) {
                addAnimal(2);
                if(Main.getReptiles_Counter()<2){
                    System.out.println("Reptiles Need to be Atleat 2!!!!");
                    System.out.println("Add More reptiles");
                    addAnimal(2);
                }
            } else if (Objects.equals("amphibians", cat.toLowerCase())) {
                addAnimal(3);
                if(Main.getAmphibians_Counter()<2){
                    System.out.println("Amphibians Need to be Atleat 2!!!!");
                    System.out.println("Add More Amphibians");
                    addAnimal(3);
                }
            } else {
                System.out.println("Not a Valid Option!!!!!");
                //input_manageAnimals.close();
            }
            //while loop for satisfying the property
            while(Main.getMammals_Counter()<2){
                System.out.println("Mammals Need to be Atleast 2");
                System.out.println("Add a Mammal!!!!!");
                addAnimal(1);
            }
            while(Main.getReptiles_Counter()<2){
                System.out.println("Reptiles Need to be Atleast 2");
                System.out.println("Add a Reptile!!!!!");
                addAnimal(2);
            }
            while(Main.getAmphibians_Counter()<2){
                System.out.println("Amphibians Need to be Atleast 2");
                System.out.println("Add a Amphibian!!!!!");
                addAnimal(3);
            }
        } else if (Objects.equals("2", choice)) {
            // update the animal here
            System.out.print("Enter The Name of Animal: ");
            choice = input_manageAnimals.nextLine();
            for (Animal animal : Main.Animals_Record) {
                if (Objects.equals(animal.getMy_name(), choice)) {
                    // perform updation
                    System.out.print("Enter New Description: ");
                    choice = input_manageAnimals.nextLine();
                    animal.setDescription(choice);
                    //input_manageAnimals.close();
                    return;
                }
            }
            System.out.println("Animal Not Found !!!!!");
            //input_manageAnimals.close();
            return;
        } else if (Objects.equals("3", choice)) {
            // remove animal
            System.out.print("Enter the Name of Animal:");
            choice=input_manageAnimals.nextLine();
            boolean isFound = false;
            int delete=0;
            for (Animal animal : Main.Animals_Record) {
                if(Objects.equals(animal.getMy_name(), choice)){
                    isFound=true;
                    if(animal instanceof Mammals){
                        //check for mammal counter
                        System.out.println("Deleted Successfully.....");
                        if(Main.getMammals_Counter()<2){
                            System.out.println("Mammals Need to be Atleast 2");
                            System.out.println("Add a Mammal!!!!!");
                            addAnimal(1);
                        }
                    }
                    else if(animal instanceof Reptiles){
                        //check for Reptile counter
                        System.out.println("Deleted Successfully.....");
                        if(Main.getReptiles_Counter()<2){
                            System.out.println("Reptiles Need to be Atleast 2");
                            System.out.println("Add a Reptile!!!!!");
                            addAnimal(2);
                        }
                    }
                    else if(animal instanceof Amphibians){
                        //check for Amphibian counter
                        System.out.println("Deleted Successfully.....");
                        if(Main.getAmphibians_Counter()<2){
                            System.out.println("Amphibians Need to be Atleast 2");
                            System.out.println("Add a Amphibian!!!!!");
                            addAnimal(3);
                        }
                    }
                    break;
                }
                delete++;
            }
            if(isFound==false){
                System.out.println("Animal Not Found!!!!");
            }
            else{
                Main.Animals_Record.remove(delete);
            }
        } else if (Objects.equals("4", choice)) {
            // back
            //input_manageAnimals.close();
            return;
        } else {
            System.out.println("Not A Valid Option!!!!!");
            //input_manageAnimals.close();
        }
        //input_manageAnimals.close();

    }

    public void setSpecialDeals() {
        System.out.println("Set Special Deals");
        Scanner input_setSpecialDeals = new Scanner(System.in);
        System.out.println("-------------------");
        System.out.println(
                "DEAL (1): If a person buys more than 2 tickets, they get a special discount of 15% on the total amount");
        System.out.print("Status: ");
        if (Main.getDeal_2_attractions_15() == 0) {
            System.out.print("Not Active\n");
        } else {
            System.out.print("Active\n");
        }
        System.out.println("-------------------");
        System.out.println();
        System.out.println("-------------------");
        System.out.println(
                "DEAL (2): If a person buys more than 3 tickets, they get a special discount of 30% on the total amount");
        System.out.print("Status: ");
        if (Main.getDeal_3_attractions_30() == 0) {
            System.out.print("Not Active\n");
        } else {
            System.out.print("Active\n");
        }
        System.out.println("-------------------");
        String choice;
        String option;
        System.out.print("Enter your choice of deal: ");
        choice = input_setSpecialDeals.nextLine();
        System.out.print("Enter whether you want to make deal active or not active (1-Active) (2-Not Active): ");
        option = input_setSpecialDeals.nextLine();
        if (Objects.equals(choice, "1")) {
            // 2 vali deal
            if (Objects.equals("1", option)) {
                Main.setDeal_2_attractions_15(1);
                System.out.println("Deal Active Now.....");
            } else if (Objects.equals("2", option)) {
                Main.setDeal_2_attractions_15(0);
                System.out.println("Deal Not Active Now.....");
            } else {
                System.out.println("Incorrect Option!!!!!");
            }
        } else if (Objects.equals(choice, "2")) {
            // 3 vali deal
            if (Objects.equals("1", option)) {
                Main.setDeal_3_attractions_30(1);
                System.out.println("Deal Active Now.....");
            } else if (Objects.equals("2", option)) {
                Main.setDeal_3_attractions_30(0);
                System.out.println("Deal Not Active Now.....");
            } else {
                System.out.println("Incorrect Option!!!!!");
            }
        } else {
            System.out.println("Incorrect Option!!!!!");
        }
       // input_setSpecialDeals.close();

    }

    public void addAnimal(int flag) {
        // 1 for mammal , 2 for reptile , 3 for amphibian
        String name;
        String description;
        String sound;
        Scanner input_addAnimal = new Scanner(System.in);
        if (flag == 1) {
            // mammal
            System.out.print("Enter the name: ");
            name = input_addAnimal.nextLine();
            System.out.print("Enter the sound made by animal: ");
            sound = input_addAnimal.nextLine();
            System.out.print("Enter the animal history/description: ");
            description = input_addAnimal.nextLine();
            Mammals new_Mammal = new Mammals(name, sound, description);
            Main.Animals_Record.add(new_Mammal);
            Main.setMammals_Counter(Main.getMammals_Counter()+1);

        } else if (flag == 2) {
            // reptile
            System.out.print("Enter the name: ");
            name = input_addAnimal.nextLine();
            System.out.print("Enter the sound made by animal: ");
            sound = input_addAnimal.nextLine();
            System.out.print("Enter the animal history/description: ");
            description = input_addAnimal.nextLine();
            Reptiles new_Mammal = new Reptiles(name, sound, description);
            Main.Animals_Record.add(new_Mammal);
            Main.setReptiles_Counter(Main.getReptiles_Counter() + 1);
        } else if (flag == 3) {
            // amphibian
            System.out.print("Enter the name: ");
            name = input_addAnimal.nextLine();
            System.out.print("Enter the sound made by animal: ");
            sound = input_addAnimal.nextLine();
            System.out.print("Enter the animal history/description: ");
            description = input_addAnimal.nextLine();
            Amphibians new_Mammal = new Amphibians(name, sound, description);
            Main.Animals_Record.add(new_Mammal);
            Main.setAmphibians_Counter(Main.getAmphibians_Counter()+1);
        } else {
            System.out.println("Not A valid Optino!!!!!");
        }
        //input_addAnimal.close();
    }
}
