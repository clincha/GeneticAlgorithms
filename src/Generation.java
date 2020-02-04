import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract class Generation<T> {

  List<Chromosome<T>> population;

  private int generation;
  private Chromosome<T> winner;

  public Generation(int populationSize) {
    this(null, populationSize);
  }

  public Generation(Generation<T> previousGeneration, int populationSize) {
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

    List<Chromosome<T>> newPopulation = cull();

    population = breed(populationSize, newPopulation);
  }

  private List<Chromosome<T>> cull() {
    return population.subList(0, population.size() / 2);
  }

  private List<Chromosome<T>> breed(int populationSize, List<Chromosome<T>> population) {
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

  public Chromosome<T> getWinner() {
    return winner;
  }

  public void setWinner(Chromosome<T> winner) {
    this.winner = winner;
  }

  public List<Chromosome<T>> getPopulation() {
    return population;
  }

  public void setPopulation(List<Chromosome<T>> population) {
    this.population = population;
  }

  abstract List<Chromosome<T>> populate(int size);
}
