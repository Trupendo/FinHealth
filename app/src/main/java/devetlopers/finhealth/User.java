package devetlopers.finhealth;

public class User {

    private String name;
    private String surname;
    private double zostatok;
    private double pMesVydavky;
    private double pMesPrijem;
    private double rezerva;
    private double majetok;

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
}
