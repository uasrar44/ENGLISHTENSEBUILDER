package softsluz.com.englishtensebuilder.model;

/**
 * Created by a_bas on 1/9/2018.
 */

public class lesson {
    private String id,type,title,description, example;

    public lesson() {
    }

    public lesson(String id,String type,String title, String description, String example) {
        this.id=id;
        this.title = title;
        this.description = description;
        this.example = example;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

}
