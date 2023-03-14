package DAOFactory;

import entityObjects.HotelRoom;

import java.util.ArrayList;

public interface IHotelRoomDAO {
    void upadteRoomByNumber(int number, HotelRoom.RoomType type, boolean miniBar, boolean tv, boolean dryer, float price, int singleBedsAmount, int doubleBedsAmount) throws Exception;

    ArrayList<HotelRoom> findRoomsByParams(HotelRoom.RoomType type, int capacity, int bedsAmount, float maxPrice) throws Exception;
}
