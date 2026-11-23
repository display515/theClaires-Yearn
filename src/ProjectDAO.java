import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProjectDAO {
    private final String url;

    public ProjectDAO(String url){ this.url = url; }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }
}