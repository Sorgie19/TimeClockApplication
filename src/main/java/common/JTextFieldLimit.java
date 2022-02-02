package common;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument
{
    private final int limit;

    /**
     * Constructs an instance of this class.
     * @param limit
     */
    public JTextFieldLimit(int limit)
    {
        super();
        this.limit = limit;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException
    {
        if(str == null)
        {
            return;
        }

        if((getLength() + str.length()) <= limit)
        {
            super.insertString(offset, str, attr);
        }
    }
}