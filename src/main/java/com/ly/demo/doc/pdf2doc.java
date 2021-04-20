package com.ly.demo.doc;


import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

/**
 * @author ：LY
 * @date ：2020/9/3 13:46
 * @description ：pdf2doc
 */

public class pdf2doc {
    public static void main(String[] args) {
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile("C:/Users/Hjy/Desktop/1.pdf");
        pdf.saveToFile("C:\\Users\\Hjy\\Desktop\\1.docx", FileFormat.DOCX);
    }
}
