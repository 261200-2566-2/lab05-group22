public class Shield {
    private final int BaseDefense;
    private int Level;
    private double ShieldDefense;

    public Shield(int _BaseDefense,int Level){
        BaseDefense =  _BaseDefense;
        this.Level = Level;
        ShieldDefense = BaseDefense*(1+0.05*Level);
    }
    public void Levelup(){
        Level++;
        ShieldDefense = BaseDefense*(1+0.05*Level);
    }
    public int getLevel() {
        return Level;
    }
    public int getBaseDefense() {
        return BaseDefense;
    }
    public double getShieldDefense() {
        return ShieldDefense;
    }
}