import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DoubleChromosome extends Chromosome<Double> {

  static final int LENGTH = 20;
  private static final Double MIN = -0.5, MAX = 0.5;
  private static final Double MUTATION_RATE = 0.001;
  private static final int MUTATION_LENGTH = 4;

  private Double[] data;
  private Double fitness;

  DoubleChromosome() {
    data = new Double[LENGTH];
    Random random = new Random();
    for (int j = 0; j < LENGTH; j++) {
      data[j] = random.nextDouble() - 0.5;
    }
  }

  DoubleChromosome(Double[] data) {
    this.data = data;
  }

  public static List<DoubleChromosome> createDoubleChromosomePopulation(int size) {
    ArrayList<DoubleChromosome> population = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      population.add(new DoubleChromosome());
    }
    return population;
  }

  List<DoubleChromosome> breedWith(Chromosome<Double> father, boolean mutate) {
    Random random = new Random();

    Double[] sonData = new Double[LENGTH];
    Double[] daughterData = new Double[LENGTH];

    crossover(LENGTH, this, father, sonData, daughterData);
    DoubleChromosome son = new DoubleChromosome(sonData);
    DoubleChromosome daughter = new DoubleChromosome(daughterData);

    // Mutate
    if (random.nextDouble() < MUTATION_RATE && mutate) {
      son.mutate();
      daughter.mutate();
    }

    return List.of(son, daughter);
  }

  void mutate() {
    Random random = new Random();
    double change = random.nextDouble() - 0.5;
    int startPosition = random.nextInt(LENGTH);
    for (int gene = 0; gene < MUTATION_LENGTH; gene++) {
      this.data[(gene + startPosition) % LENGTH] += change;
      if (this.data[(gene + startPosition) % LENGTH] < MIN) {
        this.data[(gene + startPosition) % LENGTH] = MIN;
      }
      if (this.data[(gene + startPosition) % LENGTH] > MAX) {
        this.data[(gene + startPosition) % LENGTH] = MAX;
      }
      change = random.nextDouble() - 0.5;
    }
    this.setData(this.data);
  }

  Double calculateFitness() {
    double[] primitiveArray = new double[this.data.length];
    for (int i = 0; i < primitiveArray.length; i++) {
      primitiveArray[i] = this.data[i];
    }
    return Assess.getTest1(primitiveArray);
  }

  @Override
  public String toString() {
    return "Chromosome{" +
      "fitness=" + fitness +
      '}';
  }

  Double[] getData() {
    return data;
  }

  private void setData(Double[] data) {
    this.data = data;
  }
}
