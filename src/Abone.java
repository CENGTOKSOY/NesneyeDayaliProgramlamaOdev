import java.io.*;
//Ali Gaffar TOKSOY
//221106206006
public class Abone {

    public static void main(String[] args) throws IOException {
        IAbone[] abone = new IAbone[220];
        int aboneIndex = 0;

        try {
            File fileR = new File("src/tuketim.txt");
            File fileW = new File("src/tuketimRaporu.txt");
            FileReader fr = new FileReader(fileR);
            FileWriter fw = new FileWriter(fileW);
            BufferedReader br = new BufferedReader(fr);
            BufferedWriter br2 = new BufferedWriter(fw);

            String str;
            double totalFaturaTutari = 0;
            int aileSayisi = 0, ogrenciSayisi = 0, emekliSayisi = 0, ticarethaneSayisi = 0;
            double aileToplamFatura = 0, ogrenciToplamFatura = 0, emekliToplamFatura = 0, ticarethaneToplamFatura = 0;
            int aileBorcSayisi = 0, ogrenciBorcSayisi = 0, emekliBorcSayisi = 0, ticarethaneBorcSayisi = 0;

            // En yüksek fatura ödeyen aboneler
            String[] highestPayingAbone = new String[4]; // 0: Aile, 1: Ogrenci, 2: Emekli, 3: Ticarethane
            double[] highestFatura = new double[4];

            br.readLine(); // İlk satırı (başlık satırını) atla
            while ((str = br.readLine()) != null) {
                String[] parts = str.split(" ");
                String aboneNo = parts[0];
                String ad = parts[1];
                String soyad = parts[2];
                String aboneTip = parts[3];
                int tuketim = Integer.parseInt(parts[4]);
                String eskiBorcStr = parts[5];
                double eskiBorc = eskiBorcStr.equals("YOK") ? 0 : Double.parseDouble(eskiBorcStr);

                IAbone yeniAbone = null;
                double fatura = 0;
                switch (aboneTip) {
                    case "Aile":
                        yeniAbone = new Aile(eskiBorc);
                        fatura = yeniAbone.faturaHesapla(tuketim, eskiBorc);
                        aileSayisi++;
                        aileToplamFatura += fatura;
                        if (eskiBorc > 0) aileBorcSayisi++;
                        if (fatura > highestFatura[0]) {
                            highestFatura[0] = fatura;
                            highestPayingAbone[0] = aboneNo + "\t" + ad + "\t" + soyad + "\t" + aboneTip + "\t" + fatura;
                        }
                        break;
                    case "Emekli":
                        yeniAbone = new Emekli(eskiBorc);
                        fatura = yeniAbone.faturaHesapla(tuketim, eskiBorc);
                        emekliSayisi++;
                        emekliToplamFatura += fatura;
                        if (eskiBorc > 0) emekliBorcSayisi++;
                        if (fatura > highestFatura[2]) {
                            highestFatura[2] = fatura;
                            highestPayingAbone[2] = aboneNo + "\t" + ad + "\t" + soyad + "\t" + aboneTip + "\t" + fatura;
                        }
                        break;
                    case "Ogrenci":
                        yeniAbone = new Ogrenci(eskiBorc);
                        fatura = yeniAbone.faturaHesapla(tuketim, eskiBorc);
                        ogrenciSayisi++;
                        ogrenciToplamFatura += fatura;
                        if (eskiBorc > 0) ogrenciBorcSayisi++;
                        if (fatura > highestFatura[1]) {
                            highestFatura[1] = fatura;
                            highestPayingAbone[1] = aboneNo + "\t" + ad + "\t" + soyad + "\t" + aboneTip + "\t" + fatura;
                        }
                        break;
                    case "Ticarethane":
                        yeniAbone = new Ticarethane(eskiBorc);
                        fatura = yeniAbone.faturaHesapla(tuketim, eskiBorc);
                        ticarethaneSayisi++;
                        ticarethaneToplamFatura += fatura;
                        if (eskiBorc > 0) ticarethaneBorcSayisi++;
                        if (fatura > highestFatura[3]) {
                            highestFatura[3] = fatura;
                            highestPayingAbone[3] = aboneNo + "\t" + ad + "\t" + soyad + "\t" + aboneTip + "\t" + fatura;
                        }
                        break;
                }

                totalFaturaTutari += fatura;
                abone[aboneIndex++] = yeniAbone;
            }

            br2.write("FATURA İSTATİSTİKLERİ\n\n");
            br2.write("1- TOPLAM FATURA TUTARI: " + totalFaturaTutari + "\n\n");
            br2.write("2- ABONE SAYILARI\n");
            br2.write("AİLE\t\t: " + aileSayisi + "\n");
            br2.write("ÖĞRENCİ\t\t: " + ogrenciSayisi + "\n");
            br2.write("EMEKLİ\t\t: " + emekliSayisi + "\n");
            br2.write("TİCARETHANE\t: " + ticarethaneSayisi + "\n\n");

            br2.write("3- ABONE TÜRLERİNE GÖRE TOPLAM FATURA TUTARLARI\n");
            br2.write("AİLE\t\t: " + aileToplamFatura + "\n");
            br2.write("ÖĞRENCİ\t\t: " + ogrenciToplamFatura + "\n");
            br2.write("EMEKLİ\t\t: " + emekliToplamFatura + "\n");
            br2.write("TİCARETHANE\t: " + ticarethaneToplamFatura + "\n\n");

            br2.write("4- ABONE TÜRLERİNE GÖRE GEÇMİŞ DÖNEMDEN BORCU OLAN ABONE SAYILARI\n");
            br2.write("AİLE\t\t: " + aileBorcSayisi + "\n");
            br2.write("ÖĞRENCİ\t\t: " + ogrenciBorcSayisi + "\n");
            br2.write("EMEKLİ\t\t: " + emekliBorcSayisi + "\n");
            br2.write("TİCARETHANE\t: " + ticarethaneBorcSayisi + "\n\n");

            br2.write("5- ABONE TÜRLERİNE GÖRE EN YÜKSEK FATURA ÖDEYEN ABONE BİLGİLERİ\n");
            br2.write("ABONE NO\tADI\tSOYADI\tABONE TÜRÜ\tFATURA TUTARI\n");
            for (String info : highestPayingAbone) {
                if (info != null) {
                    br2.write(info + "\n");
                }
            }

            br.close();
            br2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
