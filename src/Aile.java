public class Aile implements IAbone {
    //Ali Gaffar TOKSOY
//221106206006
    private double eskiBorc;

    public Aile(double eskiBorc) {
        this.eskiBorc = eskiBorc;
    }

    @Override
    public double faturaHesapla(int tuketimMiktari, double eskiBorc) {
        double fatura = 0;
        if (tuketimMiktari <= 10) {
            fatura = tuketimMiktari * 15;
        } else if (tuketimMiktari <= 20) {
            fatura = 10 * 15 + (tuketimMiktari - 10) * 20;
        } else {
            fatura = 10 * 15 + 10 * 20 + (tuketimMiktari - 20) * 30;
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
        return "Aile";
    }
}
