package minmax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class minmax {
	public static void main(String[] args) throws IOException {

		String val = "";
		Scanner s = new Scanner(new File("inputdata.txt")).useDelimiter("#");
		List<String> ls = new ArrayList<String>();

		while (s.hasNext()) {
			val = s.next();
			ls.add(val);
		}
		s.close();
		ArrayList<Sensor> objs;
		objs = new ArrayList<>();
		for (String a : ls) { 
			String[] attr = a.split(";");
			objs.add(new Sensor(attr[0], attr[1], attr[2], attr[3]));
		}
		Sensor[] senObj = objs.toArray(new Sensor[0]);
		int n = senObj.length;
		int a[] = new int[n];
		int min[] = new int[n];
		int max[] = new int[n];
		int mi = 0, ma = 0;
		for (int i = 0; i < n; i++) {
			a[i] = senObj[i].getTemp();
		}

		if (n % 2 == 0) {
			if (a[0] > a[1]) {
				max[0] = a[0];
				min[0] = a[1];
				mi = min[0];
				ma = max[0];
			} else {
				max[0] = a[1];
				min[0] = a[0];
				mi = min[0];
				ma = max[0];

			}
			for (int i = 2; i < n; i += 2) {
				if (a[i] < a[i + 1]) {
					max[i / 2] = a[i + 1];
					min[i / 2] = a[i];
				} else {
					max[i / 2] = a[i];
					min[i / 2] = a[i + 1];
				}
				if (max[i / 2] > max[(i / 2) - 1]) {
					ma = max[i / 2];
				} else {
					ma = max[(i / 2) - 1];
				}
				if (min[i / 2] > min[(i / 2) - 1]) {
					mi = min[(i / 2) - 1];
				} else {
					mi = min[(i / 2)];
				}

			}
			System.out.println(mi + " " + ma);
		} else {
			min[0] = a[0];
			max[0] = a[0];
			for (int i = 1; i < n; i += 2) {
				if (a[i] < a[i + 1]) {
					max[(i + 1) / 2] = a[i + 1];
					min[i / 2] = a[i];
				} else {
					max[((i + 1) / 2) / 2] = a[i];
					min[((i + 1) / 2)] = a[i + 1];
				}
				if (max[((i + 1) / 2)] > max[(((i + 1) / 2)) - 1]) {
					ma = max[((i + 1) / 2)];
				} else {
					ma = max[((i + 1) / 2) - 1];
				}
				if (min[((i + 1) / 2)] > min[((i + 1) / 2) - 1]) {
					mi = min[((i + 1) / 2) - 1];
				} else {
					mi = min[((i + 1) / 2)];
				}

			}
			System.out.println(mi + " " + ma);
		}
	}
}

class Sensor {
	String sensor_id;
	String place;
	String time;
	int temp;

	Sensor(String id, String p, String t, String tmp) {
		sensor_id = id;
		place = p;
		time = t;
		temp = Integer.parseInt(tmp);
	}

	public int getTemp() {
		return this.temp;
	}

}
