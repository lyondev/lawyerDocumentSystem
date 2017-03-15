/**
 * Java file that contains the class that interfaces
 * with the database functions and the ui of the application.
 */
package Application;

import Database.DBFunctions;
import Docx.DocxParser;
import Pdf.PdfParser;
import StringMaper.StringMapper;

import java.awt.*;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Java class that interfaces with the database of the application.
 * Contains the upload and search functions.
 */
public class BackEndDriver {
    private StringMapper mapper = new StringMapper();
    private DBFunctions dbFunctions = new DBFunctions();
    private PdfParser pdfParser = new PdfParser();
    private DocxParser docxParser = new DocxParser();

    /**
     * Function uploads document to the database
     * Takes the file name, uses the built in parsers
     *
     * @param filePath
     */
    public static void uploadDocument(String filePath) {
        String text = "";
        String[] filePathArray = filePath.split("/");
        String fileName = filePathArray[filePathArray.length-1];
        HashMap<String, Integer> parsedFileMap;
        if (filePath.endsWith("docx")){
            text = docxParser.getTextString(filePath);
        } else if (filePath.endsWith("pdf")){
            text = pdfParser.getTextString(filePath);
        }
        parsedFileMap = mapper.processString(text);
        dbFunctions.insertKeywordData(fileName,parsedFileMap);
    }

    /**
     * Searches the database for files containg the keyword param
     *
     * @param keyword
     * @return reutrns hashmap of filenames and occurances of the keyword
     */
    public static HashMap<String, Integer> searchDocuments(String keyword){
        return dbFunctions.getFileNamesContainingKeyword(keyword);
    }

    /**
     * Returns an array list generated from the hash map param
     *
     * @param results
     * @return ArrayList<String>
     */
    public static ArrayList<String> getResults(HashMap<String, Integer> results){
        ArrayList<String> fileNames = new ArrayList<>();
        int i = 0;
        for (String fileName : results.keySet()) {
            fileNames.add(fileName);
            i++;
        }
        return fileNames;
    }

}
