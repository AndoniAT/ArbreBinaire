package arbre;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Implantation de l'interface Collection basée sur les arbres binaires de
 * recherche. Les éléments sont ordonnés soit en utilisant l'ordre naturel (cf
 * Comparable) soit avec un Comparator fourni à la création.
 * 
 * Certaines méthodes de AbstractCollection doivent être surchargées pour plus
 * d'efficacité.
 * 
 * @param <E> le type des clés stockées dans l'arbre
 */
public class ABR<E> extends AbstractCollection<E> {
	private Noeud racine;
	private int taille;
	private Comparator<? super E> cmp;

	// ----------------- NOEUD --------------------------
	
	private class Noeud {
		E cle;
		Noeud gauche;
		Noeud droit;
		Noeud pere;

		Noeud(E cle) {
			this.cle = cle;
			this.gauche = null;
			this.droit = null;
			this.pere = null;
		}

		/**
		 * Renvoie le noeud contenant la cle minimale du sous-arbre enracine
		 * dans ce noeud
		 * 
		 * @return le noeud contenant la cle minimale du sous-arbre enracine
		 *         dans ce noeud
		 */
		Noeud minimum() {
			if(ilyaFilsGauche()) {
				return this.gauche.minimum();
			} else {
				return this;
			}
			
		}

		/**
		 * Renvoie le successeur de ce noeud
		 * 
		 * @return le noeud contenant la cle qui suit la cle de ce noeud dans
		 *         l'ordre des cles, null si c'es le noeud contenant la plus
		 *         grande cle
		 */
		Noeud suivant() {
			if(ilyaFilsDroit()) {
				return this.droit.minimum();
			}
			
			if(!ilyaFilsDroit() && estFilsGauche()) {
				return this.pere;
			} 
			
			if(!ilyaFilsDroit() && estFilsDroit()) {
				if(this.pere.estRacine()) {
					System.out.println("Il n'y a pas d'element suivant");
					return this;
				}
				
				if (this.pere.estFilsGauche()) {
					return this.pere.pere;
				}
			}
			
			return null;
		}
		
		private boolean estFilsGauche() {
			if(this == this.pere.gauche) {
				return true;
			} else {
				return false;
			}
		}
		
		private boolean estFilsDroit() {
			if(this == this.pere.droit) {
				return true;
			} else {
				return false;
			}
		}
		
		private boolean ilyaFilsDroit(){
			if(this.droit != null) {
				return true;
			} else {
				return false;
			}
		}
		
		private boolean ilyaFilsGauche(){
			if(gauche != null) {
				return true;
			} else {
				return false;
			}
		}
		
		private boolean estRacine() {
			if(pere == null) {
				return true;
			} else {
				return false;
			}
		}
		
		
	}
	
	
	
	// ---------------------------------------------

	// Consructeurs

	/**
	 * Crée un arbre vide. Les Elements sont ordonnes selon l'ordre naturel
	 */
	public ABR() {
		racine = null;
		taille = 0;
		//comp = (e1, e2) -> ((Comparable) e1).compareTo((Comparable) e2);
		
		cmp = new Comparator<E>() {
			public int compare(E e1, E e2) {
				return  ((Comparable<E>) e1).compareTo(e2);
			}
		};
	}
	

	/**
	 * Cree un arbre vide. Les Elemments sont compares selon l'ordre impose par
	 * le comparateur
	 * 
	 * @param cmp le comparateur utilise pour definir l'ordre des Elements
	 */
	public ABR(Comparator<? super E> cmp) {
		
	}

	/**
	 * Constructeur par recopie. Crée un arbre qui contient les mêmes éléments
	 * que c. L'ordre des éléments est l'ordre naturel.
	 * 
	 * @param c
	 *            la collection à copier
	 */
	public ABR(Collection<? extends E> c) {
		// TODO
	}

	@Override
	public Iterator<E> iterator() {
		return new ABRIterator();
	}

	@Override
	public int size() {
		return taille;
	}

	// Quelques méthodes utiles

	/**
	 * Recherche une clé. Cette méthode peut être utilisée par
	 * {@link #contains(Object)} et {@link #remove(Object)}
	 * 
	 * @param o
	 *            la clé à chercher
	 * @return le noeud qui contient la clé ou null si la clé n'est pas trouvée.
	 */
	private Noeud rechercher(Object o) {
		return null;
	}

	/**
	 * Supprime le noeud z. Cette méthode peut être utilisée dans
	 * {@link #remove(Object)} et {@link Iterator#remove()}
	 * 
	 * @param z
	 *            le noeud à supprimer
	 * @return le noeud contenant la clé qui suit celle de z dans l'ordre des
	 *         clés. Cette valeur de retour peut être utile dans
	 *         {@link Iterator#remove()}
	 */
	private Noeud supprimer(Noeud z) {
		// TODO
		return null;
	}

	/**
	 * Les itreateurs doivent parcourir les Elements dans l'ordre ! Ceci peut se
	 * faire facilement en utilisant {@link Noeud#minimum()} et
	 * {@link Noeud#suivant()}
	 */
	private class ABRIterator implements Iterator<E> {
		
		public boolean hasNext() {
			// TODO
			return false;
		}

		public E next() {
			// TODO		
			return null;
		}

		public void remove() {
			// TODO
		}
	}

	// Pour un "joli" affichage

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		toString(racine, buf, "", maxStrLen(racine));
		return buf.toString();
	}

	private void toString(Noeud x, StringBuffer buf, String path, int len) {
		if (x == null)
			return;
		toString(x.droit, buf, path + "D", len);
		for (int i = 0; i < path.length(); i++) {
			for (int j = 0; j < len + 6; j++)
				buf.append(' ');
			char c = ' ';
			if (i == path.length() - 1)
				c = '+';
			else if (path.charAt(i) != path.charAt(i + 1))
				c = '|';
			buf.append(c);
		}
		buf.append("-- " + x.cle.toString());
		if (x.gauche != null || x.droit != null) {
			buf.append(" --");
			for (int j = x.cle.toString().length(); j < len; j++)
				buf.append('-');
			buf.append('|');
		}
		buf.append("\n");
		toString(x.gauche, buf, path + "G", len);
	}

	private int maxStrLen(Noeud x) {
		return x == null ? 0 : Math.max(x.cle.toString().length(),
				Math.max(maxStrLen(x.gauche), maxStrLen(x.droit)));
	}

	// TODO : voir quelles autres methodes il faut surcharger
	@Override
	public boolean add(E e) {
		
		if(racine == null) {
			Noeud noeud = new Noeud(e);
			racine = noeud;
		} else {
			if (cmp.compare(e, racine.cle) < 0) {
				if(racine.gauche == null) {
					Noeud noeud = new Noeud(e);
					racine.gauche = noeud;
					noeud.pere = racine;
				} else {
					ABR abrtmp = new ABR<>();
					abrtmp.racine = racine.gauche;
					return abrtmp.add(e);
				}
				
			} else if(e.equals(racine.cle)){
				System.out.println("Element " + e + " d�j� existant (Non ajout�)");
				
			} else {
				if(racine.droit == null) {
					Noeud noeud = new Noeud(e);
					racine.droit= noeud;
					noeud.pere = racine;
				} else {
					ABR abrtmp = new ABR<>();
					abrtmp.racine = racine.droit;
					return abrtmp.add(e);
					
				}
				
			}
		
		}
		
		/*
		if(racine.cle.equals(4)) {
			System.out.println("mon fils droit is " + racine.gauche.cle);
			System.out.println("Le suivant de " + racine.pere.cle + " est : " + racine.pere.suivant().cle);
			System.out.println("Le suivant de " + racine.gauche.cle + " est : " + racine.gauche.suivant().cle);
			System.out.println("Le suivant de " + racine.cle + " est : " + racine.suivant().cle);
			System.out.println("Le suivant de " + racine.suivant().cle + " est : " + racine.suivant().suivant().cle);			
		}
		
		if(racine.cle.equals(8)) {
			System.out.println("Le suivant de " + racine.cle + " est : " + racine.suivant().cle);
		}
		
		if(racine.cle.equals(7)) {
			System.out.println("Le suivant de " + racine.cle + " est : " + racine.suivant().cle);
			System.out.println("Le suivant de " + racine.droit.cle + " est : " + racine.droit.suivant().cle);
		}*/
		
		
		
		return false;
		
	}
	
}
	
	