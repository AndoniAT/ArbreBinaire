package arbre;

import java.util.Iterator;

import javax.print.attribute.standard.MediaSize.Other;

public class Main {

	public static void main(String[] args) {
		System.out.println("Creation d'arbre");
		ABR<Integer> abr = new ABR<>();
		abr.add(5);
		abr.add(2);
		abr.add(10);
		abr.add(1);
		abr.add(4);
		abr.add(7);
		abr.add(3);
		abr.add(9);
		
		System.out.println(abr.toString());
		
		Iterator<Integer> it = abr.iterator();
		
		//it.remove();
		it.next();
		it.remove();
		it = abr.iterator();
		while(it.hasNext()) {
			it.next();
		}
		
		
		
		
		
		
		
		
		
		
	}

}
