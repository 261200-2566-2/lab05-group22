import java.util.Scanner;

interface Job {
    void performJobAbility();
}
interface Wizard extends Job{
    void decreaseMaxHp();
    void increaseMaxMana();
    void castSpell();
    void healing();

}
interface Warrior extends Job{
    void increaseMaxHp();
    void increaseSpeed();
    void decreaseMaxMana();
}
public class RPG_character implements Job, Warrior, Wizard{

    private final String name;
    private int level;
    private double BaseSpeed;
    private double MaxHp,MaxMana,Hp,Mana,Atk,MaxSpeed,current_Speed;
    private Sword Sword;
    private Shield Shield;
    public RPG_character(String name, int _level, double _BaseSpeed){
        this.name = name;
        level = _level;
        MaxHp = 100+10*level;
        Hp = MaxHp;
        MaxMana = 50+2*level;
        Mana = MaxMana;
        Atk = 10+2*level;
        BaseSpeed = _BaseSpeed;
        MaxSpeed = BaseSpeed*(0.1+0.03*level);
        Sword = null;
        Shield = null;

    }

    public void LevelUp(){
        level++;
        if(Hp == MaxHp){
            Hp = 100+10*level;
        }
        if(Mana == MaxMana){
            Mana = 50+2*level;
        }
        MaxHp = 100+10*level;
        MaxMana = 50+2*level;
        Atk = 10+4*level;
        MaxSpeed = BaseSpeed*(0.1+0.03*level);
    }
    public  double attack(){
        if(Sword != null){
            return Sword.getSwordDamage()+Atk;
        }
        return Atk;
    }
    public void beAttacked(double damage){
        if(Shield != null){
            if(Shield.getShieldDefense() >= damage){
                System.out.println("U Take no damage");
            }else{
                Hp = Math.max((Hp - (damage-Shield.getShieldDefense())), 0);
            }
        }
        Hp = Math.max((Hp - damage), 0);
    }
    public void Equip(Sword sword){
        if(Sword == null){
            this.Sword = sword;
        }else{
            System.out.println("You already equip Sword!!!");
        }
    }
    public void Equip(Shield shield){
        if(Shield == null){
            this.Shield = new Shield(shield.getBaseDefense(),shield.getLevel());
        }else{
            System.out.println("You already equip Shield!!!");
        }
    }
    public void UnEquip(){
        Sword = null;
        Shield = null;
    }
    public void UnEquip(String equipment){
        if(equipment.equals("Sword")){
            Sword = null;
        }else if(equipment.equals("Shield")){
            Shield = null;
        }else{
            System.out.println("I don't know what equipment U want to Unequipped");
        }
    }
    public void ShowStat(){
        System.out.println("----------------------------------------------------------------");
        System.out.println(name +" level = " + level);
        System.out.println(name +" Hp = " + Hp + "/" + MaxHp);
        System.out.println(name +" Mp = " + Mana + "/" + MaxMana);
        System.out.println("Sword : " + (Sword != null ? "Sword" + " Lv." + Sword.getLevel() + " Dmg is "+ Sword.getSwordDamage(): "Nothing"));
        System.out.println("Shield : " + (Shield != null ? "Shield" + " Lv." + Shield.getLevel(): "Nothing"));
    }

    public void decreaseMaxHp(){
        MaxHp -= 50;
        Hp -= 50;
    }
    public void increaseMaxMana() {
        MaxMana += 50;
        Mana += 50;
    }


    public void castSpell() {
        System.out.println("Casting a spell!");
    }


    public void healing() {
        System.out.println("Healing ability activated!");
        if(Hp == MaxHp - 20){
            Hp = MaxHp;

        }else if(Hp < MaxHp - 20){
            Hp += 20;
        }else{
            Hp = MaxHp;
        }
    }



    public void increaseMaxHp() {
        MaxHp += 100;
        Hp += 100;
    }


    public void increaseSpeed() {
        BaseSpeed += 20;
    }


    public void decreaseMaxMana() {
        MaxMana -= 10;
        Mana -= 10;
    }

    public void performJobAbility() {
        System.out.println("Performing job ability!");
    }


}
