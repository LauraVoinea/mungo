package demos.buyer_seller.socket;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Typestate("SellerProtocol")
public class SellerRole{
    private BufferedReader socketBuyer1In = null;
    private PrintWriter socketBuyer1Out = null;

    private BufferedReader socketBuyer2In = null;
    private PrintWriter socketBuyer2Out = null;

    public SellerRole() {
        // Bind the socket
        ServerSocket serverBuyer1 = null;
        ServerSocket serverBuyer2 = null;
        try {
            serverBuyer1 = new ServerSocket(20000);
            serverBuyer2 = new ServerSocket(20001);
        } catch (IOException e) {
            System.out.println("Unable to listen on port 20000 or 20001");
            System.exit(-1);
        }

        // Accept a client connection
        Socket socketBuyer1 = null;
        Socket socketBuyer2 = null;
        try {
            System.out.println("Accepting...");
            socketBuyer1 = serverBuyer1.accept();
            System.out.println("Buyer1 accepted");
            socketBuyer2 = serverBuyer2.accept();
            System.out.println("Buyer2 accepted");
        } catch (IOException e) {
            System.out.println("Accept failed");
            System.exit(-1);
        }

        // Create the read and write streams
        try {
            socketBuyer1In = new BufferedReader(new InputStreamReader(socketBuyer1.getInputStream()));
            socketBuyer1Out = new PrintWriter(socketBuyer1.getOutputStream(), true);
            socketBuyer2In = new BufferedReader(new InputStreamReader(socketBuyer2.getInputStream()));
            socketBuyer2Out = new PrintWriter(socketBuyer2.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }
    }

    public String receiveTitleFromBuyer1() {
        String title = "";
        try {
            title = this.socketBuyer1In.readLine();
        } catch (IOException e) {
            System.out.println("Input/Output error, unable to get title from buyer1");
            System.exit(-1);
        }

        return title;
    }

    public void sendPriceToBuyer1(int price) {
        this.socketBuyer1Out.println(price);
        //System.out.println("Seller to Buyer1: The price of the book is " + price);
    }

    public AgreementLabel receiveLabelFromBuyer2() {
        String label = "";

        try {
            label = this.socketBuyer2In.readLine();
        } catch (IOException e) {
            System.out.println("Input/Output error, unable to get label from buyer2");
            System.exit(-1);
        }

        if (label.equals("AGREE")) {
            return AgreementLabel.AGREE;
        }
        return AgreementLabel.QUIT;
    }

    public int receiveMoneyFromBothBuyers() {
        String fromBuyer1 = "";
        String fromBuyer2 = "";

        try {
            fromBuyer1 = this.socketBuyer1In.readLine();
            fromBuyer2 = this.socketBuyer2In.readLine();
        } catch (IOException e) {
            System.out.println("Input/Output error, unable to get money from buyers");
            System.exit(-1);
        }

        return Integer.parseInt(fromBuyer1) + Integer.parseInt(fromBuyer2);
    }
}
