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
    void Equip(Sword Thing);
    void Equip(Shield Thing);
    void Equip(Armor Thing);
    void UnEquip(Sword Thing);
    void UnEquip(Shield Thing);


}
interface Accessories_Setting{
    String name();
    int Level();
    void LevelUp();
}
class RPG_character implements Character_Setting{
    protected String name;
    protected int level;
    protected double MaxHp,MaxMana,Hp,Mana,Atk,Def,BaseSpeed,MaxSpeed;
    protected Sword sword;
    protected Shield shield;
    protected Armor[] armor;;
    public RPG_character(String name,int level){
        this.name = name;
        this.level = level;
        sword = new Sword();
        shield = new Shield();
        armor = new Armor[3];
        for(int i=0;i<3;i++){
            armor[i] = new Armor();
        }
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
    public void ShowStat(){
        System.out.println("----------------------------------------------------------------");
        System.out.println("Name : " + name);
        System.out.println("level : " + level);
    }
//    public static void main(String[] args) {
//        Job Eve = new Warrior("Eve", 1);
//        Eve.ShowStat();
//        Sword LightSaber = new Sword("LightSaber",1,99);
//        Shield HolyShield = new Shield("Holy Shield",1,99);
//        Armor Helmet = new Armor("Holy Helmet","Helmet",1, new int[]{10, 2, 15});
//        Armor Chest = new Armor("Holy Chest","Chest",1, new int[]{10, 2, 20});
//        Armor Pant = new Armor("Holy Pant","Pant",1, new int[]{10, 2, 10});
//        Eve.Equip(LightSaber);
//        Eve.Equip(HolyShield);
//        Eve.Equip(Helmet);
//        Eve.Equip(Chest);
//        Eve.Equip(Pant);
//        Eve.ShowStat();
//        Eve.UnEquip();
//        Eve.ShowStat();
//    }
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
        this.Def = 10+level;
        this.MaxHp = Stat[0]+10*level;
        this.Hp = MaxHp;
        this.MaxMana = Stat[1]+2*level;
        this.Mana = MaxMana;
        this.BaseSpeed =Stat[2];
        this.MaxSpeed = BaseSpeed*(0.1+0.03*level);
    }
    public  double attack(){
        if(!Objects.equals(sword.name, "None")){
            return sword.SwordDamage+Atk;
        }
        return Atk;
    }
    public void beAttacked(double damage){
        if(!Objects.equals(shield.name, "none")){
            damage /= (Def + shield.ShieldDefense);
        }else{
            damage /= Def;
        }
        Hp = Math.max((Hp - damage), 0);
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
        Def += level;
        MaxSpeed = BaseSpeed*(0.1+0.03*level);
        this.level++;
    }
    @Override
    public void Equip(Sword Thing) {
        if(Objects.equals(Thing.Type, "Weapon-Sword") && Objects.equals(sword.name, "None")){
            sword = Thing;
        }else{
            System.out.println("U cant equip " + Thing.name +"!");
        }
    }
    public void Equip(Shield Thing) {
        if(Objects.equals(Thing.Type, "Weapon-Shield") && Objects.equals(shield.name, "None")){
            shield = Thing;
        }else{
            System.out.println("U cant equip " + Thing.name +"!");
        }
    }
    public void Equip(Armor Thing) {
        switch (Thing.Type){
            case "Helmet":
                if(Objects.equals(armor[0].name, "None")){
                    armor[0] = Thing;
                    UpdateStat("Plus",armor[0].Stat);
                }else{System.out.println("U cant equip " + Thing.name +"!");}
                break;
            case "Chest" :
                if(Objects.equals(armor[1].name, "None")){
                    armor[1] = Thing;
                    UpdateStat("Plus",armor[1].Stat);
                }else{System.out.println("U cant equip " + Thing.name +"!");}
                break;
            case "Pant" :
                if(Objects.equals(armor[2].name, "None")){
                    armor[2] = Thing;
                    UpdateStat("Plus",armor[2].Stat);
                }else{System.out.println("U cant equip " + Thing.name +"!");}
                break;
        }
    }

    public void UnEquip(Sword Thing) {
        if (Objects.equals(sword.name, Thing.name)) {
            sword = new Sword();
        } else {
            System.out.println("You don't have " + Thing.name + " equipped!");
        }
    }

    @Override
    public void UnEquip(Shield Thing) {
        if (Objects.equals(shield.name, Thing.name)) {
            shield = new Shield();
        } else {
            System.out.println("You don't have " + Thing.name + " equipped!");
        }
    }


    public void UpdateStat(String A, int[] stat){
        if (Objects.equals(A, "Plus")){
            if(Hp == MaxHp){
                Hp += stat[0];
            }
            MaxHp += stat[0];
            MaxSpeed -= stat[1];
            Def += stat[2];
        }else if(Objects.equals(A, "minus")){
            if(Hp == MaxHp){
                Hp -= stat[0];
            }
            MaxHp -= stat[0];
            MaxSpeed += stat[1];
            Def -= stat[2];
        }
    }
    public void UnEquipAllArmor(){
//        sword = new Sword();
//        shield = new Shield();
        UpdateStat("minus",armor[0].Stat);
        armor[0] = new Armor();
        UpdateStat("minus",armor[1].Stat);
        armor[1] = new Armor();
        UpdateStat("minus",armor[2].Stat);
        armor[2] = new Armor();
    }
    public void ShowStat(){
        System.out.println("----------------------------------------------------------------");
        System.out.println("Name : " + name + "          level : " + level);
        System.out.println("Hp : " + Hp + "/" + MaxHp + "     Mp : " + Mana + "/" + MaxMana + "     Def : " + Def);
        System.out.println("Job : " + Job_type + "     Atk : " + attack());
        if(!Objects.equals(sword.name, "None")){System.out.println(sword.Type + " : " + sword.name + " lv." + sword.Level + " Damage :" + sword.SwordDamage); }
        if(!Objects.equals(shield.name, "None")){System.out.println(shield.Type + " : " + shield.name + " lv." + shield.Level + " Defense :" + shield.ShieldDefense); }
        if(!Objects.equals(armor[0].name, "None")){System.out.println(armor[0].Type + " : " + armor[0].name + " lv." + armor[0].Level);}
        if(!Objects.equals(armor[1].name, "None")){System.out.println(armor[1].Type + " : " + armor[1].name + " lv." + armor[1].Level);}
        if(!Objects.equals(armor[2].name, "None")){System.out.println(armor[2].Type + " : " + armor[2].name + " lv." + armor[2].Level);}

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
        this.Def += level;

    }
    public void BoostSpeed(){
        if(Mana >= 20){
            BaseSpeed *= 2;
            Mana -= 20;
        }else{
            System.out.println("Not Enough Mp");
        }
    }
}
class Accessories implements Accessories_Setting{
    protected String name;
    protected String Type;
    protected int Level;
    public Accessories() {
        this.name = "None";
        this.Level = 0;
        this.Type = "None";
    }
    public Accessories(String name,int Level){
        this.name = name;
        this.Level = Level;
        this.Type = "None";
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
    protected int Stat;
    public Weapon() {
        super();
        this.Type = "Weapon";
    }
    public Weapon(String name, int Level,int Stat) {
        super(name,Level);
        this.Type = "Weapon";
        this.Stat = Stat;
    }
}
class Sword extends Weapon{
    private int BaseDamage;
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
}
class Shield extends Weapon{
    private int BaseDefense;
    protected double ShieldDefense;
    public Shield() {
        super();
        this.Type = "Weapon-Shield";
    }
    public Shield(String name, int Level, int Stat) {
        super(name,Level,Stat);
        BaseDefense = Stat;
        ShieldDefense = BaseDefense*(1+0.05*Level);
        this.Type = "Weapon-Shield";
    }
    @Override
    public void LevelUp() {
        super.LevelUp();
        ShieldDefense = BaseDefense*(1+0.05*Level);
    }
}
class Armor extends Accessories{
    protected int[] Stat;
    public Armor() {
        super();
        this.Type = "Armor";
    }
    public Armor(String name,String type,int Level,int[] Stat) {
        super(name,Level);
        switch (type){
            case "Helmet" : this.Type = "Helmet";break;
            case "Chest" : this.Type = "Chest";break;
            case "Pant" : this.Type = "Pant";break;
        }
        this.Stat = Stat;
    }
}