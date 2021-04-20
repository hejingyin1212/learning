package com.ly.demo.poi;


import com.aspose.words.Document;

/**
 * @author ：LY
 * @date ：2020/9/15 14:20
 * @description ：doc2docx
 */

public class doc2docx {
    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = new Document("C:\\Users\\Hjy\\Desktop\\Test\\Test-in.doc");
            doc.save("C:\\Users\\Hjy\\Desktop\\Test\\out\\Test-out2.docx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
