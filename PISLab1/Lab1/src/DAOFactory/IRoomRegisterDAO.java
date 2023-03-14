package DAOFactory;

import entityObjects.HotelRoom;
import entityObjects.RoomRegister;

import java.util.ArrayList;

public interface IRoomRegisterDAO {
    void addRegister(RoomRegister roomRegister) throws Exception;
    ArrayList<RoomRegister> findRoomRegistersByRoomNumber(int number) throws Exception;
}
