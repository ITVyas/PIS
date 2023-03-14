package running;

import DAOFactory.*;
import MySqlDAOFactory.MySqlDAOFactory;
import entityObjects.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void CheckClient() throws Exception {
        DAOFactory daoFactory = MySqlDAOFactory.getInstance();
        IClientDAO clientDAO = daoFactory.getClientDAO();
        clientDAO.addClient(new Client("Viacheslav", "Rudametkin", "123123123", "viach@mail.com"));
        Client client = clientDAO.findClientByEmail("viach@mail.com");
        if(client != null)
            client.print();
        else
            System.out.println("CLIENT NOT FOUND.");
        clientDAO.updateClient(client, new String[] {"Viktor", client.getSurname(), client.getPhone(), client.getEmail()});
        client = clientDAO.findClientByEmail("viach@mail.com");
        if(client != null)
            client.print();
        else
            System.out.println("CLIENT NOT FOUND.");
        clientDAO.removeClient(client.getId());
        client = clientDAO.findClientByEmail("viach@mail.com");
        if(client != null)
            client.print();
        else
            System.out.println("CLIENT NOT FOUND.");
    }
    public static void CheckRooms() throws Exception {
        DAOFactory daoFactory = MySqlDAOFactory.getInstance();
        IHotelRoomDAO roomDAO = daoFactory.getHotelRoomDAO();
        ArrayList<HotelRoom> list = roomDAO.findRoomsByParams(HotelRoom.RoomType.Economy, 1, 1, 100);
        for(HotelRoom el : list)
            el.print();

        // Change Standard to Economy
        ArrayList<HotelRoom> stdList = roomDAO.findRoomsByParams(HotelRoom.RoomType.Standard, 1, 1, 100);
        if(stdList.size() != 0)
            roomDAO.upadteRoomByNumber(stdList.get(0).getNumber(), HotelRoom.RoomType.Economy,
                    false, false, false, 10, 1, 1);
        System.out.println();
        list = roomDAO.findRoomsByParams(HotelRoom.RoomType.Economy, 1, 1, 100);
        for(HotelRoom el : list)
            el.print();
    }
    public static void main(String[] args) throws Exception, SQLException {
        CheckClient();
        System.out.println();
        CheckRooms();
    }
}