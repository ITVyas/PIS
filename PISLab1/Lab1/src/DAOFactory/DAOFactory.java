package DAOFactory;

public abstract class DAOFactory {
    public abstract IClientDAO getClientDAO();
    public abstract IAdminDAO getAdminDAO();
    public abstract IClientRequestDAO getClientRequestDAO();
    public abstract IHotelRoomDAO getHotelRoomDAO();
    public abstract IRoomRegisterDAO getRoomRegisterDAO();
}
