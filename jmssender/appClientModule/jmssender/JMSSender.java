package jmssender;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.jms.*;
import javax.naming.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class JMSSender extends JFrame {

	static String imagepath;

	
	
	
	  JButton button,button2;
	   JLabel Imagelabel;

	    public JMSSender() throws Exception{
	        super("Image");
	        getContentPane().setBackground(Color.PINK);
	        button = new JButton("Select");
	        button.setBackground(Color.LIGHT_GRAY);
	        button.setFont(new Font("MV Boli", Font.BOLD, 15));
	        button.setBounds(535,13,100,30);
	        button2 = new JButton("Send");
	        button2.setBackground(Color.LIGHT_GRAY);
	        button2.setFont(new Font("MV Boli", Font.BOLD, 15));
	        button2.setBounds(535,337,100,30);
	        Imagelabel = new JLabel();
	        Imagelabel.setBounds(12,10,479,357);
	        getContentPane().add(button);
	        getContentPane().add(button2);
	        getContentPane().add(Imagelabel);
	        
	        button.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	            	JFileChooser file = new JFileChooser();
	                file.setCurrentDirectory(new File(System.getProperty("user.home")));
	                //filter the files
	                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
	                file.addChoosableFileFilter(filter);
	                int result = file.showSaveDialog(null);
	                 //if the user click on save in Jfilechooser
	                if(result == JFileChooser.APPROVE_OPTION){
	                    File selectedFile = file.getSelectedFile();
	                    String path = selectedFile.getAbsolutePath();
	                    imagepath = path;
	                    Imagelabel.setIcon(ResizeImage(path));
	                }
	                 //if the user click on save in Jfilechooser


	                else if(result == JFileChooser.CANCEL_OPTION){
	                    System.out.println("No File Selected");
	                }
	              }
	          });
	        
	        // conversion de l'image récupérée en tableau de bits
	        
	        BufferedImage bImage = ImageIO.read(new File(imagepath));
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        ImageIO.write(bImage, "jpg", bos );
	        byte [] data = bos.toByteArray();
	        
	        
	        button2.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	            	
	            	
	            	Context ctx = null;
					try {
						ctx = ClientUtil.getInitialContext();
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		
	        		ConnectionFactory fabrique = null;
					try {
						fabrique = (ConnectionFactory)ctx.lookup("jms/CF1");
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	        		Destination destination = null;
					try {
						destination = (Destination)ctx.lookup("DES1");
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	        		
	        		
	        		
	        		Connection connexion =null;
					try {
						connexion = fabrique.createConnection();
					} catch (JMSException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		Session session =null;
					try {
						session = connexion.createSession(false, Session.AUTO_ACKNOWLEDGE);
					} catch (JMSException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	        		MessageProducer envoi = null;
					try {
						envoi = session.createProducer(destination);
					} catch (JMSException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	        		BytesMessage message = null;
					try {
						message = session.createBytesMessage();
					} catch (JMSException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	        		try {
						message.writeBytes(data);
					} catch (JMSException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 	
	        		try {
						envoi.send(message);
					} catch (JMSException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	        		try {
						connexion.close();
					} catch (JMSException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		System.out.println("Message sent");
	            
	              }
	          });
	        
	        
	        
	        
	        
	        
	        
	        getContentPane().setLayout(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        setSize(700,427);
	        setVisible(true);
	        }
	    public ImageIcon ResizeImage(String ImagePath)
	    {
	        ImageIcon MyImage = new ImageIcon(ImagePath);
	        Image img = MyImage.getImage();
	        Image newImg = img.getScaledInstance(Imagelabel.getWidth(), Imagelabel.getHeight(), Image.SCALE_SMOOTH);
	        ImageIcon image = new ImageIcon(newImg);
	        return image;
	    }
	            
	
	
	
	public static void main(String[] args) throws NamingException, JMSException, Exception {
		
		
		
		new JMSSender();
		
		

	}

}
