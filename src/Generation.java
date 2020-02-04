import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

abstract class Generation {

  protected List<? extends Chromosome> population;
  private int generation;
  private Chromosome winner;

  public Generation(int populationSize) {
    this(null, populationSize);
  }

  public Generation(Generation previousGeneration, int populationSize) {
    if (previousGeneration == null) {
      this.population = populate(populationSize);
      this.generation = 0;
    } else {
      this.population = previousGeneration.getPopulation();
      this.generation = previousGeneration.getGeneration() + 1;
    }

    population = population.stream()
      .sorted()
      .collect(Collectors.toCollection(ArrayList::new));

    winner = population.get(0);

    List<? extends Chromosome> newPopulation = cull();

    population = breed(populationSize, newPopulation);
  }

  private List<? extends Chromosome> cull() {
    return population.subList(0, population.size() / 2);
  }

  private List<? extends Chromosome> breed(int populationSize, List<? extends Chromosome> population) {
    for (int j = 0; j < populationSize / 4; j++) {
      population.addAll(this.population.get(j * 2).breedWith(this.population.get(j * 2 + 1), true));
    }

    return population;
  }

  public int getGeneration() {
    return generation;
  }

  public void setGeneration(int generation) {
    this.generation = generation;
  }

  public Chromosome getWinner() {
    return winner;
  }

  public void setWinner(Chromosome winner) {
    this.winner = winner;
  }

  public List<? extends Chromosome> getPopulation() {
    return population;
  }

  public void setPopulation(List<Chromosome> population) {
    this.population = population;
  }

  abstract List<? extends Chromosome> populate(int size);
}
