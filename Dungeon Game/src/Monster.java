public class Monster{
    public double stamina;
    public double health;
    public boolean hostile;
    public String name;
    public String ability;
    public String mClass;
    public double ability_damage;
    // Variables.
    public Monster(String mClass, double stamina, double health, boolean hostile, String name, String ability, double ability_damage){
        this.mClass = mClass;
        this.stamina = stamina;
        this.health = health;
        this.hostile = hostile;
        this.name = name;
        this.ability = ability;
        this.ability_damage = ability_damage;}
    // Constructor
    public double getHealth(){
        return health;}
    public String getName(){
        return name;}
    public void setHealth(double health){
        this.health = health;}
    public void setStamina(double stamina){
        this.stamina = stamina;}
    public double getAbilityDamage(){
        return ability_damage;}
    public double getStamina(){
        return stamina;}
    // Getters and setters
    @Override
    public String toString(){
        return "Entity type: " + name + "\nClass: " + mClass + "\nStamina: " + stamina + "\nHealth: " + health + "\n" +
                (hostile ? "It is Hostile" : "It is Not Hostile") + "\nAbility: " + ability;}}
    // tostering