//�m�W:�\ã��
//�Ǹ�:108403036
//�t��:���2B
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Main extends JFrame{
	public static void main(String[] args) {
		Layout painter = new Layout();
		painter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		painter.setSize(700, 500);
		painter.setLocationRelativeTo(null);
		painter.setVisible(true);
		painter.showWelcome();
	}
}
