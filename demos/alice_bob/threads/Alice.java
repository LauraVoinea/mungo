package demos.alice_bob.threads;

@Typestate("AliceProtocol")
public class Alice{
	Session s;

	public Alice(Session s) {
		this.s = s;
	}

	public void sendStringToBob(String a) {
		s.send(a);
	}

	public String receiveStringFromBob() {
		return s.recvString();
	}
}
