package com.ly.demo.aspose;

import com.aspose.words.*;
import com.ly.M;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.cglib.core.internal.Function;

/**
 * @author ：LY
 * @date ：2020/9/10 11:27
 * @description ：wo
 */


public class Word2PdfAsposeUtil {


    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = M.class.getClassLoader().getResourceAsStream("license.xml"); // license.xml应放在..\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean doc2pdf(String inPath, String outPath) {
        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
            return false;
        }
        FileOutputStream os = null;
        try {
            long old = System.currentTimeMillis();
            File file = new File(outPath); // 新建一个空白pdf文档
            os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
            // EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            System.out.println("pdf转换成功，共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
    /**
     * 加水印方法
     *
     * @param doc           word文件流
     * @param watermarkText 水印内容
     */
    public static void insertWatermarkText(Document doc, String watermarkText) {
        getLicense();
        // 居中
        insertWatermarkText(doc, watermarkText, new Function<Shape, Object>() {
            @Override
            public Object apply(Shape watermark) {
                // Place the watermark in the page center.
                watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
                watermark.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
                watermark.setWrapType(WrapType.NONE);
                watermark.setVerticalAlignment(VerticalAlignment.CENTER);
                watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);
                return null;
            }
        });
        // 顶部
        insertWatermarkText(doc, watermarkText, new Function<Shape, Object>() {
            @Override
            public Object apply(Shape watermark) {
                watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.MARGIN);
                watermark.setRelativeVerticalPosition(RelativeVerticalPosition.MARGIN);
                watermark.setWrapType(WrapType.NONE);
                //  我们需要自定义距离顶部的高度
                watermark.setVerticalAlignment(VerticalAlignment.TOP);
                watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);
                //  watermark.setTop(120);
                return null;
            }
        });

        //尾部
        insertWatermarkText(doc, watermarkText, new Function<Shape, Object>() {
            @Override
            public Object apply(Shape watermark) {
                watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.MARGIN);
                watermark.setRelativeVerticalPosition(RelativeVerticalPosition.MARGIN);
                watermark.setWrapType(WrapType.NONE);
                // 我们需要自定义距离顶部的高度
                watermark.setVerticalAlignment(VerticalAlignment.BOTTOM);
                watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);
                // 设置距离顶部的高度
                //   watermark.setTop(480);

                return null;
            }
        });
    }

    private static void insertWatermarkText(Document doc, String watermarkText,
                                            Function<Shape, Object> watermaskPositionConfigFunc) {

        Shape watermark = new Shape(doc, ShapeType.TEXT_PLAIN_TEXT);
        watermark.getTextPath().setText(watermarkText);
        // 这里设置为宋体可以保证在转换为PDF时中文不是乱码.
        watermark.getTextPath().setFontFamily("WeiRuanYaHei");
        //WeiRuanYaHei 宋体

        try {
            // 水印大小
            watermark.setWidth(150);
            watermark.setHeight(30);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 左下到右上
        watermark.setRotation(-20);
//字体RGB颜色
        final String colorStr = "EE8262";
        watermark.getFill().setColor(new java.awt.Color(Integer.parseInt(colorStr, 16)));
        watermark.setStrokeColor(new java.awt.Color(Integer.parseInt(colorStr, 16)));
        watermaskPositionConfigFunc.apply(watermark);
        Paragraph watermarkPara = new Paragraph(doc);
        watermarkPara.appendChild(watermark);
        for (Section sect : doc.getSections()) {
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_PRIMARY);

        }

    }

    private static void insertWatermarkIntoHeader(Paragraph watermarkPara, Section sect,
                                                  int headerType) {
        HeaderFooter header = sect.getHeadersFooters().getByHeaderFooterType(headerType);
        if (header == null) {
            header = new HeaderFooter(sect.getDocument(), headerType);
            sect.getHeadersFooters().add(header);
        }
        try {
            header.appendChild(watermarkPara.deepClone(true));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}