import java.net.*; // Because I am using networking

class Server{

    
    ServerSocket server; //Using ServerSocket class and storing in server variable
    Socket socket; // Using Socket class and storing in socket variable
    
    // Constructor..
    public Server(){

        try {
            server=new ServerSocket(7777);
            System.out.println("Server is ready to accept connection");
            System.out.println("waiting...");
            socket=server.accept();



        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args){

        System.out.println("this is server.. going to start server");
        new Server(); // Server object is called here
    }
}