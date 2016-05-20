package net.rouletteplayer.model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ScreenRecognizer {

	private Runtime runtime = Runtime.getRuntime();
	private int x;
	private int y;
	private int width;
	private int height;
	private String screenFileName;
    private String textFileName;

    ScreenRecognizer(int x, int y, int width, int height, String screenFileName, String textFileName) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.screenFileName = screenFileName;
        this.textFileName = textFileName;
	}

	private void captureAndSaveScreen() {
		this.runCommand(this.getScreenCommand());
	}

    private void recognizeAndSaveText() {
        this.runCommand(this.getOcrCommand());
    }

    private void runCommand(String command) {
        try {
            Process process = this.runtime.exec(command);
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getOcrCommand() {
        return "tesseract " + this.screenFileName + " " + this.textFileName.split("\\.")[0];
    }

	private String getScreenCommand() {
		return "import -window root -crop "
				+ this.width
				+ "x"
				+ this.height
				+ "+"
				+ this.x
				+ "+"
				+ this.y
				+ " -quality 100 "
				+ this.screenFileName;
	}

    private String readFile(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());
			if (lines.isEmpty()) {
				return "";
			} else {
				return lines.get(0);
			}
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

	public int recognizeScreen() {

		while (true) {
			this.captureAndSaveScreen();
            this.recognizeAndSaveText();
            String s = this.readFile(this.textFileName);

			try {
				return (int) Float.parseFloat(s.replace('*', '0').replaceAll("[^\\d.]", ""));
			} catch (ArrayIndexOutOfBoundsException ignored) {

			} catch (NumberFormatException ignored) {

			}

		}
	}
}
