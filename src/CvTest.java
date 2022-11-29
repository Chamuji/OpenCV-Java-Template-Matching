import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//正常に起動した
public class CvTest {
    public String filePath = "resource";
    public String filePathResult = "resource/result/result.png";

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat im = null;
        Mat tmp = null;


        CvTest filepath = new CvTest();

        //検索対象の画像パスを指定
        im = Imgcodecs.imread(filepath.filePath + "/target/mario.png");
        //テンプレート画像のパス指定
        tmp = Imgcodecs.imread(filepath.filePath + "/template/edit_coin.png");
        Mat result = new Mat();

        //テンプレートマッチング
        Imgproc.matchTemplate(im, tmp, result, Imgproc.TM_CCOEFF_NORMED);
        // 検出結果から相関係数が閾値以下の部分を削除
        Imgproc.threshold(result, result, 0.89, 1.0, Imgproc.THRESH_TOZERO);

        int count = 0;
        ArrayList<Double> targetList = new ArrayList<>();

        for (int i = 0; i < result.rows(); i++) {
            for (int j = 0; j < result.cols(); j++) {
                if (result.get(i, j)[0] > 0) {
                    Imgproc.rectangle(im, new Point(j, i), new Point(j + tmp.cols(), i + tmp.rows()), new Scalar(0, 0, 255));
                    System.out.println(result.get(i, j)[0]);
                    count++;
                    targetList.add(result.get(i, j)[0]);
                }
            }
        }
       //重複した枠線削除する必要あり。



        // 画像の出力
        Imgcodecs.imwrite(filepath.filePathResult, im);
        System.out.println("検索結果は" + count + "個です");
        System.out.println("Finish");

        //画像出力クラス
        JF printFrame = new JF();
        printFrame.Main();

    }
}

