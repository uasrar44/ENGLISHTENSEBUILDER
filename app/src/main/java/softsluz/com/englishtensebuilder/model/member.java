package softsluz.com.englishtensebuilder.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;

/**
 * Created by a_bas on 1/2/2018.
 */
@IgnoreExtraProperties
public class member {
    private String id;
    private String name;


    public member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public member(){}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
