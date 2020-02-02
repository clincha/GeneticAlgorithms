import java.util.List;
import java.util.Random;

class Chromosome {

  static final int LENGTH = 20;
  private static final double MIN = -0.5, MAX = 0.5;
  private static final double MUTATION_RATE = 0.001;
  private static final int MUTATION_LENGTH = 4;

  private double[] data;
  private double fitness;

  Chromosome(double[] data) {
    this.data = data;
  }

  Chromosome(double[] data, double fitness) {
    this.data = data;
    this.fitness = fitness;
  }

  Chromosome() {
    data = new double[LENGTH];
    Random random = new Random();
    for (int j = 0; j < LENGTH; j++) {
      data[j] = random.nextDouble() - 0.5;
    }
  }

  static List<Chromosome> breed(Chromosome mother, Chromosome father, boolean mutate) {
    Random random = new Random();

    double[] sonData = new double[LENGTH];
    double[] daughterData = new double[LENGTH];

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
    Chromosome son = new Chromosome(sonData);
    Chromosome daughter = new Chromosome(daughterData);

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

  @Override
  public String toString() {
    return "Chromosome{" +
      "fitness=" + fitness +
      '}';
  }

  double[] getData() {
    return data;
  }

  private void setData(double[] data) {
    this.data = data;
  }

  double getFitness() {
    return fitness;
  }

  void setFitness(double fitness) {
    this.fitness = fitness;
  }
}
