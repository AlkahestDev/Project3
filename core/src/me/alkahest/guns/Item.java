package me.alkahest.guns;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by dumpl on 7/7/2017.
 */
public class Item {
    private Vector2 position;
    private Vector2 velocities;
    public Item(float x, float y){
        this.position = new Vector2(x,y);
        this.velocities = new Vector2(0,0); // direction and speed
    }
}
