/*      Copyright 2002 Arizona Board of regents on behalf of
 *                  The University of Arizona
 *                     All Rights Reserved
 *         (USE & RESTRICTION - Please read COPYRIGHT file)
 *
 *  Version    : DEVSJAVA 2.7
 *  Date       : 08-15-02
 */


package DEVSJAVALab;

import simView.*;


import java.lang.*;
import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;

public class transducer extends  ViewableAtomic{
 protected double  arrived, solved;
 protected double  arrived1, solved1;
 protected double observation_time;
 
 protected int numOfarrivingcars = 0;
 protected int numOfFinishedCars = 0;
 
  protected int numOfarrivingcars1 = 0;
 protected int numOfFinishedCars1 = 0;

 public transducer(String  name,double Observation_time){
  super(name);
   addInport("in");
   addOutport("out");
  addInport("ariv");
  addInport("Assigned");
  observation_time = Observation_time;
  addTestInput("ariv",new entity("val"));
  addTestInput("Assigned",new entity("val"));
  initialize();
 }

 public transducer() {this("Operator", 1500);}

 public void initialize(){
  phase = "active";
  sigma = observation_time;
  super.initialize();
 }


 public void  deltext(double e,message  x){
  Continue(e);
  entity  val;
  for(int i=0; i< x.size();i++){
    if(messageOnPort(x,"ariv",i)){
       val = x.getValOnPort("ariv",i);
       if(val.getName().compareTo("Doctor3")==0){
    	   arrived=this.getSimulationTime();
       	   System.out.println(val.getName()+" arrived at time:"+arrived);
       }
       numOfarrivingcars++;
    }
    if(messageOnPort(x,"Assigned",i)){
       val = x.getValOnPort("Assigned",i);
       if(val.getName().compareTo("Doctor3")==0){  
    	   solved = this.getSimulationTime();
       	   System.out.println(val.getName()+" is finished at time:"+solved);
       }
       numOfFinishedCars++;
    }
  }
  
  
    for(int i=0; i< x.size();i++){
    if(messageOnPort(x,"ariv",i)){
       val = x.getValOnPort("ariv",i);
       if(val.getName().compareTo("Ambulance1")==0){
    	   arrived1=this.getSimulationTime();
       	   System.out.println(val.getName()+" arrived at time:"+arrived1);
       }
       numOfarrivingcars1++;
    }
    if(messageOnPort(x,"Assigned",i)){
       val = x.getValOnPort("Assigned",i);
       if(val.getName().compareTo("Ambulance1")==0){  
    	   solved1 = this.getSimulationTime();
       	   System.out.println(val.getName()+" is finished at time:"+solved1);
       }
       numOfFinishedCars1++;
    }
  }
  
 }

 public void  deltint(){
//  System.out.println("the total service time for Patient is: "+(solved-arrived));
  System.out.println("Patient discharged:"+numOfFinishedCars);
 //   System.out.println("The total service time for Patient is: "+(arrived1-solved1));
//  System.out.println("Doctors assigned:"+numOfFinishedCars1);
  passivate();
  //show_state();
 }

 public  message    out( ){
  message  m = new message();
  content  con = makeContent("out",new entity("stop"));
  m.add(con);
  return m;
 }



 public void  show_state(){

  System.out.println("car3 arrive time "  +  arrived );
  System.out.println("car3 solved time "  +  solved );
 }
}
