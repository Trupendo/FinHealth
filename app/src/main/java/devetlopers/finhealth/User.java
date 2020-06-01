package devetlopers.finhealth;

public class User {

    private String name;
    private String surname;
    private double zostatok;
    private double rezervaPlan;
    private double customPlan;
    private boolean planCreated = false;

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

    public double getRezervaPlan() {
        return rezervaPlan;
    }

    public void setRezervaPlan(double rezervaPlan) {
        this.rezervaPlan = rezervaPlan;
    }

    public double getCustomPlan() {
        return customPlan;
    }

    public void setCustomPlan(double customPlan) {
        this.customPlan = customPlan;
    }

    public boolean isPlanCreated() {
        return planCreated;
    }

    public void setPlanCreated(boolean planCreated) {
        this.planCreated = planCreated;
    }
}
