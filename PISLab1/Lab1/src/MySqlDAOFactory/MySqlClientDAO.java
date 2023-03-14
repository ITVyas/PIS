package MySqlDAOFactory;

import DAOFactory.IClientDAO;
import SqlConnection.MySqlDataSource;
import entityObjects.Client;

import java.sql.*;

public class MySqlClientDAO implements IClientDAO {
    @Override
    public void addClient(Client client) throws Exception {
        MySqlDataSource msds = MySqlDataSource.getInstance();;
        Connection con = msds.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO clients (name, surname, phone, email) VALUES (?,?,?,?);");
        stmt.setString(1, client.getName());
        stmt.setString(2, client.getSurname());
        stmt.setString(3, client.getPhone());
        stmt.setString(4, client.getEmail());
        stmt.execute();
        msds.releaseConnection(con);
    }

    private Client findClientByStatement(String statementString, String param) throws Exception
    {
        MySqlDataSource msds = MySqlDataSource.getInstance();
        Connection con = msds.getConnection();
        CallableStatement stmt = con.prepareCall(statementString);
        stmt.setString(1, param);
        ResultSet resultSet = stmt.executeQuery();
        msds.releaseConnection(con);
        if(resultSet.next() == false)
            return null;
        else
            return new Client(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5));
    }
    @Override
    public Client findClientByEmail(String email) throws Exception
    {
        return findClientByStatement("SELECT * FROM clients WHERE email = ? ;", email);
    }

    @Override
    public Client findClientByPhone(String phone) throws Exception
    {
        return findClientByStatement("SELECT * FROM clients WHERE phone = ? ;", phone);
    }

    private void updateClientFields(Client client, String[] params) throws Exception
    {
        if(params.length != 4)
            throw new Exception("Client params amount must be 4. [Name, Surname, Phone, Email]");

        client.setName(params[0]);
        client.setSurname(params[1]);
        client.setPhone(params[2]);
        client.setEmail(params[3]);
    }

    @Override
    // Params: [name, surname, phone, email]
    public void updateClient(Client client, String[] params) throws Exception
    {
        updateClientFields(client, params);

        MySqlDataSource msds = MySqlDataSource.getInstance();;
        Connection con = msds.getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE clients " +
                "SET name = ?, surname = ?, phone = ?, email = ?" +
                "WHERE id = ?");
        stmt.setString(1, client.getName());
        stmt.setString(2, client.getSurname());
        stmt.setString(3, client.getPhone());
        stmt.setString(4, client.getEmail());
        stmt.setInt(5, client.getId());
        stmt.execute();
        msds.releaseConnection(con);
    }

    @Override
    public void removeClient(int id) throws Exception
    {
        MySqlDataSource msds = MySqlDataSource.getInstance();;
        Connection con = msds.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM clients WHERE id = ?;");
        stmt.setInt(1, id);
        stmt.execute();
        msds.releaseConnection(con);
    }
}
