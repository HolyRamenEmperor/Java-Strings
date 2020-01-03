// Name: J4-18
// Date: 9/17/19
import java.util.*;

public class LogMessageTest
{
   public static void main(String[] args)
   {
      String[] messages = {
         "CLIENT3:security alert - repeated login failures",
         "Webserver:disk offline",
         "SERVER1:file not found",
         "SERVER2:read error on disk DSK1",
         "SERVER1:write error on disk DSK2",
         "Webserver:error on /dev/disk",
         "True:disk",
         "True:error on disk",
         "True:error on /dev/disk disk",
         "True:error on disk DSK1",
         "False:DISK",
         "False:error on disk3",
         "False:error on /dev/disk",
         "False:diskette"};
   
    // Parts A and B
      for (String s : messages)
      {
         LogMessage msg = new LogMessage(s);
         System.out.println(msg.getMachineId() + ":" + msg.getDescription() + " ==> " + msg.containsWord("disk"));
      }
    
   // Part C
   //    SystemLog theLog  = new SystemLog(messages);
   //     LogMessage[] removed = theLog.removeMessages("disk");
   //     
   //     System.out.println();
   // 
   //     System.out.println("Removed messages:\n");
   //     for (LogMessage msg : removed)
   //       System.out.println(msg);
   //     System.out.println();
   // 
   //     System.out.println("Remaining messages:\n");
   //     System.out.println(theLog);
    
   }
}

class LogMessage
{
   private String machineId;
   private String description;

   /* Part (a) */
   public LogMessage(String message)
   {
      machineId = "";
      description = "";
      boolean temp = false;
      for(int i = 0; i < message.length(); i++)
      {
         if((message.substring(i, i+1).equals(":") == false) && (temp == false))
         {
            machineId += message.substring(i, i+1);
         }
         else if(message.substring(i, i+1).equals(":") == true)
         {
            description += message.substring(i+1);
            temp = true;
         }
      }
   
   }

   /* Part (b) */
   public boolean containsWord(String keyword)
   {
      boolean containsWord = false;
      boolean finished = false;
      if(description.contains(keyword) == true && finished == false) //checks if the string contains the keyword
      {
         if(description.length() == keyword.length()) //if the string is the keyword, then returns true
         {
            containsWord = true;
            finished = true;
         }
         else if((description.substring(0, keyword.length()).equals(keyword)) && (description.substring(keyword.length(), (keyword.length() + 1)).equals(" ")) && finished == false)
         {
            containsWord = true;
            finished = true;
         }
         else if((description.substring(description.length() - keyword.length()).equals(keyword)) && (description.substring(description.length() - (keyword.length() + 1), description.length() - keyword.length()).equals(" ")) && finished == false)
         {
            containsWord = true;
            finished = true;
         }
         else
         {            
            for(int i = (keyword.length() + 1); i < (description.length() - (keyword.length() + 1)); i++)
            {
              if((description.substring(i, (i + keyword.length())).equals(keyword)) && (description.substring(i-1, i).equals(" ")) && (description.substring((i + keyword.length()), (i + keyword.length() + 1)).equals(" ")) && finished == false)
              {
                 containsWord = true;
                 finished = true;
              }
            }
         }
      }
      return containsWord;
   }

   public String getMachineId()
   { 
      return machineId; 
   }

   public String getDescription()
   { 
      return description; 
   }

   public String toString()
   {
      return getMachineId() + ":" + getDescription();
   }
}

class SystemLog
{
   private LogMessage[] messageList;

   public SystemLog(String[] messages)
   {
      messageList = new LogMessage[messages.length];
      for (int i=0;i<messages.length; i++)
         messageList[i]=(new LogMessage(messages[i]));
   }

  /* Part (c) */

//   public LogMessage[] removeMessages(String keyword)
//   {
//     
//             
//       
//   }    


   public String toString()
   {
      String s = "";
      for (LogMessage msg : messageList)
         s += msg + "\n";
      return s;
   }
}

 /**************** Sample output:

   // Parts a and b   

 CLIENT3:security alert - repeated login failures ==> false
 Webserver:disk offline ==> true
 SERVER1:file not found ==> false
 SERVER2:read error on disk DSK1 ==> true
 SERVER1:write error on disk DSK2 ==> true
 Webserver:error on /dev/disk ==> false
 True:disk ==> true
 True:error on disk ==> true
 True:error on /dev/disk disk ==> true
 True:error on disk DSK1 ==> true
 False:DISK ==> false
 False:error on disk3 ==> false
 False:error on /dev/disk ==> false
 False:diskette ==> false
 
 
 // Part c  
 
 Removed messages:
 
 Webserver:disk offline
 SERVER2:read error on disk DSK1
 SERVER1:write error on disk DSK2
 True:disk
 True:error on disk
 True:error on /dev/disk disk
 True:error on disk DSK1
 
 Remaining messages:
 
 CLIENT3:security alert - repeated login failures
 SERVER1:file not found
 Webserver:error on /dev/disk
 False:DISK
 False:error on disk3
 False:error on /dev/disk
 False:diskette
 
 ********************************************/    

