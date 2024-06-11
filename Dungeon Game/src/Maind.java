import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.Scanner;
public class Maind{// This entire program is magik
    private Random random = new Random();
    private boolean getRandomBoolean(){return random.nextBoolean();}
    private double getRandomDouble(double min, double max){return round(min + (max - min) * random.nextDouble(), 2);}
    // private int getRandomInt(int min, int max){Random random = new Random();return random.nextInt(max - min + 1) + min;}
    private String getRandomString(String[] options){int index = random.nextInt(options.length);return options[index];}
    private double round(double value, int places){if (places < 0) throw new IllegalArgumentException();BigDecimal bd = BigDecimal.valueOf(value);bd = bd.setScale(places, RoundingMode.HALF_UP);return bd.doubleValue();}
    private Scanner scanner = new Scanner(System.in);
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
        switch(entityType){//Entity creation    class                    Strenght of monster             Health of monster                 Hostlity            name                 Abilities                   Monster ability damage
            case "Knight":monster = new Knight("Normal", getRandomDouble(100, 150), getRandomDouble(150, 250), getRandomBoolean(), "Knight", getRandomString(knightAbilities), getRandomDouble(30, 40));break;
            case "Archer":monster = new Archer("Light", getRandomDouble(100, 180), getRandomDouble(100, 200), getRandomBoolean(), "Archer", getRandomString(archerAbilities), getRandomDouble(20, 30), "Arrow");break;
            case "Dragon":monster = new Dragon("Heavy", getRandomDouble(100, 220), getRandomDouble(250, 350), getRandomBoolean(), "Dragon", getRandomString(dragonAbilities), getRandomDouble(40, 50), 1.0, getRandomString(dragonBreaths));break;
            case "Zombie":monster = new Zombie("Normal", getRandomDouble(100, 175), getRandomDouble(150, 250), getRandomBoolean(), "Zombie", getRandomString(zombieAbilities), getRandomDouble(30, 40));break;
            case "Monster":monster = new Monster("Heavy", getRandomDouble(100, 220), getRandomDouble(250, 350), getRandomBoolean(), "Monster", getRandomString(monsterAbilities), getRandomDouble(40, 50));break;}
        encounterMonster(monster);}
    private void encounterMonster(Monster monster){// Encounter monster handler
        boolean vcc = false;
        while(!vcc){
        System.out.print("\nWhat character class do you want (Normal | Light | Heavy) ");if(vcc = true){break;}}
        String cs = scanner.nextLine();
        System.out.print("\nWhat do you want to name your character? ");
        String cn = scanner.nextLine();
        System.out.println("\n|----------------------------------------------------------------------------------------|\n");
        Player player = null;
        switch(cs){// Player Character selection and creationc and player character type
            case "light":player = new Player(100, 100, "Light", cn, "Shank", "Dodge", "Poison", "Invisibility");vcc = true;break;
            case "normal":player = new Player(150, 150, "Normal", cn, "Slash", "Stab", "Parry", "Boost");vcc = true;break;
            case "heavy":player = new Player(300, 200, "Heavy", cn, "Strike", "Slam", "Tank", "Challenge");vcc = true;break;}
        System.out.println(player);
        System.out.println("\n|----------------------------------------------------------------------------------------|\n");
        System.out.println(monster);
        entityFight(monster, player);}
        private void entityFight(Monster monster, Player player){
            boolean play = true;
            while(play){
                boolean reduceDamage = false;
                boolean poisonEffect = false;
                boolean challi = false;
                boolean Boost = false;
                boolean regen = true;
                boolean mregen = true;
                int BoostCount = 0;
                int poisonCount = 0;
                while(monster.getHealth() > 0 && player.getH() > 0){
                    boolean vm = false;
                    boolean em = true;
                    System.out.println("|----------------------------------------------------------------------------------------|\n");
                    while(!vm && player.getS() >= 0){System.out.print("What is your move: ");if (vm = true) break;}
                    String pm = scanner.nextLine();
                    System.out.println("\n|----------------------------------------------------------------------------------------|\n");
                    double damageDealt = 0;
                    damageDealt = round(damageDealt, 2);
                    double monsterDamage = 0;
                    monsterDamage = round(monsterDamage, 2);
                    String characterType = player.getClas();
                    switch(characterType){ // Special Move Handler
                        case "Light":damageDealt = handleLightMoves(pm, monster, player, vm);if(pm.equals("dodge")){reduceDamage = true;}else if (pm.equals("poison")){poisonEffect = true;}break;
                        case "Normal":damageDealt = handleNormalMoves(pm, monster, player, monsterDamage, vm);if(pm.equals("parry")){reduceDamage = true;}if(pm.equals("boost")){Boost = true;}break;
                        case "Heavy":damageDealt = handleHeavyMoves(pm, monster, player, vm);if(pm.equals("tank")){reduceDamage = true;}if(pm.equals("challenge")){challi = true;}break;}
                    if(regen && player.getS() <= 75){ // Player regen
                        player.setS(player.getS() + round(player.getS() * .05, 2));}
                    if(mregen && monster.getStamina() <= 75){ // Monster regen
                        monster.setStamina(monster.getStamina() + round(monster.getStamina() * .20, 2));}
                    if(poisonEffect){ // Poison move thing idk
                        poisonCount += 1;
                        double poisonDamage = getRandomDouble(5, 10);
                        poisonDamage = round(poisonDamage, 2);
                        monster.setHealth(round(monster.getHealth() - poisonDamage, 2));
                        System.out.println("\nPoison dealt " + poisonDamage + " damage to the monster.");
                        if(poisonCount == 3){
                            poisonEffect = false;
                            poisonCount = 0;}}
                    if(reduceDamage){ // Any moves that reduce or remove damage to the player
                        em = false;
                        if(pm.equals("dodge")){
                            monster.setHealth(round(monster.getHealth() - monsterDamage, 2));
                            reduceDamage = false;
                            System.out.println("\nDodge successful, " + monsterDamage + " has been dealt to your opponent.\n");}
                        if(pm.equals("parry")){
                            monster.setHealth(round(monster.getHealth() - monsterDamage, 2));
                            reduceDamage = false;
                            System.out.println("\nParry successful, " + monsterDamage + " has been dealt to your opponent.\n");} 
                        else{
                            monster.setHealth(round(player.getH() - (monsterDamage / 2), 2));
                            reduceDamage = false;
                            System.out.println("\nTank successful, " + monsterDamage + " has been dealt to you.\n");}}
                    if(Boost){ // Boost move
                        BoostCount += 1;
                        regen = false;
                        player.setH(round(player.getH() + (player.getH() * .10), 2));
                        player.setS(round(player.getS() + (player.getS() * .25), 2));
                        System.out.println("\nYour health has been increased by 10%.\n\nYou are now at: " + player.getH() + " HP.\n");
                        if(BoostCount == 3){
                            System.out.println("Your Boost has run out!");
                            Boost = false;
                            regen = true;
                            BoostCount = 0;}}
                    if(challi){ // Challenge move
                        em = false;
                        System.out.println("You stand to win everything, call it... ");
                        String ct = scanner.nextLine();
                        String[] coinflip = {"heads", "tails"};
                        String cgf = getRandomString(coinflip);
                        if (ct.equals(cgf)){monster.setHealth(round(monster.getHealth() - monster.getHealth() / 2, 2));} 
                        else{player.setH(round(player.getH() - player.getH() / 2, 2));
                            System.out.println("You are now at " + player.getH() + " HP.");}}
                    if(em){ // Entity Movement handler
                        player.setH(round(player.getH() - monster.getAbilityDamage(), 2));
                        monster.setStamina(round(monster.getStamina() - 30, 2));
                        System.out.println("The entity attacked and did " + monster.getAbilityDamage() + " Damage!\n\n|----------------------------------------------------------------------------------------|\n\nYou are now at " + player.getH() + " HP.\nYou now have " + player.getS() + " Stamina.\n");}
                    System.out.println("\n|----------------------------------------------------------------------------------------|\n");
                    System.out.println("Your opponent is now at " + monster.getHealth() + " HP.");
                    System.out.println("Your opponent now has " + monster.getStamina() + " Stamina\n");}
                if(player.getH() > 0){System.out.println("\nYou won the fight!\n");} 
                else{System.out.println("\nYou lost the fight!\n");}
                System.out.println("\n|----------------------------------------------------------------------------------------|\n");
                System.out.println("What do you want to do? (continue | quit)");
                String poo = scanner.nextLine();
                switch(poo){
                    case "continue":play = true;break;
                    case "quit":play = false;break;}}}
    private double handleLightMoves(String pm, Monster monster, Player player, boolean vm){
        double damageDealt = 0;
        switch(pm){// Light Move Handler
            case "shank":vm =true;damageDealt = getRandomDouble(10, 20);System.out.println("\nYou hit the enemy for " + damageDealt + " damage!");monster.setHealth(round(monster.getHealth() - damageDealt, 2));player.setS(round(player.getS() - 10, 2));break;
            case "dodge":vm =true;damageDealt = getRandomDouble(25, 35);System.out.println("\nThe enemy hit the ground and took " + damageDealt + " damage!");monster.setHealth(round(monster.getHealth() - damageDealt, 2));player.setS(round(player.getS() - 20, 2));break;
            case "poison":vm =true;damageDealt = getRandomDouble(10, 20);System.out.println("\nYou decided to Poison. You will do " + damageDealt + " damage, this turn and deal additional damage for the rest of the battle.");monster.setHealth(round(monster.getHealth() - damageDealt, 2));player.setS(round(player.getS() - 25, 2));break;
            case "invisibility":vm =true;System.out.println("\nYou used Invisibility. You have a 50% chance not to take damage");if(getRandomBoolean()){System.out.println("\nYou were successful and were not hit!");}player.setS(round(player.getS() - (player.getS() * .45), 2));break;}
        return damageDealt;}
    private double handleNormalMoves(String pm, Monster monster, Player player, double monsterDamage, boolean vm){
        double damageDealt = 0;
        switch(pm){// Normal Move Handler
            case "slash":vm =true;damageDealt = getRandomDouble(20, 30);System.out.println("\nYou hit the enemy for " + damageDealt + " damage!");monster.setHealth(round(monster.getHealth() - damageDealt, 2));player.setS(round(player.getS() - 15, 2));break;
            case "stab":vm =true;damageDealt = getRandomDouble(35, 45);System.out.println("\nYou hit the enemy for " + damageDealt + " damage!");monster.setHealth(round(monster.getHealth() - damageDealt, 2));player.setS(round(player.getS() - 20, 2));break;
            case "parry":vm =true;System.out.println("\nYou decided to Parry. Your opponent will take damage from missing you.\nThey took " + monsterDamage + " damage!");monster.setHealth(round(monster.getHealth() - monsterDamage, 2));player.setS(round(player.getS() - 25, 2));break;
            case "boost":vm =true;System.out.println("\nYou used Boost. You will now start to regen.");player.setS(round(player.getS() - (player.getS() * .45), 2));break;}
        return damageDealt;}
    private double handleHeavyMoves(String pm, Monster monster, Player player, boolean vm){
        double damageDealt = 0;
        switch(pm){// Heavy Move Handler
            case "strike":vm =true;damageDealt = getRandomDouble(35, 45);System.out.println("\nYou hit the enemy for " + damageDealt + " damage!");monster.setHealth(round(monster.getHealth() - damageDealt, 2));player.setS(round(player.getS() - 10, 2));break;
            case "slam":vm =true;damageDealt = getRandomDouble(40, 50);System.out.println("\nYou hit the enemy for " + damageDealt + " damage!");monster.setHealth(round(monster.getHealth() - damageDealt, 2));player.setS(round(player.getS() - 15, 2));break;
            case "tank":vm =true;System.out.println("\nYou decided to Tank. You will take reduced damage.");player.setS(round(player.getS() - 45, 2));break;
            case "challenge":vm =true;System.out.println("\nA coin toss that decides your fate.");player.setS(round(player.getS() - (player.getS() * .50), 2));break;}
        return damageDealt;}
    public static void main(String[] args){
        new Maind().run();}}