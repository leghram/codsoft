package src.repository;

public class Course {
    
    private String name;
    private int mark;
    private int id;
    private static int idSubjet=0;

    public Course( String name, int mark){
        this.name = name;
        this.mark = mark;
        this.id = idSubjet + 1;
    }
    public int getMark() {
        return mark;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
}
