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

//    double[] sol1 = problem1();

    boolean[] sol2 = problem2();

//    Assess.checkIn(name, login, sol1, sol2);

    //Do not delete or alter the next line
    long endT = System.currentTimeMillis();
    System.out.println("Total execution time was: " + ((endT - startT) / 1000.0) + " seconds");
  }

  private static double[] problem1() {
    int minimumGenerations = 10;
    int populationSize = 2500;
    List<DoubleChromosome> population = createDoubleChromosomePopulation(populationSize);
    ArrayList<DoubleChromosome> winnerList = new ArrayList<>();
    boolean converged = false;

    while (!converged) {
      // Discover fitness
      for (DoubleChromosome chromosome : population) {
        chromosome.setFitness(Assess.getTest1(chromosome.getData()));
      }

      // Sort by fitness
      population.sort(Comparator.comparing(DoubleChromosome::getFitness));
      winnerList.add(population.get(0));

      // Check for convergence
      if (winnerList.size() > minimumGenerations) {
        converged =
          winnerList.get(winnerList.size() - 1).equals(winnerList.get(winnerList.size() - 2)) &&
            winnerList.get(winnerList.size() - 2).equals(winnerList.get(winnerList.size() - 3));
      }

      // Cull, and then breed the fittest
      population = population.subList(0, population.size() / 2);
      for (int j = 0; j < populationSize / 4; j++) {
        population.addAll(DoubleChromosome.breed(population.get(j * 2), population.get(j * 2 + 1), true));
      }
    }
    return winnerList.get(winnerList.size() - 1).getData();
  }

  public static List<DoubleChromosome> createDoubleChromosomePopulation(int size) {
    ArrayList<DoubleChromosome> population = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      population.add(new DoubleChromosome());
    }
    return population;
  }

  private static boolean[] problem2() {
    int limit = 500;
    int minimumGenerations = 100;
    int populationSize = 2500;
    List<BooleanChromosome> population = createBooleanChromosomePopulation(100);
    ArrayList<BooleanChromosome> winnerList = new ArrayList<>();
    boolean converged = false;

    int generation = 1;

    while (!converged) {
      // Discover fitness
      for (BooleanChromosome chromosome : population) {
        double[] results = Assess.getTest2(chromosome.getData());
        chromosome.setWeight(results[0]);
        if (chromosome.getWeight() > limit) {
          chromosome.setFitness(0);
        } else {
          chromosome.setFitness(results[1]);
        }
      }

      population.sort(Comparator.comparing(BooleanChromosome::getFitness).reversed());
      winnerList.add(population.get(0));

      // Check for convergence
      if (winnerList.size() > minimumGenerations) {
        converged =
          winnerList.get(winnerList.size() - 1).equals(winnerList.get(winnerList.size() - 2)) &&
            winnerList.get(winnerList.size() - 2).equals(winnerList.get(winnerList.size() - 3));
      }

      // Cull, and then breed the fittest
      population = population.subList(0, population.size() / 2);
      for (int j = 0; j < populationSize / 4; j++) {
        population.addAll(BooleanChromosome.breed(population.get(j * 2), population.get(j * 2 + 1), true));
      }

      System.out.println("Generation " + generation + ": " + winnerList.get(winnerList.size() - 1).getFitness() + "|" + winnerList.get(winnerList.size() - 1).getWeight());
      generation++;
    }

    System.out.println(winnerList.get(0).getFitness());
    System.out.println(winnerList.get(0).getWeight());

    return winnerList.get(winnerList.size() - 1).getData();
  }

  public static List<BooleanChromosome> createBooleanChromosomePopulation(int size) {
    ArrayList<BooleanChromosome> population = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      population.add(new BooleanChromosome());
    }
    return population;
  }


}
