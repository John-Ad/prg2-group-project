package Point_Of_Sale.Users;

public class Employee extends User{
    private double salary;
    private String taxRegNum;

    public Employee(){
        super();
        this.salary = 0;
    }

    public Employee(String id, String name, String email, double salary) {
        super(id, name, email);
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getTaxRegNum() {
        return this.taxRegNum;
    }

    public void setTaxRegNum(String taxRegNum) {
        this.taxRegNum = taxRegNum;
    }
}
