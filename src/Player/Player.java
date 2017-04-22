package Player;

import Items.Gun;
import Items.Item;

import java.util.ArrayList;

/**
 * Created by Daniel Torres on 4/21/2017.
 */
public class Player {

    private int money;
    private ArrayList<Item> inventory;
    private final int INVENTORY_SIZE;
    private final int MAX_HEALTH;
    private int health;
    private int armor;
    private Gun weapon;
    private int radiation;
    private int sanity;
    private final int MAX_SANITY;

    public Player(){
        money = 100;
        inventory = new ArrayList<>();
        MAX_HEALTH = 100;
        INVENTORY_SIZE = 50;
        health = MAX_HEALTH;
        armor = 10;
        weapon = null;
        radiation = 0;
        MAX_SANITY = 100;
        sanity = MAX_SANITY;
    }

}
