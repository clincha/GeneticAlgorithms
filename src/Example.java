//This is my example Solution

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    List<DoubleChromosome> population = DoubleChromosome.createDoubleChromosomePopulation(populationSize);
    ArrayList<DoubleChromosome> winnerList = new ArrayList<>();
    boolean converged = false;

    while (!converged) {
      for (DoubleChromosome chromosome : population) {
        chromosome.setFitness(Assess.getTest1(Arrays.stream(chromosome.getData()).mapToDouble(Double::doubleValue).toArray()));
      }

      population.sort(Comparator.comparing(DoubleChromosome::getFitness));
      winnerList.add(population.get(0));

      converged = isConverged(minimumGenerations, winnerList);

      population = population.subList(0, population.size() / 2);
      for (int j = 0; j < populationSize / 4; j++) {
        population.addAll(population.get(j * 2).breedWith(population.get(j * 2 + 1), true));
      }
    }
    return Arrays.stream(winnerList.get(winnerList.size() - 1).getData()).mapToDouble(Double::doubleValue).toArray();
  }

  private static boolean[] problem2() {
    int minimumGenerations = 100;
    int populationSize = 2500;
    boolean converged = false;

    ArrayList<BooleanGeneration> generations = new ArrayList<>();
    while (!converged) {
      BooleanGeneration previousGeneration = generations.isEmpty() ? null : generations.get(generations.size() - 1);
      BooleanGeneration generation = new BooleanGeneration(previousGeneration, populationSize);

      generations.add(generation);

      System.out.println("Generation " + generation.getGeneration() + " has fitness of " + generation.getWinner().getFitness());

      converged = isConverged(minimumGenerations, generations.stream().map(Generation::getWinner).collect(Collectors.toCollection(ArrayList::new)));
    }
    return convertToPrimitiveArray((BooleanChromosome) (generations.get(generations.size() - 1).getWinner()));
  }

  private static boolean isConverged(int minimumGenerations, List winnerList) {
    boolean converged = false;
    if (winnerList.size() > minimumGenerations) {
      converged =
        winnerList.get(winnerList.size() - 1).equals(winnerList.get(winnerList.size() - 2)) &&
          winnerList.get(winnerList.size() - 2).equals(winnerList.get(winnerList.size() - 3));
    }
    return converged;
  }

  private static boolean[] convertToPrimitiveArray(BooleanChromosome booleanChromosome) {
    boolean[] result = new boolean[booleanChromosome.getData().length];
    for (int i = 0; i < booleanChromosome.getData().length; i++) {
      result[i] = booleanChromosome.getData()[i];
    }
    return result;
  }


}
