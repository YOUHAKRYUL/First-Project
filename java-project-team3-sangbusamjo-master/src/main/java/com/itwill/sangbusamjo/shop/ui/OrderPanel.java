package com.itwill.sangbusamjo.shop.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itwill.sangbusamjo.shop.service.CartService;
import com.itwill.sangbusamjo.shop.service.MemberService;
import com.itwill.sangbusamjo.shop.service.OrderService;
import com.itwill.sangbusamjo.shop.vo.Cart;
import com.itwill.sangbusamjo.shop.vo.Member;
import com.itwill.sangbusamjo.shop.vo.Order;
import com.itwill.sangbusamjo.shop.vo.OrderItem;
import com.itwill.sangbusamjo.shop.vo.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class OrderPanel extends JPanel {
	
	ShopMainFrame shopMainFrame;

	private JTable orderTable;
	private JTextField nameTextField;
	private JTextField phoneTextField;
	private JTextField addressTextField;
	private JTextField msgTextField;
	private JTable cartTable;
	private JTable orderListTable;
	private JTable orderDetailTable;
	public JTabbedPane orderTabbedPane;
	private JLabel totalPricePrintLabel;
	private JButton cartDeleteButton;
	private JButton orderDetailButton;
	private JPanel cartPanel;
	
	public void setShopMainFrame(ShopMainFrame shopMainFrame) {
		this.shopMainFrame=shopMainFrame;
	}

	/**
	 * Create the panel.
	 */
	public OrderPanel() {
		setLayout(new BorderLayout(0, 0));
		
		orderTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		orderTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int index = orderTabbedPane.getSelectedIndex();
				if(index==2) {
					shopMainFrame.changePanel(ShopMainFrame.TOP_SHOP_ORDER_PANEL, ShopMainFrame.SUB_ORDER_ORDER_LIST_PANEL, null);
					try {
						displayOrderList4();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		add(orderTabbedPane, BorderLayout.CENTER);
		
		cartPanel = new JPanel();
		orderTabbedPane.addTab("장바구니", null, cartPanel, null);
		cartPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel cartSouthPanel = new JPanel();
		cartPanel.add(cartSouthPanel, BorderLayout.SOUTH);
		
		JButton cartOrderButton = new JButton("주문하기");
		cartOrderButton.setActionCommand("전체 주문하기");
		cartOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int rowCount = cartTable.getRowCount();
					int columnCount = cartTable.getColumnCount();

					// 행과 열 수를 이용하여 JTable이 비어 있는지 확인
					if (rowCount == 0 || columnCount == 0) {
					    JOptionPane.showMessageDialog(null, "장바구니가 비었습니다.");
					    return;
					}
					orderTabbedPane.setEnabledAt(0, false);
					orderTabbedPane.setEnabledAt(1, true);
					orderTabbedPane.setSelectedIndex(1);
					displayOrderList();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cartSouthPanel.add(cartOrderButton);
		
		cartDeleteButton = new JButton("장바구니에서 삭제하기");
		cartDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// 설정
					CartService cartService = new CartService();
				int rowIndex = cartTable.getSelectedRow();
				int selectCartNo = (Integer)cartTable.getValueAt(rowIndex, 0);
					cartService.deleteCartItemByNo(selectCartNo);
					displayCartList();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//displayCartList();
				
			}
		});
		cartSouthPanel.add(cartDeleteButton);
		
		JScrollPane cartScrollPanel = new JScrollPane();
		cartPanel.add(cartScrollPanel, BorderLayout.NORTH);
		
		cartTable = new JTable();
		cartTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cartDeleteButton.setEnabled(true);
			}
		});
		cartTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\uBC88\uD638", "\uC774\uBBF8\uC9C0", "\uC81C\uD488\uBA85", "\uC218\uB7C9", "\uAC00\uACA9"
			}
		));
		cartTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		cartTable.getColumnModel().getColumn(2).setPreferredWidth(145);
		cartTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		cartTable.setRowHeight(30);
		cartScrollPanel.setViewportView(cartTable);
		
		JPanel orderPanel1 = new JPanel();
		orderTabbedPane.addTab("주문하기", null, orderPanel1, null);
		orderPanel1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane orderScrollPanel = new JScrollPane();
		orderPanel1.add(orderScrollPanel, BorderLayout.CENTER);
		
		JPanel orderPanel_2 = new JPanel();
		orderPanel_2.setMinimumSize(new Dimension(10, 800));
		orderScrollPanel.setViewportView(orderPanel_2);
		orderPanel_2.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 10, 339, 155);
		orderPanel_2.add(scrollPane_2);
		
		orderTable = new JTable();
		orderTable.setRowHeight(25);
		orderTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"\uC81C\uD488", "\uC81C\uD488\uBA85", "\uC218\uB7C9", "\uAC00\uACA9"
			}
		));
		scrollPane_2.setViewportView(orderTable);
		
		JLabel nameLabel = new JLabel("받는 분 성함");
		nameLabel.setBounds(36, 204, 68, 15);
		orderPanel_2.add(nameLabel);
		
		JLabel phoneLabel = new JLabel("전화번호");
		phoneLabel.setBounds(190, 204, 57, 15);
		orderPanel_2.add(phoneLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(36, 229, 116, 21);
		orderPanel_2.add(nameTextField);
		nameTextField.setColumns(10);
		
		phoneTextField = new JTextField();
		phoneTextField.setBounds(185, 229, 116, 21);
		orderPanel_2.add(phoneTextField);
		phoneTextField.setColumns(10);
		
		JLabel addressLabel = new JLabel("주소");
		addressLabel.setBounds(36, 268, 57, 15);
		orderPanel_2.add(addressLabel);
		
		JLabel msgLabel = new JLabel("배송 시 요청사항");
		msgLabel.setBounds(36, 324, 98, 15);
		orderPanel_2.add(msgLabel);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(36, 293, 265, 21);
		orderPanel_2.add(addressTextField);
		addressTextField.setColumns(10);
		
		msgTextField = new JTextField();
		msgTextField.setBounds(36, 349, 265, 21);
		orderPanel_2.add(msgTextField);
		msgTextField.setColumns(10);
		
		JButton cancelButton = new JButton("취소");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameTextField.setText("");
				phoneTextField.setText("");
				addressTextField.setText("");
				msgTextField.setText("");
				orderTabbedPane.setEnabledAt(0, true);
				orderTabbedPane.setEnabledAt(1, false);
				orderTabbedPane.setSelectedIndex(0);
			}
		});
		cancelButton.setBounds(190, 426, 97, 23);
		orderPanel_2.add(cancelButton);
		
		JButton orderButton = new JButton("결제하기");
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				CartService cartService=new CartService();
				OrderService orderService = new OrderService();
				
				orderTabbedPane.setEnabledAt(1, false);
				orderTabbedPane.setEnabledAt(0, true);
				String name = nameTextField.getText();
				String phone = phoneTextField.getText();
				String address = addressTextField.getText();
				String msg = msgTextField.getText();
				String totalPrice = totalPricePrintLabel.getText().substring(0,totalPricePrintLabel.getText().length()-1);
				if(name.equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
					nameTextField.requestFocus();
					return;
				}
				if(phone.equals("")) {
					JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.");
					phoneTextField.requestFocus();
					return;
				}
				if(address.equals("")) {
					JOptionPane.showMessageDialog(null, "주소를 입력해주세요.");
					addressTextField.requestFocus();
					return;
				}
				
				Order order = new Order(0, Integer.parseInt(totalPrice), msg,address, phone, null,name,Member.builder().mId(shopMainFrame.loginMember.getMId()).build(), null);
				orderTabbedPane.setSelectedIndex(2);
				shopMainFrame.tempOrder = order;
	
				orderService.createOrder(order);
				cartService.deleteCartItemByUserId(order.getMember().getMId());
				displayOrderList4();
				displayCartList();
				}catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				
			}
		});
		orderButton.setBounds(64, 426, 97, 23);
		orderPanel_2.add(orderButton);
		
		JLabel totalPriceLabel = new JLabel("총 주문금액 :");
		totalPriceLabel.setBounds(109, 390, 85, 15);
		orderPanel_2.add(totalPriceLabel);
		
		totalPricePrintLabel = new JLabel("");
		totalPricePrintLabel.setBounds(185, 390, 111, 15);
		orderPanel_2.add(totalPricePrintLabel);
		
		JCheckBox defaultSettingCheckBox = new JCheckBox("기본 배송지로 설정");
		defaultSettingCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					MemberService memberService = new MemberService();
					String memberId = shopMainFrame.loginMember.getMId();
					Member member = memberService.findMember(memberId);
					if (defaultSettingCheckBox.isSelected()) {
						addressTextField.setText(member.getMAddress());						
					}else {
						addressTextField.setText("");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		defaultSettingCheckBox.setBounds(176, 264, 152, 23);
		orderPanel_2.add(defaultSettingCheckBox);
		
		JPanel orderListPanel = new JPanel();
		orderListPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					displayOrderList4();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		orderTabbedPane.addTab("주문내역", null, orderListPanel, null);
		orderListPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane orderListScrollPanel = new JScrollPane();
		orderListPanel.add(orderListScrollPanel, BorderLayout.CENTER);
		
		orderListTable = new JTable();
		orderListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				orderDetailButton.setEnabled(true);
			}
		});
		orderListTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\uC8FC\uBB38\uBC88\uD638", "\uC8FC\uBB38\uB0A0\uC9DC", "\uBC1B\uB294 \uBD84", "\uACB0\uC81C\uAE08\uC561", "\uC8FC\uC18C"
			}
		));
		orderListTable.setRowHeight(30);
		orderListScrollPanel.setViewportView(orderListTable);
		
		JPanel orderListSouthPanel = new JPanel();
		orderListPanel.add(orderListSouthPanel, BorderLayout.SOUTH);
		
		orderDetailButton = new JButton("주문 상세보기");
		orderDetailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int rowIndex = orderListTable.getSelectedRow();
					int selectOrderNo = (Integer)orderListTable.getValueAt(rowIndex, 0);
					orderTabbedPane.setSelectedIndex(3);
					displayOrderDetailList(selectOrderNo);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		orderListSouthPanel.add(orderDetailButton);
		
		JPanel orderDetailPanel = new JPanel();
		orderTabbedPane.addTab("주문상세", null, orderDetailPanel, null);
		orderTabbedPane.setEnabledAt(3, false);
		orderDetailPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane orderDetailScrollPanel = new JScrollPane();
		orderDetailPanel.add(orderDetailScrollPanel, BorderLayout.CENTER);
		
		orderDetailTable = new JTable();
		orderDetailTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"\uC81C\uD488", "\uC81C\uD488 \uC774\uB984", "\uC218\uB7C9", "\uAC00\uACA9"
			}
		));
		orderDetailTable.setRowHeight(30);
		orderDetailScrollPanel.setViewportView(orderDetailTable);
		orderTabbedPane.setEnabledAt(1, false);
		
		

	}
	
	public void displayCartList() throws Exception {
		CartService cartService = new CartService();
		if(shopMainFrame.loginMember==null) {
			shopMainFrame.changePanel(shopMainFrame.TOP_SHOP_MEMBER_PANEL, shopMainFrame.SUB_MEMBER_LOGIN_PANEL, null);
			JOptionPane.showMessageDialog(null, "로그인이 필요합니다.");
			
			
			return;
		}
		// 설정
		List<Cart> cartList = cartService.getCartItemByUserId(shopMainFrame.loginMember.getMId());
		Vector columnVector = new Vector<>();
		columnVector.add("번호");
		columnVector.add("이미지");
		columnVector.add("제품명");
		columnVector.add("수량");
		columnVector.add("가격");
		Vector tableVector = new Vector();
		for (Cart cart : cartList) {
			Vector rowVector = new Vector();
			rowVector.add(cart.getCNo());
			rowVector.add(cart.getProduct().getPImage());
			rowVector.add(cart.getProduct().getPName());
			rowVector.add(cart.getCQty());
			rowVector.add(cart.getProduct().getPPrice());
			tableVector.add(rowVector);
		}
		DefaultTableModel tableModel = new DefaultTableModel(tableVector,columnVector);
		cartTable.setModel(tableModel);
		cartDeleteButton.setEnabled(false);
	}
	
	public void displayOrderList() throws Exception {
		
		// 설정
		CartService cartService = new CartService();
		
		String memberId = shopMainFrame.loginMember.getMId();
		List<Cart> cartList = cartService.getCartItemByUserId(memberId);
		Vector columnVector = new Vector();
		columnVector.add("제품");
		columnVector.add("제품명");
		columnVector.add("수량");
		columnVector.add("가격");
		Vector tableVector = new Vector();
		
		// 설정
		int totalPricePrint = 0;
		for (Cart cart : cartList) {
			Vector rowVector = new Vector();
			rowVector.add(cart.getProduct().getPImage());
			rowVector.add(cart.getProduct().getPName());
			rowVector.add(cart.getCQty());
			rowVector.add(cart.getProduct().getPPrice());
			totalPricePrint += cart.getCQty()*cart.getProduct().getPPrice();
			tableVector.add(rowVector);
		}
		
		totalPricePrintLabel.setText(totalPricePrint+"원");
		DefaultTableModel tableModel = new DefaultTableModel(tableVector,columnVector);
		orderTable.setModel(tableModel);
	}
	
	public void displayOrderList2(String address,String name) throws Exception {
		
		// 설정
		CartService cartService = new CartService();
		OrderService orderService = new OrderService();
		List<Order> orderList = orderService.orderListAll(shopMainFrame.loginMember.getMId());
		Vector columnVector = new Vector<>();
		columnVector.add("주문번호");
		columnVector.add("주문날짜");
		columnVector.add("받는 분");
		columnVector.add("결제금액");
		columnVector.add("주소");
		Vector tableVector = new Vector();
		for (Order order : orderList) {
			Vector rowVector = new Vector();
			rowVector.add(order.getONo());
			rowVector.add(order.getODate());
			rowVector.add(name);
			rowVector.add(order.getOTotalPrice());
			rowVector.add(address);
			tableVector.add(rowVector);
			orderDetailButton.setEnabled(false);

		}
		
		DefaultTableModel tableModel = new DefaultTableModel(tableVector,columnVector);
		orderListTable.setModel(tableModel);
		
	}
	
public void displayOrderDetailList(int selectOrderNo) throws Exception {
		
		// 설정
		CartService cartService = new CartService();
		OrderService orderService = new OrderService();
		Order order = orderService.orderWithOrderItem(selectOrderNo);
		Vector columnVector = new Vector<>();
		columnVector.add("제품");
		columnVector.add("제품 이름");
		columnVector.add("수량");
		columnVector.add("가격");
		Vector tableVector = new Vector();
		for (OrderItem orderItem : order.getOrderItemList()) {
			Vector rowVector = new Vector();
			rowVector.add(orderItem.getProduct().getPImage());
			rowVector.add(orderItem.getProduct().getPName());
			rowVector.add(orderItem.getOiQty());
			rowVector.add(orderItem.getProduct().getPPrice());
			tableVector.add(rowVector);
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(tableVector,columnVector);
		orderDetailTable.setModel(tableModel);
		
	}

public void displayOrderList4() throws Exception {
	
	// 설정
	CartService cartService = new CartService();
	OrderService orderService = new OrderService();
	List<Order> orderList = orderService.orderListAll(shopMainFrame.loginMember.getMId());
	Vector columnVector = new Vector<>();
	columnVector.add("주문번호");
	columnVector.add("주문날짜");
	columnVector.add("받는 분");
	columnVector.add("결제금액");
	columnVector.add("주소");
	Vector tableVector = new Vector();
	for (Order order : orderList) {
		Vector rowVector = new Vector();
		rowVector.add(order.getONo());
		rowVector.add(order.getODate());
		rowVector.add(order.getOName());
		rowVector.add(order.getOTotalPrice());
		rowVector.add(order.getOAddress());
		tableVector.add(rowVector);
		orderDetailButton.setEnabled(false);

	}
	
	DefaultTableModel tableModel = new DefaultTableModel(tableVector,columnVector);
	orderListTable.setModel(tableModel);


	 
	
}
}
