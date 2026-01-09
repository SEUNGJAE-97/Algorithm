import java.io.*;
import java.util.*;

public class Main {
	
	private static class Node{
		Object data;
		Node left;
		Node right;
		
		private Node(Object data) {
            this.data = data;
        }
		
		private void postOrder(Node node) {
			if (node == null) return;
			this.postOrder(node.left);
			this.postOrder(node.right);
			System.out.print(node.data); 
		}
		
		private void preOrder(Node node) {
			if (node == null) return;
			System.out.print(node.data); 
			this.preOrder(node.left);
			this.preOrder(node.right);
		}
		private void inOrder(Node node) {
			if (node == null) return;
			this.inOrder(node.left);
			System.out.print(node.data); 
			this.inOrder(node.right);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Node[] tree = new Node[26];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char p = st.nextToken().charAt(0);
		    char l = st.nextToken().charAt(0);
		    char r = st.nextToken().charAt(0);
		    
			if(tree[p-'A']== null) {
				tree[p - 'A'] = new Node(p);
			}

		    if (l != '.') {
		        if (tree[l - 'A'] == null) tree[l - 'A'] = new Node(l);
		        tree[p - 'A'].left = tree[l - 'A'];
		    }
		    
		    if (r != '.') {
		        if (tree[r - 'A'] == null) tree[r - 'A'] = new Node(r);
		        tree[p - 'A'].right = tree[r - 'A'];
		    }
		}
		Node root = tree[0];
        root.preOrder(root);
        System.out.println();
        root.inOrder(root);
        System.out.println();
        root.postOrder(root);
	}

}
