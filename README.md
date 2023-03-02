# Pi.java

    - This class counts the PI until the decimalCount = 70.
    
    - this class uses the RatNum.java, and
    - it uses the Spigot algorithm for counting the PI :https://en.wikipedia.org/wiki/Pi#Spigot_algorithms

    ```java 
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

    ```

# RatNum.java

- This class implements RatNum numbers and implements usefull methods such as: 
    ```java 
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
    ```
and equals, sub, div, parse, ...