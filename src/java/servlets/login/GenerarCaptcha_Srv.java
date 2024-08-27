/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel Melendrez
 */
public class GenerarCaptcha_Srv extends HttpServlet {

    private static final String ALPHANUMERIC_CHARS = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int CODE_LENGTH = 6;

    public static String generateRandomCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(ALPHANUMERIC_CHARS.length());
            char randomChar = ALPHANUMERIC_CHARS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8;autoFlush=true");

        try {
            int width = 1000;
            int height = 200;
            HttpSession session_actual = request.getSession(true);
            String codigo_generado = generateRandomCode();
            session_actual.invalidate();
            HttpSession nueva_session = request.getSession(true);

            GradientPaint background = new GradientPaint(0, height / 4, Color.BLUE, 0, height, Color.white, false);
            Color fbl = new Color(255, 153, 0);
            Font fnt = new Font("Tahoma", 1, 13);
            BufferedImage cpimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = cpimg.createGraphics();
            g.setPaint(background);
            g.fillRect(0, 0, width, height);
            g.setColor(fbl);
            g.setFont(fnt);
            g.setFont(g.getFont().deriveFont(100f));
            SecureRandom random = new SecureRandom();
            StringBuilder sb = new StringBuilder(CODE_LENGTH);
            int minX = 50;
            int minY = 80;
            int maxX = 100;
            int maxY = 190;
            int randomNumberX = 0;
            int randomNumberY = 0;
            for (int i = 0; i < CODE_LENGTH; i++) {
                int randomIndex = random.nextInt(ALPHANUMERIC_CHARS.length());
                char randomChar = ALPHANUMERIC_CHARS.charAt(randomIndex);
                randomNumberX = generateRandomNumber(minX, maxX);
                randomNumberY = generateRandomNumber(minY, maxY);
                if (randomNumberX > width) {
                    randomNumberX = randomNumberX - 20;

                }
                if (randomNumberY > height) {
                    randomNumberY = randomNumberY - 10;
                }
                g.drawString(randomChar + "", randomNumberX, randomNumberY);
                sb.append(randomChar);
                minX = randomNumberX + 50;
                maxX = randomNumberX + 200;
            }
            codigo_generado = sb.toString();
            g.dispose();
            response.setContentType("image/png");
            OutputStream strm = null;
            nueva_session.setAttribute("codigoCaptcha", codigo_generado);
            strm = response.getOutputStream();
            ImageIO.write(cpimg, "png", strm);
            cpimg.flush();
            strm.flush();
            strm.close();
            strm = null;
            response.flushBuffer();
        } catch (IOException ex) {
            // out.println(ex.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
