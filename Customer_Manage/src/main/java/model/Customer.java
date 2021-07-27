package model;

public class Customer {
    private int id;
    private String name;
    private String email;
    private int salary;

    public Customer(){

    }

    public Customer(String name, String email, int salary) {
        this.name = name;
        this.email = email;
        this.salary = salary;
    }

    public Customer(int id, String name, String email, int salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
