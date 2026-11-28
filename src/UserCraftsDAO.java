import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCraftsDAO {
    private final Database db;
    public UserCraftsDAO(Database db) { this.db = db; }

    public List<String> getAllByUser(int userId) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT * FROM user_crafts WHERE user_id = ?";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("craft"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public boolean insert(int userId, String craft) {
        String sql = "INSERT INTO user_crafts (user_id, craft) VALUES (?, ?)";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, craft);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}