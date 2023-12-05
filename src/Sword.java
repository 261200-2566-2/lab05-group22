public class Sword {
    private final int BaseDamage;
    private int Level;
    private double SwordDamage;

    public Sword(int _BaseDamage,int Level){
        BaseDamage =  _BaseDamage;
        this.Level = Level;
        SwordDamage = BaseDamage*(1+0.1*Level);
    }

    public void LevelUp(){
        Level += 1;
        SwordDamage = BaseDamage*(1+0.1*Level);
    }
    public int getLevel() {
        return Level;
    }
    public int getBaseDamage() {
        return BaseDamage;
    }
    public double getSwordDamage() {
        return SwordDamage;
    }

}