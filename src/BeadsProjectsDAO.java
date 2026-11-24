import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeadsProjectsDAO {
    private final Database db;
    public BeadsProjectsDAO(Database db) { this.db = db; }

    public List<BeadsProject> getAllByUser(int userId) {
        List<BeadsProject> list = new ArrayList<>();
        String sql = "SELECT * FROM beads_projects WHERE user_id = ?";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new BeadsProject(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("amount_bead"),
                        rs.getInt("user_id")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public boolean insert(String name, int amountBead, int userId) {
        String sql = "INSERT INTO beads_projects (name, amount_bead, user_id) VALUES (?, ?, ?)";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, amountBead);
            ps.setInt(3, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}