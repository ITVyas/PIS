package SqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlDataSource {
    private static MySqlDataSource instance = null;
    private final ArrayList<Connection> connectionPool = new ArrayList<>();
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private final String URL = "jdbc:mysql://localhost:3306/hoteldatabase";

    private MySqlDataSource (int startPoolSize) throws Exception
    {
        if(startPoolSize < 0)
            throw new Exception("Minimal start connection pool size is 0");

        for(int i = 0; i < startPoolSize; i++)
            connectionPool.add(DriverManager.getConnection(URL, USERNAME, PASSWORD));
    }

    public static MySqlDataSource getInstance() throws Exception
    {
        if(instance == null)
            instance = new MySqlDataSource(1);
        return instance;
    }

    public int getPoolSize()
    {
        return connectionPool.size();
    }

    public Connection getConnection() throws SQLException
    {
        if(connectionPool.size() == 0)
            connectionPool.add(DriverManager.getConnection(URL, USERNAME, PASSWORD));
        Connection con = connectionPool.get(0);
        connectionPool.remove(0);
        return con;
    }
    public void releaseConnection(Connection con)
    {
        connectionPool.add(con);
    }
    public void closePoolConections() throws SQLException
    {
        while(connectionPool.size() > 0)
        {
            connectionPool.get(0).close();
            connectionPool.remove(0);
        }
    }
}
