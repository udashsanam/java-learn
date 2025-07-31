//package com.learn;
//
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfReader;
//import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
//
//import java.io.*;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//
//public class PDF {
//
////    public static void main(String[] args) throws Exception {
////        byte[] pdfBytes = Files.readAllBytes(Paths.get("/Users/sanamudash/Desktop/Pharmedica.pdf"));
////
////        // Load PDDocument from byte stream
////        try (PDDocument document = Loader.loadPDF(pdfBytes)) {
////
////            if (document.isEncrypted()) {
////                throw new Exception("Document is encrypted.");
////            }
////
////
////            PDFTextStripper pdfStripper = new PDFTextStripper();
////            String text = pdfStripper.getText(document);
////            System.out.println(text);
////        }
//
//    public static void main(String[] args) {
//
//        Map<String, String> pdfContents = new HashMap<>();
//        System.setProperty("jna.library.path", "/opt/homebrew/lib");
//
//
//        try (ZipInputStream zis =
////                     new ZipInputStream(new FileInputStream("/Users/sanamudash/Desktop/Pharmedica.zip")
//                     new ZipInputStream(new FileInputStream("/Users/sanamudash/Downloads/Wellness Partners - Meltdown Corporate Wellbeing.zip")
//                     )) {
//            ZipEntry entry;
//            byte[] buffer = new byte[4096];
//
//            while ((entry = zis.getNextEntry()) != null) {
//                if (!entry.isDirectory() && entry.getName().toLowerCase().endsWith(".pdf")) {
//
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    int bytesRead;
//                    while ((bytesRead = zis.read(buffer)) != -1) {
//                        baos.write(buffer, 0, bytesRead);
//                    }
//                    byte[] pdfBytes = baos.toByteArray();
//
//                    try (PdfDocument document = new PdfDocument(new PdfReader(new ByteArrayInputStream(pdfBytes)))) {
//                        if (true) {
//
//
//
//                                int numberOfPages = document.getNumberOfPages();
//                                StringBuilder text = new StringBuilder();
//
//                                for (int i = 1; i <= numberOfPages; i++) {
//                                    String pageText = PdfTextExtractor.getTextFromPage(document.getPage(i));
//                                    text.append(pageText).append("\n");
//                                }
//
//                                System.out.println(text.toString());
//                        } else {
//                            pdfContents.put(entry.getName(), "Encrypted PDF - cannot extract text");
//                        }
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//                zis.closeEntry();
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(pdfContents);
//    }
//
//}
