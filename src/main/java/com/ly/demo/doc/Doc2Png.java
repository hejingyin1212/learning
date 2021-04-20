package com.ly.demo.doc;

import com.spire.doc.Document;
import com.spire.doc.documents.ImageType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author ：LY
 * @date ：2020/8/20 11:00
 * @description ：Doc2Png
 */

public class Doc2Png {
    public static void main(String[] args) throws Exception{
        Document doc =new Document();

//加载Word文档

        doc.loadFromFile("C:\\Users\\Hjy\\Desktop\\Test\\Test-in.docx");

        //多页png
        doc.getBuiltinDocumentProperties().getPageCount();
        for (int i=0;i<doc.getBuiltinDocumentProperties().getPageCount();i++){
            BufferedImage image= doc.saveToImages(i, ImageType.Bitmap);
            //将图片数据保存为PNG格式文档
            File file= new File("C:\\Users\\Hjy\\Desktop\\Test\\output_" + i + ".png");
            ImageIO.write(image, "PNG", file);
        }
//将指定页保存为BufferedImage

       // BufferedImage[] images= doc.saveToImages(ImageType.Bitmap);
       // System.out.println(doc.getBuiltinDocumentProperties().getPageCount());
        //System.out.println(images.length);

//将图片数据保存为PNG格式文档

//        File file=new File("C:/Users/Hjy/Desktop/Test/ToPNG.png");
//
//        ImageIO.write(image,"PNG", file);

////将Word保存为SVG格式
//
//        doc.saveToFile("C:\\Users\\Hjy\\Desktop\\Test\\ToSVG.svg", FileFormat.SVG);
//
////将Word保存为RTF格式
//
//        doc.saveToFile("output/ToRTF.rtf",FileFormat.Rtf);
//
////将Word保存为XPS格式
//
//        doc.saveToFile("output/ToXPS.xps",FileFormat.XPS);
//
////将Word保存为XML格式
//
//        doc.saveToFile("output/ToXML.xml",FileFormat.Xml);
//
////将Word保存为TXT格式
//
//        doc.saveToFile("output/ToTXT.txt",FileFormat.Txt);
    }

}
