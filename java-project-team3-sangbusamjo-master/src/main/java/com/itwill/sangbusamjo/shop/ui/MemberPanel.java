package com.itwill.sangbusamjo.shop.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.itwill.sangbusamjo.shop.service.MemberService;
import com.itwill.sangbusamjo.shop.vo.Member;
import java.awt.Color;

public class MemberPanel extends JPanel {


	/********* 1.MemberService멤버필드선언 *****/

	/******** 1. ShopMainFrame 멤버필드선언 *****/
	ShopMainFrame shopMainFrame;


	/********* 2.로그인한회원 멤버필드선언 *****/
	Member loginMember;

	private JTextField memberIdTextField;
	private JTextField memberJoinIdTextField;
	private JTextField memberJoinNameTextField;
	private JTextField memberJoinPhoneTextField;
	private JTextField memberJoinAddressTextField;
	private JTextField memberFindIdNameTextField;
	private JTextField memberFindIdPhoneTextField;
	private JPasswordField memberPwField;
	private JTextField memberFindPwIdTextField;
	private JTextField memberFindPwNameTextField;
	private JTextField memberInfoIdTextField;
	private JTextField memberInfoPwTextField;
	private JTextField memberInfoNameTextField;
	private JTextField memberInfoPhoneTextField;
	private JTextField memberInfoAddressTextField;
	public JTabbedPane memberTabbedPane;
	private JLabel memberLoginIdMessageLabel;
	private JLabel memberLoginPwMessageLabel;
	private JLabel memberJoinDuplicateIdMessageLabel;
	private JButton memberUpdateButton;
	private JButton memberJoinDuplicateCheckButton;
	private JButton memberJoinButton;
	private JLabel memberJoinPwMsgLabel;
	private JLabel memberJoinPwCheckMsgLabel;
	private JLabel memberJoinNameMsgLabel;
	private JLabel memberJoinPhoneMsgLabel;
	private JLabel memberJoinAddressMsgLabel;
	private JPasswordField memberJoinPwTextField;
	private JPasswordField memberJoinPwCheckTextField;
	
	/******** 2. MemberPanel에 setter 메소드 생성 ********/
	public void setShopMainFrame(ShopMainFrame shopMainFrame) {
		this.shopMainFrame=shopMainFrame;
	}
	/**
	 * Create the panel.
	 */
	public MemberPanel() {

		setLayout(new BorderLayout(0, 0));

		memberTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(memberTabbedPane, BorderLayout.CENTER);

		JPanel loginPanel = new JPanel();
		memberTabbedPane.addTab("로그인", null, loginPanel, null);
		loginPanel.setLayout(null);

		JLabel memberLoginIdLabel = new JLabel("ID");
		memberLoginIdLabel.setBounds(58, 186, 57, 15);
		loginPanel.add(memberLoginIdLabel);

		JLabel memberLoginPwLabel = new JLabel("PASSWORD");
		memberLoginPwLabel.setBounds(25, 234, 74, 15);
		loginPanel.add(memberLoginPwLabel);

		memberIdTextField = new JTextField();
		memberIdTextField.setBounds(114, 180, 188, 21);
		loginPanel.add(memberIdTextField);
		memberIdTextField.setColumns(10);

		JButton memberLoginButton = new JButton("로그인");
		memberLoginButton.addActionListener(new ActionListener() {
			// 로그인 성공 시 메인화면

			public void actionPerformed(ActionEvent e) {
				/*********** 회원로그인 ************/

				/******** 로그인버튼클릭시 *********/
				// 1.입력유효성체크
				String memberid = memberIdTextField.getText();
				String password = memberPwField.getText();
				if (memberid.equals("") && password.equals("")) {
					// 아이디와 비밀번호 모두 공백인 경우
					memberLoginIdMessageLabel.setText("아이디를 입력하세요.");
					memberLoginPwMessageLabel.setText("비밀번호를 입력하세요.");
					memberIdTextField.requestFocus();
					return;
				} else if (memberid.equals("")) {
					// 아이디만 공백인 경우
					memberLoginIdMessageLabel.setText("아이디를 입력하세요.");
					memberIdTextField.requestFocus();
					return;
				} else if (password.equals("")) {
					// 비밀번호만 공백인 경우
					memberLoginIdMessageLabel.setText("");
					memberLoginPwMessageLabel.setText("비밀번호를 입력하세요.");
					memberPwField.requestFocus();
					return;
				}
				// 2.UserService.login 메쏘드호출
				/*********** 2.UserService.login 메쏘드호출 **************/

				try {
					MemberService memberService = new MemberService();
					int result = memberService.login(memberid, password);
					if (result == 1) {
						/*
						 * 로그인성공 - 성공한아이디로 User정보얻기 - ShopMainFrame에 User객체 넘겨주기(ShopMainFrame객체의 메소드호출시
						 * 인자로 넘겨주기) - 로그인창닫기
						 */
						Member loginMember = memberService.findMember(memberid);
						loginProcess(loginMember);
						memberLoginIdMessageLabel.setText("");
						memberLoginPwMessageLabel.setText("");
					} else if (result == 0) {
						// 로그인실패
						memberLoginIdMessageLabel.setText("아이디/비밀번호 불일치");
						memberLoginPwMessageLabel.setText("");
						memberIdTextField.setText(""); // 아이디 초기화
						memberPwField.setText(""); // 비밀번호 초기화
						memberIdTextField.requestFocus();
						memberIdTextField.setSelectionStart(0);
						memberIdTextField.setSelectionEnd(memberIdTextField.getText().length());
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				/******************************************************/
			}
		});

		memberLoginButton.setBounds(114, 287, 153, 33);
		loginPanel.add(memberLoginButton);

		JButton memberLoginJoinButton = new JButton("회원가입");
		memberLoginJoinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberTabbedPane.setSelectedIndex(1);
			}
		});
		memberLoginJoinButton.setBounds(114, 330, 153, 33);
		loginPanel.add(memberLoginJoinButton);

		JButton memberFindIdPwButton = new JButton("아이디/비밀번호 찾기");
		memberFindIdPwButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberTabbedPane.setSelectedIndex(3);
			}
		});
		memberFindIdPwButton.setBounds(114, 373, 153, 33);
		loginPanel.add(memberFindIdPwButton);

		memberPwField = new JPasswordField();
		memberPwField.setBounds(114, 231, 188, 21);
		loginPanel.add(memberPwField);

		memberLoginIdMessageLabel = new JLabel("");
		memberLoginIdMessageLabel.setForeground(Color.RED);
		memberLoginIdMessageLabel.setBounds(114, 200, 200, 21);
		loginPanel.add(memberLoginIdMessageLabel);

		memberLoginPwMessageLabel = new JLabel("");
		memberLoginPwMessageLabel.setForeground(Color.RED);
		memberLoginPwMessageLabel.setBounds(114, 244, 200, 50);
		loginPanel.add(memberLoginPwMessageLabel);

		JPanel joinPanel = new JPanel();
		memberTabbedPane.addTab("회원가입", null, joinPanel, null);
		joinPanel.setLayout(null);

		JLabel memberJoinIdLabel = new JLabel("아이디");
		memberJoinIdLabel.setBounds(12, 90, 57, 15);
		joinPanel.add(memberJoinIdLabel);

		JLabel memberJoinPwLabel = new JLabel("비밀번호");
		memberJoinPwLabel.setBounds(12, 140, 57, 15);
		joinPanel.add(memberJoinPwLabel);

		JLabel memberJoinPwCheckLabel = new JLabel("비밀번호 확인");
		memberJoinPwCheckLabel.setBounds(12, 190, 86, 15);
		joinPanel.add(memberJoinPwCheckLabel);

		JLabel memberJoinNameLabel = new JLabel("이름");
		memberJoinNameLabel.setBounds(12, 240, 57, 15);
		joinPanel.add(memberJoinNameLabel);

		JLabel memberJoinPhoneLabel = new JLabel("전화번호");
		memberJoinPhoneLabel.setBounds(12, 290, 57, 15);
		joinPanel.add(memberJoinPhoneLabel);

		JLabel memberJoinAddressLabel = new JLabel("주소");
		memberJoinAddressLabel.setBounds(12, 340, 57, 15);
		joinPanel.add(memberJoinAddressLabel);

		memberJoinIdTextField = new JTextField();
		memberJoinIdTextField.setBounds(98, 87, 161, 21);
		joinPanel.add(memberJoinIdTextField);
		memberJoinIdTextField.setColumns(10);

		memberJoinDuplicateCheckButton = new JButton("중복체크");
		memberJoinDuplicateCheckButton.addActionListener(new ActionListener() {
			// 회원가입 시 아이디 중복체크 버튼
			public void actionPerformed(ActionEvent e) {
				try {
					MemberService memberService = new MemberService();
					String memberId = memberJoinIdTextField.getText();
					boolean isDuplicate = memberService.isDuplicate(memberId);
					if (isDuplicate) {
						memberJoinIdTextField.setSelectionStart(0);
						memberJoinIdTextField.setSelectionEnd(memberId.length());
						memberJoinIdTextField.requestFocus();
						memberJoinDuplicateIdMessageLabel.setText("아이디가 사용중입니다");
						return;
					} else {
						memberJoinIdTextField.setSelectionStart(0);
						memberJoinIdTextField.setSelectionEnd(memberId.length());
						memberJoinPwTextField.requestFocus();
						memberJoinDuplicateIdMessageLabel.setText("아이디 사용 가능");
						memberJoinButton.setEnabled(true);
					}
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
		memberJoinDuplicateCheckButton.setBounds(268, 86, 97, 23);
		joinPanel.add(memberJoinDuplicateCheckButton);

		memberJoinNameTextField = new JTextField();
		memberJoinNameTextField.setBounds(98, 237, 161, 21);
		joinPanel.add(memberJoinNameTextField);
		memberJoinNameTextField.setColumns(10);

		memberJoinPhoneTextField = new JTextField();
		memberJoinPhoneTextField.setBounds(98, 287, 161, 21);
		joinPanel.add(memberJoinPhoneTextField);
		memberJoinPhoneTextField.setColumns(10);

		memberJoinAddressTextField = new JTextField();
		memberJoinAddressTextField.setBounds(98, 337, 161, 21);
		joinPanel.add(memberJoinAddressTextField);
		memberJoinAddressTextField.setColumns(10);

		memberJoinButton = new JButton("회원가입");
		memberJoinButton.setEnabled(false);
		memberJoinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 회원가입 버튼
				try {
					MemberService memberService = new MemberService();

					String memberId = memberJoinIdTextField.getText();
					String memberPw = memberJoinPwTextField.getText();
					String memberName = memberJoinNameTextField.getText();
					String memberPhone = memberJoinPhoneTextField.getText();
					String memberAddress = memberJoinAddressTextField.getText();

					/******** 유효성 체크 ***********/
					if (memberPw.equals("") || memberPw == null) {
						memberJoinPwMsgLabel.setText("비밀번호를 입력하세요");
						memberJoinPwTextField.requestFocus();
						return;
					} else {
						memberJoinPwMsgLabel.setText("");
					}
					if (!memberPw.equals(memberJoinPwCheckTextField.getText())) {
						memberJoinPwCheckMsgLabel.setText("비밀번호가 일치하지 않습니다");
						memberJoinPwCheckTextField.requestFocus();
						return;
					} else {
						memberJoinPwCheckMsgLabel.setText("");
					}
					if (memberName.equals("") || memberName == null) {
						memberJoinNameMsgLabel.setText("이름을 입력하세요");
						memberJoinNameTextField.requestFocus();
						return;
					} else {
						memberJoinNameMsgLabel.setText("");
					}
					if (memberPhone.equals("") || memberPhone == null) {
						memberJoinPhoneMsgLabel.setText("번호를 입력하세요");
						memberJoinPhoneTextField.requestFocus();
						return;
					} else {
						memberJoinPhoneMsgLabel.setText("");
					}
					if (memberAddress.equals("") || memberAddress == null) {
						memberJoinAddressMsgLabel.setText("주소를 입력하세요");
						memberJoinAddressTextField.requestFocus();
						return;
					} else {
						memberJoinAddressMsgLabel.setText("");
					}
					loginMember = new Member(memberId, memberPw, memberName, memberPhone, memberAddress);
					memberService.create(loginMember);

					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다");
					// 로그인 탭으로 이동
					memberTabbedPane.setSelectedIndex(0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				;

			}
		});
		memberJoinButton.setBounds(63, 403, 97, 34);
		joinPanel.add(memberJoinButton);

		JButton memberJoincancleButton = new JButton("취소");
		memberJoincancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberTabbedPane.setSelectedIndex(0);
				memberJoinIdTextField.setText("");
				memberJoinPwTextField.setText("");
				memberJoinPwCheckTextField.setText("");
				memberJoinNameTextField.setText("");
				memberJoinPhoneTextField.setText("");
				memberJoinAddressTextField.setText("");

			}
		});
		memberJoincancleButton.setBounds(195, 403, 97, 34);
		joinPanel.add(memberJoincancleButton);

		memberJoinDuplicateIdMessageLabel = new JLabel("");
		memberJoinDuplicateIdMessageLabel.setForeground(Color.RED);
		memberJoinDuplicateIdMessageLabel.setBounds(108, 94, 200, 50);
		joinPanel.add(memberJoinDuplicateIdMessageLabel);

		memberJoinPwMsgLabel = new JLabel("");
		memberJoinPwMsgLabel.setForeground(Color.RED);
		memberJoinPwMsgLabel.setBounds(98, 140, 200, 50);
		joinPanel.add(memberJoinPwMsgLabel);

		memberJoinPwCheckMsgLabel = new JLabel("");
		memberJoinPwCheckMsgLabel.setForeground(Color.RED);
		memberJoinPwCheckMsgLabel.setBounds(98, 190, 200, 50);
		joinPanel.add(memberJoinPwCheckMsgLabel);

		memberJoinNameMsgLabel = new JLabel("");
		memberJoinNameMsgLabel.setForeground(Color.RED);
		memberJoinNameMsgLabel.setBounds(98, 240, 200, 50);
		joinPanel.add(memberJoinNameMsgLabel);

		memberJoinPhoneMsgLabel = new JLabel("");
		memberJoinPhoneMsgLabel.setForeground(Color.RED);
		memberJoinPhoneMsgLabel.setBounds(98, 294, 200, 50);
		joinPanel.add(memberJoinPhoneMsgLabel);

		memberJoinAddressMsgLabel = new JLabel("");
		memberJoinAddressMsgLabel.setForeground(Color.RED);
		memberJoinAddressMsgLabel.setBounds(98, 340, 200, 50);
		joinPanel.add(memberJoinAddressMsgLabel);
		
		memberJoinPwTextField = new JPasswordField();
		memberJoinPwTextField.setBounds(98, 137, 161, 21);
		joinPanel.add(memberJoinPwTextField);
		
		memberJoinPwCheckTextField = new JPasswordField();
		memberJoinPwCheckTextField.setBounds(98, 187, 161, 21);
		joinPanel.add(memberJoinPwCheckTextField);

		JPanel infoPanel = new JPanel();
		memberTabbedPane.addTab("회원정보", null, infoPanel, null);
		memberTabbedPane.setEnabledAt(2, false);
		infoPanel.setLayout(null);

		JLabel memberInfoIdLabel = new JLabel("아이디");
		memberInfoIdLabel.setBounds(33, 96, 57, 15);
		infoPanel.add(memberInfoIdLabel);

		JLabel memberInfoPwLabel = new JLabel("비밀번호");
		memberInfoPwLabel.setBounds(33, 146, 57, 15);
		infoPanel.add(memberInfoPwLabel);

		JLabel memberInfoNameLabel = new JLabel("이름");
		memberInfoNameLabel.setBounds(33, 196, 57, 15);
		infoPanel.add(memberInfoNameLabel);

		JLabel memberInfoPhoneLabel = new JLabel("전화번호");
		memberInfoPhoneLabel.setBounds(33, 246, 57, 15);
		infoPanel.add(memberInfoPhoneLabel);

		JLabel memberInfoAddressLabel = new JLabel("주소");
		memberInfoAddressLabel.setBounds(33, 296, 57, 15);
		infoPanel.add(memberInfoAddressLabel);

		memberInfoIdTextField = new JTextField();
		memberInfoIdTextField.setEditable(false);
		memberInfoIdTextField.setColumns(10);
		memberInfoIdTextField.setBounds(119, 93, 173, 21);
		infoPanel.add(memberInfoIdTextField);

		memberInfoPwTextField = new JTextField();
		memberInfoPwTextField.setEditable(false);
		memberInfoPwTextField.setColumns(10);
		memberInfoPwTextField.setBounds(119, 143, 173, 21);
		infoPanel.add(memberInfoPwTextField);

		memberInfoNameTextField = new JTextField();
		memberInfoNameTextField.setEditable(false);
		memberInfoNameTextField.setColumns(10);
		memberInfoNameTextField.setBounds(119, 193, 173, 21);
		infoPanel.add(memberInfoNameTextField);

		memberInfoPhoneTextField = new JTextField();
		memberInfoPhoneTextField.setEditable(false);
		memberInfoPhoneTextField.setColumns(10);
		memberInfoPhoneTextField.setBounds(119, 243, 173, 21);
		infoPanel.add(memberInfoPhoneTextField);

		memberInfoAddressTextField = new JTextField();
		memberInfoAddressTextField.setEditable(false);
		memberInfoAddressTextField.setColumns(10);
		memberInfoAddressTextField.setBounds(119, 293, 173, 21);
		infoPanel.add(memberInfoAddressTextField);

		memberUpdateButton = new JButton("회원수정");
		memberUpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 회원수정 버튼
				try {
					MemberService memberService = new MemberService();
					String memberId = memberInfoIdTextField.getText();
					String memberPw = memberInfoPwTextField.getText();
					String memberName = memberInfoNameTextField.getText();
					String memberPhone = memberInfoPhoneTextField.getText();
					String memberAddress = memberInfoAddressTextField.getText();
					int option = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?");
					if (option == JOptionPane.YES_OPTION) {
						Member updateMember = new Member(memberId, memberPw, memberName, memberPhone, memberAddress);
						memberService.update(updateMember);
						loginMember = memberService.findMember(memberId);
						updateFormEnable(false);
					} else {
						displayMemberInfo(loginMember);

					}

					loginMember = memberService.findMember(memberId);
					updateFormEnable(false);

				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
		memberUpdateButton.setEnabled(false);
		memberUpdateButton.setBounds(195, 356, 97, 34);
		infoPanel.add(memberUpdateButton);

		JButton memberInfoLogoutButton = new JButton("로그아웃");
		memberInfoLogoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 상품리스트화면으로
				int option = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?");
				if (option == JOptionPane.YES_OPTION) {
					logoutProcess();

				} else {

				}

			}
		});
		memberInfoLogoutButton.setBounds(75, 400, 97, 34);
		infoPanel.add(memberInfoLogoutButton);

		JButton memberInfoUpdateFormButton = new JButton("수정하기");
		memberInfoUpdateFormButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 수정하기 버튼 활성화
				updateFormEnable(true);
			}
		});
		memberInfoUpdateFormButton.setBounds(75, 356, 97, 34);
		infoPanel.add(memberInfoUpdateFormButton);

		JButton memberInfoViewButton = new JButton("정보보기");
		memberInfoViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 로그인한 회원 정보보기
				displayMemberInfo(loginMember);
			}
		});
		memberInfoViewButton.setBounds(29, 47, 97, 23);
		infoPanel.add(memberInfoViewButton);

		JButton memberInfoDeleteButton = new JButton("회원탈퇴");
		memberInfoDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MemberService memberService = new MemberService();

					// 현재 로그인한 회원 아이디
					String deleteMemberId = loginMember.getMId();

					// 경고 메시지 표시 후 확인 시 탈퇴 진행
					int option = JOptionPane.showConfirmDialog(null, "정말로 회원탈퇴 하시겠습니까?");
					if (option == JOptionPane.YES_OPTION) {
						int result = memberService.delete(deleteMemberId);
						if (result > 0) {
							// 탈퇴 성공

							JOptionPane.showMessageDialog(null, "회원탈퇴가 완료되었습니다.");
							logoutProcess();

						} else {
							// 탈퇴 실패

						}
						// 이후 상품리스트 탭으로
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		memberInfoDeleteButton.setBounds(195, 400, 97, 34);
		infoPanel.add(memberInfoDeleteButton);

		JPanel findIdPanel = new JPanel();
		memberTabbedPane.addTab("아이디찾기", null, findIdPanel, null);
		findIdPanel.setLayout(null);

		JLabel memberFindIdNameLabel = new JLabel("이름");
		memberFindIdNameLabel.setBounds(52, 174, 57, 15);
		findIdPanel.add(memberFindIdNameLabel);

		JLabel memberFindIdPhoneLabel = new JLabel("전화번호");
		memberFindIdPhoneLabel.setBounds(52, 228, 57, 15);
		findIdPanel.add(memberFindIdPhoneLabel);

		JButton memberFindIdCheckButton = new JButton("확인");
		memberFindIdCheckButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberService memberService;

				/******* 아이디 찾기 *******/
				try {
					// 객체선언
					memberService = new MemberService();
					String memberFindIdName = memberFindIdNameTextField.getText();
					String memberFindIdPhone = memberFindIdPhoneTextField.getText();
					Member member = memberService.findId(memberFindIdName, memberFindIdPhone);
					if (member == null) {
						JOptionPane.showMessageDialog(null, "아이디를 찾을 수 없습니다.", "ERROR_MESSAGE",
								JOptionPane.ERROR_MESSAGE);
					} else {
						String findId = "아이디 찾기 결과 : ";
						findId += member.getMId();
						JOptionPane.showMessageDialog(null, findId, "Find ID", JOptionPane.INFORMATION_MESSAGE);
						// 아이디 찾은 후 이름, 번호 텍스트 초기화
						memberFindIdNameTextField.setText("");
						memberFindIdPhoneTextField.setText("");
					}

				} catch (Exception e1) {

					e1.printStackTrace();
				}

			}
		});
		memberFindIdCheckButton.setBounds(75, 287, 228, 23);
		findIdPanel.add(memberFindIdCheckButton);

		memberFindIdNameTextField = new JTextField();
		memberFindIdNameTextField.setBounds(140, 171, 163, 21);
		findIdPanel.add(memberFindIdNameTextField);
		memberFindIdNameTextField.setColumns(10);

		memberFindIdPhoneTextField = new JTextField();
		memberFindIdPhoneTextField.setBounds(140, 225, 163, 21);
		findIdPanel.add(memberFindIdPhoneTextField);
		memberFindIdPhoneTextField.setColumns(10);

		JButton memberFindIdLoginButton = new JButton("로그인");
		memberFindIdLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberTabbedPane.setSelectedIndex(0);
			}
		});
		memberFindIdLoginButton.setBounds(75, 331, 97, 23);
		findIdPanel.add(memberFindIdLoginButton);

		JButton memberFindIdFindPwButton = new JButton("비밀번호 찾기");
		memberFindIdFindPwButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberTabbedPane.setSelectedIndex(4);
			}
		});
		memberFindIdFindPwButton.setBounds(184, 331, 119, 23);
		findIdPanel.add(memberFindIdFindPwButton);

		JPanel findPwPanel = new JPanel();
		memberTabbedPane.addTab("비밀번호찾기", null, findPwPanel, null);
		findPwPanel.setLayout(null);

		JLabel memberFindPwIdLabel = new JLabel("아이디");
		memberFindPwIdLabel.setBounds(51, 167, 57, 15);
		findPwPanel.add(memberFindPwIdLabel);

		JLabel memberFindPwNameLabel = new JLabel("이름");
		memberFindPwNameLabel.setBounds(51, 221, 57, 15);
		findPwPanel.add(memberFindPwNameLabel);

		JButton memberFindPwCheckButton = new JButton("확인");
		memberFindPwCheckButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberService memberService;

				/******* 비밀번호 찾기 *******/
				try {
					// 객체선언
					memberService = new MemberService();

					// member를 찾기 위한 파라미터 설정 -> text field에 작성된 text를 갖고 memberService의 findPw
					// method를 사용하여 member 객체를 가져옴
					String memberFindPwId = memberFindPwIdTextField.getText();
					String memberFindPwName = memberFindPwNameTextField.getText();
					Member member = memberService.findPw(memberFindPwId, memberFindPwName);

					if (member == null) {
						JOptionPane.showMessageDialog(null, "비밀번호를 찾을 수 없습니다.", "ERROR_MESSAGE",
								JOptionPane.ERROR_MESSAGE);
					} else {
						String findPw = "비밀번호 찾기 결과 : ";
						findPw += member.getMPw();
						JOptionPane.showMessageDialog(null, findPw, "Find Pw", JOptionPane.INFORMATION_MESSAGE);
						// 비밀번호 찾기 후 텍스트 초기화
						memberFindPwIdTextField.setText("");
						memberFindPwNameTextField.setText("");
					}

				} catch (Exception e1) {

					e1.printStackTrace();
				}

			}
		});
		memberFindPwCheckButton.setBounds(74, 280, 228, 23);
		findPwPanel.add(memberFindPwCheckButton);

		memberFindPwIdTextField = new JTextField();
		memberFindPwIdTextField.setColumns(10);
		memberFindPwIdTextField.setBounds(139, 164, 163, 21);
		findPwPanel.add(memberFindPwIdTextField);

		memberFindPwNameTextField = new JTextField();
		memberFindPwNameTextField.setColumns(10);
		memberFindPwNameTextField.setBounds(139, 218, 163, 21);
		findPwPanel.add(memberFindPwNameTextField);

		JButton memberFindPwLoginButton = new JButton("로그인");
		memberFindPwLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberTabbedPane.setSelectedIndex(0);
			}
		});
		memberFindPwLoginButton.setBounds(74, 324, 97, 23);
		findPwPanel.add(memberFindPwLoginButton);

		JButton memberFindPwfindIdButton = new JButton("아이디 찾기");
		memberFindPwfindIdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberTabbedPane.setSelectedIndex(3);
			}
		});
		memberFindPwfindIdButton.setBounds(183, 324, 119, 23);
		findPwPanel.add(memberFindPwfindIdButton);
	

	}// 생성자 끝

	/************** 로그아웃시 호출할메쏘드 ***************/
	public void logoutProcess() {
		this.loginMember = null;

		memberTabbedPane.setEnabledAt(0, true);		//로그인 탭 활성화
		memberTabbedPane.setEnabledAt(1, true);		//회원가입 탭 활성화
		memberTabbedPane.setEnabledAt(2, false);	//회원정보 탭 비활성화
		memberTabbedPane.setEnabledAt(3, true);		//아이디찾기 탭 활성화
		memberTabbedPane.setEnabledAt(4, true); 	//비밀번호 찾기 탭 활성화
		shopMainFrame.loginMember = null;
		memberTabbedPane.setSelectedIndex(0);	// 로그인 탭으로 전환
		memberIdTextField.setText("");
		memberPwField.setText("");
		shopMainFrame.loginMenuItem.setEnabled(true);
	}

	/************** 로그인성공시 호출할메쏘드 ***************/
	// 로그인 성공시 메인화면으로
	public void loginProcess(Member loginMember) throws Exception {
		this.loginMember = loginMember;

		memberTabbedPane.setEnabledAt(0, false);	//로그인 탭 활성화
		memberTabbedPane.setEnabledAt(1, false);	//회원가입 탭 활성화
		memberTabbedPane.setEnabledAt(2, true);		//회원정보 탭 비활성화
		memberTabbedPane.setEnabledAt(3, false);	//아이디찾기 탭 활성화
		memberTabbedPane.setEnabledAt(4, false);	//비밀번호 찾기 탭 활성화
		memberTabbedPane.setSelectedIndex(2);
		shopMainFrame.loginMember = loginMember;
		shopMainFrame.changePanel(ShopMainFrame.TOP_SHOP_MAIN_PANEL, 0, null);
		shopMainFrame.loginMenuItem.setEnabled(false);
		shopMainFrame.logoutMenuItem_1.setEnabled(true);
		

	}

	private void displayMemberInfo(Member member) {
		/**** 로그인한회원상세데이타보여주기 *****/
		memberInfoIdTextField.setText(member.getMId());
		memberInfoPwTextField.setText(member.getMPw());
		memberInfoNameTextField.setText(member.getMName());
		memberInfoPhoneTextField.setText(member.getMPhone());
		memberInfoAddressTextField.setText(member.getMAddress());
	}

	/************* 회원수정하기 활성화 비활성화 ****************/
	private void updateFormEnable(boolean b) {

		memberInfoPwTextField.setEditable(b);
		memberInfoNameTextField.setEditable(b);
		memberInfoPhoneTextField.setEditable(b);
		memberInfoAddressTextField.setEditable(b);
		memberUpdateButton.setEnabled(b);
	}
}// 클래스 끝