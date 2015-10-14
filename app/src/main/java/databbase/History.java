package databbase;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = DbKeys.TXNS)
 public class History extends Model {

    public History(){
        super();
    }
    @Column(name = DbKeys.TYPE)
    public String type;
    @Column(name = DbKeys.CB)
    public boolean cashOrBank;
    @Column(name = DbKeys.AMT)
    public double amount;
    @Column(name = DbKeys.DCPN)
    public String dcpn;
    @Column(name = DbKeys.DATE)
    public String date;
    @Column(name = DbKeys.SUB_TYPE, onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Types subType;

    public History(String type, boolean cashOrBank, double amount,String dcpn, String date,Types subType){
        super();
        this.type = type;
        this.cashOrBank = cashOrBank;
        this.dcpn = dcpn;
        this.subType = subType;
        this.date = date;
        this.amount = amount;
    }

}