import java.util.Scanner;

public class Transactions extends HouseControl{         //Ana menüde ekrana çıkacak seçenekleri içerir ve
                                                        //bu seçeneklere göre dogru methodlara yönledirme yapmak
    Sale sale= new Sale();          //Sale classından yeni nesne üretimi
    Rent rent= new Rent();          //Rent classından yeni nesne üretimi
    Scanner girdi= new Scanner(System.in);

    @Override
    public void arayuz() {
        super.arayuz();
    }

    @Override
    public void evOlustur(String odaSayisi, String konum, String isitma, double fiyat) {
        super.evOlustur(odaSayisi, konum, isitma, fiyat);
    }

    @Override
    public void evKarsilastirma(String odaSayisi, String konum, String isitma, double fiyat) {
        super.evKarsilastirma(odaSayisi, konum, isitma, fiyat);
    }

    @Override
    public void evCikar() {
        super.evCikar();
    }

    @Override
    public void evGoster() {
        super.evGoster();
    }

    public void choices(){
        boolean loop = true;
        boolean loop2= true;
        String choice;
        String saleChoise="";
        int x = 0;

        while(loop){
            while (loop2){
                System.out.println("1)Satılık ev için işlem yap");
                System.out.println("2)Kiralık ev için işlem yap");
                String rentOrSale= girdi.nextLine();

                if(rentOrSale.equalsIgnoreCase("1")){
                    saleChoise="sale";
                    x=1;
                    for(int i=0; i<homes.size();i++){                               //kiralikdan satiliga gecis yaparken homes arrayini sifirlandigindan
                        tempRent.add(homes.get(i));                                 //kiraliktaki evlerin kaybolmamasi icin gecici biri arraye evleri atar
                    }
                    homes.clear();                                                  //bu gecis esnasinda satiliktaki evleri homes arrayine geri atar
                    for(int i=0; i<tempSale.size();i++){
                        homes.add(tempSale.get(i));
                    }
                    loop2=false;
                } else if (rentOrSale.equalsIgnoreCase("2")) {           //yukarda yapilan islemlerin kiralik icin tam tersi
                    saleChoise="rent";
                    x=2;
                    for(int i=0; i<homes.size();i++){
                        tempSale.add(homes.get(i));
                    }
                    homes.clear();
                    for(int i=0; i<tempRent.size();i++){
                        homes.add(tempRent.get(i));

                    }
                    loop2=false;
                }
                else System.out.println("Lütfen geçerli bir değer giriniz!\n");
            }
            if (x == 1){
                System.out.println("\nSatılık evlerde islem yapmaktasınız\n----------------------------------------------\n");
            }
            if (x == 2){
                System.out.println("\nKiralık evlerde işlem yapmaktasınız\n----------------------------------------------\n");
            }
            System.out.println("Yapmak istediğiniz işlemi seçiniz: ");
            System.out.println("1.) Ev ekle");
            System.out.println("2.) Ev çıkar");
            System.out.println("3.) Evleri listele");
            System.out.println("4.) Evleri karşılaştır");
            System.out.println("5.) Satış türünü degiştir");
            System.out.println("6.) Çıkış yap");
            choice=girdi.nextLine();
            if(choice.equalsIgnoreCase("1")){
                setOptionTransactions("1");
                arayuz();
                if (saleChoise.equalsIgnoreCase("sale")){                   //Rent ve Sale classlarindaki aciklamalara bakilabilir
                    sale.process1();
                } else if (saleChoise.equalsIgnoreCase("rent")) {
                    rent.process1();
                }
            } else if (choice.equalsIgnoreCase("2")) {
                setOptionTransactions("2");
                if (saleChoise.equalsIgnoreCase("sale")){
                    sale.process2();
                } else if (saleChoise.equalsIgnoreCase("rent")) {
                    rent.process2();
                }
                evCikar();
            } else if (choice.equalsIgnoreCase("3")) {
                if (saleChoise.equalsIgnoreCase("sale")){
                    sale.process2();
                } else if (saleChoise.equalsIgnoreCase("rent")) {
                    rent.process2();
                }
                evGoster();
            } else if (choice.equalsIgnoreCase("4")) {
                setOptionTransactions("4");
                if (saleChoise.equalsIgnoreCase("sale")){
                    sale.process2();
                } else if (saleChoise.equalsIgnoreCase("rent")) {
                    rent.process2();
                }
                arayuz();
            } else if (choice.equalsIgnoreCase("5")) {
                System.out.println("----------------------------------------------");
                loop2=true;
            } else if (choice.equalsIgnoreCase("6")) {
                System.out.println("İşleminiz sonlandırılıyor...");
                break;
            }
            else {
                System.out.println("Lütfen geçerli bir değer giriniz!\n");
            }
        }
    }
}
