import java.util.Random;

class Chromosome {

  private static final int LENGTH = 20;
  private static final double MIN = -0.5, MAX = 0.5;

  private double[] data;
  private double fitness;

  public Chromosome(double[] data) {
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

  public double[] getData() {
    return data;
  }

  public void setData(double[] data) {
    this.data = data;
  }

  public double getFitness() {
    return fitness;
  }

  public void setFitness(double fitness) {
    this.fitness = fitness;
  }
}
