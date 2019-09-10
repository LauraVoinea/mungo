package demos.aliasing;

class Main {
    public static void main () {
        LoggedFile f = new LoggedFile();
        f.open();
        f.read();
        f.clearLog();
        LoggedFile g = f; // TODO: make this File
        f.close();
    }
}
