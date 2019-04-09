/*
 * 2017-2018 BBM 204 PA3
 * @author REYYAN OFLAZ
 */
import java.io.FileNotFoundException;
import java.io.IOException;
/*
 * dpVector1Vector2 > 0, a and b form an angle less than 90o
 * dpab = 0, a and b form an angle that is exactly 90o
 * dpab < 0, a and b form an angle greater than 90o
 * An angle of 0o means that cos gama = 1 so the vectors have identical directions. An angle of 90o
 * means that cos gama = 0 so the vectors have perpendicular directions or are orthogonal. 
 *  dpVector1 is the dot product of word  with itself.
 *  dpVector2 is the dot product of word  with itself
 *  la = (dpVector1) root= the length of Vector 1.
 * la*lb =the length product (lpab) of a and b
 * Therefore, when we compute a cosine similarity we are measuring the direction-length
 * resemblance between data sets represented as vectors.	
 * 
 * https://stackoverflow.com/questions/2889777/difference-between-hashmap-linkedhashmap-and-treemap
 */
public class Assignment3 {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"))+"/"+args[0];
		String absolutePath = Assignment3.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		if(args.length != 4) {
			System.out.println("Invalid command");
			  System.exit(1);
 
		}
		ManipulateGraph manipulateWordVObj= new ManipulateGraph();
		manipulateWordVObj.wordVectorFile=manipulateWordVObj.readFile(args[0]);
		manipulateWordVObj.fileObj.closeReaderFile(manipulateWordVObj.fileObj.buffReadManager);
		manipulateWordVObj.wordPairsFile=manipulateWordVObj.readFile(args[1]);
		manipulateWordVObj.fileObj.closeReaderFile(manipulateWordVObj.fileObj.buffReadManager);
		manipulateWordVObj.setSymbolGraphSize();
		manipulateWordVObj.constructSymbolGraphFromFile();
		

	}
}