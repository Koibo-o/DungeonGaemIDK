import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.Scanner;
// Imports?
public class Maind{
    private Random random = new Random();
    private boolean getRandomBoolean(){return random.nextBoolean();}
    private double getRandomDouble(double min, double max){return round(min + (max - min) * random.nextDouble(), 2);}
    private String getRandomString(String[] options){int index = random.nextInt(options.length);return options[index];}
    private double round(double value, int places){if (places < 0) throw new IllegalArgumentException();BigDecimal bd = BigDecimal.valueOf(value);bd = bd.setScale(places, RoundingMode.HALF_UP);return bd.doubleValue();}
    private Scanner scanner = new Scanner(System.in);
    // Magik
    public void run(){
        String[] knightAbilities = {"Slash", "Thrust", "Parry"};
        String[] archerAbilities = {"Shoot", "Snipe", "First Move"};
        String[] dragonAbilities = {"Rush", "Screech", "Wind Rush"};
        String[] dragonBreaths = {"Breath Fire", "Spit Lava", "Spew Acid"};
        String[] zombieAbilities = {"Third Life", "Decay", "Disassemble"};
        String[] monsterAbilities = {"Rush", "Intimidate", "Slam"};
        String[] entityTypes = {"Knight", "Archer", "Zombie", "Dragon", "Monster"};
        String entityType = getRandomString(entityTypes);
        Monster monster = null;
        // Entity Ability Selection
        switch(entityType){
            case "Knight":monster = new Knight("Normal", getRandomDouble(80, 120), getRandomDouble(150, 250), getRandomBoolean(), "Knight", getRandomString(knightAbilities), getRandomDouble(30, 40));break;
            case "Archer":monster = new Archer("Light", getRandomDouble(40, 80), getRandomDouble(100, 200), getRandomBoolean(), "Archer", getRandomString(archerAbilities), getRandomDouble(20, 30), "Arrow");break;
            case "Dragon":monster = new Dragon("Heavy", getRandomDouble(120, 160), getRandomDouble(250, 350), getRandomBoolean(), "Dragon", getRandomString(dragonAbilities), getRandomDouble(40, 50), 1.0, getRandomString(dragonBreaths));break;
            case "Zombie":monster = new Zombie("Normal", getRandomDouble(80, 120), getRandomDouble(150, 250), getRandomBoolean(), "Zombie", getRandomString(zombieAbilities), getRandomDouble(30, 40));break;
            case "Monster":monster = new Monster("Heavy", getRandomDouble(120, 160), getRandomDouble(250, 350), getRandomBoolean(), "Monster", getRandomString(monsterAbilities), getRandomDouble(40, 50));break;}
        encounterMonster(monster);}
        // Monster creation
    private void encounterMonster(Monster monster){
        System.out.print("\nWhat character class do you want (Normal | Light | Heavy) ");
        String cs = scanner.nextLine();
        System.out.print("\nWhat do you want to name your character? ");
        String cn = scanner.nextLine();
        Player player = null;
        switch(cs){
            case "Light":player = new Player(1000, 70, "Light", cn, "Shank", "Dodge", "Poison", "Invisibility");break;
            case "Normal":player = new Player(1000, 150, "Normal", cn, "Slash", "Stab", "Parry", "Boost");break;
            case "Heavy":player = new Player(1000, 350, "Heavy", cn, "Strike", "Slam", "Tank", "Challenge");break;}
        System.out.println(player + "\n");
        System.out.println("You will be fighting a " + monster.getName() + "\n");
        System.out.println(monster + "\n");
        entityFight(monster, player);}
    // Player Character selection and creation
    private void entityFight(Monster monster, Player player){
        boolean reduceDamage = false;
        boolean poisonEffect = false;
        int roundCount = 0;
        int poisonCount = 0;
        // Effect Checking
        while(monster.getHealth() > 0 && player.getH() > 0){
            roundCount += 1;
            System.out.print("What is your move: ");
            String pm = scanner.nextLine();
            double damageDealt = 0;
            damageDealt = round(damageDealt, 2);
            double monsterDamage = 0;
            monsterDamage = round(monsterDamage, 2);
            String characterType = player.getClas();
            switch(characterType){
                case "Light":damageDealt = handleLightMoves(pm, monster, player);if(pm.equals("Dodge")){reduceDamage = true;}else if(pm.equals("Poison")){poisonEffect = true;}break;
                case "Normal":damageDealt = handleNormalMoves(pm, monster, player, monsterDamage);if(pm.equals("Parry")){reduceDamage = true;}break;
                case "Heavy":damageDealt = handleHeavyMoves(pm, monster, player);if(pm.equals("Tank")){reduceDamage = true;}break;}
            // Special Move Handler
            if(poisonEffect){
                double poisonDamage = getRandomDouble(5, 10);
                poisonDamage = round(poisonDamage, 2);
                monster.setHealth(round(monster.getHealth() - poisonDamage, 2));
                System.out.println("\nPoison dealt " + poisonDamage + " damage to the monster.");}
            // Poison move thing idk
            if(reduceDamage){
                if(pm.equals("Dodge")){monster.setHealth(round(monster.getHealth() - monsterDamage, 2));reduceDamage = false;
                    System.out.println("\nDodge sucsessful, " + monsterDamage + " has been dealt to your opponent.\n");}
                if(pm.equals("Parry")){monster.setHealth(round(monster.getHealth() - monsterDamage, 2));reduceDamage = false;
                    System.out.println("\nParry sucsessful, " + monsterDamage + " has been dealt to your opponent.\n");}
                else{monster.setHealth(round(player.getH() - (monsterDamage / 2), 2));reduceDamage = false;
                    System.out.println("\nTank sucsessful, " + monsterDamage + " has been dealt to you.\n");}}


            
        }









        if(player.getH() > 0){System.out.println("\nYou won the fight!\n");} 
        else{System.out.println("\nYou lost the fight!\n");}}
    private double handleLightMoves(String pm, Monster monster, Player player){
        double damageDealt = 0;
        switch(pm){
            case "Shank":damageDealt = getRandomDouble(10, 20);System.out.println("\nYou hit the enemy for " + damageDealt + " damage!");monster.setHealth(round(monster.getHealth() - damageDealt, 2));break;
            case "Dodge":damageDealt = getRandomDouble(25, 35);System.out.println("\nThe enemy hit the ground and took " + damageDealt + " damage!");monster.setHealth(round(monster.getHealth() - damageDealt, 2));break;
            case "Poison":damageDealt = getRandomDouble(10, 20);System.out.println("\nYou decided to Poison. You will do " + damageDealt + " damage, this turn and deal additional damage for the rest of the battle.");monster.setHealth(round(monster.getHealth() - damageDealt, 2));break;
            case "Invisibility":System.out.println("\nYou used Invisibility. You have a 50% chance not to take damage");if(getRandomBoolean()){System.out.println("\nYou were successful and were not hit!");}break;}
        return damageDealt;}
    // Light Move Handler
    private double handleNormalMoves(String pm, Monster monster, Player player, double monsterDamage){
        double damageDealt = 0;
        switch(pm){
            case "Slash":damageDealt = getRandomDouble(20, 30);System.out.println("\nYou hit the enemy for " + damageDealt + " damage!");monster.setHealth(round(monster.getHealth() - damageDealt, 2));break;
            case "Stab":damageDealt = getRandomDouble(35, 45);System.out.println("\nYou hit the enemy for " + damageDealt + " damage!");monster.setHealth(round(monster.getHealth() - damageDealt, 2));break;
            case "Parry":System.out.println("\nYou decided to Parry. Your opponent will take damage from missing you.\nThey took " + monsterDamage + " damage!");monster.setHealth(round(monster.getHealth() - monsterDamage, 2));break;
            case "Boost":System.out.println("\nYou used Boost. Your damage is greatly increased and the monster's is reduced.");break;}
        return damageDealt;}
    // Normal Move Handler
    private double handleHeavyMoves(String pm, Monster monster, Player player){
        double damageDealt = 0;
        switch(pm){
            case "Strike":damageDealt = getRandomDouble(35, 45);System.out.println("\nYou hit the enemy for " + damageDealt + " damage!");monster.setHealth(round(monster.getHealth() - damageDealt, 2));break;
            case "Slam":damageDealt = getRandomDouble(40, 50);System.out.println("\nYou hit the enemy for " + damageDealt + " damage!");monster.setHealth(round(monster.getHealth() - damageDealt, 2));break;
            case "Tank":System.out.println("\nYou decided to Tank. You will take reduced damage.");break;
            case "Challenge":System.out.println("\nYou used Challenge. You will do great damage but also take great damage.");break;}
        return damageDealt;}
    // Heavy Move Handler
    public static void main(String[] args){
        new Maind().run();}}