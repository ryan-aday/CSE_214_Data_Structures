/**
@author Ryan Aday
112754800
REC 30
Section 1
**/

/**
* The <code>Simulator</code> class only has one method called .simulate() that
* prints out 3 lines for the total wait time, the total number of Requests
* fullfilled, and the average wait time.
*
**/

public class Simulator{

  /**
  * Prints out 3 lines for the total wait time, the total number of Requests
  * fullfilled, and the average wait time.  O(n^2) from the use of the while and
  * for loops.
  *
  * @param probability
  *    The limit for the probability for elevators to generate.
  * @param numberOfFloors
  *    The number of floors of the simulation.
  * @param numberOfElevators
  *    The number of elevators of the simulation.
  * @param lengthOfSimulation
  *    The number of time units for the simulation to progress.
  *
  * @throws InvalidFloorsException
  *   Indicates the number of floors are not greater than 1.
  * @throws InvalidLengthException
  *   Indicates the length of the simulation is not greater than 0.
  * @throws InvalidElevatorsException
  *   Indicates the number of elevators is not greater than 0.
  * @throws InvalidProbabilityException
  *   Indicates the probability is not within 0.00 and 1.00 inclusive.
  *
  **/

  public static void simulate(double probability, int numberOfFloors, int numberOfElevators, int lengthOfSimulation)
  throws InvalidFloorsException, InvalidLengthException, InvalidElevatorsException, InvalidProbabilityException{

    //Input exceptions
    if (probability < 0.0 || probability > 1.0){
      throw new InvalidProbabilityException("\nProbability out of range from 0.0 to 1.0 inclusive.");
    }
    if (numberOfFloors < 1){
      throw new InvalidFloorsException("\nThe number of floors must be greater than 1.");
    }
    if (numberOfElevators < 0){
      throw new InvalidElevatorsException("\nThere must be more than 0 elevators.");
    }
    if (lengthOfSimulation < 0){
      throw new InvalidLengthException("\nThe length of the simulation must be greater than 0.");
    }

    //Creates probability generator
    BooleanSource passengerProbability = new BooleanSource(probability);

    //Initializes the elevators
    Elevator[] elevatorArray = new Elevator[numberOfElevators];

    for (int counter = 0; counter < numberOfElevators; counter++){
        //Sets all elevators on the first floor as IDLE
        elevatorArray[counter] = new Elevator();
        elevatorArray[counter].setCurrentFloor(1);
        elevatorArray[counter].setCurrentState(0);
    }

    //Initializes RequestQueue
    RequestQueue elevatorQueue = new RequestQueue();

    //Creates a timer for the simulation
    int timer = lengthOfSimulation;

    //Creates a time counter for the amount of time passed already
    int timeCounter = 0;

    //Creates total amount of time waited for an elevator to arrive
    int totalWaitTime = 0;

    //Creates number of requests completed
    int requestsCompleted = 0;


    //While loop to last for the length of the simulation
    while (timer > 0){
      //Decrement to lower the overall time left in the simulation
      timer--;
      //Increments for each of the requests for when they are recieved
      timeCounter++;

      //If the prob generator spits out true, then append a new random request to
      //the elevator queue for each time unit
      if (passengerProbability.requestArrived()){
        Request newRequest = new Request(numberOfFloors);

        //Sets the time the Request was entered at
        newRequest.setTime(timeCounter);

        elevatorQueue.enqueue(newRequest);

        //NOTE:  Uncomment these for part of the queue printout check
        //System.out.print("\nStep " + timeCounter + ": A request arrives from Floor " + newRequest.getSource() + " to Floor " + newRequest.getDest());
      }else{
        //System.out.print("\nStep " + timeCounter + ": Nothing Arrives");

      }

      for (Elevator elevator : elevatorArray){
        //For each elevator- done in case wait time goes past the limit of the simulation

        //If the elevator is IDLE (state at 0)
        if (elevator.getCurrentState() == elevator.IDLE){
          //If the ElevatorQueue is not empty
          try{
            //Dequeues the queue
            if (!elevatorQueue.isEmpty()){
              elevator.setRequest(elevatorQueue.dequeue());

              //Sets the current state as TO_SOURCE
              elevator.setCurrentState(elevator.TO_SOURCE);

            }

          }//NOTE: Empty queue doesn't return anything
          catch (EmptyQueueException e){}

        }

        //If the elevator is going TO_SOURCE
        if (elevator.getCurrentState() == elevator.TO_SOURCE){
          //If the elevator is below the target floor
          if (elevator.getCurrentFloor() < elevator.getRequest().getSource()){
            elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);

            //Everytime elevator moves before TO_DESTINATION, total wait time
            //increases
            elevator.setTempWaitTime(elevator.getTempWaitTime() + 1);

          }//If the elevator is above the target floor
          else if (elevator.getCurrentFloor() > elevator.getRequest().getSource()){
            elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);

            //Everytime elevator moves before TO_DESTINATION, total wait time
            //increases
            elevator.setTempWaitTime(elevator.getTempWaitTime() + 1);

          }//If the elevator is at source location
          else{
            //Sets current state of elevator to TO_DESTINATION
            elevator.setCurrentState(elevator.TO_DESTINATION);
          }
        }

        //If the elevator going TO_DESTINATION
        if (elevator.getCurrentState() == elevator.TO_DESTINATION){
          //If the elevator is below the target floor

          if (elevator.getCurrentFloor() < elevator.getRequest().getDest()){
            elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);

            //If it reaches the dest after moving
            if (elevator.getCurrentFloor() == elevator.getRequest().getDest()){
              //Sets current state of elevator to IDLE
              elevator.setCurrentState(elevator.IDLE);
              //Adds to the number of requestsCompleted
              requestsCompleted++;
              totalWaitTime = totalWaitTime + elevator.getTempWaitTime();
              elevator.setTempWaitTime(elevator.IDLE);
              elevator.setRequest(null);
            }

          }
          //If the elevator is above the target floor
          else if (elevator.getCurrentFloor() > elevator.getRequest().getDest()){
            elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);

            //If it reaches the dest after moving
            if (elevator.getCurrentFloor() == elevator.getRequest().getDest()){
              //Sets current state of elevator to IDLE
              elevator.setCurrentState(elevator.IDLE);
              //Adds to the number of requestsCompleted
              requestsCompleted++;
              totalWaitTime = totalWaitTime + elevator.getTempWaitTime();
              elevator.setTempWaitTime(elevator.IDLE);
              elevator.setRequest(null);
            }

          }//If elevator is at destination
          //NOTE: Should not be triggered; only as a failsafe
          else{
            //Sets current state of elevator to IDLE
            elevator.setCurrentState(elevator.IDLE);
            //Adds to the number of requestsCompleted
            requestsCompleted++;
            totalWaitTime = totalWaitTime + elevator.getTempWaitTime();
            elevator.setTempWaitTime(elevator.IDLE);
            elevator.setRequest(null);
          }

        }
      }

      //NOTE:  Comment after this line for the block comment for the queue print
      //out check

      /*
      //Printing all current requests
      System.out.print("\nRequests: ");
      elevatorQueue.printQueue();

      System.out.print("\nElevators: ");

      for (Elevator elevator : elevatorArray){

        //Formatting for elevator state
        String state;
        if (elevator.getCurrentState() == 0){
          state = "IDLE";
        } else if (elevator.getCurrentState() == 1){
          state = "TO_SOURCE";
        }else state = "TO_DESTINATION";

        //Printing all elevator info
        if (elevator.getCurrentState() != 0){
          String elevatorRequest = "(" + elevator.getRequest().getSource() + ", " + elevator.getRequest().getDest() + ", " + elevator.getRequest().getTime() + ")";

          System.out.print("[Floor " + elevator.getCurrentFloor() + ", " + state + ", " + elevatorRequest + "] ");

        }else System.out.print("[Floor " + elevator.getCurrentFloor() + ", " + state + ", " + "---" + "] ");

      }

      System.out.println("\n");
    //NOTE:  Comment after this line for the block comment for the queue print
    //out check
    */
    }

    //For average
    double average = 0.00;
    if (requestsCompleted != 0){
      average = (double)totalWaitTime / requestsCompleted;
    }

    //For Actual Output
    System.out.println("\nTotal Wait Time: " + totalWaitTime);
    System.out.println("Total Requests: " + requestsCompleted);
    System.out.println(String.format("Average Wait Time: %.2f", average));


  }


}
