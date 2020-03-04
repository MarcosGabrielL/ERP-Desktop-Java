package ImpressaoDANFE;

public class Console {
	private static String readLine() {
		int tCh;
		String tLinha = "";
		boolean tFim = false;

		while (!tFim) {
			try {
				tCh = System.in.read();
				if (tCh < 0 || (char) tCh == '\n')
					tFim = true;
				else if ((char) tCh != '\r')
					tLinha = tLinha + (char) tCh;
			} catch (java.io.IOException tExcept) {
				tLinha = null;
				tFim = true;
			}
		}
		return tLinha;
	}

	private static void printPrompt(String pPrompt) {
		System.out.print(pPrompt);
		System.out.flush();
	}

	private static byte ajustaByte(long pValor) {
		if (pValor < Byte.MIN_VALUE)
			return Byte.MIN_VALUE;

		if (pValor > Byte.MAX_VALUE)
			return Byte.MAX_VALUE;

		return (byte) pValor;
	}

	private static short ajustaShort(long pValor) {
		if (pValor < Short.MIN_VALUE)
			return Short.MIN_VALUE;

		if (pValor > Short.MAX_VALUE)
			return Short.MAX_VALUE;

		return (short) pValor;
	}

	private static int ajustaInt(long pValor) {
		if (pValor < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;

		if (pValor > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;

		return (int) pValor;
	}

	private static float ajustaFloat(double pValor) {
		if (pValor < Float.MIN_VALUE)
			return Float.MIN_VALUE;

		if (pValor > Float.MAX_VALUE)
			return Float.MAX_VALUE;

		return (float) pValor;
	}

	public static byte readByte(String pPrompt, long pValor) {

		while (true) {
			try {
				String tLinha;

				printPrompt(pPrompt);
				tLinha = readLine().trim();
				if (tLinha.equals(""))
					return ajustaByte(pValor);
				return Byte.valueOf(tLinha).byteValue();
			} catch (NumberFormatException tExcept) {
				System.out.println("Valor invalido para o tipo 'byte'");
				System.out.println("Digite novamente...");
			}
		}
	}

	public static byte readByte(String pPrompt) {
		return readByte(pPrompt, 0);
	}

	public static byte readByte(long pVlr) {
		byte tVlr = ajustaByte(pVlr);

		return readByte("Entre com um valor do tipo 'byte' (" + tVlr
				+ " default) : ", tVlr);
	}

	public static byte readByte() {
		return readByte("Entre com um valor do tipo 'byte' (0 default) : ", 0);
	}

	public static short readShort(String pPrompt, long pValor) {

		while (true) {
			try {
				String tLinha;

				printPrompt(pPrompt);
				tLinha = readLine().trim();
				if (tLinha.equals(""))
					return ajustaShort(pValor);
				return Short.valueOf(tLinha).shortValue();
			} catch (NumberFormatException tExcept) {
				System.out.println("Valor invalido para o tipo 'short'");
				System.out.println("Digite novamente...");
			}
		}
	}

	public static short readShort(String pPrompt) {
		return readShort(pPrompt, 0);
	}

	public static short readShort(long pVlr) {
		short tVlr = ajustaShort(pVlr);

		return readShort("Entre com um valor do tipo 'short' (" + tVlr
				+ " default) : ", tVlr);
	}

	public static short readShort() {
		return readShort("Entre com um valor do tipo 'short' (0 default) : ", 0);
	}

	public static int readInt(String pPrompt, long pValor) {

		while (true) {
			try {
				String tLinha;

				printPrompt(pPrompt);
				tLinha = readLine().trim();
				if (tLinha.equals(""))
					return ajustaInt(pValor);
				return Integer.valueOf(tLinha).intValue();
			} catch (NumberFormatException tExcept) {
				System.out.println("Valor invalido para o tipo 'int'");
				System.out.println("Digite novamente...");
			}
		}
	}

	public static int readInt(String pPrompt) {
		return readInt(pPrompt, 0);
	}

	public static int readInt(long pVlr) {
		int tVlr = ajustaInt(pVlr);

		return readInt("Entre com um valor do tipo 'int' (" + tVlr
				+ " default) : ", tVlr);
	}

	public static int readInt() {
		return readInt("Entre com um valor do tipo 'int' (0 default) : ", 0);
	}

	public static long readLong(String pPrompt, long pValor) {

		while (true) {
			try {
				String tLinha;

				printPrompt(pPrompt);
				tLinha = readLine().trim();
				if (tLinha.equals(""))
					return pValor;
				return Long.valueOf(tLinha).longValue();
			} catch (NumberFormatException e) {
				System.out.println("Valor invalido para o tipo 'long'");
				System.out.println("Digite novamente...");
			}
		}
	}

	public static long readLong(String pPrompt) {
		return readLong(pPrompt, 0);
	}

	public static long readLong(long pVlr) {
		return readLong("Entre com um valor do tipo 'long' (" + pVlr
				+ " default) : ", pVlr);
	}

	public static long readLong() {
		return readLong("Entre com um valor do tipo 'long' (0 default) : ", 0);
	}

	public static float readFloat(String pPrompt, double pValor) {

		while (true) {
			try {
				String tLinha;

				printPrompt(pPrompt);
				tLinha = readLine().trim();
				if (tLinha.equals(""))
					return ajustaFloat(pValor);
				return Float.valueOf(tLinha).floatValue();
			} catch (NumberFormatException e) {
				System.out.println("Valor invalido para o tipo 'float'");
				System.out.println("Digite novamente...");
			}
		}
	}

	public static float readFloat(String pPrompt) {
		return readFloat(pPrompt, 0);
	}

	public static float readFloat(double pVlr) {
		float tVlr = ajustaFloat(pVlr);

		return readFloat("Entre com um valor do tipo 'float' (" + tVlr
				+ " default) : ", tVlr);
	}

	public static float readFloat() {
		return readFloat("Entre com um valor do tipo 'float' (0 default) : ", 0);
	}

	public static double readDouble(String pPrompt, double pValor) {

		while (true) {
			try {
				String tLinha;

				printPrompt(pPrompt);
				tLinha = readLine().trim();
				if (tLinha.equals(""))
					return pValor;
				return Double.valueOf(tLinha).doubleValue();
			} catch (NumberFormatException e) {
				System.out.println("Valor invalido para o tipo 'double'");
				System.out.println("Digite novamente...");
			}
		}
	}

	public static double readDouble(String pPrompt) {
		return readDouble(pPrompt, 0);
	}

	public static double readDouble(double pVlr) {
		return readDouble("Entre com um valor do tipo 'double' (" + pVlr
				+ " default) : ", pVlr);
	}

	public static double readDouble() {
		return readDouble("Entre com um valor do tipo 'double' (0 default) : ",
				0);
	}

	public static boolean readBoolean(String pPrompt, boolean pValor) {

		while (true) {
			String tLinha;

			printPrompt(pPrompt);
			tLinha = readLine().trim();
			if (tLinha.equals(""))
				return pValor;
			if (tLinha.equalsIgnoreCase("true"))
				return true;
			if (tLinha.equalsIgnoreCase("false"))
				return false;
			System.out
					.println("Valor invalido para o tipo 'boolean' (true/false)");
			System.out.println("Digite novamente...");
		}
	}

	public static boolean readBoolean(String pPrompt) {
		return readBoolean(pPrompt, false);
	}

	public static boolean readBoolean(boolean pVlr) {
		return readBoolean(
				"Entre com um valor do tipo 'boolean' (true/false) (" + pVlr
						+ " default) : ", pVlr);
	}

	public static boolean readBoolean() {
		return readBoolean(
				"Entre com um valor do tipo 'boolean' (true/false) ('false' default) : ",
				false);
	}

	public static char readChar(String pPrompt, char pCh) {
		char tResult = pCh;
		int tCh;
		boolean tFim = false;
		boolean tLido = false;

		printPrompt(pPrompt);
		while (!tFim) {
			try {
				tCh = System.in.read();
				if (tCh < 0 || (char) tCh == '\n')
					tFim = true;
				else if (!tLido && (char) tCh != '\r') {
					tResult = (char) tCh;
					tLido = true;
				}
			} catch (java.io.IOException tExcept) {
				tFim = true;
			}
		}
		return tResult;
	}

	public static char readChar(String pPrompt) {
		return readChar(pPrompt, '\0');
	}

	public static char readChar(char pCh) {
		return readChar("Entre com um valor do tipo 'char' (" + pCh
				+ " default) : ", pCh);
	}

	public static char readChar() {
		return readChar("Entre com um valor do tipo 'char' ('\0' default) : ",
				'\0');
	}

	public static String readString(String pPrompt, String pStr) {

		String tLinha;

		printPrompt(pPrompt);
		tLinha = readLine();
		if (tLinha.equals(""))
			return pStr;
		return tLinha;
	}

	public static String readString(String pPrompt) {
		return readString(pPrompt, "");
	}

	public static String readString() {
		return readString(
				"Entre com um valor do tipo 'String' (\"\" default) : ", "");
	}
}