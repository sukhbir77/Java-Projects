import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FX_Control extends Application{
	
	public static void main(String [] args)
	{
		launch(args);
	}
	DB_Access db=new DB_Access();
	ArrayList<Phone> list=db.getAllProducts();
	Phone p=new Phone();
	Button show,add,delete,modify,search,submit,submit_delete,submit_modify,submit_search;
	TextField name,address,phone,id,mod,namem,addressm,phonem,searchid;
	Label name1,address1,phone1,id1,mod1,namem1,addressm1,phonem1,searchid1;
	TextArea details,details1,sdetails;
	 Alert alert = new Alert(AlertType.INFORMATION);
	 
	@Override
	
	public void start(Stage ps) throws Exception {

		show = new Button("Show");
		show.setStyle("-fx-background-color: yellow; ");
		
		add = new Button("Add");
		add.setStyle("-fx-background-color: green; ");
		
		delete = new Button("Delete");
		delete.setStyle("-fx-background-color: #ff0000; ");
		
		modify = new Button("Modify");
		modify.setStyle("-fx-background-color: blue; ");
		searchid = new TextField();
		searchid1 = new Label("ENTER NAME OR PHONE");
		search = new Button("Search");
		search.setStyle("-fx-background-color: grey; ");
		
		submit =new Button ("Submit");
		submit_delete = new Button("Delete");
		
		sdetails = new TextArea();
		
		submit_modify = new Button("Submit");
		submit_search = new Button("Submit");
		name1 = new Label("Name :");
		address1 = new Label("Address :");
		phone1 = new Label("Phone :");
		id1 = new Label("ENTER ID :");
		mod1= new Label("ENTER ID :");
		namem1 = new Label("NAME :");
		addressm1 = new Label("ADDRESS :");
		phonem1 = new Label("Phone :");
		
		details = new TextArea();
		details.setEditable(false);
		
		details1 = new TextArea();
		details1.setEditable(false);
		double height = 5 ;
		double width = 100;
		details1.setPrefHeight(height);
		details1.setPrefWidth(width);
		name = new TextField();
		address = new TextField();
		phone = new TextField();
		id = new TextField();
		mod = new TextField();
		namem = new TextField();
		addressm = new TextField();
		phonem = new TextField();
		
		HBox hbox=new HBox();
		
		hbox.setPadding(new Insets(30, 30, 30, 30));
		hbox.setSpacing(15);
		
		show.setOnAction(e->{
			Scanner s=new Scanner(System.in);
			ArrayList<Phone> list=db.getAllProducts();
			details.setText("");
			
			for (Phone p : list)
			{
				details1.setText("ID               Name                  Address                    Phone");
				details.setText(details.getText(0, details.getLength())+"\n"+p.getId()+"                 "+p.getName()+"                       "+p.getAddress()+"                   "+p.getPhone());
			}
			VBox vbox7 = new VBox();
			vbox7.getChildren().addAll(details1,details,hbox);
			Scene show=new Scene(vbox7);
			ps.setScene(show);
			ps.show();
		});
		
		add.setOnAction(e->{
			VBox vbox=new VBox();
			vbox.setPadding(new Insets(30, 30, 30, 30));
			vbox.setSpacing(15);
			vbox.getChildren().addAll(name1,name,address1,address,phone1,phone,submit,hbox);			
			Scene add = new Scene(vbox);
			ps.setScene(add);
			ps.show();
		});
		submit.setOnAction(e->
		{
			String nam=name.getText();
			String adr=address.getText();
			String ph=phone.getText();
			int setid=p.getId();
			if(valid(nam,adr,ph))
			{
			Phone p=new Phone(setid++,nam,adr,ph);	
			db.addProductV1(p);
			alert.setContentText("INFO ADDED SUCCESSFULLY!!!!!!");
			alert();
			name.setText("");
			address.setText("");
			phone.setText("");
		}	
		else 
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("MISSING");
			alert.setContentText("Please Enter the Full Information");

			alert.showAndWait();
		}
		});
		
		delete.setOnAction(e->
		{
			VBox vbox1 =new VBox();
			vbox1.setPadding(new Insets(30, 30, 30, 30));
			vbox1.setSpacing(15);
			vbox1.getChildren().addAll(id1,id,submit_delete,hbox);			
			Scene add = new Scene(vbox1);
			ps.setScene(add);
			ps.show();
		});
		
		submit_delete.setOnAction(e->{
			if(nonNumber(id.getText()))
			{
				int i=Integer.parseInt(id.getText());
				db.delProduct(i);
				list.remove(p);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("SUCCESS");
				alert.setHeaderText("DELETED");
				alert.setContentText("Deleted SUCCESSFULLY!!!!!!");
				alert.showAndWait();
				
				id.setText("");
			}
			else 
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Please Enter Valid Id");

				alert.showAndWait();
			}
			
		});

		modify.setOnAction(e->{
			VBox vbox2 =new VBox();
			vbox2.setPadding(new Insets(30, 30, 30, 30));
			vbox2.setSpacing(15);
			vbox2.getChildren().addAll(mod1,mod,namem1,namem,addressm1,addressm,phonem1,phonem,submit_modify,hbox);			
			Scene add = new Scene(vbox2);
			ps.setScene(add);
			ps.show();
			
		});
		
		submit_modify.setOnAction(e->{
			
			if(nonNumber(mod.getText()))
			{
				int i=Integer.parseInt(mod.getText());
				String n=namem.getText();
				String a=addressm.getText();
				String pn=phonem.getText();
				if(valid(n,a,pn))
				{
				Phone p1=new Phone(-1,n,a,pn);	
				db.modifyProduct(i, p1);
				alert.setContentText("Modified SUCCESSFULLY!!!!!!");
				alert();
				mod.setText("");
				namem.setText("");
				addressm.setText("");
				phonem.setText("");
				}
				else
				{
					alert.setContentText("Please Enter valid info");
					alert();
				}
			}
			else 
			{
				alert.setContentText("Please Enter Valid Info");
				alert();
			}
		});
		
		search.setOnAction(e->{
			
			VBox vbox1 =new VBox();
			vbox1.setPadding(new Insets(30, 30, 30, 30));
			vbox1.setSpacing(15);
			vbox1.getChildren().addAll(searchid1,searchid,submit_search,hbox);			
			Scene add = new Scene(vbox1);
			ps.setScene(add);
			ps.show();
		});
		
		submit_search.setOnAction(e->{
			String input = searchid.getText();
			
			ArrayList<Phone> foundProducts=db.searchProduct(input);
			sdetails.setText("");
			for (Phone p : foundProducts)
			{
				details1.setText("ID               Name                  Address               Phone");
				sdetails.setText(sdetails.getText(0, sdetails.getLength())+"\n       "+p.getId()+"             "+p.getName()+"                  "+p.getAddress()+"                         "+p.getPhone());
			}
			VBox vbox8 = new VBox();
			vbox8.getChildren().addAll(details1,sdetails,hbox);
			Scene show=new Scene(vbox8);
			ps.setScene(show);
			ps.show();
		});

		hbox.getChildren().addAll(show,add,delete,modify,search);
		
		Scene s = new Scene(hbox);
        ps.setScene(s);
        ps.setTitle("PhoneBook");
        ps.show();
		
	}
	 public boolean nonNumber(String check) {
	        if (check.isEmpty()) {
	            return false;
	        } else {
	            for (int i = 0; i < check.length(); i++) {
	                if (Character.isDigit(check.charAt(i)) );
	                else {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }

	 public boolean valid(String nam1, String add1, String ph1) {
	        if (nam1.isEmpty() || add1.isEmpty() || ph1.isEmpty()) {
	        	return false;
	            
	        }
	        else {
	            for (int i = 0; i < nam1.length(); i++) {
	                if (Character.isAlphabetic(nam1.charAt(i)));
	                else {
	                    return false;
	                }}
	                for (int j = 0; j < add1.length(); j++) {
		                if (Character.isDigit(add1.charAt(j)) || Character.isAlphabetic(add1.charAt(j)) );
		                else {
		                    return false;
		                }}
		                for (int k = 0; k < ph1.length(); k++) {
			                if (Character.isDigit(ph1.charAt(k)));
			                else {
			                    return false;
			                }}
	            }
	        return true;
	        }
	 public void alert()
	 {
		
			alert.showAndWait();
	 }


}
