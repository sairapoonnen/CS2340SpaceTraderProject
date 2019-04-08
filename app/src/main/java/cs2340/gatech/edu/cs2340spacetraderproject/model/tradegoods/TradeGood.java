package cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods;

import java.io.Serializable;
import java.util.Random;

public class TradeGood implements Serializable {

    private String name;
    private int basePrice;
    private int mtlp;
    private int mtlu;
    private int ttp;
    private int ipl;
    private int var;
    private String ie;
    private String cr;
    private String er;
    private int mtl;
    private int mth;

    private int totalPrice;
    private int changedPrice;

    public TradeGood(String name, int basePrice, int mtlp, int mtlu, int ttp, int ipl,
                     int var, String ie, String cr, String er, int mtl, int mth) {

        this.name = name;
        this.basePrice = basePrice;
        this.mtlp = mtlp;
        this.mtlu = mtlu;
        this.ttp = ttp;
        this.ipl = ipl;
        this.var = var;
        this.ie = ie;
        this.cr = cr;
        this.er = er;
        this.mtl = mtl;
        this.mth = mth;

        totalPrice = basePrice;
        //changedPrice = totalPrice;
    }

    public String getName() { return name; }

    public int getBasePrice() { return basePrice; }

    public int getMTLP() { return mtlp; }

    public int getMTLU() { return mtlu; }

    public int getTTP() { return ttp; }

    public int getIPL() { return ipl; }

    public int getVar() { return var; }

    public String getIE() { return ie; }

    public String getCR() { return cr; }

    public String getER() { return er; }

    public int getMTL() { return mtl; }

    public int getMTH() { return mth; }

    public int getTotalPrice() { return totalPrice; }

    public int getChangedPrice() { return changedPrice; }

    public void setTotalPrice(int totalPrice) {

        Random random = new Random();

        this.totalPrice = totalPrice;
//        this.changedPrice = totalPrice + (this.getIpl() * planet techlevel)
//                + (totalPrice * random.nextInt(var + 1));
    }

    public int[] getInfo() {
        int[] info = new int[]{basePrice, mtlp, mtlu, ttp, ipl, var};

        return info;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        TradeGood item = (TradeGood) obj;

        return ((TradeGood) obj).getName().equals(this.name);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + basePrice;
        return result;
    }

}
