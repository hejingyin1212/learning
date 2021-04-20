package com.ly.demo.aspose;

import com.aspose.words.Document;

/**
 * @author ：LY
 * @date ：2020/9/11 14:56
 * @description ：Test
 */

public class Test {
    public static void main(String[] args) {
        //这个加水印文字方法可用
        try {
            Document document = new Document("C:\\Users\\Hjy\\Desktop\\Test\\Test-in.docx");
            Word2PdfAsposeUtil.insertWatermarkText(document, "我的水印");
            //文件输出路径
            document.save("C:\\Users\\Hjy\\Desktop\\Test\\out\\Test-out.docx");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
