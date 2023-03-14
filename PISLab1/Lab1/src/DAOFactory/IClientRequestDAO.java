package DAOFactory;

import entityObjects.ClientRequest;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface IClientRequestDAO {
    void addRequest(ClientRequest request) throws Exception;
    ArrayList<ClientRequest> findAllRequestsByClientId(int id) throws Exception;
    void updateRequestStatus(ClientRequest request, ClientRequest.RequestStatus newStatus) throws Exception;
    void removeRequestById(int id) throws Exception;
}
