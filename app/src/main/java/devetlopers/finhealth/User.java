package devetlopers.finhealth;

public class User {

    private String name;
    private String surname;
    private double zostatok;
    private double pMesVydavky;
    private double pMesPrijem;
    private double rezerva;

    private boolean prvaVyplata = true;
    private double majetokInc = 10;

    private double majetok;
    private double rezervaCast;
    private double majetokCast;

    public User() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getZostatok() {
        return zostatok;
    }

    public void setZostatok(double zostatok) {
        this.zostatok = zostatok;
    }

    public double getpMesVydavky() {
        return pMesVydavky;
    }

    public void setpMesVydavky(double pMesVydavky) {
        this.pMesVydavky = pMesVydavky;
    }

    public double getpMesPrijem() {
        return pMesPrijem;
    }

    public void setpMesPrijem(double pMesPrijem) {
        this.pMesPrijem = pMesPrijem;
    }

    public double getRezerva() {
        return rezerva;
    }

    public void setRezerva(double rezerva) {
        this.rezerva = rezerva;
    }

    public double getMajetok() {
        return majetok;
    }

    public void setMajetok(double majetok) {
        this.majetok = majetok;
    }

    public double getRezervaCast() {
        return rezervaCast;
    }

    public void setRezervaCast(double rezervaCast) {
        this.rezervaCast = rezervaCast;
    }

    public double getMajetokCast() {
        return majetokCast;
    }

    public void setMajetokCast(double majetokCast) {
        this.majetokCast = majetokCast;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isPrvaVyplata() {
        return prvaVyplata;
    }

    public void setPrvaVyplata(boolean prvaVyplata) {
        this.prvaVyplata = prvaVyplata;
    }

    public double getMajetokInc() {
        return majetokInc;
    }

    public void setMajetokInc(double majetokInc) {
        this.majetokInc = majetokInc;
    }
}
