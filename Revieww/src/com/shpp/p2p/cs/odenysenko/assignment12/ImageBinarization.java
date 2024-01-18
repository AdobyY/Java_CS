package com.shpp.p2p.cs.odenysenko.assignment12;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The class converts an image from the user to a black and white image.
 */
public class ImageBinarization implements Constants{
    /**
     * The method converts a grayscale image to a black and white image
     * @param histogram the histogram of the image
     * @param imageGray image in gray tones
     * @return a black and white image
     */
   public static BufferedImage imageBinary(int[] histogram, BufferedImage imageGray){
       BufferedImage imageBinary = new BufferedImage(imageGray.getWidth(), imageGray.getHeight(), BufferedImage.TYPE_INT_RGB);

       int totalPixel = imageGray.getWidth() * imageGray.getHeight(); // total number of pixels
       int threshold = threshold(histogram,totalPixel); // threshold
       if(threshold==0){
           threshold = MAX_LUMINANCE/2;
       }
       //System.out.println(threshold);
       Color color; //pixel color

       for (int row = 0; row < imageGray.getWidth(); ++row) {
           for (int col = 0; col < imageGray.getHeight(); ++col) {
               color = new Color(imageGray.getRGB(row, col));
               int red = color.getRed();
               int green = color.getGreen();
               int blue = color.getBlue();
               if(red<threshold && green<threshold && blue<threshold){
                   imageBinary.setRGB(row, col, BLACK);
               }else{
                   imageBinary.setRGB(row, col, new Color(WHITE, WHITE, WHITE).getRGB());
               }
           }
       }

       return imageBinary;
   }

    /**
     * The method finds a threshold for image binarization using the Otsu method.
     * Method description: https://ru.wikipedia.org/wiki/%D0%9C%D0%B5%D1%82%D0%BE%D0%B4_%D0%9E%D1%86%D1%83
     * @param histogram histogram of the image in gray tones
     * @param totalPixel total number of pixels
     * @return threshold value for image binarization
     */

    private static int threshold(int[] histogram, int totalPixel) {

        int sum = 0;
        for (int i = 0; i < histogram.length; i++){ // calculate sum of all intensities in image
            sum += i * histogram[i];
        }

        double sumB = 0.0; // the sum of one class
        double weightB = 0; // the inferiority of this class
        double weightF;
        double max = 0.0;
        int threshold = 0;

        for(int i = 0; i < histogram.length; i++){
            weightB += histogram[i]; // weight of one class
            if(weightB == 0) {
                continue;
            }
            weightF = totalPixel - weightB; // weight of another class
            if (weightF == 0){
                break;
            }
            sumB += (double) (i * histogram[i]);
            double meanB =  sumB / weightB; // arithmetic average
            double meanF = (sum - sumB)/ weightF;
            double varBetween = weightB * weightF *Math.pow(meanB-meanF,2); // calculate between class variance (dispersion)

            if (varBetween > max){
                max  = varBetween;
                threshold = i;
            }
        }
        return threshold;
    }
}
