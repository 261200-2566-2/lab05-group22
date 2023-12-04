import java.util.Scanner;

interface Job {
    String jobType();
}
interface Wizard extends Job{
    void castSpell();
    void healing();

}
interface Warrior extends Job{
    void BoostSpeed();


}
public class RPG_character implements Job{

    private final String name;
    protected int level;
    protected double BaseSpeed;
    protected double MaxHp;
    protected double MaxMana;
    protected double Hp;
    protected double Mana;
    private double Atk;
    protected double MaxSpeed;
    protected double current_Speed;
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
    public void ShowStat() {
        System.out.println("----------------------------------------------------------------");
        System.out.println(name + " (" + jobType() + ") - Level " + level);
        System.out.println("HP: " + Hp + "/" + MaxHp);
        System.out.println("Mana: " + Mana + "/" + MaxMana);
        System.out.println("Attack Damage: " + attack());
        System.out.println("Speed: " + current_Speed);
        System.out.println("Sword: " + (Sword != null ? "Lv." + Sword.getLevel() + " Damage: " + Sword.getSwordDamage() : "Nothing"));
        System.out.println("Shield: " + (Shield != null ? "Lv." + Shield.getLevel() : "Nothing"));
    }


    @Override
    public String jobType() {
        return "I don't know";
    }

}
class Wizard_character extends RPG_character implements Wizard {


    public Wizard_character(String name, int _level, double _baseSpeed) {
        super(name, _level, _baseSpeed);
        MaxHp -= 20;
        Hp = MaxHp;
        MaxMana = 100 + 2 * level;
        Mana = MaxMana;
        MaxSpeed -= 10;
        current_Speed = BaseSpeed;

    }

    @Override
    public String jobType() {
        return "Wizard";
    }

    @Override
    public void castSpell() {
        System.out.println("Increasing Mana!");
        if(Mana == MaxMana - 20){
            Mana = MaxMana;
        }else if(Mana < MaxMana-20){
            Mana += 20;
        }
    }

    @Override
    public void healing() {
        System.out.println("Healing ability activated!");
        if(Mana >= 10){
            if(Hp == MaxHp - 20){
                Hp = MaxHp;

            }else if(Hp < MaxHp - 20){
                Hp += 20;
            }else{
                Hp = MaxHp;
            }
        }else{
            System.out.println("Your Mana is not Enough!");
        }

        Mana -= 10;
    }
}

class Warrior_character extends RPG_character implements Warrior {
    public Warrior_character(String name, int _level, double _baseSpeed) {
        super(name, _level, _baseSpeed);
        MaxHp += 50;
        Hp = MaxHp;
        MaxSpeed += 10;
        MaxMana -= 10;
        Mana = MaxMana;
        current_Speed = BaseSpeed;
    }

    @Override
    public String jobType() {
        return "Warrior";
    }



    @Override
    public void BoostSpeed() {
        current_Speed += 15;
    }
}





