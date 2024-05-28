package com.itwill.sangbusamjo.shop.ui; 

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.sangbusamjo.shop.service.MemberService;
import com.itwill.sangbusamjo.shop.vo.Member;
import com.itwill.sangbusamjo.shop.vo.Order;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import java.awt.Insets;
import javax.swing.JSeparator;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShopMainFrame extends JFrame {

	/*****************1.로그인한 Member객체,주문중인 Order객체 멤버필드선언*******************/
	Member loginMember=null;
	Order tempOrder=null;
	/*****************2.패널상수선언*************************************************/
	public static int TOP_SHOP_MAIN_PANEL=0;
	public static int TOP_SHOP_MEMBER_PANEL=1; 
	public static int TOP_SHOP_PRODUCT_PANEL=2; 
	public static int TOP_SHOP_ORDER_PANEL=3;
	
	public static int SUB_MEMBER_LOGIN_PANEL=0; 
	public static int SUB_MEMBER_JOIN_PANEL=1; 
	public static int SUB_MEMBER_INFO_PANEL=2; 
	public static int SUB_MEMBER_FINDID_PANEL=3; 
	public static int SUB_MEMBER_FINDPW_PANEL=4;
	
	public static int SUB_PRODUCT_LIST_PANEL=0; 
	public static int SUB_PRODUCT_DETAIL_PANEL=1;
	public static int SUB_PRODUCT_TOP_PANEL=2; 
	public static int SUB_PRODUCT_BOTTOM_PANEL=3; 
	public static int SUB_PRODUCT_OUTER_PANEL=4; 
	public static int SUB_PRODUCT_SHOES_PANEL=5;
	
	public static int SUB_ORDER_CART_PANEL=0;
	public static int SUB_ORDER_ORDER_PANEL=1;
	public static int SUB_ORDER_ORDER_LIST_PANEL=2;
	public static int SUB_ORDER_ORDER_DETAIL_PANEL=3;
	
	/*****************3.사용자정의패널멤버필드선언*************************************************/
	
	private JPanel contentPane;
	private JPanel topMemberPanel;
	private JPanel topOrderPanel;
	private JTabbedPane mainTabbedPane;
	private MemberPanel memberPanel;
	private ProductPanel productPanel;
	private OrderPanel orderPanel;
	private JMenu emptyBar;
	private JMenu jMenu;
	public JMenuItem loginMenuItem;
	public JMenuItem logoutMenuItem_1;
	private JMenuItem exitMenuItem;
	private JSeparator separator;
	private JPanel topProductPanel;
	private JLabel lblNewLabel;
	private JLabel mainImageLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopMainFrame frame = new ShopMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShopMainFrame() {
		setTitle("SangbuSamjo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));


		mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mainTabbedPane.setBackground(Color.WHITE);
		mainTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int selectedIndex=mainTabbedPane.getSelectedIndex();
				if(selectedIndex==TOP_SHOP_ORDER_PANEL) {
					changePanel(TOP_SHOP_ORDER_PANEL, SUB_ORDER_CART_PANEL, null);
					/*
					 * orderPanel.orderTabbedPane.setSelectedIndex(SUB_ORDER_CART_PANEL); try {
					 * orderPanel.displayCartList(); } catch (Exception e1) { // TODO Auto-generated
					 * catch block e1.printStackTrace(); }
					 */
			     }
			}
		});
		contentPane.add(mainTabbedPane, BorderLayout.CENTER);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainTabbedPane.addTab("메인페이지", null, mainPanel, null);
		mainPanel.setLayout(null);
		
		lblNewLabel = new JLabel("SangbuSamjo");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 40));
		lblNewLabel.setBounds(0, -22, 276, 98);
		mainPanel.add(lblNewLabel);
		
		mainImageLabel = new JLabel("");
		mainImageLabel.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/메인페이지1.png")));
		mainImageLabel.setBounds(0, 46, 369, 240);
		mainPanel.add(mainImageLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/메인페이지2.png")));
		lblNewLabel_1.setBounds(0, 283, 369, 216);
		mainPanel.add(lblNewLabel_1);

		topMemberPanel = new JPanel();
		topMemberPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(TOP_SHOP_MEMBER_PANEL, SUB_MEMBER_INFO_PANEL, null);
			}
		});
		mainTabbedPane.addTab("회원", null, topMemberPanel, null);
		topMemberPanel.setLayout(new BorderLayout(0, 0));

		memberPanel = new MemberPanel();
		topMemberPanel.add(memberPanel, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 0, 0, 100));
		menuBar.add(Box.createHorizontalGlue());
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		emptyBar = new JMenu("");
		emptyBar.setEnabled(false);
		emptyBar.setHorizontalAlignment(SwingConstants.TRAILING);
		emptyBar.setMargin(new Insets(0, 0, 0, 333));
		menuBar.add(emptyBar);
		
		jMenu = new JMenu("메뉴");
		jMenu.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
			}
		});
		menuBar.add(jMenu);
		
		loginMenuItem = new JMenuItem("로그인");
		loginMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(TOP_SHOP_MEMBER_PANEL, SUB_MEMBER_LOGIN_PANEL, null);
			}
		});
	
		jMenu.add(loginMenuItem);
		
		logoutMenuItem_1 = new JMenuItem("로그아웃");
		logoutMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberPanel.logoutProcess();
				changePanel(TOP_SHOP_MEMBER_PANEL, SUB_MEMBER_LOGIN_PANEL, null);
				
			}
		});
		logoutMenuItem_1.setEnabled(false);
		jMenu.add(logoutMenuItem_1);
		
		separator = new JSeparator();
		jMenu.add(separator);
		
		exitMenuItem = new JMenuItem("종료");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		jMenu.add(exitMenuItem);

		topProductPanel = new JPanel();
		mainTabbedPane.addTab("상품", null, topProductPanel, null);
		topProductPanel.setLayout(new BorderLayout(0, 0));
		
		productPanel = new ProductPanel();
		topProductPanel.add(productPanel, BorderLayout.CENTER);
		
		topOrderPanel = new JPanel();
		topOrderPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					orderPanel.displayCartList();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mainTabbedPane.addTab("주문", null, topOrderPanel, null);
		topOrderPanel.setLayout(new BorderLayout(0, 0));
		
		orderPanel = new OrderPanel();
		topOrderPanel.add(orderPanel, BorderLayout.CENTER);
		
		/*
		 * ShopMainFrame 참조를 Panel에 넘겨줌
		 */
		memberPanel.setShopMainFrame(this);
		productPanel.setShopMainFrame(this);
		orderPanel.setShopMainFrame(this);
		changePanel(TOP_SHOP_MEMBER_PANEL, SUB_MEMBER_LOGIN_PANEL, null);
	} //생성자 끝
	
	/*************************화면전환메쏘드*******************************/
	
	public void changePanel(int top, int sub, Object data) {
		if(top==TOP_SHOP_MAIN_PANEL) {
			mainTabbedPane.setSelectedIndex(TOP_SHOP_MAIN_PANEL);
			
		}else if(top==TOP_SHOP_MEMBER_PANEL) {
			mainTabbedPane.setSelectedIndex(TOP_SHOP_MEMBER_PANEL);
			if(sub==SUB_MEMBER_LOGIN_PANEL) {
				memberPanel.memberTabbedPane.setSelectedIndex(SUB_MEMBER_LOGIN_PANEL);
			} else if(sub==SUB_MEMBER_JOIN_PANEL){
				memberPanel.memberTabbedPane.setSelectedIndex(SUB_MEMBER_JOIN_PANEL);				
			} else if(sub==SUB_MEMBER_INFO_PANEL) {
				memberPanel.memberTabbedPane.setSelectedIndex(SUB_MEMBER_INFO_PANEL);								
			} else if(sub==SUB_MEMBER_FINDID_PANEL) {
				memberPanel.memberTabbedPane.setSelectedIndex(SUB_MEMBER_FINDID_PANEL);												
			} else if(sub==SUB_MEMBER_FINDPW_PANEL) {
				memberPanel.memberTabbedPane.setSelectedIndex(SUB_MEMBER_FINDPW_PANEL);												
			}
		}else if(top==TOP_SHOP_PRODUCT_PANEL) {
			mainTabbedPane.setSelectedIndex(TOP_SHOP_PRODUCT_PANEL);
			if(sub==SUB_PRODUCT_LIST_PANEL) {
				productPanel.productTabbedPane.setSelectedIndex(SUB_PRODUCT_LIST_PANEL);
			} else if(sub==SUB_PRODUCT_DETAIL_PANEL){
				productPanel.productTabbedPane.setSelectedIndex(SUB_PRODUCT_DETAIL_PANEL);				
			} else if(sub==SUB_PRODUCT_TOP_PANEL) {
				productPanel.productTabbedPane.setSelectedIndex(SUB_PRODUCT_TOP_PANEL);								
			} else if(sub==SUB_PRODUCT_BOTTOM_PANEL) {
				productPanel.productTabbedPane.setSelectedIndex(SUB_PRODUCT_BOTTOM_PANEL);											
			} else if(sub==SUB_PRODUCT_OUTER_PANEL) {
				productPanel.productTabbedPane.setSelectedIndex(SUB_PRODUCT_OUTER_PANEL);												
			} else if(sub==SUB_PRODUCT_SHOES_PANEL) {
				productPanel.productTabbedPane.setSelectedIndex(SUB_PRODUCT_SHOES_PANEL);
			}
		}else if(top==TOP_SHOP_ORDER_PANEL) {
			mainTabbedPane.setSelectedIndex(TOP_SHOP_ORDER_PANEL);
			if(sub==SUB_ORDER_CART_PANEL) {
				
				try {
					orderPanel.orderTabbedPane.setSelectedIndex(SUB_ORDER_CART_PANEL);
					orderPanel.displayCartList();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(sub==SUB_ORDER_ORDER_PANEL) {
				orderPanel.orderTabbedPane.setSelectedIndex(SUB_ORDER_ORDER_PANEL);
			} else if(sub==SUB_ORDER_ORDER_LIST_PANEL) {
				orderPanel.orderTabbedPane.setSelectedIndex(SUB_ORDER_ORDER_LIST_PANEL);
			} else if(sub==SUB_ORDER_ORDER_DETAIL_PANEL) {
				orderPanel.orderTabbedPane.setSelectedIndex(SUB_ORDER_ORDER_DETAIL_PANEL);
			}
		}
			

	}
}