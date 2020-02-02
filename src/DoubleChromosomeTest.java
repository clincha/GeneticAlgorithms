import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DoubleChromosomeTest {

  @Test
  public void mutateTest() {
    double[] testData = getAllIntTestValues(0);
    DoubleChromosome chromosome = new DoubleChromosome(Arrays.copyOf(testData, DoubleChromosome.LENGTH));
    chromosome.mutate();
    assertFalse("Mutation has not occurred", Arrays.equals(testData, chromosome.getData()));
  }

  private double[] getAllIntTestValues(int value) {
    double[] testData = new double[DoubleChromosome.LENGTH];
    for (int i = 0; i < DoubleChromosome.LENGTH; i++) {
      testData[i] = value;
    }
    return testData;
  }

  @Test
  public void breedTest() {
    DoubleChromosome mother = new DoubleChromosome(Arrays.copyOf(getAllIntTestValues(0), DoubleChromosome.LENGTH));
    DoubleChromosome father = new DoubleChromosome(Arrays.copyOf(getAllIntTestValues(1), DoubleChromosome.LENGTH));

    List<DoubleChromosome> children = DoubleChromosome.breed(mother, father, false);

    assertEquals(children.get(0).getData()[0], father.getData()[0], 0.0);
    assertEquals(children.get(0).getData()[children.get(0).getData().length - 1], mother.getData()[0], 0.0);

    assertEquals(children.get(1).getData()[0], mother.getData()[0], 0.0);
    assertEquals(children.get(1).getData()[children.get(0).getData().length - 1], father.getData()[0], 0.0);
  }

}
