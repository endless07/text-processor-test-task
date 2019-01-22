package pl.sagiton.example;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import pl.sagiton.example.impl.FileProcessor;

import java.io.BufferedReader;
import java.io.FileReader;

@AllArgsConstructor
public class Application implements Runnable{
	private String filename;
	private String searchStrategy;
	private String searchValue;

	public static void main(String[] args) {
		if (args.length != 3){
			throw new IllegalArgumentException("Incorrect argument size");
		}
		String filename = args[0];
		String searchStrategy = args[1];
		String searchValue = args[2];

		new Application(filename, searchStrategy, searchValue).run();
	}

	@SneakyThrows
	@Override
	public void run() {
		new FileProcessor().processFile(returnBufferedReaderFromFilename(filename), searchStrategy, searchValue);
	}

	@SneakyThrows
	private BufferedReader returnBufferedReaderFromFilename(String filepath){
		FileReader fileReader = new FileReader(filepath);
		return new BufferedReader(fileReader);
	}
}
