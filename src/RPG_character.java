import java.util.Objects;

interface Character_Setting{
     String name();
     int level();
     void LevelUp();
     void ShowStat();
}
interface Job_Setting extends Character_Setting {
    String Job_type();
    double[] Stat();
    void SetStat();
    void Equip(Accessories Thing);
}
class RPG_character implements Character_Setting{
    protected String name;
    protected int level;
    protected double MaxHp,MaxMana,Hp,Mana,Atk,BaseSpeed,MaxSpeed,current_Speed;
    protected Accessories[] Equipment = new Accessories[8];

    public RPG_character(String name,int level){
        this.name = name;
        this.level = level;
        Equipment[0] = new Sword();
        Equipment[1] = new Accessories();
        Equipment[2] = new Accessories();
        Equipment[3] = new Accessories();
        Equipment[4] = new Accessories();
        Equipment[5] = new Accessories();
        Equipment[6] = new Accessories();
        Equipment[7] = new Accessories();
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
    public void LevelUp() {
        level++;
    }

    public  double attack(){
        return Atk;
    }
    public void beAttacked(double damage){
        Hp = Math.max((Hp - damage), 0);
    }
    public void ShowStat(){
        System.out.println("----------------------------------------------------------------");
        System.out.println("Name : " + name);
        System.out.println("level : " + level);
    }
    public static void main(String[] args) {

        Job Adam = new Wizard("Adam", 1);
        Job Eve = new Warrior("Eve", 1);
        Job A = new Job("A",1);
        Adam.ShowStat();
        Eve.ShowStat();
        A.ShowStat();
        Sword LightSaber = new Sword("LightSaber",1,99);
        Adam.Equip(LightSaber);
        LightSaber.LevelUp();
        Adam.ShowStat();
    }
}
class Job extends RPG_character implements Job_Setting{
    protected double[] Stat;
    protected String Job_type;
    public Job(String name, int level) {
        super(name, level);
        Job_type = "None";
        this.Stat = new double[]{50, 23, 6};
        SetStat();
    }
    @Override
    public String Job_type() {
        return Job_type;
    }
    @Override
    public double[] Stat() {
        return Stat;
    }
    @Override
    public void SetStat() {
        this.Atk  = 10+2*level;
        this.MaxHp = Stat[0]+10*level;
        this.Hp = MaxHp;
        this.MaxMana = Stat[1]+2*level;
        this.Mana = MaxMana;
        this.BaseSpeed =Stat[2];
        this.MaxSpeed = BaseSpeed*(0.1+0.03*level);
    }

    @Override
    public void LevelUp() {
        if(Hp == MaxHp){
            Hp += 10*level;
        }
        if(Mana == MaxMana){
            Mana += 2*level;
        }
        this.MaxHp +=10*level;
        this.MaxMana += 2*level;
        Atk += 4*level;
        MaxSpeed = BaseSpeed*(0.1+0.03*level);
    }
    @Override
    public void Equip(Accessories Thing) {
        if(Objects.equals(Thing.Type, "Weapon-Sword") && Objects.equals(Equipment[0].name, "None")){
            Sword A = new Sword(Thing.name,Thing.Level,Thing.Stat);
            Equipment[0] = A;
            Atk += A.getSwordDamage();
        }
    }
    public void ShowStat(){
        System.out.println("----------------------------------------------------------------");
        System.out.println("Name : " + name + "          level : " + level);
        System.out.println("Hp : " + Hp + "/" + MaxHp + "     Mp : " + Mana + "/" + MaxMana);
        System.out.println("Job : " + Job_type + "     Atk : " + Atk);
        System.out.println(Equipment[0].Type + " : " + Equipment[0].name);
        System.out.println(Equipment[1].Type + " : " + Equipment[1].name);
    }
}
class Wizard extends Job{
    public Wizard(String name, int level) {
        super(name,level);
        this.Job_type = "Wizard";
        this.Stat = new double[]{22,46, 4};
        SetStat();
    }

    @Override
    public void SetStat() {
        super.SetStat();
        this.MaxHp = Stat[0]+8*level;
        this.Hp = MaxHp;
        this.MaxMana = Stat[1]+4*level;
        this.Mana = MaxMana;
    }

    @Override
    public void LevelUp() {
        super.LevelUp();
        if(Hp == MaxHp){
            Hp -= 3*level;
        }
        if(Mana == MaxMana){
            Mana += 4*level;
        }
        this.MaxHp -= 3*level;
        this.MaxMana += 4*level;
    }

    public void Healing(){
        if(Mana >= 30){
            if((Hp+20) >= MaxHp){
                Hp = MaxHp;
            }else{
                Hp += 20;
            }
        }else{
            System.out.println("Not Enough Mp");
        }
    }
}
class Warrior extends Job{
    public Warrior(String name, int level) {
        super(name,level);
        this.Job_type = "Warrior";
        this.Stat = new double[]{68,14, 7};
        SetStat();
    }

    @Override
    public void SetStat() {
        super.SetStat();
        this.MaxHp = Stat[0]+12*level;
        this.Hp = MaxHp;
        this.MaxMana = Stat[1]+level;
        this.Mana = MaxMana;
    }

    @Override
    public void LevelUp() {
        super.LevelUp();
        if(Hp == MaxHp){
            Hp += 12*level;
        }
        if(Mana == MaxMana){
            Mana += level;
        }
        this.MaxHp += 12*level;
        this.MaxMana += level;
    }

    public void BoostSpeed(){
        if(Mana >= 20){
            BaseSpeed *= 2;
        }else{
            System.out.println("Not Enough Mp");
        }
    }
}
interface Accessories_Setting{
    String name();
    int Level();
    void LevelUp();
}
class Accessories implements Accessories_Setting{
    protected String name;
    protected String Type;
    protected int Level;
    protected int Stat;
    public Accessories() {
        this.name = "None";
        this.Level = 0;
        this.Stat = 0;
        this.Type = "None";
    }
    public Accessories(String name,int Level,int Stat){
        this.name = name;
        this.Level = Level;
        this.Type = "None";
        this.Stat = Stat;
    }
    @Override
    public String name() {
        return this.name;
    }
    @Override
    public int Level() {
        return this.Level;
    }
    @Override
    public void LevelUp() {
        Level++;
    }
}
class Weapon extends Accessories{
    public Weapon() {
        super();
        this.Type = "Weapon";
    }
    public Weapon(String name, int Level,int Stat) {
        super(name,Level,Stat);
        this.Type = "Weapon";
    }
}
class Sword extends Weapon{
    private  int BaseDamage;
    protected double SwordDamage;
    public Sword() {
        super();
        this.Type = "Weapon-Sword";
    }
    public Sword(String name, int Level, int Stat) {
        super(name,Level,Stat);
        BaseDamage = Stat;
        SwordDamage = BaseDamage*(1+0.1*Level);
        this.Type = "Weapon-Sword";
    }
    @Override
    public void LevelUp() {
        super.LevelUp();
        SwordDamage = BaseDamage*(1+0.1*Level);
    }
    public double getSwordDamage() {
        return SwordDamage;
    }
}