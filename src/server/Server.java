package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends AlgorithmImpl {
   public static final String REG_ALGORITHM_RUNNER = "LCR_HS_SIMUL";


   public Server() {} 

   public static void main(String args[]) { 
      try { 
         // Instantiating the implementation class 
         AlgorithmImpl algorithmObject = new AlgorithmImpl(); 
    
         // Exporting the object of implementation class  
         // (here we are exporting the remote object to the stub) 
         AlgorithmRunner stub = (AlgorithmRunner) UnicastRemoteObject.exportObject(algorithmObject, 0);  
         
         // Binding the remote object (stub) in the registry 
         Registry registry = LocateRegistry.getRegistry(); 
         registry.bind(REG_ALGORITHM_RUNNER, stub);  

         System.err.println("Server running..."); 
      } 
      catch (Exception e) { 
         System.err.println("Server exception: " + e.toString()); 
         e.printStackTrace();
      } 
   } 
}