package entityObjects;

public class Administrator {
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String password;
    private void initValues(int id, String name, String surname, String phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    public Administrator() {}
    public Administrator(String name, String surname, String phone, String email, String password) {
        initValues(-1, name, surname, phone, email, password);
    }

    public Administrator(int id, String name, String surname, String phone, String email, String password) {
        initValues(id, name, surname, phone, email, password);
    }

    public int getId()  {
        return id;
    }

    public String getName()  {
        return name;
    }

    public String getSurname()  {
        return surname;
    }

    public String getPhone()  {
        return phone;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {return password;}

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

    public void setPassword(String newPassword)
    {
        password = newPassword;
    }
}
