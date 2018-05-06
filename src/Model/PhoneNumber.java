package Model;

import java.io.Serializable;

// Found online at https://introcs.cs.princeton.edu/java/32class/PhoneNumber.java.html
public class PhoneNumber implements Serializable {
	
	private static final long serialVersionUID = -8900782944049651929L;
	private final int area;   // area code (3 digits)
    private final int exch;   // exchange  (3 digits)
    private final int ext;    // extension (4 digits)

    public PhoneNumber(int area, int exch, int ext) {
        this.area = area;
        this.exch = exch;
        this.ext  = ext;
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        PhoneNumber that = (PhoneNumber) y;
        return (this.area == that.area) && (this.exch == that.exch) && (this.ext == that.ext);
    }

    public String toString() {
        return String.format("(%03d) %03d-%04d", area, exch, ext);
    }

    public int hashCode() {
        return 10007 * (area + 1009 * exch) + ext;
    }
}
