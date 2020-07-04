package com.desktop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DesktopApplication extends Application {

	int driverId;
	String DriverName;
	
	@Override
	public void start(Stage primaryStage) {
		
		Button startJourney = new Button("Start journey");		
		Button endJourney = new Button("End journey");
		Button startDay = new Button(" Start Day");
		Button endDay = new Button("End Day");
		Text text1 = new Text();
		FlowPane fp = new FlowPane();
		fp.getChildren().addAll(startDay,endDay,startJourney,endJourney,text1);
		
	startJourney.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				text1.setText(startJourney(driverId));
			}
		});
	
	startDay.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			text1.setText(startDay(driverId));
		}
	});
	
	endJourney.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			text1.setText(endJourney(driverId));
		}
	});
	
	endDay.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			text1.setText(endDay(driverId));
		}
	});
		
		Scene loggedIn=new Scene(fp,250,250);
		
		Pane pane = new Pane();
		
		Text text = new Text("License:");
		text.setLayoutX(20);
		text.setLayoutY(80);
		TextField tf = new TextField();
		tf.setLayoutX(80);
		tf.setLayoutY(60);
		tf.setMaxWidth(100);
		Button  login = new Button("Login");
		login.setLayoutX(80);
		login.setLayoutY(110);
		
		login.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if(tf.getText().length()>1) {
					String license = tf.getText();
					driverId=getDriverId(tf.getText());
					if(driverId>0) {
						primaryStage.setScene(loggedIn);
						primaryStage.show();
					}
				}		
			}
		});
		
		pane.getChildren().addAll(text,tf,login);
		
		Scene login1 = new Scene(pane,250,250);
		
		primaryStage.setScene(login1);
		primaryStage.setTitle("Driver");
		primaryStage.show();
		
	}
	
	public String startJourney(int driverId) {
		URL req = null;
		HttpURLConnection conn = null;
		String message="";
		try {
			req = new URL("http://localhost:8080/TaxiDriverSystem/webapi/driver/startJourney/"+driverId);
			conn = (HttpURLConnection) req.openConnection();
			conn.setRequestMethod("POST");
			conn.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			message = reader.readLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return message;
	}

	public String startDay(int driverId) {
		URL req = null;
		HttpURLConnection conn = null;
		String message="";
		try {
			req = new URL("http://localhost:8080/TaxiDriverSystem/webapi/driver/startday/"+driverId);
			conn = (HttpURLConnection) req.openConnection();
			conn.setRequestMethod("POST");
			conn.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			message = reader.readLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return message;
	}

	
	public String endJourney(int driverId) {
		URL req = null;
		HttpURLConnection conn = null;
		String message="";
		try {
			req = new URL("http://localhost:8080/TaxiDriverSystem/webapi/driver/endJourney/"+driverId);
			conn = (HttpURLConnection) req.openConnection();
			conn.setRequestMethod("POST");
			conn.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			message = reader.readLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return message;
	}
	
	public String endDay(int driverId) {
		URL req = null;
		HttpURLConnection conn = null;
		String message="";
		try {
			req = new URL("http://localhost:8080/TaxiDriverSystem/webapi/driver/endday/"+driverId);
			conn = (HttpURLConnection) req.openConnection();
			conn.setRequestMethod("POST");
			conn.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			message = reader.readLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return message;
	}
	
	
	public int getDriverId(String license) {
		URL req = null;
		HttpURLConnection conn = null;
		try {
			req = new URL("http://localhost:8080/TaxiDriverSystem/webapi/driver/getdriver/"+license);
			conn = (HttpURLConnection) req.openConnection();
			conn.setRequestMethod("GET");
			conn.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			driverId = Integer.parseInt(reader.readLine());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return driverId;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
