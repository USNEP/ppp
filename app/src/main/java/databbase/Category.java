package databbase;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Categories")
 public class Category extends Model {
    // This is the unique id given by the server
    @Column(name = "remote_id", unique = true)
    public long remoteId;
    // This is a regular field
    @Column(name = "Name")
    public String name;

    // Make sure to have a default constructor for every ActiveAndroid model
    public Category(){
        super();
    }

    // Used to return items from another table based on the foreign key
    public List<Items> items() {
        return getMany(Items.class, "Category");
    }
    public static List<Category> getAll(){
        return new Select().from(Category.class).execute();
    }
}