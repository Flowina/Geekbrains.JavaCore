package Lesson5;
class Main {
    public static void main(String[] args) {
        test1();
        test2();
    }

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void test1() {
        System.out.println("Последовательное выполнение");

        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        }
        System.out.println(System.currentTimeMillis() - a + "ms");
        testResults(arr);
    }

    public static void test2() {
        System.out.println("Параллельное выполнение");
        float[] arr = new float[SIZE];
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            //synchronized (arr) {
                System.arraycopy(a1, 0, arr, 0, HALF);
                System.out.println("1st thread complete");
            //}
        });
        Thread t2 = new Thread(() -> {
            for (int j = 0; j < HALF; j++) {
                int i = j + HALF;
                a2[j] = (float) (a2[j] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            //synchronized (arr) {
                System.arraycopy(a2, 0, arr, HALF, HALF);
                System.out.println("2nd thread complete");
            //}
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() - a + "ms");
        testResults(arr);
    }

    protected static  void testResults(float[] arr) {
        int[] indexes = {HALF / 2, HALF, HALF + 100, HALF + HALF / 2, SIZE - 1};
        for (int idx:
                indexes) {
            System.out.println("Test arr[" + idx + "] = " + arr[idx]);
        }
    }
}

