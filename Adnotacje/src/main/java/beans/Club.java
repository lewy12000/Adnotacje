package beans;

import enums.ClubType;

import java.time.LocalDate;

public class Club {
    String name;
    LocalDate dateOfFound;
    Enum clubType;

    public Club(){
        name = "Southampton";
        dateOfFound = LocalDate.parse("1885-11-21");
        clubType = ClubType.ZAWODOWY;
    }

    public Club(String name, LocalDate dateOfFound, Enum clubType) {
        this.name = name;
        this.dateOfFound = dateOfFound;
        this.clubType = clubType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfFound() {
        return dateOfFound;
    }

    public void setDateOfFound(LocalDate dateOfFound) {
        this.dateOfFound = dateOfFound;
    }

    public Enum getClubType() { return clubType; }

    public void setClubType(Enum clubType) { this.clubType = clubType; }
}
