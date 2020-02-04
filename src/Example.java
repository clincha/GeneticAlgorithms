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
    int populationSize = 2500;

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
    int populationSize = 2500;

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

  private static boolean isConverged(int minimumGenerations, List<Chromosome<?>> generations) {
    boolean converged = false;
    if (generations.size() > minimumGenerations) {
      converged =
        generations.get(generations.size() - 1).equals(generations.get(generations.size() - 2)) &&
          generations.get(generations.size() - 2).equals(generations.get(generations.size() - 3));
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
