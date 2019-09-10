package demos.exceptions;

import java.io.*;

@Typestate("FileProtocol")
public class File{
	private String file;
	private char[] readBuffer;
	private int i;

	private BufferedReader reader;

	File(String file) {
		this.file = file;
		readBuffer = new char[1024];
		i = 0;
	}

	Status open() {
		try {
			reader = new BufferedReader(new FileReader(file));
			return Status.OK;
		}
		catch(FileNotFoundException e) {
			return Status.ERROR;
		}
	}

	void close() {
		try {
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	BooleanEnum hasNext() {
		try {
			if(reader.ready())
				return BooleanEnum.TRUE;
		}catch(IOException e) {
			return BooleanEnum.FALSE;
		}
		return BooleanEnum.FALSE;
	}

	void read() {

		int c = -1;
		try {
			c = reader.read();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		readBuffer[i++] = (char) c;
	}

	//The next two methods demonstrate that
	// a created typestate object can
	// be assigned in a linear way and
	// passed around as an argument

	public static void main(String[] args) {
		File myFile = new File("file.txt");
		File a = myFile;
		processFile(a);
	}

	public static void processFile(File myFile) {
		switch(myFile.open()) {
			case OK:
				if(true) {
					loop:
					while(true) {
						switch(myFile.hasNext()) {
							case TRUE:
								myFile.read();
								break;
							case FALSE:
								break loop;
						}

					}
				}
				myFile.close();
				break;
			case ERROR:
				System.out.println("File <file.txt> not found!");
				break;
		}
	}

}
