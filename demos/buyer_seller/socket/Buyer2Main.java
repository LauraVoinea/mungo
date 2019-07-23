package demos.buyer_seller.socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Buyer2Main{
	public static String safeRead(BufferedReader br) {
        String line = "";

        try {
            line = br.readLine();
        } catch (IOException e) {
            System.out.println("Input/Outpur error, unable to read");
            System.exit(-1);
        }

        return line;
    }

    public static void main(String [] args) {
        Buyer2Role buyer2 = new Buyer2Role();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Waiting for the quote
            System.out.println("Waiting to receive quote from Buyer1...");
            int quote = buyer2.receiveQuoteFromBuyer1();
            System.out.println("Received quote from Buyer1 £" + quote);

            // Agreement decision for Buyer2
            //buyer2.sendToSellerBuyer1("AGREE");
            //buyer2.transferMoneyToSeller("30");
            System.out.print("Buyer2 to Seller and Buyer1: AGREE [y/n]? ");
            String reply = safeRead(br);
            String agreement = reply.equals("y") ? "AGREE" : "QUIT";
            
            switch (buyer2.sendToSellerBuyer1(agreement)) {
            case AGREE:
                //System.out.println("Buyer2 agrees to contribute");
                System.out.print("Buyer2 to Seller: I am transfering you £");
                int money = Integer.parseInt(safeRead(br));
                buyer2.transferMoneyToSeller(money);
                break;
            case QUIT:
                break;
            }
    }
}