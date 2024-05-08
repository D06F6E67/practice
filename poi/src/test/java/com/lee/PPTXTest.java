package com.lee;

import org.apache.poi.xslf.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lee
 */
@SpringBootTest
public class PPTXTest {

    @Test
    public void test() throws IOException {
        // 打开文件
        Resource resource = new ClassPathResource("test.pptx");
        XMLSlideShow ppt = new XMLSlideShow(resource.getInputStream());

        // Map<String, String> update = new HashMap<>();
        // update.put("title", "测试");
        // update.put("author", "Lee");
        // home(ppt, update);


        directory(ppt);


        deleteTemplate(ppt, 19);

        // 写入文件
        ppt.write(Files.newOutputStream(Paths.get("/Users/lee/Projects/IDEA/practice/poi/src/main/resources/test2.pptx")));
        ppt.close();
    }

    private void home(XMLSlideShow ppt, Map<String, String> update) {
        // 获取首页ppt
        XSLFSlide homeTemplate = ppt.getSlides().get(0);

        // 创建一页ppt
        XSLFSlide slideNew = ppt.createSlide();
        // 复制模板
        slideNew.importContent(homeTemplate.getShapes().get(0).getSheet());
        // homeTemplate.getShapes().forEach(shape -> slideNew.importContent(shape.getSheet()));
        // 修改值
        setText(slideNew, update);
    }

    private void directory(XMLSlideShow ppt) throws IOException {

        // XMLSlideShow ppt1 = new XMLSlideShow();

        List<XSLFSlideMaster> slideMasters = ppt.getSlideMasters();

        // 获取目录ppt
        // XSLFSlide directoryTemplate = ppt.getSlides().get(1);

        // 创建一页ppt
        XSLFSlide slideNew = ppt.createSlide();
        // XSLFSlide slideNew = ppt1.createSlide();
        // 复制模板
        // slideNew.importContent(directoryTemplate.getShapes().get(0).getSheet());
        // directoryTemplate.getShapes().forEach(shape -> slideNew.importContent(shape.getSheet()));
        slideNew.importContent(slideMasters.get(0));


        new ArrayList<>(slideNew.getShapes()).forEach(shape -> {
            // 处理文本类型 XSLFGroupShape
            if (shape instanceof XSLFGroupShape) {
                XSLFGroupShape groupShape = (XSLFGroupShape) shape;
                Rectangle2D anchor = groupShape.getAnchor();

                anchor.setFrame(0.0, 0.0, anchor.getWidth(), anchor.getHeight());
                groupShape.setAnchor(anchor);
                // slideNew.removeShape(shape);
                XSLFAutoShape autoShape = slideNew.createAutoShape();
                autoShape.setParent(groupShape.getParent());

                XSLFAutoShape autoShape1 = slideNew.createAutoShape();
                // autoShape1.setShapeType(groupShape.getShapes().get(0).gett());;
            }
        });
        // XSLFSheet sheet = directoryTemplate.getShapes().get(0).getSheet();

        // List<XSLFShape> shapes = slideNew.getShapes();
        // shapes.add();
        // slideNew.addChart();
        //
        // slideNew.appendContent(sheet);


        // // 创建一个空的 Group
        // XSLFGroupShape group = slideNew.createGroup();
        // group.setAnchor(new java.awt.Rectangle(100, 100, 500, 300));  // 设置组的位置和大小
        //
        // // 创建一个 AutoShape（矩形）并添加到 Group 中
        // XSLFAutoShape rectangle = slideNew.createAutoShape();
        // rectangle.setParent(group);
        // rectangle.setShapeType(ShapeType.RECT);
        // rectangle.setAnchor(new java.awt.Rectangle(0, 0, 200, 100));
        // rectangle.setText("Added Rectangle");
        // rectangle.setFillColor(new java.awt.Color(0, 255, 0));
        //
        // // 创建另一个 AutoShape（椭圆）并添加到 Group 中
        // XSLFAutoShape ellipse = slideNew.createAutoShape();
        // ellipse.setParent(group);
        // ellipse.setShapeType(ShapeType.ELLIPSE);
        // ellipse.setAnchor(new java.awt.Rectangle(300, 0, 200, 100));
        // ellipse.setText("Added Ellipse");
        // ellipse.setFillColor(new java.awt.Color(255, 0, 0));



        // directoryTemplate.getShapes().forEach(shape -> slideNew.importContent(shape.getSheet()));
        // 写入文件
        // ppt1.write(Files.newOutputStream(Paths.get("/Users/lee/Projects/IDEA/practice/poi/src/main/resources/ndzj3.pptx")));
        // ppt1.close();
    }

    private void setText(XSLFSlide slide, Map<String, String> update) {

        slide.getShapes().forEach(shape -> {
            // 处理文本类型
            if (shape instanceof XSLFTextShape) {

                XSLFTextShape textShape = (XSLFTextShape) shape;

                textShape.getTextParagraphs().forEach(
                        paragraph ->
                                paragraph.getTextRuns().forEach(
                                        run -> {
                                            // 获取原始文本
                                            String rawText = run.getRawText();
                                            String tagName = getTagName(rawText);
                                            if (Objects.nonNull(tagName)) {
                                                String value = update.get(tagName);
                                                rawText = rawText.replace(String.format("{{%s}}", tagName), value);
                                                run.setText(rawText);
                                            }
                                        })
                );
            }
        });
    }

    private void deleteTemplate(XMLSlideShow ppt, int pages) {
        for (int i = 0; i < pages; i++) {
            ppt.removeSlide(0);
        }
    }

    private String getTagName(String text) {
        // 定义正则表达式
        String regex = "\\{\\{(.+?)\\}\\}";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);

        // 创建 Matcher 对象
        Matcher matcher = pattern.matcher(text);

        // 查找匹配
        if (matcher.find()) {
            // 获取匹配到的内容
            return matcher.group(1);
        }

        return null;
    }
}
