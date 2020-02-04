import java.util.List;
import java.util.Random;

public abstract class Chromosome<T> implements Comparable<Chromosome<T>> {

  static final Double MUTATION_RATE = 0.01;
  static final int MUTATION_LENGTH = 5;

  void crossover(int length, Chromosome<T> mother, Chromosome<T> father, T[] sonData, T[] daughterData) {
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      int cutoff = random.nextInt(length);
      if (i < cutoff) {
        sonData[i] = father.getData()[i];
        daughterData[i] = mother.getData()[i];
      } else {
        sonData[i] = mother.getData()[i];
        daughterData[i] = father.getData()[i];
      }
    }
  }

  abstract T[] getData();

  abstract void mutate();

  abstract Double calculateFitness();

  abstract List<Chromosome<T>> breedWith(Chromosome<T> father, boolean mutate);

  @Override
  public int compareTo(Chromosome o) {
    return this.calculateFitness().compareTo(o.calculateFitness());
  }

  @Override
  public String toString() {
    return "Chromosome: " + calculateFitness();
  }
}
