package demos.buyer_seller;


@Typestate("Buyer1Protocol")
public class Buyer1{
	private final Socket socketSeller;
	private final Socket socketBuyer2;

	public Buyer1(Socket socketSeller, Socket socketBuyer2) {
		this.socketSeller = socketSeller;
		this.socketBuyer2 = socketBuyer2;
	}

	public void sendTitleToSeller(String title) {
		this.socketSeller.send(this, title);
		System.out.println("Buyer1 to Seller: I want to buy the book " + title);
		}

	public String receivePriceFromSeller() {
		return this.socketSeller.receive(this);
	}

	public void sendQuoteToBuyer2(String quote) {
		this.socketBuyer2.send(this, quote);
		System.out.println("Buyer1 to Buyer2: Your quote to pay is " + quote + ". Do you agree?");
	}

	public AgreementLabel receiveLabelFromBuyer2() {
		String label = this.socketBuyer2.receive(this);
		if (label.equals("AGREE")) {
			return AgreementLabel.AGREE;
		}
		return AgreementLabel.QUIT;
	}

	public void transferMoneyToSeller(String money) {
		this.socketSeller.send(this, money);
		System.out.println("Buyer1 to Seller: I transfer " + money);
	}
}
