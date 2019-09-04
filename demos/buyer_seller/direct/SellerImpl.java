package demos.buyer_seller.direct;

@Typestate("Seller")
class SellerImpl{
	Exit_price_request price_request(String title) {
		boolean inStock = true;
		if (inStock)
			// buyer1.price_response(15);
			return Exit_price_request.PRICE_RESPONSE;
		else
			// buyer1.outOfStock();
			// buyer2.outOfStock();
			return Exit_price_request.OUT_OF_STOCK;
	}

	void agree() {
	}

	void quit() {
	}
}
