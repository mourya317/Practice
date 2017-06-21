package practice;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;

public class RemoveFriend {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        InputReader ir = new InputReader(System.in);
        Print p = new Print();
        int a[];

        int T = ir.nextInt();


        for (int j = 0; j < T; j++) {

            int N = ir.nextInt();
            int K = ir.nextInt();
            int deletedFriend = 0;
            a = new int[N];
            Stack fStack = new Stack(N);


            for (int i = 0; i < N; i++) {
                a[i] = ir.nextInt();
            }

            fStack.push(a[0]);
            for (int i = 1; i < N; i++) {
				/*if (fStack.isEmpty())
					fStack.push(a[i]);
				else {*/
					/*if (a[i] < fStack.top()) {
						fStack.push(a[i]);
					} else {*/
                while (!fStack.isEmpty() && a[i] > fStack.top()
                        && deletedFriend < K) {
                    fStack.pop();
                    deletedFriend += 1;
                }
                fStack.push(a[i]);
                //}
                //}

            }

            for (int i = 0; i < K - deletedFriend; i++) {
                fStack.pop();
            }

            fStack.printStackInReverse(p);

            p.println("");
        }

        p.close();

    }

    private static class Stack {

        private int top = -1;
        private int SIZE;
        private int[] a;

        public Stack(int length) {
            // TODO Auto-generated constructor stub
            a = new int[length];
            SIZE = length;
        }

        public void push(int data) {
            if (top == SIZE - 1) {
                System.out.println("Stack overflow");
            }

            top++;
            a[top] = data;
        }

        public int pop() {
            if (top == -1) {
                System.out.println("Stack Underflow");
            }
            int val = a[top];
            top--;
            return val;
        }

        public int top() {
            if (top == -1) {
                System.out.println("Stack Underflow");
            }

            return a[top];
        }

        public int topIndex()
        {
            return top;
        }

        public boolean isEmpty() {
            if (top == -1)
                return true;
            else
                return false;
        }

        public void printStackInReverse(Print p) throws IOException
        {
            for(int i=0;i<=top;i++)
                p.print(a[i]+" ");

        }

    }

    static class InputReader {

        private InputStream stream;
        private byte[] buf = new byte[8192];
        private int curChar;
        private int snumChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int snext() {
            if (snumChars == -1)
                throw new InputMismatchException();
            if (curChar >= snumChars) {
                curChar = 0;
                try {
                    snumChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (snumChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }

            int res = 0;

            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));

            return res * sgn;
        }

        public long nextLong() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }

            long res = 0;

            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));

            return res * sgn;
        }

        public String readString() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = snext();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }

    static class Print {
        private final BufferedWriter bw;

        public Print() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void print(Object object) throws IOException {
            bw.append("" + object);
        }

        public void println(Object object) throws IOException {
            print(object);
            bw.append("\n");
        }

        public void close() throws IOException {
            bw.close();
        }
    }

}