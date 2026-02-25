package academicmanagement;

public class Instructor extends Person {
    private String employeeID;

    public Instructor() {}

    public Instructor(String id, String name, String employeeID) {
        super(id, name);
        this.employeeID = employeeID;
    }

    public String getEmployeeID() { return employeeID; }
    public void setEmployeeID(String employeeID) { this.employeeID = employeeID; }

    public String getPersonType() { return "Instructor"; }
}