package aa;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Test4 extends JFrame {
	
   private JPanel contentPane;
   private JSplitPane splitPane;
   public Object b;
   private boolean drag = false;
   JTextField txtColor;
   JTextField textH;
   JTextField textW;
   JTextField textY;
   JTextField textX;
   JTextField textTEXT;
   Tree tree;
   
   //JPanel panel_2 = new JPanel();
   JPanel panel_2 = new JPanel() { 
      public void paint(Graphics g)
      {
         super.paint(g);
       
      }
   };
   JTextArea textArea = new JTextArea();
   JLabel lblNewLabel = new JLabel();
   
   //JLabel tempLabel;
   
   JLabel l[];
   //JLabel l2 = new JLabel();
   //JLabel l3 = new JLabel();
   
   public void drawCurve(Graphics g , Point from ,Point to)
   {
      Point e = to;
      if(from.y < to.y)
      {
         Point t = from;
         from = to;
         to = t;
      }
      double tan;
      int width = Math.abs(to.x - from.x);
      int height = Math.abs(to.y - from.y);
      if(to.x == from.x)
         tan = 99999999;
      else
         tan = ((double)to.y - from.y) / (to.x - from.x);
      
      g.drawString("tan : " + tan, e.x,e.y);
      if(tan <= -1)
         g.drawArc(from.x, from.y - height, 2 * width, 2 * height, 90 , 90);
      else if(tan <= 0)
         g.drawArc(-width + from.x, from.y - 2 * height,2 * width, 2 * height, 270 , 90);
      else if(tan <= 1)
         g.drawArc(-width + from.x, from.y - 2 * height ,2 * width, 2 * height, 180 , 90);
      else
         g.drawArc(-2 * width + from.x, from.y - height,2 * width, 2 * height, 0 , 90);
   }
   
   public Test4() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       setBounds(0,0,screenSize.width, screenSize.height-50);
      //setBounds(0, 0, 800, 700);
      contentPane = new JPanel();
      //contentPane.setPreferredSize(new Dimension(640,480));
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      //how to maximize contentpane
      
      
      //contentPane.addMouseListener(new MyMouseListener());
      
      JMenuBar menuBar = new JMenuBar();
      menuBar.setToolTipText("");
      menuBar.setBounds(5, 5, screenSize.width-25, 19);
      contentPane.add(menuBar);
      JMenu file = new JMenu("파일");
      menuBar.add(file);
      JMenuItem newfile = new JMenuItem("새로 만들기");
      file.add(newfile);
      JMenuItem open = new JMenuItem("열기");
      file.add(open);
      file.addSeparator();
      JMenuItem save = new JMenuItem("저장");
      file.add(save);
      JMenuItem saveas = new JMenuItem("다른 이름으로 저장");
      file.add(saveas);
      file.addSeparator();
      JMenuItem exit = new JMenuItem("닫기");
      file.add(exit);

      JToolBar toolBar = new JToolBar();
      toolBar.setBounds(5, 25, screenSize.width-25, 25);
      contentPane.add(toolBar);
      JButton newfile_tool = new JButton("새로 만들기");
      toolBar.add(newfile_tool);
      JButton save_tool = new JButton("저장");
      toolBar.add(save_tool);

      splitPane = new JSplitPane();
      splitPane.setDividerSize(3);
      splitPane.setBounds(5, 50, screenSize.width-25, screenSize.height-140);
      contentPane.add(splitPane);
      
      //splitPane_1 : mind map , attribute 
      JSplitPane splitPane_1 = new JSplitPane();
      splitPane_1.setDividerSize(3);
      splitPane.setRightComponent(splitPane_1);
      //마우스로 이동
      //splitPane_1.addMouseListener(new MyMouseListener());
      //splitPane_1.addMouseMotionListener(new MyMouseListener());
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(new Color(255, 248, 220));
      splitPane_1.setRightComponent(panel_1);
      panel_1.setLayout(null);

      JButton btnNewButton_1 = new JButton("\uBCC0\uACBD");
      btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 20));
      btnNewButton_1.setBackground(new Color(175, 238, 238));
      btnNewButton_1.setBounds(2, 521, 180, 52);
      panel_1.add(btnNewButton_1);
      btnNewButton_1.addActionListener(new MyActionListener());
      
      //속성판 정보값들
      txtColor = new JTextField();
      txtColor.setFont(new Font("굴림", Font.PLAIN, 8));
      txtColor.setColumns(10);
      txtColor.setBackground(Color.WHITE);
      txtColor.setBounds(73, 455, 104, 45);
      panel_1.add(txtColor);
      
      textH = new JTextField();
      textH.setFont(new Font("굴림", Font.PLAIN, 12));
      textH.setColumns(10);
      textH.setBackground(Color.WHITE);
      textH.setBounds(73, 367, 104, 45);
      panel_1.add(textH);
      
      textW = new JTextField();
      textW.setFont(new Font("굴림", Font.PLAIN, 12));
      textW.setColumns(10);
      textW.setBackground(Color.WHITE);
      textW.setBounds(73, 277, 104, 45);
      panel_1.add(textW);
      
      textY = new JTextField();
      textY.setFont(new Font("굴림", Font.PLAIN, 12));
      textY.setColumns(10);
      textY.setBackground(Color.WHITE);
      textY.setBounds(73, 188, 104, 45);
      panel_1.add(textY);
      
      textX = new JTextField();
      textX.setFont(new Font("굴림", Font.PLAIN, 12));
      textX.setColumns(10);
      textX.setBackground(Color.WHITE);
      textX.setBounds(73, 97, 104, 45);
      panel_1.add(textX);
      
      textTEXT = new JTextField();
      textTEXT.setEditable(false);
      textTEXT.setFont(new Font("굴림", Font.PLAIN, 12));
      textTEXT.setColumns(10);
      textTEXT.setBackground(Color.WHITE);
      textTEXT.setBounds(73, 10, 104, 45);
      panel_1.add(textTEXT);
      
      //속성판 정보들
      JLabel lblText = new JLabel("TEXT:");
      lblText.setFont(new Font("굴림", Font.PLAIN, 20));
      lblText.setBounds(2, 8, 70, 45);
      panel_1.add(lblText);
      
      JLabel lblX = new JLabel("X:");
      lblX.setFont(new Font("굴림", Font.PLAIN, 20));
      lblX.setBounds(2, 95, 70, 45);
      panel_1.add(lblX);
      
      JLabel lblY = new JLabel("Y:");
      lblY.setFont(new Font("굴림", Font.PLAIN, 20));
      lblY.setBounds(2, 186, 70, 45);
      panel_1.add(lblY);
      
      JLabel lblW = new JLabel("W:");
      lblW.setFont(new Font("굴림", Font.PLAIN, 20));
      lblW.setBounds(2, 275, 70, 45);
      panel_1.add(lblW);
      
      JLabel lblH = new JLabel("H:");
      lblH.setFont(new Font("굴림", Font.PLAIN, 20));
      lblH.setBounds(2, 365, 70, 45);
      panel_1.add(lblH);
      
      JLabel lblColor = new JLabel("Color:");
      lblColor.setFont(new Font("굴림", Font.PLAIN, 20));
      lblColor.setBounds(2, 455, 70, 45);
      panel_1.add(lblColor);
      
      

      //속성판
      panel_2.setBackground(new Color(255, 239, 213));
      splitPane_1.setLeftComponent(panel_2);
      splitPane_1.setDividerLocation(893);
      panel_2.setLayout(null);
      
      //노드
      lblNewLabel.setOpaque(true);
      lblNewLabel.setBackground(Color.ORANGE);
      lblNewLabel.setBounds(100, 170, 100, 50);
      lblNewLabel.addMouseListener(new MyMouseListener());
      //JLabel 배열 초기화
      /*for(int i=0;i<l.length;i++) {
         l[i] = new JLabel();
         l[i].setOpaque(true);
         l[i].setBackground(Color.ORANGE);
         l[i].setBounds(100+i*50, 170+i*50, 100, 50);
         l[i].addMouseListener(new MyMouseListener());
         l[i].addMouseMotionListener(new MyMouseListener());
      }*/
      //텍스트판
      JPanel panel = new JPanel();
      splitPane.setLeftComponent(panel);
      panel.setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(0, 0, 170, screenSize.height-200);
      panel.add(scrollPane);
      textArea.setBackground(new Color(255, 228, 225));

      textArea.setBounds(0, 0, 170, screenSize.height-200);
      scrollPane.setViewportView(textArea);
      
      //테스트판 적용버튼
      JButton btnNewButton = new JButton("\uC801\uC6A9");
      btnNewButton.setFont(new Font("굴림", Font.PLAIN, 20));
      btnNewButton.setBackground(new Color(175, 238, 238));
      btnNewButton.setBounds(0, 522, 170, 52);
      btnNewButton.addActionListener(new MyActionListener());
      panel.add(btnNewButton);
      splitPane.setDividerLocation(170);
   }
   
   //텍스트판에 textArea 넣기
   /*textArea.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
         
      }
   }
   */
   JLabel tempLabel;
   class MyActionListener implements ActionListener {
      boolean check = true;
      int index = 0;
      int[] locationArrayX = {0,1,0,-1};
      int[] locationArrayY = {1,0,-1,0};
      public void actionPerformed(ActionEvent e) {
           JButton b = (JButton) e.getSource();
         if (b.getText().equals("적용")) {
           
           String inputText = textArea.getText();
           Tree tree = new Tree();
           tree.makeTree(inputText);
           tree.relocation(tree.head, 100, 100, 0, 360);
           panel_2.repaint();
           
         }
         if (b.getText().equals("변경")) {
         String x = textX.getText();
         String y = textY.getText();
         String w = textW.getText();
         String h = textH.getText();
         String c = txtColor.getText();
         int X = Integer.parseInt(x);
         int Y = Integer.parseInt(y);
         int W = Integer.parseInt(w);
         int H = Integer.parseInt(h);
         if(check) {
            tempLabel.setBounds(X, Y, W, H);
            check = false;
         }
         }
        }
    }


   class MyMouseListener implements MouseListener,MouseMotionListener {
         boolean enterCheck = false;
         boolean initial = true;
         boolean apply = false;
         //JLabel tempLabel;
         int x,y;
         public void mouseClicked(MouseEvent e) {
         if(initial) {
            tempLabel = (JLabel)e.getComponent();
            apply = true;
            textTEXT.setText(tempLabel.getText());
            x = tempLabel.getX();
            y = tempLabel.getY();
            //int x = e.getX();
            //int y = e.getY();
            int w = tempLabel.getWidth();
            int h = tempLabel.getHeight();
            Color c = tempLabel.getBackground(); 
            textX.setText(String.valueOf(x));
            textY.setText(String.valueOf(y));
            textW.setText(String.valueOf(w));
            textH.setText(String.valueOf(h));
            txtColor.setText(String.valueOf(c));
            tempLabel.setBackground(Color.GREEN);
            //initial = false;
            
         }
         
      }

      @Override
      public void mouseEntered(MouseEvent e) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void mouseExited(MouseEvent arg0) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void mousePressed(MouseEvent e) {
         tempLabel = (JLabel)e.getComponent();
         if(e.getSource()==tempLabel) {
            drag = true;
         }
         
      }

      @Override
      public void mouseReleased(MouseEvent e) {
         drag = false;
         if(apply){
         x = tempLabel.getX();
         y = tempLabel.getY();
         textX.setText(String.valueOf(x));
         textY.setText(String.valueOf(y));
         }
      }
      
      public void mouseDragged(MouseEvent e) {
         if(drag == true)
         {
            JComponent jc = (JComponent)e.getSource();
              jc.setLocation(jc.getX()+e.getX(), jc.getY()+e.getY());
         }
         
      }
      
      public void mouseMoved(MouseEvent e) {
      
      }
   }

//define JLabelNode in tree structure

class Tree
{
   private TreeNode head;
   public void makeTree(String text)
   {
      Stack<TreeNode> stack = new Stack<TreeNode>();
      String[] line = text.split("\n");
      for(int i = 0; i < line.length; i++)
      {
         int level = 1;
         for(int j = 0; j < line[i].length(); j++)
         {
            if(line[i].charAt(j) == '\t')
               ++level;
            else
               break;
         }
         JLabel label = new JLabel();
         label.setText(line[i].substring(level - 1));
         label.setBorder(new EmptyBorder(0, 0, 0, 0));
         label.setBackground(Color.orange);
         label.setBounds(1000, 500, 30, 50);
         label.setOpaque(true);
         label.addMouseListener(new MyMouseListener());
         label.addMouseMotionListener(new MyMouseListener());
         panel_2.add(label);
         TreeNode node = new TreeNode(label);
         
         /*System.out.println("level : " + level);
         System.out.println("STACK : ");
         for(int j = 0; j < stack.size(); j++)
            System.out.println(stack.elementAt(j).label.getText());*/
         for(int j =  stack.size(); j >= level; j--)
            stack.pop();
         if(level == 1)   // 예외 ! 처음에 Head에 넣기 위해서
            put(null , node);
         else
         {
            System.out.println(stack.peek().label.getText());
            put(stack.peek(),node);
         }
         stack.push(node);
         
      }
   } 
   public void relocation(TreeNode node ,int x, int y,float startAngle, float endAngle)
   {
      
      node.label.setLocation(x, y);
      for(int i = 0; i < node.child.size(); i++)
      {
         float nsa = (endAngle - startAngle) / node.child.size() * i + startAngle;
         float nsb = nsa +  (endAngle - startAngle) / node.child.size();
         float ma = (nsa + nsb) / 2;
         int nx = (int)(x + Math.cos(Math.toRadians(ma)) * 80);
         int ny = (int)(y + Math.sin(Math.toRadians(ma)) * 80);
         relocation(node.child.get(i), nx, ny, nsa, nsb);
      }
   }
   public void put(TreeNode parent , TreeNode child)
   {
      if(parent == null)
         this.head = child;
      else
         parent.child.add(child);
   }
}
class TreeNode
{
   ArrayList<TreeNode> child;
   JLabel label;
   TreeNode(JLabel label)
   {
      child = new ArrayList<TreeNode>();
      this.label = label;
   }
}
   public static void main(String[] args) {
      new Test4();
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Test4 frame = new Test4();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   
}


{
	
}