	package com.itwill.sangbusamjo.shop.ui;
	
	import java.awt.BorderLayout;
	import java.awt.Choice;
	import java.awt.Dimension;
	import java.awt.FlowLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.ItemEvent;
	import java.awt.event.ItemListener;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
	import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
	
	import com.itwill.sangbusamjo.shop.service.CartService;
	import com.itwill.sangbusamjo.shop.service.ProductService;
	import com.itwill.sangbusamjo.shop.vo.Cart;
import com.itwill.sangbusamjo.shop.vo.Member;
import com.itwill.sangbusamjo.shop.vo.Product;
	import java.awt.Cursor;
	
	public class ProductPanel extends JPanel {
		
		ShopMainFrame shopMainFrame;
		
		private JPanel ProductAllpanel;
		private JPanel productListContentPanel;
		private JLabel productDetailNameLB;
		private JLabel productDetailPriceLB;
		private JPanel productImageDetailPanel;
		
		private Cart OrderCartAddInfo;
		private Product selectedProduct;
		public JTabbedPane productTabbedPane;
		private ProductService productService;
		private JPanel topProductCategoryListPanel;
		private JPanel bottomProductCategoryListPanel;
		private JPanel shoesProductCategoryListPanel;
		private JPanel outerProductCategoryListPanel;
		private Choice productQtySelect;
		private JLabel productTotalPriceLB;
		
		public void setShopMainFrame(ShopMainFrame shopMainFrame) {
			this.shopMainFrame=shopMainFrame;
		}
		
		public ProductPanel() {
			setPreferredSize(new Dimension(370, 520));
			setLayout(new BorderLayout(0, 0));
	
			productTabbedPane = new JTabbedPane(JTabbedPane.TOP);
			add(productTabbedPane, BorderLayout.CENTER);
	
			JScrollPane productListTab = new JScrollPane();
			productTabbedPane.addTab("상품 리스트", null, productListTab, null);
	
			JPanel productListPanel = new JPanel();
			productListPanel.setPreferredSize(new Dimension(50, 50));
			productListTab.setViewportView(productListPanel);
			productListPanel.setLayout(new BorderLayout(0, 0));
	
			JScrollPane scrollPane = new JScrollPane();
			productListPanel.add(scrollPane, BorderLayout.CENTER);
	
			productListContentPanel = new JPanel();
			productListContentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			FlowLayout fl_productListContentPanel = (FlowLayout) productListContentPanel.getLayout();
			fl_productListContentPanel.setAlignment(FlowLayout.LEFT);
			productListContentPanel.setPreferredSize(new Dimension(10, 1300));
			scrollPane.setViewportView(productListContentPanel);
	
			/************** 상품하나 *************/
			JPanel productItemPanel = new JPanel();
			productItemPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	
		/*	productItemPanel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
	
					try {
						// 상품상세페이지 패널보여주기
						tabbedPane.setSelectedIndex(1);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			*/
			
			productItemPanel.setPreferredSize(new Dimension(150, 150));
			productListContentPanel.add(productItemPanel);
			productItemPanel.setLayout(null);
	
			JLabel productItemImageLb = new JLabel("");
			productItemImageLb.setIcon(new ImageIcon(ProductPanel.class.getResource("/images/cart.png")));
			productItemImageLb.setBounds(12, 10, 112, 64);
			productItemPanel.add(productItemImageLb);
	
			JLabel productItemNameLb = new JLabel("상품이름");
			productItemNameLb.setBounds(12, 94, 112, 22);
			productItemPanel.add(productItemNameLb);
	
			JLabel productItemPriceLb = new JLabel("상품가격");
			productItemPriceLb.setBounds(12, 118, 112, 22);
			productItemPanel.add(productItemPriceLb);
			/********************************/
	
			JPanel panel = new JPanel();
			productTabbedPane.addTab("상품상세보기", null, panel, null);
			productTabbedPane.setEnabledAt(1, false);
			panel.setLayout(new BorderLayout(0, 0));
	
			JScrollPane productDetailTab = new JScrollPane();
			panel.add(productDetailTab, BorderLayout.CENTER);
	
			JPanel productDetailPanel1 = new JPanel();
			productDetailTab.setViewportView(productDetailPanel1);
			productDetailPanel1.setLayout(new BorderLayout(0, 0));
	
			JScrollPane productDetailScrollPane = new JScrollPane();
			productDetailPanel1.add(productDetailScrollPane, BorderLayout.CENTER);
	
			JPanel productDetailPanel2 = new JPanel();
			productDetailScrollPane.setViewportView(productDetailPanel2);
	
			JPanel productDetailPanel3 = new JPanel();
			productDetailPanel3.setPreferredSize(new Dimension(330, 800));
			productDetailPanel2.add(productDetailPanel3);
			productDetailPanel3.setLayout(null);
	
			productImageDetailPanel = new JPanel();
			productImageDetailPanel.setBounds(10, 10, 310, 277);
			productDetailPanel3.add(productImageDetailPanel);
			productImageDetailPanel.setLayout(new BorderLayout(0, 0));
	
			JLabel productImageDetailLB = new JLabel("");
			productImageDetailLB.setIcon(new ImageIcon(ProductPanel.class.getResource("/images/image1.jpg")));
			productImageDetailPanel.add(productImageDetailLB, BorderLayout.CENTER);
	
			productDetailNameLB = new JLabel("제품명 :");
			productDetailNameLB.setBounds(31, 301, 200, 15);
			productDetailPanel3.add(productDetailNameLB);
	
			productDetailPriceLB = new JLabel("가격 :");
			productDetailPriceLB.setBounds(31, 350, 112, 15);
			productDetailPanel3.add(productDetailPriceLB);
			
			
			JButton productDetailCartBtn = new JButton("");
			productDetailCartBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int selectedQty = Integer.parseInt(productQtySelect.getSelectedItem());
					if(shopMainFrame.loginMember==null) {
						int option = JOptionPane.showConfirmDialog(null, "로그인 하시겠습니까?");
						if (option == JOptionPane.YES_OPTION) {
							
							shopMainFrame.changePanel(ShopMainFrame.TOP_SHOP_MEMBER_PANEL, ShopMainFrame.SUB_MEMBER_LOGIN_PANEL, null);
						} else {

						}
						return;
					}
					String memberId = shopMainFrame.loginMember.getMId();
					
					//String userId = "test1"; // 수정사항
	
					try {
						CartService cartService = new CartService();
						int result = cartService.addCart_(selectedQty,selectedProduct.getPNo(),memberId);
						if (result > 0) {
							JOptionPane.showMessageDialog(null, "장바구니에 상품이 추가되었습니다.");
							int option = JOptionPane.showConfirmDialog(null, "장바구니로 이동하시겠습니까?");
							if (option == JOptionPane.YES_OPTION) {
								shopMainFrame.changePanel(ShopMainFrame.TOP_SHOP_ORDER_PANEL, ShopMainFrame.SUB_ORDER_CART_PANEL, null);
								//Order
								}
						} else {
							
						}
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "장바구니 추가 중 오류가 발생했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			productDetailCartBtn.setIcon(new ImageIcon(ProductPanel.class.getResource("/images/cart.png")));
			productDetailCartBtn.setBounds(184, 412, 87, 23);
			productDetailPanel3.add(productDetailCartBtn);
			
			productQtySelect = new Choice();
			productQtySelect.setBounds(87, 412, 47, 21);
			productDetailPanel3.add(productQtySelect);
			for (int i = 1; i <= 10; i++) {
				productQtySelect.add(Integer.toString(i));
			}
			productQtySelect.addItemListener(new ItemListener() {
			    @Override
			    public void itemStateChanged(ItemEvent e) {
			        int selectedQty = Integer.parseInt(productQtySelect.getSelectedItem());
			        double productPrice = selectedProduct.getPPrice();
			        int totalPrice = (int) (selectedQty * productPrice);
			        productTotalPriceLB.setText("총가격: " + totalPrice + "원");
			    }
			});
			productTotalPriceLB = new JLabel("총가격 :");
			productTotalPriceLB.setFont(UIManager.getFont("Button.font"));
			productTotalPriceLB.setBounds(31, 375, 185, 22);
			productDetailPanel3.add(productTotalPriceLB);
			
			JLabel lblNewLabel = new JLabel("수량");
			lblNewLabel.setBounds(31, 418, 30, 15);
			productDetailPanel3.add(lblNewLabel);
	
			topProductCategoryListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
			topProductCategoryListPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			topProductCategoryListPanel.setPreferredSize(new Dimension(10, 800));
			JScrollPane topProductsScrollPane = new JScrollPane(topProductCategoryListPanel);
			productTabbedPane.addTab("상의", null, topProductsScrollPane, null);
	
			bottomProductCategoryListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
			bottomProductCategoryListPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			bottomProductCategoryListPanel.setPreferredSize(new Dimension(10, 800));
			JScrollPane bottomProductsScrollPane = new JScrollPane(bottomProductCategoryListPanel);
			productTabbedPane.addTab("하의", null, bottomProductsScrollPane, null);
	
			outerProductCategoryListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
			outerProductCategoryListPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			outerProductCategoryListPanel.setPreferredSize(new Dimension(10, 800));
			JScrollPane outerProductScrollPane = new JScrollPane(outerProductCategoryListPanel);
			productTabbedPane.addTab("아우터", null, outerProductScrollPane, null);
			
			shoesProductCategoryListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
			shoesProductCategoryListPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			shoesProductCategoryListPanel.setPreferredSize(new Dimension(10, 800));
			JScrollPane shoesProductScrollPane = new JScrollPane(shoesProductCategoryListPanel);
			productTabbedPane.addTab("신발", null, shoesProductScrollPane, null);
			
			JPanel topProductPanel = new JPanel();
			topProductPanel.setPreferredSize(new Dimension(150, 150));
			topProductCategoryListPanel.add(topProductPanel);
			topProductPanel.setLayout(null);
	
			JPanel bottomProductPanel = new JPanel();
			bottomProductPanel.setPreferredSize(new Dimension(150, 150));
			bottomProductCategoryListPanel.add(bottomProductPanel);
			bottomProductPanel.setLayout(null);
			
			JPanel outerProductPanel = new JPanel();
			outerProductPanel.setPreferredSize(new Dimension(150, 150));
			outerProductCategoryListPanel.add(outerProductPanel);
			outerProductPanel.setLayout(null);
			
			JPanel shoesProductPanel = new JPanel();
			shoesProductPanel.setPreferredSize(new Dimension(150, 150));
			shoesProductCategoryListPanel.add(shoesProductPanel);
			shoesProductPanel.setLayout(null);
							
			JLabel topCategotyProductImageLB = new JLabel("");
			topCategotyProductImageLB.setBounds(12, 10, 112, 64);
			topProductPanel.add(topCategotyProductImageLB);
	
			JLabel topCategotyProductPriceLB = new JLabel("상품가격");
			topCategotyProductPriceLB.setBounds(12, 118, 112, 22);
			topProductPanel.add(topCategotyProductPriceLB);
	
			JLabel topCategotyProductNameLB = new JLabel("상품이름");
			topCategotyProductNameLB.setBounds(12, 94, 112, 22);
			topProductPanel.add(topCategotyProductNameLB);
	
			JLabel bottomCategotyProductImageLB = new JLabel("");
			bottomCategotyProductImageLB.setBounds(12, 10, 112, 64);
			bottomProductPanel.add(bottomCategotyProductImageLB);
			
			JLabel bottomCategotyProductPriceLB = new JLabel("상품가격");
			bottomCategotyProductPriceLB.setBounds(12, 118, 112, 22);
			bottomProductPanel.add(bottomCategotyProductPriceLB);
			
			JLabel bottomCategotyProductNameLB = new JLabel("상품이름");
			bottomCategotyProductNameLB.setBounds(12, 94, 112, 22);
			bottomProductPanel.add(bottomCategotyProductNameLB);
			
			
			JLabel outerCategotyProductImageLB = new JLabel("");
			outerCategotyProductImageLB.setBounds(12, 10, 112, 64);
			outerProductPanel.add(outerCategotyProductImageLB);
			
			JLabel outerCategotyProductPriceLB = new JLabel("상품가격");
			outerCategotyProductPriceLB.setBounds(12, 118, 112, 22);
			outerProductPanel.add(outerCategotyProductPriceLB);
			
			JLabel outerCategotyProductNameLB = new JLabel("상품이름");
			outerCategotyProductNameLB.setBounds(12, 94, 112, 22);
			outerProductPanel.add(outerCategotyProductNameLB);
			
			JLabel shoesCategotyProductImageLB = new JLabel("");
			shoesCategotyProductImageLB.setBounds(12, 10, 112, 64);
			shoesProductPanel.add(shoesCategotyProductImageLB);
			
			JLabel shoesCategotyProductPriceLB = new JLabel("상품가격");
			shoesCategotyProductPriceLB.setBounds(12, 118, 112, 22);
			shoesProductPanel.add(shoesCategotyProductPriceLB);
			
			JLabel shoesCategotyProductNameLB = new JLabel("상품이름");
			shoesCategotyProductNameLB.setBounds(12, 94, 112, 22);
			shoesProductPanel.add(shoesCategotyProductNameLB);
			
			
	
			try {
				displayProductList();
			} catch (Exception e) {
				e.printStackTrace();
			}

	
			productTabbedPane.addChangeListener(e -> {
			    JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
			    int selectedIndex = sourceTabbedPane.getSelectedIndex();
			    String title = sourceTabbedPane.getTitleAt(selectedIndex);
	
			    if (title.equals("상의")) {
			        try {
			            displayProductsByTopCategory(1); 
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    } else if (title.equals("하의")) {
			        try {
			            displayProductsByBottomCategory(2); 
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }else if (title.equals("아우터")) {
			        try {
			            displayProductsByOuterCategory(4); 
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }else if (title.equals("신발")) {
			        try {
			            displayProductsByShoesCategory(3); 
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			    panel.setEnabled(false);
			    
			    
			    
			});
	
		}// 생성자 끝
	
		public void displayProductList() throws Exception {
			ProductService productService = new ProductService();
			productService = new ProductService();
			productListContentPanel.removeAll();
			
			List<Product> productList = productService.ProductList();
			for (Product product : productList) {
				JPanel productItemPanel = new JPanel();
	
				productItemPanel.addMouseListener(new MouseAdapter() {
	
					public void mouseClicked(MouseEvent e) {
	
						try {
	
							productTabbedPane.setSelectedIndex(1);
	
						} catch (Exception e1) {
							e1.printStackTrace();
	
						}
					}
				});
	
				productItemPanel.setPreferredSize(new Dimension(150, 150));
				productListContentPanel.add(productItemPanel);
				productItemPanel.setLayout(null);
	
				JLabel productItemImageLb = new JLabel("");
				productItemImageLb.setIcon(new ImageIcon(
						ProductPanel.class.getResource("/images/" + product.getPImage() + ".png")));
				productItemImageLb.setBounds(12, 10, 112, 64);
				productItemPanel.add(productItemImageLb);
	
				JLabel productItemNameLb = new JLabel(product.getPName());
				productItemNameLb.setBounds(12, 94, 112, 22);
				productItemPanel.add(productItemNameLb);
	
				JLabel productItemPriceLb = new JLabel(product.getPPrice() + "" + "원");
				productItemPriceLb.setBounds(12, 118, 112, 22);
				productItemPanel.add(productItemPriceLb);
	
				productItemPanel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showProductDetail(product);
					}
				});
	
			}
		}
	
		private void showProductDetail(Product product) {
			if (product != null) {
				this.selectedProduct = product;
				productImageDetailPanel.removeAll();
				
				JLabel productItemImageLb = new JLabel("");
				productItemImageLb.setIcon(new ImageIcon(
						ProductPanel.class.getResource("/images/" + product.getPImage() + ".jpg")));
				productItemImageLb.setBounds(12, 10, 112, 64);
				productImageDetailPanel.add(productItemImageLb);
	
				productDetailNameLB.setText("제품명: " + product.getPName());
				productDetailPriceLB.setText("가격: " + product.getPPrice() + "원");
				
				productImageDetailPanel.revalidate();
			    productImageDetailPanel.repaint();
			}
		}
	
		public void displayProductsByTopCategory(int category) throws Exception {
			productService = new ProductService(); 
			List<Product> productList = productService.ProductList();
	
			topProductCategoryListPanel.removeAll();
			topProductCategoryListPanel.revalidate();
			topProductCategoryListPanel.repaint();
	
			for (Product product : productList) {
				if (product.getPCategory() == category) {
					JPanel productPanel = new JPanel();
					productPanel.setLayout(null);
					productPanel.setPreferredSize(new Dimension(150, 150));
	
					JLabel productItemImageLb_1 = new JLabel();
					productItemImageLb_1
							.setIcon(new ImageIcon(getClass().getResource("/images/" + product.getPImage() + ".png")));
					productItemImageLb_1.setBounds(12, 10, 112, 64);
					productPanel.add(productItemImageLb_1);
	
					JLabel productItemNameLb_1 = new JLabel(product.getPName());
					productItemNameLb_1.setBounds(12, 84, 112, 22);
					productPanel.add(productItemNameLb_1);
	
					JLabel productItemPriceLb_1 = new JLabel(String.format("%d원", product.getPPrice()));
					productItemPriceLb_1.setBounds(12, 106, 112, 22);
					productPanel.add(productItemPriceLb_1);
					productPanel.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseClicked(MouseEvent e) {
			                showProductDetail(product);
			                productTabbedPane.setSelectedIndex(1);
			            }
			        });
					topProductCategoryListPanel.add(productPanel);
					
					
				}
			}
			topProductCategoryListPanel.revalidate();
			topProductCategoryListPanel.repaint();
			
			
		}
	
		public void displayProductsByBottomCategory(int category) throws Exception {
			productService = new ProductService(); 
			List<Product> productList = productService.ProductList();
	
			bottomProductCategoryListPanel.removeAll();
			bottomProductCategoryListPanel.revalidate();
			bottomProductCategoryListPanel.repaint();
	
			for (Product product : productList) {
				if (product.getPCategory() == category) {
					JPanel productPanel = new JPanel();
					productPanel.setLayout(null);
					productPanel.setPreferredSize(new Dimension(150, 150));
					productPanel.putClientProperty("product", product); // 상품 정보를 패널에 태깅
	
					JLabel bottomCategotyProductImageLB = new JLabel();
					bottomCategotyProductImageLB.setIcon(new ImageIcon(getClass().getResource("/images/" + product.getPImage() + ".png")));
					bottomCategotyProductImageLB.setBounds(12, 10, 112, 64);
					productPanel.add(bottomCategotyProductImageLB);
	
					JLabel bottomCategotyProductNameLB = new JLabel(product.getPName());
					bottomCategotyProductNameLB.setBounds(12, 84, 112, 22);
					productPanel.add(bottomCategotyProductNameLB);
	
					JLabel bottomCategotyProductPriceLB = new JLabel(String.format("%d원", product.getPPrice()));
					bottomCategotyProductPriceLB.setBounds(12, 106, 112, 22);
					productPanel.add(bottomCategotyProductPriceLB);
					productPanel.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseClicked(MouseEvent e) {
			                showProductDetail(product);
			                productTabbedPane.setSelectedIndex(1);
			            }
			        });
					bottomProductCategoryListPanel.add(productPanel);
				}
			}
			bottomProductCategoryListPanel.revalidate();
			bottomProductCategoryListPanel.repaint();
		}
		
		public void displayProductsByOuterCategory(int category) throws Exception {
			productService = new ProductService(); 
			List<Product> productList = productService.ProductList();
	
			outerProductCategoryListPanel.removeAll();
			outerProductCategoryListPanel.revalidate();
			outerProductCategoryListPanel.repaint();
	
			for (Product product : productList) {
				if (product.getPCategory() == category) {
					JPanel productPanel = new JPanel();
					productPanel.setLayout(null);
					productPanel.setPreferredSize(new Dimension(150, 150));
	
					JLabel outerCategotyProductImageLB = new JLabel();
					outerCategotyProductImageLB.setIcon(new ImageIcon(getClass().getResource("/images/" + product.getPImage() + ".png")));
					outerCategotyProductImageLB.setBounds(12, 10, 112, 64);
					productPanel.add(outerCategotyProductImageLB);
	
					JLabel outerCategotyProductNameLB = new JLabel(product.getPName());
					outerCategotyProductNameLB.setBounds(12, 84, 112, 22);
					productPanel.add(outerCategotyProductNameLB);
	
					JLabel outerCategotyProductPriceLB = new JLabel(String.format("%d원", product.getPPrice()));
					outerCategotyProductPriceLB.setBounds(12, 106, 112, 22);
					productPanel.add(outerCategotyProductPriceLB);
					productPanel.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseClicked(MouseEvent e) {
			                showProductDetail(product);
			                productTabbedPane.setSelectedIndex(1);
			            }
			        });
					outerProductCategoryListPanel.add(productPanel);
					
				}
			}
			outerProductCategoryListPanel.revalidate();
			outerProductCategoryListPanel.repaint();
		}
		
		public void displayProductsByShoesCategory(int category) throws Exception {
			productService = new ProductService(); 
			List<Product> productList = productService.ProductList();
	
			shoesProductCategoryListPanel.removeAll();
			shoesProductCategoryListPanel.revalidate();
			shoesProductCategoryListPanel.repaint();
	
			for (Product product : productList) {
				if (product.getPCategory() == category) {
					JPanel productPanel = new JPanel();
					productPanel.setLayout(null);
					productPanel.setPreferredSize(new Dimension(150, 150));
	
					JLabel shoesCategotyProductImageLB = new JLabel();
					shoesCategotyProductImageLB.setIcon(new ImageIcon(getClass().getResource("/images/" + product.getPImage() + ".png")));
					shoesCategotyProductImageLB.setBounds(12, 10, 112, 64);
					productPanel.add(shoesCategotyProductImageLB);
	
					JLabel shoesCategotyProductNameLB = new JLabel(product.getPName());
					shoesCategotyProductNameLB.setBounds(12, 84, 112, 22);
					productPanel.add(shoesCategotyProductNameLB);
	
					JLabel shoesCategotyProductPriceLB = new JLabel(String.format("%d원", product.getPPrice()));
					shoesCategotyProductPriceLB.setBounds(12, 106, 112, 22);
					productPanel.add(shoesCategotyProductPriceLB);
					productPanel.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseClicked(MouseEvent e) {
			                showProductDetail(product);
			                productTabbedPane.setSelectedIndex(1);
			            }
			        });
					shoesProductCategoryListPanel.add(productPanel);
				}
			}
			shoesProductCategoryListPanel.revalidate();
			shoesProductCategoryListPanel.repaint();
		}
	}