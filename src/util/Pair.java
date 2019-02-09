package util;

import java.util.Objects;

public class Pair {
	public int x, y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x  + ", " + y + ")"; 
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		
		if (!(o instanceof Pair)) {
			return false;
		}
		
		Pair p = (Pair) o; 
		return p.x == this.x && p.y == y;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
		
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
