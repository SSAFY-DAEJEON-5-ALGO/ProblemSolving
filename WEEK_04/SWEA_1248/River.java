import java.io.*;
import java.util.*;

public class River{
	static class Node{
		int val;
		Node left;
		Node right;

		public Node(int v) {
			val = v;
			left = null;
			right = null;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());

			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int targetA = Integer.parseInt(st.nextToken());
			int targetB = Integer.parseInt(st.nextToken());

			ArrayList<Node> nodes = new ArrayList<>();
			for(int i=0;i<=V;i++)
				nodes.add(new Node(i));
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				int parent = Integer.parseInt(st.nextToken());
				int son = Integer.parseInt(st.nextToken());

				Node p = nodes.get(parent);
				if(p.left==null)
					p.left = nodes.get(son);
				else {
					if(p.left.val < son)
						p.right = nodes.get(son);
					else if(p.left.val > son) {
						p.right = p.left;
						p.left = nodes.get(son);
					}
				}
			}

			int answer = 1;
			int ansSize = Integer.MAX_VALUE;
			for(int i=V;i>=1;i--) {
				size = 0;
				findA = false;
				findB = false;
				inorder(nodes.get(i), targetA, targetB);
				if(findA && findB) {
					if(size < ansSize) {
						answer = i;
						ansSize = size;
					}
				}
			}
			System.out.printf("#%d %d %d\n",tc, answer, ansSize);
		}
	}
	static int size = 0;
	static boolean findA = false;
	static boolean findB = false;
	static void inorder(Node node, int targetA, int targetB) {
		if(node.left!=null)
			inorder(node.left, targetA, targetB);

		size++;
		if(node.val == targetA) findA = true;
		if(node.val == targetB) findB = true;

		if(node.right!=null)
			inorder(node.right, targetA, targetB);
	}
}
