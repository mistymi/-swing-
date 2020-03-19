package welcome;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;



import java.awt.BorderLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;

public class mainfest {

	public JFrame frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainfest window = new mainfest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainfest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private  void  initialize() {
		frame = new JFrame();
		frame.setBounds(300, 200, 500, 400);
		frame.setTitle("misty's store");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("文件");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("打开");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("导出文件");
		mnNewMenu.add(mntmNewMenuItem_6);
		
	
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("保存");
		mnNewMenu.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_1 = new JMenu("查询");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmS = new JMenuItem("商品信息查询");
		mnNewMenu_1.add(mntmS);
		mntmS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectgoods win =  new selectgoods ();
				win.frame.setVisible(true);
			}
		});
		
		JMenuItem menuItem_1 = new JMenuItem("供应商信息查询");
		mnNewMenu_1.add(menuItem_1);
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seletmanu man=new seletmanu();
				man.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("进货单据查询");
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_2 = new JMenu("财务管理");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("财务报表");
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("流水账");
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenu menu = new JMenu("日常业务");
		menuBar.add(menu);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("销售");
		menu.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Sells aa=new Sells();
					aa.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("入库");
		menu.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertgoods ins;
				try {
					ins = new insertgoods();
					ins.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		
		
		JMenu menu_1 = new JMenu("帮助");
		menuBar.add(menu_1);
		
	}
	
	
}
    