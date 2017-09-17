import com.googlecode.fannj.Fann;

import java.io.File;

public class Demo {
    public static void main(String[] args) {
        System.setProperty("jna.library.path", "C:/tools/FANN-2.2.0-Source/bin/");
        System.out.println(System.getProperty("jna.library.path")); //maybe the path is malformed
        File file = new File(System.getProperty("jna.library.path") + "fannfloat.dll");
        System.out.println("Is the dll file there:" + file.exists());
        System.load(file.getAbsolutePath());

        Fann fann = new Fann("ann");
        float[][] tests = {
                {1.0f, 0, 1},
                {0.9f, 1, 3},
                {0.3f, 0, 8},
                {1, 1, 8},
                {0.1f, 0, 0},
        };
        for (float[] test : tests) {
            System.out.println(getAction(fann.run(test)));
        }
    }

    private static String getAction(float[] out) {
        int i = 0;
        for (int j = 1; j < 4; j++) {
            if (out[i] < out[j]) {
                i = j;
            }
        }
        switch (i) {
            case 0:
                return "атаковать";
            case 1:
                return "прятаться";
            case 2:
                return "бежать";
            case 3:
                return "ничего не делать";
        }
        return "";
    }
}
