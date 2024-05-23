public class Dragon extends Monster{
    private double flight_time;
    private String breath;
    public Dragon(String mClass, double strength, double health, 
    boolean hostile, String name, String ability, double ability_damage,
    double flight_time, String breath){
        super(mClass, strength, health, hostile, name, ability, ability_damage);
        this.flight_time = flight_time;
        this.breath = breath;}
    //parameter constructor
    public double getAbility_damage(){
        return ability_damage;}
    public double getFlight_time(){
        return flight_time;}
    public String getBreath(){
        return breath;}
    //getters that will be used to determine flight and so i can compare entities
    public String toString(){
        return super.toString() + "\nAbility Damage: " + ability_damage + 
        "\nFlight Time: " + flight_time + " Minutes" +
        "\nBreath Type: " + breath;}}
    /* yet another toString this time it is modified so that i can add characteristics of the dragon
    since they are not found in other classes */