import java.util.ArrayList;
import java.util.List;

public class BooleanGeneration extends Generation<Boolean> {

  public BooleanGeneration(Generation<Boolean> previousGeneration, int populationSize) {
    super(previousGeneration, populationSize);
  }

  @Override
  List<Chromosome<Boolean>> populate(int size) {
    ArrayList<Chromosome<Boolean>> result = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      result.add(new BooleanChromosome());
    }
    return result;
  }
}
