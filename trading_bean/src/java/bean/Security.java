package bean;

public class Security {
    
    private String type;
    private String name;

    public Security() {}

    // Setters

    public void setType(String s) {
	   this.type = s;
    }

    public void setName(String s) {
	   this.name = s;
    }

    // Getters

    public String getType() {
	   return type;
    }

    public String getName() {
	   return name;
    }

}
