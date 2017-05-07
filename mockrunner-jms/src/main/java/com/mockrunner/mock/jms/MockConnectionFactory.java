package com.mockrunner.mock.jms;

import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Mock implementation of JMS <code>ConnectionFactory</code>.
 * Can be used as generic factory for JMS 1.1.
 * Also implements <code>QueueConnectionFactory</code> and
 * <code>TopicConnectionFactory</code> and can be used to
 * create queue and topic connections as well as generic
 * JMS 1.1 connections. It is recommended to use
 * {@link com.mockrunner.mock.jms.MockQueueConnectionFactory}
 * if you only use queues and
 * {@link com.mockrunner.mock.jms.MockTopicConnectionFactory}
 * if you only use topics.
 * This implementation is primary for generic JMS 1.1 connections
 * but can also be used, if a server provides one implementation
 * for both domains (which is not portable).
 */
public class MockConnectionFactory implements QueueConnectionFactory, TopicConnectionFactory, Serializable
{

    private static final long serialVersionUID = 1047767516970592805L;

    private DestinationManager destinationManager;
    private ConfigurationManager configurationManager;
    private List<MockConnection> connections;
    private JMSException exception;

    public MockConnectionFactory(DestinationManager destinationManager, ConfigurationManager configurationManager)
    {
        connections = new CopyOnWriteArrayList<>();
        this.destinationManager = destinationManager;
        this.configurationManager = configurationManager;
        exception = null;
    }

    @Override
    public Connection createConnection() throws JMSException
    {
        return createConnection(null, null);
    }

    @Override
    public Connection createConnection(String name, String password) throws JMSException
    {
        MockConnection connection = new MockConnection(destinationManager, configurationManager, name, password);
        connection.setJMSException(exception);
        connections.add(connection);
        return connection;
    }

    @Override
    public QueueConnection createQueueConnection() throws JMSException
    {
        return createQueueConnection(null, null);
    }

    @Override
    public QueueConnection createQueueConnection(String name, String password) throws JMSException
    {
        MockQueueConnection connection = new MockQueueConnection(destinationManager(), configurationManager(), name, password);
        connection.setJMSException(exception());
        connections().add(connection);
        return connection;
    }

    @Override
    public TopicConnection createTopicConnection() throws JMSException
    {
        return createTopicConnection(null, null);
    }

    @Override
    public TopicConnection createTopicConnection(String name, String password) throws JMSException
    {
        MockTopicConnection connection = new MockTopicConnection(destinationManager(), configurationManager(), name, password);
        connection.setJMSException(exception());
        connections().add(connection);
        return connection;
    }

    /**
     * Set an exception that will be passed to all
     * created connections. This can be used to
     * simulate server errors. Check out
     * {@link MockConnection#setJMSException}
     * for details.
     * @param exception the exception
     */
    public void setJMSException(JMSException exception)
    {
        this.exception = exception;
    }

    /**
     * Clears the list of connections
     */
    public void clearConnections()
    {
        connections.clear();
    }

    /**
     * Returns the connection with the specified index
     * or <code>null</code> if no such connection
     * exists.
     * @param index the index
     * @return the connection
     */
    public MockConnection getConnection(int index)
    {
        if(connections.size() <= index) return null;
        return connections.get(index);
    }

    /**
     * Returns the latest created connection
     * or <code>null</code> if no such connection
     * exists.
     * @return the connection
     */
    public MockConnection getLatestConnection()
    {
        if(connections.size() == 0) return null;
        return connections.get(connections.size() - 1);
    }

    protected DestinationManager destinationManager()
    {
        return destinationManager;
    }

    protected ConfigurationManager configurationManager()
    {
        return configurationManager;
    }

    protected List<MockConnection> connections()
    {
        return connections;
    }

    protected JMSException exception()
    {
        return exception;
    }
}
