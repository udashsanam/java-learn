package com.learn;

import org.graalvm.polyglot.*;

public class PdfJsInsideJava {
    public static void main(String[] args) {
        try (Context context = Context.newBuilder("js")
                                      .allowIO(true)
                                      .allowAllAccess(true)
                                      .build()) {

            // Load PDF.js library code (must be accessible to Java, e.g., as a file or string)
            String pdfJsCode = new String(java.nio.file.Files.readAllBytes(
                                          java.nio.file.Paths.get("pdf.js"))); 
            context.eval("js", pdfJsCode);

            // Load your PDF bytes as base64 string
            byte[] pdfBytes = java.nio.file.Files.readAllBytes(
                                   java.nio.file.Paths.get("sample.pdf"));
            String base64 = java.util.Base64.getEncoder().encodeToString(pdfBytes);

            // JavaScript code to parse and extract text
            String jsCode = """
                (async function() {
                    const bytes = Uint8Array.from(atob('""" + base64 + """'), c => c.charCodeAt(0));
                    const pdf = await pdfjsLib.getDocument({data: bytes}).promise;
                    let text = '';
                    for (let i = 1; i <= pdf.numPages; i++) {
                        const page = await pdf.getPage(i);
                        const content = await page.getTextContent();
                        text += content.items.map(item => item.str).join(' ') + '\\n';
                    }
                    console.log(text);
                    return text;
                })();
                """;

            Value result = context.eval("js", jsCode);
            System.out.println("Extracted Text:\n" + result.asString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
