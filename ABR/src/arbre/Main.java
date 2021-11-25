package arbre;


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
		
		
	}

}
