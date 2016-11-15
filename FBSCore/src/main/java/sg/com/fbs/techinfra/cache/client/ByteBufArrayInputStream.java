package sg.com.fbs.techinfra.cache.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;



/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Jul 18, 2016 2:57:06 PM $
 * 
 */
public final class ByteBufArrayInputStream extends InputStream implements LineInputStream {

	private Logger logger = Logger.getLogger(ByteBufArrayInputStream.class);
	private ByteBuffer[] bufs;
	private int currentBuf = 0;

	public ByteBufArrayInputStream(ByteBuffer[] vBufs) {
		if (vBufs == null) {
			this.bufs = new ByteBuffer[0];
		} else {
			this.bufs = Arrays.copyOf(vBufs, vBufs.length);
		}

		if (bufs.length == 0) {
			logger.error("buffer is empty");
		}

		for (ByteBuffer b : bufs) {
			b.flip();
		}
	}

	public ByteBufArrayInputStream(List<ByteBuffer> bufs) {
		this(bufs.toArray(new ByteBuffer[] {}));
	}

	public void clearEOL() throws IOException {
		byte[] b = new byte[1];
		boolean eol = false;
		while (read(b, 0, 1) != -1) {

			// only stop when we see
			// \r (13) followed by \n (10)
			if (b[0] == 13) {
				eol = true;
				continue;
			}

			if (eol) {
				if (b[0] == 10)
					break;
				eol = false;
			}
		}
	}

	public int read() {
		do {
			if (bufs[currentBuf].hasRemaining()) {
				return bufs[currentBuf].get();
			}
			currentBuf++;
		} while (currentBuf < bufs.length);

		currentBuf--;
		return -1;
	}

	public int read(byte[] buf) {
		int len = buf.length;
		int bufPos = 0;
		do {
			if (bufs[currentBuf].hasRemaining()) {
				int n = Math.min(bufs[currentBuf].remaining(), len - bufPos);
				bufs[currentBuf].get(buf, bufPos, n);
				bufPos += n;
			}
			currentBuf++;
		} while (currentBuf < bufs.length && bufPos < len);

		currentBuf--;

		if (bufPos > 0 || (bufPos == 0 && len == 0)) {
			return bufPos;
		}
		return -1;
	}

	public String readLine() throws IOException {
		byte[] b = new byte[1];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		boolean eol = false;

		while (read(b, 0, 1) != -1) {
			if (b[0] == 13) {
				eol = true;
			} else {
				if (eol) {
					if (b[0] == 10)
						break;
					eol = false;
				}
			}

			// cast byte into char array
			bos.write(b, 0, 1);
		}

		if (bos == null || bos.size() <= 0) {
			throw new IOException(
					"++++ Stream appears to be dead, so closing it down");
		}

		// else return the string
		return bos.toString().trim();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("ByteBufArrayIS: ");
		sb.append(bufs.length).append(" bufs of sizes: \n");

		for (int i = 0; i < bufs.length; i++) {
			sb.append("                                        ").append(i)
					.append(":  ").append(bufs[i]).append("\n");
		}
		return sb.toString();
	}

}
