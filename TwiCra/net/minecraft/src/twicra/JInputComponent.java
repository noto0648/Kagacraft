package net.minecraft.src.twicra;

import java.awt.Rectangle;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.font.TextHitInfo;
import java.awt.im.InputMethodRequests;
import java.text.AttributedCharacterIterator;
import java.text.AttributedCharacterIterator.Attribute;

import javax.swing.JComponent;

public class JInputComponent extends JComponent implements InputMethodListener, InputMethodRequests
{

	public JInputComponent()
	{
		super();
		addInputMethodListener(this);
	}

	@Override
	public InputMethodRequests getInputMethodRequests()
	{
		return this;
	}

	@Override
	public Rectangle getTextLocation(TextHitInfo offset)
	{
		return new Rectangle(0, 0);
	}

	@Override
	public TextHitInfo getLocationOffset(int x, int y)
	{
		return null;
	}

	@Override
	public int getInsertPositionOffset()
	{
		return 0;
	}

	@Override
	public AttributedCharacterIterator getCommittedText(int beginIndex, int endIndex, Attribute[] attributes)
	{
		return null;
	}

	@Override
	public int getCommittedTextLength()
	{
		return 0;
	}

	@Override
	public AttributedCharacterIterator cancelLatestCommittedText(Attribute[] attributes)
	{
		return null;
	}

	@Override
	public AttributedCharacterIterator getSelectedText(Attribute[] attributes)
	{
		return null;
	}

	@Override
	public void inputMethodTextChanged(InputMethodEvent event)
	{
		AttributedCharacterIterator iter = event.getText();
		if(iter == null)
		{
			repaint();
			return;
		}
	}

	@Override
	public void caretPositionChanged(InputMethodEvent event)
	{

	}

}
