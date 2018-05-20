package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Settings extends Model {
    @Id
    public String id;
    public String value;

    public static Finder<Integer, Settings> find = new Finder<>(Settings.class);

    public static String get(String key) {
        Settings setting =  find.query().where().eq("id", key).findOne();
        if (setting == null) {
            return null;
        } else {
            return setting.value;
        }
    }

    public static void set(String key, String value) {
        Settings setting =  find.query().where().eq("id", key).findOne();
        if (setting == null) {
            setting = new Settings();
            setting.id = key;
        }
        setting.value = value;
        setting.save();
    }

    public static String getMessage(){
       if(get("message") == null){
           set("message","公告暂无资料");
           return getMessage();
       }
       else{
           return get("message");
       }
    }
}
