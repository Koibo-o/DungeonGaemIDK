public class Knight extends Monster{
    public Knight(String mClass, double stamina, double health, boolean hostile, String name, String ability, double ability_damage){
        super(mClass, stamina, health, hostile, name, ability, ability_damage);}
    public double getAbility_damage(){
        return ability_damage;}
    public double getHealth(){
        return super.getHealth();}
    //constructor and getter
    public String toString(){
        return super.toString() + "\nAbility Damage: " + ability_damage;}}
    //toString that is optained from the Monster class