import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class YarnProjectsDAO {
    private final Database db;

    public YarnProjectsDAO(Database db) {
        this.db = db;
    }

    public List<Material> getAllByUser(int userId) {
        List<Material> list = new ArrayList<>();
        String sql = "SELECT * FROM yarn_projects WHERE user_id = ?";

        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Material(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("amount_yarn"),
                        rs.getInt("user_id")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}