public class Ogrenci implements IAbone {
    //Ali Gaffar TOKSOY
//221106206006
    private final double eskiBorc;

    public Ogrenci(double eskiBorc) {
        this.eskiBorc = eskiBorc;
    }

    @Override
    public double faturaHesapla(int tuketimMiktari, double eskiBorc) {
        double fatura;
        if (tuketimMiktari <= 5) {
            fatura = 0;
        } else if (tuketimMiktari <= 10) {
            fatura = (tuketimMiktari - 5) * 10;
        } else if (tuketimMiktari <= 20) {
            fatura = 5 * 10 + (tuketimMiktari - 10) * 15;
        } else {
            fatura = 5 * 10 + 10 * 15 + (tuketimMiktari - 20) * 20;
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
        return "Ogrenci";
    }
}
