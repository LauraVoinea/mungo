package demos.alice_bob.nothreads;

@Typestate("BobSession")
class Bob{
	String channel;

	public String receiveStringFromAlice() {
		return channel;
	}
}
