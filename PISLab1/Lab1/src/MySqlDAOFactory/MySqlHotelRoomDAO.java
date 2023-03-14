package MySqlDAOFactory;

import DAOFactory.IHotelRoomDAO;
import SqlConnection.MySqlDataSource;
import entityObjects.HotelRoom;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MySqlHotelRoomDAO implements IHotelRoomDAO {
    @Override
    public void upadteRoomByNumber(int number, HotelRoom.RoomType type, boolean miniBar, boolean tv, boolean dryer, float price, int singleBedsAmount, int doubleBedsAmount) throws Exception {
        MySqlDataSource msds = MySqlDataSource.getInstance();
        Connection con = msds.getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE hotelrooms " +
                "SET type = ?, mini_bar = ?, tv = ?, dryer = ?, price = ?, single_beds_amount = ?, double_beds_amount = ? " +
                "WHERE number = ?;");
        stmt.setString(1, type.toString());
        stmt.setBoolean(2, miniBar);
        stmt.setBoolean(3, tv);
        stmt.setBoolean(4, dryer);
        stmt.setFloat(5, price);
        stmt.setInt(6, singleBedsAmount);
        stmt.setInt(7, doubleBedsAmount);
        stmt.setInt(8, number);
        stmt.execute();
        msds.releaseConnection(con);
    }
    @Override
    public ArrayList<HotelRoom> findRoomsByParams(HotelRoom.RoomType type, int capacity, int bedsAmount, float maxPrice) throws Exception {
        MySqlDataSource msds = MySqlDataSource.getInstance();
        Connection con = msds.getConnection();
        CallableStatement stmt = con.prepareCall("SELECT * FROM hotelrooms WHERE type = ? AND (2*double_beds_amount+single_beds_amount) >= ? AND (double_beds_amount+single_beds_amount) >= ? AND price <= ?;");
        stmt.setString(1, type.toString());
        stmt.setInt(2, capacity);
        stmt.setInt(3, bedsAmount);
        stmt.setFloat(4, maxPrice);
        ResultSet resultSet = stmt.executeQuery();
        msds.releaseConnection(con);
        ArrayList<HotelRoom> list = new ArrayList<>();
        while(resultSet.next())
            list.add(new HotelRoom(resultSet.getInt(1), HotelRoom.RoomType.valueOf(resultSet.getString(2)),
                    resultSet.getInt(3), resultSet.getInt(4),
                    resultSet.getInt(5), resultSet.getBoolean(6), resultSet.getBoolean(7),
                    resultSet.getBoolean(8), resultSet.getFloat(9)));

        return list;
    }
}
