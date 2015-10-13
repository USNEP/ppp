package databbase;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = DbKeys.txns)
 public class History extends Model {
    // This is the unique id given by the server
    @Column(name = "remote_id", unique = true)
    public long remoteId;
    // This is a regular field
    @Column(name = "Name")
    public String name;

    // Make sure to have a default constructor for every ActiveAndroid model
    public History(){
        super();
    }

    // Used to return items from another table based on the foreign key
    public List<Types> items() {
        return getMany(Types.class, "History");
    }
    public static List<History> getAll(){
        return new Select().from(History.class).execute();
    }
}