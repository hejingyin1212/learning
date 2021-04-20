package com.ly.demo.jacob;

/**
 * @author ：LY
 * @date ：2020/9/10 11:19
 * @description ：Test
 */

public class Test {
    public static void main(String[] args) {
        String docPath = "C:\\Users\\Hjy\\Desktop\\Test\\jacob\\Test-in.docx";
        String watermarkPath = "C:\\Users\\Hjy\\Desktop\\Test\\jacob\\w.JPG";
//        String pdfPath = "C:\\Users\\Hjy\\Desktop\\Test\\Test.pdf";
////        boolean res = Word2PdfJacobUtil.word2PDF(docPath, pdfPath);
////        System.out.println(res);
//        Word2PdfAsposeUtil.doc2pdf(docPath,pdfPath);
        WatermarkUtil watermarkUtil = new WatermarkUtil();
        watermarkUtil.addWaterMark(docPath,watermarkPath,100,300);

    }
}
