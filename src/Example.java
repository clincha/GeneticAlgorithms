//This is my example Solution

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Example {
  public static void main(String[] args) {
    //Do not delete/alter the next line
    long startT = System.currentTimeMillis();

    //Edit this according to your name and login
    String name = "Angus Clinch";
    String login = "aglc2";

    double[] sol1 = problem1();

    boolean[] sol2 = problem2();

    Assess.checkIn(name, login, sol1, sol2);

    //Do not delete or alter the next line
    long endT = System.currentTimeMillis();
    System.out.println("Total execution time was: " + ((endT - startT) / 1000.0) + " seconds");
  }

  private static double[] problem1() {
    int minimumGenerations = 10;
    int populationSize = 2500;
    List<Chromosome> population = populate(populationSize);
    ArrayList<Chromosome> winnerList = new ArrayList<>();

    boolean converged = false;
    while(!converged) {
      // Discover fitness
      for (Chromosome chromosome : population) {
        chromosome.setFitness(Assess.getTest1(chromosome.getData()));
      }

      // Sort by fitness
      population.sort(Comparator.comparing(Chromosome::getFitness));
      winnerList.add(population.get(0));

      if (winnerList.size() > minimumGenerations) {
        converged =
          winnerList.get(winnerList.size() - 1).equals(winnerList.get(winnerList.size() - 2)) &&
            winnerList.get(winnerList.size() - 2).equals(winnerList.get(winnerList.size() - 3));
      }

      // Cull, and then breed the fittest
      population = population.subList(0, population.size() / 2);
      for (int j = 0; j < populationSize / 4; j++) {
        population.addAll(Chromosome.breed(population.get(j * 2), population.get(j * 2 + 1), true));
      }
    }


    return winnerList.get(winnerList.size() - 1).getData();
  }

  private static ArrayList<Chromosome> populate(int size) {
    ArrayList<Chromosome> population = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      population.add(new Chromosome());
    }
    return population;
  }

  private static boolean[] problem2() {
    System.out.println(" ");
    System.out.println(" ");
    System.out.println("Now let us turn to the second problem:");
    System.out.println("A sample solution in this case is a boolean array of size 100.");
    System.out.println("I now create a random sample solution and get the weight and utility:");

    //Creating a sample solution for the second problem
    //The higher the fitness, the better, but be careful of  the weight constraint!
    boolean[] sol2 = new boolean[100];
    for (int i = 0; i < sol2.length; i++) {
      sol2[i] = (Math.random() > 0.5);
    }

    //Now checking the fitness of the candidate solution
    double[] tmp = (Assess.getTest2(sol2));

    //The index 0 of tmp gives the weight. Index 1 gives the utility
    System.out.println("The weight is: " + tmp[0]);
    System.out.println("The utility is: " + tmp[1]);
    return sol2;
  }


}
