package st0gr.pdf;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.multipdf.LayerUtility;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.util.Matrix;

/**
 * MergeUpperHalves is a tool to merge the top halves of two consecutive pages from a PDF
 * into a single page in a new PDF document. This utility is useful for reducing the
 * number of pages by half while maintaining the essential content from each page.
 * I use it to merge bording passes 2 by 1 into one pdf to save on paper and space.
 *
 * Usage:
 * java -jar MergeUpperHalves.jar path/to/input.pdf
 *
 * The program outputs a modified PDF file with '_modified' appended to the original filename.
 */

public class MergeUpperHalves {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: java MergeUpperHalves <sourceFile.pdf>");
            System.exit(1);
        }
        String srcFilePath = args[0];
        mergeUpperHalves(srcFilePath);
    }

    public static void mergeUpperHalves(String srcFilePath) throws IOException {
        File srcFile = new File(srcFilePath);
        PDDocument pdfDocument = PDDocument.load(srcFile);
        PDDocument outPdf = new PDDocument();

        for (int i = 0; i < pdfDocument.getNumberOfPages(); i += 2) {
            PDPage page1 = pdfDocument.getPage(i);
            PDPage page2 = i + 1 < pdfDocument.getNumberOfPages() ? pdfDocument.getPage(i + 1) : null;

            PDRectangle pdf1Frame = page1.getCropBox();
            float newHeight = pdf1Frame.getHeight() / 2;
            PDRectangle outPdfFrame = new PDRectangle(pdf1Frame.getWidth(), newHeight * 2);

            PDPage newPage = new PDPage(outPdfFrame);
            outPdf.addPage(newPage);

            LayerUtility layerUtility = new LayerUtility(outPdf);
            PDFormXObject formPdf1 = layerUtility.importPageAsForm(pdfDocument, page1);

            // Draw the first page (odd) at the top half of the new page
            PDPageContentStream cs1 = new PDPageContentStream(outPdf, newPage, PDPageContentStream.AppendMode.APPEND, true, true);
            cs1.transform(Matrix.getTranslateInstance(0, 0)); //newHeight
            cs1.drawForm(formPdf1);
            cs1.close();

            if (page2 != null) {
                PDFormXObject formPdf2 = layerUtility.importPageAsForm(pdfDocument, page2);

                // Draw the second page (even) at the bottom half of the new page
                PDPageContentStream cs2 = new PDPageContentStream(outPdf, newPage, PDPageContentStream.AppendMode.APPEND, true, true);
                cs2.transform(Matrix.getTranslateInstance(0, -newHeight));
                cs2.drawForm(formPdf2);
                cs2.close();
            }
        }

        String outFilePath = getOutputFilePath(srcFilePath);
        outPdf.save(outFilePath);
        outPdf.close();
        pdfDocument.close();
        System.out.println("PDF processed successfully. Output saved to: " + outFilePath);
    }

    private static String getOutputFilePath(String srcFilePath) {
        int dotIndex = srcFilePath.lastIndexOf('.');
        String baseName = (dotIndex == -1) ? srcFilePath : srcFilePath.substring(0, dotIndex);
        return baseName + "_modified.pdf";
    }
}
