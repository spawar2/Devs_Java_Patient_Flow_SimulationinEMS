package DEVSJAVALab;


import GenCol.*;
import genDevs.modeling.*;
import genDevs.simulation.*;
import genDevs.simulation.realTime.*;


public class test{

protected static digraph testDig;

  public test(){}

  public static void main(String[ ] args)
  {
      testDig = new EMS_Sim();
      genDevs.simulation.coordinator cs = new genDevs.simulation.coordinator(testDig);

//      TunableCoordinator cs = new TunableCoordinator(testDig);
//      cs.setTimeScale(0.1);

      cs.initialize();
      cs.simulate(550000);
      System.out.println("Finished execution");
  }
}
