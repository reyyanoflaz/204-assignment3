/**
 * @author REYYAN OFLAZ
 * @version 1.0
 */

import java.io.*;
public class FileManager{
	public BufferedReader buffReadManager;
	public BufferedWriter buffWriteManager;
	public FileWriter fileWriter;
	public File fileReadMng;
	public File fileWriteMng;
	public String lineManager;
	public FileReader fileReader;
/**
 * open and read file the file
 * @param file
 * @throws
 * @return BufferedReader object
 */
	public BufferedReader openFile(String file) throws IOException,FileNotFoundException{
	
		try{
			fileReadMng= new File(file);
	    	fileReader = new FileReader(fileReadMng);
	    	buffReadManager= new BufferedReader(fileReader);
			return buffReadManager;
		}
		catch(Exception e){
			throw new IOException();
		}
		
	}
	/**
	 * writes to the file
	 * @param file
	 * @throws
	 * @return BufferedWriter object
	 */
	public BufferedWriter writeFile (String file) throws IOException{
		try{
			fileWriteMng = new File(file);
			if(!fileWriteMng.exists()){
				   fileWriteMng.createNewFile();
				}
			fileWriter = new FileWriter(fileWriteMng);
	    	buffWriteManager= new BufferedWriter(fileWriter);
			return buffWriteManager;
		}
		catch(Exception e){
			throw new IOException();
		}
	}
	/** 
	 * Closes this file
	 * @param buffered reader object
	 * @throws IOException
	 */
	public void closeReaderFile(BufferedReader br) throws IOException{
		if(br!=null){
			br.close();
		}
	}
	public void closeWriterFile(BufferedWriter bw) throws IOException{
		if(bw!=null){
			bw.close();
		}
	}
	
	
}
