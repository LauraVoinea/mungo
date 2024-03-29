package demos.buyer_seller;


@Typestate("SellerProtocol")
public class Seller{
	private final Socket socketBuyer1;
	private final Socket socketBuyer2;

	public Seller(Socket socketBuyer1, Socket socketBuyer2) {
		this.socketBuyer1 = socketBuyer1;
		this.socketBuyer2 = socketBuyer2;
	}

	public String receiveTitleFromBuyer1() {
		return this.socketBuyer1.receive(this);
	}

	public void sendPriceToBuyer1(String price) {
		this.socketBuyer1.send(this, price);
		System.out.println("Seller to Buyer1: The price of the book is " + price);
	}

	public AgreementLabel receiveLabelFromBuyer2() {
		String label = this.socketBuyer2.receive(this);
		if (label.equals("AGREE")) {
			return AgreementLabel.AGREE;
		}
		return AgreementLabel.QUIT;
	}

	public String receiveMoneyFromBothBuyers() {
		String fromBuyer2 = this.socketBuyer2.receive(this);
		String fromBuyer1 = this.socketBuyer1.receive(this);
		String total = "£42 + £38 = £80";
		return total;
	}
}
