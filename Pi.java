import java.math.BigInteger;

import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

public class Pi {

    public static void main(String[] args) {
        int decimalCount = 70;
        if (args.length > 0) {
            decimalCount = Integer.valueOf(args[0]);
        }

        RatNum res = new RatNum(0,1);        
        for(int i = 0; i<=decimalCount; i++){//epsilon
            RatNum oneDivSixK = new RatNum(1,16);
            oneDivSixK = oneDivSixK.pow(i);
            int j = i*8; 
            RatNum d1 = new RatNum(4, j+1);
            RatNum d2 = new RatNum(2, j+4);
            RatNum d3 = new RatNum(1, j+5);
            RatNum d4 = new RatNum(1, j+6);
            res = res.add(oneDivSixK.mul(d1.sub(d2).sub(d3).sub(d4)));
        }

        // kod för utskriften (behöver inte ändras)
        // denna kod antar att den första decimalen av res är något annat än 0
        // (i pi är den första decimalen 1)
        RatNum m = new RatNum(1,1);
        RatNum ten = new RatNum(10,1);
        for (int k=0; k < decimalCount; k++) {
            m = m.mul(ten);
        }
        System.out.print("pi = ");
        String intPart = res.toIntString();
        System.out.print(intPart);
        System.out.print(".");
        RatNum digits = res.sub(RatNum.parse(intPart));
        System.out.print(digits.mul(m).toIntString());
        System.out.println("...");
    }

}