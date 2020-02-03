import java.util.ArrayList;
import java.util.List;

public class DoubleGeneration extends Generation {

  public DoubleGeneration(Generation previousGeneration, int populationSize) {
    super(previousGeneration, populationSize);
  }

  @Override
  List<? extends Chromosome<Double>> populate(int size) {
    ArrayList<DoubleChromosome> result = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      result.add(new DoubleChromosome());
    }
    return result;
  }
}
