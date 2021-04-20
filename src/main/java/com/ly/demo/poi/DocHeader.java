package com.ly.demo.poi;

import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.OfficeDrawing;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

import java.io.*;

/**
 * @author ：LY
 * @date ：2020/9/15 13:31
 * @description ：DocHeader
 */

public class DocHeader {
    public static void main(String[] args) throws Exception{
        File is = new File("C:\\Users\\Hjy\\Desktop\\Test\\Test-in.doc");//文件路径
        FileInputStream fis = new FileInputStream(is);

        HWPFDocument docx = new HWPFDocument(fis);//文档对象

        createHeader(docx);
        //createFooter(docx);
        //createWatermark(docx);
        //replaceInPara(docx.getHeaderArray().getParagraphArray(0));
    }

    public static void createHeader( HWPFDocument doc) throws Exception {

        OfficeDrawing officeDrawing = new OfficeDrawing() {
            @Override
            public HorizontalPositioning getHorizontalPositioning() {
                return null;
            }

            @Override
            public HorizontalRelativeElement getHorizontalRelative() {
                return null;
            }

            @Override
            public EscherContainerRecord getOfficeArtSpContainer() {
                return null;
            }

            @Override
            public byte[] getPictureData() {
                File file = new File("C:\\Users\\Hjy\\Desktop\\Test\\f.JPG");
                byte[] bytes = new byte[0];
                try {
                    InputStream is = new FileInputStream(file);
                    bytes = toByteArray(is);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return bytes;
            }

            @Override
            public int getRectangleBottom() {
                return 0;
            }

            @Override
            public int getRectangleLeft() {
                return 0;
            }

            @Override
            public int getRectangleRight() {
                return 0;
            }

            @Override
            public int getRectangleTop() {
                return 0;
            }

            @Override
            public int getShapeId() {
                return 0;
            }

            @Override
            public VerticalPositioning getVerticalPositioning() {
                return null;
            }

            @Override
            public VerticalRelativeElement getVerticalRelativeElement() {
                return null;
            }
        };
        doc.getOfficeDrawingsHeaders().getOfficeDrawings().add(officeDrawing);
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
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Hjy\\Desktop\\Test\\out\\Test-out.doc");
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
        doc.write(new FileOutputStream("C:\\Users\\Hjy\\Desktop\\Test\\out\\Test-out.doc"));
        doc.close();
    }
    private static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }
}
