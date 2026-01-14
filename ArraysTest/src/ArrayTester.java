import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public class ArrayTester {

	private static final int LOOPS = 5;
	private static final int LINE_SIZE = 70;

	public static void main(String[] args) {

		String[] array = null;
		List<String> list = null;
		
		try {
			spaces("The array Size is updating");
			/*
			 * The array Size is updating
			 */
			array = null;
			array = new String[] { "Ardiles" };
			for (int i = 0; i < array.length; i++) {
				String string = i + " = " + array[i];
				if (i < LOOPS) {
					array = (String[]) ArrayUtils.add(array, "Guerra");
				}
				System.out.println(string);
			}

			spaces("The array Size is not updating");

			/*
			 * The array Size is not updating
			 */
			array = null;
			array = new String[] { "Ardiles" };
			for (String string : array) {
				int i = array.length;
				if (i < LOOPS) {
					array = (String[]) ArrayUtils.add(array, "Guerra");
				}
				System.out.println(string);
			}

			spaces("Avoiding ConcurrentModificationException");

			/*
			 * Avoiding ConcurrentModificationException
			 */
			list = null;
			list = new ArrayList<String>();
			list.add("Ardiles");
			for (int i = 0; i < list.size(); i++) {
				String string = i + " = " + list.get(i);
				if (i < LOOPS) {
					list.add("Guerra");
				}
				System.out.println(string);
			}

			spaces("Getting ConcurrentModificationException (Using Iterator)");

			/*
			 * Getting ConcurrentModificationException (Using Iterator)
			 */
			try {
				list = null;
				list = new ArrayList<String>();
				list.add("Ardiles");
				
				for(Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
					int i = list.size();
					String string = i + " = " + iterator.next();
					if (i < LOOPS) {
						list.add("Guerra");
					}
					System.out.println(string);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			spaces("Getting ConcurrentModificationException");
			/*
			 * Getting ConcurrentModificationException
			 */
			try {
				list = null;
				list = new ArrayList<String>();
				list.add("Ardiles");
				for (String string : list) {
					if (list.size() < LOOPS) {
						list.add("Guerra");
					}
					System.out.println(string);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		spaces("Testing ArrayUtils");
		
		arrayUtilsTester();
	}

	private static void arrayUtilsTester() {
	
		String[] array = null;
		
		System.out.println("Array is Empty when null? " + ArrayUtils.isEmpty(array));
		
		array = new String[]{};
		
		System.out.println("Array is Empty when new String[]? " + ArrayUtils.isEmpty(array));
		
		array = null;
		array = new String[]{"Ardiles"};
		
		System.out.println("Array is Empty when new String[]{'Ardiles'}? " + ArrayUtils.isEmpty(array));
		
	}
	
	private static void spaces(String message) {
		
		final String dash = StringUtils.repeat("=", LINE_SIZE);
		System.out.println();
		System.out.println(dash);
		
		final int messageSize = message.length();
		final int spacesSize = (LINE_SIZE - messageSize) / 2;
		
		System.out.println(StringUtils.repeat(" ", spacesSize) + message);
		System.out.println(dash);
		System.out.println();
	}

}
