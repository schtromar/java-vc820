
/**
 * Write a description of class Lcd here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lcd
{
    private boolean AC;
    private boolean DC;
    private boolean auto;
    private boolean rs232;
    private boolean minus;
    public Segment one = new Segment();
    public Segment two = new Segment();
    public Segment three = new Segment();
    public Segment four = new Segment();
    private boolean micro;
    private boolean nano;
    private boolean kilo;
    private boolean diode;
    private boolean mili;
    private boolean percent;
    private boolean mega;
    private boolean beep;
    private boolean farads;
    private boolean ohms;
    private boolean delta;
    private boolean hold;
    private boolean amps;
    private boolean volts;
    private boolean hertz;
    private boolean low;
    
    public Lcd(){
        this.AC = false;
        this.DC = false;
        this.auto = false;
        this.rs232 = false;
        this.minus = false;
    
        this.micro = false;
        this.nano = false;
        this.kilo = false;
        this.diode = false;
        this.mili = false;
        this.percent = false;
        this.mega = false;
        this.beep = false;
        this.farads = false;
        this.ohms = false;
        this.delta = false;
        this.hold = false;
        this.amps = false;
        this.volts = false;
        this.hertz = false;
        this.low = false;
    }
    
    public void setAC(boolean in){
        this.AC = in;
    }
    public void setDC(boolean in){
        this.DC = in;
    }
    public void setAuto(boolean in){
        this.auto = in;
    }
    public void setRs232(boolean in){
        this.rs232 = in;
    }
    public void setMinus(boolean in){
        this.minus = in;
    }
    public void setMicro(boolean in){
        this.micro = in;
    }
    public void setNano(boolean in){
        this.nano = in;
    }
    public void setKilo(boolean in){
        this.kilo = in;
    }
    public void setDiode(boolean in){
        this.diode = in;
    }
    public void setMili(boolean in){
        this.mili = in;
    }
    public void setPercent(boolean in){
        this.percent = in;
    }
    public void setMega(boolean in){
        this.mega = in;
    }
    public void setBeep(boolean in){
        this.beep = in;
    }
    public void setFarads(boolean in){
        this.farads = in;
    }
    public void setOhms(boolean in){
        this.ohms = in;
    }
    public void setDelta(boolean in){
        this.delta = in;
    }
    public void setHold(boolean in){
        this.hold = in;
    }
    public void setAmps(boolean in){
        this.amps = in;
    }
    public void setVolts(boolean in){
        this.volts = in;
    }
    public void setHertz(boolean in){
        this.hertz = in;
    }
    public void setLow(boolean in){
        this.low = in;
    }
    
    
  
    public String toString(){
        //System.out.println((Float.toString(this.toFloat()) + " " + this.getPrefix() + "" + this.getUnits()));
        //System.out.println("bigga");
        return (Float.toString(this.toFloat()) + " " + this.getUnits());
    }
    
    
    public String getFlags(){
        String out = "";
        if(this.auto){
            out = out + " AUTO "; 
        }
        if (this.rs232){
            out = out + " RS232 ";
        }
        if (this.diode){
            out = out + " D ";
        }
        if (this.hold){
            out = out + " Hold ";
        }
        if (this.delta){
            out = out + " Δ ";
        }
        if (this.low){
            out = out + " \uD83D\uDD0B ";
        }
        return out;
    }
      
    
    public String getPrefix(){
        if(this.micro){
            return "μ" + acDc();
        } else if(this.nano){
            return "n";
        } else if(this.kilo){
            return "k";
        } else if(this.mili){
            return "m";
        } else if(this.mega){
            return "M";
        } else{
            return "";
        }
    }
    
    public String getUnits(){
        return this.getPrefix() + this.getBaseUnits();
    }
    
    public String getBaseUnits(){
        if(this.volts){
            return "V" + acDc();
        } else if(this.amps){
            return "A";
        } else if(this.farads){
            return "F";
        } else if(this.ohms){
            return "Ω";
        } else if(this.hertz){
            return "Hz";
        } else if(this.percent){
            return "%";
        }else{
            return "";
        }
    }
    
    public String acDc(){
        if(this.AC){
            return "AC";
        }else if(this.DC){
            return "DC";
        }else{
            return "";
        }
    }
    
    public void printAllSegs(){
            System.out.print(this.one.convert());
            System.out.print(this.two.convert());
            System.out.print(this.three.convert());
            System.out.print(this.four.convert());
            System.out.println();
    }
    
    public float toFloat(){
        float value = this.one.convert()*1000 + this.two.convert()*100 + this.three.convert()*10 + this.four.convert();
        if(this.one.getDot()){
            value = value/1000;
        }else if(this.two.getDot()){
            value = value/100;
        }else if(this.three.getDot()){
            value = value/10;
        }else if(this.four.getDot()){
            value = value/1;
        }
        if(this.minus){
            value = value*(-1);
        }
        
        return value;
    }
    
    public float getTrueValue(){
        if(this.micro){
            return this.toFloat()/1000000;
        } else if(this.nano){
            return this.toFloat()/1000000000;
        } else if(this.kilo){
            return this.toFloat()*1000;
        } else if(this.mili){
            return this.toFloat()/1000;
        } else if(this.mega){
            return this.toFloat()*1000000;
        } else{
            return this.toFloat();
        }
    }
    
    
    public String getBaseUnitValueString(){
        return Float.toString(this.getTrueValue()) + " " + this.getBaseUnits();
    }
}
