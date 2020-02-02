import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;

public class ChromosomeTest {

  @Test
  public void mutateTest() {
    double[] testData = new double[20];
    for (int i = 0; i < 20; i++) {
      testData[i] = 0;
    }
    Chromosome chromosome = new Chromosome(Arrays.copyOf(testData, 20));
    chromosome.mutate();
    assertFalse("Mutation has not occurred", Arrays.equals(testData, chromosome.getData()));
  }

}
