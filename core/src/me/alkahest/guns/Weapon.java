package me.alkahest.guns;

import me.alkahest.tools.EntropyTools;

import java.util.HashMap;

/**
 * Created by dumpl on 7/7/2017.
 */
public class Weapon{
    public static class Gun extends Item{
        public static final int NUMPARTS = 3;

        public enum PartType {
            STOCK,
            RECEIVER,
            BARREL
        }


        public enum Stat {
            DAMAGE,
            ACCURACY,
            DURABILITY,
            FIRE_RATE,
            RELOAD_SPEED,
            MASS;
        }
        public enum FireMode{
            SEMI,
            AUTO,
            BOLT;
        }
        public enum MaterialType{
            WOOD(
                    0.8f,1.2f,1,
                    0.5f,0.4f,0.2f,
                    0.3f,0.2f,0.2f,
                    0.5f),
            STEEL(
                    1,1,1,
                    1,1,1,
                    1,1,1,
                    1),
            PLASTIC(
                    0.8f,0.9f,0.9f,
                    0.6f,0.5f,0.3f,
                    0.3f,0.3f,0.2f,
                    0.3f),
            TUNGSTEN(
                    1.3f,1,1,
                    1.3f,0.95f,1,
                    1,1,1.2f,
                    1.2f),
            GOLD(
                    0.9f,0.7f,0.2f,
                    0.7f,0.6f,0.2f,
                    0.65f,0.6f,0.2f,
                    1.5f),
            TITANIUM(
                    1.4f,1.2f,1.5f,
                    1.6f,1.4f,1.5f,
                    1.6f,1.5f,1.5f,
                    0.8f),
            CARBON_FIBER(
                    1.55f,1.3f,1.6f,
                    1.5f,1.3f,1.5f,
                    1.5f,1.2f,1.4f,
                    0.6f
            ),
            ALUMINIUM(
                    1.2f,0.9f,0.9f,
                    1.2f,1,0.9f,
                    1.2f,1,0.7f,
                    0.6f
            );
            private final Material material;
            MaterialType(float stockDamage, float stockAccuracy, float stockDurability, float receiverDamage, float receiverAccuracy, float receiverDurability, float barrelDamage, float barrelAccuracy, float barrelDurability,float weight) {
                material = new Material(this.name(),weight);
                material.setStat(PartType.STOCK,Stat.DAMAGE,stockDamage);
                material.setStat(PartType.STOCK,Stat.ACCURACY,stockAccuracy);
                material.setStat(PartType.STOCK,Stat.DURABILITY,stockDurability);
                material.setStat(PartType.RECEIVER,Stat.DAMAGE,receiverDamage);
                material.setStat(PartType.RECEIVER,Stat.ACCURACY,receiverAccuracy);
                material.setStat(PartType.RECEIVER,Stat.DURABILITY,receiverDurability);
                material.setStat(PartType.BARREL,Stat.DAMAGE,barrelDamage);
                material.setStat(PartType.BARREL,Stat.ACCURACY,barrelAccuracy);
                material.setStat(PartType.BARREL,Stat.DURABILITY,barrelDurability);
                for(PartType pt : PartType.values()){
                    for(Stat s : Stat.values()){
                        if(s != Stat.DAMAGE && s!=Stat.ACCURACY&&s!=Stat.DURABILITY) {
                            material.setStat(pt, s, 0f);
                        }
                    }
                }
            }
            public Material getMaterial(){
                return this.material;
            }
        }
        public enum ComponentType{
            //Lower accuracy is better (recoil amount)
            //Lower reload speed is better
            SHORT_BARREL(PartType.BARREL,null,0,-0.5f,-0.1f,0.2f,0.3f,null),
            MEDIUM_BARREL(PartType.BARREL,null,0,0,0.1f,0.4f,0,null),
            LONG_BARREL(PartType.BARREL,null,0,0.4f,0.7f,0.7f,-0.5f,null),
            BULLPUP_AUTO_RECEIVER(PartType.RECEIVER,FireMode.AUTO,600,1.2f,1,1f,1,true),
            BULLPUP_SEMI_RECEIVER(PartType.RECEIVER,FireMode.SEMI,180,1.2f,1.4f,1f,1,true),
            BULLPUP_BOLTACTION_RECEIVER(PartType.RECEIVER,FireMode.BOLT,60,1.4f,3f,1.2f,0.15f,true),
            STANDARD_AUTO_RECEIVER(PartType.RECEIVER,FireMode.AUTO,750,1,1,1.2f,1,true),
            STANDARD_SEMI_RECEIVER(PartType.RECEIVER,FireMode.SEMI,220,1,1.4f,1.2f,1,true),
            STANDARD_BOLTACTION_RECEIVER(PartType.RECEIVER,FireMode.BOLT,80,1.2f,3f,1.35f,0.2f,true),
            FLINTLOCK_RECEIVER(PartType.RECEIVER,FireMode.SEMI,15,4,4,1.3f,2,true),
            BLUNDERBUSS_RECEIVER(PartType.RECEIVER,FireMode.SEMI,15,5,1,1.4f,4,true),
            BULLPUP_AUTO_SHOTGUN_RECEIVER(PartType.RECEIVER,FireMode.AUTO,400,1.5f,0.8f,1.3f,3.5f,false),
            BULLPUP_SEMI_SHOTGUN_RECEIVER(PartType.RECEIVER,FireMode.SEMI,160,1.4f,0.9f,1.25f,3.3f,false),
            BULLPUP_PUMPACTION_SHOTGUN_RECEIVER(PartType.RECEIVER,FireMode.BOLT,30,2,1.2f,1.2f,3f,false),
            STANDARD_AUTO_SHOTGUN_RECEIVER(PartType.RECEIVER,FireMode.AUTO,500,1.3f,0.75f,1.4f,3.4f,false),
            STANDARD_SEMI_SHOTGUN_RECEIVER(PartType.RECEIVER,FireMode.SEMI,180,1.4f,0.8f,1.3f,3.3f,false),
            STANDARD_PUMPACTION_SHOTGUN_RECEIVER(PartType.RECEIVER,FireMode.BOLT,90,1.5f,0.8f,1.3f,2.5f,false),
            CRANE_STOCK(PartType.STOCK,null,0,0.05f,0,0.3f,-0.2f,null),
            PRECISION_STOCK(PartType.STOCK,null,0,0.1f,0.05f,0.25f,-0.3f,null),
            NO_STOCK(PartType.STOCK,null,0,-0.2f,0,0,0.4f,null),
            WIRE_STOCK(PartType.STOCK,null,0,0,0,0.1f,0.2f,null),
            SOLID_STOCK(PartType.STOCK,null,0,0.1f,0.05f,0.3f,-0.25f,null);
            private float fireRate,reloadSpeed,damage,mass,accuracy;
            private PartType pType;
            private FireMode fMode;
            private Boolean singleProjectile;
            ComponentType(PartType pType, FireMode fMode, float fireRateIn, float reloadSpeed, float damage, float mass, float accuracy, Boolean singleProjectile){
                this.pType = pType;
                switch (pType){
                    case BARREL:
                        this.fireRate = fireRateIn;
                        this.reloadSpeed = reloadSpeed;
                        this.damage = damage;
                        this.mass = mass;
                        this.accuracy = accuracy;
                        break;
                    case RECEIVER:
                        this.fMode = fMode;
                        this.fireRate = fireRateIn;
                        this.reloadSpeed = reloadSpeed;
                        this.damage = damage;
                        this.mass = mass;
                        this.accuracy = accuracy;
                        this.singleProjectile = singleProjectile;
                        break;
                    case STOCK:
                        this.fireRate = fireRateIn;
                        this.reloadSpeed = reloadSpeed;
                        this.damage = damage;
                        this.mass = mass;
                        this.accuracy = accuracy;
                        this.singleProjectile = singleProjectile;
                }
            }
            public Component makeComponent(int level,Material mat){
                Component cOut = new Component(this.name(),level,this.pType,mat);
                cOut.setStat(Stat.DAMAGE,this.damage);
                cOut.setStat(Stat.ACCURACY,this.accuracy);
                cOut.setStat(Stat.DURABILITY,mat.getStat(Stat.DURABILITY,this.pType));
                cOut.setStat(Stat.FIRE_RATE,this.fireRate);
                cOut.setStat(Stat.RELOAD_SPEED,this.reloadSpeed);
                cOut.setStat(Stat.MASS,this.mass*mat.getWeight());
                return cOut;
            }
            public float getFireRate() {
                return fireRate;
            }

            public float getReloadSpeed() {
                return reloadSpeed;
            }

            public float getDamage() {
                return damage;
            }

            public float getMass() {
                return mass;
            }

            public float getAccuracy() {
                return accuracy;
            }

            public PartType getpType() {
                return pType;
            }

            public FireMode getfMode() {
                return fMode;
            }

            public Boolean getSingleProjectile() {
                return singleProjectile;
            }
            public static ComponentType[] stocks(){
                return new ComponentType[]{CRANE_STOCK,PRECISION_STOCK,NO_STOCK,SOLID_STOCK,WIRE_STOCK};
            }
            public static ComponentType[] receivers(){
                return new ComponentType[]{
                        BULLPUP_AUTO_RECEIVER,BULLPUP_SEMI_RECEIVER,BULLPUP_BOLTACTION_RECEIVER,
                        STANDARD_AUTO_RECEIVER,STANDARD_SEMI_RECEIVER,STANDARD_BOLTACTION_RECEIVER,
                        FLINTLOCK_RECEIVER,BLUNDERBUSS_RECEIVER,
                        BULLPUP_AUTO_SHOTGUN_RECEIVER,BULLPUP_SEMI_SHOTGUN_RECEIVER,BULLPUP_PUMPACTION_SHOTGUN_RECEIVER,
                        STANDARD_AUTO_SHOTGUN_RECEIVER,STANDARD_SEMI_SHOTGUN_RECEIVER,STANDARD_PUMPACTION_SHOTGUN_RECEIVER
                };
            }
            public static ComponentType[] barrels(){
                return new ComponentType[]{
                        LONG_BARREL,MEDIUM_BARREL,SHORT_BARREL
                };
            }
        }
        private HashMap<PartType,Component> components;
        private String name;

        public Gun(float x, float y) {
            super(x, y);
            this.components = new HashMap<PartType, Component>();
        }
        public void autoGenerateParts(int levelIn){
            Material material;
            Component temp;
            //Pick a random material
            material = ((MaterialType) EntropyTools.randChoice(MaterialType.values())).getMaterial();
            //Pick a random receiver
            temp = ((ComponentType)EntropyTools.randChoice(ComponentType.receivers())).makeComponent(levelIn,material);
            //Modify receiver's stats based on material's
            temp.autoGenerate();
            this.components.put(PartType.RECEIVER,temp);
            material = ((MaterialType)EntropyTools.randChoice(MaterialType.values())).getMaterial();
            temp = ((ComponentType)EntropyTools.randChoice(ComponentType.stocks())).makeComponent(levelIn,material);
            temp.autoGenerate();
            this.components.put(PartType.STOCK, temp);
            material = ((MaterialType)EntropyTools.randChoice(MaterialType.values())).getMaterial();
            temp = ((ComponentType)EntropyTools.randChoice(ComponentType.barrels())).makeComponent(levelIn,material);
            temp.autoGenerate();
            this.components.put(PartType.BARREL, temp);
        }
        public HashMap<Stat, Float> addStats(Component receiver, Component stock, Component barrel){
            HashMap<Stat, Float> statsOut = new HashMap<Stat, Float>();
            for(Stat s : Stat.values()){
                Float val = receiver.getStat(s) + stock.getStat(s) + barrel.getStat(s);
                statsOut.put(s,val);
            }
            return statsOut;
        }
        public HashMap<Stat, Float> addStats(Gun gIn){
            return addStats(this.components.get(PartType.RECEIVER),this.components.get(PartType.STOCK),this.components.get(PartType.BARREL));
        }
        public void autoGenerateName(){

        }

        @Override
        public String toString() {
            String sOut = String.format("Gun{\nname='%s'\nCombined stats: %s\n", name, addStats(this));
            for(Component c : components.values()){
                sOut+=c.toString()+"\n";
            }
            sOut+="}";
            return sOut;
        }
        public String getWeaponType(){
            return this.components.get(PartType.RECEIVER).name;
        }

        /**
         * A class that handles each component of the weapon
         */
        public static class Component {
            private int level;
            private Material material;
            private PartType partType;
            private HashMap<Stat, Float> stats = new HashMap<Stat, Float>();
            private String name;
            public Component(String name, int levelIn, PartType partTypeIn, Material materialIn) {
                this.name = name;
                this.level = levelIn;
                this.partType = partTypeIn;
                this.material = materialIn;
            }

            public Component(Component cIn) {
                this.name= cIn.name;
                this.level = cIn.level;
                this.material = cIn.material;
                this.partType = cIn.partType;
                this.stats = cIn.stats;
            }

            public void autoGenerate() {
                for (Stat s : Stat.values()) {;
                    float val = this.stats.get(s);
                    switch (s){
                        case ACCURACY:
                            val = Math.max(0,(val*((((float)Math.random())/20f)+0.8f)));
                            break;
                        case DURABILITY:
                            val = (val*((((float)Math.random()-0.5f)/10f)+1f));
                            break;
                        case DAMAGE:
                        val = (val*((((float)Math.random()-0.5f)/10f)+1f))*((float)level/100f);
                        break;
                    }
                    this.stats.put(s, val);
                }
            }

            public void setStat(Stat statIn, Float value) {
                this.stats.put(statIn, value);
            }

            public Float getStat(Stat statIn) {
                return this.stats.get(statIn);
            }

            @Override
            public String toString() {
                String sOut =  "Component{" +
                        "\nname="+name+
                        "\nlevel=" + level +
                        "\nmaterial=" + material +
                        "\npartType=" + partType +
                        "\nstats:\n";
                for(Stat s : this.stats.keySet()){
                    sOut+=String.format("%s: %.3f\n",s,this.stats.get(s));
                }
                return sOut+"}";
            }
        }

        /**
         * A class that handles materials to be used in each component of a weapon
         */
        public static class Material {
            private float weight;
            private String name;
            private HashMap<PartType, HashMap<Stat, Float>> stats = new HashMap<PartType, HashMap<Stat, Float>>(); // first dimension is the stat (damage, accuracy, durability, etc.) second dimension is which part (barrel, receiver, stock, etc.)

            public Material(String name, float weight) {
                this.name=  name;
                this.weight = weight;
                for(PartType p : PartType.values()){
                    this.stats.put(p,new HashMap<Stat,Float>());
                }
            }

            public Material(Material pIn) {
                this.stats = pIn.getStats();
            }

            public void setStat(PartType partType, Stat stat, Float value) {
                this.stats.get(partType).put(stat,value);
            }

            public Float getStat(Stat statIn, PartType partTypeIn) {
                //System.out.println(partTypeIn);
                return this.stats.get(partTypeIn).get(statIn);
            }

            public float getWeight() {
                return weight;
            }

            public void setWeight(float weight) {
                this.weight = weight;
            }

            private HashMap<PartType, HashMap<Stat, Float>> getStats() {
                return stats;
            }

            @Override
            public String toString() {
                return "Material{" +
                        "name="+name+
                        ", weight=" + weight +
                        ", stats=" + stats +
                        '}';
            }
        }
    }
}
