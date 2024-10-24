package adri.suivi_courrier.data.entity;
import org.springframework.stereotype.Component;

@Component
public class StatCourrierDept {

    private int year;
    private int month;
    private String deptDestinataire;
    private int courrierCount;

    public StatCourrierDept() {}

    public StatCourrierDept(int year, int month, String deptDestinataire, int courrierCount) {
        this.year = year;
        this.month = month;
        this.deptDestinataire = deptDestinataire;
        this.courrierCount = courrierCount;
    }
    
    // Getters
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public String getDeptDestinataire() {
        return deptDestinataire;
    }

    public int getCourrierCount() {
        return courrierCount;
    }

    // Setters
    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDeptDestinataire(String deptDestinataire) {
        this.deptDestinataire = deptDestinataire;
    }

    public void setCourrierCount(int courrierCount) {
        this.courrierCount = courrierCount;
    }
}
