import java.util.*;
import java.math.BigInteger;
import java.text.ParseException;

/**
 * klassen RatNum beskriver rationellatal
 * ett rationellttal : a/b  
 */
    public class RatNum {
    private BigInteger a;
    private BigInteger b;  
    //.........................................................STEG 1.......................................................//
    /**metoden räknar ut största gemensamma dividerare för två heltal a och b
     * @param int,int 
     */
    public static int gcd(int a, int b){ 
        int rtrnValue = 1;
        if(a == 0 && b == 0){
            throw new IllegalArgumentException();
        }
        if(a<0){
            a -= 2*a; 
        }
        if(b<0){
            b -= 2*b; 
        }
        if(a==0){
            rtrnValue = b;
        }
        if(b==0){
            rtrnValue = a;
        }else{
        for(int i=1; i<=a && i<=b; i++){
            if(a % i==0 && b % i == 0){
                rtrnValue = i;
            }
        }
        }
        return rtrnValue;
    }
    
    //........................................................STEG 2........................................................//
    public RatNum(){
        this.a = new BigInteger(String.valueOf(0));
        this.b = new BigInteger(String.valueOf(1));
    }
    public RatNum(int a){
        this.a = new BigInteger(String.valueOf(a));
        this.b = new BigInteger(String.valueOf(1));
    }
    public RatNum(int a, int b){// cons int
        this(new BigInteger(String.valueOf(a)), new BigInteger(String.valueOf(b)));
    }
    public RatNum(RatNum r){
        this.a = r.a;   
        this.b = r.b;   
    }
    private RatNum(BigInteger a, BigInteger b){
        BigInteger valOfGcd = a.gcd(b); 
        BigInteger zero = new BigInteger(String.valueOf(0));
        BigInteger one  = new BigInteger(String.valueOf(1));
        if(valOfGcd.compareTo(one) == 0){
            this.a=a;
            this.b=b;
        }
        if(b.compareTo(zero)==0){
            throw new NumberFormatException("Denominator = 0");
        }
        else{
            if(b.compareTo(zero)==-1){
                a = a.negate();
                b = b.negate();
                this.a = a.divide(valOfGcd);
                this.b = b.divide(valOfGcd);
            }
            else{
                this.a = a.divide(valOfGcd);
                this.b = b.divide(valOfGcd);
            }
        }
    }
    /**retunerar täljare */
    public int getNumerator(){
        return this.a.intValue();
    }
    /**retunerar nämnare */
    public int getDenominator(){
        return this.b.intValue();
    }
    //...................................................this.pow(int);....................................................//  
    public RatNum pow(int k) { 
        RatNum b = new RatNum(1,1);
        if(k>1){
            for(int i = 1; i<=k ; i++){
                b = b.mul(this);
            }
        }
        if(k == 1){
            b = this;
        }
        return b;
    }
    //....................................................STEG3............................................................//
    /**retunerar till läsbar form d.v.s. a/b  */
    public String toString(){
        return this.a + "/" + this.b;
    }
    /**metoden undersöker en text, om texten beskriver en rationellt tal på ett korrekt sätt,
     * skapas ett nytt rationellt tal ur texten och retunerar nya talet
     * @param String 
     */
    public static RatNum parse(String s){
        String s1 = ""; //sparar täljare
        String s2 = ""; //sparar nämnare
        
        if(!(s.contains("/"))){  
         s = s.concat("/1");
        }
        int indexOfSlash = s.indexOf("/");
        for(int i=0; i<s.length(); i++){
            if(i<indexOfSlash){
                    s1 += s.charAt(i);
            }
            if(i>indexOfSlash){
                  s2 += s.charAt(i);         
            }
            }            
            return new RatNum(new BigInteger(String.valueOf(Integer.parseInt(s1))), new BigInteger(String.valueOf(Integer.parseInt(s2))));
    }

    public RatNum(String s){
        this(parse(s));        
    }
    /**metoden retunerar true om objekten som parameter är lika med rationella talet som objekt
     * @param Objekt
     */
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(other.getClass() != this.getClass()){
            return false;
        }
        RatNum x = (RatNum)other;
        if(x.a.compareTo(this.a) != 0 || x.b.compareTo(this.b) != 0){
            return false;
        }
        return true;
    }
    /**metoden retunerar true om rationella talet som parameter är mindre än akuella talet 
     * @param RatNum class som typ
    */
    public boolean lessThan(RatNum r){ 
        BigInteger a = this.a; 
        BigInteger b = this.b; 
        BigInteger c = r.a;
        BigInteger d = r.b;
        a = a.multiply(d);
        b = b.multiply(c);
        if(a.compareTo(b)==1 || a.compareTo(b)==0){
            return false;
        }
        return true;
        
    }
    /**metoden retunerar ett rationell tal som är summan av två rationella tal
     * @param RatNum class som typ
     */
    public RatNum add(RatNum r){ 
        BigInteger a = this.a; 
        BigInteger b = this.b; 
        BigInteger c = r.a;
        BigInteger d = r.b;
        b = d.multiply(b); //bd
        a = a.multiply(d); //ad
        c = c.multiply(this.b); //cb
        a = a.add(c); //ad+cb
        return new RatNum(a, b);
    }
    /**metoden retunerar ett rationell tal som är substraktion av två rationella tal 
     * @param RatNum class som typ
    */
    public RatNum sub(RatNum r){
        BigInteger a = this.a; 
        BigInteger b = this.b; 
        BigInteger c = r.a;
        BigInteger d = r.b;
        b = d.multiply(b); //bd
        a = a.multiply(d); //ad
        c = c.multiply(this.b); //cb
        a = a.subtract(c); //ad+cb
        return new RatNum(a, b);
    }
    /**metoden retunerar ett rationell tal som är produkt av två rationella tal 
     * @param RatNum class som typ
    */
    public RatNum mul(RatNum r){
        BigInteger a = this.a; 
        BigInteger b = this.b; 
        BigInteger c = r.a;
        BigInteger d = r.b;
        a = a.multiply(c);
        b = b.multiply(d);
        return new RatNum(a, b);
    }
    /**metoden retunerar ett rationell tal som är division av två rationella tal 
     * @param RatNum class som typ
    */
    public RatNum div(RatNum r){
        BigInteger a = this.a; 
        BigInteger b = this.b; 
        BigInteger c = r.a;
        BigInteger d = r.b;
        a = a.multiply(d); //a*d
        b = b.multiply(c); //b*c
        return new RatNum(a, b);
    }
    /**avrundar till ett rationellt tal till närmare noll och retunerar i form av String */
    public String toIntString(){
        String s = "";
        return s += this.a.divide(this.b);
    }
}



