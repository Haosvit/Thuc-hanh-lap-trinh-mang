package hao.thuchanhltmang.buoi1bai2;

import java.util.Arrays;
import java.util.Stack;

public class PolishNotation {

	private boolean isError = false;
	private String varString[] = { "ans", "va", "vb", "vc", "vd", "ve", "vf" };
	private String constString[] = { "pi", "π", "e" }; // mang chuoi cac hang
	public double var[] = new double[varString.length];
	private double cons[] = { Math.PI, Math.PI, Math.E };
	private boolean isDegOrRad = true;
	private int radix = 10, sizeRound = 10;
	private ConvertNumber convertNumber = new ConvertNumber();

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public int getSizeRound() {
		return sizeRound;
	}

	public void setSizeRound(int sizeRound) {
		this.sizeRound = sizeRound;
	}

	public int getRadix() {
		return radix;
	}

	public void setRadix(int radix) {
		this.radix = radix;
	}

	public boolean isDegOrRad() {
		return isDegOrRad;
	}

	public void setDegOrRad(boolean isDegOrRad) {
		this.isDegOrRad = isDegOrRad;
	}

	protected boolean isIntegerNumber(double num) {
		long a = (long) num;
		if (a == num) {
			return true;
		}
		return false;
	}

	private String myRound(double num, int size) {
		if (isIntegerNumber(num)) {
			return Long.toString((long) num);
		} else {
			int n = size - Long.toString((long) num).length();
			num = Math.round(num * Math.pow(10, n)) / Math.pow(10, n);
			if (isIntegerNumber(num)) {
				return Long.toString((long) num);
			} else {
				return Double.toString(num);
			}
		}
	}

	// num!
	private long factorial(int num) {
		if (num >= 0) {
			long result = 1;
			for (int i = 1; i <= num; i++) {
				result *= i;
			}
			return result;
		}
		return -1;
	}

	// chinh hop chap b cua a=a!/(a-b)!=(a-b+1)(a-b+2)...a
	private long permutation(int a, int b) {
		if (a < b) {
			return -1;
		}
		if (a >= 0 && b >= 0) {
			long result = 1;
			int c = a - b;
			for (int i = c + 1; i <= a; i++) {
				result *= i;
			}
			return result;
		}
		return -1;
	}

	// to hop chap b cua a
	private long combination(int a, int b) {
		if (a < b) {
			return -1;
		}
		if (a >= 0 && b >= 0) {
			long result = 1;
			int c = a - b;
			if (c > b) {
				int temp = c;
				c = b;
				b = temp;
			}
			for (int i = b + 1; i <= a; i++) {
				result *= i;
			}
			result /= factorial(c);
			return result;
		}
		return -1;
	}

	private double convertToDeg(double num) {
		num = num * 180 / Math.PI;
		return num;
	}

	private double convertToRad(double num) {
		num = num * Math.PI / 180;
		return num;
	}

	// kiem tra chuoi s co la so khong (bien cung la so)
	protected boolean isNumber(String s) {
		if (radix != 10 && convertNumber.isRadixString(s, radix)) {
			return true;
		}
		if (isVarOrConst(s)) {
			return true;
		}
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private boolean isNumber(char c) {
		String numberChar = ".0123456789abcdef";
		int index = numberChar.indexOf(c);
		if (radix == 10 && index >= 0 && index <= 10) {
			return true;
		}
		if (radix == 16 && index >= 0) {
			return true;
		}
		if (radix == 8 && index >= 0 && index <= 8) {
			return true;
		}
		if (radix == 2 && index >= 0 && index <= 2) {
			return true;
		}
		return false;
	}

	// Chuoi sang so
	private double stringToNumber(String s) {
		int index = indexVar(s);
		if (index >= 0) {
			return var[index];
		}
		index = indexConst(s);
		if (radix != 10) {
			if (convertNumber.isRadixString(s, radix)) {
				return convertNumber.stringRadixToDouble(s, radix);
			} else {
				isError = true;
			}
		}
		if (index >= 0) {
			return cons[index];
		}
		if (s.charAt(s.length() - 1) == '.') {
			isError = true;
			return -1;
		}
		try {
			return Double.parseDouble(s);
		} catch (Exception e) {
			isError = true;
		}
		return -1;
	}

	public String numberToString(double num, int radix, int size) {
		if (radix != 10) {
			return convertNumber.doubleToStringRadix(num, radix, size);
		}
		return myRound(num, size);

	}

	private int indexVar(String s) {
		for (int i = 0; i < varString.length; i++) {
			if (s.equals(varString[i])) {
				return i;
			}
		}
		return -1;
	}

	private int indexConst(String s) {
		for (int i = 0; i < constString.length; i++) {
			if (s.equals(constString[i])) {
				return i;
			}
		}
		return -1;
	}

	private boolean isVarOrConst(String s) {
		if (indexConst(s) >= 0 || indexVar(s) >= 0) {
			return true;
		}
		return false;
	}

	// kiem tra xem co phai toan tu
	private boolean isOperator(String s) {
		String operator[] = { "+", "-", "*", "/", "ℂ", "ℙ", "ncr", "npr", "^",
				"~", "√", "sqrt", "�?�√", "n√", "!", "%", ")", "(", "²", "sin",
				"cos", "tan", "arcsin", "arccos", "arctan", "log", "→", "sto",
				"mod", "and", "or", "xor", "not", "∧", "∨", "⊻", "¬", "<<",
				">>", "≫", "≪" };
		Arrays.sort(operator);
		if (Arrays.binarySearch(operator, s) > -1) {
			return true;
		} else {
			return false;
		}
	}

	// thiet lap thu tu uu tien
	private int priority(String s) {
		int p = 1;
		if (s.equals("→") || s.equals("sto")) {
			return p;
		}
		p++;
		if (s.equals("+") || s.equals("-")) {
			return p;
		}
		p++;
		if (s.equals("*") || s.equals("/")) {
			return p;
		}
		p++;
		if (s.equals("and") || s.equals("∧") || s.equals("or") || s.equals("∨")
				|| s.equals("xor") || s.equals("⊻") || s.equals("mod")
				|| s.equals(">>") || s.equals("<<") || s.equals("≫")
				|| s.equals("≪")) {
			return p;
		}
		p++;
		if (s.equals("ℂ") || s.equals("ℙ") || s.equals("ncr")
				|| s.equals("npr")) {
			return p;
		}
		p++;
		if (s.equals("not") || s.equals("¬")) {
			return p;
		}
		p++;
		if (s.equals("~")) {
			return p;
		}
		p++;
		if (s.equals("sin") || s.equals("cos") || s.equals("tan")
				|| s.equals("arcsin") || s.equals("arccos")
				|| s.equals("arctan") || s.equals("log")) {
			return p;
		}
		p++;
		if (s.equals("√") || s.equals("n√") || s.equals("�?�√") || s.equals("!")
				|| s.equals("^") || s.equals("²") || s.equals("sqrt")) {
			return p;
		}
		p++;
		return 0;
	}

	// kiem tra xem co phai la phep toan 1 ngoi
	private boolean isOneMath(String c) {
		String operator[] = { "sin", "cos", "tan", "arcsin", "arccos",
				"arctan", "√", "sqrt", "(", "~", "not", "¬", "log" };
		Arrays.sort(operator);
		if (Arrays.binarySearch(operator, c) > -1) {
			return true;
		} else {
			return false;
		}
	}

	// kiem tra xem co phai phep toan dang sau
	private boolean isPostOperator(String s) {
		String postOperator[] = { "!", "²" };
		for (int i = 0; i < postOperator.length; i++) {
			if (s.equals(postOperator[i])) {
				return true;
			}
		}
		return false;
	}

	// kiem tra xem cac ky tu lien nhau co la 1 tu khong
	private boolean isWord(char c1, char c2) {
		char word[][] = { { '2', '√' }, { 'n', '√' }, { 'p', 'i' },
				{ 's', 'i', 'n' }, { 'c', 'o', 's' }, { 't', 'a', 'n' },
				{ 'a', 'r', 'c', 's', 'i', 'n' },
				{ 'a', 'r', 'c', 'c', 'o', 's' },
				{ 'a', 'r', 'c', 't', 'a', 'n' }, { 'a', 'n', 's' },
				{ 's', 'q', 'r', 't' }, { 'n', 'c', 'r' }, { 'n', 'p', 'r' },
				{ 's', 't', 'o' }, { 'a', 'n', 'd' }, { 'o', 'r' },
				{ 'x', 'o', 'r' }, { 'n', 'o', 't' }, { 'm', 'o', 'd' },
				{ '<', '<' }, { '>', '>' } };
		for (int i = 0; i < word.length; i++) {
			for (int j = 0; j < word[i].length; j++) {
				for (int k = j + 1; k < word[i].length; k++) {
					if (c1 == word[i][j] && c2 == word[i][k]) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// kiem tra chuoi s co la 1 tu khong
	private boolean isWord(String s) {
		String word[] = { "ⁿ√", "n√", "pi", "sin", "cos", "tan", "arcsin",
				"arccos", "arctan", "sqrt", "ncr", "npr", "sto", "and", "or",
				"xor", "not", "mod", "<<", ">>", "va", "vb", "vc", "vd", "ve",
				"ans" };
		for (int i = 0; i < word.length; i++) {
			if (s.equals(word[i])) {
				return true;
			}
		}
		return false;
	}

	// chuan hoa chuoi
	private String standardize(String s) {
		s = s.trim();
		s = s.replaceAll("\\s+", " ");
		return s;
	}

	// cat chuoi thanh cac phan tu
	private String[] trimString(String s) {
		String temp[] = s.split(" ");
		return temp;
	}
	
	private String standardizeMath(String[] s) { // chuan hoa bieu thuc
		String s1 = "";
		// standardize(s);

		int open = 0, close = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i].equals("(")) {
				open++;
			} else if (s[i].equals(")")) {
				close++;
			}
		}

		for (int i = 0; i < s.length; i++) {
			// chuyen ...)(... thanh ...)*(...
			if (i > 0 && isOneMath(s[i])
					&& (s[i - 1].equals(")") || isNumber(s[i - 1]))) {
				s1 = s1 + "* ";
			}
			// 3!2!
			if (i > 0 && isPostOperator(s[i - 1]) && isNumber(s[i])) {
				s1 = s1 + "* ";
			}
			// so duong
			if ((i == 0 || (i > 0 && !isNumber(s[i - 1])
					&& !s[i - 1].equals(")") && !isPostOperator(s[i - 1])))
					&& (s[i].equals("+"))
					&& (isNumber(s[i + 1]) || s[i + 1].equals("+"))) {
				continue;
			}
			// check so am
			if ((i == 0 || (i > 0 && !isNumber(s[i - 1])
					&& !s[i - 1].equals(")") && !isPostOperator(s[i - 1])))
					&& (s[i].equals("-"))
					&& (isNumber(s[i + 1]) || s[i + 1].equals("-"))) {
				s1 = s1 + "~ ";
			}// VD hoac 6π , ...)π chuyen sang 6*π , ...)*π
			else if (i > 0
					&& ((isNumber(s[i - 1]) || s[i - 1].equals(")")) && isVarOrConst(s[i]))) {
				s1 = s1 + "* " + s[i] + " ";
			} else {
				s1 = s1 + s[i] + " ";
			}
		}

		// them cac dau ")" vao cuoi neu thieu
		for (int i = 0; i < (open - close); i++) {
			s1 += ") ";
		}
		return s1;
	}
	// xu ly bieu thuc nhap vao thanh cac phan tu
	private String processInput(String sMath) {
		sMath = sMath.toLowerCase();
		sMath = standardize(sMath); // chuan hoa bieu thuc
		String s = "", temp = "";
		for (int i = 0; i < sMath.length(); i++) {
			// is'nt number
			if (!isNumber(sMath.charAt(i))
					|| (i < sMath.length() - 1 && isWord(sMath.charAt(i),
							sMath.charAt(i + 1)))) {
				s += " " + temp;
				temp = "" + sMath.charAt(i);
				// is operator and isn't word
				if (isOperator(sMath.charAt(i) + "") && i < sMath.length() - 1
						&& !isWord(sMath.charAt(i), sMath.charAt(i + 1))) {
					s += " " + temp;
					temp = "";
				} else { // isn't operator but is word
					i++;
					while (i < sMath.length()
							&& !isNumber(sMath.charAt(i))
							&& (!isOperator(sMath.charAt(i) + ""))
							|| (i < sMath.length() - 1 && isWord(
									sMath.charAt(i - 1), sMath.charAt(i)))) {
						temp += sMath.charAt(i);
						i++;
						if (isWord(temp)) {
							s += " " + temp;
							temp = "";
							break;
						}
					}
					i--;
					s += " " + temp;
					temp = "";
				}
			} else { // is number
				temp = temp + sMath.charAt(i);
			}
		}
		s += " " + temp;
		s = standardize(s);
		s = standardizeMath(trimString(s));
		return s;
	}

	private String postFix(String math) {
		String[] elementMath = trimString(math);

		String s1 = "";
		Stack<String> S = new Stack<String>();
		for (int i = 0; i < elementMath.length; i++) { // duyet cac phan tu
			if (!isOperator(elementMath[i])) // neu khong la toan tu
			{
				s1 = s1 + elementMath[i] + " "; // xuat elem vao s1
			} else { // c la toan tu
				if (elementMath[i].equals("(")) {
					S.push(elementMath[i]); // c la "(" -> day phan tu vao Stack
				} else {
					if (elementMath[i].equals(")")) { // c la ")"
						// duyet lai cac phan tu trong Stack
						String temp = "";
						do {
							temp = S.peek();
							if (!temp.equals("(")) {
								s1 = s1 + S.peek() + " "; // trong khi c1 != "("
							}
							S.pop();
						} while (!temp.equals("("));
					} else {
						// Stack khong rong va trong khi phan tu trong Stack co
						// do uu tien >= phan tu hien tai
						while (!S.isEmpty()
								&& priority(S.peek()) >= priority(elementMath[i])
								&& !isOneMath(elementMath[i])) {
							s1 = s1 + S.pop() + " ";
						}
						S.push(elementMath[i]); // dua phan tu hien tai vao
						// Stack
					}
				}
			}
		}
		while (!S.isEmpty()) {
			s1 = s1 + S.pop() + " "; // Neu Stack con phan tu thi day het vao s1
		}
		return s1;
	}
/**
 * calculate the math expression
 * @param math
 * Math expression
 * @return result
 * result in double
 */
	public Double calculate(String math) {
		math = processInput(math);
		math = postFix(math);
		String[] elementMath = trimString(math);
		Stack<Double> S = new Stack<Double>();
		double num = 0.0;
		double ans = 0.0;
		for (int i = 0; i < elementMath.length; i++) {
			if (!isOperator(elementMath[i])) {
				S.push(stringToNumber(elementMath[i]));
			} else { // toan tu
				if (S.isEmpty()) {
					System.out.println("Stack is empty ^^ ");
					isError = true;
					return 0.0;
				}
				double num1 = S.pop();
				String ei = elementMath[i];
				if (ei.equals("~")) {
					num = -num1;
				} else if (ei.equals("sin")) {
					if (isDegOrRad) {
						num1 = convertToRad(num1);
					}
					num = Math.sin(num1);
				} else if (ei.equals("cos")) {
					if (isDegOrRad) {
						num1 = convertToRad(num1);
					}
					num = Math.cos(num1);
				} else if (ei.equals("tan")) {
					if (isDegOrRad) {
						num1 = convertToRad(num1);
					}
					num = Math.tan(num1);
				} else if (ei.equals("arcsin")) {
					num = Math.asin(num1);
					if (isDegOrRad) {
						num = convertToDeg(num);
					}
				} else if (ei.equals("arccos")) {
					num = Math.acos(num1);
					if (isDegOrRad) {
						num = convertToDeg(num);
					}
				} else if (ei.equals("arctan")) {
					num = Math.atan(num1);
					if (isDegOrRad) {
						num = convertToDeg(num);
					}
				} else if (ei.equals("log")) {
					num = Math.log10(num1);
				} else if (ei.equals("%")) {
					num = num1 / 100;
				} else if (ei.equals("²")) {
					num = Math.pow(num1, 2);
				} else if (ei.equals("√") || ei.equals("sqrt")) {
					if (num1 >= 0) {
						num = Math.sqrt(num1);
					} else {
						isError = true;
						System.out.println("Error sqrt");
						return 0.0;
					}
				} else if (ei.equals("not") || ei.equals("¬") || ei.equals("!")) {
					if (isIntegerNumber(num1) && num1 >= 0) {
						if (ei.equals("not") || ei.equals("¬")) {
							num = ~(long) num1;
						} else if (ei.equals("!")) {
							num = factorial((int) num1);
						}
					}
				} else if (!S.empty()) {
					double num2 = S.peek();

					if (ei.equals("→") || ei.equals("sto")) {
						if (indexVar(elementMath[i - 1]) >= 0) {
							var[indexVar(elementMath[i - 1])] = num2;
							ans = num2;
							return ans;
						} else {
							isError = true;
							System.out.println("Error sto");
							return 0.0;
						}
					} else if (ei.equals("+")) {
						num = num2 + num1;
						S.pop();
					} else if (ei.equals("-")) {
						num = num2 - num1;
						S.pop();
					} else if (ei.equals("*")) {
						num = num2 * num1;
						S.pop();
					} else if (ei.equals("/")) {
						if (num1 != 0) {
							num = num2 / num1;
						} else {
							isError = true;
							return 0.0;
						}
						S.pop();
					} else if (ei.equals("^")) {
						num = Math.pow(num2, num1);
						S.pop();
					} else if (ei.equals("�?�√") || ei.equals("n√")) {
						num = Math.pow(num1, (double) 1 / num2);
						S.pop();
					} else if (isIntegerNumber(num1) && isIntegerNumber(num2)) {
						if (ei.equals("ncr") || ei.equals("ℂ")) {
							num = combination((int) num2, (int) num1);
							S.pop();
						} else if (ei.equals("npr") || ei.equals("ℙ")) {
							num = permutation((int) num2, (int) num1);
							S.pop();
						} else if (ei.equals("and") || ei.equals("∧")) {
							num = (long) num2 & (long) num1;
							S.pop();
						} else if (ei.equals("or") || ei.equals("∨")) {
							num = (long) num2 | (long) num1;
							S.pop();
						} else if (ei.equals("xor") || ei.equals("⊻")) {
							num = (long) num2 ^ (long) num1;
							S.pop();
						} else if (ei.equals("mod")) {
							num = (long) num2 % (long) num1;
							S.pop();
						} else if (ei.equals("<<") || ei.equals("≪")) {
							num = (long) num2 << (long) num1;
							S.pop();
						} else if (ei.equals(">>") || ei.equals("≫")) {
							num = (int) num2 >> (int) num1;
							S.pop();
						}
					}
				} else {
					System.out.println("Error stack empty");
					isError = true;
					return 0.0;
				}
				S.push(num);
			}
		}
		ans = S.pop();
		return ans;
	}

	public String primeMulti(double num) {
		return convertNumber.primeMulti(num);
	}

private class ConvertNumber {

    // chuyen chuoi he radix (he 2, 8, 10, 16) sang so double he 10
    protected double stringRadixToDouble(String str, int radix) {
        str = str.toUpperCase();
        int len = str.length();
        int indexDot = -1;
        double num = -1;
        String radixChar = "0123456789ABCDEF";

        // neu khong phai la chuoi trong he radix thi tra ve -1
        if (!isRadixString(str, radix)) {
            return num;
        }
        // tim vi tri dau cham de phan tach phan nguyen va phan thap phan
        indexDot = str.indexOf(".");
        if (indexDot > 0) {
            // chuoi phan nguyen
            String intString = str.substring(0, indexDot);
            // chuoi phan thap phan
            String floatString = str.substring(indexDot + 1, len);
            // chuyen phan nguyen vao num
            num = Integer.parseInt(intString, radix);
            // cong don phan thap phan
            for (int i = 0; i < floatString.length(); i++) {
                num += radixChar.indexOf(floatString.charAt(i))
                        / Math.pow(radix, i + 1);
            }
        } else {
            num = Integer.parseInt(str, radix);
        }
        return num;
    }

    // chuyen so he 10 sang he radix (2, 8, 16)
    protected String doubleToStringRadix(double num, int radix, int countRount) {
        String str = "";
        String radixChar = "0123456789ABCDEF";
        long intNum = (long) num;
        double floatNum = (num - intNum);
        // doi so nguyen inNumber sang chuoi he radixs
        String intString = Long.toString(intNum, radix).toUpperCase();
        String floatString = "";
        // doi phan thap phan
        while (floatNum > 0 && countRount > 0) {
            floatNum = (floatNum * radix);
            floatString += radixChar.charAt((int) floatNum);
            floatNum = floatNum - (int) floatNum;
            countRount--;
        }
        str = intString;
        if (floatString.length() > 0) {
            str += "." + floatString;
        }
        return str;
    }

    protected boolean isRadixString(String str, int radix) {
        str = str.toUpperCase();
        int len = str.length();
        int countDot = 0;
        String radixChar = "";
        if (radix == 2) {
            radixChar = "01.";
        } else if (radix == 8) {
            radixChar = "01234567.";
        } else if (radix == 16) {
            radixChar = "0123456789ABCDEF.";
        } else {
            return false;
        }
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c == '.') {
                countDot++;
            }
            if (radixChar.indexOf(c) < 0 || countDot > 1) {
                return false;
            }
        }
        return true;
    }

    // doi so numberDouble sang tich cua cac thua so nguyen to
    protected String primeMulti(double numDouble) {
        if (numDouble >= 0 && (long) numDouble == numDouble) {
            long num = (long) numDouble;
            // powCount la so mu tach duoc
            int powCount = 0, i = 2, m = (int) Math.sqrt(num) + 1;
            String s = "";
            // duyet tu 2 den can bac 2 cua num
            while (i < m) {
                powCount = 0; // so mu cua i
                // tach num thanh i^n
                while (num > 0 && num % i == 0) {
                    powCount++;
                    num /= i;
                }
                // cong them phan tu neu no chia het
                if (powCount > 0) {
                    if (s.length() > 0) {
                        s += "×"; // cong dau nhan
                    }
                    s += i; // cong so vao
                    if (powCount > 1) {
                        s += myPowAxB(powCount); // cong so mu
                    }
                }
                if (i == 2) {
                    i++;
                } else {
                    i += 2;
                }
            }
            // kiem tra num sau vong lap co la so nguyen to ko, 
            //neu la so nguyen to thi cho them vao
            if (num > 1) {
                if (s.length() > 0) {
                    s += "×";
                }
                s += num;
            } else if (s.length() == 0) {
                s += num;
            }
            return s;
        }
        return "-1";
    }

    private String myPowAxB(int number) {
        String numString = number + "";
        String numPow = "⁰¹²³⁴⁵⁶⁷⁸⁹";
        String result = "";
        for (int i = 0; i < numString.length(); i++) {
            result += numPow.charAt(Integer.parseInt(numString.charAt(i) + ""));
        }
        return result;
    }
}

}
