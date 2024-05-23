public class Archer extends Knight{
    private String item_drop;
    public Archer(String mClass, double strength, double health, boolean hostile, String name, String ability, double ability_damage, String item_drop){
        super(mClass, strength, health, hostile, name, ability, ability_damage);
        this.item_drop = item_drop;}
    /* Archer constructor along with the item drop variable wich i will add to everything else
    later but for now my code is going to be a bit redundant */
    public String toString(){
        return super.toString() + "\nItem Dropped: " + item_drop;}}
        //more toString stuff