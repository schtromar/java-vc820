import com.fazecast.jSerialComm.*;

/*
final class PacketListener implements SerialPortPacketListener
{
@Override
public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

@Override
public int getPacketSize() { return 14; }

@Override
public void serialEvent(SerialPortEvent event)
{
byte[] newData = event.getReceivedData();
System.out.println("Received data of size: " + newData.length);
for (int i = 0; i < newData.length; ++i)
System.out.print((char)newData[i]);
System.out.println("\n");
}
}
 */


class  DMM /*implements SerialPortPacketListener
{
    
    
    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

    @Override
    public int getPacketSize() { return 14;}
    
    */
    /*
    @Override
    public void serialEvent(SerialPortEvent event)
    {
        byte[] newData = event.getReceivedData();
        //System.out.println("Received data of size: " + newData.length);
        for (int i = 0; i < newData.length; ++i){
            //System.out.println(newData[i]);
            System.out.println(String.format("%8s", Integer.toBinaryString((byte)newData[i] & 0xFF)).replace(' ', '0'));
        }    
        System.out.println(System.nanoTime());
        sevenSegDecode(newData);
        //lcd.printAllSegs();
        System.out.println("higga");
        //System.out.println(lcd.toString());
        //System.out.println(lcd.getPrefix());
        //System.out.println(lcd.getUnits());
    }
    */
    
    
  {  
      
    public static Lcd lcd = new Lcd();  
    static public void main(String[] args)
    {
        while (true){
            SerialPort comPort = SerialPort.getCommPorts()[0];
            comPort.openPort();
            comPort.setBaudRate(2400);
            comPort.setNumDataBits(8);
            comPort.setParity(0);
            comPort.setNumStopBits(1);
            if(comPort.setDTR()==false)
                System.out.println("Could not set DTR, have you tried turning it off and on again?");
            if(comPort.clearRTS()==false)
                System.out.println("Could not set RTS, have you tried turning it off and on again?");
    
                
            PacketListener listener = new PacketListener();
            comPort.addDataListener(listener);
            try { Thread.sleep(5000); } catch (Exception e) { e.printStackTrace(); }
            comPort.removeDataListener();
            comPort.closePort();
        }
    }
    
    
    
    public static void sevenSegDecode(byte[] readByte){
        for(int i=0; i<14; i++){
            sevenSegDecodeDigit(readByte[i]);
        }
    }
    
    public static void /*int*/ sevenSegDecodeDigit(byte readByte){
        //for (int i = 0; i<14; i++/*=8*/){      //Byte modulo 16 is the low nibble, byte div 16 is the high nibble.
            
            switch ((readByte & 0b11110000)/16 /*16/*upper nibble*/){
                case 1:{
                    //System.out.println("0");
                    //AC.set(readByte & 0b00001000);
                    
                    lcd.setAC((readByte & 0b00001000)!=0);
                    lcd.setDC((readByte & 0b00000100)!=0);
                    lcd.setAuto((readByte & 0b00000010)!=0);
                    lcd.setRs232((readByte & 0b00000001)!=0);
                    break;
                    
                } 
                case 2:{
                    //System.out.println("2");
                    lcd.setMinus((readByte & 0b00001000)!=0);
                    lcd.one.setA((readByte & 0b00000100)!=0);
                    lcd.one.setB((readByte & 0b00000010)!=0);
                    lcd.one.setC((readByte & 0b00000001)!=0);
                    break;
                }
                case 3:{
                    //System.out.println("3");
                    lcd.one.setD((readByte & 0b00001000)!=0);
                    lcd.one.setE((readByte & 0b00000100)!=0);
                    lcd.one.setF((readByte & 0b00000010)!=0);
                    lcd.one.setG((readByte & 0b00000001)!=0);
                    break;
                }
                case 4:{
                    //System.out.println("4");
                    lcd.one.setDot((readByte & 0b00001000)!=0);
                    lcd.two.setA((readByte & 0b00000100)!=0);
                    lcd.two.setB((readByte & 0b00000010)!=0);
                    lcd.two.setC((readByte & 0b00000001)!=0);
                    break;
                }
                case 5:{
                    //System.out.println("5");
                    lcd.two.setD((readByte & 0b00001000)!=0);
                    lcd.two.setE((readByte & 0b0000100)!=0);
                    lcd.two.setF((readByte & 0b00000010)!=0);
                    lcd.two.setG((readByte & 0b00000001)!=0);
                    break;
                }
                case 6:{
                    //System.out.println("6");
                    lcd.two.setDot((readByte & 0b00001000)!=0);
                    lcd.three.setA((readByte & 0b00000100)!=0);
                    lcd.three.setB((readByte & 0b00000010)!=0);
                    lcd.three.setC((readByte & 0b00000001)!=0);
                    break;
                }       
                case 7:{
                    //System.out.println("7");
                    lcd.three.setD((readByte & 0b00001000)!=0);
                    lcd.three.setE((readByte & 0b00000100)!=0);
                    lcd.three.setF((readByte & 0b00000010)!=0);
                    lcd.three.setG((readByte & 0b00000001)!=0);
                    break;
                }
                case 8:{
                    //System.out.println("8");
                    lcd.three.setDot((readByte & 0b00001000)!=0);
                    lcd.four.setA((readByte & 0b00000100)!=0);
                    lcd.four.setB((readByte & 0b00000010)!=0);
                    lcd.four.setC((readByte & 0b00000001)!=0);
                    break;
                }        
                case 9:{
                    //System.out.println("9");
                    lcd.four.setD((readByte & 0b00001000)!=0);
                    lcd.four.setE((readByte & 0b00000100)!=0);
                    lcd.four.setF((readByte & 0b00000010)!=0);
                    lcd.four.setG((readByte & 0b00000001)!=0);
                    break;
                }
                
                
                
                case 10:{
                    //System.out.println("10");
                    lcd.setMicro((readByte & 0b00001000)!=0);
                    lcd.setNano((readByte & 0b00000100)!=0);
                    lcd.setKilo((readByte & 0b00000010)!=0);
                    lcd.setDiode((readByte & 0b00000001)!=0);
                    break;
                }    
                case 11:{
                    //System.out.println("11");
                    lcd.setMili((readByte & 0b00001000)!=0);
                    lcd.setPercent((readByte & 0b00000100)!=0);
                    lcd.setMega((readByte & 0b00000010)!=0);
                    lcd.setBeep((readByte & 0b00000001)!=0);
                    break;
                }
                case 12:{
                    //System.out.println("12");
                    lcd.setFarads((readByte & 0b00001000)!=0);
                    lcd.setOhms((readByte & 0b00000100)!=0);
                    lcd.setDelta((readByte & 0b00000010)!=0);
                    lcd.setHold((readByte & 0b00000001)!=0);
                    break;
                }
                case 13:{
                    //System.out.println("13");
                    lcd.setAmps((readByte & 0b00001000)!=0);
                    lcd.setVolts((readByte & 0b00000100)!=0);
                    lcd.setHertz((readByte & 0b00000010)!=0);
                    lcd.setLow((readByte & 0b00000001)!=0);
                    break;
                }/*
                case 14:{
                    //System.out.println("13");
                    lcd.setuser1((readByte & 0b00001000)!=0);
                    lcd.setuser2((readByte & 0b00000100)!=0);
                    lcd.setuser3((readByte & 0b00000010)!=0);
                    lcd.setuser4((readByte & 0b00000001)!=0);
                    break;
                }  */

            //}
            
        }
        //return 0;
    }
    
}

