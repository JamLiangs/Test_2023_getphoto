package com;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ImageToCharImage {
    private static final int WIDTH = 80; // 字符图片的宽度
    private static final int HEIGHT = 50; // 字符图片的高度
    private static final String CHARACTERS = "@%#*+=-:. "; // 使用的字符集

    public static void main(String[] args) {
        String imagePath = "D:/桌面/微信图片_20230804103339.jpg";
        String charImagePath = "D:/桌面/char_image22.txt";
        try {
            // 读取图片文件为BufferedImage
            BufferedImage image = ImageIO.read(new File(imagePath));

            // 缩放图片
            int scaledWidth = (int) (image.getWidth() * 1.0 / image.getHeight() * HEIGHT);
            int scaledHeight = HEIGHT;
            Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

            // 转换为字符图片
            String charImage = convertImageToCharImage(scaledImage);

            // 保存字符图片到文本文件
            saveCharImageToFile(charImage, charImagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertImageToCharImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Color color = new Color(bufferedImage.getRGB(x * bufferedImage.getWidth() / WIDTH, y * bufferedImage.getHeight() / HEIGHT));
                int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                int charIndex = (int) (gray / 255.0 * (CHARACTERS.length() - 1));
                sb.append(CHARACTERS.charAt(charIndex));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static void saveCharImageToFile(String charImage, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.write(charImage);
        }
    }
}