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

    public List<Project> getAllByUser(int userId) {
        List<Project> list = new ArrayList<>();
        String sql = "SELECT * FROM yarn_projects WHERE user_id = ?";

        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Project(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        ProjectStatus.valueOf(rs.getString("status"))
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}