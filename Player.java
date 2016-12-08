/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mark
 */
public class Player {
    
    
    private String name;
    private int health;
    private Item currentItemEquipped;
    /**
     * Creates a Player object that mirrors the adventurer in order to participate in combat
     * @param name name of this Player
     * @param health maximum health of this Player
     */
    Player(String name, int health)
    {
        this.name = name;
        this.health = health;
    }
    
    /**
     * Returns player's current health
     * @return health field
     */
    public int getHealth()
    {
        return this.health;
    }
    
    /**
     * Sets player's health to a new value
     * @param health new value for health
     */
    public void setHealth(int health)
    {
        this.health = health;
    }
            
    /**
     * Returns player's name
     * @return name field
     */
    public String getName()
    {
        return this.name;
    }
            
    /**
     * Sets player's name to a new String
     * @param name new name
     */
    public void setName(String name)
    {
        this.name = name;
    }
            

}
