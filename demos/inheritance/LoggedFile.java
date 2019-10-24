package demos.inheritance;

@Typestate("LoggedFileProtocol")
public class LoggedFile extends File{
    private LogService logging;

    public LoggedFile () {
        // super();
        logging = new LogService();
    }

    // It should be an error to call super.open() before logging.start(), because
    // super.open() will call my override of read(), which will write to the log.
    public void open () {
        // super.read();
        logging.start();
        logging.log("Opening file");

    }

    public byte read () {
        byte ch = 0;
        logging.log("Reading " + ch);
        // return super.read();
        return ch;
    }

    public void close () {
        logging.log("Closing file");
        logging.stop();
    }

    public void clearLog () {
        logging.clear();
    }
}
