import java.util.ArrayList;
import java.util.Scanner;

public class HouseControl extends  HouseData{       //arayuz, evKarsilastirma, evOlustur, evCikar, evGoster methodlarını içerir
    Scanner girdi= new Scanner(System.in);

    private String optionTransactions;


    static int newPrice;

    public String getOptionTransactions() {
        return optionTransactions;
    }

    public void setOptionTransactions(String optionTransactions) {
        this.optionTransactions = optionTransactions;
    }

    public HouseControl(){

    }

    public HouseControl(String odaSayisi, String konum, String isitma, double fiyat) {
        super(odaSayisi, konum, isitma, fiyat);
    }
    ArrayList<HouseData> homes = new ArrayList<HouseData>();        //Tüm evleri içeren ve methodlarda kullanılan array

    ArrayList<HouseData> tempRent = new ArrayList<HouseData>();     //geçici kiralık evler için array
    ArrayList<HouseData> tempSale = new ArrayList<HouseData>();     //geçici satılık evler için array
    public void arayuz(){
        boolean loop= true;
        boolean optionWhile= true;
        String choice= "";
        String option="";   //Seçilen 1-2-3-4-5 değerini tutar
        while(loop){        //Konsoldaki arayüzün devamlılığını sağlayan while döngüsü

            String odaSayisi="";
            String konum="";
            String isitma="";
            System.out.println("Oda sayısını giriniz:\n1) 1+0\n2) 1+1\n3) 2+1\n4) 3+1\n5) 4+1 ");
            optionWhile=true;
            while(optionWhile){
                option=girdi.nextLine();
                if(option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4")|| option.equals("5")){
                    optionWhile=false;
                }
                else{
                    System.out.println("Lütfen geçerli bir sayı giriniz! ");
                }
            }

            if(option.equalsIgnoreCase("1")){
                odaSayisi="1+0";
            }
            if (option.equalsIgnoreCase("2")) {
                odaSayisi="1+1";
            }
            if (option.equalsIgnoreCase("3")) {
                odaSayisi="2+1";
            }

            if (option.equalsIgnoreCase("4")) {
                odaSayisi="3+1";
            }
            if (option.equalsIgnoreCase("5")) {
                odaSayisi="4+1";
            }


            System.out.println("Konum giriniz:\n1) Kızılay\n2) Maltepe\n3) Altındağ\n4) Ulus\n5) Etimesgut ");
            optionWhile=true;
            while(optionWhile){
                option=girdi.nextLine();
                if(option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4")|| option.equals("5")){
                    optionWhile=false;
                }
                else{
                    System.out.println("Lütfen geçerli bir sayı giriniz! ");
                }
            }

            if(option.equalsIgnoreCase("1")){
                konum="Kızılay";
            } else if (option.equalsIgnoreCase("2")) {
                konum="Maltepe";
            }
            else if (option.equalsIgnoreCase("3")) {
                konum="Altındağ";
            }

            else if (option.equalsIgnoreCase("4")) {
                konum="Ulus";
            }
            else if (option.equalsIgnoreCase("5")) {
                konum="Etimesgut";
            }

            System.out.println("Isıtma türünü giriniz:\n1) Kombi\n2) Merkezi\n3) Soba(Kömürlü)\n4) Yok ");
            optionWhile=true;
            while(optionWhile){
                option=girdi.nextLine();
                if(option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4")){
                    optionWhile=false;
                }
                else{
                    System.out.println("Lütfen geçerli bir sayı giriniz! ");
                }
            }
            if(option.equalsIgnoreCase("1")){
                isitma="Kombi";
            } else if (option.equalsIgnoreCase("2")) {
                isitma="Merkezi";
            }
            else if (option.equalsIgnoreCase("3")) {
                isitma="Soba(Kömürlü)";
            }

            else if (option.equalsIgnoreCase("4")) {
                isitma="Yok";
            }


            System.out.println("Evin fiyatını giriniz: ");
            double fiyat=0;
            String price;
            boolean control= true;
            while(control){
                price=girdi.nextLine();
                try{
                    newPrice=Integer.parseInt(price);   //girilen string fiyatı int değere çeviren fonksiyon
                    control=false;
                }catch (Exception exception){           //yanlış girişte ekrana mesaj yollıyıp döngüde kalmasını sağlıyor
                    System.out.println("Lütfen geçerli bir değer giriniz!");
                }
            }
            fiyat=newPrice;



            if(getOptionTransactions().equalsIgnoreCase("1")){
                evOlustur(odaSayisi,konum,isitma,fiyat);
            } else if (getOptionTransactions().equalsIgnoreCase("2")) {
                evCikar();
            }else if (getOptionTransactions().equalsIgnoreCase("4")){
                evKarsilastirma(odaSayisi,konum,isitma,fiyat);
                break;
            }
            System.out.println("Devam etmek istemiyorsanız \"H\"ye , devam etmek istiyorsanız herhangi bir tuşa basınız.");   //ana menüye dönüp dönülmeyeceğini belirler
            choice=girdi.nextLine();
            if(choice.equalsIgnoreCase("H")){
                break;
            }

        }
    }

    public  void evKarsilastirma(String odaSayisi, String konum, String isitma, double fiyat){
        HouseData searchedHouse= new HouseData(odaSayisi,konum,isitma,fiyat);
        int[] similarity = new int[homes.size()];
        int maxNumber;                                          //girilen evler arrayinin boyutunda 2 yeni dizi oluşturur
        int [] numberArrangement= new int[homes.size()];

        for(int m=0;m<similarity.length;m++){                   //bunlara ilk değer ataması yapılır
            similarity[m]=0;
            numberArrangement[m]=0;
        }
        System.out.println("Aranan evin: ");
        System.out.println("Oda sayısı: "+ odaSayisi+ "-- Konumu: "+ konum+ " --Isıtma türü: "+ isitma+ " --Fiyat(Max): "+ fiyat);
        System.out.println("\n");
        for(int i=0; i<homes.size();i++){                                       //girilen evlerin istenilen özelliklere göre karşılaştırılması
            if(homes.get(i).getOdaSayisi().equalsIgnoreCase(odaSayisi)){
                similarity[i]++;
            }                                                                   //girilen özelliğin aynı olması durumunda girilen evlerle aynı indeksdeki
            if ((homes.get(i).getKonum().equalsIgnoreCase(konum))){             //similarity dizisinin değerinin artmasını sağlar
               similarity[i]+=3;                                                //Not: istenilen konumda olmasının istenilen konumda olmamasından daha önemli olması
            }                                                                   //sıralamayı etkilemesin diye similarity[i]+=3
            if ((homes.get(i).getIsitma().equalsIgnoreCase(isitma))){
                similarity[i]++;
            }

            for(int m=0; m<similarity.length;m++){
                numberArrangement[m]=similarity[m];
            }


        }
        for(int i=0; i<numberArrangement.length;i++){                           //benzerlik değerlerini taşıyan dizinin sıralama algoritmasıyla büyükten küçüğe sıralanması

            for(int j=i+1;j<numberArrangement.length;j++){
                if(numberArrangement[j]>numberArrangement[i]){
                    maxNumber=numberArrangement[i];
                    numberArrangement[i]=numberArrangement[j];
                    numberArrangement[j]=maxNumber;
                }
            }
        }
        boolean found=false;
                for(int a=0; a<numberArrangement.length;a++){                   //^^
                    for(int b=0; b<similarity.length;b++){
                        if(numberArrangement[a]==similarity[b] && fiyat>=homes.get(b).getFiyat()){
                            System.out.print("Evin konumu: "+homes.get(b).getKonum()+" --- ");
                            System.out.print("Evin oda sayısı: "+homes.get(b).getOdaSayisi()+" --- ");
                            System.out.print("Evin ısıtma türü: "+homes.get(b).getIsitma()+" --- ");
                            System.out.println("Evin fiyatı: "+homes.get(b).getFiyat());
                            similarity[b]+=100;
                            if(konum.equals(homes.get(b).getKonum()) && odaSayisi.equals(homes.get(b).getOdaSayisi()) && isitma.equals(homes.get(b).getIsitma())){
                                System.out.println("\n Aradığınız ev tam olarak bulundu\n Sizin için önerdiğimiz diğer evlere de göz atın...\n");
                                found=true;
                            }
                        }
                    }
                }
                if(found==false){
                    System.out.println("\nAradığınız ev tam olarak bulunamadı. Sizin için önerdiğimiz evlere göz atın...");
                }


        System.out.println("\n");
    }
    public  void evOlustur(String odaSayisi, String konum, String isitma, double fiyat){
        HouseData houseData= new HouseData(odaSayisi, konum, isitma,fiyat);
        homes.add(houseData);
}
    public void evCikar(){
        evGoster();
        System.out.println("Çıkarmak istediğiniz evin numarasını giriniz.");
        int index= girdi.nextInt();
        if (index-1<homes.size()){
            homes.remove(index-1);
        }else{
            System.out.println("Bu değerde bir ev bulunmamaktadır!");
        }

    }

    public void evGoster(){
        for(int k=0; k<homes.size();k++){
            System.out.print((k+1)+".) Evin konumu: "+homes.get(k).getKonum()+" --- ");
            System.out.print("Evin oda sayısı: "+homes.get(k).getOdaSayisi()+" --- ");
            System.out.print("Evin ısıtma türü: "+homes.get(k).getIsitma()+" --- ");
            System.out.println("Evin fiyatı: "+homes.get(k).getFiyat());
        }
        System.out.println();
    }
}
