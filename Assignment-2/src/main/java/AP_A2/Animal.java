package AP_A2;

public abstract class Animal {
    private static int Animal_Counter=0;
    private String my_name;
    public String getMy_name() {
        return my_name;
    }
    public void setMy_name(String my_name) {
        this.my_name = my_name;
    }
    public static int getAnimal_Counter() {
        return Animal_Counter;
    }
    public static void setAnimal_Counter(int animal_Counter) {
        Animal_Counter = animal_Counter;
    }
     private String sound;
    public String getSound() {
        return sound;
    }
    public void setSound(String sound) {
        this.sound = sound;
    }
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Animal(String name,String sound,String des){
        setMy_name(name);
        setDescription(des);
        setSound(sound);
    }
    public abstract void feed();
    public abstract void read();

}

class Mammals extends Animal{
    private static int my_type_counter;
    public static void setMy_type_counter(int my_type_counter) {
        Mammals.my_type_counter = my_type_counter;
    }
    public int getMy_type_counter() {
        return my_type_counter;
    }
    private Attractions specific_attraction;
    public Attractions getSpecific_attraction() {
        return specific_attraction;
    }
    public void setSpecific_attraction(Attractions specific_attraction) {
        this.specific_attraction = specific_attraction;
    }
    public void  feed(){
        String res=getSound();
        System.out.println(res);
    }
    public void read(){
        String res=getDescription();
        System.out.println(res);
    }

    public Mammals(String name,String sound,String des){
        super(name, sound, des);
        setSpecific_attraction(null);
    }
    public Mammals(String name,String sound,String des,Attractions attr){
        super(name, sound, des);
        setSpecific_attraction(attr);
    }
}



class Reptiles extends Animal{
    private static int my_type_counter;
    public static void setMy_type_counter(int my_type_counter) {
        Reptiles.my_type_counter = my_type_counter;
    }
    public int getMy_type_counter() {
        return my_type_counter;
    }
    private Attractions specific_attraction;
    public Attractions getSpecific_attraction() {
        return specific_attraction;
    }
    public void setSpecific_attraction(Attractions specific_attraction) {
        this.specific_attraction = specific_attraction;
    }
    public void  feed(){
        String res=getSound();
        System.out.println(res);
    }
    public void read(){
        String res=getDescription();
        System.out.println(res);
    }

    public Reptiles(String name,String sound,String des){
        super(name, sound, des);
        setSpecific_attraction(null);
    }
    public Reptiles(String name,String sound,String des,Attractions attr){
        super(name, sound, des);
        setSpecific_attraction(attr);
    }
}



class Amphibians extends Animal{
    private static int my_type_counter;
    public static void setMy_type_counter(int my_type_counter) {
        Amphibians.my_type_counter = my_type_counter;
    }
    public int getMy_type_counter() {
        return my_type_counter;
    }
    private Attractions specific_attraction;
    public Attractions getSpecific_attraction() {
        return specific_attraction;
    }
    public void setSpecific_attraction(Attractions specific_attraction) {
        this.specific_attraction = specific_attraction;
    }
    public void  feed(){
        String res=getSound();
        System.out.println(res);
    }
    public void read(){
        String res=getDescription();
        System.out.println(res);
    }

    public Amphibians(String name,String sound,String des){
        super(name, sound, des);
        setSpecific_attraction(null);
    }
    public Amphibians(String name,String sound,String des,Attractions attr){
        super(name, sound, des);
        setSpecific_attraction(attr);
    }
}
