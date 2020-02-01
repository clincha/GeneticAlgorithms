import java.util.List;
import java.util.Random;

class Chromosome {

  private static final int LENGTH = 20;
  private static final double MUTATION_RATE = 0.01;
  private static final double MIN = -0.5, MAX = 0.5;

  private double[] data;
  private double fitness;

  private Chromosome(double[] data) {
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

  static List<Chromosome> breed(Chromosome mother, Chromosome father) {
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

    // Mutate
    if (random.nextDouble() < 0.01) {
      int mutatePosition = random.nextInt(sonData.length);
      sonData[mutatePosition] = sonData[mutatePosition] + random.nextDouble() * 2 - 1;
      daughterData[mutatePosition] = daughterData[mutatePosition] + random.nextDouble() * 2 - 1;
      if (sonData[mutatePosition] < -5) {
        sonData[mutatePosition] = -5;
      }
      if (sonData[mutatePosition] > 5) {
        sonData[mutatePosition] = 5;
      }
      if (daughterData[mutatePosition] < -5) {
        daughterData[mutatePosition] = -5;
      }
      if (daughterData[mutatePosition] > 5) {
        daughterData[mutatePosition] = 5;
      }
    }
    return List.of(new Chromosome(sonData), new Chromosome(daughterData));
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

  public void setData(double[] data) {
    this.data = data;
  }

  double getFitness() {
    return fitness;
  }

  void setFitness(double fitness) {
    this.fitness = fitness;
  }
}
