import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class SortHashMapByValues {

	public LinkedHashMap<Integer, String> sortHashMapByValues(HashMap<Integer, String> passedMap) {
		List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
		List<String> mapValues = new ArrayList<>(passedMap.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);
		Collections.sort(mapKeys, Collections.reverseOrder());
		LinkedHashMap<Integer, String> sortedMap = new LinkedHashMap<>();

		Iterator<String> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			String val = valueIt.next();
			Iterator<Integer> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				Integer key = keyIt.next();
				String comp1 = passedMap.get(key);
				String comp2 = val;

				if (comp1.equals(comp2)) {
					keyIt.remove();
					sortedMap.put(key, val);
					break;
				}
			}
		}
		return sortedMap;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "froyo");
		map.put(2, "abby");
		map.put(3, "denver");
		map.put(4, "frost");
		map.put(5, "daisy");
		SortHashMapByValues s = new SortHashMapByValues();
		LinkedHashMap<Integer, String> sortedMap1 = new LinkedHashMap<>();
		sortedMap1 = s.sortHashMapByValues(map);
		for (int i : sortedMap1.keySet()) {
			System.out.println("key" + i + " value " + sortedMap1.get(i));
		}
		ArrayList<Integer> x = new ArrayList<Integer>();

	}

}
