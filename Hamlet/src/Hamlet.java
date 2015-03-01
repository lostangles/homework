import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Hamlet {

	public static void main(String[] arg) throws FileNotFoundException
		{

			final ArrayList<WordElem> myList = new ArrayList<WordElem>();
			Scanner in = new Scanner(new File("hamlet.txt"));
			final Map<String, Integer> words = new TreeMap<String, Integer>();

			while (in.hasNext()) {
				final String tmp = helper(in.next());
				words.put(tmp, 0);

			}
			
			in = new Scanner(new File("hamlet.txt"));
			while (in.hasNext()) {
				final String tmp = helper(in.next());
				words.put(tmp, (words.get(tmp) + 1));

			}

			final Iterator<String> itr = words.keySet().iterator();
			while (itr.hasNext()) {
				final String temp2 = itr.next();
				final WordElem temp = new WordElem(temp2, words.get(temp2));
				myList.add(temp);
			}

			Collections.sort(myList);

			for (int i = 0; i < 10; i++) {
				System.out.println(myList.get(i));
			}

		}

	static String helper(String str)
		{
			String res = "";
			for (final char c : str.toCharArray()) {
				if (Character.isLetter(c))
					res = res + c;
			}

			return res.toUpperCase();
		}

}

class WordElem implements Comparable<WordElem> {
	String str;
	int num;

	WordElem(String str, int num) {
			this.str = str;
			this.num = num;
	}

	@Override
	public int compareTo(WordElem wordelem)
		{
			final int before = -1;
			final int equal = 0;
			final int after = 1;

			if (this.num == wordelem.num)
				return equal;
			if (this.num > wordelem.num)
				return before;
			if (this.num < wordelem.num)
				return after;

			return equal;
		}

	@Override
	public String toString()
		{

			return str + " (" + num + ")";
		}
}