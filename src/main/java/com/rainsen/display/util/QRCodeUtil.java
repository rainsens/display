package com.rainsen.display.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeUtil {

    public static void generate(
            String text, int width, int hight, String savePath
    ) throws WriterException, IOException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, hight);
        Path path = FileSystems.getDefault().getPath(savePath);
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
    }
}
