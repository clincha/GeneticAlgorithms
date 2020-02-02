import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ChromosomeTest {

  @Test
  public void mutateTest() {
    double[] testData = getAllIntTestValues(0);
    Chromosome chromosome = new Chromosome(Arrays.copyOf(testData, 20));
    chromosome.mutate();
    assertFalse("Mutation has not occurred", Arrays.equals(testData, chromosome.getData()));
  }

  private double[] getAllIntTestValues(int value) {
    double[] testData = new double[20];
    for (int i = 0; i < 20; i++) {
      testData[i] = value;
    }
    return testData;
  }

  @Test
  public void breedTest() {
    Chromosome mother = new Chromosome(Arrays.copyOf(getAllIntTestValues(0), Chromosome.LENGTH));
    Chromosome father = new Chromosome(Arrays.copyOf(getAllIntTestValues(1), Chromosome.LENGTH));

    List<Chromosome> children = Chromosome.breed(mother, father, false);

    assertEquals(children.get(0).getData()[0], father.getData()[0], 0.0);
    assertEquals(children.get(0).getData()[children.get(0).getData().length - 1], mother.getData()[0], 0.0);

    assertEquals(children.get(1).getData()[0], mother.getData()[0], 0.0);
    assertEquals(children.get(1).getData()[children.get(0).getData().length - 1], father.getData()[0], 0.0);
  }

}
