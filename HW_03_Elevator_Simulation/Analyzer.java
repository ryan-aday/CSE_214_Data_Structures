/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>Analyzer</code> class prints output from the Simulator class.
*
**/


import java.util.Scanner;  //Needed for scanning the terminal for user input.

public class Analyzer{

  /**
  * Prints out 3 lines for the total wait time, the total number of Requests
  * fullfilled, and the average wait time.  O(n^2) from the use of the while and
  * for loops.
  *
  **/

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    System.out.println("Welcome to the Elevator Simulator!\n");

    //NOTE:  For Extra Credit
    //System.out.print("Would you like to use the optimal method (yes/no)?: ");
    //String confirm = sc.nextLine();

    System.out.print("Please enter the probability of arrival for Requests: ");
    double probability = sc.nextDouble();
    System.out.print("Please enter the number of floors: ");
    int floors = sc.nextInt();
    System.out.print("Please enter the number of elevators: ");
    int elevators = sc.nextInt();
    System.out.print("Enter the length of the simulation (in time units): ");
    int length = sc.nextInt();

    //NOTE:  For Extra Credit

    /*
    if (confirm.equals("yes") || confirm.equals("YES") || confirm.equals("Yes")){
      try{
        OptimalSimulator simulation = new OptimalSimulator();
        simulation.simulate(probability, floors, elevators, length);

      }catch (InvalidProbabilityException e){
        System.out.println("Probability out of range from 0.0 to 1.0 inclusive.");
      }catch (InvalidFloorsException e){
        System.out.println("The number of floors must be greater than 1.");
      }catch (InvalidElevatorsException e){
        System.out.println("There must be more than 0 elevators.");
      }catch (InvalidLengthException e){
        System.out.println("The length of the simulation must be greater than 0.");
      }
    }

    if (confirm.equals("no") || confirm.equals("NO") || confirm.equals("No")){
      try{
        Simulator simulation = new Simulator();
        simulation.simulate(probability, floors, elevators, length);

      }catch (InvalidProbabilityException e){
        System.out.println("Probability out of range from 0.0 to 1.0 inclusive.");
      }catch (InvalidFloorsException e){
        System.out.println("The number of floors must be greater than 1.");
      }catch (InvalidElevatorsException e){
        System.out.println("There must be more than 0 elevators.");
      }catch (InvalidLengthException e){
        System.out.println("The length of the simulation must be greater than 0.");
      }
    }
    */

    try{
      Simulator simulation = new Simulator();
      simulation.simulate(probability, floors, elevators, length);

    }catch (InvalidProbabilityException e){
      System.out.println("Probability out of range from 0.0 to 1.0 inclusive.");
    }catch (InvalidFloorsException e){
      System.out.println("The number of floors must be greater than 1.");
    }catch (InvalidElevatorsException e){
      System.out.println("There must be more than 0 elevators.");
    }catch (InvalidLengthException e){
      System.out.println("The length of the simulation must be greater than 0.");
    }

  }
}
