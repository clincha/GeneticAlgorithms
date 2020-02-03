import java.util.ArrayList;
import java.util.List;

public class BooleanGeneration extends Generation {

  public BooleanGeneration(int populationSize) {
    super(populationSize);
  }

  public BooleanGeneration(Generation previousGeneration, int populationSize) {
    super(previousGeneration, populationSize);
  }



  @Override
  List<? extends Chromosome<Boolean>> populate(int size) {
    ArrayList<BooleanChromosome> result = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      result.add(new BooleanChromosome());
    }
    return result;
  }
}
