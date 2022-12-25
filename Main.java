import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner girdi = new Scanner(System.in);
        HouseControl houseControl= new HouseControl();
        Transactions transactions= new Transactions();

        transactions.choices();

    }

}
