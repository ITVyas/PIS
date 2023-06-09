package MySqlDAOFactory;

import DAOFactory.IRoomRegisterDAO;
import SqlConnection.MySqlDataSource;
import entityObjects.RoomRegister;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MySqlRoomRegisterDAO implements IRoomRegisterDAO {
    @Override
    public void addRegister(RoomRegister roomRegister) throws Exception {
        MySqlDataSource msds = MySqlDataSource.getInstance();
        Connection con = msds.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO roomregister (room_number, status, start_date_time, end_date_time, client_id) VALUES (?,?,?,?,?);");
        stmt.setInt(1, roomRegister.getRoomNumber());
        stmt.setString(2, roomRegister.getStatus().toString());
        stmt.setTimestamp(3, roomRegister.getStartDateTime());
        stmt.setTimestamp(4, roomRegister.getEndDateTime());
        if(roomRegister.getClientId() != -1)
            stmt.setInt(5, roomRegister.getClientId());
        else
            stmt.setNull(5, java.sql.Types.INTEGER);

        stmt.execute();
        msds.releaseConnection(con);
    }
    @Override
    public ArrayList<RoomRegister> findRoomRegistersByRoomNumber(int number) throws Exception {
        MySqlDataSource msds = MySqlDataSource.getInstance();
        Connection con = msds.getConnection();
        CallableStatement stmt = con.prepareCall("SELECT * FROM roomregister WHERE room_number = ? ;");
        stmt.setInt(1, number);
        ResultSet resultSet = stmt.executeQuery();
        msds.releaseConnection(con);
        ArrayList<RoomRegister> list = new ArrayList<>();
        while (resultSet.next())
        {
            int client_id = -1;
            if(resultSet.getInt(6) != 0)
                client_id = resultSet.getInt(6);

            list.add(new RoomRegister(resultSet.getInt(1), resultSet.getInt(2), RoomRegister.RoomStatus.valueOf(resultSet.getString(3)),
                resultSet.getTimestamp(4),resultSet.getTimestamp(5), client_id));
        }

        return list;
    }
}
