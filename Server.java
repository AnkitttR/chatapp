import java.net.*; // Because I am using networking
import java.io.*;

class Server{

    
    ServerSocket server; //Using ServerSocket class and storing in server variable
    Socket socket; // Using Socket class and storing in socket variable

    BufferedReader br; // Input stream jo accept karenge line 20 mein wo yaha de denge hum
    PrintWriter out; // Output stream jo accept karenge line 20 mein wo yaha de denge hum
    
    // Constructor..
    public Server(){

        try {
            server=new ServerSocket(7777);
            System.out.println("Server is ready to accept connection");
            System.out.println("waiting...");
            socket=server.accept(); // socket se input stream nikal sakte hain data ko read karne ke liye
                                    // socket se hi output stream nikal sakte hain data ko write karne ke liye

            // Byte ke data ko InputStreamReader, character mein change kar dega

            br=new BufferedReader(new InputStreamReader(socket.getInputStream())); // streams are uni directional

            out=new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();


        }catch (Exception e){
            e.printStackTrace();
        }

        public void startReading(){

            // thread data ko read karke deta rahega 

            Runnable r1=()->{

                System.out.println("reader started...");

                while(true){

                    try{
                    String msg=br.readLine();
                    if(msg.equals("exit")){

                        System.out.println("Client terminated the chat");
                        break;
                    }

                    System.out.println("Client: "+msg);
                    }catch(Exception e){

                        e.printStackTrace();
                    }
                }

            };

            new Thread(r1).start(); // Starting the thread r1

        }

        public void startWriting(){

            // thread-data ko user se lega & then send karega client tak

            Runnable r2=()->{
                System.out.println("Writer started..");
                while(true){

                    try{

                        BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));// Input from console

                        String content=br1.readLine();

                        out.println(content);
                        out.flush(); // kabhi kabhi data nahi jaa raha hoga, isliye flush kiya

                    }catch(Exception e){

                        e.printStackTrace(); // Kis line pe exception aayi usko print karne ke liye
                    }


                }

            };

            new Thread(r2).start(); // Starting the thread r2

        }






    }

    public static void main(String[] args){

        System.out.println("this is server.. going to start server");
        new Server(); // Server object is called here
    }
}