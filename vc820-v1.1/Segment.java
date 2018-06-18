
/**
 * Write a description of class Segment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Segment
{
    private byte  value;
    private boolean dot;
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    
    public Segment(){
        this.value = -1;
        dot = false;
        A = false;
        B = false;
        C = false;
        D = false;
        E = false;
        F = false;
        G = false;
    }
    
    public boolean getDot(){
        return this.dot;
    }
    public  void setDot(boolean in){
        this.dot = in;
    }
    public void setA(boolean in){
        this.A = in;
    }
    public void setB(boolean in){
        this.B = in;
    }
    public void setC(boolean in){
        this.C = in;
    }
    public void setD(boolean in){
        this.D = in;
    }
    public void setE(boolean in){
        this.E = in;
    }
    public void setF(boolean in){
        this.F = in;
    }
    public void setG(boolean in){
        this.G = in;
    }
    
    
    public byte toByte(){
        this.value=0;        //DO NOT USE SWITCH! MUST CHECK ALL SEGMENTS
        if(A){
            this.value+=64;
        }
                
        if(B){
             this.value+=32;
        }
                
        if(C){
             this.value+=16;
        }
                
        if(D){
             this.value+=8;
        }
                
        if(E){
             this.value+=4;
        }  
        
        if(F){
             this.value+=2;
        }
        
        if(G){
             this.value+=1;
        }
        return  this.value;
    }
    
    
    /*
    public int convert(){
            //System.out.println(toByte());
            if(toByte()==0b01111110){
                return 0;
            }
            if(toByte()==0b00110000){
                return 1;
            }
            if(toByte()==0b01101101){
                return 2;
            }
            if(toByte()==0b01111001){
                return 3;
            }
            if(toByte()==0b00110011){
                return 4;
            }
            if(toByte()==0b01011011){
                return 5;
            }
            if(toByte()==0b01011111){
                return 6;
            }
            if(toByte()==0b01110000){
                return 7;
            }
            if(toByte()==0b01111111){
                return 8;
            }
            if(toByte()==0b01111011){
                return 9;
            }
            else{
                return -1;
            }
            
        }    
    
    */
    
    
    
    public int convert(){
            switch (this.toByte()){
                case 0b01111101:{
                    return 0;
                }
                case (0b00000101):{
                    return 1;
                }
                case (0b01011011):{
                    return 2;
                }
                case (0b00011111):{
                    return 3;
                }
                case (0b00100111):{
                    return 4;
                }
                case (0b00111110):{
                    return 5;
                }
                case (0b01111110):{
                    return 6;
                }
                case (0b00010101):{
                    return 7;
                }
                case (0b01111111):{
                    return 8;
                }
                case (0b00111111):{
                    return 9;
                }
                case (0b01101000):{
                    return -2;
                }
                //return -1;
            }
            return -1;
        }
        
       
    }
