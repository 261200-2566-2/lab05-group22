interface Character_Setting{
     String name();
     int level();
     double MaxHp();
     double MaxMana();
     double Hp();
     double Mana();
     double Atk();
     double BaseSpeed();
     double MaxSpeed();
     double current_Speed();
}

interface Job_Setting extends Character_Setting {
    String Job_name();
}

interface Accessories_Setting extends Character_Setting {

}

class RPG_character implements Character_Setting{
    protected String name;
    protected int level;
    protected double MaxHp,MaxMana,Hp,Mana,Atk,BaseSpeed,MaxSpeed,current_Speed;
    public RPG_character(String name,int level){
        this.name = name;
        this.level = level;
        MaxHp = 100+10*level;
        Hp = MaxHp;
        MaxMana = 50+2*level;
        Mana = MaxMana;
        Atk = 10+2*level;
        this.BaseSpeed = BaseSpeed;
        MaxSpeed = BaseSpeed*(0.1+0.03*level);
    }
    @Override
    public String name() {
        return this.name;
    }
    @Override
    public int level() {
        return this.level;
    }
    @Override
    public double MaxHp() {
        return this.MaxHp;
    }
    @Override
    public double MaxMana() {
        return this.MaxMana;
    }
    @Override
    public double Hp() {
        return this.Hp;
    }
    @Override
    public double Mana() {
        return this.Mana;
    }
    @Override
    public double Atk() {
        return this.Atk;
    }
    @Override
    public double BaseSpeed() {
        return this.BaseSpeed;
    }
    @Override
    public double MaxSpeed() {
        return MaxSpeed;
    }
    @Override
    public double current_Speed() {
        return this.current_Speed;
    }

    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        Wizard Adam = new Wizard("Adam", 1);
        Warrior Eve = new Warrior("Eve", 1);
        RPG_character A = new RPG_character("A",1);
        Adam.ShowStat();
        Eve.ShowStat();
        A.ShowStat();
    }
    public void ShowStat(){
        System.out.println("----------------------------------------------------------------");
        System.out.println(name +" level = " + level);
        System.out.println(name +" Hp = " + Hp + "/" + MaxHp);
        System.out.println(name +" Mp = " + Mana + "/" + MaxMana);
    }
}

class Job extends RPG_character implements Job_Setting{
    protected String Job_name;
    public Job(String name, int level) {
        super(name, level);
        this.MaxHp = 90+10*level;
        this.Hp = MaxHp;
        this.MaxMana = 48+2*level;
        this.Mana = MaxMana;
        this.BaseSpeed = 6;
        this.MaxSpeed = 6*(0.1+0.03*level);
    }

    @Override
    public String Job_name() {
        return Job_name;
    }
}

class Wizard extends Job{
    double[] Stat = {80,70,4};
    public Wizard(String name, int level) {
        super(name,level);
        this.MaxHp = 80+10*level;
        this.Hp = MaxHp;
        this.MaxMana = 70+2*level;
        this.Mana = MaxMana;
        this.BaseSpeed = 4;
        this.MaxSpeed = 4*(0.1+0.03*level);
        this.Job_name = "Wizard";
    }
    void Healing(){
        if((Hp+20) >= MaxHp){
            Hp = MaxHp;
        }else{
            Hp += 20;
        }
    }
}

class Warrior extends Job{
    double[] Stat = {130,20,8};
    public Warrior(String name, int level) {
        super(name,level);
        this.Job_name = "Warrior";
        this.MaxHp = 130+10*level;
        this.Hp = MaxHp;
        this.MaxMana = 20+2*level;
        this.Mana = MaxMana;
        this.BaseSpeed = 8;
        this.MaxSpeed = 8*(0.1+0.03*level);
        this.Job_name = "Wizard";
    }
    void BoostSpeed(){
        BaseSpeed *= 2;
    }
}