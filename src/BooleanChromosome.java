import java.util.Collection;
import java.util.List;
import java.util.Random;

public class BooleanChromosome {

  static final int LENGTH = 100;
  private static final double MUTATION_RATE = 1;
  private static final int MUTATION_LENGTH = 5;

  private double weight;
  private double fitness;
  private boolean[] data;

  public BooleanChromosome() {
    data = new boolean[LENGTH];
    Random random = new Random();
    for (int j = 0; j < LENGTH; j++) {
      data[j] = false;
    }
    data[random.nextInt(LENGTH)] = true;
  }

  public BooleanChromosome(boolean[] data) {
    this.setData(data);
  }

  public static List<BooleanChromosome> breed(BooleanChromosome mother, BooleanChromosome father, boolean mutate) {
    Random random = new Random();

    boolean[] sonData = new boolean[LENGTH];
    boolean[] daughterData = new boolean[LENGTH];

    for (int i = 0; i < LENGTH; i++) {
      int cutoff = random.nextInt(LENGTH);
      if (i < cutoff) {
        sonData[i] = father.getData()[i];
        daughterData[i] = mother.getData()[i];
      } else {
        sonData[i] = mother.getData()[i];
        daughterData[i] = father.getData()[i];
      }
    }
    BooleanChromosome son = new BooleanChromosome(sonData);
    BooleanChromosome daughter = new BooleanChromosome(daughterData);

    // Mutate
    if (random.nextDouble() < MUTATION_RATE && mutate) {
      son.mutate();
      daughter.mutate();
    }

    return List.of(son, daughter);
  }

  void mutate() {
    Random random = new Random();
    int startPosition = random.nextInt(LENGTH);
    for (int gene = 0; gene < MUTATION_LENGTH; gene++) {
      this.data[(gene + startPosition) % LENGTH] = !this.data[(gene + startPosition) % LENGTH];
    }
    this.setData(this.data);
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public double getFitness() {
    return fitness;
  }

  public void setFitness(double fitness) {
    this.fitness = fitness;
  }

  public boolean[] getData() {
    return data;
  }

  public void setData(boolean[] data) {
    this.data = data;
  }
}
