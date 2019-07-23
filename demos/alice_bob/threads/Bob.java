package demos.alice_bob.threads;

@Typestate("BobProtocol")
public class Bob{
	Session s;

	public Bob(Session s) {
		this.s = s;
	}

	public void sendStringToAlice(String a) {
		s.send(a);
	}

	public String receiveStringFromAlice() {
		return s.recvString();
	}
}
