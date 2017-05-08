package com.mockrunner.mock.jms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MessageEOFException;
import javax.jms.MessageFormatException;
import javax.jms.MessageNotReadableException;
import javax.jms.MessageNotWriteableException;

import com.mockrunner.base.NestedApplicationException;

/**
 * Mock implementation of JMS <code>BytesMessage</code>.
 */
public class MockBytesMessage extends MockMessage implements BytesMessage
{

    private static final long serialVersionUID = 6308036429588965733L;
    private DataOutputStream outStream;
    private ByteArrayOutputStream byteOutStream;
    private DataInputStream inStream;

    public MockBytesMessage()
    {
        try
        {
            clearBody();
        }
        catch(JMSException exc)
        {
            throw new NestedApplicationException(exc);
        }
    }

    @Override
    public long getBodyLength() throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        return outStream.size();
    }

    @Override
    public boolean readBoolean() throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        try
        {
            return inStream.readBoolean();
        }
        catch(EOFException exc)
        {
            throw new MessageEOFException(exc.getMessage());
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public byte readByte() throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        try
        {
            return inStream.readByte();
        }
        catch(EOFException exc)
        {
            throw new MessageEOFException(exc.getMessage());
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public int readUnsignedByte() throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        try
        {
            return inStream.readByte();
        }
        catch(EOFException exc)
        {
            throw new MessageEOFException(exc.getMessage());
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public short readShort() throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        try
        {
            return inStream.readShort();
        }
        catch(EOFException exc)
        {
            throw new MessageEOFException(exc.getMessage());
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public int readUnsignedShort() throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        try
        {
            return inStream.readShort();
        }
        catch(EOFException exc)
        {
            throw new MessageEOFException(exc.getMessage());
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public char readChar() throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        try
        {
            return inStream.readChar();
        }
        catch(EOFException exc)
        {
            throw new MessageEOFException(exc.getMessage());
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public int readInt() throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        try
        {
            return inStream.readInt();
        }
        catch(EOFException exc)
        {
            throw new MessageEOFException(exc.getMessage());
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public long readLong() throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        try
        {
            return inStream.readLong();
        }
        catch(EOFException exc)
        {
            throw new MessageEOFException(exc.getMessage());
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public float readFloat() throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        try
        {
            return inStream.readFloat();
        }
        catch(EOFException exc)
        {
            throw new MessageEOFException(exc.getMessage());
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public double readDouble() throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        try
        {
            return inStream.readDouble();
        }
        catch(EOFException exc)
        {
            throw new MessageEOFException(exc.getMessage());
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public String readUTF() throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        try
        {
            return inStream.readUTF();
        }
        catch(EOFException exc)
        {
            throw new MessageEOFException(exc.getMessage());
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public int readBytes(byte[] data) throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        try
        {
            return inStream.read(data);
        }
        catch(EOFException exc)
        {
            throw new MessageEOFException(exc.getMessage());
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public int readBytes(byte[] data, int length) throws JMSException
    {
        if(isInWriteMode())
        {
            throw new MessageNotReadableException("Message is in write mode");
        }
        try
        {
            return inStream.read(data, 0, length);
        }
        catch(EOFException exc)
        {
            throw new MessageEOFException(exc.getMessage());
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void writeBoolean(boolean value) throws JMSException
    {
        if(!isInWriteMode())
        {
            throw new MessageNotWriteableException("Message is in read mode");
        }
        try
        {
            outStream.writeBoolean(value);
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void writeByte(byte value) throws JMSException
    {
        if(!isInWriteMode())
        {
            throw new MessageNotWriteableException("Message is in read mode");
        }
        try
        {
            outStream.writeByte(value);
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void writeShort(short value) throws JMSException
    {
        if(!isInWriteMode())
        {
            throw new MessageNotWriteableException("Message is in read mode");
        }
        try
        {
            outStream.writeShort(value);
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void writeChar(char value) throws JMSException
    {
        if(!isInWriteMode())
        {
            throw new MessageNotWriteableException("Message is in read mode");
        }
        try
        {
            outStream.writeChar(value);
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void writeInt(int value) throws JMSException
    {
        if(!isInWriteMode())
        {
            throw new MessageNotWriteableException("Message is in read mode");
        }
        try
        {
            outStream.writeInt(value);
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void writeLong(long value) throws JMSException
    {
        if(!isInWriteMode())
        {
            throw new MessageNotWriteableException("Message is in read mode");
        }
        try
        {
            outStream.writeLong(value);
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void writeFloat(float value) throws JMSException
    {
        if(!isInWriteMode())
        {
            throw new MessageNotWriteableException("Message is in read mode");
        }
        try
        {
            outStream.writeFloat(value);
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void writeDouble(double value) throws JMSException
    {
        if(!isInWriteMode())
        {
            throw new MessageNotWriteableException("Message is in read mode");
        }
        try
        {
            outStream.writeDouble(value);
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void writeUTF(String value) throws JMSException
    {
        if(!isInWriteMode())
        {
            throw new MessageNotWriteableException("Message is in read mode");
        }
        try
        {
            outStream.writeUTF(value);
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void writeBytes(byte[] data) throws JMSException
    {
        if(!isInWriteMode())
        {
            throw new MessageNotWriteableException("Message is in read mode");
        }
        try
        {
            outStream.write(data);
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void writeBytes(byte[] data, int offset, int length) throws JMSException
    {
        if(!isInWriteMode())
        {
            throw new MessageNotWriteableException("Message is in read mode");
        }
        try
        {
            outStream.write(data, offset, length);
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
    }

    @Override
    public void writeObject(Object object) throws JMSException
    {
        if(!isInWriteMode())
        {
            throw new MessageNotWriteableException("Message is in read mode");
        }
        if(object instanceof Byte)
        {
            writeByte((Byte) object);
            return;
        }
        if(object instanceof Short)
        {
            writeShort((Short) object);
            return;
        }
        if(object instanceof Integer)
        {
            writeInt((Integer) object);
            return;
        }
        if(object instanceof Long)
        {
            writeLong((Long) object);
            return;
        }
        if(object instanceof Float)
        {
            writeFloat((Float) object);
            return;
        }
        if(object instanceof Double)
        {
            writeDouble((Double) object);
            return;
        }
        if(object instanceof Character)
        {
            writeChar((Character) object);
            return;
        }
        if(object instanceof Boolean)
        {
            writeBoolean((Boolean) object);
            return;
        }
        if(object instanceof String)
        {
            writeUTF((String)object);
            return;
        }
        if(object instanceof byte[])
        {
            writeBytes((byte[])object);
            return;
        }
        throw new MessageFormatException(object.getClass().getName() + " is an invalid type");
    }

    @Override
    public void reset() throws JMSException
    {
        setReadOnly(true);
        try
        {
            outStream.flush();
        }
        catch(IOException exc)
        {
            throw new JMSException(exc.getMessage());
        }
        inStream = new DataInputStream(new ByteArrayInputStream(byteOutStream.toByteArray()));
    }

    @Override
    public void clearBody() throws JMSException
    {
        super.clearBody();
        byteOutStream = new ByteArrayOutputStream();
        outStream = new DataOutputStream(byteOutStream);
    }

    /**
     * Returns a copy of the underlying byte data regardless if the message
     * is in read or write mode.
     * @return the byte data
     */
    public byte[] getBytes()
    {
        try
        {
            outStream.flush();
        }
        catch(IOException exc)
        {
            throw new RuntimeException(exc.getMessage());
        }
        return byteOutStream.toByteArray();
    }

    /**
     * Compares the underlying byte data.
     */
    @Override
    public boolean equals(Object otherObject)
    {
        if(null == otherObject) return false;
        if(!(otherObject instanceof MockBytesMessage)) return false;
        MockBytesMessage otherMessage = (MockBytesMessage)otherObject;
        byte[] firstData = getBytes();
        byte[] secondData = otherMessage.getBytes();
        return Arrays.equals(firstData, secondData);
    }

    @Override
    public int hashCode()
    {
        int value = 17;
        byte[] data = getBytes();
        for (byte aData : data) {
            value = (31 * value) + aData;
        }
        return value;
    }

    @Override
    public Object clone()
    {
        MockBytesMessage message = (MockBytesMessage)super.clone();
        try
        {
            message.clearBody();
            message.outStream.write(getBytes());
            return message;
        }
        catch(Exception exc)
        {
            throw new NestedApplicationException(exc);
        }
    }

    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append(this.getClass().getName()).append(": [");
        byte[] data = getBytes();
        for(int ii = 0; ii < data.length; ii++)
        {
            buffer.append(data[ii]);
            if(ii < data.length - 1)
            {
                buffer.append(", ");
            }
        }
        buffer.append("]");
        return buffer.toString();
    }
}
