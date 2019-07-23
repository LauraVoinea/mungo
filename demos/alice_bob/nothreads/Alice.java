package demos.alice_bob.nothreads;

@Typestate("AliceSession")
class Alice{
	String channel;

	public void sendStringToBob(String s) {
		channel = s;
	}
}
