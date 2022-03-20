import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Layout extends JFrame implements ItemListener, ActionListener {

	private JLabel label1;// ø�Ϥu��
	private JLabel label2;// ����j�p
	private JLabel space;// �ť�
	private JLabel space2;// �٬O�ť�
	private JLabel statusLabel;// ���Ц�m

	private JComboBox<String> Drawtool;
	private String tools[] = { "����", "���u", "����", "�x��", "�ꨤ�x��" };

	private JRadioButton[] size;
	private ButtonGroup radioGroup;
	private JCheckBox fill;
	private JButton color;
	private JButton clear;
	private JButton undo;
	private JButton eraser;

	private DrawPanel drawPanel;
	private JColorChooser colorChooser = new JColorChooser();

	public Layout() {
		super("�p�e�a");
		JPanel toolPanel = new JPanel();
		statusLabel = new JLabel("���Ц�m: " + "(0,0)");
		drawPanel = new DrawPanel(statusLabel);// �D�n��Panel

		JPanel tool1 = new JPanel();
		JPanel tool2 = new JPanel();
		tool1.setLayout(new GridLayout(2, 0));
		tool2.setLayout(new GridLayout(2, 3));// �ƪ���GridLayout
		statusLabel.setOpaque(true);
		statusLabel.setBackground(Color.black);
		statusLabel.setForeground(Color.white);
		drawPanel.setBackground(Color.white);
		colorChooser.setColor(Color.black);//�w�]�¦�

		label1 = new JLabel("ø�Ϥu��");
		label2 = new JLabel("����j�p");
		space = new JLabel("");
		space2 = new JLabel("");

		Drawtool = new JComboBox<String>(tools);
		Drawtool.addItemListener(this);

		size = new JRadioButton[3];
		size[0] = new JRadioButton("�p", true);
		size[1] = new JRadioButton("��", false);
		size[2] = new JRadioButton("�j", false);
		radioGroup = new ButtonGroup();
		tool2.add(label2);
		tool2.add(space);
		tool2.add(space2);//�ƪ�

		for (int i = 0; i < size.length; i++) {
			tool2.add(size[i]);
			size[i].addItemListener(this);
			radioGroup.add(size[i]);
		} //��Radiobutton�[�itool2

		fill = new JCheckBox("��");
		fill.addItemListener(this);
		fill.setHorizontalTextPosition(JCheckBox.CENTER);
		fill.setVerticalTextPosition(JCheckBox.TOP);//��checkbox�x���k�令�W�U

		color = new JButton("�����C��");
		clear = new JButton("�M���e��");
		undo = new JButton("�W�@�B");
		eraser = new JButton("�����");
		color.addActionListener(this);
		clear.addActionListener(this);
		undo.addActionListener(this);
		eraser.addActionListener(this);
		
		//�\Panel��m
		add(toolPanel, BorderLayout.NORTH);
		add(statusLabel, BorderLayout.SOUTH);
		add(drawPanel);

		tool1.add(label1);
		tool1.add(Drawtool);
		toolPanel.add(tool1);
		toolPanel.add(tool2);
		toolPanel.add(fill);
		toolPanel.add(color);
		toolPanel.add(clear);//��Ҧ��F�F�[�itoolPanel
		toolPanel.add(eraser);
		toolPanel.add(undo);

		drawPanel.setStrokeWidth(1);
		fill.setEnabled(false);//�w�]������
	}

	// �bCONSOLE���C�L��r�AgetSource�ӨM�w��ܪ��u��
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == Drawtool && e.getStateChange() == ItemEvent.SELECTED) {
			System.out.println("��� " + Drawtool.getSelectedItem());// �n����]�⦸�����D
			if(Drawtool.getSelectedItem().toString() == "����") {
				fill.setEnabled(false);//�Y�ﶵ������A�h����ϥζ�
			}
			else {
				fill.setEnabled(true);
			}
			drawPanel.setShapeType(Drawtool.getSelectedItem().toString());
		} else if (e.getSource() == fill) {
			System.out.println((fill.isSelected() ? "���" : "����") + " ��");
			drawPanel.setFilledShape(fill.isSelected());
		}
		for (int i = 0; i < size.length; i++) {
			if (e.getSource() == size[i] && size[i].isSelected()) {
				System.out.println("��� " + size[i].getText() + " ����");
				drawPanel.setStrokeWidth(i + 1);//�]�w����j�p
				
			}
		}
	}

	// �P�W�A�u�O���PListener
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == color) {
			System.out.println("�I�� �����C��");
			JDialog dialog = JColorChooser.createDialog(this, "�п���C��", false, colorChooser,
					(e1) -> drawPanel.setDrawingColor(colorChooser.getColor()), null);

			dialog.setVisible(true);
		} else if (e.getSource() == clear) {
			System.out.println("�I�� �M���e��");
			drawPanel.clearDrawing();
		} else if (e.getSource() == undo) {
			System.out.println("�I�� �W�@�B");
			drawPanel.clearLastShape();
		} else if (e.getSource() == eraser) {
			System.out.println("�I�� �����");
			if(eraser.getText() == "�����") {
				drawPanel.setDrawingColor(drawPanel.getBackground());
				eraser.setText("�_��");
			} 
			else {
				drawPanel.setDrawingColor(colorChooser.getColor());
				eraser.setText("�����");
			}
		}

	}

	
	public void showWelcome() {
		Icon logo =new ImageIcon(Main.class.getResource("logo.jpg"));
        JOptionPane.showMessageDialog(this,"Welcome!","�T��",JOptionPane.INFORMATION_MESSAGE,logo);
		
	}
}
