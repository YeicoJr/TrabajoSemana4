package Mensajeria;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumidor {
    private static final String BROKER_URL = "tcp://localhost:61616"; 
    private static final String QUEUE_NAME = "TransaccionesQueue"; 

    public static void main(String[] args) {
        try {
            
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);

            
            MessageConsumer consumer = session.createConsumer(destination);

            
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if (message instanceof MapMessage) {
                        try {
							procesarTransaccion((MapMessage) message);
						} catch (JMSException e) {
							
							e.printStackTrace();
						}
                    }
                }
            });

           
            System.out.println("Esperando mensajes...");
            Thread.sleep(30000); 

           
            connection.close();
        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void procesarTransaccion(MapMessage message) throws JMSException {
 
        String tipo = message.getString("Tipo");
        String producto = message.getString("Producto");
        int cantidad = message.getInt("Cantidad");
        double precio = message.getDouble("Precio");

       
        System.out.println("Transacción procesada: " + tipo + " - " + producto + " - Cantidad: " + cantidad + " - Precio: " + precio);
    }
}
