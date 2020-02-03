import java.util.List;
import java.util.Random;

public class BooleanChromosome extends Chromosome<Boolean> {

  static final int LENGTH = 100;
  private static final double MUTATION_RATE = 1;
  private static final int MUTATION_LENGTH = 5;
  private static final int WEIGHT_LIMIT = 500;

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

  public List<BooleanChromosome> breedWith(Chromosome<Boolean> father, boolean mutate) {
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

  private void checkAnswers() {
    boolean[] primitiveArray = new boolean[this.getData().length];
    for (int i = 0; i < primitiveArray.length; i++) {
      primitiveArray[i] = this.getData()[i];
    }
    this.weight = Assess.getTest2(primitiveArray)[0];
    this.fitness = Assess.getTest2(primitiveArray)[1];

    if (weight > WEIGHT_LIMIT) {
      this.fitness = 0;
    }
  }

  public double getWeight() {
    checkAnswers();
    return this.fitness;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public Double getFitness() {
    checkAnswers();
    return this.fitness;
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
