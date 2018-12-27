package softsluz.com.englishtensebuilder.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;

/**
 * Created by a_bas on 1/2/2018.
 */
@IgnoreExtraProperties
public class Classes {
    private String ClassName;
    private String creator;


    public Classes(){}

    public Classes(String name, String creator) {
        this.ClassName = name;
        this.creator = creator;

    }

    public HashMap<String,String> getHash(){

        HashMap <String,String> h=new HashMap<String, String>();
        h.put("ClassName",ClassName);
        h.put("admin",creator);
        return h;
    }

}
