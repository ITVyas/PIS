package MySqlDAOFactory;

import DAOFactory.IAdminDAO;
import SqlConnection.MySqlDataSource;
import entityObjects.Administrator;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlAdminDAO implements IAdminDAO {

    @Override
    public void addAdmin(Administrator admin) throws Exception {
        MySqlDataSource msds = MySqlDataSource.getInstance();
        Connection con = msds.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO Administrators (name, surname, phone, email, password) VALUES (?,?,?,?,?);");
        stmt.setString(1, admin.getName());
        stmt.setString(2, admin.getSurname());
        stmt.setString(3, admin.getPhone());
        stmt.setString(4, admin.getEmail());
        stmt.setString(5, admin.getPassword());
        stmt.execute();
        msds.releaseConnection(con);
    }

    @Override
    public Administrator findAdminByEmail(String email) throws Exception {
        MySqlDataSource msds = MySqlDataSource.getInstance();
        Connection con = msds.getConnection();
        CallableStatement stmt = con.prepareCall("SELECT * FROM Administrators WHERE email = ?;");
        stmt.setString(1, email);
        ResultSet resultSet = stmt.executeQuery();
        msds.releaseConnection(con);
        if(resultSet.next() == false)
            return null;
        else
            return new Administrator(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
    }

    private void updateAdminFields(Administrator admin, String[] params) {
        admin.setName(params[0]);
        admin.setSurname(params[1]);
        admin.setPhone(params[2]);
        admin.setEmail(params[3]);
        admin.setPassword(params[4]);
    }
    @Override
    public void updateAdmin(Administrator admin, String[] params) throws Exception {
        if(params.length != 5)
            throw new Exception("Wrong params amount. For updating admin must be 5 parameters.");
        updateAdminFields(admin, params);
        MySqlDataSource msds = MySqlDataSource.getInstance();
        Connection con = msds.getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE Administrators " +
                "SET name = ?, surname = ?, phone = ?, email = ?, password = ? " +
                "WHERE id = ? ;");
        stmt.setString(1, params[0]);
        stmt.setString(2, params[1]);
        stmt.setString(3, params[2]);
        stmt.setString(4, params[3]);
        stmt.setString(5, params[4]);
        stmt.setInt(6, admin.getId());
        stmt.execute();
        msds.releaseConnection(con);
    }
    @Override
    public void removeAdmin(int id) throws Exception {
        MySqlDataSource msds = MySqlDataSource.getInstance();;
        Connection con = msds.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM Administrators WHERE id = ?;");
        stmt.setInt(1, id);
        stmt.execute();
        msds.releaseConnection(con);
    }
}
