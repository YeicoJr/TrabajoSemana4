package Mensajeria;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Productor {
    private static final String BROKER_URL = "tcp://localhost:61616"; 
    private static final String QUEUE_NAME = "TransaccionesQueue"; 

    public static void main(String[] args) {
        try {
            // Configuración de la conexión al servidor ActiveMQ
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Creación de la sesión y la cola
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);

            // Creación del productor
            MessageProducer producer = session.createProducer(destination);

            // Envío de mensajes de transacciones (puedes adaptar esta lógica según tus necesidades)
            enviarTransaccion(session, producer, "Compra", "Producto A", 10, 15.99);
            enviarTransaccion(session, producer, "Venta", "Producto B", 5, 25.99);

            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private static void enviarTransaccion(Session session, MessageProducer producer, String tipo, String producto, int cantidad, double precio) throws JMSException {
        // Creación del mensaje y data
        MapMessage message = session.createMapMessage();
        message.setString("Tipo", tipo);
        message.setString("Producto", producto);
        message.setInt("Cantidad", cantidad);
        message.setDouble("Precio", precio);

        // Envío del mensaje con data
        producer.send(message);
        System.out.println("Mensaje enviado: " + tipo + " - " + producto);
    }
}
