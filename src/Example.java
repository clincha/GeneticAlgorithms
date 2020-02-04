//This is my example Solution

import java.util.ArrayList;
import java.util.Arrays;
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
    int minimumGenerations = 100;
    int populationSize = 10000;

    boolean converged = false;
    ArrayList<DoubleGeneration> generations = new ArrayList<>();

    while (!converged) {
      DoubleGeneration previousGeneration = generations.isEmpty() ? null : generations.get(generations.size() - 1);
      DoubleGeneration generation = new DoubleGeneration(previousGeneration, populationSize);

      generations.add(generation);

      converged = isConverged(
        minimumGenerations,
        generations.stream()
          .map(Generation::getWinner)
          .collect(Collectors.toCollection(ArrayList::new))
      );
    }
    return Arrays.stream(generations.get(generations.size() - 1).getWinner().getData()).mapToDouble(Double::doubleValue).toArray();
  }

  private static boolean[] problem2() {
    int minimumGenerations = 100;
    int populationSize = 10000;

    boolean converged = false;
    ArrayList<BooleanGeneration> generations = new ArrayList<>();

    while (!converged) {
      BooleanGeneration previousGeneration = generations.isEmpty() ? null : generations.get(generations.size() - 1);
      BooleanGeneration generation = new BooleanGeneration(previousGeneration, populationSize);

      generations.add(generation);

      converged = isConverged(
        minimumGenerations,
        generations.stream()
          .map(Generation::getWinner)
          .collect(Collectors.toCollection(ArrayList::new))
      );
    }
    return convertToPrimitiveArray((BooleanChromosome) (generations.get(generations.size() - 1).getWinner()));
  }

  private static boolean isConverged(int minimumGenerations, List<Chromosome<?>> winners) {
    boolean converged = false;
    if (winners.size() > minimumGenerations) {
      converged =
        winners.get(winners.size() - 1).calculateFitness() - winners.get(winners.size() - 2).calculateFitness() < 0.0000000000000001 &&
          winners.get(winners.size() - 2).calculateFitness() - winners.get(winners.size() - 3).calculateFitness() < 0.0000000000000001;
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
