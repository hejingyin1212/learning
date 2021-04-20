package com.ly.demo.doc;

import com.spire.doc.*;
import com.spire.doc.documents.*;
import com.spire.doc.fields.DocPicture;

public class Test {
    public static void main(String[] args) {
        //加载测试文档
        Document doc = new Document();
        doc.loadFromFile("C:\\Users\\Hjy\\Desktop\\Test-in.docx");

        //获取第一节
        Section sec = doc.getSections().get(0);

        //调用insertHeaderAndFooter方法插入页眉页脚到第一个section
        insertHeaderAndFooter(sec);

        //保存文档
        doc.saveToFile("C:\\Users\\Hjy\\Desktop\\Test-out.docx", FileFormat.Docx);
    }

    private static void insertHeaderAndFooter(Section section) {

        //分别获取section的页眉页脚
        HeaderFooter header = section.getHeadersFooters().getHeader();
        HeaderFooter footer = section.getHeadersFooters().getFooter();

        //清空原有页眉页脚
        header.getChildObjects().clear();
        footer.getChildObjects().clear();

        //添加段落到页眉
        Paragraph headerParagraph = header.addParagraph();

        //插入左侧页眉图片
        DocPicture headerPicture = headerParagraph.appendPicture("C:\\Users\\Hjy\\Desktop\\h.JPG");
        headerPicture.setHorizontalAlignment(ShapeHorizontalAlignment.Center);
        headerPicture.setVerticalOrigin(VerticalOrigin.Top_Margin_Area);
        headerPicture.setVerticalAlignment(ShapeVerticalAlignment.Bottom);
        headerPicture.setWidth(420f);
        headerPicture.setHeight(44f);
        headerPicture.setHeightScale(10f);


//        //添加文字到页眉的段落
//        TextRange text = headerParagraph.appendText("Demo of Spire.Doc");
//        text.getCharacterFormat().setFontName("Arial");
//        text.getCharacterFormat().setFontSize(10);
//        text.getCharacterFormat().setItalic(true);
//        headerParagraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Right);
//
//        //设置文字环绕方式
//        headerPicture.setTextWrappingStyle(TextWrappingStyle.Behind);

        //设置页眉段落的底部边线样式
//        headerParagraph.getFormat().getBorders().getBottom().setBorderType(BorderStyle.Single);
//        headerParagraph.getFormat().getBorders().getBottom().setLineWidth(1f);

        //添加段落到页脚
        Paragraph footerParagraph = footer.addParagraph();

        //插入页脚图片
        DocPicture footerPictureRight = footerParagraph.appendPicture("C:\\Users\\Hjy\\Desktop\\f.JPG");
        footerPictureRight.setHorizontalAlignment(ShapeHorizontalAlignment.Right);
        footerPictureRight.setVerticalOrigin(VerticalOrigin.Top_Margin_Area);
        footerPictureRight.setVerticalAlignment(ShapeVerticalAlignment.Bottom);
        footerPictureRight.setWidth(420f);
        footerPictureRight.setHeight(25f);
        footerPictureRight.setHeightScale(10f);


//        //添加Field_Page和Field_Num_Pages域到页脚段落，用于显示当前页码和总页数
//        footerParagraph.appendField("page number", FieldType.Field_Page);
//        footerParagraph.appendText(" of ");
//        footerParagraph.appendField("number of pages", FieldType.Field_Num_Pages);
//        footerParagraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Right);

        //设置页脚段落的顶部边线样式
//        footerParagraph.getFormat().getBorders().getTop().setBorderType(BorderStyle.Single);
//        footerParagraph.getFormat().getBorders().getTop().setLineWidth(1f);
    }
}
