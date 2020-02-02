import java.util.List;
import java.util.Random;

public class BooleanChromosome extends Chromosome<Boolean> {

  static final int LENGTH = 100;
  private static final double MUTATION_RATE = 1;
  private static final int MUTATION_LENGTH = 5;

  private double weight;
  private double fitness;
  private Boolean[] data;

  public BooleanChromosome() {
    data = new Boolean[LENGTH];
    Random random = new Random();
    for (int j = 0; j < LENGTH; j++) {
      data[j] = false;
    }
    data[random.nextInt(LENGTH)] = true;
  }

  public BooleanChromosome(Boolean[] data) {
    this.setData(data);
  }

  public List<BooleanChromosome> breedWith(BooleanChromosome father, boolean mutate) {
    Random random = new Random();

    Boolean[] sonData = new Boolean[LENGTH];
    Boolean[] daughterData = new Boolean[LENGTH];

    crossover(LENGTH, this, father, sonData, daughterData);
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

  public Boolean[] getData() {
    return data;
  }

  public void setData(Boolean[] data) {
    this.data = data;
  }
}
