import static java.lang.System.in;
import static java.lang.System.out;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Abdul Rafeh
 */
public class Class1 

{

    /**
     *
     * @param args
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) 

    {

        // Main objects

        Scanner console = new Scanner(in);
        Random rand = new Random();

        // Game variables

        String[] enemies = { "Kobold", "Kobold Warrior", "Kobold Archer", "Kobold Overseer" };
        String[] shopItems = { "Silver Sword", "Steel Sword", "Iron Helmet", "Iron Chestplate", "Iron Boots", "Iron Gauntlets", "Steel Helmet", "Steel Chestplate", "Steel Boots", "Steel Gauntlets", "Illbane" };
        String randomItem = null;

        int enemyAttackDamage = 25;
        int enemyHealth = 0;

        // Boss Variables

        String[] bossList = { "Red Drake" };

        int redDrakeArmor = 20;
        int redDrakeAttack = 75;
        int redDrakeSpecialAttackValue = 200;
        // Player variables

        int playerHealth = 100;
        int playerAttackDamage = 50;
        int initialPlayerAttack = playerAttackDamage;
        int playerArmorValue = 0;
        int numHealthPotions = 5;                                       // How many potions the player will start with.
        int healthPotionEffect = 30;                                    // How much each potion will heal.
        int healthPotionDropChance = 50;                                // Percentage drop from enemies.

        int numStrengthPotions = 0;
        int strengthPotionEffect = (rand.nextInt(3) + 1);
        int goldDropChance = 75;                                        // Percentage drop of gold from enemies.
        int goldDropAmount;
        int goldAmount = 1_000;
        int crimsonSwordDropChance = 25;

        int buyStrengthPotion;
        int buyHealthPotion;

        int illbaneCount = 4;
        boolean running = true;

        out.println("Now entering the Kobolds Lair...");

        GAME:
        while(running) 

        {

            out.println("-------------------------------------------------");

            String enemy = enemies[rand.nextInt(enemies.length)];       // Enemy Spawning.

            out.println("\t# " + enemy + " appears! #\n");

            if (null != enemy)                                 

            switch (enemy) {
            // Enemy stat setting.
                case "Kobold":
                    enemyHealth = rand.nextInt(100) + 50;
                    enemyAttackDamage = 25;
                    break;
                case "Kobold Archer":
                    enemyHealth = rand.nextInt(150) + 70;
                    enemyAttackDamage = 30;
                    break;
                case "Kobold Warrior":
                    enemyHealth = rand.nextInt(200) + 90;
                    enemyAttackDamage = 40;
                    break;
                case "Kobold Overseer":
                    enemyHealth = rand.nextInt(250) + 150;
                    enemyAttackDamage = 50;
                    break;
                default:
                    break;
            }
            OUTER:
            while (enemyHealth > 0) {
                out.println("\tYour HP is: " + playerHealth);
                out.println("\t" + enemy + "'s HP: " + enemyHealth);
                out.println("\n\tWhat would you like to do?");
                out.println("\t1. Attack");
                out.println("\t2. Drink health potion");
                out.println("\t3. Run!");
                out.println("\t4. Drink strength potion");
                String input = console.nextLine();
                switch (input) {
                    case "1":
                        int damageDealt = rand.nextInt(playerAttackDamage);
                        int damageTaken = rand.nextInt(enemyAttackDamage) - (playerArmorValue);
                        enemyHealth -= damageDealt;
                        playerHealth -= damageTaken;
                        if (damageTaken <= 0)
                        {
                            
                            damageTaken = rand.nextInt(5) + 5;
                            
                        }   out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                        out.println("\t> You recieve " + damageTaken + " in retaliation!");
                        if (playerHealth < 1) {
                            out.println("\t> You have taken too much damage, you are too weak to go on!");
                            break OUTER;
                        }
                        break;
                    case "2":
                        if(numHealthPotions > 0)
                        {
                            
                            playerHealth += healthPotionEffect;
                            numHealthPotions--;
                            out.println("\t> You drink a health potion and recover " + healthPotionEffect + " health!"
                                    + "\n\t> You now have " + playerHealth + " HP."
                                            + "\n\t> You have " + numHealthPotions + " health potions left.\n)");
                            
                        }
                        
                        else
                        {
                            
                            out.println("\t> You have no health potions left! ");
                            
                        }   break;
                    case "3":
                        out.println("\tYou run away from the " + enemy + "!");
                        continue GAME;
                    case "4":
                        int playerAttack = playerAttackDamage;
                        if (numStrengthPotions > 0) {
                            playerAttackDamage = playerAttack * strengthPotionEffect;
                        }   numStrengthPotions--;
                        out.println("You drank a strength potion and your attack has multiplied by: " + strengthPotionEffect + " time(s).!");
                        break;
                    default:
                        out.println("\tInvalid command...");
                        break;
                }
            }

            if (playerHealth < 1)

            {

                out.println("\n\tYou crawl out of the dungeon to live and fight another day.");
                break;

            }

            out.println("-------------------------------------------------");
            out.println(" # " + enemy + " was defeated! #");
            out.println(" # You have " + playerHealth + " HP left. #");

            playerAttackDamage = initialPlayerAttack;

            if(rand.nextInt(100) < healthPotionDropChance)          // Health Potion drop chance; differs from enemy to enemy.

            {

                if (null != enemy)

                switch (enemy) {
                    case "Kobold Archer":
                        healthPotionDropChance = 55;
                        break;
                    case "Kobold Warrior":
                        healthPotionDropChance = 60;
                        break;
                    case "Kobold Overseer":
                        healthPotionDropChance = 75;
                        break;
                    default:
                        break;
                }

                numHealthPotions++;

                out.println(" # The " + enemy + " dropped a health potion! # ");
                out.println(" # You have " + numHealthPotions + " health potion(s). # ");

            }

            if(rand.nextInt(100) < goldDropChance)          // Gold drop chance; also differs from enemy to enemy.

            {

                if (null != enemy)

                switch (enemy) {
                    case "Kobold Archer":
                        goldDropChance = 55;
                        break;
                    case "Kobold Warrior":
                        goldDropChance = 60;
                        break;
                    case "Kobold Overseer":
                        goldDropChance = 75;
                        break;
                    default:
                        break;
                }

                goldDropAmount = rand.nextInt(500) + 1;
                goldAmount += goldDropAmount;

                out.println(" # The " + enemy + " dropped " + goldDropAmount + " gold! #");
                out.println(" # You now have " + goldAmount + " gold. #");

            }

            out.println("-------------------------------------------------");            
            out.println("What to do now?");          // Next set of options;
            out.println("1. Continue fighting");     // 1. Resets loop, keeps progress.
            out.println("2. Exit dungeon");          // 2. Breaks loop, progress lost; game over.
            out.println("3. Visit the shop");        // 3. Triggers shop sequence.
            out.println("4. Sacrifice Illbane...");  // 4. Triggers hard battle.

            String input = console.nextLine();


            while(!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4"))
            {

                out.println("Invalid command...");
                input = console.nextLine();

            }

            if (input.equals("1"))

            {

                out.println("You continue on your trek through the dungeon.");

                continue;

            }

            else if (input.equals("2"))

            {

                out.println("You exit the dungeon.");
                break;

            }

            else if (input.equals("4") && illbaneCount >= 4)        // Boss Battle Sequence, few special rules...

            {
                    illbaneCount -= 4;

                    out.println("-------------------------------------------------");
                    out.println("\t# A great beast stirs! #");

                    int redDrakeHealth = 2_500;
                    String enemyBoss = bossList[0];

                    out.println("\t# " + enemyBoss + " appears! #\n");

                    while(redDrakeHealth > 0)

                    {
                        // Enemy introduction and presentation of options.

                        out.println("\tYour HP is: " + playerHealth);
                        out.println("\t" + enemyBoss + "'s HP: " + redDrakeHealth);
                        out.println("\n\tWhat would you like to do?");

                        // Player options

                        out.println("\t1. Attack");
                        out.println("\t2. Drink health potion");
                        out.println("\t3. Run!");
                        out.println("\t4. Drink strength potion");

                        String inputBossFight = console.nextLine();

                        if (inputBossFight.equals("1"))

                        {

                            int bossDamageDealt = rand.nextInt(playerAttackDamage) - (redDrakeArmor);
                            int bossDamageTaken = rand.nextInt(redDrakeAttack) - (playerArmorValue);

                            redDrakeHealth -= bossDamageDealt;
                            playerHealth -= bossDamageTaken;

                            if (bossDamageTaken <= 0)
                            {

                                bossDamageTaken = rand.nextInt(20) + 10;

                            }

                            out.println("\t> You strike the " + enemyBoss + " for " + bossDamageDealt + " damage.");
                            out.println("\t> You recieve " + bossDamageTaken + " in retaliation!");

                            if(playerHealth < 1)

                            {

                                out.println("\t> You have taken too much damage, you are too weak to go on!");
                                break;

                            }

                        }

                        else if (inputBossFight.equals("2"))

                        {

                            if(numHealthPotions > 0)
                            {

                                playerHealth += healthPotionEffect;
                                numHealthPotions--;
                                out.println("\t> You drink a health potion and recover " + healthPotionEffect + " health!"
                                                   + "\n\t> You now have " + playerHealth + " HP."
                                                   + "\n\t> You have " + numHealthPotions + " health potions left.\n)");

                            }

                            else
                            {

                                out.println("\t> You have no health potions left! ");

                            }

                        }

                        else if (inputBossFight.equals("3"))

                        {

                            out.println("\tYou run away from the " + enemy + "!");
                            continue GAME; 

                        }

                        else if (input.equals("4"))

                        {

                            int playerAttack = playerAttackDamage;

                            if (numStrengthPotions > 0) {
                                playerAttackDamage = playerAttack * strengthPotionEffect;
                            }
                            numStrengthPotions--;

                            out.println("You drank a strength potion and your attack has multiplied by: " + strengthPotionEffect + " time(s).!");

                        }


                        else

                        {

                            out.println("\tInvalid command...");

                        }

                    }

                    if (playerHealth < 1)

                    {

                        out.println("\n\tYou crawl out of the dungeon to live and fight another day.");
                        break;

                    }

                    if (redDrakeHealth <= 100)

                    {

                        out.println("\n\t!!!# The Red Drake unleashes it's special attack #!!!");

                        playerHealth -= redDrakeSpecialAttackValue;

                        out.println("\t>!!!# You recieve " + redDrakeSpecialAttackValue + " in retaliation from the Drake's fiery breath! #!!!");

                    }

                    out.println("-------------------------------------------------");
                    out.println(" # " + enemyBoss + " was defeated! #");
                    out.println(" # You have " + playerHealth + " HP left. #");

                    playerAttackDamage = initialPlayerAttack;

                    if(rand.nextInt(100) < healthPotionDropChance)

                    {

                        numHealthPotions++;
                        numStrengthPotions++;

                        out.println(" # The " + enemyBoss + " dropped a health potion, and a strength potion! # ");
                        out.println(" # You have " + numHealthPotions + " health potion(s), and " + numStrengthPotions + "! #");

                    }

                    if(rand.nextInt(100) < goldDropChance)

                    {

                        goldDropAmount = rand.nextInt(500) + 1_000;
                        goldAmount += goldDropAmount;

                        out.println(" # The " + enemyBoss + " dropped " + goldDropAmount + " gold! #");
                        out.println(" # You now have " + goldAmount + " gold. #");

                    }

                    if (rand.nextInt(100) < crimsonSwordDropChance)

                    {

                        out.println("\n\t!!!# The fearsome Red Drake has dropped a ruby sword of power, your attack has increased five fold! #!!!");

                        playerAttackDamage *= 5;

                    }

                    else if (illbaneCount != 4)

                    {

                        out.println("You do not have enough illbane to make a worthy sacrifice!");

                        continue;

                    }

            }



            else if (input.equals("3"))         // Shop Sequence

            {

                out.println("\nWelcome to the sshhop outssider...");
                out.println("What would you like to buy?");

                randomItem = shopItems[rand.nextInt(shopItems.length)];

                SHOP:
                out.println("\nWould to like to buy a(n): " + randomItem + "?");

                switch (randomItem) {
                    case "Silver Sword":
                        out.println("\nThat will be 1000 gold.");
                        break;
                    case "Steel Sword":
                        out.println("\nThat will be 250 gold.");
                        break;
                    case "Iron Helmet":
                        out.println("\nThat will be 150 gold.");
                        break;
                    case "Iron Chestplate":
                        out.println("\nThat will be 200 gold.");
                        break;
                    case "Iron Boots":
                        out.println("\nThat will be 100 gold.");
                        break;
                    case "Iron Gauntlets":
                        out.println("\nThat will be 75 gold.");
                        break;
                    case "Steel Helmet":
                        out.println("\nThat will be 400 gold.");
                        break;
                    case "Steel Chestplate":
                        out.println("\nThat will be 600 gold.");
                        break;
                    case "Steel Boots":
                        out.println("\nThat will be 300 gold.");
                        break;
                    case "Steel Gauntlets":
                        out.println("\nThat will be 250 gold.");
                        break;
                    case "Illbane":
                        out.println("\nThat will be 2500 gold. Interesting...");
                        break;
                    default:
                        break;
                }

                out.println("1. Yes");
                out.println("2. No");

                String input1 = console.nextLine();

                if (input1.equals("1") && randomItem.equals("Silver Sword"))

                {

                    out.println("Thank you for your purchase.");

                    goldAmount -= 1_000;
                    playerAttackDamage += 100;

                    shopItems[0] = "Empty";

                    continue;

                }

                else if (input1.equals("1") && randomItem.equals("Steel Sword"))

                {

                    out.println("Thank you for your purchase.");

                    goldAmount -= 250;
                    playerAttackDamage += 25;

                    shopItems[1] = "Empty";

                    continue;

                }

                else if (input1.equals("1") && randomItem.equals("Iron Helmet"))

                {

                    out.println("Thank you for your purchase.");

                    goldAmount -= 150;
                    playerArmorValue += 10;

                    shopItems[2] = "Empty";

                    continue;

                }

                else if (input1.equals("1") && randomItem.equals("Iron Chestplate"))

                {

                    out.println("Thank you for your purchase.");

                    goldAmount -= 200;
                    playerArmorValue += 18;

                    shopItems[3] = "Empty";

                    continue;

                }

                else if (input1.equals("1") && randomItem.equals("Iron Boots"))

                {

                    out.println("Thank you for your purchase.");

                    goldAmount -= 100;
                    playerArmorValue += 8;

                    shopItems[4] = "Empty";

                    continue;

                }

                else if (input1.equals("1") && randomItem.equals("Iron Gauntlets"))

                {

                    out.println("Thank you for your purchase.");

                    goldAmount -= 75;
                    playerArmorValue += 5;

                    shopItems[5] = "Empty";

                    continue;

                }

                else if (input1.equals("1") && randomItem.equals("Steel Helmet"))

                {

                    out.println("Thank you for your purchase.");

                    goldAmount -= 300;
                    playerArmorValue += 15 - 10;

                    shopItems[6] = "Empty";

                    continue;

                }

                else if (input1.equals("1") && randomItem.equals("Steel Chestplate"))

                {

                    out.println("Thank you for your purchase.");

                    goldAmount -= 600;
                    playerArmorValue += 30 - 20;

                    shopItems[7] = "Empty";

                    continue;

                }

                else if (input1.equals("1") && randomItem.equals("Steel Boots"))

                {

                    out.println("Thank you for your purchase.");

                    goldAmount -= 300;
                    playerArmorValue += 18 - 8;

                    shopItems[8] = "Empty";

                    continue;

                }

                else if (input1.equals("1") && randomItem.equals("Steel Gauntlets"))

                {

                    out.println("Thank you for your purchase.");

                    goldAmount -= 250;
                    playerArmorValue += 12 - 5;

                    shopItems[9] = "Empty";

                    continue;

                }

                else if (input1.equals("1") && randomItem.equals("Illbane"))

                {

                    out.println("Thank you for your purchase. See what you can do with a couple more of those...");

                    goldAmount -= 2_500;
                    illbaneCount++;

                    shopItems[10] = "Empty";

                    continue;

                }

                else if (input1.equals("2"))

                {

                    out.println("\nWould you like to buy some potions atleast?");
                    out.println("1. Yes");
                    out.println("2. No!");

                    String input2 = console.nextLine();



                    POTIONCHOICE:

                    if (input2.equals("2"))

                    {

                        out.println("\nAlrighty.");

                        continue;

                    }

                    if (input2.equals("1"))

                    {

                        out.println("\nHealth Potions or Strength Potions?");
                        out.println("1. Health Potions: 100 gold");
                        out.println("2. Strength Potions: 500 gold");
                        out.println("3. Nevermind!");

                        String input3 = console.nextLine();

                        if (input2.equals("3"))

                        {

                            out.println("Then get on wiv' it!");

                            continue;

                        }

                        else if (input3.equals("1"))

                        {

                            out.println("How many would you like to buy?");
                            int inputNumH = console.nextInt();

                            goldAmount -= inputNumH * 100;
                            numHealthPotions += inputNumH;

                            out.println("Here you are: " + inputNumH + " health potions.");

                            continue;

                        }

                         else if (input3.equals("2"))

                         {

                             out.println("How many would you like to buy?");
                             int inputNumS = console.nextInt();

                             goldAmount -= inputNumS * 500;
                             numStrengthPotions += inputNumS;

                             out.println("Here you are: " + inputNumS + " strength potions.");

                             continue;

                         }

                         else if (input3.equals("3"))

                         {

                             out.println("Stop wasting my time!");

                             continue;

                         }

                         else

                         {

                             out.println("What are you trying to say?!");

                             continue;

                         }



                        }



                }

                out.println("\n\t# THANKS FOR PLAYING! # ");

                break;

            }

        }

    }
}

