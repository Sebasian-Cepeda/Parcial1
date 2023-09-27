package edu.eci.arsw.math;

import java.util.stream.Stream;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {

    private static ParcialThread[] pts;

    /**
     * Returns a range of hexadecimal digits of pi.
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count, int N) {
        if (start < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        if (count < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        
        pts = new ParcialThread[N];
        int aux = count/N;
        for(int i = 0; i<N;i++){
            if(i == N-1){
                aux += count%N;
            }
            pts[i] = new ParcialThread(start,aux);
            pts[i].start();
            start +=aux;
        }

        byte[] digits = new byte[count];
        int cont = 0;
        for(int i = 0; i<pts.length;i++){
            try {
                pts[i].join();
                for(int j = 0; j<pts[i].getDigits().length;j++){
                    digits[cont] = pts[i].getDigits()[j];
                    cont++;
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }

        return digits;
    }

}