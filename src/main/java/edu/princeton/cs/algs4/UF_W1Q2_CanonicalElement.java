package edu.princeton.cs.algs4;

public class UF_W1Q2_CanonicalElement {
    private int[] id;
    private int[] sz;
    private int[] gt;

    public UF_W1Q2_CanonicalElement(int n) {
        id = new int[n];
        sz = new int[n];
        gt = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
            gt[i] = i;
        }
    }

    public int root(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ) return;

        if (sz[rootP] < sz[rootQ]) {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
            if (gt[rootQ] < gt[rootP]) {
                gt[rootQ] = gt[rootP];
            }
        } else {
            id[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
            if (gt[rootP] < gt[rootQ]) {
                gt[rootP] = gt[rootQ];
            }
        }
    }

    public int find(int p) {
        return gt[root(p)];
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        UF_W1Q2_CanonicalElement uf = new UF_W1Q2_CanonicalElement(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q + " : " + uf.find(p));
        }
    }
}
