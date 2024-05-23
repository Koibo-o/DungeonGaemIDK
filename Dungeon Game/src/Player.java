public class Player{
    public double h;
    private double s;
    private String clas;
    private String n;
    private String a1;
    private String a2;
    private String sa1;
    private String sa2;
    // Variables.
    public Player(double h, double s, String clas, String n, String a1, String a2, String sa1, String sa2){
        this.h = h;
        this.s = s;
        this.clas = clas;
        this.n = n;
        this.a1 = a1;
        this.a2 = a2;
        this.sa1 = sa1;
        this.sa2 = sa2;}
    // Constructor
    public String getClas(){
        return clas;}
    public double getH(){
        return h;}
    public void setH(double h){
        this.h = h;}
    // Getters and setters.
    @Override
    public String toString(){
        return "Name: " + n + "\nClass: " + clas + "\nHealth: " + h + "\nStrength: " + s + "\nAbility 1: " + a1 + "\nAbility 2: " + a2
            + "\nSpecial 1: " + sa1 + "\nSpecial 2: " + sa2;}}
    // ToString.