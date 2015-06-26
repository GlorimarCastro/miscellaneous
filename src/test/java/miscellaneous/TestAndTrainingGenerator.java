package miscellaneous;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Files.CSVFileSpliter;
import Files.FileHandeler;
import Files.KFoldCrossValidationGenerator;

public class TestAndTrainingGenerator {
	public static void main(String[] args) throws IOException{
		String FILE_SEPARATOR = System.getProperty("file.separator");
		
		//inputs 1. file with data 2. directory to save results
		
		//Create data separation
		KFoldCrossValidationGenerator filter = new KFoldCrossValidationGenerator(new File(args[0]));
		filter.setFoldAmnt(100);
		filter.setTestPercentage(20);
		filter.setMainResultDirectoryPath(args[1]);
		filter.executeTestAndTrainingSpliterToFile();
		
		
		File testDirectory = filter.getTestDirectory();
		File trainingDirectory = filter.getTrainingDirectory();
		
		File[] testFiles = testDirectory.listFiles();
		File[] trainingFile = trainingDirectory.listFiles();
		
		
		//Divide the features
		ArrayList<Integer> defaultColumn = new ArrayList<Integer>();
		defaultColumn.add(19);
		defaultColumn.add(20);
		defaultColumn.add(21);
		defaultColumn.add(22);
		for(File f : testFiles){
			if(f.isDirectory()){
				continue;
			}
			System.out.println(f.getName());
			
			File tempTestDirectory = new File(testDirectory + FILE_SEPARATOR + FileHandeler.getNameWithoutExt(f));
			tempTestDirectory.mkdir();
			
			CSVFileSpliter csvSpliter = new CSVFileSpliter(f, true, null, defaultColumn);
			csvSpliter.setDelimiterToUseInResult(" ");
			csvSpliter.setFileExtForResult(".txt");
			csvSpliter.setShouldSaveHeader(false);
			
			csvSpliter.setResultDir(tempTestDirectory);
			csvSpliter.excecuteCSVSplitToFile();
			
		}
		
		for(File f : trainingFile){
			if(f.isDirectory()){
				continue;
			}
			System.out.println(f.getName());
			
			File tempTrainingDirectory = new File(trainingDirectory + FILE_SEPARATOR + FileHandeler.getNameWithoutExt(f));
			tempTrainingDirectory.mkdir();
			
			CSVFileSpliter csvSpliter = new CSVFileSpliter(f, true, null, defaultColumn);
			csvSpliter.setDelimiterToUseInResult(" ");
			csvSpliter.setFileExtForResult(".txt");
			csvSpliter.setShouldSaveHeader(false);
			csvSpliter.setResultDir(tempTrainingDirectory);
			csvSpliter.excecuteCSVSplitToFile();
			
		}
	
	}
}
