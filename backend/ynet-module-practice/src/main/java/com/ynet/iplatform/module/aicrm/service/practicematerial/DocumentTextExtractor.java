package com.ynet.iplatform.module.aicrm.service.practicematerial;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 文档文本抽取器
 * 支持 Word(.doc/.docx)、PDF(.pdf)、Text(.txt) 格式
 *
 * @author AI CRM Team
 */
@Slf4j
@Component
public class DocumentTextExtractor {

    /**
     * 支持的文件格式
     */
    private static final Set<String> SUPPORTED_EXTENSIONS = new HashSet<>(
            Arrays.asList("doc", "docx", "pdf", "txt")
    );

    /**
     * 判断文件格式是否支持
     *
     * @param filename 文件名
     * @return 是否支持
     */
    public boolean isSupportedFormat(String filename) {
        if (filename == null || filename.isEmpty()) {
            return false;
        }
        String extension = getFileExtension(filename);
        return SUPPORTED_EXTENSIONS.contains(extension.toLowerCase());
    }

    /**
     * 抽取文件文本内容
     *
     * @param file 上传的文件
     * @return 文本内容
     * @throws Exception 抽取失败时抛出异常
     */
    public String extractText(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            log.warn("[extractText] 文件为空");
            return "";
        }

        String filename = file.getOriginalFilename();
        if (!isSupportedFormat(filename)) {
            throw new IllegalArgumentException("不支持的文件格式: " + filename);
        }

        String extension = getFileExtension(filename).toLowerCase();
        log.info("[extractText] 开始抽取文件文本: filename={}, extension={}, size={}",
                filename, extension, file.getSize());

        long startTime = System.currentTimeMillis();
        String content;

        try {
            switch (extension) {
                case "doc":
                    content = extractFromDoc(file.getInputStream());
                    break;
                case "docx":
                    content = extractFromDocx(file.getInputStream());
                    break;
                case "pdf":
                    content = extractFromPdf(file.getInputStream());
                    break;
                case "txt":
                    content = extractFromTxt(file.getInputStream());
                    break;
                default:
                    throw new IllegalArgumentException("不支持的文件格式: " + extension);
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("[extractText] 文本抽取完成: filename={}, contentLength={}, elapsedTime={}ms",
                    filename, content.length(), elapsedTime);

            return content;
        } catch (Exception e) {
            log.error("[extractText] 文本抽取失败: filename={}", filename, e);
            throw e;
        }
    }

    /**
     * 从 .doc 文件抽取文本
     */
    private String extractFromDoc(InputStream inputStream) throws Exception {
        try (HWPFDocument document = new HWPFDocument(inputStream);
             WordExtractor extractor = new WordExtractor(document)) {
            return extractor.getText();
        }
    }

    /**
     * 从 .docx 文件抽取文本
     */
    private String extractFromDocx(InputStream inputStream) throws Exception {
        try (XWPFDocument document = new XWPFDocument(inputStream);
             XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {
            return extractor.getText();
        }
    }

    /**
     * 从 PDF 文件抽取文本
     */
    private String extractFromPdf(InputStream inputStream) throws Exception {
        try (PDDocument document = org.apache.pdfbox.Loader.loadPDF(inputStream.readAllBytes())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    /**
     * 从文本文件抽取内容
     */
    private String extractFromTxt(InputStream inputStream) throws Exception {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    /**
     * 获取文件扩展名
     */
    private String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return "";
        }
        return filename.substring(lastDotIndex + 1);
    }
}
