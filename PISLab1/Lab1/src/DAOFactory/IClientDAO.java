package DAOFactory;

import entityObjects.Client;

public interface IClientDAO {
    void addClient(Client client) throws Exception;
    Client findClientByEmail(String email) throws Exception;
    Client findClientByPhone(String phone) throws Exception;

    // Params: [name, surname, phone, email]
    void updateClient(Client client, String[] params) throws Exception;
    void removeClient(int id) throws Exception;
}
