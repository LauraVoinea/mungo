package demos.buyer_seller;


@Typestate("Buyer2Protocol")
public class Buyer2{
	private final Socket socketSeller;
	private final Socket socketBuyer1;

	public Buyer2(Socket socketSeller, Socket socketBuyer1) {
		this.socketSeller = socketSeller;
		this.socketBuyer1 = socketBuyer1;
	}

	public String receiveQuoteFromBuyer1() {
		return this.socketBuyer1.receive(this);
	}

	public AgreementLabel sendToSellerBuyer1(String agreement) {
		this.socketBuyer1.send(this, agreement);
		this.socketSeller.send(this, agreement);

		if (agreement.equals("AGREE")) {
			System.out.println("Buyer2 to Seller and Buyer1: I agree to pay the quote");
			return AgreementLabel.AGREE;
		}
		System.out.println("Buyer2 to Seller and Buyer1: I do not agree to pay the quote");
		return AgreementLabel.QUIT;
	}

	public void transferMoneyToSeller(String money) {
		this.socketSeller.send(this, money);
		System.out.println("Buyer2 to Seller: I transfer " + money);
	}
}
