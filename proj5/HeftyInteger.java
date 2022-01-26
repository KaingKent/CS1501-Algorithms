import java.util.Random;

public class HeftyInteger {

	private final byte[] ONE = {(byte) 1};
	private final byte[] ZERO = {(byte) 0};

	private byte[] val;

	/**
	 * Construct the HeftyInteger from a given byte array
	 * @param b the byte array that this HeftyInteger should represent
	 */
	public HeftyInteger(byte[] b) {
		val = b;
	}

	/**
	 * Return this HeftyInteger's val
	 * @return val
	 */
	public byte[] getVal() {
		return val;
	}

	public void setVal(byte[] newVal){
		val = newVal;
	}

	/**
	 * Return the number of bytes in val
	 * @return length of the val byte array
	 */
	public int length() {
		return val.length;
	}

	/**
	 * Add a new byte as the most significant in this
	 * @param extension the byte to place as most significant
	 */
	public void extend(byte extension) {
		byte[] newv = new byte[val.length + 1];
		newv[0] = extension;
		for (int i = 0; i < val.length; i++) {
			newv[i + 1] = val[i];
		}
		val = newv;
	}

	/**
	 * If this is negative, most significant bit will be 1 meaning most
	 * significant byte will be a negative signed number
	 * @return true if this is negative, false if positive
	 */
	public boolean isNegative() {
		return (val[0] < 0);
	}

	/**
	 * Computes the sum of this and other
	 * @param other the other HeftyInteger to sum with this
	 */
	public HeftyInteger add(HeftyInteger other) {
		byte[] a, b;
		// If operands are of different sizes, put larger first ...
		if (val.length < other.length()) {
			a = other.getVal();
			b = val;
		}
		else {
			a = val;
			b = other.getVal();
		}

		// ... and normalize size for convenience
		if (b.length < a.length) {
			int diff = a.length - b.length;

			byte pad = (byte) 0;
			if (b[0] < 0) {
				pad = (byte) 0xFF;
			}

			byte[] newb = new byte[a.length];
			for (int i = 0; i < diff; i++) {
				newb[i] = pad;
			}

			for (int i = 0; i < b.length; i++) {
				newb[i + diff] = b[i];
			}

			b = newb;
		}

		// Actually compute the add
		int carry = 0;
		byte[] res = new byte[a.length];
		for (int i = a.length - 1; i >= 0; i--) {
			// Be sure to bitmask so that cast of negative bytes does not
			//  introduce spurious 1 bits into result of cast
			carry = ((int) a[i] & 0xFF) + ((int) b[i] & 0xFF) + carry;

			// Assign to next byte
			res[i] = (byte) (carry & 0xFF);

			// Carry remainder over to next byte (always want to shift in 0s)
			carry = carry >>> 8;
		}

		HeftyInteger res_li = new HeftyInteger(res);

		// If both operands are positive, magnitude could increase as a result
		//  of addition
		if (!this.isNegative() && !other.isNegative()) {
			// If we have either a leftover carry value or we used the last
			//  bit in the most significant byte, we need to extend the result
			if (res_li.isNegative()) {
				res_li.extend((byte) carry);
			}
		}
		// Magnitude could also increase if both operands are negative
		else if (this.isNegative() && other.isNegative()) {
			if (!res_li.isNegative()) {
				res_li.extend((byte) 0xFF);
			}
		}

		// Note that result will always be the same size as biggest input
		//  (e.g., -127 + 128 will use 2 bytes to store the result value 1)
		return res_li;
	}

	/**
	 * Negate val using two's complement representation
	 * @return negation of this
	 */
	public HeftyInteger negate() {
		byte[] neg = new byte[val.length];
		int offset = 0;

		// Check to ensure we can represent negation in same length
		//  (e.g., -128 can be represented in 8 bits using two's
		//  complement, +128 requires 9)
		if (val[0] == (byte) 0x80) { // 0x80 is 10000000
			boolean needs_ex = true;
			for (int i = 1; i < val.length; i++) {
				if (val[i] != (byte) 0) {
					needs_ex = false;
					break;
				}
			}
			// if first byte is 0x80 and all others are 0, must extend
			if (needs_ex) {
				neg = new byte[val.length + 1];
				neg[0] = (byte) 0;
				offset = 1;
			}
		}

		// flip all bits
		for (int i  = 0; i < val.length; i++) {
			neg[i + offset] = (byte) ~val[i];
		}

		HeftyInteger neg_li = new HeftyInteger(neg);

		// add 1 to complete two's complement negation
		return neg_li.add(new HeftyInteger(ONE));
	}

	/**
	 * Implement subtraction as simply negation and addition
	 * @param other HeftyInteger to subtract from this
	 * @return difference of this and other
	 */
	public HeftyInteger subtract(HeftyInteger other) {
		return this.add(other.negate());
	}

	/**
	 * Compute the product of this and other
	 * @param other HeftyInteger to multiply by this
	 * @return product of this and other
	 */
	public HeftyInteger multiply(HeftyInteger other) {
		// YOUR CODE HERE (replace the return, too...)
		HeftyInteger a, b;
		boolean neg = false;
		

		if(this.isZero() || other.isZero()){
			return new HeftyInteger(ZERO);
		}

		// If operands are of different sizes, put larger first ...
		if (val.length < other.length()) {
			a = other;
			b = this;
		}
		else {
			a = this;
			b = other;
		}
		
		if(a.isNegative() && b.isNegative()){
			a = a.negate();
			b = b.negate();
		}else if(a.isNegative() && !b.isNegative()){
			a = a.negate();
			neg = true;
		}else if(!a.isNegative() && b.isNegative()){
			b = b.negate();
			neg = true;
		}
		
		
		if (b.getVal().length < a.getVal().length) {
			int diff = a.getVal().length - b.getVal().length;

			byte pad = (byte) 0;
			if (b.getVal()[0] < 0) {
				pad = (byte) 0xFF;
			}

			byte[] newb = new byte[a.getVal().length];
			for (int i = 0; i < diff; i++) {
				newb[i] = pad;
			}

			for (int i = 0; i < b.getVal().length; i++) {
				newb[i + diff] = b.getVal()[i];
			}

			b.setVal(newb);
		}

		int shift = 0;
		HeftyInteger res = new HeftyInteger(ZERO);
		byte[] vals = a.getVal();

		for(int i = vals.length-1; i >= 0; i--){
			byte curr = vals[i];
			for(int j = 0; j < 8; j++){
				int bit = (curr >> j) & 1;
				if(bit == 1){
					HeftyInteger add = new HeftyInteger( shiftLeft(b.getVal(), shift));
					res = res.add(add);
				}
				shift++;
			}
		}
		
		if(neg){
			res = res.negate();
		}
		return res;
	}

	public byte[] toByte(int data){
		return new byte[] {
			(byte)((data >> 24) & 0xff),
			(byte)((data >> 16) & 0xff),
			(byte)((data >> 8) & 0xff),
			(byte)((data >> 0) & 0xff),
		};
	}
	
	public byte[] shiftLeft(byte[] data, int len) {
		byte[] x = new byte[data.length];

		int word_size = (len / 8) + 1;
		int shift = len % 8;
		byte carry_mask = (byte) ((1 << shift) - 1);
		int offset = word_size - 1;
		for (int i = 0; i < data.length; i++) {
			int src_index = i+offset;
			if (src_index >= data.length) {
				x[i] = 0;
			} else {
				byte src = data[src_index];
				byte dst = (byte) (src << shift);
				if (src_index+1 < data.length) {
					dst |= data[src_index+1] >> (8-shift) & carry_mask;
				}
				x[i] = dst;
			}
		}

		return x;
	}

	/**
	 * Run the extended Euclidean algorithm on this and other
	 * @param other another HeftyInteger
	 * @return an array structured as follows:
	 *   0:  the GCD of this and other
	 *   1:  a valid x value
	 *   2:  a valid y value
	 * such that this * x + other * y == GCD in index 0
	 */
	 public HeftyInteger[] XGCD(HeftyInteger other) {
		// YOUR CODE HERE (replace the return, too...)
		return null;
	 }

	 public boolean isZero() {
		for(byte b : val){
			if(b != 0) return false;
		} 
		return true;
	}


}
