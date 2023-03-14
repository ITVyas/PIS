package MySqlDAOFactory;

import DAOFactory.IClientRequestDAO;
import SqlConnection.MySqlDataSource;
import entityObjects.Client;
import entityObjects.ClientRequest;

import java.sql.*;
import java.util.ArrayList;

public class MySqlClientRequestDAO implements IClientRequestDAO {
    @Override
    public void addRequest(ClientRequest request) throws Exception {
        MySqlDataSource msds = MySqlDataSource.getInstance();;
        Connection con = msds.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO requests (client_id, room_number, arrival_date, departure_date, paid, status) " +
                        "VALUES (?,?,?,?);");
        stmt.setInt(1, request.getClientId());
        stmt.setInt(2, request.getRoomNumber());
        stmt.setDate(3, request.getArrivalDate());
        stmt.setDate(4, request.getDepartureDate());
        stmt.setBoolean(5, request.getIsPaid());
        stmt.setString(7, request.getStatus().toString());
        stmt.execute();
        msds.releaseConnection(con);
    }
    @Override
    public ArrayList<ClientRequest> findAllRequestsByClientId(int id) throws Exception {
        MySqlDataSource msds = MySqlDataSource.getInstance();;
        Connection con = msds.getConnection();
        CallableStatement stmt = con.prepareCall("SELECT * FROM requests WHERE client_id = ? ;");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();
        msds.releaseConnection(con);
        ArrayList<ClientRequest> list = new ArrayList<>();
        while(resultSet.next())
            list.add(new ClientRequest(resultSet.getInt(1), resultSet.getInt(2),resultSet.getInt(3),
                    resultSet.getDate(4), resultSet.getDate(5), resultSet.getBoolean(6), ClientRequest.RequestStatus.valueOf(resultSet.getString(7))));
        return list;
    }
    @Override
    public void updateRequestStatus(ClientRequest request, ClientRequest.RequestStatus newStatus) throws Exception {
        MySqlDataSource msds = MySqlDataSource.getInstance();;
        Connection con = msds.getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE requests SET status = ? WHERE id = ?;");
        stmt.setString(1, newStatus.toString());
        stmt.setInt(2, request.getId());
        stmt.execute();
        msds.releaseConnection(con);
        request.setStatus(newStatus);
    }
    @Override
    public void removeRequestById(int id) throws Exception {
        MySqlDataSource msds = MySqlDataSource.getInstance();;
        Connection con = msds.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM requests WHERE id = ?;");
        stmt.setInt(1, id);
        stmt.execute();
        msds.releaseConnection(con);
    }
}
