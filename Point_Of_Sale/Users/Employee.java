package Point_Of_Sale.Users;

public class Employee extends User{
    //instance variables
    private double salary;
    private String taxRegNum;

    // constructor
    public Employee(){
        super();
        this.salary = 0;
        this.taxRegNum = "";
    }

    public Employee(String id, String name, String email, double salary,String reg) {
        super(id, name, email);
        this.salary = salary;
        this.taxRegNum = reg;
    }

    // getters/setters
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
