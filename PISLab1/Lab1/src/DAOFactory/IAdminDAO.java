package DAOFactory;

import entityObjects.Administrator;

public interface IAdminDAO {
    void addAdmin(Administrator admin) throws Exception;
    Administrator findAdminByEmail(String email) throws Exception;
    void updateAdmin(Administrator admin, String[] params) throws Exception;
    void removeAdmin(int id) throws Exception;
}
