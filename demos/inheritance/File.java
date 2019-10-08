package demos.inheritance;

@Typestate("FileProtocol")
public class File{
    protected FileHandle h; // Mungo currently requires 'private' here

    public File () {
        h = new FileHandle();
    }

    // Calls read() internally, which may be overridden.
    public final void open () {
        h.acquire();
        byte header = read(); // suppose every file starts with a header byte
    }

    public synchronized byte read () {
        return 0;
    }

    public synchronized void close () {  
        h.release();
    }
}
