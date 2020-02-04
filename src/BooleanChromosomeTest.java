import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BooleanChromosomeTest {

  @Test
  public void mutateTest() {
    Boolean[] testData = getAllBooleanTestValues(true);
    BooleanChromosome chromosome = new BooleanChromosome(Arrays.copyOf(testData, BooleanChromosome.LENGTH));
    chromosome.mutate();
    assertFalse("Mutation has not occurred", Arrays.equals(testData, chromosome.getData()));
  }

  private Boolean[] getAllBooleanTestValues(Boolean value) {
    Boolean[] testData = new Boolean[BooleanChromosome.LENGTH];
    for (int i = 0; i < BooleanChromosome.LENGTH; i++) {
      testData[i] = value;
    }
    return testData;
  }

  @Test
  public void breedTest() {
    BooleanChromosome mother = new BooleanChromosome(Arrays.copyOf(getAllBooleanTestValues(true), BooleanChromosome.LENGTH));
    BooleanChromosome father = new BooleanChromosome(Arrays.copyOf(getAllBooleanTestValues(false), BooleanChromosome.LENGTH));

    List<Chromosome<Boolean>> children = mother.breedWith(father, false);

    assertEquals(children.get(0).getData()[0], father.getData()[0]);
    assertEquals(children.get(0).getData()[children.get(0).getData().length - 1], mother.getData()[0]);

    assertEquals(children.get(1).getData()[0], mother.getData()[0]);
    assertEquals(children.get(1).getData()[children.get(0).getData().length - 1], father.getData()[0]);
  }

}
