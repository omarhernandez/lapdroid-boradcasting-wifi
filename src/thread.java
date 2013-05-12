import java.io.*;
import java.net.*;


public class thread {
 

	public DatagramSocket socket ;
	private static final int DISCOVERY_PORT = 2562;
	public Thread thread;
	
 public void runThread(){
	 
	 
		try{
			  socket = new DatagramSocket(DISCOVERY_PORT);
		      socket.setBroadcast(true);
		}catch (IOException e) {
			 
			}
		
 /*
		thread = new Thread(new Runnable(){
			@Override
			public void run(){
	 
				
				 try {
					
					  System.out.println("Monitoreando ...");
 
				      //socket.setSoTimeout(TIMEOUT_MS);
				       
				      listenForResponses(socket);
				      System.out.println("enviando algo..");
				    
	 
				} catch (IOException e) {
					System.out.println("mal !!");
				}
			 
				
		 
			}
			
			
		}
				
		);
		
		thread.start();
		
		*/
		
		thread = new Thread(new Runnable(){
			@Override
			public void run(){
	 
				
				 try {
					
					  System.out.println("Monitoring");
					  sendDiscoveryRequest(socket); 
				     
		 
				} catch (IOException e) {
					System.out.println("mal !!");
				}
			 
				
		 
			}
			
			
		}
				
		);
		
		thread.start();
		
	
		
 }
 
 
	/* SENDING */
	/* ********************************************************************************* */
	
	
	  private void sendDiscoveryRequest(DatagramSocket socket) throws IOException {
		    
	
 
          byte[] receiveData = new byte[6024];
         // byte[] sendData = new byte[1024];
          while(true)
             {
        	  System.out.println("Lapdroid: Listening ...");
        	    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        	    socket.receive(receivePacket);
        	    
                String sentence = new String( receivePacket.getData());
                
              
                System.out.println("Received: " + sentence);
                
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
              
                for(int i=0;i<10;i++){
                DatagramSocket datagramSocket = new DatagramSocket();

                byte[] buffer = "0123456789".getBytes();
        
                System.out.println("se enviara algo 0123456789 "+IPAddress);
                
                DatagramPacket packet = new DatagramPacket(
                buffer, buffer.length, IPAddress, port);
                datagramSocket.send(packet);
                }
                /*
                for(int i=0;i<50;i++){
                    String capitalizedSentence = "PC-"+sentence.toUpperCase();
                    byte[] sendData = capitalizedSentence.getBytes();
                    
                    System.out.println("se enviara: "+capitalizedSentence);
                    
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    socket.send(sendPacket);
                 	}
                */

             }
	 
	  }
	
	
	/* LISTEN */
	/* ********************************************************************************* */
	
 private void listenForResponses(DatagramSocket socket) throws IOException{
	
	
	byte[] buf = new byte[1024];
   try {
     while (true) {
       DatagramPacket packet = new DatagramPacket(buf, buf.length);
       socket.receive(packet);
       String s = new String(packet.getData(), 0, packet.getLength());
       

       
	       System.out.println(""+s);
     }
     
     
   } catch (SocketTimeoutException e) {
     
     System.out.println("error");
   }
	
	
}
	/* ********************************************************************************* */
	
 
 

}
