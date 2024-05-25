import java.io.*;
import java.util.HashMap;
import java.util.Map;
//Ali Gaffar TOKSOY
//221106206006
public class Seyehat {
    public static void main(String[] args) {
        try {
            File fileR = new File("src/seyehat.txt");
            File fileW = new File("src/seyehatRaporu.txt");
            FileReader fr = new FileReader(fileR);
            FileWriter fw = new FileWriter(fileW);
            BufferedReader br = new BufferedReader(fr);
            BufferedWriter br2 = new BufferedWriter(fw);

            Map<String, Integer> cityCodes = new HashMap<>();
            int[][] distances = new int[8][8];

            String str;
            boolean readingCityCodes = false;
            boolean readingDistances = false;
            boolean readingPlan = false;
            int cityCodeIndex = 0;

            br2.write("SEYEHAT RAPORU\n");
            br2.write("ADI\tSOYADI\tNEREDEN\tNEREYE\tHIZ (km/saat)\tMESAFE (km)\tSÜRE (saat)\n");

            while ((str = br.readLine()) != null) {
                str = str.trim();
                if (str.equals("Şehir Kodları:")) {
                    readingCityCodes = true;
                    readingDistances = false;
                    readingPlan = false;
                    continue;
                } else if (str.equals("Şehirler Arası Mesafeler:")) {
                    readingCityCodes = false;
                    readingDistances = true;
                    readingPlan = false;
                    continue;
                } else if (str.equals("Seyehat Planı:")) {
                    readingCityCodes = false;
                    readingDistances = false;
                    readingPlan = true;
                    continue;
                } else if (str.isEmpty()) {
                    continue;
                }

                if (readingCityCodes) {
                    String[] parts = str.split(" ");
                    if (parts.length == 2) {
                        cityCodes.put(parts[0], Integer.parseInt(parts[1]) - 1);
                    } else {
                        System.out.println("Geçersiz veri formatı: " + str);
                    }
                } else if (readingDistances) {
                    String[] parts = str.split("\t");
                    if (parts.length == 9) {
                        for (int i = 1; i < parts.length; i++) {
                            distances[cityCodeIndex][i - 1] = Integer.parseInt(parts[i]);
                        }
                        cityCodeIndex++;
                    } else {
                        System.out.println("Geçersiz şehir adı: " + str);
                    }
                } else if (readingPlan) {
                    String[] parts = str.split(" ");
                    if (parts.length == 5) {
                        String adi = parts[0];
                        String soyadi = parts[1];
                        String nereden = parts[2];
                        String nereye = parts[3];
                        int hiz;
                        try {
                            hiz = Integer.parseInt(parts[4]);
                        } catch (NumberFormatException e) {
                            System.out.println("Geçersiz hız değeri: " + str);
                            continue;
                        }

                        int neredenKod = cityCodes.getOrDefault(nereden, -1);
                        int nereyeKod = cityCodes.getOrDefault(nereye, -1);

                        if (neredenKod == -1 || nereyeKod == -1) {
                            System.out.println("Geçersiz şehir adı: " + str);
                            continue;
                        }

                        int mesafe = distances[neredenKod][nereyeKod];


                        double sure = (double) mesafe / hiz;
                        int saat = (int) sure; // Saati alıp, kesirli kısmı çıkarıyoruz
                        int dakika = (int) ((sure - saat) * 60); // Dakikayı hesaplıyoruz

                        br2.write(String.format("%s\t%s\t%s\t%s\t%d\t%d\t %d Saat %d Dakika\n",
                                adi, soyadi, nereden, nereye, hiz, mesafe, saat, dakika));
                    } else {
                        System.out.println("Geçersiz veri formatı: " + str);
                    }
                }
            }

            br.close();
            br2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

