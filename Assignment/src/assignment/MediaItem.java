
package assignment;

public class MediaItem {
    // Information hiding: private fields
    private int id;
    private String name;
    private int ageRestriction;

    // Constructors (default + parameterized)
    public MediaItem() {}

    public MediaItem(int id, String name, int ageRestriction) {
        this.id = id;
        this.name = name;
        this.ageRestriction = ageRestriction;
    } //(Farrell, 2025)

    // Getters / Setters (encapsulation)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name == null ? "" : name; }
    public void setName(String name) { this.name = name; }

    public int getAgeRestriction() { return ageRestriction; }
    public void setAgeRestriction(int ageRestriction) { this.ageRestriction = ageRestriction; }

    // Common toString component
    protected String baseReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("SERIES ID: ").append(id).append("\n");
        sb.append("SERIES NAME: ").append(getName()).append("\n");
        sb.append("SERIES AGE RESTRICTION: ").append(ageRestriction).append("\n");
        return sb.toString();
    }
}
//Reference List
//Farrell, J.2023.Java Programming.Boston: Cengage.
//Deepseek, 2024. Response to query about coding. [online] Available at: https://chat.deepseek.com/ [Accessed 2 September 2025]

