package beans;

import java.time.LocalDate;

public class Student {
    String name;
    String lastname;
    LocalDate dateOfBrith;

    public Student(){
        name = "Jakub";
        lastname = "Lewandowski";
        dateOfBrith = LocalDate.parse("1999-11-02");
    }

    public Student(String name, String lastname, LocalDate dateOfBrith) {
        this.name = name;
        this.lastname = lastname;
        this.dateOfBrith = dateOfBrith;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateOfBrith() {
        return dateOfBrith;
    }

    public void setDateOfBrith(LocalDate dateOfBrith) {
        this.dateOfBrith = dateOfBrith;
    }
}
