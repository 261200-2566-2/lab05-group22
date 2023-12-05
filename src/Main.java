// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Job Eve = new Wizard("Eve", 1);
        Job Adam = new Warrior("Muhamed",1);
        Eve.ShowStat();
        Sword LightSaber = new Sword("LightSaber",1,99);
        Shield HolyShield = new Shield("Holy Shield",1,99);
        Armor Helmet = new Armor("Holy Helmet","Helmet",1, new int[]{10, 2, 15});
        Armor Chest = new Armor("Holy Chest","Chest",1, new int[]{10, 2, 20});
        Armor Pant = new Armor("Holy Pant","Pant",1, new int[]{10, 2, 10});
        Eve.Equip(LightSaber);
        Eve.Equip(HolyShield);
        Eve.Equip(Helmet);
        Eve.Equip(Chest);
        Eve.Equip(Pant);
        Eve.ShowStat();
        Eve.UnEquip(LightSaber);
        Eve.UnEquip(HolyShield);
        Eve.ShowStat();
//        LightSaber.LevelUp();
//        Eve.ShowStat();
//        Adam.ShowStat();

//        Adam.beAttacked(Eve.attack());
//        Adam.ShowStat();






    }
}