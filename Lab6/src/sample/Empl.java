package sample;



public class Empl {
    private long id;
    private String firstName;
    private String lastName;
    private String departmentName;

    public Empl(String firstName, String lastName, String departmentName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentName = departmentName;
    }

    public Empl(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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


    public String getDepartmentId() {
        return departmentName;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentName = departmentId;
    }

    @Override
    public String toString() {
        return   this.firstName + "\t|" +this.lastName + "\t|" + this.departmentName;
    }
}
