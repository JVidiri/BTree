package hto.edu.ifsp.searchbtree.control;

import hto.edu.ifsp.searchbtree.model.Node;

public class BTree {

	private Node root;

	/**
	 * inserir conteúdo na arvore.
	 * 
	 * @param conteudo
	 * @return
	 */
	public boolean insertKey(int conteudo) {
		root = insert(root, conteudo);
		return true;
	}

	private Node insert(Node actNode, int conteudo) {
		// Se a raiz for nulo, ele insere a raiz.
		if (actNode == null) {
			return new Node(conteudo, null, null, null);
		} else {
			// Se o counteúdo for menor que o conteúdo do Node ele insere à
			// esquerda
			if (conteudo <= actNode.getContent()) {
				actNode.setLeftChild(insert(actNode.getLeftChild(), conteudo));
				// Se o counteúdo for maior que o conteúdo do Node ele insere à
				// direita
			} else {
				actNode.setRightChild(insert(actNode.getRightChild(), conteudo));
			}
		}
		return actNode;
	}

	/**
	 * Metodo de pesquisa para achar o conteudo.
	 * 
	 * @param conteudo
	 * @return
	 */
	public boolean containsKey(int conteudo) {
		return contains(root, conteudo);
	}

	private boolean contains(Node actNode, int conteudo) {
		// Verifica se raiz é nula.
		if (actNode == null) {
			return false;
		}
		// Verifica se o conteúdo da raiz é o valor buscado.
		if (actNode.getContent() == conteudo) {
			return true;
		}
		// Faz recursão a direita.
		if (actNode.getContent() < conteudo) {
			return contains(actNode.getRightChild(), conteudo);
			// Faz recursão a esquerda.
		} else {
			return contains(actNode.getLeftChild(), conteudo);
		}
	}

	//
	/**
	 * Método de retorno tamanho da árvore
	 * 
	 * @param myNode
	 * @return lenght
	 */
	public int length() {
		return lengthTree(root);
	}

	private int lengthTree(Node myNode) {
		int length = 0;
		if (myNode != null) {
			length++;
			// Faz recursão a esquerda contando quantos tem.
			length += lengthTree(myNode.getLeftChild());
			// Faz recursão a direita contando quantos tem.
			length += lengthTree(myNode.getRightChild());
		}
		return length;
	}

	/**
	 * Método verificar se é folha
	 * 
	 * @param toTest
	 * @return
	 */
	public boolean isLeaf(Node toTest) {
		// Verifica tem direita e esquerda nulo.
		if (toTest.getRightChild() == null && toTest.getLeftChild() == null) {
			return true;
		}
		return false;
	}

	public boolean isFullNode(Node toTest) {
		// Verifica tem direita e esquerda nulo.
		if (toTest.getRightChild() != null && toTest.getLeftChild() != null) {
			return true;
		}
		return false;
	}

	public boolean removeKey(int contentToRemove) {
		if (containsKey(contentToRemove)) {
			removeNode(root, contentToRemove);
			return true;
		}
		return false;
	}

	private void changeFatherReference(Node node, Node newReference, int contentRemoved) {
		if (node.getFather().getContent() > contentRemoved) {
			// retirando minha referencia para o pai...
			node.getFather().setLeftChild(newReference);
		} else {
			node.getFather().setRightChild(newReference);
		}
	}

	private Node getMinorRightChild(Node node) {
		Node aux = node.getRightChild().getLeftChild();
		if (null == aux) {
			return node.getRightChild();
		} else {
			while (aux.getLeftChild() != null) {
				aux = aux.getLeftChild();
			}
			return aux;
		}
	}

	private void removeNode(Node node, int contentToRemove) {
		if (node.getContent() == contentToRemove) {
			if (isLeaf(node)) {
				// passando minha referência para nulo
				changeFatherReference(node, null, contentToRemove);
			} else {
				if (isFullNode(node)) {
					// tenho que achar meu menor filho a direita...
					Node minorRightChild = getMinorRightChild(node);
					// e remover ele de maneira segura...
					removeNode(root, minorRightChild.getContent());
					// e depois trocar meu conteudo pelo dele...
					node.setContent(minorRightChild.getContent());
				} else {
					// repassando minha referência para meu filho unico
					if (node.getLeftChild() == null) {
						changeFatherReference(node, node.getRightChild(), contentToRemove);
					} else {
						changeFatherReference(node, node.getLeftChild(), contentToRemove);
					}
				}
			}
		} else {
			// vamos procurar eu sinto que ele está por aqui...
			if (node.getContent() > contentToRemove) {
				removeNode(node.getLeftChild(), contentToRemove);	
			}else{
				removeNode(node.getRightChild(), contentToRemove);
			}
		}
	}

	public void print() {
		root.printTree(System.out);
	}
}
