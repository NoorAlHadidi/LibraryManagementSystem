package Model;

public abstract class Human {
    private int id;
    private String firstName;
    private String lastName;
    private int age;

    public Human(int id, String fName, String lName, int age) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.age = age;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
