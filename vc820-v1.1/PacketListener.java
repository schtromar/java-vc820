

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


class PacketListener implements SerialPortPacketListener
{
    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

    @Override
    public int getPacketSize() { return 14;}

    @Override
    public void serialEvent(SerialPortEvent event)
    {
        byte[] newData = event.getReceivedData();
        //System.out.println("Received data of size: " + newData.length);
        //for (int i = 0; i < newData.length; ++i){
            //System.out.println(newData[i]);
            //System.out.println(String.format("%8s", Integer.toBinaryString((byte)newData[i] & 0xFF)).replace(' ', '0'));
        //}    
        //System.out.println(System.nanoTime());
        DMM.sevenSegDecode(newData);
        //DMM.sevenSegDecode(newData);
        //DMM.lcd.printAllSegs();
        System.out.println(DMM.lcd.getFlags());
        System.out.println(DMM.lcd.toString());
        System.out.println(DMM.lcd.getBaseUnitValueString());
    }

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
}

