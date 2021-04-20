package com.ly.demo.doc;

import com.spire.doc.*;
import com.spire.doc.documents.WatermarkLayout;

import java.awt.*;

/**
 * @author ：LY
 * @date ：2020/8/15 15:14
 * @description ：Demo
 */

public class Demo {
    public static void main(String[] args) {
        //加载模板文档
        Document template = new Document();
        template.loadFromFile("C:\\Users\\Hjy\\Desktop\\template.docx");
        //获取section
        Section section1 = template.getSections().get(0);
        //获取文档1的页眉页脚
        HeaderFooter header = section1.getHeadersFooters().getHeader();
        HeaderFooter footer = section1.getHeadersFooters().getFooter();


        //加载修改前文档
        Document doc = new Document();
        doc.loadFromFile("C:\\Users\\Hjy\\Desktop\\Test-in.docx");
        Section s = doc.getSections().get(0);

        HeaderFooter h = s.getHeadersFooters().getHeader();
        HeaderFooter f = s.getHeadersFooters().getFooter();

        //清空原有页眉页脚
        h.getChildObjects().clear();
        f.getChildObjects().clear();

        for (int i = 0; i< doc.getSections().getCount();i++)
        {
            Section section2 = doc.getSections().get(i);
            //遍历页眉中的对象
            for(int j = 0 ; j< header.getChildObjects().getCount();j++)
            {
                //获取页眉中的所有子对象
                Object object1 = header.getChildObjects().get(j);
                //复制文档1的页眉添加到文档2
                section2.getHeadersFooters().getHeader().getChildObjects().add(((DocumentObject) object1).deepClone());
            }

            //同理复制页脚
            for(int z = 0 ; z< footer.getChildObjects().getCount();z++)
            {
                Object object2 = footer.getChildObjects().get(z);
                section2.getHeadersFooters().getFooter().getChildObjects().add(((DocumentObject) object2).deepClone());
            }
        }
        insertTextWatermark(s);

        //保存文档
        doc.saveToFile("C:\\Users\\Hjy\\Desktop\\Test-out.docx", FileFormat.Docx);
    }
    private static void insertTextWatermark(Section section) {
        TextWatermark txtWatermark = new TextWatermark();
        txtWatermark.setText("国瓴云签版权所有");
        txtWatermark.setFontSize(40);
        txtWatermark.setColor(Color.lightGray);
        txtWatermark.setLayout(WatermarkLayout.Diagonal);
        section.getDocument().setWatermark(txtWatermark);
    }

}
