package AP_A2;

public class Attractions implements Comparable<Attractions> {
    private int schedule; // 1 for open ; 0 for closed
    private int ID;
    private String name;
    private String description;
    private int price;
    private int discount;
    private int number_of_visitors;

    public int getNumber_of_visitors() {
        return number_of_visitors;
    }

    public void setNumber_of_visitors(int number_of_visitors) {
        this.number_of_visitors = number_of_visitors;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public void setID(int iD) {
        this.ID = iD;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Attractions(String name, String des, int price) {
        setID(Admin.getAttraction_ID());
        Admin.setAttraction_ID(Admin.getAttraction_ID());
        setName(name);
        setDescription(des);
        setPrice(price);
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    @Override
    public int compareTo(Attractions attr) {
        return Integer.compare(this.number_of_visitors, attr.number_of_visitors);
    }

}