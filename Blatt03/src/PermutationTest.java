import java.util.LinkedList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PermutationTest {
	PermutationVariation p1;
	PermutationVariation p2;
	public int n1;
	public int n2;
	int cases=0;

	int anzahl_de(int len){
		if(len == 0){
			return 1;
		}
		if(len == 1){
			return 0;
		}
		return (anzahl_de(len-1) + anzahl_de(len-2))*(len-1);
	}

	void initialize() {
		n1=4;
		n2=6;
		Cases c= new Cases();
		p1= c.switchforTesting(cases, n1);
		p2= c.switchforTesting(cases, n2);
	}

	public static boolean contains(int[] array,int value) {

		boolean result = false;

		for(int i : array){
			if(i == value){
				result = true;
				break;
			}
		}
		return result;
	}

	@Test
	void testPermutation() {
		initialize();
		// TODO
		assertNotNull(p1,"Die Folge ist nicht initialisiert");
		assertEquals(n1, p1.original.length,"Länge stimmt nicht");
		assertNotNull(p2,"Die Folge ist nicht initialisiert");
		assertEquals(n2, p2.original.length,"Länge stimmt nicht");
		for(int i=0; i<n1-1; i++){
			for(int j=i+1;j<n1;j++){
				assertNotEquals(p1.original[i],p1.original[j],"Die Zahl "+p1.original[i]+" kommt doppelt vor");
			}
		}
		for(int i=0; i<n2-1; i++){
			for(int j=i+1;j<n2;j++){
				assertNotEquals(p2.original[i],p2.original[j],"Die Zahl "+p2.original[i]+" kommt doppelt vor");
			}
		}
		assertNotNull(p1.allDerangements,"Die Liste ist nicht initialisiert");
		assertTrue(p1.allDerangements.isEmpty(),"Die Liste ist nicht leer");
		assertNotNull(p2.allDerangements,"Die Liste ist nicht initialisiert");
		assertTrue(p2.allDerangements.isEmpty(),"Die Liste ist nicht leer");
	}

	@Test
	void testDerangements() {
		initialize();
		//in case there is something wrong with the constructor
		fixConstructor();
		// TODO
		p1.derangements();
		p2.derangements();
		assertEquals(anzahl_de(p1.original.length),p1.allDerangements.size(),"Die Anzahl der Derangments stimmt nicht");
		assertEquals(anzahl_de(p2.original.length),p2.allDerangements.size(),"Die Anzahl der Derangments stimmt nicht");
		for(int i=0;i<p1.allDerangements.size();i++){
			for (int j= 0; j<p1.original.length;j++ ) {
				assertNotEquals(p1.original[j], p1.allDerangements.get(i)[j], "Das Derangment ist nicht fixpunktfrei");
			}
		}
		for(int i=0;i<p2.allDerangements.size();i++){
			for (int j= 0; j<p2.original.length;j++ ) {
				assertNotEquals(p2.original[j], p2.allDerangements.get(i)[j], "Das Derangment ist nicht fixpunktfrei");
			}
		}
	}

	@Test
	void testsameElements() {
		initialize();
		//in case there is something wrong with the constructor
		fixConstructor();
		// TODO
		p1.derangements();
		p2.derangements();
		assertFalse(p1.allDerangements.isEmpty(),"keine Permutation wird berechnet");
		assertFalse(p2.allDerangements.isEmpty(),"keine Permutation wird berechnet");
		for(int i = 0; i<p1.allDerangements.size();i++){
			for(int j=0;j<p1.original.length;j++){
				assertTrue(contains(p1.original,p1.allDerangements.get(i)[j]),"Element nicht aus Original");
			}
		}
		for(int i = 0; i<p2.allDerangements.size();i++){
			for(int j=0;j<p2.original.length;j++){
				assertTrue(contains(p2.original,p2.allDerangements.get(i)[j]),"Element nicht aus Original");
			}
		}
		for(int i = 0; i<p1.allDerangements.size();i++){
			assertEquals(p1.allDerangements.get(i).length,p1.original.length,"Die Länge des Derangement ist nicht gleich");
		}
		for(int i = 0; i<p2.allDerangements.size();i++){
			assertEquals(p2.allDerangements.get(i).length,p2.original.length,"Die Länge des Derangement ist nicht gleich");
		}
	}

	void setCases(int c) {
		this.cases=c;
	}

	public void fixConstructor() {
		//in case there is something wrong with the constructor
		p1.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n1;i++)
			p1.original[i]=2*i+1;

		p2.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n2;i++)
			p2.original[i]=i+1;
	}
}


