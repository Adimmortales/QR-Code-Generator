import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Window extends JFrame {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -3277613095633600218L;

    private static JTextField FileName;
    private static JTextField QRCodeInput;
    private static JLabel QRCode;

    public Window() {
        setLayout(null);
        setSize(650, 500);
        setTitle("QR Code Generator ~ Made by Hennes");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // SET NEW ICON
        Image FrameIcon = Toolkit.getDefaultToolkit().getImage("D:\\Icon.png");
        setIconImage(FrameIcon);


        // TEXTFIELDS
        FileName = new JTextField();
        FileName.setSize(300, 30);
        FileName.setLocation(30, 200);
        QRCodeInput = new JTextField();
        QRCodeInput.setSize(300, 30);
        QRCodeInput.setLocation(30, 140);

        // LABELS
        JLabel fileNameLabel = new JLabel("File Name: ");
        fileNameLabel.setSize(250, 30);
        fileNameLabel.setLocation(30, 170);
        JLabel QRCodeInputLabel = new JLabel("QR code content: ");
        QRCodeInputLabel.setSize(250, 30);
        QRCodeInputLabel.setLocation(30, 110);
        QRCode = new JLabel();
        QRCode.setSize(200, 200);
        QRCode.setLocation(350, 125);
        ImageIcon icon = new ImageIcon("D:\\test.png");
        QRCode.setIcon(icon);

        // BUTTON
        JButton create = new JButton("Create");
        create.setSize(90, 30);
        create.setLocation(30, 250);
        create.addActionListener(e -> {
            try {
                generateQRCodeImage(QRCodeInput.getText(), FileName.getText());
                System.out.println("QR Code Generated");
            } catch (WriterException | IOException e1) {
                e1.printStackTrace();
            }
        });

        // ADD WIDGETS
        add(FileName);
        add(QRCodeInput);
        add(fileNameLabel);
        add(QRCodeInputLabel);
        add(create);
        add(QRCode);
    }

    private void generateQRCodeImage(String text, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        Path path = FileSystems.getDefault().getPath(filePath + ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ImageIcon newIcon = new ImageIcon(image);
        QRCode.setIcon(newIcon);
    }

}
