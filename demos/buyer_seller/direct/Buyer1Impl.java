package demos.buyer_seller.direct;


@Typestate("Buyer1")
class Buyer1Impl{
	private SellerImpl seller;

	Buyer1Impl() {
		seller = new SellerImpl();
	}

	void init() {
		seller.price_request("Brave New World");
	}

	void price_response (int price) {
	}

	void outOfStock () {
	}
}
