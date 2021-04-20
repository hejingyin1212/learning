package com.ly.demo.jacob;

/**
 * @author ：LY
 * @date ：2020/9/15 15:46
 * @description ：doc2docx
 */

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import java.io.File;

class Main {
    public static void main(String[] args) {
        Main dfc = new Main();
        String srcDocPath = "C:\\Users\\Hjy\\Desktop\\Test\\jacob\\Test-in.doc";
        dfc.wordConveter(srcDocPath,"C:\\Users\\Hjy\\Desktop\\Test\\jacob\\Test-in.docx");
    }
    public void wordConveter(String src,String dist) {
        File file = new File(src);
        // 启动word应用程序(Microsoft Office Word 2003)
        ActiveXComponent app = new ActiveXComponent("Word.Application");
        System.out.println("正在转换     "+file.getAbsolutePath()+"");
        try {
            // 设置word应用程序不可见
            app.setProperty("Visible", new Variant(false));
            // documents表示word程序的所有文档窗口，（word是多文档应用程序）
            Dispatch docs = app.getProperty("Documents").toDispatch();
            // 打开要转换的word文件
            Dispatch doc = Dispatch.invoke(
                    docs,
                    "Open",
                    Dispatch.Method,
                    new Object[] { file.getAbsolutePath(), new Variant(false),
                            new Variant(true) }, new int[1]).toDispatch();
            int type=12;
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
                    dist, new Variant(type) }, new int[1]);
            // 关闭word文件
            Dispatch.call(doc, "Close", new Variant(false));
        } catch (Exception e) {
            System.out.println("*******转换出错********");
        } finally {
            // 关闭word应用程序
            app.invoke("Quit", new Variant[] {});
        }
        System.out.println("*******转换完毕********");
    }
}
