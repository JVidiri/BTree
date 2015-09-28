package hto.edu.ifsp.searchbtree.tests;

import hto.edu.ifsp.searchbtree.control.BTree;

public class BTreeRemoveTests {
	public static void main(String[] args) {
		BTree teste = new BTree();
		teste.insertKey(0);
		teste.insertKey(-10);
		teste.insertKey(-5);
		teste.insertKey(-15);
		teste.insertKey(10);
		teste.insertKey(5);
		teste.insertKey(15);
		teste.insertKey(8);
		teste.insertKey(-8);
		teste.insertKey(9);
		System.out.println("Arvore completa");
		teste.print();
		System.out.println("removendo nó cheio -10");
		teste.removeKey(-10);
		teste.print();
		System.out.println("removendo nó com apenas um filho: 8");
		teste.removeKey(8);
		teste.print();
		System.out.println("removendo folha: -15");
		teste.removeKey(-15);
		teste.print();
		//tentando remover nó que não existe
		System.out.println("removendo nó inexistente: 24");
		boolean remocao = teste.removeKey(24);
		if (remocao == true){
			System.out.println("remoção retornou true");
		}else{
			System.out.println("remoção retornou false");
		}
	}
}
