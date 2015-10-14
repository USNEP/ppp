package databbase;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = DbKeys.TYPES)
public class Types extends Model {
    public Types(){}
    @Column(name = DbKeys.HEAD)
    public String head;
    @Column(name = DbKeys.TYPE, unique = true)
    public String type;
    public Types( String head, String type){
        super();
        this.head = head;
        this.type = type;
    }
    public List<History> items() {
        return getMany(History.class, DbKeys.SUB_TYPE);
    }
    public static List<Types> getAll(){
        return new Select().from(Types.class).execute();
    }
    public static List<Types> getSubTypeByHead(String head) {
        return new Select()
                .from(Types.class)
                .where(DbKeys.HEAD + " = ?", head)
                 .execute();
    }
    public static Types getSubTypeByName(String type) {
        return new Select()
                .from(Types.class)
                .where(DbKeys.TYPE + " = ?", type)
                .executeSingle();
    }


}
