package entityObjects;

import java.sql.Date;

public class ClientRequest {
    public enum RequestStatus {
        InProcess("In process"), Processed("Processed"), Refund("Refund");

        private String value;

        RequestStatus(String value){
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    private int id;
    private int clientId;
    private int roomNumber;
    private Date arrivalDate;
    private Date departureDate;
    private boolean isPaid;
    private RequestStatus status;
    private void initValues(int id, int clientId, int roomNumber,  Date arrivalDate,
                            Date departureDate, boolean isPaid, RequestStatus status)
    {
        this.id = id;
        this.clientId = clientId;
        this.roomNumber = roomNumber;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.isPaid = isPaid;
        this.status = status;
    }
    public ClientRequest() {}
    public ClientRequest(int id, int clientId, int roomNumber,  Date arrivalDate,
                         Date departureDate, boolean isPaid, RequestStatus status) {
        initValues(id, clientId, roomNumber, arrivalDate, departureDate, isPaid, status);
    }
    public ClientRequest(int clientId, int roomNumber,  Date arrivalDate,
                         Date departureDate, boolean isPaid, RequestStatus status) {
        initValues(-1, clientId, roomNumber, arrivalDate, departureDate, isPaid, status);
    }
    public int getId() {
        return id;
    }
    public int getClientId() {
        return clientId;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public boolean getIsPaid()
    {
        return isPaid;
    }
    public RequestStatus getStatus()
    {
        return status;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setArrivalDate(Date newDate) {
        arrivalDate = newDate;
    }
    public void setDepartureDate(Date newDate) {
        departureDate = newDate;
    }
    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
