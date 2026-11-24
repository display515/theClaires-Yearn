import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class YarnProjectColorsDAO {
    private final Database db;
    public YarnProjectColorsDAO(Database db) { this.db = db; }

    public List<YarnProjectColor> getAllByProject(int projectId) {
        List<YarnProjectColor> list = new ArrayList<>();
        String sql = "SELECT * FROM yarn_projects_colors WHERE project_id = ?";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, projectId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new YarnProjectColor(
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
        String sql = "INSERT INTO yarn_projects_colors (project_id, color, grams) VALUES (?, ?, ?)";
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