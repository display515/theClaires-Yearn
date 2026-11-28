import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInventoryDAO {
    private final Database db;

    public UserInventoryDAO(Database db) { 
        this.db = db;  
    }

    public List<Material> getAllByUser(int userId) {
        List<Material> list = new ArrayList<>();
        String sql = "SELECT * FROM user_inventory WHERE user_id = ?";

        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("item_name");
                    String color = rs.getString("item_color");
                    int grams = rs.getInt("grams");
                    String type = rs.getString("inventory_type");

                    if (type.equals("crocheting")){
                        list.add(new Yarn(id, name, color, grams));
                    } else if (type.equals("beading")){
                        list.add(new Bead(id, name, color, grams));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
