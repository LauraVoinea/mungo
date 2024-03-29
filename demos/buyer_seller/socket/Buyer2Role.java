package demos.buyer_seller.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


@Typestate("Buyer2Protocol")
public class Buyer2Role{
    private BufferedReader socketBuyer1In = null;
    private PrintWriter socketBuyer1Out = null;

    private BufferedReader socketSellerIn = null;
    private PrintWriter socketSellerOut = null;

    public Buyer2Role() {
        // Bind the socket
        ServerSocket serverBuyer1 = null;
        try {
            serverBuyer1 = new ServerSocket(20002);
        } catch (IOException e) {
            System.out.println("Unable to listen on port 20002");
            System.exit(-1);
        }

        // Accept a client connection
        Socket socketBuyer1 = null;
        try {
            System.out.println("Accepting...");
            socketBuyer1 = serverBuyer1.accept();
        } catch (IOException e) {
            System.out.println("Accept failed");
            System.exit(-1);
        }

        // Create the read and write streams
        try {
            socketBuyer1In = new BufferedReader(new InputStreamReader(socketBuyer1.getInputStream()));
            socketBuyer1Out = new PrintWriter(socketBuyer1.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }

        System.out.println("Accepted connection from Buyer1");

        // Must connect to the Seller
        try {
            // Create the socket
            Socket socketSeller = new Socket("localhost", 20001);

            // Create the read/write streams
            socketSellerIn = new BufferedReader(new InputStreamReader(socketSeller.getInputStream()));
            socketSellerOut = new PrintWriter(socketSeller.getOutputStream(), true);
            System.out.println("Connected to the Seller");
        } catch(UnknownHostException e) {
            System.out.println("Unable to connect to the remote host");
            System.exit(-1);
        } catch(IOException e) {
            System.out.println("Input/Output error, unable to connect");
            System.exit(-1);
        }
    }

    public int receiveQuoteFromBuyer1() {
        String quote = "";

        try {
            //System.out.println("receiving quote ... " + this.socketBuyer1In.ready());
            quote = this.socketBuyer1In.readLine();
        } catch (IOException e) {
            System.out.println("Input/Output error, unable to get quote from buyer1");
            System.exit(-1);
        }

        return Integer.parseInt(quote);
    }

    public AgreementLabel sendToSellerBuyer1(String agreement) {
        this.socketBuyer1Out.println(agreement);
        this.socketSellerOut.println(agreement);

        if (agreement.equals("AGREE")) {
            //System.out.println("Buyer2 to Seller and Buyer1: I agree to pay the quote");
            return AgreementLabel.AGREE;
        }

        //System.out.println("Buyer2 to Seller and Buyer1: I do not agree to pay the quote");
        return AgreementLabel.QUIT;
    }

    public void transferMoneyToSeller(int money) {
        this.socketSellerOut.println(money);
        //System.out.println("Buyer2 to Seller: I transfer " + money);
        System.out.println("Bye!");
    }

}
