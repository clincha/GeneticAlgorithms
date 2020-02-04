import java.util.List;
import java.util.Random;

public class BooleanChromosome extends Chromosome<Boolean> {

  static final int LENGTH = 100;
  private static final int WEIGHT_LIMIT = 500;

  private Boolean[] data;

  public BooleanChromosome() {
    data = new Boolean[LENGTH];
    Random random = new Random();
    for (int j = 0; j < LENGTH; j++) {
      data[j] = false;
    }
    data[random.nextInt(LENGTH)] = true;
    calculateFitness();
  }

  public BooleanChromosome(Boolean[] data) {
    this.setData(data);
    calculateFitness();
  }

  public List<Chromosome<Boolean>> breedWith(Chromosome<Boolean> father, boolean mutate) {
    Random random = new Random();

    Boolean[] sonData = new Boolean[LENGTH];
    Boolean[] daughterData = new Boolean[LENGTH];

    crossover(LENGTH, this, father, sonData, daughterData);
    BooleanChromosome son = new BooleanChromosome(sonData);
    BooleanChromosome daughter = new BooleanChromosome(daughterData);

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

  public Double calculateFitness() {
    boolean[] primitiveArray = new boolean[this.data.length];
    for (int i = 0; i < primitiveArray.length; i++) {
      primitiveArray[i] = this.data[i];
    }
    double[] results = Assess.getTest2(primitiveArray);
    double weight = results[0];
    return weight > WEIGHT_LIMIT ? 0 : results[1];
  }

  @Override
  public int compareTo(Chromosome o) {
    return o.calculateFitness().compareTo(this.calculateFitness());
  }

  public Boolean[] getData() {
    return data;
  }

  public void setData(Boolean[] data) {
    this.data = data;
  }
}
