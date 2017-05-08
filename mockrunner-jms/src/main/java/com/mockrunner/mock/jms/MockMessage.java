package com.mockrunner.mock.jms;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageFormatException;
import javax.jms.MessageNotWriteableException;

import com.mockrunner.base.NestedApplicationException;

/**
 * Mock implementation of JMS <code>Message</code>.
 */
public class MockMessage implements Message, Cloneable, Serializable
{
    private static final long serialVersionUID = -2148569108190675500L;

    private static final String CORRELATION_ID_ENCODING = "ISO-8859-1";

    private String messageId;
    private long timestamp;
    private String correlationId;
    private Destination replyTo;
    private Destination destination;
    private int deliveryMode;
    private boolean redelivered;
    private String type;
    private long expiration;
    private int priority;
    private boolean acknowledged;
    private Map<String,Object> properties;
    private boolean isInWriteMode;
    private boolean isInWriteModeProperties;

    public MockMessage()
    {
        messageId = null;
        timestamp = System.currentTimeMillis();
        deliveryMode = DeliveryMode.PERSISTENT;
        redelivered = false;
        expiration = 0;
        priority = 4;
        acknowledged = false;
        properties = new HashMap<>();
        isInWriteMode = true;
        isInWriteModeProperties = true;
    }

    public boolean isAcknowledged()
    {
        return acknowledged;
    }

    @Override
    public String getJMSMessageID() throws JMSException
    {
        return messageId;
    }

    @Override
    public void setJMSMessageID(String messageId) throws JMSException
    {
        this.messageId = messageId;
    }

    @Override
    public long getJMSTimestamp() throws JMSException
    {
        return timestamp;
    }

    @Override
    public void setJMSTimestamp(long timestamp) throws JMSException
    {
        this.timestamp = timestamp;
    }

    @Override
    public byte[] getJMSCorrelationIDAsBytes() throws JMSException
    {
        if(null == correlationId) return null;
        try
        {
            return correlationId.getBytes(CORRELATION_ID_ENCODING);
        }
        catch(UnsupportedEncodingException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void setJMSCorrelationIDAsBytes(byte[] correlationId) throws JMSException
    {
        try
        {
            if(null == correlationId)
            {
                this.correlationId = null;
            }
            else
            {
                this.correlationId = new String(correlationId, CORRELATION_ID_ENCODING);
            }
        }
        catch(UnsupportedEncodingException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void setJMSCorrelationID(String correlationId) throws JMSException
    {
        this.correlationId = correlationId;
    }

    @Override
    public String getJMSCorrelationID() throws JMSException
    {
        return correlationId;
    }

    @Override
    public Destination getJMSReplyTo() throws JMSException
    {
        return replyTo;
    }

    @Override
    public void setJMSReplyTo(Destination replyTo) throws JMSException
    {
        this.replyTo = replyTo;
    }

    @Override
    public Destination getJMSDestination() throws JMSException
    {
        return destination;
    }

    @Override
    public void setJMSDestination(Destination destination) throws JMSException
    {
        this.destination = destination;
    }

    @Override
    public int getJMSDeliveryMode() throws JMSException
    {
        return deliveryMode;
    }

    @Override
    public void setJMSDeliveryMode(int deliveryMode) throws JMSException
    {
        this.deliveryMode = deliveryMode;
    }

    @Override
    public boolean getJMSRedelivered() throws JMSException
    {
        return redelivered;
    }

    @Override
    public void setJMSRedelivered(boolean redelivered) throws JMSException
    {
        this.redelivered = redelivered;
    }

    @Override
    public String getJMSType() throws JMSException
    {
        return type;
    }

    @Override
    public void setJMSType(String type) throws JMSException
    {
        this.type = type;
    }

    @Override
    public long getJMSExpiration() throws JMSException
    {
        return expiration;
    }

    @Override
    public void setJMSExpiration(long expiration) throws JMSException
    {
        this.expiration = expiration;
    }

    @Override
    public int getJMSPriority() throws JMSException
    {
        return priority;
    }

    @Override
    public void setJMSPriority(int priority) throws JMSException
    {
        this.priority = priority;
    }

    @Override
    public void clearProperties() throws JMSException
    {
        isInWriteModeProperties = true;
        properties.clear();
    }

    @Override
    public boolean propertyExists(String name) throws JMSException
    {
        return properties.containsKey(name);
    }

    @Override
    public boolean getBooleanProperty(String name) throws JMSException
    {
        Object value = getObjectProperty(name);
        if(value == null)
        {
            return false;
        }
        if(value instanceof String)
        {
            return Boolean.valueOf((String) value);
        }
        if(value instanceof Boolean)
        {
            return (Boolean) value;
        }
        throw new MessageFormatException(buildConversionErrorMessage(name, value, "boolean"));
    }

    @Override
    public byte getByteProperty(String name) throws JMSException
    {
        Object value = getObjectProperty(name);
        if(value == null)
        {
            throw new MessageFormatException(getNullPropertyMessage(name));
        }
        if(value instanceof String)
        {
            return Byte.valueOf((String) value);
        }
        if(value instanceof Byte)
        {
            return ((Number)value).byteValue();
        }
        throw new MessageFormatException(buildConversionErrorMessage(name, value, "byte"));
    }

    @Override
    public short getShortProperty(String name) throws JMSException
    {
        Object value = getObjectProperty(name);
        if(value == null)
        {
            throw new MessageFormatException(getNullPropertyMessage(name));
        }
        if(value instanceof String)
        {
            return Short.valueOf((String) value);
        }
        if((value instanceof Short) || (value instanceof Byte))
        {
            return ((Number)value).shortValue();
        }
        throw new MessageFormatException(buildConversionErrorMessage(name, value, "short"));
    }

    @Override
    public int getIntProperty(String name) throws JMSException
    {
        Object value = getObjectProperty(name);
        if(value == null)
        {
            throw new MessageFormatException(getNullPropertyMessage(name));
        }
        if(value instanceof String)
        {
            return Integer.valueOf((String) value);
        }
        if((value instanceof Integer) || (value instanceof Short) || (value instanceof Byte))
        {
            return ((Number)value).intValue();
        }
        throw new MessageFormatException(buildConversionErrorMessage(name, value, "int"));
    }

    @Override
    public long getLongProperty(String name) throws JMSException
    {
        Object value = getObjectProperty(name);
        if(value == null)
        {
            throw new MessageFormatException(getNullPropertyMessage(name));
        }
        if(value instanceof String)
        {
            return Long.valueOf((String) value);
        }
        if((value instanceof Long) || (value instanceof Integer) || (value instanceof Short) || (value instanceof Byte))
        {
            return ((Number)value).longValue();
        }
        throw new MessageFormatException(buildConversionErrorMessage(name, value, "long"));
    }

    @Override
    public float getFloatProperty(String name) throws JMSException
    {
        Object value = getObjectProperty(name);
        if(value == null)
        {
            throw new MessageFormatException(getNullPropertyMessage(name));
        }
        if(value instanceof String)
        {
            return Float.valueOf((String) value);
        }
        if(value instanceof Float)
        {
            return ((Number)value).floatValue();
        }
        throw new MessageFormatException(buildConversionErrorMessage(name, value, "float"));
    }

    @Override
    public double getDoubleProperty(String name) throws JMSException
    {
        Object value = getObjectProperty(name);
        if(value == null)
        {
            throw new MessageFormatException(getNullPropertyMessage(name));
        }
        if(value instanceof String)
        {
           return Double.valueOf((String) value);
        }
        if((value instanceof Double) || (value instanceof Float))
        {
            return ((Number)value).doubleValue();
        }
        throw new MessageFormatException(buildConversionErrorMessage(name, value, "double"));
    }

    @Override
    public String getStringProperty(String name) throws JMSException
    {
        Object value = getObjectProperty(name);
        if(null == value) return null;
        return value.toString();
    }

    @Override
    public Object getObjectProperty(String name) throws JMSException
    {
        return properties.get(name);
    }

    @Override
    public Enumeration<String> getPropertyNames() throws JMSException
    {
        return new Vector<>(properties.keySet()).elements();
    }

    @Override
    public void setBooleanProperty(String name, boolean value) throws JMSException
    {
        setObjectProperty(name, value);
    }

    @Override
    public void setByteProperty(String name, byte value) throws JMSException
    {
        setObjectProperty(name, value);
    }

    @Override
    public void setShortProperty(String name, short value) throws JMSException
    {
        setObjectProperty(name, value);
    }

    @Override
    public void setIntProperty(String name, int value) throws JMSException
    {
        setObjectProperty(name, value);
    }

    @Override
    public void setLongProperty(String name, long value) throws JMSException
    {
        setObjectProperty(name, value);
    }

    @Override
    public void setFloatProperty(String name, float value) throws JMSException
    {
        setObjectProperty(name, value);
    }

    @Override
    public void setDoubleProperty(String name, double value) throws JMSException
    {
        setObjectProperty(name, value);
    }

    @Override
    public void setStringProperty(String name, String value) throws JMSException
    {
        setObjectProperty(name, value);
    }

    @Override
    public void setObjectProperty(String name, Object object) throws JMSException
    {
        if(!isInWriteModeProperties)
        {
            throw new MessageNotWriteableException("Message is in read mode");
        }
        if(null == name || name.length() <= 0)
        {
            throw new IllegalArgumentException("Property names must not be null or empty strings");
        }
        if(null == object) return;
        if((object instanceof String) || (object instanceof Number) || (object instanceof Boolean))
        {
            properties.put(name, object);
            return;
        }
        throw new MessageFormatException(object.getClass().getName() + " not a valid type");
    }

    @Override
    public void acknowledge() throws JMSException
    {
        acknowledged = true;
    }

    @Override
    public void clearBody() throws JMSException
    {
        isInWriteMode = true;
    }

    public void setReadOnly(boolean isReadOnly)
    {
        isInWriteMode = !isReadOnly;
    }

    public void setReadOnlyProperties(boolean isReadOnly)
    {
        isInWriteModeProperties = !isReadOnly;
    }

    @Override
    public Object clone()
    {
        try
        {
            MockMessage clone = (MockMessage)super.clone();
            clone.properties = new HashMap<>(properties);
            return clone;
        }
        catch(CloneNotSupportedException exc)
        {
            throw new NestedApplicationException(exc);
        }
    }

    protected boolean isInWriteMode()
    {
        return isInWriteMode;
    }

    protected String getNullPropertyMessage(String name) {
      return String.format("Property %s was null", name);
    }

  protected String buildConversionErrorMessage(String name, Object value, final String targetType) {
      return String.format("Cannot convert property %s of type %s to %s ", name,value.getClass().getName(), targetType);
  }
}
