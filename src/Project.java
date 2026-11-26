public class Project {
    private final int id;
    private final int userId;
    private String name;
    private String description;
    private ProjectStatus status;

    public Project(int id, int userId, String name, String description, ProjectStatus status){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getId()                { return id; }
    public int getUserId()            { return userId; }
    public String getName()           { return name; }
    public String getDescription()    { return description; }
    public ProjectStatus getStatus()  { return status; }

    public void updateStatus(ProjectStatus status) { this.status = status; }
}
