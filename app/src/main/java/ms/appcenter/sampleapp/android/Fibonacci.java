package ms.appcenter.sampleapp.android;

public class Fibonacci {
    public int getNthElement(int num) {
        int a = 0;
        int b = 1;
        int tmp = 0;

        for(int i = 2; i <= num; i++) {
            tmp = a + b;
            a = b;
            b = tmp;
        }
        return b;
    }
}