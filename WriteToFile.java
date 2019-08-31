import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
/**
 * This class records results to files
 */
public class WriteToFile {
	public void writeToFile(String source, String path) throws Exception{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
		bw.write(source);
		bw.flush();
		bw.close();
	}
}
