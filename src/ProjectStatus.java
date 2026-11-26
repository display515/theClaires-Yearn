public enum ProjectStatus {
    CURRENTLY_WORKING_ON("Currently Working On"),
    PENDING("Pending"),
    DONE("Done");

    private final String label;

    ProjectStatus(String label) { this.label = label;}

    @Override
    public String toString() { return label; }
}