import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.style.PlotStyle;
import server.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import static server.AlgorithmRunner.*;

public class Client {
   /* CONSTANTS */
   private static final String USAGE = "\nUsage: java Client n a" + 
            "\n\tn - (int >1) max number of processors. Program will loop from 1 to n" + 
            "\n\ta - (int >0) multiplier of ids such that ids range from 1 to a*n" + 
            "\n\tf - flag for ordering of the ids:" + 
            "\n\t\t-a for clockwise ids\n\t\t-d for counterclockwise ids\n\t\t-r for random order";
   private static final String CLOCK_IDS_FLAG = "a"; // ascending ids
   private static final String COUNTER_IDS_FLAG = "d"; // descending ids
   private static final String RANDOM_IDS_FLAG = "r"; // descending ids
   /* VARIABLES */
   private static String idOrderPlotTitle;
   private static int idOrder; // state variable for the BididerectionalRing() constructor
   private static int n; // number of processors
   private static int a; // alpha - multiplier for id range


   private Client() {}  

   public static void main(String[] args) {  
      try {
         processInput(args);
      } catch (NumberFormatException e) {
         System.out.println(USAGE);
         System.exit(1);
      }

      Complexity simulResults = new Complexity(n, idOrder);
      /* Java RMI */
      try {
         System.out.println("Calling remote method..."); 

         // Getting the registry 
         Registry registry = LocateRegistry.getRegistry(null); 
    
         // Looking up the registry for the remote object 
         AlgorithmRunner stub = (AlgorithmRunner) registry.lookup(Server.REG_ALGORITHM_RUNNER); 
    
         // Calling the remote method using the obtained object 
         simulResults = stub.runAlgorithms(100, 2, idOrder);
         
      } catch (Exception e) {
         System.err.println("Client exception: " + e.toString()); 
         e.printStackTrace(); 
      } 


      displayAnalysis(simulResults);
   } 

      /**
     * plot and display graphs from the Complexity class.
     */
    public static void displayAnalysis(Complexity counts) {
      // plot Time Complexity of LCR and HS. Add y = x for comparison
      JavaPlot p = new JavaPlot();
      p.setTitle("Time Complexity " + idOrderPlotTitle);
      if (idOrder == RANDOM_IDS) {
          plotGraph(p, "Average Case LCR", "Rounds", counts.getRoundCountLCR());
          plotGraph(p, "Worst Case LCR", "Rounds", counts.getWorstRoundCountLCR());
          plotGraph(p, "Best Case LCR", "Rounds", counts.getBestRoundCountLCR());
          plotGraph(p, "Average Case HS", "Rounds", counts.getRoundCountHS());
          plotGraph(p, "Worst Case HS", "Rounds", counts.getWorstRoundCountHS());
          plotGraph(p, "Best Case HS", "Rounds", counts.getWorstRoundCountHS());
      }
      else {
          plotGraph(p, "LCR", "Rounds", counts.getRoundCountLCR());
          plotGraph(p, "HS", "Rounds", counts.getRoundCountHS());
      }
      p.addPlot("x * log(x)");
      p.addPlot("x");
      p.plot();

      // plot communication complexity of LCR and HS, add y = x*x for comparison
      p = new JavaPlot();
      p.setTitle("Communication Complexity " + idOrderPlotTitle);
      if (idOrder == RANDOM_IDS) {
          plotGraph(p, "Average Case LCR", "Messages", counts.getMsgCountLCR());
          plotGraph(p, "Worst Case LCR", "Messages", counts.getWorstMsgCountLCR());
          plotGraph(p, "Best Case LCR", "Messages", counts.getBestMsgCountLCR());
          plotGraph(p, "Average Case HS", "Messages", counts.getMsgCountHS());
          plotGraph(p, "Worst Case HS", "Messages", counts.getWorstMsgCountHS());
          plotGraph(p, "Best Case HS", "Messages", counts.getBestMsgCountHS());
      }
      else {
          plotGraph(p, "LCR", "Messages", counts.getMsgCountLCR());
          plotGraph(p, "HS", "Messages", counts.getMsgCountHS());
      }
      if (idOrder == COUNTER_ORDERED_IDS) {
          // counter clockiwse id order is the worst case setting for LCR -> O(n^2)
          p.addPlot("x * x");
      }
      else {
          p.addPlot("x");
      }
      p.addPlot("x * log(x)");
      p.plot();
  }

  /**
   * 
   * @param p
   * @param title of what the data represents
   * @param ylabel
   * @param data
   */
  private static void plotGraph(JavaPlot p, String title, String ylabel, int[] data) {
      // convert data to 2D array
      int[][] data2D = new int[data.length + 2][1];
      for (int i = 0; i < data.length; i++) {
          data2D[i + 2] = new int[1];
          data2D[i + 2][0] = data[i];
      }

      p.getAxis("x").setBoundaries(0, n);
      p.getAxis("x").setLabel("Ring Size");
      p.getAxis("y").setLabel(ylabel);
      p.setKey(JavaPlot.Key.TOP_LEFT);

      PlotStyle myPlotStyle = new PlotStyle();
      myPlotStyle.setStyle(Style.LINES);
          
      DataSetPlot dataPlot = new DataSetPlot(data2D);
      dataPlot.setPlotStyle(myPlotStyle);
      dataPlot.setTitle(title);
      p.addPlot(dataPlot);
  }

  /**
   * Throws a NumberFormatException.
   * Creates a Bidirectional ring given parameters from user's input.
   * 
   * @param args
   */
  private static void processInput(String[] args) throws NumberFormatException {
      if (args.length < 3) {
          throw new NumberFormatException();
      }

      n = Integer.parseInt(args[0]);
      a = Integer.parseInt(args[1]);

      if (n < 2 || a < 1) {
          throw new NumberFormatException();
      }

      if (args[2].contains(CLOCK_IDS_FLAG)) {
          idOrder = CLOCK_ORDERED_IDS;
          idOrderPlotTitle = "(clockwise ids)";
      } else if (args[2].contains(COUNTER_IDS_FLAG)) {
          idOrder = COUNTER_ORDERED_IDS;
          idOrderPlotTitle = "(counter clockwise ids)";
      } else if (args[2].contains(RANDOM_IDS_FLAG)) {
          idOrder = RANDOM_IDS;
          idOrderPlotTitle = "(random ids)";
      }
      else {
          throw new NumberFormatException();
      }
  }


}