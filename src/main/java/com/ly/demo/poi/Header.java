package com.ly.demo.poi;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.*;
import java.util.List;

/**
 * @author ：LY
 * @date ：2020/9/10 16:05
 * @description ：Header
 */

public class Header {
    public static void main(String[] args) throws Exception{
        File is = new File("C:\\Users\\Hjy\\Desktop\\Test\\Test-in.docx");//文件路径
        FileInputStream fis = new FileInputStream(is);

        XWPFDocument docx = new XWPFDocument(fis);//文档对象

        createHeader(docx);
        createFooter(docx);
        //createWatermark(docx);
        //replaceInPara(docx.getHeaderArray().getParagraphArray(0));
    }

    public static void createHeader( XWPFDocument doc) throws Exception {
        CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(doc, sectPr);
        XWPFHeader header = headerFooterPolicy.createHeader(headerFooterPolicy.DEFAULT);
        XWPFParagraph paragraph = header.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.HIGH_KASHIDA);
        //paragraph.setBorderBottom(Borders.THICK);
        XWPFRun run = paragraph.createRun();
        String imgFile = "C:\\Users\\Hjy\\Desktop\\Test\\h.JPG";
        File file = new File(imgFile);
        InputStream is = new FileInputStream(file);
        XWPFPicture picture = run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(400), Units.toEMU(35));
        String blipID = "";
        for (XWPFPictureData picturedata : header.getAllPackagePictures()) { // 这段必须有，不然打开的logo图片不显示
            blipID = header.getRelationId(picturedata);
            picture.getCTPicture().getBlipFill().getBlip().setEmbed(blipID);
        }
        //run.addTab();
        is.close();
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Hjy\\Desktop\\Test\\out\\Test-out.docx");
        doc.write(fos);
        fos.close();
    }
    public static void createFooter( XWPFDocument doc) throws Exception {
        CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(doc, sectPr);
        XWPFFooter footer = headerFooterPolicy.createFooter(headerFooterPolicy.DEFAULT);
        XWPFParagraph paragraph = footer.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.RIGHT);
        //paragraph.setBorderBottom(Borders.THICK);
        XWPFRun run = paragraph.createRun();
        String imgFile = "C:\\Users\\Hjy\\Desktop\\Test\\f.JPG";
        File file = new File(imgFile);
        InputStream is = new FileInputStream(file);
        XWPFPicture picture = run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(350), Units.toEMU(25));
        String blipID = "";
        for (XWPFPictureData picturedata : footer.getAllPackagePictures()) {
            blipID = footer.getRelationId(picturedata);
            picture.getCTPicture().getBlipFill().getBlip().setEmbed(blipID);
        }
        //run.addTab();
        is.close();
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Hjy\\Desktop\\Test\\out\\Test-out.docx");
        doc.write(fos);
        fos.close();
    }
    public static void createWatermark( XWPFDocument doc) throws Exception {
        XWPFParagraph paragraph = doc.createParagraph();
        CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(doc, sectPr);
        headerFooterPolicy.createWatermark("国瓴云签版权所有");
        XWPFHeader header = headerFooterPolicy.getHeader(XWPFHeaderFooterPolicy.DEFAULT);
        paragraph = header.getParagraphArray(0);

        org.apache.xmlbeans.XmlObject[] xmlobjects = paragraph.getCTP().getRArray(0).getPictArray(0).selectChildren(
                new javax.xml.namespace.QName("urn:schemas-microsoft-com:vml", "shape"));

        if (xmlobjects.length > 0) {
            com.microsoft.schemas.vml.CTShape ctshape = (com.microsoft.schemas.vml.CTShape)xmlobjects[0];
            ctshape.setFillcolor("#d8d8d8");
            ctshape.setStyle(ctshape.getStyle() + ";rotation:315");
        }else{
            System.out.print("加水印失败");
        }
        //加完水印后的文档地址及名称
        doc.write(new FileOutputStream("C:\\Users\\Hjy\\Desktop\\Test\\out\\Test-out.docx"));
        doc.close();
    }
    /**
     * 替换段落中的占位符
     * @param para
     */
    public static void replaceInPara(XWPFParagraph para)  {
        // 获取当前段落的文本
        String sourceText = para.getParagraphText();
        System.out.println(sourceText);
        // 控制变量
        boolean replace = false;
        String key = "^p";
        if(sourceText.indexOf(key)!=-1){
            Object value = "";
            if(value instanceof String){
                // 替换文本占位符
                sourceText = sourceText.replace(key, value.toString());
                replace = true;
            }
        }
        if(replace){
            // 获取段落中的行数
            List<XWPFRun> runList = para.getRuns();
            for (int i=runList.size();i>=0;i--){
                // 删除之前的行
                para.removeRun(i);
            }
            // 创建一个新的文本并设置为替换后的值 这样操作之后之前文本的样式就没有了，待改进
            para.createRun().setText(sourceText);
        }
    }

}
