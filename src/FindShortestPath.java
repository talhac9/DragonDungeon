import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class will use an algorithm to compute the shortest path from the initial chamber to the exit.
 * The algorithm is created using a priority queue from the class DLPriorityQueue.
 * @author Talha Chaudhry (tchaud@uwo.ca)
 *
 */

public class FindShortestPath {

	public static void main (String[] args) throws Exception {
		
		try {
			// Throws an exception if no input file is specified when running method
			if (args.length < 1) throw new Exception("No input file specified");
			String dungeonFileName = args[0];
			Dungeon theDungeon = new Dungeon(dungeonFileName);		// Creates a new dungeon with the file inputed
			
			DLPriorityQueue<Hexagon> prioQueue = new DLPriorityQueue<Hexagon>();	// Creates an empty priority queue
			prioQueue.add(theDungeon.getStart(), 0);		// adds the start of the dungeon to the priority queue
			
			boolean exitFound = false, goBack = false;
			Hexagon neighbours[] = new Hexagon[6];
			Hexagon exit = null;
			Hexagon current;
			
			while (!prioQueue.isEmpty() && !exitFound) {	// This loop will run the algorithm while the prioQueue is not empty and the exit is not found
				
				current = prioQueue.removeMin();  // Removes the smallest priority hexagon from the queue
				current.markDequeued();  		  // Marks the removed hexagon as dequeued
				goBack = false;
				
				// Gets all the neighbour hexagons of the priority queue and adds them to an array if they are not null
				// If a neighbour hexagon is a dragon or the current hexagon is a dragon, goes back to while loop and gets next hexagon
				for (int i = 0; i < 6; i++) {
					if (current.getNeighbour(i) != null) neighbours[i] = current.getNeighbour(i);	
					if (neighbours[i] != null && (neighbours[i].isDragon() || current.isDragon())) goBack = true; 
				}
				
				
				if (current.isExit()) {	// If the current hexagon is the exit, ends the loop.
					exitFound = true;
					exit = current;
				}
				
				if (goBack) continue;  // if goBack, goes to the next iteration of while loop.
				
				else {
					
					for (int i = 0; i < 6; i++) { // Loops through each neighbour of the current hexagon.
						
						if (neighbours[i] != null && !neighbours[i].isWall() && !neighbours[i].isMarkedDequeued()) {
							
							boolean valueChanged = false;
							
							int d = 1 + current.getDistanceToStart();
							
							// If distance of neighbour to initial chamber is larger than D then set the distance
							// of neighbour to the initial chamber to D. Sets the predecessor of neighbour to current hexagon.
							
							if (neighbours[i].getDistanceToStart() > d) {
								neighbours[i].setDistanceToStart(d);
								neighbours[i].setPredecessor(current);
								valueChanged = true;
							}
							
							// If neighbour is marked as enqueued and the distance from neighbour to the
							// initial chamber was modified in the previous step, invokes the updatePriority
							// method from class DLPriorityQueue to update the priority that neighbour has in
							// the priority queue. The new priority of neighbour will be the distance from
							// neighbour to the initial chamber plus the distance from neighbour to the exit.
							
							if (neighbours[i].isMarkedEnqueued() && valueChanged) {
								double distanceToUpdate = neighbours[i].getDistanceToExit(theDungeon) + neighbours[i].getDistanceToStart();
								prioQueue.updatePriority(neighbours[i], distanceToUpdate);
							}
							
							// If neighbour is not marked as enqueued, then add it to the queue with
							// priority equal to its distance to the initial chamber plus its distance to the exit.
							// marks neighbour as enqueued.
							
							if (!neighbours[i].isMarkedEnqueued()) {
								double prio = neighbours[i].getDistanceToExit(theDungeon) + neighbours[i].getDistanceToStart();
								prioQueue.add(neighbours[i], prio);
								neighbours[i].markEnqueued();
							}
						}
					}
				}
				
				
				}
				
			int shortestPath = 0;
			
			if (exitFound) {
				shortestPath = exit.getDistanceToStart() + 1;
				System.out.println("Path of length " + shortestPath + " found"); 
			}
			
			else System.out.println("No path found");
			
		} catch (InvalidDungeonCharacterException e) {
			System.out.print(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
	}
	
	
	
}
