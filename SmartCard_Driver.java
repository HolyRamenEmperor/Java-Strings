// Name:J4-18
// Date:8/29/19
 
import java.text.DecimalFormat;
import java.lang.*;
public class SmartCard_Driver
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
   
      SmartCard jimmy = new SmartCard(20.00); 
      jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
      jimmy.disembark(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
      jimmy.disembark(uptown);				//Error:  did not board?!
      System.out.println();
   			
      SmartCard susie = new SmartCard(1.00); 
      susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
      susie.disembark(suburbia);				//Insuffient funds to exit. Please add more money.
      System.out.println();
   
      SmartCard kim = new SmartCard(.25);    
      kim.board(uptown);            		    //Insuffient funds to board. Please add more money.
      System.out.println();
   
      SmartCard b = new SmartCard(10.00);   
      b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
      b.disembark(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
      System.out.println();
        
      SmartCard mc = new SmartCard(10.00);  
      mc.board(suburbia);            		    //Boarded at Suburb.  SmartCard has $10.00
      mc.disembark(downtown);					//From Suburb to Downtown costs $2.75.  SmartCard has $7.25
      System.out.println();
    
      //Make more test cases.  What else needs to be tested?
   }
} 	

//Note SmartCard is not denoted as public.  Why?
class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   private double money;
   private boolean onboard;
   private Station station = new Station();
   /* enter your code below */
   SmartCard(double money)
   {
      this.money = money;
      this.onboard = false;
   }
   public void addMoney(double d)
   {
      money = money + d; 
   }
   public String getBalance()
   {
      return df.format(money);
   }
   public boolean isBoarded()
   {
      return onboard;
   }
   public void board(Station s)
   {
      if((money > MIN_FARE) & (isBoarded() == false))
      {
         this.onboard = true;
         this.station.setZone(s.getZone());
         this.station.setName(s.getName());
         System.out.println("Boarded at " + station.getName() + "." + "\t" + "SmartCard has " + df.format(money));
         return;
      }
      else
      {
         if(onboard == true)
         {
            System.out.println("Error: already boarded?!");
            return;
         }
         else
         {
            System.out.println("Insufficient funds to board. Please add more money.");
            return;
         }
      }
   }
   public double cost(Station s)
   {
      double difference = Math.abs((station.getZone()) - (s.getZone()));
      if(difference == 0)
      {
         return 0.50;
      }
      else if(difference>0)
      {
         return (difference*.75)+.5;
      }
      return -1;
   }
   public void disembark(Station s)
   {
      if(isBoarded() == true)
      {
         double price=cost(s);
         if(this.money >= price)
         {
            String stop = station.getName();
            this.money = this.money - price;
            this.onboard = false;
            this.station = null;
            System.out.println("From " + stop + " to " + s.getName() + " costs " + df.format(price) + "." + "\t" + "SmartCard has " + df.format(this.money));
            return;
         }
         else
         {
         System.out.println("Insufficient funds to exit. Please add more money.");
         return;
         }
      }
      else if(onboard == false)
      {
         System.out.println("Error: did not board?!");
         return;
      }
   }
   
   //the next 3 methods are for use ONLY by Grade-It
   //these accessor methods only return your private data
   //they do not make any changes to your data
   public double getMoneyRemaining()
   {
    //enter your code here and replace the return with yours
      return money;
   }
   
   public Station getBoardedAt()
   {
    //enter your code here and replace the return with yours
      return station;   
   }
  
   public boolean getIsOnBoard()
   {
   //enter your code here and replace the return with yours
      return onboard;
   }
}
   
//Note Station is not a public class.  Why?
class Station
{
   private int zone;
   private String name;
   Station()
   {
      this.name = "";
      this.zone = 0;
   }
   Station(String name, int zone)
   {
      this.name = name;
      this.zone = zone;
   }
   public int getZone()
   {
      return zone;
   }
   public String getName()
   {
      return name;
   }
   public void setZone(int z)
   {
      this.zone = z;
   }
   public void setName(String n)
   {
      this.name = n;
   }
}

 /*******************  Sample Run ************

 Boarded at Center City.  SmartCard has $20.00
 From Center City to Suburb costs $2.75.  SmartCard has $17.25
 Error: did not board?!
 
 Boarded at Uptown.  SmartCard has $1.00
 Insufficient funds to exit. Please add more money.
 
 Insufficient funds to board. Please add more money.
 
 Boarded at Center City.  SmartCard has $10.00
 From Center City to Downtown costs $0.50.  SmartCard has $9.50
 
 Boarded at Suburb.  SmartCard has $10.00
 From Suburb to Downtown costs $2.75.  SmartCard has $7.25
 
 ************************************************/