// Name: J4-18
// Date: 9/9/19
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
      
      String str = pig("What!?");
      System.out.print(str + "\t\t" + pigReverse(str));
      str = pig("{(Hello!)}");
      System.out.print("\n" + str + "\t\t" + pigReverse(str));
      str = pig("\"McDonald???\"");
      System.out.println("\n" + str + "  " + pigReverse(str));
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println(p);
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static String pig(String s)
   {
      boolean firstlettercap = false;
      String piglatinized = "";
      String prepunct = "";
      String postpunct = "";
      String rpostpunct = "";
      int l = 0; //for prepunct
      int m = 0; //m is used for all punctuation cases
      boolean b = true; //this is for a break cases
      int k = 0; //this is for break case for inside for loop
      int i = 0; //this is for break case for outside for loop
      boolean g = true; //this is for another break case
      int q = 0; //for all puncutation cases
      int x = s.length() - 1; //for postpunct and prepunct cases
      while(q < s.length() && b == true)
      {
         if(Character.isLetter(s.charAt(q)) == false)
         {
            m++;
         }
         else
         {
            m = 0;
            b = false;
         }
         q++;
      }
      while((Character.isLetter(s.charAt(0))) == false && m != s.length())
      {
         prepunct = prepunct + s.substring(l, l+1);
         s=s.substring(1);
         x = x-1;
      }
      while((Character.isLetter(s.charAt(x))) == false && m != s.length())
      {
         rpostpunct = rpostpunct + s.substring(x);
         s = s.substring(0, x);
         x = x - 1;
      } 
      if(s.length() == 0)
      {
         piglatinized = "";   
      }
      if (s.substring(0, 1).equals(s.substring(0, 1).toUpperCase()))
      {
         firstlettercap = true;
         s = s.substring(0, 1).toLowerCase() + s.substring(1);
      }
      if(vowels.indexOf(s.substring(0,1)) >= 0)
      {
         piglatinized = s + "way";
      }
      else
      {
         int d = 0;
         boolean word = false;
         while(i < s.length() && word == false)
         {
            if(k == vowels.length())
            {
               k=0;
            }
            while(k < vowels.length() && word == false)
            {
               if((i < s.length() - 3 && s.substring(i, i+2).equals("qu")) && (s.substring(i+2, i+3).equals(vowels.substring(k, k+1))) && word == false)
               {
                  piglatinized = (s.substring(i+2) + (s.substring(0, i+2))) + "ay";
                  d++;
                  word = true;
               }
               else if((s.substring(i, i+1).equals(vowels.substring(k, k+1)) || ((s.substring(i, i+1).equals("y") == true) && i > 0)) && word == false)
               {
                  if(s.substring(i, i+1).equals("y") && i > 0)
                  {
                     piglatinized = (s.substring(i)) + s.substring(0, i) + "ay";
                     d++;
                     word = true;
                  }
                  else
                  {
                     piglatinized = (s.substring(i)) + s.substring(0, i) + "ay";
                     d++;
                     word = true;
                  }
               }
               else
               {
                  k++;
               } 
            
            }
            i++;
         }
         if(d == 0 && word == false)
         {
            piglatinized = "**** NO VOWEL ****";
         }
      }
                  
   
      //remove and store the beginning punctuation 
           
           
      //remove and store the ending punctuation 
         
         
      //START HERE with the basic case:
   
      //find the index of the first vowel
      //     y is a vowel if it is not the first letter
      //     qu
      
      
      //if no vowel has been found
      
      
      //is the first letter capitalized?
      
      
      //return the piglatinized word 
      for(int j = rpostpunct.length() - 1; j >= 0; j--)
      {
         postpunct = postpunct + rpostpunct.charAt(j);
      }
      if (firstlettercap)
         piglatinized = piglatinized.substring(0, 1).toUpperCase() + piglatinized.substring(1);
      if (m == s.length())
         return "**** NO VOWEL ****";
      return prepunct + piglatinized + postpunct;
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      while(infile.hasNextLine())
      {
         String line = infile.nextLine();
         String trimmedline = line.trim();
         if(trimmedline.isEmpty())
         {  
            outfile.println();
         }
         else
         {
            String temp = "";
            int pos = 0;
            while (pos < line.length())
            {
               if (line.substring(pos, pos+1).equals(" "))
               {
                  outfile.print(" ");
                  pos++;
               }
               else 
               {
                  while (pos <= (line.length() - 1) && !(line.substring(pos, pos+1).equals(" ")))
                  {
                     temp += line.substring(pos, pos+1);
                     pos ++;
                  }
                  outfile.print(pig(temp));
                  temp = "";
               }
               
            }
         }
         outfile.println();
      }
   
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   **/
   public static String pigReverse(String s)
   {
      boolean firstlettercap = false; //check first letter capitalization
      String returnString = "";
      String prepunct = "";
      String rpostpunct = "";
      int m = 0;
      int l = 0;
      if(s.length() == 0) //catch
         return "";
         
      int x = s.length() - 1;
      while((Character.isLetter(s.charAt(0))) == false && m != s.length()) //punctuation remover
      {
         prepunct = prepunct + s.substring(l, l+1);
         s=s.substring(1);
         x = x-1;
      }
      while((Character.isLetter(s.charAt(x))) == false && m != s.length()) //punctuation remover
      {
         rpostpunct = s.substring(x) + rpostpunct;
         s = s.substring(0, x);
         x = x - 1;
      } 
      
      if (s.substring(0, 1).equals(s.substring(0, 1).toUpperCase())) //check if first letter is capitalized
      {
         firstlettercap = true;
         s = s.substring(0, 1).toLowerCase() + s.substring(1);
      }
      
      Stack<String> stk = new Stack<>(); //reverse the string
      for (int i = 0; i < s.length(); i++)
         stk.push(s.substring(i, i+1));
      for (int i = 0; i < s.length(); i++)
         returnString += stk.pop();
         
      if (firstlettercap)// deal with capitalization
         returnString = returnString.substring(0, 1).toUpperCase() + returnString.substring(1);
         
      return prepunct + returnString + rpostpunct;
   }
}
