public class Ticarethane implements IAbone {
    //Ali Gaffar TOKSOY
//221106206006
    private double eskiBorc;

    public Ticarethane(double eskiBorc) {
        this.eskiBorc = eskiBorc;
    }

    @Override
    public double faturaHesapla(int tuketimMiktari, double eskiBorc) {
        double fatura = 0;
        if (tuketimMiktari <= 50) {
            fatura = tuketimMiktari * 5;
        } else if (tuketimMiktari <= 100) {
            fatura = 50 * 5 + (tuketimMiktari - 50) * 10;
        } else {
            fatura = 50 * 5 + 50 * 10 + (tuketimMiktari - 100) * 15;
        }
        fatura += eskiBorc * 1.1;
        return fatura;
    }

    @Override
    public double eskiBorcMiktari() {
        return this.eskiBorc;
    }

    @Override
    public String aboneTuru() {
        return "Ticarethane";
    }
}
