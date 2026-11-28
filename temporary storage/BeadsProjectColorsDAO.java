import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeadsProjectColorsDAO {
    private final Database db;
    public BeadsProjectColorsDAO(Database db) { this.db = db; }

    public List<BeadsProjectColor> getAllByProject(int projectId) {
        List<BeadsProjectColor> list = new ArrayList<>();
        String sql = "SELECT * FROM beads_projects_colors WHERE project_id = ?";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, projectId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new BeadsProjectColor(
                        rs.getInt("project_id"),
                        rs.getString("color"),
                        rs.getInt("grams")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public boolean insert(int projectId, String color, int grams) {
        String sql = "INSERT INTO beads_projects_colors (project_id, color, grams) VALUES (?, ?, ?)";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, projectId);
            ps.setString(2, color);
            ps.setInt(3, grams);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}