public class Zombie extends Monster{
    public Zombie(String mClass, double strength, double health, boolean hostile, String name, String ability, double ability_damage){
        super(mClass, strength, health, hostile, name, ability, ability_damage);}
        /* constructor that supers monsters instance variables i should make 
        ability_damage an instance variable but thats more work than i want to do */
    public double getAbility_damage(){
        return ability_damage;}
    public String toString(){
        return super.toString() + "\nAbility Damage: " + ability_damage;}}
        // toString method