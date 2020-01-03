// Name: J4-18
// Date: 9/18/19

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
//here any additional imports that you may need

public class Cemetery
{
    public static void main (String [] args)
    {
        File file = new File("cemetery.txt");
        int numEntries = countEntries(file);
        Person[] cemetery = readIntoArray(file, numEntries);
        //System.out.println(numEntries);
        int min = locateMinAgePerson(cemetery);
        int max = locateMaxAgePerson(cemetery);
        //for testing only
        for (int i = 0; i < cemetery.length; i++)
            System.out.println(cemetery[i]);
        System.out.println("In the St. Mary Magdelene Old Fish Cemetery --> ");
        System.out.println("Name of youngest person: " + cemetery[min].getName());
        System.out.println("Age of youngest person: " + cemetery[min].getAge());
        System.out.println("Name of oldest person: " + cemetery[max].getName());
        System.out.println("Age of oldest person: " + cemetery[max].getAge());
        //you may create other testing cases here
        //comment them out when you submt your file to gradeit
    }

    /* Counts and returns the number of entries in File f.
       Returns 0 if the File f is not valid.
       Uses a try-catch block.
       @param f -- the file object
    */
    public static int countEntries(File f)
    {
        int entries = 0;
        Scanner infile = null;
        try
        {
            infile = new Scanner(f);
        }
        catch(IOException e)
        {
        }
        if(infile != null)
        {
           while(infile.hasNextLine() == true && (((infile.nextLine()).substring(0, 1).equals(" ")) == false))
           {
              entries++;
           }
        }
        
        return entries;

    }

    /* Reads the data from file f (you may assume each line has same allignment).
       Fills the array with Person objects. If File f is not valid return an empty array.
       Uses a try-catch block.
       @param f -- the file object
       @param num -- the number of lines in the File f
    */
    public static Person[] readIntoArray (File f, int num)
    {
        Person[] p = new Person[num];
        String temp = "";
        Scanner infile = null;
        try
        {
            infile = new Scanner(f);
        }
        catch(IOException e)
        {
            for(int i = 0; i < p.length; i++)
            {
                p[i] = null;
            }
            return p;
        }
        for(int i = 0; i < p.length; i++)
        {
            temp = infile.nextLine();
            p[i] = makeObjects(temp);
        }
        return p;
    }

    /* A helper method that instantiates one Person object.
       @param entry -- one line of the input file.
       This method is made public for gradeit testing purposes.
       This method should not be used in any other class!!!
    */
    public static Person makeObjects(String entry)
    {
        String name = entry.substring(0, 25).trim();
        String bd = entry.substring(25, 36).trim();
        String age = entry.substring(37,42).trim();
        Person p = new Person(name, bd, age);
        return p;
    }

    /* Finds and returns the location (the index) of the Person
       who is the youngest. (if the array is empty it returns -1)
       If there is a tie the lowest index is returned.
       @param arr -- an array of Person objects.
    */
    public static int locateMinAgePerson(Person[] arr)
    {
        int minindex = 0;
        if(arr.length == 0)
        {
            minindex = -1;
        }
        else
        {
            double min = arr[0].getAge();
            for (int i = 0; i < arr.length; i++)
            {
                if(arr[i].getAge() < min)
                {
                    min = arr[i].getAge();
                    minindex = i;
                }
            }
        }
        return minindex;
    }

    /* Finds and returns the location (the index) of the Person
       who is the oldest. (if the array is empty it returns -1)
       If there is a tie the lowest index is returned.
       @param arr -- an array of Person objects.
    */
    public static int locateMaxAgePerson(Person[] arr)
    {
        int maxindex = 0;
        if(arr.length == 0)
        {
            maxindex = -1;
        }
        else
        {
            double max = arr[0].getAge();
            for (int i = 0; i < arr.length; i++)
            {
                if (arr[i].getAge() > max)
                {
                    max = arr[i].getAge();
                    maxindex = i;
                }
            }
        }
        return maxindex;
    }
}

class Person
{
    //constant that can be used for formatting purpose
    private static final DecimalFormat df = new DecimalFormat("0.0000");
    /* private fields */
    private String name;
    private String birthdate;
    private double age;

    /* a three-arg constructor
     @param name, birthdate may have leading or trailing spaces
     It creates a valid Person object in which each field has the leading and trailing
     spaces eliminated*/
    Person(String n, String bd, String a)
    {
        name = n;
        birthdate = bd;
        age = calculateAge(a);
    }
    /* any necessary accessor methods (at least "double getAge()" and "String getName()" )
    make sure your get and/or set methods use the same datat type as the field  */
    public double getAge()
    {
        return age;
    }
    public String getName()
    {
        return name;
    }
    /*handles the inconsistencies regarding age
      @param a = a string containing an age from file. Ex: "12", "12w", "12d"
      returns the age transformed into year with 4 decimals rounding
    */
    public double calculateAge(String a)
    {
        double cA = 0.0;
        if(a.contains("w"))
        {
            cA = Double.parseDouble(df.format(Double.parseDouble(a.substring(0, a.length() - 1)) * 7.0/365));
        }
        else if(a.contains("d"))
        {
            cA = Double.parseDouble(df.format(Double.parseDouble(a.substring(0, a.length() - 1)) /365));
        }
        else
        {
            cA = Double.parseDouble(a);
        }
        return cA;
    }
}