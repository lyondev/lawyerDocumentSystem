/**
 * Java Class that uses PdfBox library to parse a pdf document and
 * returns it as string
 *
 */
package Pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;

/**
 * PdfParser class that parses a pdf document and returns a string
 */

public class PdfParser {

    private String getText(File pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        return new PDFTextStripper().getText(doc);
    }

    /**
     * Takes a pdf file and converts it to a string
     *
     * @param filePath
     * @return String - string representation of the pdf file
     */
    public String getTextString(String filePath) {
        String text;
        try {
            text = getText(new File(filePath));
        } catch (IOException e) {
            text = "Error parsing PDF file: " + e.getMessage();
            e.printStackTrace();
        }
        return text;
    }



}
