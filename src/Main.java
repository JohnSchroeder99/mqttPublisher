import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.MqttSecurityException;

public class Main {

	static String publisherID = "Tester";

	static MqttAsyncClient publisher;

	static MqttConnectionOptions options;
	static MqttMessage message = new MqttMessage();

	public void mqttErrorOccurred(MqttException arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws MqttException {
		
		// setup the publisher that will publish messages to the mqtt broker
		publisher = new MqttAsyncClient("tcp://localhost:9997", publisherID);
		
		// setup connection options for the publisher
		setupConnections(publisher);

		//setup the message that is to be published by the publisher
		message.setPayload("Doing some work here on  Testing topic".getBytes());
		message.setQos(0);

		// continually try to publish the message every 3 seconds
		while (true) {
			try {
				Thread.sleep(3000);
				publisher.publish("Testing", message);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void setupConnections(MqttAsyncClient mqttClient) {
		System.out.println("Options setting up");
		options = new MqttConnectionOptions();
		options.setAutomaticReconnect(false);
		options.setCleanStart(true);
		options.setAutomaticReconnect(true);

		try {
			mqttClient.connect(options).waitForCompletion();
			System.out.println("Publisher connected and preparing to start publishing messages"); 
		} catch (MqttSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
