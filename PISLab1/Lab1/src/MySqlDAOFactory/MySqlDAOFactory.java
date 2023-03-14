package MySqlDAOFactory;

import DAOFactory.*;
import SqlConnection.MySqlDataSource;


public class MySqlDAOFactory extends DAOFactory {

    private static MySqlDAOFactory instance = null;
    private static IClientDAO clientDAOInstance = null;
    private static IAdminDAO adminDAOInstance = null;
    private static IHotelRoomDAO hotelRoomDAOInstance = null;
    private static IClientRequestDAO clientRequestDAOInstance = null;
    private static IRoomRegisterDAO roomResisterDAOInstance = null;

    private MySqlDAOFactory() {}

    public static MySqlDAOFactory getInstance() throws Exception
    {
        if(instance == null)
            instance = new MySqlDAOFactory();
        return instance;
    }
    public IClientDAO getClientDAO()
    {
        if(clientDAOInstance == null)
            clientDAOInstance = new MySqlClientDAO();
        return clientDAOInstance;
    }

    public IAdminDAO getAdminDAO()
    {
        if(adminDAOInstance == null)
            adminDAOInstance = new MySqlAdminDAO();
        return adminDAOInstance;
    }

    public IHotelRoomDAO getHotelRoomDAO()
    {
        if(hotelRoomDAOInstance == null)
            hotelRoomDAOInstance = new MySqlHotelRoomDAO();
        return hotelRoomDAOInstance;
    }

    public IClientRequestDAO getClientRequestDAO()
    {
        if(clientRequestDAOInstance == null)
            clientRequestDAOInstance = new MySqlClientRequestDAO();
        return clientRequestDAOInstance;
    }

    public IRoomRegisterDAO getRoomRegisterDAO()
    {
        if(roomResisterDAOInstance == null)
            roomResisterDAOInstance = new MySqlRoomRegisterDAO();
        return roomResisterDAOInstance;
    }
}
