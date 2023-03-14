package entityObjects;

public class Client {
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private void initValues(int id, String name, String surname, String phone, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }
    public Client() {}
    public Client(int id, String name, String surname, String phone, String email)
    {
        initValues(id, name, surname, phone, email);
    }
    public Client(String name, String surname, String phone, String email)
    {
        initValues(-1, name, surname, phone, email);
    }
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getSurname()
    {
        return surname;
    }
    public String getPhone()
    {
        return phone;
    }
    public String getEmail()
    {
        return email;
    }
    public void setName(String newName)
    {
        name = newName;
    }
    public void setSurname(String newSurname)
    {
        surname = newSurname;
    }
    public void setPhone(String newPhone)
    {
        phone = newPhone;
    }
    public void setEmail(String newEmail)
    {
        email = newEmail;
    }
    public void print() {
        System.out.println(name + " | " + surname + " | " + phone + " | " + email);
    }
}
