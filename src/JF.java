import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JF extends Frame{
    //画像を変数に入れる
    CvTest filepath =new CvTest();
    Image pi=getToolkit().getImage(filepath.filePathResult);

    public void Main()
    {
        //フレームの作成
        JF frame = new JF();
        frame.setSize(500, 500);//初期設定は縦横500*500
        frame.setVisible(true);
        //リスナーの設定
        frame.addWindowListener(new Ada());
    }

    public void paint(Graphics g)
    {
        int sw=getWidth();//画面の幅を取得
        int sh=getHeight();//画面の高さを取得
        int iw=pi.getWidth(this);//イメージの幅を取得
        int ih=pi.getHeight(this);//イメージの高さを取得
        int x=(sw-iw)/2;//イメージが横中央になる位置を計算
        int y=(sh-ih)/2;//イメージが縦中央になる位置を計算
        g.drawImage(pi, x,y,this);//画像表示
    }

    class Ada extends WindowAdapter
    {
        public void windowClosing(WindowEvent e){
            //閉じるボタンが押されたときの処理
            System.exit(0);
        }
    }
}
