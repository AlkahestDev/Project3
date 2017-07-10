package me.alkahest.tools;

import com.badlogic.gdx.utils.Array;
import me.alkahest.guns.Weapon;

import java.util.Collection;

/**
 * Created by dumpl on 7/9/2017.
 */
public class EntropyTools {
    public static Object randChoice(Collection listIn){
        int pickedNum = (int) ((float)listIn.size()*Math.random());
        return listIn.toArray()[pickedNum];
    }
    public static Object randChoice(Array listIn){
        int pickedNum = (int) ((float)listIn.size*Math.random());
        return listIn.get(pickedNum);
    }
    public static Object randChoice(Weapon.Gun.MaterialType[] arrayIn){
        int pickedNum = (int) ((float)arrayIn.length*Math.random());
        return arrayIn[pickedNum];
    }
    public static Object randChoice(Weapon.Gun.ComponentType[] arrayIn){
        int pickedNum = (int) ((float)arrayIn.length*Math.random());
        return arrayIn[pickedNum];
    }
}
