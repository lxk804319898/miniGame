package game2048;

public class LierIntArr {

    public static int[] drop(int[] a) {
        int b = a.length;
        if (b <= 1) return a;
        int[] c = new int[b];
        int j = 0;
        for (int i = 0; i < b; i++) {
            if (c[j] == 0 && a[i] != 0)
                c[j] = a[i];
            else if (c[j] != 0 && a[i] == c[j]) {
                c[j] = 2 * a[i];
                j++;
            } else if (a[i] != 0 && c[j] != 0 && a[i] != c[j]) {
                j++;
                c[j] = a[i];
            }
        }
        return c;
    }

    public static void main(String[] args) {
        int[] a = {0, 2, 0, 2, 4, 0, 0, 4, 2, 0, 2, 5, 5, 0, 10};
        int[] b = drop(a);
        for (int i = 0; i < b.length; i++)
            System.out.print(b[i] + ",");
    }
}

