package databbase;

import global.Global;

/**
 * Created by ashok on 11/10/15.
 */
public class Status {

    private double cash;
    private double bank;
    private double loan;
    private double investment;
public Status(){}
    public Status(double cash, double bank, double loan, double investment) {
        this.cash = cash;
        this.bank = bank;
        this.loan = loan;
        this.investment = investment;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getBank() {
        return bank;
    }

    public void setBank(double bank) {
        this.bank = bank;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }

    public double getInvestment() {
        return investment;
    }

    public void setInvestment(double investment) {
        this.investment = investment;
    }


    public static Status getStatus(){
        Status st=new Status();
        double b2c=Global.global.getDb().getStatus(Querys.getBankToCash())-Global.global.getDb().getStatus(Querys.getCashToBank());
        System.out.println("cash query starts from here.........................................................................");
        st.setCash(Global.global.getDb().getStatus(Querys.getCashInQry(DbKeys.CB)) - Global.global.getDb().getStatus(Querys.getCashOutQry(DbKeys.CB))+b2c);
        System.out.println("bank query starts from here.........................................................................");
        st.setBank(Global.global.getDb().getStatus(Querys.getCashInQry(DbKeys.BANK)) - Global.global.getDb().getStatus(Querys.getCashOutQry(DbKeys.BANK))-b2c);
        st.setLoan(Global.global.getDb().getStatus(Querys.getLoanInQry()) - Global.global.getDb().getStatus(Querys.getLoanOutQry()));
        st.setInvestment(Global.global.getDb().getStatus(Querys.getInvstQry()));
        return st;
    }
}
