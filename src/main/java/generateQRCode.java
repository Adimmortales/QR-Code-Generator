import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class generateQRCode {
    String text;
    String filePath;
    ImageIcon newIcon;

    public generateQRCode(String text, String filePath) throws IOException, WriterException {
        this.text = text;
        this.filePath = filePath;
        qrCodeGenerator();
    }

    public void qrCodeGenerator() throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        Path path = FileSystems.getDefault().getPath(filePath + ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        newIcon = new ImageIcon(image);

    }
}
