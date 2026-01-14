import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang.StringUtils;


public class BigDecimalTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BigDecimal big = new BigDecimal("124.00");
		BigDecimal big2 = new BigDecimal("124.01");
		
		int decimais = big.scale();
		int inteiros = big.precision() - decimais;
		System.out.println("inteiros: " + inteiros + "\n" + 
		                   "Decimais: " + decimais + "\n" + 
				           "CompareTo: " + big2.compareTo(big));
		
		String value = "";
		String[] values = StringUtils.split(value, ",");
		
		System.out.println(values);
		
		BigDecimal big3 = new BigDecimal(2);
		BigDecimal big4 = new BigDecimal(3);
		
		big3.setScale(9, RoundingMode.UP);
		big4.setScale(9, RoundingMode.UP);
		
		System.out.println(big3.divide(big4, 3, RoundingMode.UP));
		
		System.out.println(StringUtils.indexOfAny("Ardiles Guerra de Oliveira".toLowerCase(), new String[]{"ardildes", "huhauhua"}));

	}

}
