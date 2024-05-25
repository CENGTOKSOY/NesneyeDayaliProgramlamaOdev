public class Emekli implements IAbone {
    //Ali Gaffar TOKSOY
//221106206006
    private double eskiBorc;

    public Emekli(double eskiBorc) {
        this.eskiBorc = eskiBorc;
    }

    @Override
    public double faturaHesapla(int tuketimMiktari, double eskiBorc) {
        double fatura = 0;
        if (tuketimMiktari <= 10) {
            fatura = tuketimMiktari * 10;
        } else if (tuketimMiktari <= 20) {
            fatura = 10 * 10 + (tuketimMiktari - 10) * 15;
        } else {
            fatura = 10 * 10 + 10 * 15 + (tuketimMiktari - 20) * 20;
        }
        fatura += eskiBorc * 1.1; // Eski borç %10 zamlı eklenir
        return fatura;
    }

    @Override
    public double eskiBorcMiktari() {
        return this.eskiBorc;
    }

    @Override
    public String aboneTuru() {
        return "Emekli";
    }
}
