package cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods;

import java.util.Random;

public class TradeGood {

    private String name;
    private int basePrice;
    private int mltp;
    private int mtlu;
    private int ttp;
    private int ipl;
    private int var;
    private String cr;
    private String er;
    private int mtl;
    private int mth;

    private int totalPrice;
    private int changedPrice;

    public TradeGood(String name, int basePrice, int mltp, int mtlu, int ttp,
                     int ipl, int var, String cr, String er, int mtl, int mth) {

        this.name = name;
        this.basePrice = basePrice;
        this.mltp = mltp;
        this.mtlu = mtlu;
        this.ttp = ttp;
        this.ipl = ipl;
        this.var = var;
        this.cr = cr;
        this.er = er;
        this.mtl = mtl;
        this.mth = mth;

        totalPrice = basePrice;
        changedPrice = totalPrice;
    }

    public String getName() { return name; }

    public int getBasePrice() { return basePrice; }

    public int getMltp() { return mltp; }

    public int getMtlu() { return mtlu; }

    public int getTtp() { return ttp; }

    public int getIpl() { return ipl; }

    public int getVar() { return var; }

    public String getCr() { return cr; }

    public String getEr() { return er; }

    public int getMtl() { return mtl; }

    public int getMth() { return mth; }

    public int getTotalPrice() { return totalPrice; }

    public int getChangedPrice() { return changedPrice; }

    public void setTotalPrice(int totalPrice) {

        Random random = new Random();

        this.totalPrice = totalPrice;
//        this.changedPrice = totalPrice + (this.getIpl() * planet techlevel)
//                + (totalPrice * random.nextInt(var + 1));
    }


}
