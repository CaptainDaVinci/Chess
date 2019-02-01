package util;

public class Pair {
	public int x, y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Pair p) {
		return x == p.x && y == p.y;
	}
	
	public boolean equals(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	public void copy(Pair o) {
		x = o.x;
		y = o.y;
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
