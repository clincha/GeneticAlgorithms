import java.util.ArrayList;
import java.util.List;

public class DoubleGeneration extends Generation<Double> {

  public DoubleGeneration(Generation<Double> previousGeneration, int populationSize) {
    super(previousGeneration, populationSize);
  }

  @Override
  List<Chromosome<Double>> populate(int size) {
    ArrayList<Chromosome<Double>> result = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      result.add(new DoubleChromosome());
    }
    return result;
  }
}
