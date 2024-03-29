package demos.buyer_seller.socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

@Typestate("Buyer1Protocol")
public class Buyer1Role{
    private BufferedReader socketSellerIn = null;
    private PrintWriter socketSellerOut = null;

    private BufferedReader socketBuyer2In = null;
    private PrintWriter socketBuyer2Out = null;

    public Buyer1Role() {
        // Must connect to the Seller and Buyer2
        try {
            // Create the socket
            Socket socketSeller = new Socket("localhost", 20000);
            Socket socketBuyer2 = new Socket("localhost", 20002);

            // Create the read/write streams
            socketSellerIn = new BufferedReader(new InputStreamReader(socketSeller.getInputStream()));
            socketSellerOut = new PrintWriter(socketSeller.getOutputStream(), true);
            socketBuyer2In = new BufferedReader(new InputStreamReader(socketBuyer2.getInputStream()));
            socketBuyer2Out = new PrintWriter(socketBuyer2.getOutputStream(), true);
        } catch(UnknownHostException e) {
            System.out.println("Unable to connect to the remote host");
            System.exit(-1);
        } catch(IOException e) {
            System.out.println("Input/Output error, unable to connect");
            System.exit(-1);
        }
    }

    public void sendTitleToSeller(String title) {
        this.socketSellerOut.println(title);
        //System.out.println("Buyer1 to Seller: I want to buy the book " + title);
    }

    public int receivePriceFromSeller() {
        String line = "";
        try {
            line  = this.socketSellerIn.readLine();
        } catch(IOException e) {
            System.out.println("Input/Outpur error, unable to get price from seller");
            System.exit(-1);
        }

        return Integer.parseInt(line);
    }

    public void sendQuoteToBuyer2(int quote) {
        this.socketBuyer2Out.println(quote);
        //System.out.println("Buyer1 to Buyer2: Your quote to pay is " + quote + ". Do you agree?");
    }

    public AgreementLabel receiveLabelFromBuyer2() {
        String label = "";
        try {
            label = this.socketBuyer2In.readLine();
        } catch (IOException e) {
            System.out.println("Input/Outpur error, unable to get label from buyer2");
            System.exit(-1);
        }

        if (label.equals("AGREE")) {
            return AgreementLabel.AGREE;
        }
        return AgreementLabel.QUIT;
    }

    public void transferMoneyToSeller(int money) {
        this.socketSellerOut.println(money);
        //System.out.println("Buyer1 to Seller: I transfer " + money);
        System.out.println("Bye!");
    }
}
