
/* package whatever; // don't place package name! */

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.PriorityQueue;

/* Name of the class has to be "Main" only if the class is public. */

class Employee implements Comparator<Employee> {
	int _id;
	int mngr_id;
	String name;

	public Employee(int _id, String name, int mngr_id) {
		this._id = _id;
		this.mngr_id = mngr_id;
		this.name = name;
	}

	public int compare(Employee one, Employee two) {
		return (one.mngr_id - two.mngr_id);
	}

	public static void arrangeHierarchy(String filename) {

	}

	public static void main(String[] args) throws java.lang.Exception {
		// your code goes here
		// C:/Users/jainh/employee.txt
		PriorityQueue<Employee> list = new PriorityQueue<Employee>();

		String filename = "C:\\Users\\SONY\\Documents\\Avusherla\\Docs\\employee.txt";
		Charset charset = Charset.forName("ISO-8859-1");
		for (String line : Files.readAllLines(Paths.get(filename), charset)) {
			String[] parts = line.split("\\s+");
			int e_id = Integer.valueOf(parts[0]);
			String name = parts[1];
			System.out.println(name + "  hi");
			int m_id;
			if (parts[2].matches("Null")) {
				m_id = 0;
			} else {
				m_id = Integer.valueOf(parts[2]);
			}

			Employee e = new Employee(e_id, name, m_id);
			list.add(e);
			System.out.print(" " + m_id);
		}
		// arrangeHierarchy("C:/Users/jainh/employee.txt");
	}

}