//This is my example Solution

import java.util.ArrayList;
import java.util.Comparator;

class Example {
  public static void main(String[] args) {
    //Do not delete/alter the next line
    long startT = System.currentTimeMillis();

    //Edit this according to your name and login
    String name = "Angus Clinch";
    String login = "aglc2";

    double[] sol1 = problem1();

//    boolean[] sol2 = problem2();

//    Assess.checkIn(name, login, sol1, sol2);

    //Do not delete or alter the next line
    long endT = System.currentTimeMillis();
    System.out.println("Total execution time was: " + ((endT - startT) / 1000.0) + " seconds");
  }

  private static double[] problem1() {
    int generations = 200;
    int populationSize = 1200;
    ArrayList<Chromosome> population = populate(populationSize);

    Chromosome winner = population.get(0);

    for (int generation = 0; generation < generations; generation++) {
      // Discover fitness
      for (Chromosome chromosome : population) {
        chromosome.setFitness(Assess.getTest1(chromosome.getData()));
      }

      // Sort by fitness
      population.sort(Comparator.comparing(Chromosome::getFitness));
      winner = population.get(0);

      double averageFitness = population.stream().mapToDouble(Chromosome::getFitness).sum() / population.size();
      System.out.println("Generation: " + generation + " | Average Fitness: " + averageFitness);

      // Breed fittest
      for (int j = 0; j < populationSize / 2; j++) {
        population.addAll(Chromosome.breed(population.get(j * 2), population.get(j * 2 + 1)));
      }
    }
    return winner.getData();
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
